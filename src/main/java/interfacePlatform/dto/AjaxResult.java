package interfacePlatform.dto;

import interfacePlatform.core.dict.ResponseCode;

/**
 * //TODO 添加类/接口功能描述
 * @author zsh
 */
public class AjaxResult {
    private Object status;

    private String message;

    private Object data;

    private String key;

    public AjaxResult() {
        super();
    }
    
    /**
     * 
     * @param status 状态码  eg:ResponseCode.success.getCode()
     * @param message 描述   eg:ResponseCode.success.getMsg()
     * @param key   请求的url,用户拦截做业务逻辑处理
     * @param data  返回的数据
     */
    public AjaxResult(Object status, String message, String key, Object data) {
        super();
        this.status = status;
        this.message = message;
        this.data = data;
        this.key = key;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
