/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.crediya.app.vista;

import com.mycompany.crediya.app.controlador.*;
import com.mycompany.crediya.app.model.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class CrediyaApp {

    private static Scanner sc = new Scanner(System.in);
    private static EmpleadoControlador empleadoCtrl = new EmpleadoControlador();
    private static ClienteControlador clienteCtrl = new ClienteControlador();
    private static PrestamoControlador prestamoCtrl = new PrestamoControlador();
    private static PagoControlador pagoCtrl = new PagoControlador();
    private static ReporteControlador reporteCtrl = new ReporteControlador();

    public static void main(String[] args) {
        int opcion = -1;
        do {
            System.out.println();
            System.out.println("       Sistema De Creditos - Crediya S.A.S.       ");
            System.out.println("1. Gestion de Empleados");
            System.out.println("2. Gestion de Clientes");
            System.out.println("3. Gestion de Prestamos");
            System.out.println("4. Gestion de Pagos / Abonos");
            System.out.println("5. Modulo de Reportes (Streams y Lambdas)");
            System.out.println("0. Salir del Sistema");
            System.out.println("---------------------------------------------------");
            System.out.print("Seleccione una opcion: ");

            try {
                opcion = Integer.parseInt(sc.nextLine().trim());
                switch (opcion) {
                    case 1: menuEmpleados(); break;
                    case 2: menuClientes(); break;
                    case 3: menuPrestamos(); break;
                    case 4: menuPagos(); break;
                    case 5: menuReportes(); break;
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
    // MODULO EMPLEADOS
    // ==========================================
    private static void menuEmpleados() {
        String op = "";
        while (!op.equals("0")) {
            System.out.println();
            System.out.println("--- Gestion De Empleados ---");
            System.out.println("1. Registrar Empleado");
            System.out.println("2. Listar Empleados");
            System.out.println("0. Volver al menu principal");
            System.out.print("Opcion: ");
            op = sc.nextLine();

            if (op.equals("1")) {
                String nombre = "";
                while (true) {
                    System.out.print("Nombre completo: ");
                    nombre = sc.nextLine().trim();
                    if (nombre.matches("^[a-zA-Z ]+$") && !nombre.isEmpty()) {
                        break;
                    }
                    System.out.println("Error: El nombre solo puede contener letras. Intente de nuevo.");
                }

                String doc = "";
                while (true) {
                    System.out.print("Documento (Cedula): ");
                    doc = sc.nextLine().trim();
                    if (doc.matches("\\d+")) {
                        break;
                    }
                    System.out.println("Error: El documento debe contener solo numeros. Intente de nuevo.");
                }

                String rol = "";
                while (true) {
                    System.out.print("Rol / Cargo: ");
                    rol = sc.nextLine().trim();
                    if (rol.matches("^[a-zA-Z ]+$") && !rol.isEmpty()) {
                        break;
                    }
                    System.out.println("Error: El cargo solo puede contener letras. Intente de nuevo.");
                }

                String correo = "";
                while (true) {
                    System.out.print("Correo electronico: ");
                    correo = sc.nextLine().trim();
                    if (correo.contains("@") && correo.contains(".")) {
                        break;
                    }
                    System.out.println("Error: Ingrese un correo valido con @ y un punto (ej: usuario@correo.com). Intente de nuevo.");
                }

                double salario = 0;
                while (true) {
                    try {
                        System.out.print("Salario mensual: ");
                        salario = Double.parseDouble(sc.nextLine().trim());
                        if (salario > 0) {
                            break;
                        }
                        System.out.println("Error: El salario debe ser mayor a 0. Intente de nuevo.");
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Ingrese un valor numerico valido.");
                    }
                }

                String respuesta = empleadoCtrl.registrarEmpleado(nombre, doc, rol, correo, salario);
                System.out.println(respuesta);

            } else if (op.equals("2")) {
                List<Empleado> lista = empleadoCtrl.listarEmpleados();
                if (lista.isEmpty()) {
                    System.out.println("No hay empleados registrados en la base de datos.");
                } else {
                    System.out.println();
                    System.out.println("--- Lista De Empleados ---");
                    for (Empleado e : lista) {
                        System.out.println(e);
                    }
                }
            } else if (!op.equals("0")) {
                System.out.println("Opcion no valida.");
            }
        }
    }

 
    // MODULO CLIENTES
 
    private static void menuClientes() {
        String op = "";
        while (!op.equals("0")) {
            System.out.println();
            System.out.println("--- Gestion De Clientes ---");
            System.out.println("1. Registrar Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("0. Volver al menu principal");
            System.out.print("Opcion: ");
            op = sc.nextLine();

            if (op.equals("1")) {
                String nombre = "";
                while (true) {
                    System.out.print("Nombre completo: ");
                    nombre = sc.nextLine().trim();
                    if (nombre.matches("^[a-zA-Z ]+$") && !nombre.isEmpty()) {
                        break;
                    }
                    System.out.println("Error: El nombre solo puede contener letras. Intente de nuevo.");
                }

                String doc = "";
                while (true) {
                    System.out.print("Documento (Cedula): ");
                    doc = sc.nextLine().trim();
                    if (doc.matches("\\d+")) {
                        break;
                    }
                    System.out.println("Error: El documento debe contener solo numeros. Intente de nuevo.");
                }

                String correo = "";
                while (true) {
                    System.out.print("Correo electronico: ");
                    correo = sc.nextLine().trim();
                    if (correo.contains("@") && correo.contains(".")) {
                        break;
                    }
                    System.out.println("Error: Ingrese un correo valido con @ y un punto (ej: usuario@correo.com). Intente de nuevo.");
                }

                String tel = "";
                while (true) {
                    System.out.print("Telefono de contacto: ");
                    tel = sc.nextLine().trim();
                    if (tel.matches("\\d+")) {
                        break;
                    }
                    System.out.println("Error: El telefono debe contener solo numeros. Intente de nuevo.");
                }

                String respuesta = clienteCtrl.registrarCliente(nombre, doc, correo, tel);
                System.out.println(respuesta);

            } else if (op.equals("2")) {
                List<Cliente> lista = clienteCtrl.listarClientes();
                if (lista.isEmpty()) {
                    System.out.println("No hay clientes registrados en la base de datos.");
                } else {
                    System.out.println();
                    System.out.println("--- Lista De Clientes ---");
                    for (Cliente c : lista) {
                        System.out.println(c);
                    }
                }
            } else if (!op.equals("0")) {
                System.out.println("Opcion no valida.");
            }
        }
    }
    
    // ==========================================
    // MODULO PRESTAMOS (Fechas Manuales Directas)
    // ==========================================
    private static void menuPrestamos() {
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

                // 1. FECHA DE INICIO MANUAL
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

                // 2. FECHA DE VENCIMIENTO MANUAL
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

            } else if (op.equals("2")) {
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
            } else if (!op.equals("0")) {
                System.out.println("Opcion no valida.");
            }
        }
    }

    // ==========================================
    // MODULO PAGOS
    // ==========================================
    private static void menuPagos() {
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

            } else if (op.equals("2")) {
                int prestamoId = 0;
                try {
                    System.out.print("ID del Prestamo a consultar: ");
                    prestamoId = Integer.parseInt(sc.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("Error: El ID debe ser un numero.");
                    continue;
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
            } else if (!op.equals("0")) {
                System.out.println("Opcion no valida.");
            }
        }
    }

    // ==========================================
    // MODULO REPORTES
    // ==========================================
    private static void menuReportes() {
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
