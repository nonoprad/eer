package com.socgen.application.digital.modele;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

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
@ToString
@EqualsAndHashCode
public class EntreeEnRelation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private Long id;
    @Getter @Setter
    private String raisonSociale;
    @Getter @Setter
    private String adresse;
    @Getter @Setter
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dateCreate;
    @Getter @Setter
    private String typeSociete;
    @Version
    @Column(columnDefinition = "int4 default '0'")
    private int version;
}
