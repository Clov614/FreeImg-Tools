package cn.iaimi.freeimgtools.model.dto;


import cn.iaimi.freeimgtools.common.FreeImgResponse;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author clov614
 * {@code @date} 2024/1/3 13:16
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UploadImgRes extends FreeImgResponse<UploadImgRes.DataImg> {

    @Data
    public class DataImg {
        /**
         * 图片唯一密钥
         */
        private String key;

        /**
         * 图片名称
         */
        private String name;

        private String md5;

        private Links links;
    }

    @Data
    public class Links {

        /**
         * 图片访问 url
         */
        private String url;

        /**
         * 缩略图 url
         */
        @SerializedName("thumbnail_url")
        private String thumbnailUrl;

        /**
         * 图片删除 url
         */
        @SerializedName("delete_url")
        private String deleteUrl;
    }

}
