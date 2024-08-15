package com.pruebahacerapimanuel.apimanuelcantatesconimplementos.Repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pruebahacerapimanuel.apimanuelcantatesconimplementos.Entities.Album;
import com.pruebahacerapimanuel.apimanuelcantatesconimplementos.Entities.Cantante;
import java.util.Optional;
@Repository
public interface CantanteRepository extends JpaRepository<Cantante, Long> {
    Optional<Cantante> findByName(String name);
    
}
