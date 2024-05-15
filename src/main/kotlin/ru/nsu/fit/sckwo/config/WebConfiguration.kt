package ru.nsu.fit.sckwo.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfiguration : WebMvcConfigurer {
//    /**
//     * Настраивает PathMatchConfigurer.
//     * Добавляет префикс "/api" ко всем контроллерам, аннотированным @RestController.
//     *
//     * @param configurer Конфигуратор PathMatch для настройки
//     */
//    override fun configurePathMatch(configurer: PathMatchConfigurer) {
//        configurer.addPathPrefix(
//            "/api",
//            HandlerTypePredicate.forAnnotation(RestController::class.java)
//        )
//    }

    /**
     * Настройка параметров Cross-Origin Resource Sharing (CORS).
     * Разрешает определенные источники, методы HTTP и заголовки для URL-шаблонов "/api/v1/ **".
     *
     * @param registry Реестр Cors для настройки
     */
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins("http://127.0.0.1:5173")
            .allowedMethods("GET", "POST", "PUT", "DELETE")
            .allowedHeaders("*")
            .allowCredentials(true)
    }
}