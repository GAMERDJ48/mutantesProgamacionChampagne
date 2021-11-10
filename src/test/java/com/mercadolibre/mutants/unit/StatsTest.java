package com.mercadolibre.mutants.unit;

import com.mercadolibre.mutants.entities.Mutant;
import com.mercadolibre.mutants.repositories.MutantRepository;
import com.mercadolibre.mutants.stats.Stats;
import com.mercadolibre.mutants.stats.StatsExperto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class StatsTest {

    MutantRepository repo;

    @Test
    public void statsTest(){

        Stats stats2 = new Stats(10,2,0.2);
        double count_mutant_dna = stats2.getCount_mutant_dna();
        double count_human_dna = stats2.getCount_human_dna();
        double mutantRatio = stats2.getMutantRatio();

        Stats stats = new Stats();
        stats.setCount_human_dna(10);
        stats.setCount_mutant_dna(2);
        stats.setMutantRatio(0.2);
    }

    @Test
    public void statsExpertoTest(){
        List<Mutant> mutantes = new ArrayList<>();
        Mutant m = new Mutant();
        m.setIsMutant(true);
        mutantes.add(m);
        StatsExperto.statsService(mutantes);
    }


}
