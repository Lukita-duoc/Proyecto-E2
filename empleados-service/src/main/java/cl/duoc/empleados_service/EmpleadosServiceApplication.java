package cl.duoc.empleados_service;

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
				title = "Api de Empleados",
				version = "1.0.1",
				description = "Documentación de API de Empleados con todos los endpoints creados",
				contact = @Contact(
						name = "Cristopher Rojas",
						email = "cristo.rojasv@duocuc.cl"

				)
		)
)

public class EmpleadosServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmpleadosServiceApplication.class, args);
	}

}
