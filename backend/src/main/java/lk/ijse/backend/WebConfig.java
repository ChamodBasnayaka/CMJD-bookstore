package lk.ijse.backend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${upload.derectory}")
    private String uploadDirectory;

    @Value("${coverImages.derectory}")
    private String coverImageDirectory;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/***")
                .addResourceLocations("file:" + uploadDirectory + "/");

        registry.addResourceHandler("/coverImage/***").addResourceLocations("file:" + coverImageDirectory + "/");

    }
}
