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
public class HistoricoModificacao {
    private Date dataModificacao;
    private String tipo;
    private String descricao;
    private List<PecaTrocada> pecasTrocadas;
    private String provedorServico;
    private Double custoTotal;
    private Double quilometragem;
}
