package cn.com.scitc.base;

/**
 * 自定义异常
 * @author jswzj
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 4790642019499595070L;

    private int code = 400;

    public BusinessException() {
        this(400, "业务异常");
    }

    public BusinessException(int code) {
        this(code, "业务异常");
    }

    public BusinessException(String msg) {
        this(400, msg);
    }

    public BusinessException(int code, String msg) {
        super(msg);
        this.setCode(code);
    }

    /**
     * code
     *
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(int code) {
        this.code = code;
    }

}
