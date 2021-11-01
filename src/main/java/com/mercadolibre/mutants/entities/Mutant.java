package com.mercadolibre.mutants.entities;

import lombok.*;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "mutants")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Mutant {
    //Entidad Mutante
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "dna")
    private String dnaPersist;

    @Transient
    private List<String> dna;

    @Column(name = "isMutant")
    private Boolean isMutant;
}
