package org.example.tienda_online.Repository;

import org.example.tienda_online.Dto.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Optional<Cliente> findByNicknameAndPassword(String nickname, String password);

}
