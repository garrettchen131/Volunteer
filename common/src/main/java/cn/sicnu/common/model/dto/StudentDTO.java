package cn.sicnu.common.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(description = "注册用户信息")
@Data
public class StudentDTO implements Serializable {
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("手机号")
    private String phone;
    @ApiModelProperty("邮箱")
    private String email;
    @ApiModelProperty("验证码")
    private String verifyCode;
    @ApiModelProperty("真实姓名")
    private String realName;
    @ApiModelProperty("昵称")
    private String nickname;
    @ApiModelProperty("学校名称")
    private String schoolName;
    @ApiModelProperty("学号")
    private String studentNumber;
    @ApiModelProperty("头像")
    private String avatarUrl;
    @ApiModelProperty("身份证")
    private String idCard;
}
