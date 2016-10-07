package com.socgen.application.digital.modele;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "EntreeEnRelation")
@AllArgsConstructor
@NoArgsConstructor
@Table(name="ENTREE_EN_RELATION")
@NamedQueries({
    @NamedQuery(name="findAll", query = "SELECT eer FROM EntreeEnRelation eer")
})
public class EntreeEnRelation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private Long id;
    @Getter @Setter
    private String raisonSociale;
    @Getter @Setter
    private String adresse;
}
