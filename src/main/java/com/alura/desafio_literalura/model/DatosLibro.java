package com.alura.desafio_literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
        @JsonAlias("title")String titulo,
        @JsonAlias("authors") List<DatosAutor> autores,
        @JsonAlias("languages") List<String> idioma,
        @JsonAlias("copyright")String copyright,
        @JsonAlias("media_type")String media_type,
        @JsonAlias("download_count")String cantidad_descargas
) {

}
