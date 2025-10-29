package com.example.api_mongodb.repository;

import com.example.api_mongodb.model.VeiculoDigitalTwin;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.stereotype.Repository;

import static com.mongodb.client.model.Filters.eq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;


@Repository
public class VeiculoDigitalTwinRepository {

    private final MongoCollection<Document> collection;

    @Autowired
    public VeiculoDigitalTwinRepository(MongoTemplate mongoTemplate) {
        MongoDatabase database = mongoTemplate.getDb();
        this.collection = database.getCollection("veiculos_digital_twin");
    }

    public void insert(VeiculoDigitalTwin veiculo) {
        Document doc = toDocument(veiculo);
        collection.insertOne(doc);
    }

    public VeiculoDigitalTwin findByIdCaminhao(Integer idCaminhao) {
        Document doc = collection.find(eq("id_caminhao", idCaminhao)).first();

        return fromDocument(doc);
    }

    public VeiculoDigitalTwin findByPlaca(String placa) {
        Document doc = collection.find(eq("placa", placa)).first();
        return fromDocument(doc);
    }

    public void insertHistoricoModificacao(Integer idCaminhao, Document historico) {
        collection.updateOne(
            eq("id_caminhao", idCaminhao),
            new Document("$push", new Document("historico_modificacoes", historico))
        );
    }

    public void insertHistoricoAcidente(Integer idCaminhao, Document acidente) {
        collection.updateOne(
            eq("id_caminhao", idCaminhao),
            new Document("$push", new Document("historico_acidentes", acidente))
        );
    }

    public void insertUpgradeRealizado(Integer idCaminhao, Document upgrade) {
        collection.updateOne(
            eq("id_caminhao", idCaminhao),
            new Document("$push", new Document("upgrades_realizados", upgrade))
        );
    }

    public VeiculoDigitalTwin save(VeiculoDigitalTwin veiculo) {
        Document doc = toDocument(veiculo);
        collection.insertOne(doc);
        veiculo.setId(doc.getObjectId("_id").toString());
        return veiculo;
    }

    private Document toDocument(VeiculoDigitalTwin veiculo) {
        Document doc = new Document();
        doc.append("id_caminhao", veiculo.getIdCaminhao());
        doc.append("placa", veiculo.getPlaca());
        doc.append("modelo", veiculo.getModelo());
        doc.append("historico_modificacoes", veiculo.getHistoricoModificacoes() != null ? veiculo.getHistoricoModificacoes() : new java.util.ArrayList<>());
        doc.append("historico_acidentes", veiculo.getHistoricoAcidentes() != null ? veiculo.getHistoricoAcidentes() : new java.util.ArrayList<>());
        doc.append("upgrades_realizados", veiculo.getUpgradesRealizados() != null ? veiculo.getUpgradesRealizados() : new java.util.ArrayList<>());
        doc.append("criado_em", veiculo.getCriadoEm());
        doc.append("atualizado_em", veiculo.getAtualizadoEm());
        return doc;
    }

    private VeiculoDigitalTwin fromDocument(Document doc) {
        if (doc == null) return null;

        VeiculoDigitalTwin veiculo = new VeiculoDigitalTwin();
        veiculo.setId(doc.getObjectId("_id").toString());
        veiculo.setIdCaminhao(doc.getInteger("id_caminhao"));
        veiculo.setPlaca(doc.getString("placa"));
        veiculo.setModelo(doc.getString("modelo"));
        veiculo.setCriadoEm(doc.getDate("criado_em"));
        veiculo.setAtualizadoEm(doc.getDate("atualizado_em"));
        
        veiculo.setHistoricoModificacoes(convertDocumentsToHistoricoModificacoes(doc.get("historico_modificacoes", List.class)));
        veiculo.setHistoricoAcidentes(convertDocumentsToHistoricoAcidentes(doc.get("historico_acidentes", List.class)));
        veiculo.setUpgradesRealizados(convertDocumentsToUpgradesRealizados(doc.get("upgrades_realizados", List.class)));

        return veiculo;
    }
    
    private List<com.example.api_mongodb.model.HistoricoModificacao> convertDocumentsToHistoricoModificacoes(List<Object> docs) {
        if (docs == null) return new java.util.ArrayList<>();
        
        return docs.stream()
            .filter(obj -> obj instanceof Document)
            .map(obj -> {
                Document doc = (Document) obj;
                com.example.api_mongodb.model.HistoricoModificacao historico = new com.example.api_mongodb.model.HistoricoModificacao();
                historico.setDataModificacao(doc.getDate("data_modificacao"));
                historico.setTipo(doc.getString("tipo"));
                historico.setDescricao(doc.getString("descricao"));
                historico.setProvedorServico(doc.getString("provedor_servico"));
                historico.setCustoTotal(doc.getDouble("custo_total"));
                historico.setQuilometragem(doc.getDouble("quilometragem"));
                historico.setPecasTrocadas(convertDocumentsToPecasTrocadas(doc.get("pecas_trocadas", List.class)));
                return historico;
            })
            .collect(java.util.stream.Collectors.toList());
    }
    
    private List<com.example.api_mongodb.model.HistoricoAcidente> convertDocumentsToHistoricoAcidentes(List<Object> docs) {
        if (docs == null) return new java.util.ArrayList<>();
        
        return docs.stream()
            .filter(obj -> obj instanceof Document)
            .map(obj -> {
                Document doc = (Document) obj;
                com.example.api_mongodb.model.HistoricoAcidente acidente = new com.example.api_mongodb.model.HistoricoAcidente();
                acidente.setDataAcidente(doc.getDate("data_acidente"));
                acidente.setTipoAcidente(doc.getString("tipo_acidente"));
                acidente.setGravidade(doc.getString("gravidade"));
                acidente.setDescricao(doc.getString("descricao"));
                acidente.setCustoReparo(doc.getDouble("custo_reparo"));
                acidente.setSeguroCobriu(doc.getBoolean("seguro_cobriu"));
                acidente.setLaudoTecnico(doc.getString("laudo_tecnico"));
                return acidente;
            })
            .collect(java.util.stream.Collectors.toList());
    }
    
    private List<com.example.api_mongodb.model.UpgradeRealizado> convertDocumentsToUpgradesRealizados(List<Object> docs) {
        if (docs == null) return new java.util.ArrayList<>();
        
        return docs.stream()
            .filter(obj -> obj instanceof Document)
            .map(obj -> {
                Document doc = (Document) obj;
                com.example.api_mongodb.model.UpgradeRealizado upgrade = new com.example.api_mongodb.model.UpgradeRealizado();
                upgrade.setDataUpgrade(doc.getDate("data_upgrade"));
                upgrade.setTipoUpgrade(doc.getString("tipo_upgrade"));
                upgrade.setComponentes(doc.get("componentes", List.class));
                upgrade.setCusto(doc.getDouble("custo"));
                upgrade.setMelhoriasEsperadas(doc.getString("melhorias_esperadas"));
                upgrade.setResultadosObservados(doc.getString("resultados_observados"));
                return upgrade;
            })
            .collect(java.util.stream.Collectors.toList());
    }
    
    private List<com.example.api_mongodb.model.PecaTrocada> convertDocumentsToPecasTrocadas(List<Object> docs) {
        if (docs == null) return new java.util.ArrayList<>();
        
        return docs.stream()
            .filter(obj -> obj instanceof Document)
            .map(obj -> {
                Document doc = (Document) obj;
                com.example.api_mongodb.model.PecaTrocada peca = new com.example.api_mongodb.model.PecaTrocada();
                peca.setNomePeca(doc.getString("nome_peca"));
                peca.setCusto(doc.getDouble("custo"));
                peca.setQuilometragemTroca(doc.getDouble("quilometragem_troca"));
                return peca;
            })
            .collect(java.util.stream.Collectors.toList());
    }

}
