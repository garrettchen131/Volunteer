package cn.sicnu.common.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountVO implements Serializable {
    private String username;
    private String email;
    private String phone;
    private String idCard;
}
