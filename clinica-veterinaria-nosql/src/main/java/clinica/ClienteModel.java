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
import java.sql.Date;
import java.sql.Time;

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

    static void update(ClienteBean c, MongoDatabase db) throws Exception {
        UpdateResult result = db.getCollection("cliente").updateOne(Filters.eq("id", c.getIdCliente()),
            Updates.combine(
                Updates.set("nome", c.getNome()),
                Updates.set("sobrenome", c.getSobrenome()),
                Updates.set("telefone", c.getTelefone()),
                Updates.set("cep", c.getCep()),
                Updates.set("bairro", c.getBairro()),
                Updates.set("rua", c.getRua())
            ));
        
        if (result.getMatchedCount() == 0) {
            throw new Exception("Cliente com ID " + c.getIdCliente() + " não encontrado.");
        }
    }

    static void remove(int id, MongoDatabase db) throws Exception {
        long qtdAnimais = db.getCollection("animal").countDocuments(Filters.eq("idCliente", id));
        if (qtdAnimais > 0) {
            throw new Exception("Este cliente possui " + qtdAnimais + " animal(is) cadastrado(s). Remova os animais antes de excluir o cliente.");
        }
        
        DeleteResult result = db.getCollection("cliente").deleteOne(Filters.eq("id", id));
        if (result.getDeletedCount() == 0) {
            throw new Exception("Cliente com ID " + id + " não encontrado para exclusão.");
        }
    }
}