package com.firstproject.basicecommercewebsite;

import com.firstproject.basicecommercewebsite.resolver.LearnResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
  @Override
  public void addArgumentResolvers(
    List<HandlerMethodArgumentResolver> argumentResolvers) {
    argumentResolvers.add(new LearnResolver());
  }
}
