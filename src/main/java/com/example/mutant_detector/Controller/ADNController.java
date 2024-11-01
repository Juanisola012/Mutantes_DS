package com.example.mutant_detector.Controller;

import com.example.mutant_detector.Servicio.ADNService;
import com.example.mutant_detector.request.DNARequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/mutant")
@CrossOrigin("*")
public class ADNController {

    private final ADNService adnService;

    public ADNController(ADNService adnService) {
        this.adnService = adnService;
    }

    @PostMapping
    public ResponseEntity<Void> isMutant(@RequestBody DNARequest dnaRequest) {
        boolean mutant = adnService.isMutant(dnaRequest.getMutantDNA());
        return mutant ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getStats() {
        Map<String, Object> stats = adnService.getStatistics();
        return ResponseEntity.ok(stats);
    }


}
