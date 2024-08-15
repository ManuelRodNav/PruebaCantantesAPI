package com.pruebahacerapimanuel.apimanuelcantatesconimplementos.Servicio;
import com.pruebahacerapimanuel.apimanuelcantatesconimplementos.Entities.Cantante;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.pruebahacerapimanuel.apimanuelcantatesconimplementos.Entities.Album;
import com.pruebahacerapimanuel.apimanuelcantatesconimplementos.Entities.Album;
import org.springframework.data.domain.Page;
import com.pruebahacerapimanuel.apimanuelcantatesconimplementos.Repository.CantanteRepository;
import java.util.List;
import java.util.Optional;
@Service
public class ServicioCantante {

@Autowired
private CantanteRepository cantanteRepository;


public Optional<Cantante> EncontrarCantantepornombre(String nombre){
    Optional<Cantante> newcCantante = cantanteRepository.findByName(nombre);
    return newcCantante;
}

public Optional<Cantante> EncontrarCantanteporId(Long id){
    Optional<Cantante> newcCantante = cantanteRepository.findById(id);
    return newcCantante;
}


public void borrarperfil(Long id){
    cantanteRepository.deleteById(id);
}

public Cantante saveCantante(Cantante cantante){
    return cantanteRepository.save(cantante);
}
public Page<Cantante> encontrarTodos(Pageable pageable){
    return cantanteRepository.findAll(pageable);
}





}
