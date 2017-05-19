package org.learnj.spring.boot.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.learnj.spring.boot.web.rest.filter.CardNumberDecryptFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Loster on 5/6/17.
 */
@Component
public class RestWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {

    @Bean
    private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(ObjectMapper objectMapper) {
        return new RestMessageConverter(objectMapper);
    }

    @Bean
    private FilterRegistrationBean cardNumberDecryptFilterRegistrationBean() {
        FilterRegistrationBean registration = new FilterRegistrationBean(new CardNumberDecryptFilter());
        registration.addUrlPatterns("/*");
        return registration;
    }

//    @Override
//    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//    }
//
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        super.configureMessageConverters(converters);
//    }
//
//    @Override
//    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
//
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new ExceptionHandlerInterceptor()).addPathPatterns("/**");
//        super.addInterceptors(registry);
//    }
}
