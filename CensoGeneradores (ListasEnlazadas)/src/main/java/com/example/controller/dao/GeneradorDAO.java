/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.controller.dao;

import com.example.models.Generador;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author blach
 */
public class GeneradorDAO {

    private List<Generador> generadores;
    private int contador;

    public GeneradorDAO() {
        this.generadores = new LinkedList<>();
        this.contador = 0;
    }

    public Generador crear(Generador generador) {
        generador.setId(++contador); // Asigna un nuevo ID
        generadores.add(generador);
        return generador;
    }

    public Generador[] obtenerTodos() {
        return generadores.toArray(new Generador[0]);
    }

    public Generador obtenerPorId(int id) {
        for (Generador gen : generadores) {
            if (gen.getId() == id) {
                return gen;
            }
        }
        return null;
    }

    public boolean actualizar(Generador generador) {
        for (int i = 0; i < generadores.size(); i++) {
            if (generadores.get(i).getId() == generador.getId()) {
                generadores.set(i, generador);
                return true;
            }
        }
        return false;
    }

    public boolean eliminar(int id) {
        return generadores.removeIf(gen -> gen.getId() == id);
    }
}

