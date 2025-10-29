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
public class UpgradeRealizadoDTO {
    private Date dataUpgrade;
    private String tipoUpgrade;
    private List<String> componentes;
    private Double custo;
    private String melhoriasEsperadas;
    private String resultadosObservados;
}