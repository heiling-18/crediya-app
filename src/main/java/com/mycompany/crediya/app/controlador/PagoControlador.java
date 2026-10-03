/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crediya.app.controlador;

import com.mycompany.crediya.app.Modelo.Persistencia.PagoDAO;
import com.mycompany.crediya.app.Modelo.Persistencia.PrestamoDAO;
import com.mycompany.crediya.app.model.EstadoPrestamo;
import com.mycompany.crediya.app.model.Pago;
import com.mycompany.crediya.app.model.Prestamo;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Heiling
 */
public class PagoControlador {

    private PagoDAO pagoDAO;
    private PrestamoDAO prestamoDAO;

    public PagoControlador() {
        this.pagoDAO = new PagoDAO();
        this.prestamoDAO = new PrestamoDAO();
    }

    public String registrarAbono(int prestamoId, double montoAbono) {
        if (montoAbono <= 0) {
            return "Error: El monto del abono debe ser mayor a $0.";
        }

        Prestamo prestamo = prestamoDAO.buscarPorId(prestamoId);
        if (prestamo == null) {
            return "Error: No existe ningun prestamo con el ID " + prestamoId + ".";
        }

        if (prestamo.getEstado() == EstadoPrestamo.PAGADO) {
            return "Aviso: Este prestamo ya se encuentra completamente PAGADO. No requiere mas abonos.";
        }

        if (montoAbono > prestamo.getSaldoPendiente()) {
            return "Error: El abono ($" + montoAbono + ") supera el saldo pendiente actual ($" + prestamo.getSaldoPendiente() + ").";
        }

        double nuevoSaldo = prestamo.getSaldoPendiente() - montoAbono;
        EstadoPrestamo nuevoEstado = (nuevoSaldo <= 0.001) ? EstadoPrestamo.PAGADO : EstadoPrestamo.PENDIENTE;

        Pago nuevoPago = new Pago(0, prestamoId, LocalDate.now(), montoAbono);
        boolean pagoGuardado = pagoDAO.guardar(nuevoPago);

        if (!pagoGuardado) {
            return "Error: No se pudo registrar el pago en la base de datos.";
        }

        boolean saldoActualizado = prestamoDAO.actualizarSaldoYEstado(prestamoId, nuevoSaldo, nuevoEstado);

        if (saldoActualizado) {
            String respuesta = "Abono registrado con exito. " +
                               "Monto abonado: $" + montoAbono + ". " +
                               "Nuevo saldo pendiente: $" + nuevoSaldo + ".";
            if (nuevoEstado == EstadoPrestamo.PAGADO) {
                respuesta += " FELICITACIONES! El prestamo ha sido PAGADO EN SU TOTALIDAD.";
            }
            return respuesta;
        } else {
            return "Advertencia: El pago se registro pero hubo un problema actualizando el saldo del prestamo.";
        }
    }

    public List<Pago> listarHistorialPagos(int prestamoId) {
        return pagoDAO.listarPorPrestamo(prestamoId);
    }
}