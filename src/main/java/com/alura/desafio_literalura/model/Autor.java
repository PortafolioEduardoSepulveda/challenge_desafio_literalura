package com.alura.desafio_literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String fecha_nacimiento;
    private String fecha_fallecimiento;
    /*
    @ManyToOne


    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "libros_MAPPING_autores", joinColumns = @JoinColumn(name = "autor_id"),
            inverseJoinColumns = @JoinColumn(name = "libro_id"))

     */
    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "autores")
    private List<Libro> libros = new ArrayList<>();;
    public Autor(){}
    public Autor(String nombre, String fecha_nacimiento, String fecha_fallecimiento) {
      this.nombre = nombre;
      this.fecha_nacimiento = fecha_nacimiento;
      this.fecha_fallecimiento = fecha_fallecimiento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getFecha_fallecimiento() {
        return fecha_fallecimiento;
    }

    public void setFecha_fallecimiento(String fecha_fallecimiento) {
        this.fecha_fallecimiento = fecha_fallecimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        String imprime_libro = "";
        int contador = 0;
        contador = this.getLibros().size();
        for(int x = 0; x < contador ; x++){
            if(x == 0){
                imprime_libro += this.getLibros().get(x).getTitulo();
            }else{
                if(x == contador-1 ){
                    imprime_libro += this.getLibros().get(x).getTitulo();
                }else{
                    imprime_libro += this.getLibros().get(x).getTitulo()+",";
                }

            }

        }
        var imprime_autor = " \n"+
                "Autor: "+nombre+" \n"+
                "Fecha Nacimiento: "+fecha_nacimiento+" \n"+
                "Fecha Fallecimiento: "+fecha_fallecimiento +" \n"+
                "libros : ["+imprime_libro+"] \n";
        return imprime_autor;
    }


}
