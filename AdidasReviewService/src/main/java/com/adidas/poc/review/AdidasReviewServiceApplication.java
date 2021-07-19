package com.adidas.poc.review;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.adidas.poc.review.filter.JWTFilter;

@SpringBootApplication
public class AdidasReviewServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdidasReviewServiceApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean getFilter()
	{
		
		UrlBasedCorsConfigurationSource urlconfig=new UrlBasedCorsConfigurationSource();
		
		CorsConfiguration config=new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedMethod("*");
		config.addAllowedHeader("*");
		
		urlconfig.registerCorsConfiguration("/**", config);
		
	
		FilterRegistrationBean filterbean=new FilterRegistrationBean(new CorsFilter(urlconfig));
		
		filterbean.setFilter(new JWTFilter());
		
		
		filterbean.addUrlPatterns("/api/review/*");
		
		return filterbean;
		
		
		
	}
	
}
