package com.optimus.docusign

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
 * Created by sbhowmick on 10/25/16.
 */
@SpringBootApplication
open class Application {
    companion object {
        @JvmStatic fun  main(args: Array<String>){
            SpringApplication.run(Application::class.java, *args)
        }
    }
}