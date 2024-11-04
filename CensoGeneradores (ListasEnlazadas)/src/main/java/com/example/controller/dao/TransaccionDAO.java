/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.controller.dao;

import com.example.models.Transaccion;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author blach
 */
public class TransaccionDAO {

    private List<Transaccion> transacciones;
    private int contador;

    public TransaccionDAO() {
        this.transacciones = new LinkedList<>();
        this.contador = 0;
    }

    public Transaccion crear(Transaccion transaccion) {
        transaccion.setId(++contador); // Asigna un nuevo ID
        transacciones.add(transaccion);
        return transaccion;
    }

    public Transaccion[] obtenerTodos() {
        return transacciones.toArray(new Transaccion[0]);
    }

    public Transaccion[] obtenerPorFamiliaId(int familiaId) {
        List<Transaccion> resultado = new LinkedList<>();
        
        for (Transaccion transaccion : transacciones) {
            if (transaccion.getFamiliaId() == familiaId) {
                resultado.add(transaccion);
            }
        }

        return resultado.toArray(new Transaccion[0]);
    }
}

