/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crediya.app.vista;

import com.mycompany.crediya.app.controlador.PagoControlador;
import com.mycompany.crediya.app.controlador.PrestamoControlador;
import com.mycompany.crediya.app.model.EstadoPrestamo;
import com.mycompany.crediya.app.model.Pago;
import com.mycompany.crediya.app.model.Prestamo;
import java.util.List;
import java.util.Scanner;

/**
 * Vista modular para la gestion de Pagos y Abonos a prestamos.
 * 
 * @author Heiling
 */
public class PagoVista {

    private Scanner sc;
    private PagoControlador pagoCtrl;
    private PrestamoControlador prestamoCtrl;

    public PagoVista(Scanner sc, PagoControlador pagoCtrl, PrestamoControlador prestamoCtrl) {
        this.sc = sc;
        this.pagoCtrl = pagoCtrl;
        this.prestamoCtrl = prestamoCtrl;
    }

    public void mostrarMenu() {
        String op = "";
        while (!op.equals("0")) {
            System.out.println();
            System.out.println("--- Gestion De Pagos Y Abonos ---");
            System.out.println("1. Registrar Abono a Prestamo");
            System.out.println("2. Ver Historico de Pagos de un Prestamo");
            System.out.println("0. Volver al menu principal");
            System.out.print("Opcion: ");
            op = sc.nextLine();

            if (op.equals("1")) {
                registrarAbono();
            } else if (op.equals("2")) {
                verHistoricoPagos();
            } else if (!op.equals("0")) {
                System.out.println("Opcion no valida.");
            }
        }
    }

    private void registrarAbono() {
        int prestamoId = 0;
        Prestamo prestamoEncontrado = null;

        while (true) {
            try {
                System.out.print("ID del Prestamo al que desea abonar (0 para cancelar): ");
                prestamoId = Integer.parseInt(sc.nextLine().trim());
                if (prestamoId == 0) return;

                prestamoEncontrado = prestamoCtrl.buscarPrestamoPorId(prestamoId);
                if (prestamoEncontrado == null) {
                    System.out.println("Error: No existe ningun prestamo con el ID " + prestamoId + ". Intente de nuevo.");
                    continue;
                }
                if (prestamoEncontrado.getEstado() == EstadoPrestamo.PAGADO) {
                    System.out.println("Aviso: Este prestamo ya fue cancelado en su totalidad. No requiere abonos.");
                    continue;
                }

                System.out.println("Prestamo seleccionado. Saldo pendiente actual: $" + prestamoEncontrado.getSaldoPendiente());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Error: El ID debe ser un numero.");
            }
        }

        double monto = 0;
        while (true) {
            try {
                System.out.print("Monto del abono ($): ");
                monto = Double.parseDouble(sc.nextLine().trim());
                if (monto > 0) {
                    if (monto <= prestamoEncontrado.getSaldoPendiente()) {
                        break;
                    }
                    System.out.println("Error: El abono no puede ser mayor al saldo pendiente ($" + prestamoEncontrado.getSaldoPendiente() + ").");
                    continue;
                }
                System.out.println("Error: El abono debe ser mayor a 0.");
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un monto valido.");
            }
        }

        String respuesta = pagoCtrl.registrarAbono(prestamoId, monto);
        System.out.println();
        System.out.println(respuesta);
    }

    private void verHistoricoPagos() {
        int prestamoId = 0;
        try {
            System.out.print("ID del Prestamo a consultar: ");
            prestamoId = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Error: El ID debe ser un numero.");
            return;
        }

        List<Pago> historial = pagoCtrl.listarHistorialPagos(prestamoId);
        if (historial.isEmpty()) {
            System.out.println("No se registran abonos para el prestamo #" + prestamoId);
        } else {
            System.out.println();
            System.out.println("--- Historico De Abonos (Prestamo #" + prestamoId + ") ---");
            for (Pago p : historial) {
                System.out.println(p);
            }
        }
    }
}
