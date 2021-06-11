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
public class Record extends BaseModel {
    private Long studentId;

    private Long activityId;

    private String title;

    private Long score;

    private Integer status;

}
