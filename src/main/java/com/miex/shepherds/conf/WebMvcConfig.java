package com.miex.shepherds.conf;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig extends WebMvcAutoConfiguration {

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/dashboard/**").addResourceLocations("classpath:/static/");
//        registry.addResourceHandler("/dashboard/js/**").addResourceLocations("classpath:/static/js/");
//    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        if (!this.resourceProperties.isAddMappings()) {
//            logger.debug("Default resource handling disabled");
//            return;
//        }
//        Integer cachePeriod = this.resourceProperties.getCachePeriod();
//        if (!registry.hasMappingForPattern("/webjars/**")) {
//            customizeResourceHandlerRegistration(
//                    registry.addResourceHandler("/webjars/**")
//                            .addResourceLocations(
//                                    "classpath:/META-INF/resources/webjars/")
//                            .setCachePeriod(cachePeriod));
//        }
//        String staticPathPattern = this.mvcProperties.getStaticPathPattern();
//        if (!registry.hasMappingForPattern(staticPathPattern)) {
//            customizeResourceHandlerRegistration(
//                    registry.addResourceHandler(staticPathPattern)
//                            .addResourceLocations(
//                                    this.resourceProperties.getStaticLocations())
//                            .setCachePeriod(cachePeriod));
//        }
//    }
}
