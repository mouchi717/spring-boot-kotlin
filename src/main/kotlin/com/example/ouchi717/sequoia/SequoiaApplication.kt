package com.example.ouchi717.sequoia

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import springfox.documentation.swagger2.annotations.EnableSwagger2

@SpringBootApplication
@EnableSwagger2
class SequoiaApplication

fun main(args: Array<String>) {
    SpringApplication.run(SequoiaApplication::class.java, *args)
}
