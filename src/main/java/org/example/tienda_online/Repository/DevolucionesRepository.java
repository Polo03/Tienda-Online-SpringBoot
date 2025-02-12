package org.example.tienda_online.Repository;

import org.example.tienda_online.Dto.Devoluciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DevolucionesRepository extends JpaRepository<Devoluciones, Integer> {
}
