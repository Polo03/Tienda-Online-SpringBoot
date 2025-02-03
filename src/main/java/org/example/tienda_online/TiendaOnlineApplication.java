package org.example.tienda_online;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@EnableConfigurationProperties
public class TiendaOnlineApplication {

    public static void main(String[] args) {
        SpringApplication.run(TiendaOnlineApplication.class, args);
    }

}
