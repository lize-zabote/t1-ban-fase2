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

// --- ANIMAL MODEL ---
class AnimalModel {
    static void create(AnimalBean a, MongoDatabase db) {
        MongoCollection<Document> col = db.getCollection("animal");
        col.insertOne(new Document("id", SequenceManager.getNextId(db, "animalid"))
            .append("nome", a.getNome())
            .append("dataNascimento", a.getDataNascimento()) // Mongo driver aceita java.util.Date (pai de sql.Date)
            .append("idEspecie", a.getIdEspecie())
            .append("idCliente", a.getIdCliente()));
    }

    static List<AnimalBean> listAll(MongoDatabase db) {
        List<AnimalBean> list = new ArrayList<>();
        MongoCollection<Document> col = db.getCollection("animal");
        
        for (Document d : col.find().sort(new Document("id", 1))) {
            AnimalBean ab = new AnimalBean(d.getInteger("id"), d.getString("nome"), 
                            new java.sql.Date(d.getDate("dataNascimento").getTime()), 
                            d.getInteger("idEspecie"), d.getInteger("idCliente"));
            
            // Simular JOIN (Lookup manual)
            Document cli = db.getCollection("cliente").find(Filters.eq("id", ab.getIdCliente())).first();
            if(cli != null) ab.setNomeCliente(cli.getString("nome") + " " + cli.getString("sobrenome"));
            
            Document esp = db.getCollection("especie").find(Filters.eq("id", ab.getIdEspecie())).first();
            if(esp != null) ab.setNomeEspecie(esp.getString("descricao"));
            
            list.add(ab);
        }
        return list;
    }

    static void update(AnimalBean a, MongoDatabase db) {
        db.getCollection("animal").updateOne(Filters.eq("id", a.getIdAnimal()),
            Updates.combine(
                Updates.set("nome", a.getNome()),
                Updates.set("dataNascimento", a.getDataNascimento()),
                Updates.set("idEspecie", a.getIdEspecie()),
                Updates.set("idCliente", a.getIdCliente())
            ));
    }

    static void remove(int id, MongoDatabase db) {
        db.getCollection("animal").deleteOne(Filters.eq("id", id));
    }
}