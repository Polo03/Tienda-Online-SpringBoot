package org.example.tienda_online.Repository;

import org.example.tienda_online.Dto.Devolucion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DevolucionRepository extends JpaRepository<Devolucion, Integer> {
}
