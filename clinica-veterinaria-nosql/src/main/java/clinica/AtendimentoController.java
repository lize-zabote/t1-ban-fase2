package clinica;

import com.mongodb.client.MongoDatabase;
import java.util.List;
import java.util.Scanner;

class AtendimentoController {
    public void efetuarAtendimento(MongoDatabase db) {
        try {
            Scanner input = new Scanner(System.in);
            System.out.println("--- Efetuar Novo Atendimento ---");
            System.out.println("Agendamentos disponíveis:");
            new AgendamentoController().listarAgendamentos(db);
            System.out.print("Selecione o ID do Agendamento: ");
            int idAgendamento = Integer.parseInt(input.nextLine());
            
            System.out.println("Funcionários disponíveis:");
            new FuncionarioController().listarFuncionarios(db);
            System.out.print("Selecione o ID do Funcionário: ");
            int idFuncionario = Integer.parseInt(input.nextLine());
            
            AtendimentoBean ab = new AtendimentoBean(idAgendamento, idFuncionario);
            AtendimentoModel.create(ab, db);
            System.out.println("✅ Atendimento registrado com sucesso!");
        } catch (Exception e) {
            System.out.println("❌ Erro ao efetuar atendimento: " + e.getMessage());
        }
    }
    
    public void listarAtendimentos(MongoDatabase db) {
        List<AtendimentoBean> atendimentos = AtendimentoModel.listAll(db);
        System.out.println("\n--- Histórico de Atendimentos ---");
        if(atendimentos.isEmpty()) System.out.println("Nenhum atendimento realizado.");
        for (AtendimentoBean ab : atendimentos) System.out.println(ab);
        System.out.println("---------------------------------\n");
    }
}