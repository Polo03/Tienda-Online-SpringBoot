package org.example.tienda_online.Repository;

import org.example.tienda_online.Dto.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    @Query("SELECT p.nombre FROM Producto p")
    List<String> findAllNombresProducto();

    @Query("SELECT p.nombre FROM Producto p WHERE p.id != :id")
    List<String> findAllNombresProductoAndId(@Param("id") Integer id);
}
