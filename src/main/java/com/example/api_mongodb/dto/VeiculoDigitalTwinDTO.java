package com.example.api_mongodb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VeiculoDigitalTwinDTO {
    private String id;
    private Integer idCaminhao;
    private String placa;
    private String modelo;
    private List<HistoricoModificacaoDTO> historicoModificacoes;
    private List<HistoricoAcidenteDTO> historicoAcidentes;
    private List<UpgradeRealizadoDTO> upgradesRealizados;
    private Date criadoEm;
    private Date atualizadoEm;
}