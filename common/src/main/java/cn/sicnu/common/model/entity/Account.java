package cn.sicnu.common.model.entity;

import cn.sicnu.common.model.base.BaseModel;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@With
@ToString
@EqualsAndHashCode(callSuper = false)
public class Account extends BaseModel {
    private String username;
    private String password;
    private String email;
    private String phone;
    private String roles;
    private String idCard;
}
