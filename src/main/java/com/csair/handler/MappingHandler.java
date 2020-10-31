package com.csair.handler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

/**
 * context-path处理器
 *
 * @author LiRenGui
 * @date 2020年10月31日19:23:34
 */

public class MappingHandler extends RequestMappingHandlerMapping {
    @Value("${context-path}")
    private String contextPath;

    @Override
    public void afterPropertiesSet() {
        super.afterPropertiesSet();
    }

    @Override
    protected RequestMappingInfo getMappingForMethod(Method method, Class handlerType) {
        RequestMappingInfo info = super.getMappingForMethod(method, handlerType);
        if(info!=null){
            return RequestMappingInfo.paths(contextPath).build().combine(info);
        }
        return null;
    }
}
