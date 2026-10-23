package pe.edu.cibertec.tambo.alertas.dto;

import java.time.LocalDateTime;

public record AlertaResponse(Long id, Long productoId, String productoNombre, Integer stockActual,
        LocalDateTime fechaHora) {
}
