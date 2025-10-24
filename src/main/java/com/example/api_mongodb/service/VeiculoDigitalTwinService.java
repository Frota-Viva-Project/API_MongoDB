package com.example.api_mongodb.service;

import com.example.api_mongodb.model.HistoricoAcidente;
import com.example.api_mongodb.model.VeiculoDigitalTwin;
import org.springframework.web.bind.annotation.PathVariable;

public interface VeiculoDigitalTwinService {
    VeiculoDigitalTwin listarHistorico(@PathVariable("id_caminhao") Integer id_caminhao);
}
