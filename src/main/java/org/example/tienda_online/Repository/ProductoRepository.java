package org.example.tienda_online.Repository;

import org.example.tienda_online.Dto.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}
