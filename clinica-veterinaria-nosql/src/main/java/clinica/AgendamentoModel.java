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
import java.sql.Time;

class AgendamentoModel {
    static void create(AgendamentoBean a, MongoDatabase db) {
        MongoCollection<Document> col = db.getCollection("agendamento");
        col.insertOne(new Document("id", SequenceManager.getNextId(db, "agendamentoid"))
            .append("data", a.getData())
            .append("hora", a.getHora().toString())
            .append("idServico", a.getIdServico())
            .append("idAnimal", a.getIdAnimal()));
    }

    static List<AgendamentoBean> listAll(MongoDatabase db) {
        List<AgendamentoBean> list = new ArrayList<>();
        for (Document d : db.getCollection("agendamento").find().sort(new Document("data", 1))) {
            Time hora = Time.valueOf(d.getString("hora"));
            AgendamentoBean ab = new AgendamentoBean(d.getInteger("id"), 
                                 new java.sql.Date(d.getDate("data").getTime()), 
                                 hora, d.getInteger("idServico"), d.getInteger("idAnimal"));
            
            Document anim = db.getCollection("animal").find(Filters.eq("id", ab.getIdAnimal())).first();
            if(anim != null) ab.setNomeAnimal(anim.getString("nome"));
            
            Document serv = db.getCollection("servico").find(Filters.eq("id", ab.getIdServico())).first();
            if(serv != null) ab.setDescServico(serv.getString("descricao"));
            
            list.add(ab);
        }
        return list;
    }

    static void update(AgendamentoBean a, MongoDatabase db) throws Exception {
        UpdateResult result = db.getCollection("agendamento").updateOne(Filters.eq("id", a.getIdAgendamento()),
            Updates.combine(
                Updates.set("data", a.getData()),
                Updates.set("hora", a.getHora().toString()),
                Updates.set("idServico", a.getIdServico()),
                Updates.set("idAnimal", a.getIdAnimal())
            ));
            
        if (result.getMatchedCount() == 0) {
            throw new Exception("Agendamento com ID " + a.getIdAgendamento() + " não encontrado.");
        }
    }

    static void remove(int id, MongoDatabase db) throws Exception {
        // 1. Validação de Integridade
        long qtdAtendimentos = db.getCollection("atendimento").countDocuments(Filters.eq("idAgendamento", id));
        if (qtdAtendimentos > 0) {
            throw new Exception("Este agendamento já foi atendido por um funcionário. Não é possível excluir o histórico.");
        }
        
        // 2. Validação de Existência
        DeleteResult result = db.getCollection("agendamento").deleteOne(Filters.eq("id", id));
        if (result.getDeletedCount() == 0) {
            throw new Exception("Agendamento com ID " + id + " não encontrado para exclusão.");
        }
    }
}
