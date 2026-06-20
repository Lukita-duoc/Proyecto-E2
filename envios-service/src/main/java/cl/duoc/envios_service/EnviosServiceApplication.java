package cl.duoc.envios_service;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;



@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Api de Envios",
				version = "1.0.1",
				description = "Documentación de API de Envios con todos los endpoints creados",
				contact = @Contact(
						name = "Equipo Rocket",
						email = "cristo.rojasv@duocuc.cl"

				)
		)
)

public class EnviosServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnviosServiceApplication.class, args);
	}

}

