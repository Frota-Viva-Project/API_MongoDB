package com.example.api_mongodb.controller.Impl;

import com.example.api_mongodb.controller.VeiculoDigitalTwinController;
import com.example.api_mongodb.model.VeiculoDigitalTwin;
import com.example.api_mongodb.service.VeiculoDigitalTwinService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/api")
public class VeiculoDigitalTwinControllerImpl implements VeiculoDigitalTwinController {
    private VeiculoDigitalTwinService veiculoDigitalTwinService;

    public VeiculoDigitalTwinControllerImpl(VeiculoDigitalTwinService veiculoDigitalTwinService) {
        this.veiculoDigitalTwinService = veiculoDigitalTwinService;
    }

    public ResponseEntity<VeiculoDigitalTwin> findById(@PathVariable("id_caminhao") Integer id_caminhao){
        VeiculoDigitalTwin veiculoDigitalTwin = veiculoDigitalTwinService.listarHistorico(id_caminhao);
        if (veiculoDigitalTwin == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(veiculoDigitalTwin);
    }
}
