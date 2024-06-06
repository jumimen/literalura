package com.example.literalura.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public class Autor {
    private String nombre;
    private int fechaNacimiento;
    private int fechaFallecimiento;
    public Autor(List<DatosAutor> datosAutor){
        this.nombre = datosAutor.get(0).nombre();
        this.fechaNacimiento = OptionalInt.of(Integer.valueOf(datosAutor.get(0).fechaNacimiento())).orElse(0);
        this.fechaFallecimiento = OptionalInt.of(Integer.valueOf(datosAutor.get(0).fechaFallecimiento())).orElse(0);
    }

    @Override
    public String toString() {
        return
                "nombre='" + nombre + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", fechaFallecimiento=" + fechaFallecimiento;
    }
}
