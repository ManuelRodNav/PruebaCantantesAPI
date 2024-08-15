package com.pruebahacerapimanuel.apimanuelcantatesconimplementos.Controller;



import java.util.Optional;

import javax.naming.NameNotFoundException;

import java.net.URI;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pruebahacerapimanuel.apimanuelcantatesconimplementos.Entities.Album;
import com.pruebahacerapimanuel.apimanuelcantatesconimplementos.Entities.Cantante;
import com.pruebahacerapimanuel.apimanuelcantatesconimplementos.Entities.Song;
import com.pruebahacerapimanuel.apimanuelcantatesconimplementos.Servicio.ServicioAlbum;
import com.pruebahacerapimanuel.apimanuelcantatesconimplementos.Servicio.ServicioCantante;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Map;
import java.util.HashMap;
@RestController
public class AlbumController {

    @Autowired
    private ServicioAlbum servicioAlbum;

    @Autowired
    private ServicioCantante servicioCantante;

    @GetMapping("/albums")
    public Page<Album> mostrarAlbums(Pageable pageable) {
        return servicioAlbum.findAll(pageable);
    }

    @GetMapping("/albums/{name}")
    public ResponseEntity<Album> buscarAlbumpornombre(@PathVariable("name") String name) {
    Optional<Album> album = servicioAlbum.encontrarpornombre(name);
    if (!album.isPresent()) {
        return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(album.get());
}

@GetMapping("/albums/{album}/{song}")
public ResponseEntity<Song> buscarCancionporalbum(@PathVariable("album") String albumtitle, @PathVariable("song") String cancion){
    Optional<Album> album= servicioAlbum.encontrarpornombre(albumtitle);
    if(!album.isPresent()){
        return new  ResponseEntity("No se ha encontrado ningun album" , HttpStatus.NOT_FOUND);
    }
    Collection<Song> cancionbuscar = album.get().getCanciones();
    for(Song cancionfor : cancionbuscar){
        if(cancionfor.getTitle().equals(cancion)){
            return ResponseEntity.ok().body(cancionfor);
        }
        
    }
    return ResponseEntity.notFound().build();
}
@PostMapping("/album")
public ResponseEntity<Album> crearAlbum(@RequestBody Album reqAlbum) {
    Optional<Cantante> cantante= servicioCantante.EncontrarCantanteporId(reqAlbum.getId());

    if(!cantante.isPresent()){
        return ResponseEntity.notFound().build();
    }
    reqAlbum.setAutor(cantante.get());
    Album nuevoalbum= servicioAlbum.guardarAlbum(reqAlbum);
    URI ubicacion= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(reqAlbum.getId()).toUri();
    return ResponseEntity.created(ubicacion).body(nuevoalbum);
    
}

@DeleteMapping("/album//{id}")
public ResponseEntity<Map<Object, Object>> borraralbum(@PathVariable("id")  Long id){
    
    Map<Object,Object>  response = new HashMap<>();
    servicioAlbum.borrarAlbum(id);
     response.put("Borrado:", "Exitosamente");
     return ResponseEntity.ok().body(response);
}




}
