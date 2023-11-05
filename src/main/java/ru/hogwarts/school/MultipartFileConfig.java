package ru.hogwarts.school;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

public class MultipartFileConfig {

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.parse("5000KB"));
        factory.setMaxRequestSize(DataSize.parse("5000KB"));
        return factory.createMultipartConfig();
    }
}
