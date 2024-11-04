/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.controller.dao.services;

/**
 *
 * @author blach
 */

import com.example.controller.dao.FamiliaDAO;
import com.example.models.Familia;

public class FamiliaService {
    private final FamiliaDAO familiaDAO;

    public FamiliaService() {
        this.familiaDAO = new FamiliaDAO();
    }

    public Familia registrarFamilia(String nombre, int numeroMiembros) {
        Familia familia = new Familia(0, nombre, numeroMiembros, false);
        return familiaDAO.crear(familia);
    }

    public Familia[] obtenerTodasFamilias() {
        return familiaDAO.obtenerTodos();
    }

    public Familia obtenerFamilia(int id) {
        return familiaDAO.obtenerPorId(id);
    }

    public boolean actualizarEstadoGenerador(int familiaId, boolean tieneGenerador) {
        Familia familia = familiaDAO.obtenerPorId(familiaId);
        if (familia != null) {
            familia.setGeneradorAdquirido(tieneGenerador);
            return familiaDAO.actualizar(familia);
        }
        return false;
    }
}
