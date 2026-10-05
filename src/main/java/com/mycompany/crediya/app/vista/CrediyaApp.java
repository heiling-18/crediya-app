/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.crediya.app.vista;

import com.mycompany.crediya.app.Modelo.Persistencia.GestorArchivos;
import com.mycompany.crediya.app.controlador.*;
import com.mycompany.crediya.app.model.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Clase principal del sistema CrediYa S.A.S.
 * Orquesta la ejecucion del menu principal y delega la interaccion
 * a las vistas modulares correspondientes.
 * 
 * @author Heiling
 */
public class CrediyaApp {

    private static Scanner sc = new Scanner(System.in);

    // Controladores del sistema
    private static EmpleadoControlador empleadoCtrl = new EmpleadoControlador();
    private static ClienteControlador clienteCtrl = new ClienteControlador();
    private static PrestamoControlador prestamoCtrl = new PrestamoControlador();
    private static PagoControlador pagoCtrl = new PagoControlador();
    private static ReporteControlador reporteCtrl = new ReporteControlador();

    // Vistas modulares
    private static EmpleadoVista empleadoVista = new EmpleadoVista(sc, empleadoCtrl);
    private static ClienteVista clienteVista = new ClienteVista(sc, clienteCtrl);
    private static PrestamoVista prestamoVista = new PrestamoVista(sc, prestamoCtrl, clienteCtrl, empleadoCtrl);
    private static PagoVista pagoVista = new PagoVista(sc, pagoCtrl, prestamoCtrl);
    private static ReporteVista reporteVista = new ReporteVista(sc, reporteCtrl);

    public static void main(String[] args) {
        int opcion = -1;
        do {
            System.out.println();
            System.out.println("       Sistema De Creditos - Crediya S.A.S.       ");
            System.out.println("1. Gestion de Empleados");
            System.out.println("2. Gestion de Clientes");
            System.out.println("3. Gestion de Prestamos");
            System.out.println("4. Gestion de Pagos / Abonos");
            System.out.println("5. Modulo de Reportes");
            System.out.println("6. Exportar Datos a Archivos TXT");
            System.out.println("0. Salir del Sistema");
            System.out.println("---------------------------------------------------");
            System.out.print("Seleccione una opcion: ");

            try {
                opcion = Integer.parseInt(sc.nextLine().trim());
                switch (opcion) {
                    case 1:
                        empleadoVista.mostrarMenu();
                        break;
                    case 2:
                        clienteVista.mostrarMenu();
                        break;
                    case 3:
                        prestamoVista.mostrarMenu();
                        break;
                    case 4:
                        pagoVista.mostrarMenu();
                        break;
                    case 5:
                        reporteVista.mostrarMenu();
                        break;
                    case 6:
                        exportarArchivosTxt();
                        break;
                    case 0:
                        System.out.println();
                        System.out.println("Gracias por usar el sistema CrediYa. Hasta pronto.");
                        break;
                    default:
                        System.out.println("Opcion invalida. Intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un numero valido.");
            }
        } while (opcion != 0);
    }

    // ==========================================
    // EXPORTAR A ARCHIVOS TXT
    // ==========================================
    private static void exportarArchivosTxt() {
        System.out.println();
        System.out.println("--- Exportando Informacion a Archivos TXT ---");

        List<Empleado> empleados = empleadoCtrl.listarEmpleados();
        List<Cliente> clientes = clienteCtrl.listarClientes();
        List<Prestamo> prestamos = prestamoCtrl.listarPrestamos();

        // Recolectar todos los pagos registrados
        List<Pago> todosLosPagos = new ArrayList<>();
        for (Prestamo p : prestamos) {
            todosLosPagos.addAll(pagoCtrl.listarHistorialPagos(p.getId()));
        }

        boolean okEmp = GestorArchivos.exportarEmpleados(empleados);
        boolean okCli = GestorArchivos.exportarClientes(clientes);
        boolean okPre = GestorArchivos.exportarPrestamos(prestamos);
        boolean okPag = GestorArchivos.exportarPagos(todosLosPagos);

        if (okEmp && okCli && okPre && okPag) {
            System.out.println("Archivos generados exitosamente en la carpeta de su proyecto:");
            System.out.println("- empleados.txt (" + empleados.size() + " registros)");
            System.out.println("- clientes.txt (" + clientes.size() + " registros)");
            System.out.println("- prestamos.txt (" + prestamos.size() + " registros)");
            System.out.println("- pagos.txt (" + todosLosPagos.size() + " registros)");
        } else {
            System.out.println("Ocurrio un problema al generar algunos archivos de texto.");
        }
    }
}