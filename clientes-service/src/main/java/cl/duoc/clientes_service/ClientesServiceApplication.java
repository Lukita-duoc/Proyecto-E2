package cl.duoc.clientes_service;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Api de Clientes",
				version = "1.0.1",
				description = "Documentacion de API de CLientes con endpoints creados",
				contact = @Contact(
						name = "Lucas Ortiz",
						email = "luc.ortizr@duocuc.cl"
				)
		)
) // Doc: http://localhost:50858/swagger-ui/index.html
public class ClientesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientesServiceApplication.class, args);
	}

}
