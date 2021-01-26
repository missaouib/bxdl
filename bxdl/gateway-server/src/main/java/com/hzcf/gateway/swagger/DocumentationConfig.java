package com.hzcf.gateway.swagger;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

/**
 *<dl>
 *<dt>类名：DocumentationConfig.java</dt>
 *<dd>描述: Swagger 资源文档配置类</dd> 
 *<dd>创建时间： 2018年8月8日 下午3:27:41</dd>
 *<dd>创建人： TieGuowei </dd>
 *<dt>版本历史: </dt>
 * <pre>
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2018年8月8日 下午3:27:41    TieGuowei       1.0        1.0 Version 
 * </pre>
 *</dl>
 */
@Component
@Primary
public class DocumentationConfig implements SwaggerResourcesProvider{

	@Override
    public List<SwaggerResource> get() {
         List resources = new ArrayList<>();
            resources.add(swaggerResource("金融云平台微服务接口", "/api/v2/api-docs", "1.0"));
            return resources;
    }

     private SwaggerResource swaggerResource(String name, String location, String version) {
            SwaggerResource swaggerResource = new SwaggerResource();
            swaggerResource.setName(name);
            swaggerResource.setLocation(location);
            swaggerResource.setSwaggerVersion(version);
            return swaggerResource;
     }
	
}
