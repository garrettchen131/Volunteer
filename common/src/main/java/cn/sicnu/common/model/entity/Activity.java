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
public class Activity extends BaseModel {
    private Long schoolId;
    private String title;
    private String content;
    private Long score;
    private Integer status;
    private Integer personNumber;
    private Integer totalNumber;
    private Integer star;
    private String startTime;
    private String endTime;
    private String address;
    private String subtitle;
    private String imageUrl;

}
