/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.controller.dao.services;

/**
 *
 * @author blach
 */

import com.example.controller.dao.EstadisticaDAO;
import com.example.models.Estadistica;

public class EstadisticaService {
    private final EstadisticaDAO estadisticaDAO;

    public EstadisticaService() {
        this.estadisticaDAO = new EstadisticaDAO();
    }

    public Estadistica[] obtenerTodasEstadisticas() {
        return estadisticaDAO.obtenerTodas();
    }

    public Estadistica obtenerEstadisticaPorId(int id) {
        return estadisticaDAO.obtenerPorId(id);
    }

    public boolean agregarEstadistica(Estadistica estadistica) {
        // Validaciones de negocio
        if (estadistica.getValor() < 0) {
            return false;
        }
        return estadisticaDAO.agregarEstadistica(estadistica);
    }

    public boolean actualizarEstadistica(Estadistica estadistica) {
        // Validaciones de negocio
        if (estadistica.getValor() < 0) {
            return false;
        }
        return estadisticaDAO.actualizarEstadistica(estadistica);
    }

    public boolean eliminarEstadistica(int id) {
        return estadisticaDAO.eliminarEstadistica(id);
    }

    public Estadistica[] obtenerEstadisticasPorCategoria(String categoria) {
        Estadistica[] todas = estadisticaDAO.obtenerTodas();
        int count = 0;
        
        // Primer conteo para determinar el tamaño del array
        for (Estadistica est : todas) {
            if (est.getCategoria().equals(categoria)) {
                count++;
            }
        }

        // Crear array del tamaño exacto necesario
        Estadistica[] filtradas = new Estadistica[count];
        int index = 0;
        
        // Llenar el array con las estadísticas filtradas
        for (Estadistica est : todas) {
            if (est.getCategoria().equals(categoria)) {
                filtradas[index++] = est;
            }
        }

        return filtradas;
    }
}
