/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crediya.app.vista;

import com.mycompany.crediya.app.controlador.EmpleadoControlador;
import com.mycompany.crediya.app.model.Empleado;
import java.util.List;
import java.util.Scanner;

/**
 * Vista modular para la gestion y captura de datos de Empleados.
 * 
 * @author Heiling
 */
public class EmpleadoVista {

    private Scanner sc;
    private EmpleadoControlador empleadoCtrl;

    public EmpleadoVista(Scanner sc, EmpleadoControlador empleadoCtrl) {
        this.sc = sc;
        this.empleadoCtrl = empleadoCtrl;
    }

    public void mostrarMenu() {
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
                registrarEmpleado();
            } else if (op.equals("2")) {
                listarEmpleados();
            } else if (!op.equals("0")) {
                System.out.println("Opcion no valida.");
            }
        }
    }

    private void registrarEmpleado() {
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
    }

    private void listarEmpleados() {
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
    }
}
