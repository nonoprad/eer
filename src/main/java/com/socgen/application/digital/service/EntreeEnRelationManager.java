package com.socgen.application.digital.service;

import com.socgen.application.digital.modele.EntreeEnRelation;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class EntreeEnRelationManager {

    @PersistenceContext
    private EntityManager entityManager;


    public Long createEntreeEnRelation(EntreeEnRelation eer){
        entityManager.persist(eer);
        return eer.getId();
    }

    public EntreeEnRelation update(EntreeEnRelation eer) {
        return entityManager.merge(eer);
    }

    public EntreeEnRelation get(Long id) {
        return entityManager.find(EntreeEnRelation.class,id);
    }

    public List<EntreeEnRelation> findAll() {
        return entityManager.createNamedQuery("findAll", EntreeEnRelation.class).getResultList();
    }
}



