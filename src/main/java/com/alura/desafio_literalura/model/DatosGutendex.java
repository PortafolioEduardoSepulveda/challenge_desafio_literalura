package com.alura.desafio_literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosGutendex(
        @JsonAlias("count")String cantidad,
        @JsonAlias("next")String siguiente,
        @JsonAlias("previous")String previo,
        @JsonAlias("results") List<DatosLibro> libros

        ) {
}
