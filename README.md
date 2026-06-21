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

# Contexto

La empresa TechGlobal B2B, dedicada a la comercialización mayorista y minorista de tecnología, experimentó un crecimiento acelerado que evidenció limitaciones en su sistema monolítico.

Entre los principales problemas se encontraban las caídas del sistema durante períodos de alta demanda, la falta de sincronización entre el inventario físico y las ventas en línea, errores en la facturación, retrasos en los envíos y dificultades para mantener la trazabilidad de los proveedores.

Como solución, se implementó una arquitectura basada en microservicios utilizando Spring Cloud, permitiendo desacoplar las funcionalidades del negocio, mejorar la escalabilidad y aumentar la disponibilidad de los servicios.

---

# Arquitectura del Sistema

El sistema está compuesto por los siguientes microservicios:

| Microservicio         | Responsabilidad                                               | Testing                | Swagger               |
| --------------------- | ------------------------------------------------------------- | ---------------------- | --------------------- |
| `clientes-service`    | Gestión de clientes y empresas asociadas.                     | Unitario / Integración | Habilitado            |
| `productos-service`   | Catálogo de productos y marcas.                               | Unitario / Integración | Habilitado            |
| `empleados-service`   | Gestión de empleados y sucursales.                            | Unitario / Integración | Habilitado            |
| `proveedores-service` | Administración de proveedores y contactos.                    | N/A                    | Excluido              |
| `inventario-service`  | Control de stock mínimo y stock actual por sucursal.          | N/A                    | Excluido              |
| `ordenes-service`     | Gestión de órdenes de compra y detalles de venta.             | N/A                    | Excluido              |
| `facturacion-service` | Generación de facturas y métodos de pago.                     | N/A                    | Excluido              |
| `envios-service`      | Control de despachos, transportistas y seguimiento de envíos. | Unitario / Integración | Habilitado            |

---

# Tecnologías Utilizadas

## Backend

* Java 25
* Spring Boot

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

# Networking

Todas las solicitudes externas son recibidas por el API Gateway, que actúa como punto de entrada único del sistema y se encarga de enrutar las peticiones hacia los microservicios correspondientes.

### Rutas principales expuestas

```http
/api/v1/clientes/**
/api/v1/productos/**
/api/v1/empleados/**
/api/v1/proveedores/**
/api/v1/inventario/**
/api/v1/ordenes/**
/api/v1/facturas/**
/api/v1/envios/**
```

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

---

# Guía de Despliegue

## Requisitos Previos

* Java 25
* Maven
* Docker
* Docker Compose
* MySQL

## Ejecución con Docker

1. Clonar el repositorio

```bash
git clone https://github.com/Lukita-duoc/Proyecto-E2.git
```

2. Levantar los contenedores y construir las imágenes

```bash
docker compose up --build
```

3. Verificar la ejecución de los contenedores

```bash
docker ps
```

---

## Ejecución Local / Híbrida

1. Configurar y ejecutar Config Server.
2. Configurar y ejecutar Eureka Server.
3. Ejecutar los microservicios.
4. Ejecutar API Gateway.
5. Acceder a la documentación Swagger centralizada.

```text
http://localhost:8080/swagger-ui.html
```
