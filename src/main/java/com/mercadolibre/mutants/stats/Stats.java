package com.mercadolibre.mutants.stats;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Stats {
    private double count_mutant_dna;
    private double count_human_dna;
    private double mutantRatio;
}
