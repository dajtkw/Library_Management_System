package com.example.library_management.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing //Kích hoạt Auditing cho ghi nhận thời gian
public class JpaConfig {
    
}
