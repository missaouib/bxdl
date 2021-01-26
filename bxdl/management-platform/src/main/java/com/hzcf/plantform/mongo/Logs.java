package com.hzcf.plantform.mongo;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * <dl>
 * <dt>类名：com.hzcf.basic.pojo</dt>
 * <dd>描述: 结算单管理</dd>
 * <dd>创建时间：下午 15:52 2018/8/14 0014 </dd>
 * <dd>创建人：朱广伟</dd>
 * <dt>版本历史: </dt>
 * </dl>
 */

@Document(collection = "logs")
public class Logs implements Serializable{
    @Id
    private String id;
    private String headerInfo;//请求header信息
    private String requestURL;//请求地址
    private String callTime; //调用时间
    private String createTime; //创建时间

    private String param;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getHeaderInfo() {
        return headerInfo;
    }

    public void setHeaderInfo(String headerInfo) {
        this.headerInfo = headerInfo;
    }

    public String getCallTime() {
        return callTime;
    }

    public void setCallTime(String callTime) {
        this.callTime = callTime;
    }

    public String getRequestURL() {
        return requestURL;
    }

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }
}
