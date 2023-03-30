package com.wissen.zwiggy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@CrossOrigin("http://localhost:4200")
@SpringBootApplication
public class FoodOrderingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodOrderingSystemApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:4200").allowedMethods("GET", "POST", "PUT", "DELETE");
			}
		};
	}

	@Configuration
	public class SecurityConfig {

		@Bean
		public SecurityFilterChain configure(HttpSecurity http) throws Exception {
			return http.csrf(csrf -> csrf.disable()).authorizeRequests(auth -> {
				auth.requestMatchers("/api/**").permitAll();
			}).httpBasic(Customizer.withDefaults()).build();
		}
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
