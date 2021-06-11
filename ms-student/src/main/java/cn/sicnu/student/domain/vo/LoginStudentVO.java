package cn.sicnu.student.domain.vo;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginStudentVO implements Serializable {

    private String nickname;
    private String token;
    private String avatarUrl;

}