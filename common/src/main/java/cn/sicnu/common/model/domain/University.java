package cn.sicnu.common.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
public class University implements Serializable {
    private String schoolName;
    private String englishName;
}
