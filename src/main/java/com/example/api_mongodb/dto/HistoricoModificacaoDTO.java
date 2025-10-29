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
public class HistoricoModificacaoDTO {
    private Date dataModificacao;
    private String tipo;
    private String descricao;
    private List<PecaTrocadaDTO> pecasTrocadas;
    private String provedorServico;
    private Double custoTotal;
    private Double quilometragem;
}