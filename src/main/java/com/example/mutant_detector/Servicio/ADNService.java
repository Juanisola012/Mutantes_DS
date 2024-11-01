package com.example.mutant_detector.Servicio;

import com.example.mutant_detector.Repositorio.ADNRepository;
import com.example.mutant_detector.model.ADN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ADNService {
    @Autowired
    private ADNRepository adnRepository;
    //estadisticas
    public Map<String, Object> getStatistics() {
        long countMutantDna = adnRepository.countByIsMutant(true);
        long countHumanDna = adnRepository.countByIsMutant(false);
        double ratio = countHumanDna > 0 ? (double) countMutantDna / countHumanDna : 0.0;

        Map<String, Object> stats = new HashMap<>();
        stats.put("count_mutant_dna", countMutantDna);
        stats.put("count_human_dna", countHumanDna);
        stats.put("ratio", ratio);

        return stats;
    }
    //logica para mutatnes y humanos
    public boolean isMutant(String[] dna) {
        int count = 0;
        int length = dna.length;

        // Contar secuencias horizontales y verticales
        for (int i = 0; i < length; i++) {
            int horizontalCount = countSequences(dna[i]);
            int verticalCount = countSequences(getVerticalSequence(dna, i));

            count += horizontalCount;
            count += verticalCount;
        }

        // Contar secuencias diagonales
        int diagonalCount = countDiagonalSequences(dna);
        count += diagonalCount;

        boolean isMutant = count > 2; // Más de dos secuencias indican mutante

        // Guardar en la base de datos
        saveADN(dna, isMutant); // Llama al método para guardar la secuencia

        return isMutant;
    }
    private void saveADN(String[] dna, boolean isMutant) {
        ADN adn = new ADN();
        adn.setSequence(String.join(",", dna)); // Guarda la secuencia como un String
        adn.setMutant(isMutant); // Guarda el estado de mutante o humano
        adnRepository.save(adn); // Guarda el ADN en la base de datos

        // Agregar registro de depuración
        System.out.println("Guardando ADN: " + String.join(",", dna) + ", Mutante: " + isMutant);
    }
    private int countSequences(String sequence) {
        int count = 0;
        int consecutive = 0;
        char lastChar = ' ';

        for (char currentChar : sequence.toCharArray()) {
            if (currentChar == lastChar) {
                consecutive++;
            } else {
                lastChar = currentChar;
                consecutive = 1;
            }
            if (consecutive == 4) {
                count++;
                consecutive = 0; // Reiniciar para evitar contar la misma secuencia
            }
        }
        return count;
    }

    private String getVerticalSequence(String[] dna, int index) {
        StringBuilder vertical = new StringBuilder();
        for (String row : dna) {
            vertical.append(row.charAt(index));
        }
        return vertical.toString();
    }

    private int countDiagonalSequences(String[] dna) {
        int count = 0;
        int length = dna.length;

        for (int i = 0; i <= length - 4; i++) {
            for (int j = 0; j <= length - 4; j++) {
                int mainDiagonalCount = countDiagonal(dna, i, j, 1, 1); // Diagonal principal
                int secondaryDiagonalCount = countDiagonal(dna, i, length - j - 1, 1, -1); // Diagonal secundaria

                count += mainDiagonalCount;
                count += secondaryDiagonalCount;

            }
        }
        return count;
    }

    private int countDiagonal(String[] dna, int row, int col, int rowStep, int colStep) {
        StringBuilder diagonal = new StringBuilder();
        int count = 0;

        while (row < dna.length && row >= 0 && col < dna.length && col >= 0) {
            diagonal.append(dna[row].charAt(col));
            row += rowStep;
            col += colStep;
        }

        count = countSequences(diagonal.toString());
        return count;
    }
}
