package cn.iaimi.freeimgtools.common;

/**
 * 错误码
 * @author zhenghongyi
 * {@code @date} 2023/11/21 9:31
 */
public enum ErrorCode {

    SUCCESS(0, "ok", ""),
    PARAMS_ERROR(40000, "请求参数错误", ""),
    NULL_ERROR(40001, "请求数据为空", ""),
    NOT_LOGIN(40100, "未登录", ""),
    NO_AUTH(40101, "无权限", ""),
    NOT_FOUND_ERROR(40400, "请求数据不存在", ""),
    FORBIDDEN(40300, "禁止操作", ""),
    SYSTEM_ERROR(50000, "系统内部异常", ""),
    OPERATION_ERROR(50001, "操作失败", "");


    private final int code;
    /**
     * 状态码信息
     */
    private final String message;
    /**
     * 状态码详情
     */
    private final String description;

    ErrorCode(int code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}
