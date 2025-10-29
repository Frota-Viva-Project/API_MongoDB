package com.example.api_mongodb.controller;

import com.example.api_mongodb.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface VeiculoDigitalTwinController {

    @GetMapping("/veiculodigitaltwin/{id_caminhao}")
    ResponseEntity<VeiculoDigitalTwinDTO> findById(@PathVariable("id_caminhao") Integer id_caminhao);

    @PostMapping("/veiculodigitaltwin")
    ResponseEntity<VeiculoDigitalTwinDTO> criarVeiculo(@RequestBody VeiculoDigitalTwinDTO veiculo);

    @PostMapping("/veiculodigitaltwin/{id_caminhao}/historico-modificacao")
    ResponseEntity<String> adicionarHistoricoModificacao(@PathVariable("id_caminhao") Integer id_caminhao, @RequestBody HistoricoModificacaoDTO historico);

    @PostMapping("/veiculodigitaltwin/{id_caminhao}/historico-acidente")
    ResponseEntity<String> adicionarHistoricoAcidente(@PathVariable("id_caminhao") Integer id_caminhao, @RequestBody HistoricoAcidenteDTO acidente);

    @PostMapping("/veiculodigitaltwin/{id_caminhao}/upgrade-realizado")
    ResponseEntity<String> adicionarUpgradeRealizado(@PathVariable("id_caminhao") Integer id_caminhao, @RequestBody UpgradeRealizadoDTO upgrade);
}
