package com.pollorosa.literalura.model;

public enum Idioma {
    es("es", "español"),
    en("en", "inglés"),
    fr("fr", "francés"),
    nl("nl", "holandés"),
    de("de", "alemán"),
    pt("pt", "portugués");

    private String idiomaAPI;
    private String idiomaNombreCompleto;

    Idioma(String idiomaAPI, String idiomaNombreCompleto) {
        this.idiomaAPI = idiomaAPI;
        this.idiomaNombreCompleto = idiomaNombreCompleto;
    }

    public static Idioma fromString(String text) {
        for (Idioma idi : Idioma.values()) {
            if (idi.idiomaAPI.equalsIgnoreCase(text)) {
                return idi;
            }
        }
        throw new IllegalArgumentException("Ningún idioma encontrado: " + text);
    }

    public String imprimir() {
        return idiomaAPI + " - " + idiomaNombreCompleto;
    }
}
