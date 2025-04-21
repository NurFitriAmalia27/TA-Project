package web.sekolah.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/admin/guru/api/guru/**")
                .allowedOrigins("*")  // Membuka akses dari semua origin
                .allowedMethods("GET");  // Mengizinkan metode GET
    }
}
