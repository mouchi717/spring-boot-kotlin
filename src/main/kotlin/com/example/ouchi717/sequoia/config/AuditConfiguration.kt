package com.example.ouchi717.sequoia.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
class AuditConfiguration {

    @Bean
    fun auditorProvider(): AuditorAware<String> {
        return AuditorAware { "hoge" } // TODO masayuki.ouchi あとから良い感じにする (2018/01/07)
    }
}