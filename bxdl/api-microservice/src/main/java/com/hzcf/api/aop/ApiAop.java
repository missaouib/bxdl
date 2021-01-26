package com.hzcf.api.aop;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hzcf.api.async.MyAsyncTask;
import com.hzcf.api.feign.BasicFgignClient;
import com.netflix.discovery.converters.Auto;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.UUID;

/**
 * <dl>
 * <dt>类名：com.hzcf.api.aop</dt>
 * <dd>描述: api模块controller层日志打印</dd>
 * <dd>创建时间：上午 10:42 2018/11/5 0005 </dd>
 * <dd>创建人：朱广伟</dd>
 * <dt>版本历史: </dt>
 * </dl>
 */
@SuppressWarnings("ALL")
@Aspect
@Component
public class ApiAop {

    private Logger logger = Logger.getLogger(ApiAop.class);

    @Autowired
    MyAsyncTask myAsyncTask;

    private static ThreadLocal<Date> startTime = new ThreadLocal<Date>();
    private static ThreadLocal<String> uri = new ThreadLocal<String>();
    private static ThreadLocal<String> param = new ThreadLocal<String>();
    private static ThreadLocal<String> heaerParam = new ThreadLocal<String>();
    private static ObjectMapper objectMapper = new ObjectMapper();
    /*
     * 定义拦截规则：拦截com.hzcf.api.controller..*(..))包下面的所有类中，有@RequestMapping注解的方法
     */


    @Pointcut("execution(* com.hzcf.api.controller..*.*(..))")
    public void controllerMethodPointcut() {
    }

    /*
     * 请求方法前打印内容
     *
     * @param joinPoint
     */
    @Before(value = "controllerMethodPointcut()")
    public void doBefore(JoinPoint joinPoint){
        // 请求开始时间
        startTime.set(new Date());
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        // 获取请求头
        Enumeration<String> enumeration = request.getHeaderNames();
        JSONObject object = new JSONObject();
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            object.put(name, request.getHeader(name));
        }
        heaerParam.set(object.toJSONString());
        uri.set(request.getRequestURI());
        String method = request.getMethod();
        StringBuffer params = new StringBuffer();
        if (HttpMethod.GET.toString().equals(method)) { // get请求
            String queryString = request.getQueryString();
            if (StringUtils.isNotBlank(queryString)) {
                try{
                    params.append(URLEncoder.encode(queryString, "UTF-8"));
                }catch (Exception e){
                    logger.error("编码错误", e);
                }

            }
        } else { //其他请求
            Object[] paramsArray = joinPoint.getArgs();
            if (paramsArray != null && paramsArray.length > 0) {
                for (int i = 0; i < paramsArray.length; i++) {
                    params.append(paramsArray[i].toString()).append(",");
                }
            }
        }
        param.set(params.toString());
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        myAsyncTask.asyncInsertApiLog(heaerParam.get(),param.get(),uri.get(),format.format(startTime.get()));

    }

}
