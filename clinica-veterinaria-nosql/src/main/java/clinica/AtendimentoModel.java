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

// --- ATENDIMENTO MODEL ---
class AtendimentoModel {
    static void create(AtendimentoBean a, MongoDatabase db) {
        MongoCollection<Document> col = db.getCollection("atendimento");
        col.insertOne(new Document("id", SequenceManager.getNextId(db, "atendimentoid"))
            .append("idAgendamento", a.getIdAgendamento())
            .append("idFuncionario", a.getIdFuncionario()));
    }

    static List<AtendimentoBean> listAll(MongoDatabase db) {
        List<AtendimentoBean> list = new ArrayList<>();
        // JOIN Complexo: Atendimento -> Agendamento -> (Animal, Servico) e Atendimento -> Funcionario
        for (Document d : db.getCollection("atendimento").find()) {
            AtendimentoBean ab = new AtendimentoBean(d.getInteger("idAgendamento"), d.getInteger("idFuncionario"));
            
            Document func = db.getCollection("funcionario").find(Filters.eq("id", ab.getIdFuncionario())).first();
            if(func != null) ab.setNomeFuncionario(func.getString("nome") + " " + func.getString("sobrenome"));
            
            Document ag = db.getCollection("agendamento").find(Filters.eq("id", ab.getIdAgendamento())).first();
            if(ag != null) {
                ab.setData(new java.sql.Date(ag.getDate("data").getTime()));
                
                Document anim = db.getCollection("animal").find(Filters.eq("id", ag.getInteger("idAnimal"))).first();
                if(anim != null) ab.setNomeAnimal(anim.getString("nome"));
                
                Document serv = db.getCollection("servico").find(Filters.eq("id", ag.getInteger("idServico"))).first();
                if(serv != null) ab.setDescServico(serv.getString("descricao"));
            }
            list.add(ab);
        }
        return list;
    }
}