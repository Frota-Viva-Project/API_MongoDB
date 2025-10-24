package com.example.api_mongodb.model;

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
public class VeiculoDigitalTwin {
    private String id;
    private Integer idCaminhao;
    private String placa;
    private String modelo;
    private List<HistoricoModificacao> historicoModificacoes;
    private List<HistoricoAcidente> historicoAcidentes;
    private List<UpgradeRealizado> upgradesRealizados;
    private Date criadoEm;
    private Date atualizadoEm;
}

