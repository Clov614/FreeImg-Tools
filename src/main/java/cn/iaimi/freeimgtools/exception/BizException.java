package cn.iaimi.freeimgtools.exception;


import cn.iaimi.freeimgtools.common.ErrorCode;

/**
 * 业务异常类
 * @author zhenghongyi
 * {@code @date} 2023/11/21 10:03
 */
public class BizException extends RuntimeException{

    private final int code;

    private final String description;

    public BizException(String message, int code, String description) {
        super(message);
        this.code = code;
        this.description = description;
    }

    public BizException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = errorCode.getDescription();
    }

    public BizException(ErrorCode errorCode, String description) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
