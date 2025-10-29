package com.example.api_mongodb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PecaTrocadaDTO {
    private String nomePeca;
    private Double custo;
    private Double quilometragemTroca;
}