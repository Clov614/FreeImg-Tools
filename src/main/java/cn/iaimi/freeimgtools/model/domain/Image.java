package cn.iaimi.freeimgtools.model.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 图片信息表
 */

@Data
@Accessors(fluent = true)
public class Image implements Serializable {
    /**
     * id
     */

    private Long id;

    /**
     * 图片唯一key
     */

    private String imgKey;

    /**
     * 图片名称
     */
    private String name;

    /**
     * 图片访问URL
     */
    private String url;

    /**
     * 删除图片的URL
     */
    private String deleteUrl;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     *
     */
    private Date updateTime;

    /**
     * 是否删除
     */

    private Integer isDelete;

    private static final long serialVersionUID = 9215242974589763891L;
}