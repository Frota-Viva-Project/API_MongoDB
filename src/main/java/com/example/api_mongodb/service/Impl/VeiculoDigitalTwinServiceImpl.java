package com.example.api_mongodb.service.Impl;

import com.example.api_mongodb.dto.*;
import com.example.api_mongodb.mapper.VeiculoDigitalTwinMapper;
import com.example.api_mongodb.model.VeiculoDigitalTwin;
import com.example.api_mongodb.repository.VeiculoDigitalTwinRepository;
import com.example.api_mongodb.service.VeiculoDigitalTwinService;
import org.bson.Document;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;

@Service
public class VeiculoDigitalTwinServiceImpl implements VeiculoDigitalTwinService {
    private final VeiculoDigitalTwinRepository veiculoDigitalTwinRepository;
    private final VeiculoDigitalTwinMapper mapper;

    public VeiculoDigitalTwinServiceImpl(VeiculoDigitalTwinRepository veiculoDigitalTwinRepository, VeiculoDigitalTwinMapper mapper) {
        this.veiculoDigitalTwinRepository = veiculoDigitalTwinRepository;
        this.mapper = mapper;
    }

    @Override
    public VeiculoDigitalTwinDTO listarHistorico(@PathVariable("id_caminhao") Integer id_caminhao){
        VeiculoDigitalTwin veiculo = veiculoDigitalTwinRepository.findByIdCaminhao(id_caminhao);
        return mapper.toDTO(veiculo);
    }

    @Override
    public VeiculoDigitalTwinDTO criarVeiculo(@RequestBody VeiculoDigitalTwinDTO veiculoDTO) {
        VeiculoDigitalTwin veiculo = mapper.toEntity(veiculoDTO);
        
        // Definir datas de criação e atualização
        Date agora = new Date();
        veiculo.setCriadoEm(agora);
        veiculo.setAtualizadoEm(agora);
        veiculo.setId(null); // Garantir que é um novo documento
        
        VeiculoDigitalTwin veiculoSalvo = veiculoDigitalTwinRepository.save(veiculo);
        return mapper.toDTO(veiculoSalvo);
    }

    @Override
    public void adicionarHistoricoModificacao(@PathVariable("id_caminhao") Integer id_caminhao, @RequestBody HistoricoModificacaoDTO historico) {
        Document doc = new Document();
        doc.append("data_modificacao", historico.getDataModificacao() != null ? historico.getDataModificacao() : new Date());
        doc.append("tipo", historico.getTipo());
        doc.append("descricao", historico.getDescricao());
        
        // Converter PecaTrocadaDTO para Document
        java.util.List<Document> pecasDocument = new java.util.ArrayList<>();
        if (historico.getPecasTrocadas() != null) {
            for (PecaTrocadaDTO peca : historico.getPecasTrocadas()) {
                Document pecaDoc = new Document();
                pecaDoc.append("nome_peca", peca.getNomePeca());
                pecaDoc.append("custo", peca.getCusto());
                pecaDoc.append("quilometragem_troca", peca.getQuilometragemTroca());
                pecasDocument.add(pecaDoc);
            }
        }
        
        doc.append("pecas_trocadas", pecasDocument);
        doc.append("provedor_servico", historico.getProvedorServico());
        doc.append("custo_total", historico.getCustoTotal());
        doc.append("quilometragem", historico.getQuilometragem());
        veiculoDigitalTwinRepository.insertHistoricoModificacao(id_caminhao, doc);
    }

    @Override
    public void adicionarHistoricoAcidente(@PathVariable("id_caminhao") Integer id_caminhao, @RequestBody HistoricoAcidenteDTO acidente) {
        Document doc = new Document();
        doc.append("data_acidente", acidente.getDataAcidente() != null ? acidente.getDataAcidente() : new Date());
        doc.append("tipo_acidente", acidente.getTipoAcidente());
        doc.append("gravidade", acidente.getGravidade());
        doc.append("descricao", acidente.getDescricao());
        doc.append("custo_reparo", acidente.getCustoReparo());
        doc.append("seguro_cobriu", acidente.getSeguroCobriu());
        doc.append("laudo_tecnico", acidente.getLaudoTecnico());
        veiculoDigitalTwinRepository.insertHistoricoAcidente(id_caminhao, doc);
    }

    @Override
    public void adicionarUpgradeRealizado(@PathVariable("id_caminhao") Integer id_caminhao, @RequestBody UpgradeRealizadoDTO upgrade) {
        Document doc = new Document();
        doc.append("data_upgrade", upgrade.getDataUpgrade() != null ? upgrade.getDataUpgrade() : new Date());
        doc.append("tipo_upgrade", upgrade.getTipoUpgrade());
        doc.append("componentes", upgrade.getComponentes() != null ? upgrade.getComponentes() : new java.util.ArrayList<>());
        doc.append("custo", upgrade.getCusto());
        doc.append("melhorias_esperadas", upgrade.getMelhoriasEsperadas());
        doc.append("resultados_observados", upgrade.getResultadosObservados());
        veiculoDigitalTwinRepository.insertUpgradeRealizado(id_caminhao, doc);
    }
}