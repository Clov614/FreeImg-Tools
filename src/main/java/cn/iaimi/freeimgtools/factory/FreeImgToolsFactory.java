package cn.iaimi.freeimgtools.factory;

import cn.iaimi.freeimgtools.model.config.FreeImgConfig;
import cn.iaimi.freeimgtools.tools.FreeImgCrudTool;

/**
 * @author Clov614
 * @version 1.0
 * DATE 2024/4/3
 */
public class FreeImgToolsFactory {

    private FreeImgConfig freeImgConfig ;

    private FreeImgCrudTool freeImgCrudTool;

    private static final String DEFAULT_UPLOAD_IMG_URL = "https://www.freeimg.cn/api/v1/upload";

    public FreeImgToolsFactory() {
        freeImgConfig = new FreeImgConfig().setUploadImageUrl(DEFAULT_UPLOAD_IMG_URL);
        freeImgCrudTool = new FreeImgCrudTool();
    }

    public static FreeImgToolsFactory create() {
        FreeImgToolsFactory factory = new FreeImgToolsFactory();
        factory.freeImgConfig = new FreeImgConfig().setUploadImageUrl(DEFAULT_UPLOAD_IMG_URL);
        factory.freeImgCrudTool = new FreeImgCrudTool();
        return factory;
    }

    public FreeImgToolsFactory setUploadImageUrl(String uploadImgUrl) {
        freeImgConfig.UPLOAD_IMAGE_URL = uploadImgUrl;
        return this;
    }

    public FreeImgToolsFactory setFreeImageToken(String freeImgToken) {
        this.freeImgConfig.FREEIMG_TOKEN = freeImgToken;
        return this;
    }

    public FreeImgToolsFactory setFreeImageAlbumId(Integer freeImgAlbumId) {
        this.freeImgConfig.FREEIMG_ALBUM_ID = freeImgAlbumId;
        return this;
    }

    public FreeImgCrudTool build() {
        return freeImgCrudTool.setFreeImgConfig(freeImgConfig);
    }

}
