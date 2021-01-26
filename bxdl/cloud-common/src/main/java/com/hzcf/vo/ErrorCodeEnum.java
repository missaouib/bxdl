package com.hzcf.vo;

/**
 * <dl>
 * <dt>类名：com.hzcf.vo</dt>
 * <dd>描述: 结算单管理</dd>
 * <dd>创建时间：下午 16:18 2018/10/23 0023 </dd>
 * <dd>创建人：朱广伟</dd>
 * <dt>版本历史: </dt>
 * </dl>
 */
public enum  ErrorCodeEnum {
    ;

    private String errorCode;
    private String errorMsg;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
