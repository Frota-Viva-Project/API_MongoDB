package com.example.api_mongodb.service.Impl;

import com.example.api_mongodb.model.VeiculoDigitalTwin;
import com.example.api_mongodb.repository.VeiculoDigitalTwinRepository;
import com.example.api_mongodb.service.VeiculoDigitalTwinService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class VeiculoDigitalTwinServiceImpl implements VeiculoDigitalTwinService {
    private final VeiculoDigitalTwinRepository veiculoDigitalTwinRepositor;

    public VeiculoDigitalTwinServiceImpl(VeiculoDigitalTwinRepository veiculoDigitalTwinRepositor) {
        this.veiculoDigitalTwinRepositor = veiculoDigitalTwinRepositor;
    }

    @Override
    public VeiculoDigitalTwin listarHistorico(@PathVariable("id_caminhao") Integer id_caminhao){
        return veiculoDigitalTwinRepositor.findByIdCaminhao(id_caminhao);
    }
}
