package cn.sicnu.oauth.mapper;

import cn.sicnu.common.model.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface IAccountMapper {

    Optional<Account> selectByAccountInfo(@Param("account") String account);

    Optional<Account> selectByPhone(@Param("phone") String phone);

    Optional<Account> selectByUsername(@Param("username") String username);

    Optional<Account> selectByEmail(@Param("email") String email);

    Account selectById(@Param("id") long id);

    int save(@Param("account") Account account);

}
