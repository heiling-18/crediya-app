/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crediya.app.controlador;

import com.mycompany.crediya.app.Modelo.Persistencia.ClienteDAO;
import com.mycompany.crediya.app.Modelo.Persistencia.EmpleadoDAO;
import com.mycompany.crediya.app.Modelo.Persistencia.PrestamoDAO;
import com.mycompany.crediya.app.model.Cliente;
import com.mycompany.crediya.app.model.Empleado;
import com.mycompany.crediya.app.model.EstadoPrestamo;
import com.mycompany.crediya.app.model.Prestamo;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Heiling
 */
public class PrestamoControlador {

    private PrestamoDAO prestamoDAO;
    private ClienteDAO clienteDAO;
    private EmpleadoDAO empleadoDAO;

    public PrestamoControlador() {
        this.prestamoDAO = new PrestamoDAO();
        this.clienteDAO = new ClienteDAO();
        this.empleadoDAO = new EmpleadoDAO();
    }

    public String crearPrestamo(int clienteId, int empleadoId, double monto, double interes, int cuotas, LocalDate fechaInicio, LocalDate fechaVencimiento) {
        if (monto <= 0) {
            return "Error: El monto a prestar debe ser mayor a $0.";
        }
        if (interes < 0 || interes > 100) {
            return "Error: La tasa de interes debe estar entre 0% y 100%.";
        }
        if (cuotas <= 0) {
            return "Error: El numero de cuotas debe ser al menos 1.";
        }
        if (fechaVencimiento.isBefore(fechaInicio)) {
            return "Error: La fecha de vencimiento no puede ser anterior a la fecha de inicio.";
        }

        Cliente cliente = clienteDAO.buscarPorId(clienteId);
        if (cliente == null) {
            return "Error: No existe ningun cliente registrado con el ID " + clienteId + ".";
        }

        Empleado empleado = empleadoDAO.buscarPorId(empleadoId);
        if (empleado == null) {
            return "Error: No existe ningun empleado registrado con el ID " + empleadoId + ".";
        }

        // Calculos directos
        double montoTotal = monto + (monto * (interes / 100.0));
        double valorCuota = montoTotal / cuotas;
        double saldoPendiente = montoTotal;

        Prestamo nuevoPrestamo = new Prestamo(0, cliente, empleado, monto, interes, cuotas, 
                                             fechaInicio, fechaVencimiento, montoTotal, 
                                             valorCuota, saldoPendiente, EstadoPrestamo.PENDIENTE);

        boolean exito = prestamoDAO.guardar(nuevoPrestamo);

        if (exito) {
            return "Prestamo registrado con exito. " +
                   "Total a pagar: $" + montoTotal + ". " +
                   "Cuota mensual: $" + valorCuota + ". " +
                   "Vence el: " + fechaVencimiento;
        } else {
            return "Error: Ocurrio un problema al guardar el prestamo en la base de datos.";
        }
    }

    public List<Prestamo> listarPrestamos() {
        return prestamoDAO.listarTodos();
    }

    public Prestamo buscarPrestamoPorId(int id) {
        return prestamoDAO.buscarPorId(id);
    }
}