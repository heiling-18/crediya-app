/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crediya.app.vista;

import com.mycompany.crediya.app.controlador.ClienteControlador;
import com.mycompany.crediya.app.model.Cliente;
import java.util.List;
import java.util.Scanner;

/**
 * Vista modular para la gestion y captura de datos de Clientes.
 * 
 * @author Heiling
 */
public class ClienteVista {

    private Scanner sc;
    private ClienteControlador clienteCtrl;

    public ClienteVista(Scanner sc, ClienteControlador clienteCtrl) {
        this.sc = sc;
        this.clienteCtrl = clienteCtrl;
    }

    public void mostrarMenu() {
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
                registrarCliente();
            } else if (op.equals("2")) {
                listarClientes();
            } else if (!op.equals("0")) {
                System.out.println("Opcion no valida.");
            }
        }
    }

    private void registrarCliente() {
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
    }

    private void listarClientes() {
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
    }
}
