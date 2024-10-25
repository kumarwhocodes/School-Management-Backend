package org.school.bps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = "org.school.bps.*")
@EnableScheduling
public class BrightPublicSchoolApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(BrightPublicSchoolApplication.class, args);
    }
    
}
