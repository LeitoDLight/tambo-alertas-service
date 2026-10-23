package pe.edu.cibertec.tambo.alertas.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import pe.edu.cibertec.tambo.alertas.dto.AlertaResponse;
import pe.edu.cibertec.tambo.alertas.dto.StockBajoMensaje;
import pe.edu.cibertec.tambo.alertas.entity.Alerta;
import pe.edu.cibertec.tambo.alertas.repository.AlertaRepository;

@Service
@RequiredArgsConstructor
public class AlertaService {

    private final AlertaRepository alertaRepository;

    @Transactional(readOnly = true)
    public List<AlertaResponse> listar() {
        return alertaRepository.findAllByOrderByFechaHoraDesc().stream().map(this::aResponse).toList();
    }

    @Transactional
    public AlertaResponse registrar(StockBajoMensaje mensaje) {
        Alerta alerta = Alerta.builder()
                .productoId(mensaje.productoId())
                .productoNombre(mensaje.nombre())
                .stockActual(mensaje.stockActual())
                .fechaHora(LocalDateTime.now())
                .build();
        return aResponse(alertaRepository.save(alerta));
    }

    private AlertaResponse aResponse(Alerta a) {
        return new AlertaResponse(a.getId(), a.getProductoId(), a.getProductoNombre(), a.getStockActual(),
                a.getFechaHora());
    }
}
