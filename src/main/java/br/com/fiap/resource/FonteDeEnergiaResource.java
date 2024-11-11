package br.com.fiap.resource;

import br.com.fiap.bo.FonteDeEnergiaBO;
import br.com.fiap.to.FonteDeEnergiaTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/fonte")
public class FonteDeEnergiaResource {
    private FonteDeEnergiaBO fonteBO = new FonteDeEnergiaBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(){
        ArrayList<FonteDeEnergiaTO> resultado = fonteBO.findAll();
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
    @Path("/{id_es}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByCodigo(@PathParam("id_es") Long id_es){
        FonteDeEnergiaTO resultado = fonteBO.findByCodigo(id_es);
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
    public Response save(@Valid FonteDeEnergiaTO fonte){
        FonteDeEnergiaTO resultado = fonteBO.save(fonte);
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
    @Path("/{id_es}")
    public Response delete(@PathParam("id_es") Long id_es){
        Response.ResponseBuilder response = null;
        if (fonteBO.delete(id_es)){
            response = Response.status(204); //204 - NO CONTENT
        }else {
            response = Response.status(404); //404 - NOT FOUND
        }
        return response.build();
    }

    @PUT
    @Path("/{id_es}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@Valid FonteDeEnergiaTO fonte, @PathParam("id_es") Long id_es){
        fonte.setId_es(id_es);
        FonteDeEnergiaTO resultado = fonteBO.update(fonte);
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
