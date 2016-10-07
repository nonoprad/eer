package com.socgen.application.digital.controller;



import com.socgen.application.digital.modele.EntreeEnRelation;
import com.socgen.application.digital.service.EntreeEnRelationManager;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("eer")
@Stateless
public class EntreeEnRelationController {

    @Inject
    private EntreeEnRelationManager entreeEnRelationManager;



    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<EntreeEnRelation> findAll(){
        return entreeEnRelationManager.findAll();
    }


    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createEntreeEnRelation(final EntreeEnRelation entreeEnRelation) {
        entreeEnRelationManager.createEntreeEnRelation(entreeEnRelation);
    }
}
