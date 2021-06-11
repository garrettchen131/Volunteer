package cn.sicnu.common.model.vo;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ActivityVO implements Serializable {
    private Long id;
    private String title;
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
