package cn.iaimi.freeimgtools.tools;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.iaimi.freeimgtools.common.ErrorCode;
import cn.iaimi.freeimgtools.exception.BizException;
import cn.iaimi.freeimgtools.model.config.FreeImgConfig;
import cn.iaimi.freeimgtools.model.domain.Image;
import cn.iaimi.freeimgtools.model.dto.UploadImgRes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * 图床工具类
 *
 * @author clov614
 * {@code @date} 2024/1/3 15:20
 */
public class FreeImgCrudTool {

    @Resource
    private FreeImgConfig freeImgConfig;

    private static final String ERROR_STR = "文件参数 || 流 为空";

    public UploadImgRes uploadImage(File file) {
        // 确保文件参数不为空
        if (file == null) {
            throw new BizException(ErrorCode.PARAMS_ERROR, ERROR_STR);
        }
        // 将InputStream 转为 File
        String basePath = System.getProperty("user.dir");
        File localFile = new File(basePath + "/temp_image.jpg");
        try {
            // 使用工具方法保存InputStream到文件
            FileUtils.copyFile(file, localFile);
        } catch (IOException e) {
            throw new BizException(ErrorCode.SYSTEM_ERROR, e.getMessage());
        }

        return getUploadImgRes(file);
    }

    public UploadImgRes uploadImage(MultipartFile multipartFile) {
        // 确保参数不为空
        if (multipartFile == null) {
            throw new BizException(ErrorCode.PARAMS_ERROR, ERROR_STR);
        }
        // 将MultipartFile 转为 File
        String basePath = System.getProperty("user.dir");
        File localFile = new File(basePath + "/" + multipartFile.getOriginalFilename());
        try {
            multipartFile.transferTo(localFile);

        } catch (IOException e) {
            throw new BizException(ErrorCode.SYSTEM_ERROR, e.getMessage());
        }

        // 创建参数映射
        return getUploadImgRes(localFile);
    }

    public UploadImgRes uploadImage(InputStream inputStream) {
        // 确保参数不为空
        if (inputStream == null) {
            throw new BizException(ErrorCode.PARAMS_ERROR, ERROR_STR);
        }
        // 将InputStream 转为 File
        String basePath = System.getProperty("user.dir");
        File localFile = new File(basePath + "/temp_image.jpg");
        try {
            // 使用工具方法保存InputStream到文件
            FileUtils.copyInputStreamToFile(inputStream, localFile);
        } catch (IOException e) {
            throw new BizException(ErrorCode.SYSTEM_ERROR, e.getMessage());
        }

        // 创建参数映射
        return getUploadImgRes(localFile);
    }

    private UploadImgRes getUploadImgRes(File localFile) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("file", localFile);
        paramMap.put("permission", 0); // 私有
        if (freeImgConfig.FREEIMG_ALBUM_ID != null) { // 判断一下相册id是否需要传
            paramMap.put("album_id", freeImgConfig.FREEIMG_ALBUM_ID);
        }

        String body = HttpRequest.post(freeImgConfig.UPLOAD_IMAGE_URL)
                .header("Authorization", freeImgConfig.FREEIMG_TOKEN)
                .form(paramMap).execute().body();
        boolean delete = localFile.delete();
        if (!delete) {
            throw new BizException(ErrorCode.SYSTEM_ERROR, "删除临时图片失败");
        }
        Gson gson = new GsonBuilder()
                .setLenient()// json宽松
                .enableComplexMapKeySerialization()//支持Map的key为复杂对象的形式
                .serializeNulls() //智能null
                .setPrettyPrinting()// 调教格式
                .disableHtmlEscaping() //默认是GSON把HTML 转义的
                .create();
        return gson.fromJson(body, UploadImgRes.class);
    }

    /**
     * 根据delete_url请求删除
     *
     * @param deleteUrl 删除url image字段中有
     * @return 是否删除成功
     */
    public boolean deleteImg(String deleteUrl) {
        HttpResponse res = HttpRequest.get(deleteUrl)
                .header("Authorization", freeImgConfig.FREEIMG_TOKEN).execute();
        return res.isOk();
    }

    /**
     * 根据 key 删除图片
     *
     * @param image 图片对象
     * @return 是否删除成功
     */
    @Deprecated
    public boolean deleteImg(Image image) {
        String key = image.imgKey();
        String deleteUrl = "https://www.freeimg.cn/images/" + key;

        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("key", key);

        HttpResponse res = HttpRequest.delete(deleteUrl)
                .header("Authorization", freeImgConfig.FREEIMG_TOKEN)
                .form(paramMap)
                .execute();

        if (res.isOk() || res.getStatus() == 302) {
            System.out.println(res.body());
            // 解析返回的JSON
            JSONObject obj = JSONUtil.parseObj(res.body());
            return obj.getBool("status");
        } else {
            return false;
        }
    }
}
