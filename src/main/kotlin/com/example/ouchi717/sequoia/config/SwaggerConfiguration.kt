package com.example.ouchi717.sequoia.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket

@Configuration
class SwaggerConfiguration {

    companion object {
        const val BASE_PACKAGE_NAME: String = "jp.example.ouchi717.sequoia.controller"
    }

    @Bean
    fun customDocket(): Docket {
        return Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE_NAME))
                .build()
    }
}