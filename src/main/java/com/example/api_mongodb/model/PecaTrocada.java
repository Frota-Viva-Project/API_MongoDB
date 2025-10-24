package com.example.api_mongodb.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PecaTrocada {
    private String nomePeca;
    private Double custo;
    private Double quilometragemTroca;
}
