/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crediya.app.controlador;

import com.mycompany.crediya.app.Modelo.Persistencia.ClienteDAO;
import com.mycompany.crediya.app.model.Cliente;
import java.util.List;

/**
 * @author Heiling
 */
public class ClienteControlador {

    private ClienteDAO clienteDAO;

    public ClienteControlador() {
        this.clienteDAO = new ClienteDAO();
    }

    public String registrarCliente(String nombre, String documento, String correo, String telefono) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return "Error: El nombre del cliente no puede estar vacio.";
        }
        if (documento == null || !documento.matches("\\d+")) {
            return "Error: El documento debe contener unicamente numeros.";
        }
        if (telefono == null || !telefono.matches("\\d+")) {
            return "Error: El telefono debe contener unicamente numeros.";
        }
        if (correo != null && !correo.isEmpty() && !correo.contains("@")) {
            return "Error: El correo electronico no tiene un formato valido.";
        }

        Cliente nuevoCliente = new Cliente(0, nombre, documento, correo, telefono);
        boolean exito = clienteDAO.guardar(nuevoCliente);

        if (exito) {
            return "Cliente registrado exitosamente en el sistema.";
        } else {
            return "Error: No se pudo guardar el cliente en la base de datos (posible documento duplicado).";
        }
    }

    public List<Cliente> listarClientes() {
        return clienteDAO.listarTodos();
    }

    public Cliente buscarClientePorId(int id) {
        return clienteDAO.buscarPorId(id);
    }
}