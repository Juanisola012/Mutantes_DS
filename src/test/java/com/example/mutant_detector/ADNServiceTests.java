package com.example.mutant_detector;

import com.example.mutant_detector.Repositorio.ADNRepository;
import com.example.mutant_detector.Servicio.ADNService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = MutantDetectorApplication.class)
@ExtendWith(SpringExtension.class)
public class ADNServiceTests {
	@Autowired
	private ADNService adnService; // Inyectar el servicio

	@Autowired
	private ADNRepository adnRepository; // Inyectar el repositorio
	@Test
	public void testMutant() {
		String[] mutantDNA = {
				"ATGCGA",
				"CAGTGC",
				"TTATGT",
				"AGAAGG",
				"CCCCTA",
				"TCACTG"
		};
		assertTrue(adnService.isMutant(mutantDNA), "El ADN detectado es de un mutante");
	}

	@Test
	public void testHuman() {
		String[] humanDNA = {
				"ATGCGA",
				"CAGTGC",
				"TTATGT",
				"AGAAAG",
				"CCCCTA",
				"TCACTG"
		};
		assertFalse(adnService.isMutant(humanDNA), "El ADN detectado es el de un Humano");
	}
}