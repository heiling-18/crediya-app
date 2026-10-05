/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crediya.app.vista;

import com.mycompany.crediya.app.controlador.ReporteControlador;
import com.mycompany.crediya.app.model.Cliente;
import com.mycompany.crediya.app.model.Prestamo;
import java.util.List;
import java.util.Scanner;

/**
 * Vista modular para el modulo de Reportes y consultas analiticas con Streams.
 * 
 * @author Heiling
 */
public class ReporteVista {

    private Scanner sc;
    private ReporteControlador reporteCtrl;

    public ReporteVista(Scanner sc, ReporteControlador reporteCtrl) {
        this.sc = sc;
        this.reporteCtrl = reporteCtrl;
    }

    public void mostrarMenu() {
        String op = "";
        while (!op.equals("0")) {
            System.out.println();
            System.out.println("--- Modulo Reportes ---");
            System.out.println("1. Prestamos Activos (Pendientes)");
            System.out.println("2. Prestamos Vencidos");
            System.out.println("3. Clientes Morosos");
            System.out.println("4. Total Cartera Pendiente por Cobrar");
            System.out.println("0. Volver al menu principal");
            System.out.print("Opcion: ");
            op = sc.nextLine();

            switch (op) {
                case "1":
                    List<Prestamo> activos = reporteCtrl.obtenerPrestamosActivos();
                    if (activos.isEmpty()) {
                        System.out.println("No hay prestamos activos actualmente.");
                    } else {
                        System.out.println();
                        System.out.println("--- Prestamos Activos (" + activos.size() + ") ---");
                        activos.forEach(System.out::println);
                    }
                    break;
                case "2":
                    List<Prestamo> vencidos = reporteCtrl.obtenerPrestamosVencidos();
                    if (vencidos.isEmpty()) {
                        System.out.println("No hay prestamos vencidos en el sistema.");
                    } else {
                        System.out.println();
                        System.out.println("--- Prestamos Vencidos (" + vencidos.size() + ") ---");
                        vencidos.forEach(System.out::println);
                    }
                    break;
                case "3":
                    List<Cliente> morosos = reporteCtrl.obtenerClientesMorosos();
                    if (morosos.isEmpty()) {
                        System.out.println("No hay clientes en mora actualmente.");
                    } else {
                        System.out.println();
                        System.out.println("--- Clientes Morosos (" + morosos.size() + ") ---");
                        morosos.forEach(System.out::println);
                    }
                    break;
                case "4":
                    double total = reporteCtrl.calcularTotalCarteraPendiente();
                    System.out.println();
                    System.out.println("Total de cartera pendiente por cobrar: $" + total);
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        }
    }
}
