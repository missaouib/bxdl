package com.hzcf.plantform.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;

@Configuration
public class WebDateConfigBeans {

    @Autowired
    RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    @PostConstruct
    public void initEditableValidation(){
        ConfigurableWebBindingInitializer configurableWebBindingInitializer = (ConfigurableWebBindingInitializer) requestMappingHandlerAdapter.getWebBindingInitializer();
        if(configurableWebBindingInitializer.getConversionService()!=null){
            GenericConversionService service = (GenericConversionService) configurableWebBindingInitializer.getConversionService();
            service.addConverter(new DateConverter());
        }
    }

}
