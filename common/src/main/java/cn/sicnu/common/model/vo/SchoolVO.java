package cn.sicnu.common.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class SchoolVO implements Serializable {
    private String username;
    private String email;
    private String phone;
    private String schoolName;
    private String nickname;
    private String avatarUrl;
}
