package com.example.api_mongodb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistoricoAcidenteDTO {
    private Date dataAcidente;
    private String tipoAcidente;
    private String gravidade;
    private String descricao;
    private Double custoReparo;
    private Boolean seguroCobriu;
    private String laudoTecnico;
}