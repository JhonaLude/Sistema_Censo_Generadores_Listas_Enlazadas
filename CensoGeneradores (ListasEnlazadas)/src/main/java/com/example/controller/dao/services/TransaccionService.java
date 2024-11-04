/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.controller.dao.services;

/**
 *
 * @author blach
 */
import com.example.controller.dao.TransaccionDAO;
import com.example.models.Transaccion;
import java.util.Date;

public class TransaccionService {

    private final TransaccionDAO transaccionDAO;

    public TransaccionService() {
        this.transaccionDAO = new TransaccionDAO();
    }

    public Transaccion registrarTransaccion(int familiaId, int generadorId, String tipo) {
        Transaccion transaccion = new Transaccion(0, new Date(), familiaId, generadorId, tipo);
        return transaccionDAO.crear(transaccion);
    }

    public Transaccion[] obtenerTransaccionesPorFamilia(int familiaId) {
        return transaccionDAO.obtenerPorFamiliaId(familiaId);
    }

    public Transaccion[] obtenerTodasTransacciones() {
        return transaccionDAO.obtenerTodos();
    }
}
