package app.bank.configue;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebMvcConfiguration implements WebMvcConfigurer {
    @Value("${fileStorage.image_location}")
    String fileStorageLocation;
    @Value("${fileStorage.image_client}")
    String clientLocation;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(clientLocation)
                .addResourceLocations("file:"+fileStorageLocation);

    }

}
