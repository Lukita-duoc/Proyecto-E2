package cl.duoc.proveedores_service;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Api de Proveedores",
				version = "1.0.1",
				description = "Documentacion de API de Proveedores con endpoints creados"
		)
) // 50957
public class ProveedoresServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProveedoresServiceApplication.class, args);
	}

}
