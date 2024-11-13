package br.com.fiap.resource;

import br.com.fiap.bo.CalculoEconomiaBO;
import br.com.fiap.to.CalculoEconomiaTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/economia")
public class CalculoEconomiaResource {
    private CalculoEconomiaBO calculoEconomiaBO = new CalculoEconomiaBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(){
        ArrayList<CalculoEconomiaTO> resultado = calculoEconomiaBO.findAll();
        Response.ResponseBuilder response = null;
        if (resultado != null){
            response = Response.ok(); //200 (OK)
        }else {
            response = Response.status(404); //404 (NOT FOUND)
        }
        response.entity(resultado);
        return response.build();
    }

    @GET
    @Path("/{id_economia}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByCodigo(@PathParam("id_economia") Long id_economia){
        CalculoEconomiaTO resultado = calculoEconomiaBO.findByCodigo(id_economia);
        Response.ResponseBuilder response = null;
        if (resultado != null){
            response = Response.ok(); //200 (OK)
        }else {
            response = Response.status(404); //404 (NOT FOUND)
        }
        response.entity(resultado);
        return response.build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid CalculoEconomiaTO economia){
        CalculoEconomiaTO resultado = calculoEconomiaBO.save(economia);
        Response.ResponseBuilder response = null;
        if (resultado != null){
            response = Response.created(null); //201 - CREATED
        }else {
            response = Response.status(400); //400 - BAD REQUEST
        }
        response.entity(resultado);
        return response.build();
    }

    @DELETE
    @Path("/{id_economia}")
    public Response delete(@PathParam("id_economia") Long id_economia){
        Response.ResponseBuilder response = null;
        if (calculoEconomiaBO.delete(id_economia)){
            response = Response.status(204); //204 - NO CONTENT
        }else {
            response = Response.status(404); //404 - NOT FOUND
        }
        return response.build();
    }

    @PUT
    @Path("/{id_economia}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@Valid CalculoEconomiaTO economia, @PathParam("id_economia") Long id_economia){
        economia.setId_economia(id_economia);
        CalculoEconomiaTO resultado = calculoEconomiaBO.update(economia);
        Response.ResponseBuilder response = null;
        if (resultado != null){
            response = Response.created(null); //201 - CREATED
        }else {
            response = Response.status(400); //400 - BAD REQUEST
        }
        response.entity(resultado);
        return response.build();
    }
}