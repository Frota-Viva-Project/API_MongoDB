package com.example.api_mongodb.controller;

import com.example.api_mongodb.model.VeiculoDigitalTwin;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface VeiculoDigitalTwinController {

    @GetMapping("/veiculodigitaltwin/{id_caminhao}")
    ResponseEntity<VeiculoDigitalTwin> findById(@PathVariable("id_caminhao") Integer id_caminhao);
}
