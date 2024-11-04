/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.controller.dao;

import com.example.models.Familia;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author blach
 */
public class FamiliaDAO {

    private List<Familia> familias;
    private int contador;

    public FamiliaDAO() {
        this.familias = new LinkedList<>();
        this.contador = 0;
    }

    public Familia crear(Familia familia) {
        familia.setId(++contador);
        familias.add(familia);
        return familia;
    }

    public Familia[] obtenerTodos() {
        return familias.toArray(new Familia[0]);
    }

    public Familia obtenerPorId(int id) {
        for (Familia fam : familias) {
            if (fam.getId() == id) {
                return fam;
            }
        }
        return null;
    }

    public boolean actualizar(Familia familia) {
        for (int i = 0; i < familias.size(); i++) {
            if (familias.get(i).getId() == familia.getId()) {
                familias.set(i, familia);
                return true;
            }
        }
        return false;
    }

    public boolean eliminar(int id) {
        return familias.removeIf(fam -> fam.getId() == id);
    }
}

