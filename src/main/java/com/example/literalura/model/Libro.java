package com.example.literalura.model;

import jakarta.persistence.*;

import java.util.OptionalDouble;
@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String titulo;
   // private String autor;
    private String idioma;
    private double numeroDescargas;
    @ManyToOne
    private Autor autor;

    public Libro(){}

    public Libro(DatosLibro datosLibro){
        this.titulo = datosLibro.titulo();
        //this.autor = datosLibro.autor().get(0).nombre();
        this.idioma = datosLibro.idiomas().get(0);
        this.numeroDescargas = OptionalDouble.of(Double.valueOf(datosLibro.numeroDescargas())).orElse(0);
        this.autor = new Autor(datosLibro.autor());
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

//    public String getAutor() {
//        return autor;
//    }
//
//    public void setAutor(String autor) {
//        this.autor = autor;
//    }

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

    @Override
    public String toString() {
        return
                "titulo='" + titulo + '\'' +
                        //", autor='" + autor + '\'' +
                        ",autor=" + autor.getNombre() +
                        ", idioma='" + idioma + '\'' +
                        ", numeroDescargas=" + numeroDescargas;
    }
}
