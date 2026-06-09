package cl.duoc.facturacion_service;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
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
				title = "Api de Facturas",
				version = "1.0.1",
				description = "Documentacion de API de facturacion con endpoints creados"
		)
) // Doc: http://localhost:8080/facturacion-service/swagger-ui/index.html
public class FacturacionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FacturacionServiceApplication.class, args);
	}

}
