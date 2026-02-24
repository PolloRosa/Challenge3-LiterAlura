package com.pollorosa.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConsultaDatos {
    private final ObjectMapper mapper = new ObjectMapper();
    private final ConsumoAPI consumoAPI = new ConsumoAPI();

    public <T> T cargarDatos(String url, Class<T> clase) {
        var json = consumoAPI.consultar(url);
        try {
            return mapper.readValue(json, clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
