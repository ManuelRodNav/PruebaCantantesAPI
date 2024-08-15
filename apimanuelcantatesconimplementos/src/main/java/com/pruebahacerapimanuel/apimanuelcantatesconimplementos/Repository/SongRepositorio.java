package com.pruebahacerapimanuel.apimanuelcantatesconimplementos.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.pruebahacerapimanuel.apimanuelcantatesconimplementos.Entities.Song;
import java.util.List;
import java.util.Collection;
import com.pruebahacerapimanuel.apimanuelcantatesconimplementos.Entities.Cantante;
import java.util.List;
import com.pruebahacerapimanuel.apimanuelcantatesconimplementos.Entities.Album;
import java.util.Optional;
public interface SongRepositorio extends JpaRepository<Song, Long > {
   List<Song> findBySingers(Collection<Cantante> singers);
    List<Song> findByAlbum(Album album);
    Optional<Song> findBytitle(String title);
    
    
}
