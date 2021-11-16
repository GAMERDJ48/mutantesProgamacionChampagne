package com.mercadolibre.mutants.stats;

import com.mercadolibre.mutants.entities.Mutant;
import com.mercadolibre.mutants.repositories.MutantRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StatsExperto {
    @Autowired
    MutantRepository mutantRepository;

    public StatsExperto(MutantRepository mutantRepository) {
        this.mutantRepository = mutantRepository;
    }

    public Stats statsService() {
        Stats stats = new Stats();
        double countHuman = this.mutantRepository.getCountHumans();
        double countMutant = this.mutantRepository.getCountMutants();
        double mutantRatio;
        if(countHuman!=0){
            mutantRatio = countMutant / countHuman;
        }else{
            mutantRatio = 0;
        }
        stats.setCount_human_dna(countHuman);
        stats.setCount_mutant_dna(countMutant);
        stats.setMutantRatio(mutantRatio);
        return stats;
    }
}
