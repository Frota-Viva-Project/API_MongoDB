package com.example.api_mongodb.controller.Impl;

import com.example.api_mongodb.controller.VeiculoDigitalTwinController;
import com.example.api_mongodb.dto.*;
import com.example.api_mongodb.service.VeiculoDigitalTwinService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/veiculos")
public class VeiculoDigitalTwinControllerImpl implements VeiculoDigitalTwinController {

    private final VeiculoDigitalTwinService veiculoDigitalTwinService;

    public VeiculoDigitalTwinControllerImpl(VeiculoDigitalTwinService veiculoDigitalTwinService) {
        this.veiculoDigitalTwinService = veiculoDigitalTwinService;
    }

    @Override
    @GetMapping("/{id_caminhao}/historico")
    public ResponseEntity<VeiculoDigitalTwinDTO> listarHistorico(@PathVariable("id_caminhao") Integer id_caminhao) {
        VeiculoDigitalTwinDTO veiculo = veiculoDigitalTwinService.listarHistorico(id_caminhao);
        if (veiculo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(veiculo);
    }

    @Override
    @PostMapping
    public ResponseEntity<VeiculoDigitalTwinDTO> criarVeiculo(@RequestBody VeiculoDigitalTwinDTO veiculoDTO) {
        VeiculoDigitalTwinDTO veiculoCriado = veiculoDigitalTwinService.criarVeiculo(veiculoDTO);
        return ResponseEntity.ok(veiculoCriado);
    }

    @Override
    @PostMapping("/{id_caminhao}/historico-modificacao")
    public ResponseEntity<Void> adicionarHistoricoModificacao(@PathVariable("id_caminhao") Integer id_caminhao, @RequestBody HistoricoModificacaoDTO historico) {
        veiculoDigitalTwinService.adicionarHistoricoModificacao(id_caminhao, historico);
        return ResponseEntity.ok().build();
    }

    @Override
    @PostMapping("/{id_caminhao}/historico-acidente")
    public ResponseEntity<Void> adicionarHistoricoAcidente(@PathVariable("id_caminhao") Integer id_caminhao, @RequestBody HistoricoAcidenteDTO acidente) {
        veiculoDigitalTwinService.adicionarHistoricoAcidente(id_caminhao, acidente);
        return ResponseEntity.ok().build();
    }

    @Override
    @PostMapping("/{id_caminhao}/upgrade-realizado")
    public ResponseEntity<Void> adicionarUpgradeRealizado(@PathVariable("id_caminhao") Integer id_caminhao, @RequestBody UpgradeRealizadoDTO upgrade) {
        veiculoDigitalTwinService.adicionarUpgradeRealizado(id_caminhao, upgrade);
        return ResponseEntity.ok().build();
    }
}