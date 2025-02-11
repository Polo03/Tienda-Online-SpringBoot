package org.example.tienda_online.Repository;

import org.example.tienda_online.Dto.Producto;
import org.springframework.data.repository.CrudRepository;

public interface ProductoRepository extends CrudRepository<Producto, Integer> {
}
