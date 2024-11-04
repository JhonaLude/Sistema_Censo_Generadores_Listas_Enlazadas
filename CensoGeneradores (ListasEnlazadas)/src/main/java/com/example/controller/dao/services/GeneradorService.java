/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.controller.dao.services;

/**
 *
 * @author blach
 */
import com.example.controller.dao.GeneradorDAO;
import com.example.models.Generador;

public class GeneradorService {

    private final GeneradorDAO generadorDAO;

    public GeneradorService() {
        this.generadorDAO = new GeneradorDAO();
    }

    public Generador registrarGenerador(String marca, String modelo, double costo,
            double consumoPorHora, double generacion, String uso) {
        Generador generador = new Generador(0, marca, modelo, costo, consumoPorHora, generacion, uso);
        return generadorDAO.crear(generador);
    }

    public Generador[] obtenerTodosGeneradores() {
        return generadorDAO.obtenerTodos();
    }

    public Generador obtenerGenerador(int id) {
        return generadorDAO.obtenerPorId(id);
    }

    public boolean actualizarGenerador(Generador generador) {
        return generadorDAO.actualizar(generador);
    }
}
