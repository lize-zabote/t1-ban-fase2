package clinica;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

class EspecieModel {
    static void create(EspecieBean e, MongoDatabase db) {
        MongoCollection<Document> col = db.getCollection("especie");
        col.insertOne(new Document("id", SequenceManager.getNextId(db, "especieid"))
                     .append("descricao", e.getDescricao()));
    }

    static List<EspecieBean> listAll(MongoDatabase db) {
        List<EspecieBean> list = new ArrayList<>();
        for (Document d : db.getCollection("especie").find().sort(new Document("id", 1))) {
            list.add(new EspecieBean(d.getInteger("id"), d.getString("descricao")));
        }
        return list;
    }

    static void update(EspecieBean e, MongoDatabase db) throws Exception {
        UpdateResult result = db.getCollection("especie").updateOne(Filters.eq("id", e.getIdEspecie()),
            Updates.set("descricao", e.getDescricao()));
            
        if (result.getMatchedCount() == 0) {
            throw new Exception("Espécie com ID " + e.getIdEspecie() + " não encontrada.");
        }
    }

    static void remove(int id, MongoDatabase db) throws Exception {
        // 1. Validação de Integridade
        long qtdAnimais = db.getCollection("animal").countDocuments(Filters.eq("idEspecie", id));
        if (qtdAnimais > 0) {
            throw new Exception("Existem " + qtdAnimais + " animal(is) cadastrado(s) com esta espécie. Não é possível excluir.");
        }
        
        // 2. Validação de Existência
        DeleteResult result = db.getCollection("especie").deleteOne(Filters.eq("id", id));
        if (result.getDeletedCount() == 0) {
            throw new Exception("Espécie com ID " + id + " não encontrada para exclusão.");
        }
    }
}