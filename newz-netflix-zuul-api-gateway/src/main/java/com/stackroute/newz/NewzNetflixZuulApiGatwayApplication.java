package com.stackroute.newz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.stackroute.newz.zuul.filter.JwtFilter;

/*
 * The @SpringBootApplication annotation is equivalent to using @Configuration, @EnableAutoConfiguration 
 * and @ComponentScan with their default attributes
 * 
 * Add @EnableZuulProxy and @EnableEurekaClient
 * 
 */

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
public class NewzNetflixZuulApiGatwayApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewzNetflixZuulApiGatwayApplication.class, args);
	}

    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilter() {
    	final FilterRegistrationBean<JwtFilter> registrationBean = new FilterRegistrationBean<JwtFilter>();
		registrationBean.setFilter(new JwtFilter());
		registrationBean.addUrlPatterns("/api/*");
		return registrationBean;
    }
	

}
