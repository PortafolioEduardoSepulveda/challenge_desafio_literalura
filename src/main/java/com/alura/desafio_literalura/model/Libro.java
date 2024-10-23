package com.alura.desafio_literalura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Entity
@Table(name = "Libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;


@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
@JoinTable(
        name = "Libro_Autor", joinColumns = @JoinColumn( name = "libro_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "autor_id" , referencedColumnName = "id")
)
    private List<Autor> autores = new ArrayList<>();
    private String idioma;
    private String copyright;
    private String media_type;
    private Integer cantidad_descargas;

    public Libro(){}
    public Libro(DatosLibro libros) {
      this.titulo = libros.titulo();
      this.copyright = libros.copyright();
      this.media_type = libros.media_type();
      this.cantidad_descargas = Integer.parseInt(libros.cantidad_descargas());
      this.idioma = libros.idioma().get(0);

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public Integer getCantidad_descargas() {
        return cantidad_descargas;
    }

    public void setCantidad_descargas(Integer cantidad_descargas) {
        this.cantidad_descargas = cantidad_descargas;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    @Override
    public String toString() {


        var imprime_libro = "\n----- Libro ------\n"+
                      "Titulo:  "+titulo+" \n"+
                      "Idioma:  "+idioma+" \n"+
                      "Autor:   "+getAutores().get(0).getNombre()+" \n"+
                      "Cantidad de Descargas:   " + cantidad_descargas+
                      "\n-----------------\n";


        return imprime_libro;
    }
}
