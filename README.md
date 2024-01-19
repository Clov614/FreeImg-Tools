# FreeImg-Tools

关于[FreeImg图床](https://www.freeimg.cn/)的调用SDK

## 开始使用

### 1. 引入 Maven 依赖

```xml
<dependency>
    <groupId>cn.iaimi</groupId>
    <artifactId>FreeImg-Tools</artifactId>
    <version>0.0.1</version>
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

