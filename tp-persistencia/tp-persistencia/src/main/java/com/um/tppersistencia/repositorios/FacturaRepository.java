package com.um.tppersistencia.repositorios;

import com.um.tppersistencia.entidades.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaRepository extends JpaRepository<Factura,Long> {
}
