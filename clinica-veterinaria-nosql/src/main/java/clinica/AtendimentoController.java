package clinica;

import com.mongodb.client.MongoDatabase;
import java.sql.Date;
import java.sql.Time;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

// --- ATENDIMENTO CONTROLLER ---
class AtendimentoController {
    public void efetuarAtendimento(MongoDatabase db) {
        Scanner input = new Scanner(System.in);
        System.out.println("--- Efetuar Novo Atendimento ---");
        System.out.println("Agendamentos disponíveis:");
        new AgendamentoController().listarAgendamentos(db);
        System.out.print("Selecione o ID do Agendamento: ");
        int idAgendamento = input.nextInt();
        System.out.println("Funcionários disponíveis:");
        new FuncionarioController().listarFuncionarios(db);
        System.out.print("Selecione o ID do Funcionário: ");
        int idFuncionario = input.nextInt();
        AtendimentoBean ab = new AtendimentoBean(idAgendamento, idFuncionario);
        AtendimentoModel.create(ab, db);
        System.out.println("Atendimento registrado com sucesso!");
    }
    
    public void listarAtendimentos(MongoDatabase db) {
        List<AtendimentoBean> atendimentos = AtendimentoModel.listAll(db);
        System.out.println("\n--- Histórico de Atendimentos ---");
        for (AtendimentoBean ab : atendimentos) System.out.println(ab);
        System.out.println("---------------------------------\n");
    }
}