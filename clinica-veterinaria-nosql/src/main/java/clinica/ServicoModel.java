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
import java.math.BigDecimal;

class ServicoModel {
    static void create(ServicoBean s, MongoDatabase db) {
        MongoCollection<Document> col = db.getCollection("servico");
        col.insertOne(new Document("id", SequenceManager.getNextId(db, "servicoid"))
                     .append("valor", s.getValor().doubleValue())
                     .append("descricao", s.getDescricao()));
    }

    static List<ServicoBean> listAll(MongoDatabase db) {
        List<ServicoBean> list = new ArrayList<>();
        for (Document d : db.getCollection("servico").find().sort(new Document("id", 1))) {
            list.add(new ServicoBean(d.getInteger("id"), 
                     BigDecimal.valueOf(d.getDouble("valor")), 
                     d.getString("descricao")));
        }
        return list;
    }

    static void update(ServicoBean s, MongoDatabase db) throws Exception {
        UpdateResult result = db.getCollection("servico").updateOne(Filters.eq("id", s.getIdServico()),
            Updates.combine(
                Updates.set("valor", s.getValor().doubleValue()),
                Updates.set("descricao", s.getDescricao())
            ));
            
        if (result.getMatchedCount() == 0) {
            throw new Exception("Serviço com ID " + s.getIdServico() + " não encontrado.");
        }
    }

    static void remove(int id, MongoDatabase db) throws Exception {
        long qtdAgendamentos = db.getCollection("agendamento").countDocuments(Filters.eq("idServico", id));
        if (qtdAgendamentos > 0) {
            throw new Exception("Este serviço está vinculado a " + qtdAgendamentos + " agendamento(s). Não é possível excluir.");
        }

        DeleteResult result = db.getCollection("servico").deleteOne(Filters.eq("id", id));
        if (result.getDeletedCount() == 0) {
            throw new Exception("Serviço com ID " + id + " não encontrado para exclusão.");
        }
    }
}
