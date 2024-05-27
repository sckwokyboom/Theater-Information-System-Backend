package ru.nsu.fit.sckwo.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins("http://127.0.0.1:5173", "http://localhost:5173", "http://localhost:80")
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
    }
}
