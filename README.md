# FreeImg-Tools

关于[FreeImg图床](https://www.freeimg.cn/)的调用SDK

## 开始使用

### 1. 引入 Maven 依赖

```xml
<dependency>
    <groupId>cn.iaimi</groupId>
    <artifactId>FreeImg-Tools</artifactId>
    <version>0.0.2</version>
</dependency>
```

### 2. 配置 application.yml

在 `application.xml` 中配置 Token 和 AlbumID(可选)

```yaml
cloverapi:
  free-img:
    # 图床的调用 token
    free-img-token: Bearer 153|zENXxxxxaB1C6sjxxxxxC2fVaxxxdUPpX6xxxxxxY
    # 相册id，可选  （在相册访问url中可知）
    free-img-album-id: 102
```

### 3. 使用示例

这里使用的是 SpringBoot 的测试类作为使用 demo，如下：

```java
@SpringBootTest
@ActiveProfiles({"work"})
public class FreeImgToolTest {

    @Resource
    private FreeImgCrudTool freeImgCrudTool;

    @Test
    void uploadImgByFileTest() throws IOException {

        UploadImgRes uploadImgRes = freeImgCrudTool.uploadImage(getFile());
        System.out.println(uploadImgRes);
        System.out.println(uploadImgRes.getData());
        System.out.println(uploadImgRes.getData().getLinks());
    }

    @Test
    void uploadImgByStreamTest() throws IOException {
        ResourceLoader loader = new DefaultResourceLoader();
        org.springframework.core.io.Resource resource = loader.getResource("classpath:/image.png");
        UploadImgRes uploadImgRes = freeImgCrudTool.uploadImage(resource.getInputStream());
        System.out.println(uploadImgRes);
        System.out.println(uploadImgRes.getData());
        System.out.println(uploadImgRes.getData().getLinks());
    }

    /**
     * 获取类路径下的图片文件
     * @return
     * @throws IOException
     */
    private static File getFile() throws IOException {
        ResourceLoader loader = new DefaultResourceLoader();
        org.springframework.core.io.Resource resource = loader.getResource("classpath:/image.png");
        return resource.getFile();
    }

    @Test
    void deleteTest() {
        // https://www.freeimg.cn/delete/uyq5wp/a1101ccdb1e9a066238b8ae972383f46
        boolean b = freeImgCrudTool
                .deleteImg("https://www.freeimg.cn/delete/uyq5wp/a1101ccdb1e9a066238b8ae972383f46");
        System.out.println(b);
    }
}
```

#### 将 FreeImgCrudTool 依赖注入进来使用

```java
@Resource
private FreeImgCrudTool freeImgCrudTool;
```

#### 第二种使用方式-工厂模式

```java
public class UploadImageToolTest {

    private final String FREEIMG_TOKEN = "Bearer 100|fvxxxxxxxxxxxxXP";
    private final Integer FREEIMG_ALBUM_ID = 102;


    @Test
    public void useFactoryTest() throws IOException {
        FreeImgCrudTool freeImgCrudTool = FreeImgToolsFactory.create()
                .setFreeImageToken(FREEIMG_TOKEN)  // "Bearer 100|fvxxxxxxxxxxxxXP"
                .setFreeImageAlbumId(FREEIMG_ALBUM_ID) // 相册id  102
                .build();
        // 上传照片
        cn.iaimi.freeimgtools.model.domain.Image image = freeImgCrudTool.upload(getFile());
        System.out.println(image);
        // 删除照片
        boolean isSuccess = freeImgCrudTool.delete(image);// 推荐使用delete 也可以使用 freeImgCrudTool.deleteImg(<传入deleleUrl>)
        if (isSuccess) {
            System.out.println("删除成功");
        } else {
            System.out.println("删除失败");
        }
    }

    private static File getFile() throws IOException {
        ResourceLoader loader = new DefaultResourceLoader();
        Resource resource = loader.getResource("classpath:/image.png");
        String absolutePath = resource.getFile().getAbsolutePath();
        System.out.println(absolutePath);
        return resource.getFile();
    }
}
```