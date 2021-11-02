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

    //Servicio principal que se encarga de recibir un mutante y guardarlo en la base de datos con su respectivo resultado.
    @Transactional
    public boolean isMutant(Mutant mutant) throws Exception {
        try {
            MutantDetector det = new MutantDetector();

            //LLamo al MutantDetector que me dice si es mutante o no
            boolean result = det.mutantDetector(mutant.getDna());

            //Seteo el resultado en la entidad Mutante
            mutant.setIsMutant(result);

            //Convierto la lista de cadenas en una sola cadena para luego setearla en otra variable,
            //de esa forma guardamos una cadena en la base de datos y no una lista
            mutant.setDnaPersist(mutant.getDna().toString());

            mutant = mutantRepository.save(mutant);
            return mutant.getIsMutant();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //Metodo que retorna las estadisticas de las consultas
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
