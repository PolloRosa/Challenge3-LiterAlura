package com.pollorosa.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosResultado(
        @JsonAlias("count") Integer total,
        @JsonAlias("next") String next,
        @JsonAlias("previous") String prev,
        @JsonAlias("results") List<DatosLibro> libros
) {
}
