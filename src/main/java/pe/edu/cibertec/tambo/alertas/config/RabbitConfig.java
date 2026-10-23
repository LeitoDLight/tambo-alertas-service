package pe.edu.cibertec.tambo.alertas.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String EXCHANGE = "tambo.exchange";
    public static final String RK_STOCK_BAJO = "stock.bajo";
    public static final String COLA_STOCK_BAJO = "alertas.stock.bajo";

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(EXCHANGE, true, false);
    }

    @Bean
    Queue colaStockBajo() {
        return new Queue(COLA_STOCK_BAJO, true);
    }

    @Bean
    Binding bindingStockBajo() {
        return BindingBuilder.bind(colaStockBajo()).to(exchange()).with(RK_STOCK_BAJO);
    }
}
