package pe.edu.cibertec.tambo.alertas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.cibertec.tambo.alertas.entity.Alerta;

public interface AlertaRepository extends JpaRepository<Alerta, Long> {

    List<Alerta> findAllByOrderByFechaHoraDesc();
}
