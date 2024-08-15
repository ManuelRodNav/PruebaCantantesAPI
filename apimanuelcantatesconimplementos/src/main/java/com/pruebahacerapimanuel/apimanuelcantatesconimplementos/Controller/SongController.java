package com.pruebahacerapimanuel.apimanuelcantatesconimplementos.Controller;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import com.pruebahacerapimanuel.apimanuelcantatesconimplementos.Entities.Song;
import com.pruebahacerapimanuel.apimanuelcantatesconimplementos.Servicio.ServicioSong;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
@RestController
public class SongController {

    @Autowired
    private ServicioSong servicioSong;
    
    @GetMapping("/song/{title}")
    public ResponseEntity<Song> buscarcancion(@PathVariable("title") String titulo){

        Optional<Song> song=  servicioSong.EncontrarCancionpornombre(titulo);
        if(!song.isPresent()){  
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(song.get());
    }
    @PostMapping("/song")
    public ResponseEntity<Song> crearcancion(@RequestBody Song reqSong){
        Song newsong= new Song(null, reqSong.getDuration(), reqSong.getTitle(), reqSong.getAlbum(), reqSong.getSingers());
        servicioSong.guardarperfil(newsong);
        return ResponseEntity.ok().body(newsong);

    }
    @PutMapping("/song/{id}")
    public ResponseEntity<Song> actualizarcancion(@PathVariable("id") Long id , @RequestBody  Song reqSong){
        Optional<Song> song= servicioSong.EncontrarCantanteporId(id);
        if(!song.isPresent()){
            return new ResponseEntity<Song>(HttpStatus.NOT_FOUND);
        }
        song.map(newsong->{
            newsong.setAlbum(reqSong.getAlbum());
            newsong.setTitle(reqSong.getTitle());
            newsong.setSingers(reqSong.getSingers());
            newsong.setDuration(reqSong.getDuration());
            return song;
        });
        return ResponseEntity.ok().body(song.get());
    }
    @DeleteMapping("/song/{id}")
    public ResponseEntity<?> borrarcancion(@PathVariable("id") Long id){
        servicioSong.borrarperfil(id);
        return new ResponseEntity<>("Se ha eliminado correctamente la cancion",HttpStatus.OK);
    }

    @GetMapping("/canciones")
    public Page mostrartodas(Pageable pageable){
        return servicioSong.buscarTodaslascanciones(pageable);
    }
}
