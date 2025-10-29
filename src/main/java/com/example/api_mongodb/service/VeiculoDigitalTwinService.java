package com.example.api_mongodb.service;

import com.example.api_mongodb.dto.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface VeiculoDigitalTwinService {
    VeiculoDigitalTwinDTO listarHistorico(@PathVariable("id_caminhao") Integer id_caminhao);
    VeiculoDigitalTwinDTO criarVeiculo(@RequestBody VeiculoDigitalTwinDTO veiculoDTO);
    void adicionarHistoricoModificacao(@PathVariable("id_caminhao") Integer id_caminhao, @RequestBody HistoricoModificacaoDTO historico);
    void adicionarHistoricoAcidente(@PathVariable("id_caminhao") Integer id_caminhao, @RequestBody HistoricoAcidenteDTO acidente);
    void adicionarUpgradeRealizado(@PathVariable("id_caminhao") Integer id_caminhao, @RequestBody UpgradeRealizadoDTO upgrade);
}
