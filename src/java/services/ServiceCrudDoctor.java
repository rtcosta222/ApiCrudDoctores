/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import models.Doctor;
import repositories.RepositoryDoctor;

/**
 *
 * @author lscar
 */

@Path("/cruddoctor")
public class ServiceCrudDoctor {
    RepositoryDoctor repo;
    
    public ServiceCrudDoctor(){
        this.repo = new RepositoryDoctor();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getTablaDoctor() throws SQLException{
        List<Doctor> doctores = this.repo.getTablaDoctor();
        Gson converter = new Gson();
        String json = converter.toJson(doctores);
        return json;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path ("{id}")
    public String getDoctor(@PathParam("id") String id) throws SQLException{
        int iddoc = Integer.parseInt(id);
        Doctor doc = this.repo.getDoctor(iddoc);
        Gson converter = new Gson();
        String json = converter.toJson(doc);
        return json;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/post")
    public Response insertarDoctor(String jsonstr) throws SQLException{
        Gson converter = new Gson();
        Doctor doc = converter.fromJson(jsonstr, Doctor.class);
        this.repo.insertarDoctor(doc.getHcod(), doc.getDcod(), doc.getApe(), doc.getEspec(), doc.getSal());
        return Response.status(200).build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/put")
    public Response modificarDoctor(String jsonstr) throws SQLException{
        Gson converter = new Gson();
        Doctor doc = converter.fromJson(jsonstr, Doctor.class);
        this.repo.modificarDoctor(doc.getHcod(), doc.getDcod(), doc.getApe(), doc.getEspec(), doc.getSal());
        return Response.status(200).build();
    }
    
    @DELETE
    @Path("/delete/{id}")
    public Response eliminarDoctor(@PathParam("id") String id) throws SQLException{
        int iddoc = Integer.parseInt(id);
        this.repo.eliminarDoctor(iddoc);
        return Response.status(200).build();
    }
}
