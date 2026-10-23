package pe.edu.cibertec.tambo.alertas.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class StockBajoMensajeTest {

    @Test
    void leeMensajeValido() {
        StockBajoMensaje m = StockBajoMensaje.desdeTexto("5|3|Gaseosa Inca Kola 500 ml");
        assertEquals(5L, m.productoId());
        assertEquals(3, m.stockActual());
        assertEquals("Gaseosa Inca Kola 500 ml", m.nombre());
    }

    @Test
    void nombreConBarraSeConserva() {
        StockBajoMensaje m = StockBajoMensaje.desdeTexto("7|0|Pack 2|1");
        assertEquals("Pack 2|1", m.nombre());
    }

    @Test
    void rechazaFormatoInvalido() {
        assertThrows(IllegalArgumentException.class, () -> StockBajoMensaje.desdeTexto("5|3"));
        assertThrows(IllegalArgumentException.class, () -> StockBajoMensaje.desdeTexto("x|3|Agua"));
    }
}
