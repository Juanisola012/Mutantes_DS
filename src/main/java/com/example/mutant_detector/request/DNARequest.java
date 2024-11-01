package com.example.mutant_detector.request;

//esta clase es utilizada para poder enviar una request desde postman y recibir el JSON
public class DNARequest {
    private String[] mutantDNA;

    // Constructor
    public DNARequest() {}

    // Getter y Setter
    public String[] getMutantDNA() {
        return mutantDNA;
    }

    public void setMutantDNA(String[] mutantDNA) {
        this.mutantDNA = mutantDNA;
    }
}
