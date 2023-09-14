package com.um.tppersistencia.repositorios;

import com.um.tppersistencia.entidades.Rubro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RubroRepository extends JpaRepository<Rubro,Long> {
}
