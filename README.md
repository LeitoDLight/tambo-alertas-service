# tambo-alertas-service

Microservicio de **alertas de stock bajo** de Tambo Market.

| Dato | Valor |
|---|---|
| Puerto | 8084 |
| Tecnología | Java 21, Spring Boot 4.1.1, Maven, PostgreSQL 17, Spring Data JPA, RabbitMQ |
| Responsable | Joselin Carhuapoma Poma |

## Qué hace

- Escucha la cola `alertas.stock.bajo` (routing key `stock.bajo`, exchange `tambo.exchange`).
- Cada mensaje (`productoId|stockActual|nombre`) se guarda como una alerta.
- `GET /api/alertas`: lista las alertas (solo rol **ADMIN**). Las ve el administrador en la pantalla *Alertas* del frontend.

## Tolerancia a fallos

Si este servicio está caído, las ventas siguen funcionando: los mensajes esperan en la cola de RabbitMQ y se procesan cuando el servicio vuelve. Los mensajes con formato inválido se rechazan sin reintentar, para evitar bucles infinitos.

## Estructura

```
config/       RabbitConfig, SecurityConfig
controller/   AlertaController
messaging/    StockBajoListener
entity/       Alerta
service/      AlertaService
dto/          AlertaResponse, StockBajoMensaje
```

## Pruebas

`StockBajoMensajeTest`: prueba del procesamiento del mensaje de stock bajo.

## Proyecto completo

Este repositorio es **un microservicio de Tambo Market**. El proyecto completo (todos los servicios, `docker-compose.yml`, colección de Postman, informe y el historial de trabajo del equipo) está en el repositorio de integración, que es privado y tiene acceso concedido al docente:

https://github.com/LeitoDLight/tambo-market

Para levantar todo el sistema:

```
git clone https://github.com/LeitoDLight/tambo-market.git
cd tambo-market
docker compose up -d --build
```

Luego abrir http://localhost (usuario `admin` / `Admin123`).

## Equipo

Leonardo Fabricio Dorregaray Guevara · José Miguel Yataco Quispe · Alberto Muzaurieta Hidalgo · Joselin Carhuapoma Poma

Curso: Desarrollo de Aplicaciones Web II — Cibertec.