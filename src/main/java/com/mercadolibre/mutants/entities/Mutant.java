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

    //Atributo usado para almacenar como cadena el adn
    //Se le declara con la etiqueta Lob para que pueda almacenar cadenas de mas de 255 caracteres
    @Lob
    @Column(name = "dna")
    private String dnaPersist;

    //Atributo que NO se persiste en la base de datos, solo es usado para realizar el calculo de si es mutante o no
    @Transient
    private List<String> dna;

    @Column(name = "isMutant")
    private Boolean isMutant;
}
