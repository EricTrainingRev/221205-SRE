package com.example.boot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
 * The @Configuration annotation tells Spring that we are changing the default behavior of the Spring Boot
 * implementation. In this case, by implementing WebMvcConfigurer, we are adding our custom made
 * HandlerInterceptor to our Spring Web module, and this will allow Spring to know whether to execute the
 * prehandle/posthandle methods as defined in our custom interceptor
 */

@Configuration
public class WebMVCConfig implements WebMvcConfigurer { // Spring Web is the new name for Spring MVC
    
    @Autowired
    private BasicInterceptor basicInterceptor;
    @Autowired
    private LoggingInterceptor loggingInterceptor;
    @Autowired
    private AuthenticationInterceptor authenticationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /*
         * addInterceptor tells spring to keep track of the basicInterceptor that is created at runtime
         * addPathPatterns tells Spring what http url patterns should be intercepted by our interceptor
         */
        registry.addInterceptor(basicInterceptor).addPathPatterns("/**").order(Ordered.LOWEST_PRECEDENCE);
        registry.addInterceptor(loggingInterceptor).addPathPatterns("/**").order(Ordered.HIGHEST_PRECEDENCE);
        registry.addInterceptor(authenticationInterceptor).addPathPatterns("/api/**").order(1);
        
    }

    

}
