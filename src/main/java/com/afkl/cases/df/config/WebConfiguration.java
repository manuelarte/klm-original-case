package com.afkl.cases.df.config;

import com.afkl.cases.df.services.MetricRequestService;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/index.html").addResourceLocations("classpath:/static/index.html");
    }

    @Bean
    public FilterRegistrationBean<MetricRequestFilter> loggingFilter(final MetricRequestService metricRequestService){
        FilterRegistrationBean<MetricRequestFilter> registrationBean
                = new FilterRegistrationBean<>();

        registrationBean.setFilter(new MetricRequestFilter(metricRequestService));
        registrationBean.addUrlPatterns("/users/*");

        return registrationBean;
    }

}
