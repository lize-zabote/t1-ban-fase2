package clinica;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

// --- SERVICO MODEL ---
class ServicoModel {
    static void create(ServicoBean s, MongoDatabase db) {
        MongoCollection<Document> col = db.getCollection("servico");
        // Mongo não guarda BigDecimal nativo perfeitamente, usamos Double ou String. 
        // Para manter simples aqui, converteremos para Double (ou String se precisasse de precisão exata)
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

    static void update(ServicoBean s, MongoDatabase db) {
        db.getCollection("servico").updateOne(Filters.eq("id", s.getIdServico()),
            Updates.combine(
                Updates.set("valor", s.getValor().doubleValue()),
                Updates.set("descricao", s.getDescricao())
            ));
    }

    static void remove(int id, MongoDatabase db) {
        db.getCollection("servico").deleteOne(Filters.eq("id", id));
    }
}