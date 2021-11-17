package com.mercadolibre.mutants.repositories;

import com.mercadolibre.mutants.entities.Mutant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MutantRepository extends JpaRepository<Mutant, Long> {

    @Query("SELECT count (m) FROM Mutant m WHERE m.isMutant = true")
    double searchMutants();

    @Query("SELECT count (m) FROM Mutant m")
    double searchHumans();
}
