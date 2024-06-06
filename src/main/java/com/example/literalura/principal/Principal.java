package com.example.literalura.principal;

import com.example.literalura.model.*;
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
                    break;
                case (3):

                    break;
                case (4):
                    break;
                case (5):
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
        return datos;
    }
    public void buscarLibroPorTitulo(){
        Datos datos = getDatosLibro();
        DatosLibro libro = datos.resultados().get(0);
        List<DatosAutor> datosAutor = new ArrayList<>();
        datosAutor = libro.autor();
        System.out.println(libro);
        Libro objLibro = new Libro(libro);
        Autor objAutor = new Autor(datosAutor);
        System.out.println(objLibro);
        System.out.println(objAutor);


//        Optional<DatosLibro> libroBuscado = datosBusqueda.resultados().stream()
//                .filter(l -> l.titulo().toUpperCase().contains(tituloLibro))
//                .findFirst();
//        if(libroBuscado.isPresent()){
//            System.out.println(libroBuscado);
//            System.out.println("------------------");
//        }else{
//            System.out.println("Libro no encontrado");
//        }
    }
}