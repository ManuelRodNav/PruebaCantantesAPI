package com.pruebahacerapimanuel.apimanuelcantatesconimplementos.Servicio;
import com.pruebahacerapimanuel.apimanuelcantatesconimplementos.Entities.Cantante;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.pruebahacerapimanuel.apimanuelcantatesconimplementos.Entities.Album;
import com.pruebahacerapimanuel.apimanuelcantatesconimplementos.Entities.Album;
import com.pruebahacerapimanuel.apimanuelcantatesconimplementos.Repository.CantanteRepository;
import com.pruebahacerapimanuel.apimanuelcantatesconimplementos.Repository.SongRepositorio;
import com.pruebahacerapimanuel.apimanuelcantatesconimplementos.Entities.Song;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.naming.NameNotFoundException;
@Service
public class ServicioSong {

@Autowired
private SongRepositorio songRepositorio;


public Optional<Song>  EncontrarCancionpornombre(String nombre){
    Optional<Song> newsong = songRepositorio.findBytitle(nombre);
    
    return newsong;
}

public Optional<Song> EncontrarCantanteporId(Long id) {
    Optional<Song> song = songRepositorio.findById(id);
    if (!song.isPresent()) {
        throw new NoSuchElementException("No se ha encontrado ninguna canción");
    }
    return song;
}

public void borrarperfil(Long id){
    songRepositorio.deleteById(id);
}
public Song guardarperfil(Song song){
    return songRepositorio.save(song);
}

public Page buscarTodaslascanciones(Pageable pageable){
    return songRepositorio.findAll(pageable);
}






}
