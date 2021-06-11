package cn.sicnu.common.model.entity;

import cn.sicnu.common.model.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentStar extends BaseModel {
    private Long studentId;
    private Long commentId;
}
