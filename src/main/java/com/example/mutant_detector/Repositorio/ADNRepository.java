package com.example.mutant_detector.Repositorio;

import com.example.mutant_detector.model.ADN;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ADNRepository extends JpaRepository<ADN, Long> {
    long countByIsMutant(boolean isMutant);
}
