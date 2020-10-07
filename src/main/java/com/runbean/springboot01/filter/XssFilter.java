package com.runbean.springboot01.filter;

import com.fasterxml.jackson.core.json.PackageVersion;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.runbean.springboot01.request.XssAndSqlHttpServletRequestWrapper;
import com.runbean.springboot01.request.XssStringJsonDeserializer;
import com.runbean.springboot01.request.XssStringJsonSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
@WebFilter(filterName = "xssFilter", urlPatterns = "/*", asyncSupported = true)
@Component
public class XssFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("进入 doFilter...");
        HttpServletRequest req = (HttpServletRequest) request;
        XssAndSqlHttpServletRequestWrapper xssRequestWrapper = new XssAndSqlHttpServletRequestWrapper(req);
        chain.doFilter(xssRequestWrapper, response);
        System.out.println("出 doFilter...");
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

    @Bean
    @Primary
    public ObjectMapper xssObjectMapper(Jackson2ObjectMapperBuilder builder) {
        System.out.println("进入 xssObjectMapper...");
        // 解析器
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        // 注册xss解析器
       /* SimpleModule xssModule = new SimpleModule("XssStringJsonSerializer");
        xssModule.addSerializer(new XssStringJsonSerializer());
        objectMapper.registerModule(xssModule);*/
        SimpleModule jsonStringModule = new SimpleModule("JsonStringModule", PackageVersion.VERSION);
        jsonStringModule.addSerializer(new XssStringJsonSerializer());
        jsonStringModule.addDeserializer(String.class,new XssStringJsonDeserializer());
        objectMapper.registerModule(jsonStringModule);
        // 返回
        return objectMapper;
    }
}
