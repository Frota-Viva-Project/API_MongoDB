package com.example.api_mongodb.mapper;

import com.example.api_mongodb.dto.*;
import com.example.api_mongodb.model.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VeiculoDigitalTwinMapper {

    public VeiculoDigitalTwinDTO toDTO(VeiculoDigitalTwin entity) {
        if (entity == null) return null;

        VeiculoDigitalTwinDTO dto = new VeiculoDigitalTwinDTO();
        dto.setId(entity.getId());
        dto.setIdCaminhao(entity.getIdCaminhao());
        dto.setPlaca(entity.getPlaca());
        dto.setModelo(entity.getModelo());
        dto.setCriadoEm(entity.getCriadoEm());
        dto.setAtualizadoEm(entity.getAtualizadoEm());

        if (entity.getHistoricoModificacoes() != null) {
            dto.setHistoricoModificacoes(
                entity.getHistoricoModificacoes().stream()
                    .map(this::toHistoricoModificacaoDTO)
                    .collect(Collectors.toList())
            );
        }

        if (entity.getHistoricoAcidentes() != null) {
            dto.setHistoricoAcidentes(
                entity.getHistoricoAcidentes().stream()
                    .map(this::toHistoricoAcidenteDTO)
                    .collect(Collectors.toList())
            );
        }

        if (entity.getUpgradesRealizados() != null) {
            dto.setUpgradesRealizados(
                entity.getUpgradesRealizados().stream()
                    .map(this::toUpgradeRealizadoDTO)
                    .collect(Collectors.toList())
            );
        }

        return dto;
    }

    public VeiculoDigitalTwin toEntity(VeiculoDigitalTwinDTO dto) {
        if (dto == null) return null;

        VeiculoDigitalTwin entity = new VeiculoDigitalTwin();
        entity.setId(dto.getId());
        entity.setIdCaminhao(dto.getIdCaminhao());
        entity.setPlaca(dto.getPlaca());
        entity.setModelo(dto.getModelo());
        entity.setCriadoEm(dto.getCriadoEm());
        entity.setAtualizadoEm(dto.getAtualizadoEm());

        if (dto.getHistoricoModificacoes() != null) {
            entity.setHistoricoModificacoes(
                dto.getHistoricoModificacoes().stream()
                    .map(this::toHistoricoModificacaoEntity)
                    .collect(Collectors.toList())
            );
        }

        if (dto.getHistoricoAcidentes() != null) {
            entity.setHistoricoAcidentes(
                dto.getHistoricoAcidentes().stream()
                    .map(this::toHistoricoAcidenteEntity)
                    .collect(Collectors.toList())
            );
        }

        if (dto.getUpgradesRealizados() != null) {
            entity.setUpgradesRealizados(
                dto.getUpgradesRealizados().stream()
                    .map(this::toUpgradeRealizadoEntity)
                    .collect(Collectors.toList())
            );
        }

        return entity;
    }

    public HistoricoModificacaoDTO toHistoricoModificacaoDTO(HistoricoModificacao entity) {
        if (entity == null) return null;

        HistoricoModificacaoDTO dto = new HistoricoModificacaoDTO();
        dto.setDataModificacao(entity.getDataModificacao());
        dto.setTipo(entity.getTipo());
        dto.setDescricao(entity.getDescricao());
        dto.setProvedorServico(entity.getProvedorServico());
        dto.setCustoTotal(entity.getCustoTotal());
        dto.setQuilometragem(entity.getQuilometragem());

        if (entity.getPecasTrocadas() != null) {
            dto.setPecasTrocadas(
                entity.getPecasTrocadas().stream()
                    .map(this::toPecaTrocadaDTO)
                    .collect(Collectors.toList())
            );
        }

        return dto;
    }

    public HistoricoModificacao toHistoricoModificacaoEntity(HistoricoModificacaoDTO dto) {
        if (dto == null) return null;

        HistoricoModificacao entity = new HistoricoModificacao();
        entity.setDataModificacao(dto.getDataModificacao());
        entity.setTipo(dto.getTipo());
        entity.setDescricao(dto.getDescricao());
        entity.setProvedorServico(dto.getProvedorServico());
        entity.setCustoTotal(dto.getCustoTotal());
        entity.setQuilometragem(dto.getQuilometragem());

        if (dto.getPecasTrocadas() != null) {
            entity.setPecasTrocadas(
                dto.getPecasTrocadas().stream()
                    .map(this::toPecaTrocadaEntity)
                    .collect(Collectors.toList())
            );
        }

        return entity;
    }

    public HistoricoAcidenteDTO toHistoricoAcidenteDTO(HistoricoAcidente entity) {
        if (entity == null) return null;

        HistoricoAcidenteDTO dto = new HistoricoAcidenteDTO();
        dto.setDataAcidente(entity.getDataAcidente());
        dto.setTipoAcidente(entity.getTipoAcidente());
        dto.setGravidade(entity.getGravidade());
        dto.setDescricao(entity.getDescricao());
        dto.setCustoReparo(entity.getCustoReparo());
        dto.setSeguroCobriu(entity.getSeguroCobriu());
        dto.setLaudoTecnico(entity.getLaudoTecnico());

        return dto;
    }

    public HistoricoAcidente toHistoricoAcidenteEntity(HistoricoAcidenteDTO dto) {
        if (dto == null) return null;

        HistoricoAcidente entity = new HistoricoAcidente();
        entity.setDataAcidente(dto.getDataAcidente());
        entity.setTipoAcidente(dto.getTipoAcidente());
        entity.setGravidade(dto.getGravidade());
        entity.setDescricao(dto.getDescricao());
        entity.setCustoReparo(dto.getCustoReparo());
        entity.setSeguroCobriu(dto.getSeguroCobriu());
        entity.setLaudoTecnico(dto.getLaudoTecnico());

        return entity;
    }

    public UpgradeRealizadoDTO toUpgradeRealizadoDTO(UpgradeRealizado entity) {
        if (entity == null) return null;

        UpgradeRealizadoDTO dto = new UpgradeRealizadoDTO();
        dto.setDataUpgrade(entity.getDataUpgrade());
        dto.setTipoUpgrade(entity.getTipoUpgrade());
        dto.setComponentes(entity.getComponentes());
        dto.setCusto(entity.getCusto());
        dto.setMelhoriasEsperadas(entity.getMelhoriasEsperadas());
        dto.setResultadosObservados(entity.getResultadosObservados());

        return dto;
    }

    public UpgradeRealizado toUpgradeRealizadoEntity(UpgradeRealizadoDTO dto) {
        if (dto == null) return null;

        UpgradeRealizado entity = new UpgradeRealizado();
        entity.setDataUpgrade(dto.getDataUpgrade());
        entity.setTipoUpgrade(dto.getTipoUpgrade());
        entity.setComponentes(dto.getComponentes());
        entity.setCusto(dto.getCusto());
        entity.setMelhoriasEsperadas(dto.getMelhoriasEsperadas());
        entity.setResultadosObservados(dto.getResultadosObservados());

        return entity;
    }

    public PecaTrocadaDTO toPecaTrocadaDTO(PecaTrocada entity) {
        if (entity == null) return null;

        PecaTrocadaDTO dto = new PecaTrocadaDTO();
        dto.setNomePeca(entity.getNomePeca());
        dto.setCusto(entity.getCusto());
        dto.setQuilometragemTroca(entity.getQuilometragemTroca());

        return dto;
    }

    public PecaTrocada toPecaTrocadaEntity(PecaTrocadaDTO dto) {
        if (dto == null) return null;

        PecaTrocada entity = new PecaTrocada();
        entity.setNomePeca(dto.getNomePeca());
        entity.setCusto(dto.getCusto());
        entity.setQuilometragemTroca(dto.getQuilometragemTroca());

        return entity;
    }
}