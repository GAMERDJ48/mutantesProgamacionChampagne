package com.mercadolibre.mutants.repositories;

import com.mercadolibre.mutants.entities.Mutant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MutantRepository extends JpaRepository<Mutant, Long> {
    @Query(value = "SELECT (COUNT(*)) FROM mutants m WHERE m.is_mutant IS TRUE", nativeQuery = true)
    double getCountMutants();

    @Query(value = "SELECT (COUNT(*)) FROM mutants", nativeQuery = true)
    double getCountHumans();
}
