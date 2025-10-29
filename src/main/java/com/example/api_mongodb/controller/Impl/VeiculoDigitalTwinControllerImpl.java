package com.example.api_mongodb.controller.Impl;

import com.example.api_mongodb.controller.VeiculoDigitalTwinController;
import com.example.api_mongodb.dto.*;
import com.example.api_mongodb.service.VeiculoDigitalTwinService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/api")
public class VeiculoDigitalTwinControllerImpl implements VeiculoDigitalTwinController {
    private VeiculoDigitalTwinService veiculoDigitalTwinService;

    public VeiculoDigitalTwinControllerImpl(VeiculoDigitalTwinService veiculoDigitalTwinService) {
        this.veiculoDigitalTwinService = veiculoDigitalTwinService;
    }

    public ResponseEntity<VeiculoDigitalTwinDTO> findById(@PathVariable("id_caminhao") Integer id_caminhao){
        VeiculoDigitalTwinDTO veiculoDigitalTwin = veiculoDigitalTwinService.listarHistorico(id_caminhao);
        if (veiculoDigitalTwin == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(veiculoDigitalTwin);
    }

    public ResponseEntity<VeiculoDigitalTwinDTO> criarVeiculo(@RequestBody VeiculoDigitalTwinDTO veiculo) {
        VeiculoDigitalTwinDTO novoVeiculo = veiculoDigitalTwinService.criarVeiculo(veiculo);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoVeiculo);
    }

    public ResponseEntity<String> adicionarHistoricoModificacao(@PathVariable("id_caminhao") Integer id_caminhao, @RequestBody HistoricoModificacaoDTO historico) {
        veiculoDigitalTwinService.adicionarHistoricoModificacao(id_caminhao, historico);
        return ResponseEntity.ok("Histórico de modificação adicionado com sucesso");
    }

    public ResponseEntity<String> adicionarHistoricoAcidente(@PathVariable("id_caminhao") Integer id_caminhao, @RequestBody HistoricoAcidenteDTO acidente) {
        veiculoDigitalTwinService.adicionarHistoricoAcidente(id_caminhao, acidente);
        return ResponseEntity.ok("Histórico de acidente adicionado com sucesso");
    }

    public ResponseEntity<String> adicionarUpgradeRealizado(@PathVariable("id_caminhao") Integer id_caminhao, @RequestBody UpgradeRealizadoDTO upgrade) {
        veiculoDigitalTwinService.adicionarUpgradeRealizado(id_caminhao, upgrade);
        return ResponseEntity.ok("Upgrade realizado adicionado com sucesso");
    }
}
