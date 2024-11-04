/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.rest;

/**
 *
 * @author blach
 */
import com.example.controller.dao.services.GeneradorService;
import com.example.models.Generador;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/generadores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GeneradorREST {

    private final GeneradorService generadorService = new GeneradorService();

    @GET
    public Response obtenerGeneradores() {
        return Response.ok(generadorService.obtenerTodosGeneradores()).build();
    }

    @GET
    @Path("/{id}")
    public Response obtenerGenerador(@PathParam("id") int id) {
        Generador generador = generadorService.obtenerGenerador(id);
        if (generador != null) {
            return Response.ok(generador).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    public Response crearGenerador(Generador generador) {
        Generador nuevo = generadorService.registrarGenerador(
                generador.getMarca(),
                generador.getModelo(),
                generador.getCosto(),
                generador.getConsumoPorHora(),
                generador.getGeneracion(),
                generador.getUso()
        );
        return Response.status(Response.Status.CREATED).entity(nuevo).build();
    }
}
