/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.controller.dao;

import com.example.models.Estadistica;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author blach
 */

public class EstadisticaDAO {
    private List<Estadistica> estadisticas;

    public EstadisticaDAO() {
        this.estadisticas = new LinkedList<>();
        agregarEstadistica(new Estadistica(1, "2024-Q1", 75.5, "Ventas", "Primer trimestre"));
        agregarEstadistica(new Estadistica(2, "2024-Q1", 82.3, "Satisfacción", "Primer trimestre"));
    }

    public boolean agregarEstadistica(Estadistica estadistica) {
        return estadisticas.add(estadistica);
    }

    public Estadistica[] obtenerTodas() {
        return estadisticas.toArray(new Estadistica[0]);
    }

    public Estadistica obtenerPorId(int id) {
        for (Estadistica est : estadisticas) {
            if (est.getId() == id) {
                return est;
            }
        }
        return null;
    }

    public boolean actualizarEstadistica(Estadistica estadistica) {
        for (int i = 0; i < estadisticas.size(); i++) {
            if (estadisticas.get(i).getId() == estadistica.getId()) {
                estadisticas.set(i, estadistica);
                return true;
            }
        }
        return false;
    }

    public boolean eliminarEstadistica(int id) {
        return estadisticas.removeIf(est -> est.getId() == id);
    }
}

