package com.pruebahacerapimanuel.apimanuelcantatesconimplementos.Servicio;
import com.pruebahacerapimanuel.apimanuelcantatesconimplementos.Entities.Cantante;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pruebahacerapimanuel.apimanuelcantatesconimplementos.Entities.Album;
import com.pruebahacerapimanuel.apimanuelcantatesconimplementos.Entities.Album;
import com.pruebahacerapimanuel.apimanuelcantatesconimplementos.Repository.AlbumRepository;
import com.pruebahacerapimanuel.apimanuelcantatesconimplementos.Repository.CantanteRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

import javax.swing.text.html.Option;
@Service
public class ServicioAlbum {

@Autowired
private AlbumRepository albumRepository;
@Autowired
private CantanteRepository cantanteRepository;

public Optional<Album> encontrarpornombre(String nombre){
   Optional<Album> album = albumRepository.findByTitle(nombre);
   if(!album.isPresent()){
      return null;
   }
   return album;
}

public Page<Album> findAll(Pageable pageable) {
   return albumRepository.findAll(pageable);
}

public void borrarAlbum(Long id){
   albumRepository.deleteById(id);
}
public List<Album> mostratodos(){
   return  albumRepository.findAll();
}
public Album guardarAlbum(Album album){
return albumRepository.save(album);


}
public Optional<Cantante> encontrarCantantePorNombre(String nombre){
   return cantanteRepository.findByName(nombre);
}




}
