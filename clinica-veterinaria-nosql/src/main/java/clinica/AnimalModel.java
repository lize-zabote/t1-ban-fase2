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

// --- ANIMAL MODEL ---
class AnimalModel {
    static void create(AnimalBean a, MongoDatabase db) {
        MongoCollection<Document> col = db.getCollection("animal");
        col.insertOne(new Document("id", SequenceManager.getNextId(db, "animalid"))
            .append("nome", a.getNome())
            .append("dataNascimento", a.getDataNascimento()) 
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
            
            Document cli = db.getCollection("cliente").find(Filters.eq("id", ab.getIdCliente())).first();
            if(cli != null) ab.setNomeCliente(cli.getString("nome") + " " + cli.getString("sobrenome"));
            
            Document esp = db.getCollection("especie").find(Filters.eq("id", ab.getIdEspecie())).first();
            if(esp != null) ab.setNomeEspecie(esp.getString("descricao"));
            
            list.add(ab);
        }
        return list;
    }

    static void update(AnimalBean a, MongoDatabase db) throws Exception {
        UpdateResult result = db.getCollection("animal").updateOne(Filters.eq("id", a.getIdAnimal()),
            Updates.combine(
                Updates.set("nome", a.getNome()),
                Updates.set("dataNascimento", a.getDataNascimento()),
                Updates.set("idEspecie", a.getIdEspecie()),
                Updates.set("idCliente", a.getIdCliente())
            ));
            
        if (result.getMatchedCount() == 0) {
            throw new Exception("Animal com ID " + a.getIdAnimal() + " não encontrado.");
        }
    }

    static void remove(int id, MongoDatabase db) throws Exception {
        // 1. Validação de Integridade
        long qtdAgendamentos = db.getCollection("agendamento").countDocuments(Filters.eq("idAnimal", id));
        if (qtdAgendamentos > 0) {
            throw new Exception("Este animal possui " + qtdAgendamentos + " agendamento(s) vinculado(s). Não é possível excluir.");
        }
        
        // 2. Validação de Existência
        DeleteResult result = db.getCollection("animal").deleteOne(Filters.eq("id", id));
        if (result.getDeletedCount() == 0) {
            throw new Exception("Animal com ID " + id + " não encontrado para exclusão.");
        }
    }
}
