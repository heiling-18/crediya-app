/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crediya.app.controlador;

import com.mycompany.crediya.app.Modelo.Persistencia.EmpleadoDAO;
import com.mycompany.crediya.app.model.Empleado;
import java.util.List;

public class EmpleadoControlador {

    private EmpleadoDAO empleadoDAO;

    public EmpleadoControlador() {
        this.empleadoDAO = new EmpleadoDAO();
    }

    public String registrarEmpleado(String nombre, String documento, String rol, String correo, double salario) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return "Error: El nombre del empleado no puede estar vacio.";
        }
        if (documento == null || !documento.matches("\\d+")) {
            return "Error: El documento debe contener unicamente numeros.";
        }
        if (rol == null || rol.trim().isEmpty()) {
            return "Error: Debe especificar el rol o cargo del empleado.";
        }
        if (salario <= 0) {
            return "Error: El salario debe ser mayor a $0.";
        }
        if (correo != null && !correo.isEmpty() && (!correo.contains("@") || !correo.contains("."))) {
            return "Error: El formato del correo es invalido.";
        }

        Empleado nuevoEmpleado = new Empleado(0, nombre, documento, correo, rol, salario);
        boolean exito = empleadoDAO.guardar(nuevoEmpleado);

        if (exito) {
            return "Empleado registrado exitosamente en el sistema.";
        } else {
            return "Error: No se pudo guardar el empleado en la base de datos (posible documento duplicado).";
        }
    }

    public List<Empleado> listarEmpleados() {
        return empleadoDAO.listarTodos();
    }

    public Empleado buscarEmpleadoPorId(int id) {
        return empleadoDAO.buscarPorId(id);
    }
}