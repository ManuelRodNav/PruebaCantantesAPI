package com.pruebahacerapimanuel.apimanuelcantatesconimplementos.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.pruebahacerapimanuel.apimanuelcantatesconimplementos.Entities.Cantante;
import com.pruebahacerapimanuel.apimanuelcantatesconimplementos.Entities.Song;
import com.pruebahacerapimanuel.apimanuelcantatesconimplementos.Servicio.ServicioAlbum;
import com.pruebahacerapimanuel.apimanuelcantatesconimplementos.Servicio.ServicioCantante;
import com.pruebahacerapimanuel.apimanuelcantatesconimplementos.Servicio.ServicioSong;
import com.pruebahacerapimanuel.apimanuelcantatesconimplementos.Entities.Album;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

@RestController

public class CantanteController {

@Autowired
private ServicioCantante servicioCantante;
@Autowired
private ServicioAlbum servicioAlbum;
@Autowired
private ServicioSong servicioSong;




@GetMapping("/cantantes")
public Page<Cantante> mostratodos(Pageable pageable){
      return  servicioCantante.encontrarTodos(pageable);
       

}

@GetMapping("/cantante/{nombre}")
public ResponseEntity<Cantante> buscarCantante(@PathVariable("nombre") String nombre){
    Optional<Cantante> searchCantante = servicioCantante.EncontrarCantantepornombre(nombre);
    if (!searchCantante.isPresent()) {      
        return ResponseEntity.notFound().build();
    }
    
    
    return ResponseEntity.ok(searchCantante.get());
}
  
@PostMapping("/cantante")
public ResponseEntity<Cantante> añadirCantante(@RequestBody Cantante reqCantante){
Cantante newcantante=  new Cantante(null, reqCantante.getName(), reqCantante.getFollowers(), reqCantante.getAlbums(), reqCantante.getSongs());
servicioCantante.saveCantante(newcantante);
for(Album albums: reqCantante.getAlbums()){
    servicioAlbum.guardarAlbum(albums);
}
for(Song cancion : reqCantante.getSongs()){
    
    servicioSong.guardarperfil(cancion);
}

return ResponseEntity.ok().body(newcantante);

}

@DeleteMapping("/cantante/{id}")
public ResponseEntity<Map<Object, Object>> deleteCantante(@PathVariable("id") Long id) {
    Map<Object, Object> response = new HashMap<>();
    Optional<Cantante> cantante = servicioCantante.EncontrarCantanteporId(id);

    if (!cantante.isPresent()) {
        response.put("error", "No se ha encontrado un objeto con el id " + id);
        return ResponseEntity.status(404).body(response);
    }

    servicioCantante.borrarperfil(id);
    response.put("mensaje", "Objeto eliminado correctamente");
    return ResponseEntity.ok(response);
}
    @PutMapping("/cantante/{nombre}")
    public ResponseEntity<Cantante> putMethodName(@PathVariable("nombre") String nombre, @RequestBody Cantante reqCantante) {
        Optional<Cantante> cantanteOptional = servicioCantante.EncontrarCantantepornombre(nombre);

        if (!cantanteOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Cantante cantante = cantanteOptional.get();
        cantante.setName(reqCantante.getName());
        cantante.setFollowers(reqCantante.getFollowers());

        // Actualizando álbumes
        Collection<Album> updatedAlbums = reqCantante.getAlbums();
        for (Album album : updatedAlbums) {
            album.setAutor(cantante);
        }
        cantante.setAlbums(updatedAlbums);

        // Actualizando canciones
        Collection<Song> updatedSongs = reqCantante.getSongs();
        Collection<Cantante> artisCantantes = new HashSet<>();
        artisCantantes.add(cantante);
        for (Song song : updatedSongs) {
            song.setSingers(artisCantantes);
        }
        cantante.setSongs(updatedSongs);

        servicioCantante.saveCantante(cantante);

        return ResponseEntity.ok().body(cantante);
    }
@GetMapping("/{cantante}/{album}")
public ResponseEntity<Album> encontraralbum(@PathVariable("cantante") String cantante, @PathVariable("album") String albumtitle){
    Optional<Cantante> cOptional= servicioCantante.EncontrarCantantepornombre(albumtitle);
    if(!cOptional.isPresent()){
        return new ResponseEntity("Ups, no se ha encontrado nada al respecto", HttpStatus.NOT_FOUND);
    }
    Collection<Album> albums = cOptional.get().getAlbums();
    for(Album album : albums){
        if(album.getTitle()== albumtitle){
            return ResponseEntity.ok().body(album);
            
        }
    }
    return ResponseEntity.notFound().build();
}

}