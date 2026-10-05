/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crediya.app.vista;

import com.mycompany.crediya.app.controlador.ClienteControlador;
import com.mycompany.crediya.app.controlador.EmpleadoControlador;
import com.mycompany.crediya.app.controlador.PrestamoControlador;
import com.mycompany.crediya.app.model.Cliente;
import com.mycompany.crediya.app.model.Empleado;
import com.mycompany.crediya.app.model.Prestamo;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * Vista modular para la gestion y registro de Prestamos.
 * 
 * @author Heiling
 */
public class PrestamoVista {

    private Scanner sc;
    private PrestamoControlador prestamoCtrl;
    private ClienteControlador clienteCtrl;
    private EmpleadoControlador empleadoCtrl;

    public PrestamoVista(Scanner sc, PrestamoControlador prestamoCtrl, ClienteControlador clienteCtrl, EmpleadoControlador empleadoCtrl) {
        this.sc = sc;
        this.prestamoCtrl = prestamoCtrl;
        this.clienteCtrl = clienteCtrl;
        this.empleadoCtrl = empleadoCtrl;
    }

    public void mostrarMenu() {
        String op = "";
        while (!op.equals("0")) {
            System.out.println();
            System.out.println("--- Gestion De Prestamos ---");
            System.out.println("1. Crear Nuevo Prestamo");
            System.out.println("2. Listar Todos los Prestamos");
            System.out.println("0. Volver al menu principal");
            System.out.print("Opcion: ");
            op = sc.nextLine();

            if (op.equals("1")) {
                crearNuevoPrestamo();
            } else if (op.equals("2")) {
                listarPrestamos();
            } else if (!op.equals("0")) {
                System.out.println("Opcion no valida.");
            }
        }
    }

    private void crearNuevoPrestamo() {
        int clienteId = 0;
        while (true) {
            try {
                System.out.print("ID del Cliente solicitante (0 para cancelar): ");
                clienteId = Integer.parseInt(sc.nextLine().trim());
                if (clienteId == 0) return;

                Cliente cliente = clienteCtrl.buscarClientePorId(clienteId);
                if (cliente != null) {
                    System.out.println("Cliente seleccionado: " + cliente.getNombre());
                    break;
                }
                System.out.println("Error: No existe ningun cliente con el ID " + clienteId + ". Intente de nuevo.");
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un numero.");
            }
        }

        int empleadoId = 0;
        while (true) {
            try {
                System.out.print("ID del Empleado que autoriza (0 para cancelar): ");
                empleadoId = Integer.parseInt(sc.nextLine().trim());
                if (empleadoId == 0) return;

                Empleado empleado = empleadoCtrl.buscarEmpleadoPorId(empleadoId);
                if (empleado != null) {
                    System.out.println("Empleado seleccionado: " + empleado.getNombre());
                    break;
                }
                System.out.println("Error: No existe ningun empleado con el ID " + empleadoId + ". Intente de nuevo.");
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un numero.");
            }
        }

        double monto = 0;
        while (true) {
            try {
                System.out.print("Monto a prestar ($): ");
                monto = Double.parseDouble(sc.nextLine().trim());
                if (monto > 0) break;
                System.out.println("Error: El monto debe ser mayor a 0.");
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un valor numerico valido.");
            }
        }

        double interes = 0;
        while (true) {
            try {
                System.out.print("Porcentaje de interes (0 a 100%): ");
                interes = Double.parseDouble(sc.nextLine().trim());
                if (interes >= 0 && interes <= 100) break;
                System.out.println("Error: La tasa de interes debe estar entre 0% y 100%.");
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un valor numerico valido.");
            }
        }

        int cuotas = 0;
        while (true) {
            try {
                System.out.print("Numero de cuotas mensuales: ");
                cuotas = Integer.parseInt(sc.nextLine().trim());
                if (cuotas > 0) break;
                System.out.println("Error: Debe ser al menos 1 cuota.");
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un numero entero de cuotas.");
            }
        }

        LocalDate fechaInicio = null;
        while (true) {
            try {
                System.out.print("Fecha de inicio (AAAA-MM-DD): ");
                fechaInicio = LocalDate.parse(sc.nextLine().trim());
                break;
            } catch (Exception e) {
                System.out.println("Error: Formato de fecha invalido. Use AAAA-MM-DD (ejemplo: 2026-10-03).");
            }
        }

        LocalDate fechaVencimiento = null;
        while (true) {
            try {
                System.out.print("Fecha de vencimiento (AAAA-MM-DD): ");
                fechaVencimiento = LocalDate.parse(sc.nextLine().trim());
                if (!fechaVencimiento.isBefore(fechaInicio)) {
                    break;
                }
                System.out.println("Error: La fecha de vencimiento no puede ser anterior a la fecha de inicio.");
            } catch (Exception e) {
                System.out.println("Error: Formato de fecha invalido. Use AAAA-MM-DD (ejemplo: 2026-12-03).");
            }
        }

        String respuesta = prestamoCtrl.crearPrestamo(clienteId, empleadoId, monto, interes, cuotas, fechaInicio, fechaVencimiento);
        System.out.println();
        System.out.println(respuesta);
    }

    private void listarPrestamos() {
        List<Prestamo> lista = prestamoCtrl.listarPrestamos();
        if (lista.isEmpty()) {
            System.out.println("No hay prestamos registrados en el sistema.");
        } else {
            System.out.println();
            System.out.println("--- Listado General De Prestamos ---");
            for (Prestamo p : lista) {
                System.out.println(p);
            }
        }
    }
}
