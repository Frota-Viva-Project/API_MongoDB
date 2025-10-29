package com.example.api_mongodb.controller;

import com.example.api_mongodb.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface VeiculoDigitalTwinController {

    @GetMapping("/{id_caminhao}/historico")
    ResponseEntity<VeiculoDigitalTwinDTO> listarHistorico(@PathVariable("id_caminhao") Integer id_caminhao);

    @PostMapping
    ResponseEntity<VeiculoDigitalTwinDTO> criarVeiculo(@RequestBody VeiculoDigitalTwinDTO veiculoDTO);

    @PostMapping("/{id_caminhao}/historico-modificacao")
    ResponseEntity<Void> adicionarHistoricoModificacao(@PathVariable("id_caminhao") Integer id_caminhao, @RequestBody HistoricoModificacaoDTO historico);

    @PostMapping("/{id_caminhao}/historico-acidente")
    ResponseEntity<Void> adicionarHistoricoAcidente(@PathVariable("id_caminhao") Integer id_caminhao, @RequestBody HistoricoAcidenteDTO acidente);

    @PostMapping("/{id_caminhao}/upgrade-realizado")
    ResponseEntity<Void> adicionarUpgradeRealizado(@PathVariable("id_caminhao") Integer id_caminhao, @RequestBody UpgradeRealizadoDTO upgrade);
}
