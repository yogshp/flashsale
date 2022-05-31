package com.popshop.live.online.assessment.flashsale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableAutoConfiguration(exclude={org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration.class})
@OpenAPIDefinition(info = @Info(title = "Flash Sale API's", version = "1.0", description = "Below API's are used for Flash Sale Application"))
public class FlashSaleApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlashSaleApplication.class, args);
	}

}
