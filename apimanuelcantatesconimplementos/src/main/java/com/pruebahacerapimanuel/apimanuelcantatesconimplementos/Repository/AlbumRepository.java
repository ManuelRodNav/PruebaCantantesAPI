package com.pruebahacerapimanuel.apimanuelcantatesconimplementos.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pruebahacerapimanuel.apimanuelcantatesconimplementos.Entities.Album;
import java.util.List;
import com.pruebahacerapimanuel.apimanuelcantatesconimplementos.Entities.Cantante;
import java.util.Optional;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
   List<Album> findByAutor(Cantante autor);
    Optional<Album>   findByfavorite(Boolean favorite);
    void deleteBytitle(String title);
    Optional<Album> findByTitle(String title);
    

}
