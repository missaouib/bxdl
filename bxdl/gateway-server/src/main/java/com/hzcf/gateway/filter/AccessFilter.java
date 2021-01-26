/*package com.hzcf.gateway.filter;


import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class AccessFilter extends ZuulFilter {

   private static Logger log = LoggerFactory.getLogger(AccessFilter.class);

   *//**
    * pre：可以在请求被路由之前调用
     route：在路由请求时候被调用
     post：在route和error过滤器之后被调用
     error：处理请求时发生错误时被调用
    *//*
   @Override
   public String filterType() {
       //前置过滤器
       return "pre";
   }

   @Override
   public int filterOrder() {
       //优先级，数字越大，优先级越低
       return 0;
   }

   @Override
   public boolean shouldFilter() {
       //是否执行该过滤器，true代表需要过滤
       return true;
   }

   @Override
   public Object run() {
       RequestContext ctx = RequestContext.getCurrentContext();
       HttpServletRequest request = ctx.getRequest();

       log.info("send {} request to {}", request.getMethod(), request.getRequestURL().toString());

       //获取传来的参数accessToken
       Object token = request.getParameter("token");
       if(token == null) {
           log.warn("access token is empty");
           //过滤该请求，不往下级服务去转发请求，到此结束
           ctx.setSendZuulResponse(false);
           ctx.setResponseStatusCode(401);
           ctx.setResponseBody("{\"result\":\"accessToken is empty!\"}");
           return null;
       }
       //如果有token，则进行路由转发
       log.info("access token ok");
       //这里return的值没有意义，zuul框架没有使用该返回值
       return null;
   }
}
*/