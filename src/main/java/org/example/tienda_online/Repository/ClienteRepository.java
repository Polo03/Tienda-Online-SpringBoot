package org.example.tienda_online.Repository;

import org.example.tienda_online.Dto.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
