/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.rest;

/**
 *
 * @author blach
 */
import com.example.controller.dao.services.TransaccionService;
import com.example.models.Transaccion;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/transacciones")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TransaccionREST {

    private final TransaccionService transaccionService = new TransaccionService();

    @GET
    public Response obtenerTransacciones() {
        return Response.ok(transaccionService.obtenerTodasTransacciones()).build();
    }

    @GET
    @Path("/familia/{familiaId}")
    public Response obtenerTransaccionesPorFamilia(@PathParam("familiaId") int familiaId) {
        Transaccion[] transacciones = transaccionService.obtenerTransaccionesPorFamilia(familiaId);
        if (transacciones != null && transacciones.length > 0) {
            return Response.ok(transacciones).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    public Response crearTransaccion(Transaccion transaccion) {
        Transaccion nueva = transaccionService.registrarTransaccion(
                transaccion.getFamiliaId(),
                transaccion.getGeneradorId(),
                transaccion.getTipo()
        );
        return Response.status(Response.Status.CREATED).entity(nueva).build();
    }

    @GET
    @Path("/{id}")
    public Response obtenerTransaccion(@PathParam("id") int id) {
        Transaccion[] transacciones = transaccionService.obtenerTodasTransacciones();
        for (Transaccion t : transacciones) {
            if (t.getId() == id) {
                return Response.ok(t).build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
