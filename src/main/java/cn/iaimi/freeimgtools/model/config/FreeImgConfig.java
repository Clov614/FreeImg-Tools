package cn.iaimi.freeimgtools.model.config;

/**
 * @author clov614
 * {@code @date} 2024/1/19 10:00
 */
public class FreeImgConfig {

    /**
     * 上传接口
     */
    public String UPLOAD_IMAGE_URL;

    /**
     * 图床接口认证token
     */
    public String FREEIMG_TOKEN;

    /**
     * 上传的相册ID
     */
    public Integer FREEIMG_ALBUM_ID;

    public FreeImgConfig() {}

    public FreeImgConfig(String uploadImgUrl, String freeImgToken, Integer freeImgAlbumId) {
        this.UPLOAD_IMAGE_URL = uploadImgUrl;
        this.FREEIMG_TOKEN = freeImgToken;
        this.FREEIMG_ALBUM_ID = freeImgAlbumId;
    }

    public FreeImgConfig setUploadImageUrl(String uploadImgUrl) {
        this.UPLOAD_IMAGE_URL = uploadImgUrl;
        return this;
    }

    public FreeImgConfig setFreeImageToken(String freeImgToken) {
        this.FREEIMG_TOKEN = freeImgToken;
        return this;
    }

    public FreeImgConfig setFreeImageAlbumId(Integer freeImgAlbumId) {
        this.FREEIMG_ALBUM_ID = freeImgAlbumId;
        return this;
    }

}
