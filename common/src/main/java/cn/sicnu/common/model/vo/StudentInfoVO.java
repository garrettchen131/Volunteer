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
public class StudentInfoVO implements Serializable {
    private Long id;
    private String schoolName;
    private String phone;
    private String email;
    private String studentNumber;
    private String avatarUrl;
    private String nickname;
    private String realName;
    private String username;
    private Long score;
}
