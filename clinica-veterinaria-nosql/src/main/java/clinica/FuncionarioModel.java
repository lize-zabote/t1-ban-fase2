package clinica;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

class FuncionarioModel {
    static void create(FuncionarioBean f, MongoDatabase db) {
        MongoCollection<Document> col = db.getCollection("funcionario");
        Document doc = new Document("id", SequenceManager.getNextId(db, "funcionarioid"))
            .append("nome", f.getNome())
            .append("sobrenome", f.getSobrenome())
            .append("rua", f.getRua())
            .append("cep", f.getCep())
            .append("bairro", f.getBairro())
            .append("especialidade", f.getEspecialidade())
            .append("cargo", f.getCargo());
        col.insertOne(doc);
    }
    
    static List<FuncionarioBean> listAll(MongoDatabase db) {
        List<FuncionarioBean> list = new ArrayList<>();
        for (Document d : db.getCollection("funcionario").find().sort(new Document("id", 1))) {
            list.add(new FuncionarioBean(d.getInteger("id"), d.getString("nome"), d.getString("sobrenome"),
                    d.getString("rua"), d.getString("cep"), d.getString("bairro"),
                    d.getString("especialidade"), d.getString("cargo")));
        }
        return list;
    }
    
    static void update(FuncionarioBean f, MongoDatabase db) {
        db.getCollection("funcionario").updateOne(Filters.eq("id", f.getIdFuncionario()),
            Updates.combine(
                Updates.set("nome", f.getNome()),
                Updates.set("sobrenome", f.getSobrenome()),
                Updates.set("rua", f.getRua()),
                Updates.set("cep", f.getCep()),
                Updates.set("bairro", f.getBairro()),
                Updates.set("especialidade", f.getEspecialidade()),
                Updates.set("cargo", f.getCargo())
            ));
    }

    static void remove(int id, MongoDatabase db) throws Exception {
        long qtdAtendimentos = db.getCollection("atendimento").countDocuments(Filters.eq("idFuncionario", id));
        if (qtdAtendimentos > 0) {
            throw new Exception("Este funcionário está vinculado a " + qtdAtendimentos + " atendimento(s). Não é possível excluí-lo.");
        }
        db.getCollection("funcionario").deleteOne(Filters.eq("id", id));
    }
}