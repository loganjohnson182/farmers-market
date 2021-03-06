package com.farmers.market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = { "com.farmers.market" })
@Configuration
@EnableAutoConfiguration
public class FarmersMarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(FarmersMarketApplication.class, args);
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().paths(PathSelectors.any()).build().apiInfo(apiInfo())
				.useDefaultResponseMessages(false);
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().build();
	}

}