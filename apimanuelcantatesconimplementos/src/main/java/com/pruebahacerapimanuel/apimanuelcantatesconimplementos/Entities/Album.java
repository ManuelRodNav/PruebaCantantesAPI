package com.pruebahacerapimanuel.apimanuelcantatesconimplementos.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String image;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "album")
    @JsonIgnore
    private Collection<Song> canciones;

    private Boolean favorite;

    @CreatedDate
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Cantante autor;

    // Constructor accepting a string (e.g., album title)
    public Album(@JsonProperty("title") String title) {
        this.title = title;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Collection<Song> getCanciones() {
        return canciones;
    }

    public void setCanciones(Collection<Song> canciones) {
        this.canciones = canciones;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Cantante getAutor() {
        return autor;
    }

    public void setAutor(Cantante autor) {
        this.autor = autor;
    }
}
