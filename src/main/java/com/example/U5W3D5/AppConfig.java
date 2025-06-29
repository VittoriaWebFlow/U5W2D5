package com.example.U5W3D5;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource("application.properties")
public class AppConfig {

    @Bean
    public Cloudinary getCloudinary(@Value("${cloudinary.cloud_name}") String cloudName,
                                    @Value("${cloudinary.api_key}") String apiKey,
                                    @Value("${cloudinary.api_secret}") String apiSecret) {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", cloudName);
        config.put("api_key", apiKey);
        config.put("api_secret", apiSecret);
        return new Cloudinary(config);
    }
}
