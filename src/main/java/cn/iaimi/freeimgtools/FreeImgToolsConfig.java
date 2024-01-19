package cn.iaimi.freeimgtools;

import cn.iaimi.freeimgtools.model.config.FreeImgConfig;
import cn.iaimi.freeimgtools.tools.FreeImgCrudTool;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author clov614
 * {@code @date} 2024/1/19 9:51
 */
@Configuration
@ConfigurationProperties("cloverapi.free-img")
@Data
@ComponentScan
public class FreeImgToolsConfig {

    /**
     * 上传接口
     */
    private String uploadImgUrl = "https://www.freeimg.cn/api/v1/upload";

    /**
     * 图床接口认证token
     */
    private String FreeImgToken;

    /**
     * 上传的相册ID
     */
    private Integer FreeImgAlbumId;

    @Bean
    public FreeImgConfig freeImgConfig() {
        return new FreeImgConfig(uploadImgUrl, FreeImgToken, FreeImgAlbumId);
    }

    @Bean
    public FreeImgCrudTool freeImgCrudTool() {
        return new FreeImgCrudTool();
    }
}
