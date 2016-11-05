package com.socgen.application.digital.controller;



import com.socgen.application.digital.modele.EntreeEnRelation;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("eer")
@RequestScoped
public class EntreeEnRelationController {

    @PersistenceContext(unitName = "EERPU")
    private EntityManager entityManager;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<EntreeEnRelation> findAll(){
        return entityManager.createNamedQuery("findAll", EntreeEnRelation.class).getResultList();
    }


    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public EntreeEnRelation get(@PathParam("id") Long id){
        return entityManager.find(EntreeEnRelation.class, id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional(Transactional.TxType.REQUIRED)
    public void createEntreeEnRelation(final EntreeEnRelation entreeEnRelation) {
        entityManager.persist(entreeEnRelation);
    }
}
