# Servicio de Logística

## Integrantes

* Lucas Ortiz
* Cristopher Rojas

## Descripción

Este proyecto fue desarrollado con fines académicos para implementar conceptos de:

* Comunicación distribuida mediante APIs REST.
* Persistencia desacoplada e integración de microservicios.
* Descubrimiento de servicios y gateway centralizado.
* Arquitectura basada en microservicios utilizando Spring Cloud.

---

# Arquitectura del Sistema

El sistema está compuesto por los siguientes microservicios:

| Microservicio         | Responsabilidad                                               | Testing                | Swagger               |
| --------------------- | ------------------------------------------------------------- | ---------------------- | --------------------- |
| `clientes-service`    | Gestión de clientes y empresas asociadas.                     | Unitario / Integración | Habilitado            |
| `productos-service`   | Catálogo de productos y marcas.                               | Unitario / Integración | Habilitado            |
| `empleados-service`   | Gestión de empleados y sucursales.                            | Unitario / Integración | Habilitado            |
| `proveedores-service` | Administración de proveedores y contactos.                    | N/A                    | Excluido del Proxy UI |
| `inventario-service`  | Control de stock mínimo y stock actual por sucursal.          | N/A                    | Excluido del Proxy UI |
| `ordenes-service`     | Gestión de órdenes de compra y detalles de venta.             | N/A                    | Excluido del Proxy UI |
| `facturacion-service` | Generación de facturas y métodos de pago.                     | N/A                    | Excluido del Proxy UI |
| `envios-service`      | Control de despachos, transportistas y seguimiento de envíos. | Unitario / Integración | Habilitado            |

---

# Tecnologías Utilizadas

## Backend

* Java 25
* Spring Boot (WebMVC)

## Spring Cloud

* Spring Cloud
* OpenFeign
* Eureka Server
* API Gateway

## Persistencia

* Spring Data JPA
* MySQL
* Flyway

## Desarrollo

* Lombok
* Jakarta Validation API

## Documentación

* Springdoc OpenAPI
* Swagger UI v3.0.2

## Testing

* JUnit 5
* Mockito
* Spring Boot Test

## Infraestructura

* Docker
* Docker Compose

---

# Componentes de Infraestructura

## Eureka Server

Servicio de descubrimiento encargado del registro y localización dinámica de microservicios, evitando el uso de direcciones IP estáticas.

## API Gateway

Punto de entrada centralizado para recibir y enrutar todas las solicitudes hacia los microservicios correspondientes.

---

# Comunicación Entre Servicios

La comunicación interna se realiza mediante **OpenFeign**, permitiendo consultas síncronas entre microservicios.

### Ejemplos de Integración

* `inventario-service` → consulta productos y sucursales.
* `ordenes-service` → consulta clientes y productos.
* `facturacion-service` → consulta clientes y órdenes.
* `envios-service` → consulta órdenes.

---

# Documentación Unificada (Swagger)

La documentación interactiva se encuentra centralizada mediante el API Gateway.

**URL de acceso:**

```text
http://localhost:8080/swagger-ui.html
```

Desde el selector superior de Swagger UI es posible alternar entre los servicios habilitados:

* Clientes
* Productos
* Empleados
* Envíos

---

# Entidades por Microservicio

## Clientes

* Cliente
* Empresa

## Productos

* Producto
* Marca

## Empleados

* Empleado
* Sucursal

## Proveedores

* Proveedor
* ContactoProveedor

## Inventario

* Inventario

## Órdenes de Compra

* OrdenCompra
* DetalleOrden

## Facturación

* Factura

## Envíos

* Envio

---

# Endpoints Adicionales

```http
GET /api/v1/clientes/rut/{rut}
GET /api/v1/clientes/empresa/{empresaId}

GET /api/v1/productos/stock/{id}
GET /api/v1/productos/categoria/{categoria}
GET /api/v1/productos/filtrarPrecio/{min}/{max}

GET /api/v1/proveedores/pais/{pais}

GET /api/v1/inventario/buscar-producto/{productoId}/{sucursalId}
GET /api/v1/inventario/reporte-stock

GET /api/v1/facturas/cliente/{idCliente}
```
