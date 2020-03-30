package com.yc.ebuy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class EbuyZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(EbuyZuulApplication.class, args);
	}

}