package com.sofka;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class Factura{

    String descripcion;
    int precio;
    int cantidadProducto;
    int codigo;
    Date fechaFactura; //Fecha actual

    public Factura(String descripcion, int precio, int cantidadProducto, int codigo, Date fechaFactura) {
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidadProducto = cantidadProducto;
        this.codigo = codigo;
        this.fechaFactura = fechaFactura;
    }

    //Getters
    int getImporte(){
        return precio;
    }

    public String getDescripcion() {return descripcion;}

    public int getCantidadProducto() {return cantidadProducto;}

    public int getCodigo() {return codigo;}

    public Date getFechaFactura() {return fechaFactura;}

    @Override
    public String toString() {
        return "Factura{" +
                "descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", cantidadProducto=" + cantidadProducto +
                ", codigo=" + codigo +
                ", fechaFactura=" + fechaFactura +
                '}';
    }

}


public class Main {

    public static void main(String[] args) {

        //Creamos los objetos Factura
        Factura f1 = new Factura("ordenador", 1000,2,1, new Date(99,06,11));
        Factura f2 = new Factura("movil", 300,3,2, new Date(99,06,9));
        Factura f3 = new Factura("impresora", 200,5,3, new Date(99,06,8));
        Factura f4 = new Factura("imac", 1500,6,4, new Date(99,06,7));

        //Añadimos a la lista los objetos creados
        List<Factura> lista = new ArrayList<Factura>();
        lista.add(f1);
        lista.add(f2);
        lista.add(f3);
        lista.add(f4);


        /*---------------Ejemplo de Edgar-------------*/
        Predicate<Factura> predicado = new Predicate<Factura>() {
            @Override
            public boolean test(Factura t) {
                System.out.println("iteracion ");
                return t.getImporte() > 300;
            }
        };

        Factura facturaFiltro = lista.stream()
                .filter(predicado).findFirst().get();
        System.out.println("FACTURA UNICA " + facturaFiltro.getImporte());



        /*--------------Codigo Dáriel--------------*/

        //1) Predicado por Fecha mayor
        Predicate<Factura> predicadoFechaMayor = new Predicate<Factura>() {
            @Override
            public boolean test(Factura factura) {
                //System.out.println("Test (Fecha Mayor)");
                return factura.getFechaFactura().after(new Date(99, 6, 10));
            }
        };

        //1) Filtro por Fecha mayor
        List<Factura> facturaFiltroFecha = lista.stream()
                .filter(predicadoFechaMayor)
                .collect(Collectors.toList());
        System.out.println("Fecha Mayor: " + facturaFiltroFecha.toString());


        //2) Predicado por Fecha Menor
        Predicate<Factura> predicadoFechaMenor = new Predicate<Factura>() {
            @Override
            public boolean test(Factura factura) {
                //System.out.println("Test (Fecha Menor)");
                return factura.getFechaFactura().before(new Date(99, 6, 10));
            }
        };

        //2) Filtro por Fecha Menor
        List<Factura> facturaFiltroFecha2 = lista.stream()
                .filter(predicadoFechaMenor)
                .collect(Collectors.toList());
        System.out.println("Fecha Menor: " + facturaFiltroFecha2.toString());

        //3) Predicado de Cantidad Mayor
        Predicate<Factura> predicadoCantidadMayor = new Predicate<Factura>() {
            @Override
            public boolean test(Factura factura) {
                //System.out.println("Test (Cantidad Mayor) ");
                return factura.getCantidadProducto() > 4;
            }
        };

        //3) Filtrar la CantidadMayor
        List<Factura> facturaFiltroCantidad = lista.stream()
                .filter(predicadoCantidadMayor).collect(Collectors.toList());
        System.out.println("Factura con Cantidad Mayor: " + facturaFiltroCantidad.toString());

        //3) Predicado de Cantidad Mayor
        Predicate<Factura> predicadoCantidadMenor = new Predicate<Factura>() {
            @Override
            public boolean test(Factura factura) {
                //System.out.println("Test (Cantidad Menor) ");
                return factura.getCantidadProducto() < 3;
            }
        };

        //3) Filtrar la CantidadMayor
        List<Factura> facturaFiltroCantidad2 = lista.stream()
                .filter(predicadoCantidadMenor).collect(Collectors.toList());
        System.out.println("Factura con Cantidad Menor: " + facturaFiltroCantidad2.toString());

        //4) Predicado de Cantidad Igual
        Predicate<Factura> predicadoCantidadIgual = new Predicate<Factura>() {
            @Override
            public boolean test(Factura factura) {
                //System.out.println("Test (Cantidad Igual) ");
                return factura.getCantidadProducto() == 6;
            }
        };

        //4) Filtrar la CantidadMayor
        List<Factura> facturaFiltroCantidad3 = lista.stream()
                .filter(predicadoCantidadIgual).collect(Collectors.toList());
        System.out.println("Factura con Cantidad Igual: " + facturaFiltroCantidad3.toString());
    }
}
