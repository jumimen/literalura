package com.example.literalura.principal;

import com.example.literalura.model.*;
import com.example.literalura.repository.AutorRepository;
import com.example.literalura.repository.LibroRepository;
import com.example.literalura.service.ConsumoAPI;
import com.example.literalura.service.ConvierteDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private final String URL_BASE = "https://gutendex.com/books/";
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private List<DatosLibro> datosLibro = new ArrayList<>();
    private LibroRepository repositorioLibro;
    private AutorRepository repositorioAutor;

    public Principal(){}
    public Principal(LibroRepository repositoryLibro, AutorRepository repositoryAutor) {
        this.repositorioLibro = repositoryLibro;
        this.repositorioAutor = repositoryAutor;
    }

    public void mostrarElMenu() {
        var opcion = -1;
        while (opcion != 6) {
            var menu = """
                    1- Buscar libro por titulo
                    2- Listado libros buscados
                    3- Listado autores libros buscados
                    4- Listar autores vivos en un determinado año
                    5- Listar libros por idioma
                    6- Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();
            switch (opcion) {
                case (1):
                    buscarLibroPorTitulo();
                    break;
                case (2):
                    mostrarLibrosBuscados();
                    break;
                case (3):
                    mostrarAutoresLibrosBuscados();
                    break;
                case (4):
                    listarAutoresVivos();
                    break;
                case (5):
                    listarLibrosPorIdioma();
                    break;
                case (6):
                    System.out.println("Saliendo de la aplicación");
                    break;
                default:
                    System.out.println("Opción no valida");
            }

        }
    }

    private Datos getDatosLibro(){
        System.out.println("Digite el titulo del libro que quiere buscar:");
        String tituloLibro = teclado.nextLine();
        var json= consumoAPI.obtenerDatos(URL_BASE + "?search=" + tituloLibro.toUpperCase().replace(" ", "+"));
        Datos datos = conversor.obtenerDatos(json, Datos.class);
        Optional<DatosLibro> libroTitulo = datos.resultados().stream()
                .findFirst();
        if(!libroTitulo.isPresent()){
            return null;
        }else {
            return datos;
        }

    }
    public void buscarLibroPorTitulo(){
        Datos datos = getDatosLibro();
        if(datos == null){
            System.out.println("Libro no encontrado");
            return;
        }
        Autor autor = new Autor(datos.resultados().get(0).autor());
        Autor autorExistente = repositorioAutor.findByNombre(autor.getNombre());
        if(autorExistente != null){
            Libro libro = new Libro(datos.resultados().get(0));
            libro.setAutor(autorExistente);
            repositorioLibro.save(libro);
            System.out.println(libro);
        }else{
            repositorioAutor.save(autor);
            Libro libro = new Libro(datos.resultados().get(0));
            libro.setAutor(autor);
            repositorioLibro.save(libro);
            System.out.println(libro);
        }
        System.out.println(autor);
    }
    public void mostrarLibrosBuscados(){
        List<Libro> libros = repositorioLibro.findAll();
        libros.stream()
                .forEach(System.out::println);
    }

    public void mostrarAutoresLibrosBuscados(){
        List<Autor> autores = repositorioAutor.findAll();
        autores.stream()
                .forEach(System.out::println);
    }

    public void listarAutoresVivos(){
        System.out.println("Digite el año en el cual quiere listar los autores vivos:");
        int anio = teclado.nextInt();
        List<Autor> autoresVivos = repositorioAutor.findAllVivosEnAnio(anio);
        autoresVivos.forEach(System.out::println);
    }
    public void listarLibrosPorIdioma(){
        System.out.println("Digite el idioma en el cual quiere ver los libros que hay.Ingles=en,Español=es,Frances=es");
        String idioma = teclado.nextLine();
        List<Libro> librosIdioma= repositorioLibro.findByIdioma(idioma);
        librosIdioma.forEach(System.out::println);
    }
}

