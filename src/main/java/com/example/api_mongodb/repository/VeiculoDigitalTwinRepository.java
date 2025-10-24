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

    private Document toDocument(VeiculoDigitalTwin veiculo) {
        Document doc = new Document();
        doc.append("id_caminhao", veiculo.getIdCaminhao());
        doc.append("placa", veiculo.getPlaca());
        doc.append("modelo", veiculo.getModelo());
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
        veiculo.setHistoricoModificacoes(doc.get("historico_modificacoes", List.class));
        veiculo.setUpgradesRealizados(doc.get("upgrades_realizados", List.class));
        veiculo.setHistoricoAcidentes(doc.get("historico_acidentes", List.class));
        Document metricasDoc = doc.get("metricas_consolidadas", Document.class);


        return veiculo;
    }

}
