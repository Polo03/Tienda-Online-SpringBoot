package org.example.tienda_online.Repository;

import org.example.tienda_online.Dto.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<Cliente, Integer> {
}
