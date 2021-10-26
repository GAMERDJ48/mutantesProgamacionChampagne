package com.mercadolibre.mutants.services;

import com.mercadolibre.mutants.detector.MutantDetector;
import com.mercadolibre.mutants.entities.Mutant;
import com.mercadolibre.mutants.repositories.MutantRepository;
import com.mercadolibre.mutants.stats.Stats;
import com.mercadolibre.mutants.stats.StatsExperto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Service
public class MutantService {
    private MutantRepository mutantRepository;

    public MutantService(MutantRepository mutantRepository) {
        this.mutantRepository = mutantRepository;
    }

    @Transactional
    public boolean isMutant(Mutant mutant) throws Exception {
        try {
            MutantDetector det = new MutantDetector();
            int contador = 0; // empezamos a recorrer la matriz
            List<String> ADN = mutant.getDna();
            contador = det.RecorrerDiagonal(ADN, contador);
            contador = det.RecorrerVertical(ADN, contador);
            contador = det.RecorrerHorizontal(ADN, contador);
            contador = det.RecorrerContraDiagonal(ADN, contador);
            if (contador >= 2) {
                mutant.setIsMutant(true);
                mutant.setDnaPersist(mutant.getDna().toString());
                mutant = mutantRepository.save(mutant);
                return mutant.getIsMutant();
            }
            mutant.setIsMutant(false);
            mutant.setDnaPersist(mutant.getDna().toString());
            mutant = mutantRepository.save(mutant);
            return mutant.getIsMutant();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Stats statsService() throws Exception {
        try {
            List<Mutant> mutantList = mutantRepository.findAll();
            Stats stats = StatsExperto.statsService(mutantList);
            return stats;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
