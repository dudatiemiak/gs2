package br.com.fiap.resource;

import br.com.fiap.bo.ConsultoriaBO;
import br.com.fiap.to.ConsultoriaTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.ArrayList;

@Path("/consultoria")
public class ConsultoriaResource {
    private ConsultoriaBO consultoriaBO = new ConsultoriaBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(){
        ArrayList<ConsultoriaTO> resultado = consultoriaBO.findAll();
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
    @Path("/{id_consultoria}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByCodigo(@PathParam("id_consultoria") Long id_consultoria){
        ConsultoriaTO resultado = consultoriaBO.findByCodigo(id_consultoria);
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
    public Response save(@Valid ConsultoriaTO consultoria){
        ConsultoriaTO resultado = consultoriaBO.save(consultoria);
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
    @Path("/{id_consultoria}")
    public Response delete(@PathParam("id_consultoria") Long id_consultoria){
        Response.ResponseBuilder response = null;
        if (consultoriaBO.delete(id_consultoria)){
            response = Response.status(204); //204 - NO CONTENT
        }else {
            response = Response.status(404); //404 - NOT FOUND
        }
        return response.build();
    }

    @PUT
    @Path("/{id_consultoria}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@Valid ConsultoriaTO consultoria, @PathParam("id_consultoria") Long id_consultoria) throws SQLException {
        consultoria.setId_consultoria(id_consultoria);
        ConsultoriaTO resultado = consultoriaBO.update(consultoria);
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
