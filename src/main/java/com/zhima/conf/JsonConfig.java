package com.zhima.conf;

import java.math.BigInteger;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;


@Configuration
public class JsonConfig extends WebMvcConfigurationSupport {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    	 FastJsonHttpMessageConverter fastConverter = 
    		        new FastJsonHttpMessageConverter();

    		        FastJsonConfig fastJsonConfig = new FastJsonConfig();
    		        SerializeConfig serializeConfig = SerializeConfig.globalInstance;
    		        serializeConfig.put(BigInteger.class, ToStringSerializer.instance);
    		        serializeConfig.put(Long.class, ToStringSerializer.instance);
    		        serializeConfig.put(Long.TYPE, ToStringSerializer.instance);
    		        fastJsonConfig.setSerializeConfig(serializeConfig);
    		        fastConverter.setFastJsonConfig(fastJsonConfig);
    		        converters.add(fastConverter);

    }
}