package cn.sicnu.common.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ActivityDTO implements Serializable {
    private String title;
    private String content;
    private Long score;
    private Integer totalNumber;
    private String startTime;
    private String endTime;
    private String address;
    private String subtitle;
    private String imageUrl;
}
