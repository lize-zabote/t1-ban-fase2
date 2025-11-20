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

// --- CLIENTE MODEL ---
class ClienteModel {
    static void create(ClienteBean c, MongoDatabase db) {
        MongoCollection<Document> col = db.getCollection("cliente");
        Document doc = new Document("id", SequenceManager.getNextId(db, "clienteid"))
            .append("nome", c.getNome())
            .append("sobrenome", c.getSobrenome())
            .append("telefone", c.getTelefone())
            .append("cep", c.getCep())
            .append("bairro", c.getBairro())
            .append("rua", c.getRua());
        col.insertOne(doc);
    }

    static List<ClienteBean> listAll(MongoDatabase db) {
        List<ClienteBean> list = new ArrayList<>();
        for (Document d : db.getCollection("cliente").find().sort(new Document("id", 1))) {
            list.add(new ClienteBean(d.getInteger("id"), d.getString("nome"), d.getString("sobrenome"),
                    d.getString("telefone"), d.getString("cep"), d.getString("bairro"), d.getString("rua")));
        }
        return list;
    }

    static void update(ClienteBean c, MongoDatabase db) {
        db.getCollection("cliente").updateOne(Filters.eq("id", c.getIdCliente()),
            Updates.combine(
                Updates.set("nome", c.getNome()),
                Updates.set("sobrenome", c.getSobrenome()),
                Updates.set("telefone", c.getTelefone()),
                Updates.set("cep", c.getCep()),
                Updates.set("bairro", c.getBairro()),
                Updates.set("rua", c.getRua())
            ));
    }

    static void remove(int id, MongoDatabase db) {
        db.getCollection("cliente").deleteOne(Filters.eq("id", id));
    }
}