package one.vladimir.impl.services.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login_pres");
        registry.addViewController("/index").setViewName("index_pres");
        registry.addViewController("/").setViewName("index_pres");
        registry.addViewController("/request").setViewName("request_pres");
        registry.addViewController("/crewman").setViewName("crewman_pres");
        registry.addViewController("/statistics").setViewName("statistics_pres");
    }
}