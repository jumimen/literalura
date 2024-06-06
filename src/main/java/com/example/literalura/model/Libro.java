package com.example.literalura.model;

import java.util.Optional;
import java.util.OptionalDouble;

public class Libro {
    private String titulo;
    private String autor;
    private String idioma;
    private double numeroDescargas;

    public Libro(){}
    public Libro(DatosLibro datosLibro){
        this.titulo = datosLibro.titulo();
        this.autor = datosLibro.autor().get(0).nombre();
        this.idioma = datosLibro.idiomas().get(0);
        this.numeroDescargas = OptionalDouble.of(Double.valueOf(datosLibro.numeroDescargas())).orElse(0);
    }

    @Override
    public String toString() {
        return
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", idioma='" + idioma + '\'' +
                ", numeroDescargas=" + numeroDescargas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public double getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(double numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }
}
