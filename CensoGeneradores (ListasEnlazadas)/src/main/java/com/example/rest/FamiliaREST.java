/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.rest;

/**
 *
 * @author blach
 */
import com.example.controller.dao.services.FamiliaService;
import com.example.models.Familia;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/familias")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FamiliaREST {

    private final FamiliaService familiaService = new FamiliaService();

    @GET
    public Response obtenerFamilias() {
        return Response.ok(familiaService.obtenerTodasFamilias()).build();
    }

    @GET
    @Path("/{id}")
    public Response obtenerFamilia(@PathParam("id") int id) {
        Familia familia = familiaService.obtenerFamilia(id);
        if (familia != null) {
            return Response.ok(familia).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    public Response crearFamilia(Familia familia) {
        Familia nueva = familiaService.registrarFamilia(
                familia.getNombre(),
                familia.getNumeroMiembros()
        );
        return Response.status(Response.Status.CREATED).entity(nueva).build();
    }
}
