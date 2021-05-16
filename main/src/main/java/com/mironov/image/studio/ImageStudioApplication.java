package com.mironov.image.studio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ImageStudioApplication {
    public static void main(String[] args) {
        SpringApplication.run(ImageStudioApplication.class, args);
    }
}
