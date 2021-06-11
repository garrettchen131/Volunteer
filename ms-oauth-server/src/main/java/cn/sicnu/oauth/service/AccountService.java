package cn.sicnu.oauth.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.sicnu.common.model.dto.AccountDTO;
import cn.sicnu.common.model.entity.Account;
import cn.sicnu.common.model.vo.AccountVO;
import cn.sicnu.common.utils.AssertUtil;
import cn.sicnu.oauth.mapper.IAccountMapper;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final IAccountMapper accountMapper;

    /**
     * 通过邮箱用户注册
     *
     * @param accountDTO
     * @param role
     * @return 返回主键
     */
    public Long register(AccountDTO accountDTO, String role) {
        // 验证用户名是否已注册
        checkAccountIsExist(accountDTO);
        // 注册
        // 密码加密
        accountDTO.setPassword(DigestUtil.bcrypt(accountDTO.getPassword().trim()));
        val account = Account.builder().build();
        BeanUtil.copyProperties(accountDTO, account, false);
        account.setRoles(role);
        if (account.getIdCard() == null) {
            account.setIdCard("");
        }
        accountMapper.save(account);
        return account.getId();
    }

    public void checkAccountIsExist(AccountDTO accountDTO) {
        val usernameOpt = accountMapper.selectByUsername(accountDTO.getUsername().trim());
        val phoneOpt = accountMapper.selectByPhone(accountDTO.getPhone().trim());
        val emailOpt = accountMapper.selectByEmail(accountDTO.getEmail().trim());
        AssertUtil.isTrue(usernameOpt.isPresent(), "用户名已存在，请重新输入");
        AssertUtil.isTrue(phoneOpt.isPresent() , "电话号码已存在，请重新输入");
        AssertUtil.isTrue(emailOpt.isPresent(), "邮箱已存在，请重新输入");
    }

    public void checkEmailIsExist(String email) {
        val emailOpt = accountMapper.selectByEmail(email);
        AssertUtil.isTrue(emailOpt.isPresent(), "邮箱已存在，请重新输入");
    }

    public void checkPhoneIsExist(String phone) {
        val emailOpt = accountMapper.selectByPhone(phone);
        AssertUtil.isTrue(emailOpt.isPresent(), "电话号码已存在，请重新输入");
    }

    public AccountVO getAccountInfo(long accountId) {
        val account = accountMapper.selectById(accountId);
        val accountVo = new AccountVO();
        BeanUtil.copyProperties(account, accountVo, false);
        return accountVo;
    }
}
