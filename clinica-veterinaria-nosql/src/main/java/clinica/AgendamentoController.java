package clinica;

import com.mongodb.client.MongoDatabase;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Scanner;

class AgendamentoController {
    public void createAgendamento(MongoDatabase db) {
        try {
            Scanner input = new Scanner(System.in);
            System.out.println("Insira os dados para um novo agendamento:");
            System.out.print("Data (AAAA-MM-DD): "); Date data = Date.valueOf(input.nextLine());
            System.out.print("Hora (HH:MM:SS): "); Time hora = Time.valueOf(input.nextLine());
            
            new ServicoController().listarServicos(db);
            System.out.print("ID do Serviço: "); int idServico = Integer.parseInt(input.nextLine());
            
            new AnimalController().listarAnimais(db);
            System.out.print("ID do Animal: "); int idAnimal = Integer.parseInt(input.nextLine());
            
            AgendamentoBean ab = new AgendamentoBean(data, hora, idServico, idAnimal);
            AgendamentoModel.create(ab, db);
            System.out.println("✅ Agendamento criado com sucesso!!");
        } catch (Exception e) {
            System.out.println("❌ Erro ao criar agendamento: " + e.getMessage());
        }
    }

    public void listarAgendamentos(MongoDatabase db) {
        List<AgendamentoBean> agendamentos = AgendamentoModel.listAll(db);
        System.out.println("\n--- Lista de Agendamentos ---");
        if(agendamentos.isEmpty()) System.out.println("Nenhum agendamento cadastrado.");
        for (AgendamentoBean ab : agendamentos) System.out.println(ab);
        System.out.println("---------------------------\n");
    }

    public void updateAgendamento(MongoDatabase db) {
        try {
            Scanner input = new Scanner(System.in);
            listarAgendamentos(db);
            System.out.print("Informe o ID do agendamento que deseja alterar: ");
            int id = Integer.parseInt(input.nextLine());
            
            System.out.println("Insira os novos dados:");
            System.out.print("Data (AAAA-MM-DD): "); Date data = Date.valueOf(input.nextLine());
            System.out.print("Hora (HH:MM:SS): "); Time hora = Time.valueOf(input.nextLine());
            
            new ServicoController().listarServicos(db);
            System.out.print("ID do Serviço: "); int idServico = Integer.parseInt(input.nextLine());
            
            new AnimalController().listarAnimais(db);
            System.out.print("ID do Animal: "); int idAnimal = Integer.parseInt(input.nextLine());
            
            AgendamentoBean ab = new AgendamentoBean(id, data, hora, idServico, idAnimal);
            AgendamentoModel.update(ab, db);
            System.out.println("✅ Agendamento alterado com sucesso!!");
        } catch (Exception e) {
            System.out.println("❌ Erro ao atualizar agendamento: " + e.getMessage());
        }
    }

    public void deleteAgendamento(MongoDatabase db) {
        try {
            Scanner input = new Scanner(System.in);
            listarAgendamentos(db);
            System.out.print("Informe o ID do agendamento a ser removido: ");
            int id = Integer.parseInt(input.nextLine());
            
            AgendamentoModel.remove(id, db);
            System.out.println("✅ Agendamento removido com sucesso!!");
        } catch (Exception e) {
            System.out.println("❌ ERRO AO EXCLUIR: " + e.getMessage());
        }
    }
}