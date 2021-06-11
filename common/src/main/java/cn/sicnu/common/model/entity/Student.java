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
public class Student extends BaseModel {
    private Long accountId;
    private String realName;
    private String nickname;
    private String schoolName;
    private String studentNumber;
    private String avatarUrl;
}
