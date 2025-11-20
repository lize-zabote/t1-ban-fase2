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

// --- RELATORIO MODEL ---
class RelatorioModel {
    
    public static void relatorioAnimaisPorFuncionario(int idFuncionario, MongoDatabase db) {
        System.out.println("\n--- Relatório de Atendimentos por Funcionário ---");
        
        // Buscar nome do funcionário
        Document funcDoc = db.getCollection("funcionario").find(Filters.eq("id", idFuncionario)).first();
        if(funcDoc == null) { System.out.println("Funcionário não encontrado."); return; }
        System.out.println("Funcionário: " + funcDoc.getString("nome"));
        System.out.println("-------------------------------------------------");

        // Buscar atendimentos desse funcionário
        boolean found = false;
        for(Document atend : db.getCollection("atendimento").find(Filters.eq("idFuncionario", idFuncionario))) {
            // Join manual para pegar detalhes
            Document ag = db.getCollection("agendamento").find(Filters.eq("id", atend.getInteger("idAgendamento"))).first();
            if(ag != null) {
                Document animal = db.getCollection("animal").find(Filters.eq("id", ag.getInteger("idAnimal"))).first();
                Document servico = db.getCollection("servico").find(Filters.eq("id", ag.getInteger("idServico"))).first();
                Document especie = db.getCollection("especie").find(Filters.eq("id", animal.getInteger("idEspecie"))).first();
                Document cliente = db.getCollection("cliente").find(Filters.eq("id", animal.getInteger("idCliente"))).first();
                
                System.out.println("  Data: " + ag.getDate("data") + 
                                 " | Animal: " + animal.getString("nome") + 
                                 " | Espécie: " + (especie != null ? especie.getString("descricao") : "N/A") +
                                 " | Dono: " + (cliente != null ? cliente.getString("nome") : "N/A") + 
                                 " | Serviço: " + (servico != null ? servico.getString("descricao") : "N/A"));
                found = true;
            }
        }
        if (!found) System.out.println("Nenhum atendimento encontrado.");
        System.out.println("-------------------------------------------------\n");
    }

    public static void relatorioAgendamentosPorPeriodo(Date dataInicio, Date dataFim, MongoDatabase db) {
        System.out.println("\n--- Relatório de Agendamentos de " + dataInicio + " a " + dataFim + " ---");
        
        // Filtro de data (gte = maior ou igual, lte = menor ou igual)
        for(Document ag : db.getCollection("agendamento").find(Filters.and(
                Filters.gte("data", dataInicio), Filters.lte("data", dataFim))).sort(new Document("data", 1))) {
            
             Document animal = db.getCollection("animal").find(Filters.eq("id", ag.getInteger("idAnimal"))).first();
             Document cliente = db.getCollection("cliente").find(Filters.eq("id", animal.getInteger("idCliente"))).first();
             Document servico = db.getCollection("servico").find(Filters.eq("id", ag.getInteger("idServico"))).first();
             
             System.out.println("  Data: " + ag.getDate("data") +
                              " | Hora: " + ag.getString("hora") +
                              " | Animal: " + animal.getString("nome") + 
                              " | Dono: " + (cliente != null ? cliente.getString("nome") : "N/A") +
                              " | Serviço: " + (servico != null ? servico.getString("descricao") : "N/A"));
        }
        System.out.println("------------------------------------------------------------------\n");
    }

    public static void relatorioAnimaisPorCliente(int idCliente, MongoDatabase db) {
        System.out.println("\n--- Relatório de Animais por Cliente ---");
        Document cliDoc = db.getCollection("cliente").find(Filters.eq("id", idCliente)).first();
        if(cliDoc == null) { System.out.println("Cliente não encontrado."); return; }
        
        System.out.println("Cliente: " + cliDoc.getString("nome"));
        System.out.println("----------------------------------------");
        
        boolean found = false;
        for(Document animal : db.getCollection("animal").find(Filters.eq("idCliente", idCliente))) {
            Document especie = db.getCollection("especie").find(Filters.eq("id", animal.getInteger("idEspecie"))).first();
            System.out.println("  Animal: " + animal.getString("nome") +
                             " | Espécie: " + (especie != null ? especie.getString("descricao") : "N/A") +
                             " | Data Nasc.: " + animal.getDate("dataNascimento"));
            found = true;
        }
        if (!found) System.out.println("Nenhum animal encontrado.");
        System.out.println("----------------------------------------\n");
    }
}