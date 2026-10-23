package pe.edu.cibertec.tambo.alertas.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import pe.edu.cibertec.tambo.alertas.config.RabbitConfig;
import pe.edu.cibertec.tambo.alertas.dto.StockBajoMensaje;
import pe.edu.cibertec.tambo.alertas.service.AlertaService;

@Component
@RequiredArgsConstructor
public class StockBajoListener {

    private static final Logger log = LoggerFactory.getLogger(StockBajoListener.class);

    private final AlertaService alertaService;

    @RabbitListener(queues = RabbitConfig.COLA_STOCK_BAJO)
    public void recibir(String mensaje) {
        log.info("Recibido {}: {}", RabbitConfig.RK_STOCK_BAJO, mensaje);
        StockBajoMensaje datos;
        try {
            datos = StockBajoMensaje.desdeTexto(mensaje);
        } catch (IllegalArgumentException e) {
            log.error("Mensaje con formato inválido '{}': {}", mensaje, e.getMessage());
            throw new AmqpRejectAndDontRequeueException("Formato inválido", e);
        }
        alertaService.registrar(datos);
        log.info("Alerta registrada para el producto {} (stock {})", datos.productoId(), datos.stockActual());
    }
}
