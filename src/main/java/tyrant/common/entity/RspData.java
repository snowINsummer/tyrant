package tyrant.common.entity;


import tyrant.common.constants.Constants;

/**
 * Created by snow.zhang on 2016/6/6.
 */
public class RspData {

    /**
     * 响应状态码
     */
    private int code = Constants.CODE_FAILED;

    private String message = "操作成功";

    /**
     * 相应数据
     */
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
