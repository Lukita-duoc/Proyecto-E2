Integrantes:
Lucas Ortiz, Cristopher Rojas

# SERVICIO DE LOGISTICA

El proyecto fue diseñado con fines académicos para implementar:
* Comunicación distribuida y APIs REST.
* Persistencia desacoplada e integración de microservicios.
* Descubrimiento de servicios y gateway centralizado.

---

## Arquitectura del Sistema
El sistema se compone de los siguientes microservicios:

| Microservicio | Responsabilidad / Descripción |
| :--- | :--- |
| **`clientes-service`** | Gestión de clientes y empresas asociadas. |
| **`productos-service`** | Catálogo de productos y marcas. |
| **`empleados-service`** | Gestión de empleados y sucursales. |
| **`proveedores-service`** | Administración de proveedores y contactos. |
| **`inventario-service`** | Control de stock mínimo y stock actual por sucursal. |
| **`órdenes-service`** | Gestión de órdenes de compra y detalles de venta. |
| **`facturación-service`** | Generación de facturas y métodos de pago. |
| **`envíos-service`** | Control de despachos, transportistas y seguimiento de envíos. |

---

## Tecnologías Aplicadas

* **Lenguaje:** Java 25
* **Framework Principal:** Spring Boot
* **Ecosistema Cloud:** Spring Cloud, OpenFeign, Eureka Server, API Gateway
* **Persistencia y Datos:** Spring Data JPA, MySQL, Flyway
* **Herramientas de Desarrollo:** Lombok, Validation API

---

## Componentes de Infraestructura

### Eureka Server
Servicio de descubrimiento para el registro y localización dinámica de microservicios sin usar IPs estáticas.

### API Gateway
Único punto de entrada centralizado para recibir y enrutar todas las peticiones del cliente hacia los microservicios correspondientes.

### Comunicación Entre Servicios
La comunicación interna se realiza mediante OpenFeign, permitiendo consultas síncronas y seguras entre microservicios.

**Ejemplo de flujo de consultas:**
* `inventario-service` ➔ consultar productos y sucursales
* `órdenes-service` ➔ consultar clientes y productos
* `facturación-service` ➔ consultar clientes y órdenes
* `envíos-service` ➔ consultar órdenes

---

## Microservicios Implementados y Entidades

* **Microservicio Clientes**
  * *Entidades:* Cliente, Empresa
* **Microservicio Productos**
  * *Entidades:* Producto, Marca
* **Microservicio Empleados**
  * *Entidades:* Empleado, Sucursal
* **Microservicio Proveedores**
  * *Entidades:* Proveedor, Contactar Proveedor
* **Microservicio Inventario**
  * *Entidades:* Inventario
* **Microservicio Órdenes de Compra**
  * *Entidades:* Orden Compra, DetalleOrden
* **Microservicio Facturación**
  * *Entidades:* Factura
* **Microservicio Envíos**
  * *Entidades:* Envio

---

## Endpoints Extras

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

