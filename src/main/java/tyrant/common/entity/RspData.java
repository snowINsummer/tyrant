package tyrant.common.entity;


import tyrant.common.constants.Constants;

/**
 * Created by snow.zhang on 2016/6/6.
 */
public class RspData {

    /**
     * 响应状态码
     */
    private String code = Constants.CODE_FAILED;

    /**
     * 相应数据
     */
    private Object data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
