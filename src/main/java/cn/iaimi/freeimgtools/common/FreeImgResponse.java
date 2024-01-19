package cn.iaimi.freeimgtools.common;

import lombok.Data;
import lombok.ToString;

/**
 * 图床接口响应
 * @author clov614
 * {@code @date} 2024/1/3 10:31
 */
@Data
@ToString
public class FreeImgResponse<D> {

    /**
     * 状态，true 或 false
     */
    private Boolean status;

    /**
     * 描述信息
     */
    private String message;

    private D data;
}
