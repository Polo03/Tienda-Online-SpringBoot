package org.example.tienda_online.Repository;

import org.example.tienda_online.Dto.Compra;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepository extends CrudRepository<Compra, Integer> {
}
