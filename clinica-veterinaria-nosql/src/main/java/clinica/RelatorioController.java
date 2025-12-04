package clinica;

import com.mongodb.client.MongoDatabase;
import java.sql.Date;
import java.util.Scanner;

class RelatorioController {
    public void gerarRelatorioAnimaisPorFuncionario(MongoDatabase db) {
        try {
            Scanner input = new Scanner(System.in);
            new FuncionarioController().listarFuncionarios(db);
            System.out.print("Digite o ID do funcionário para gerar o relatório: ");
            int idFuncionario = Integer.parseInt(input.nextLine());
            RelatorioModel.relatorioAnimaisPorFuncionario(idFuncionario, db);
        } catch (Exception e) {
            System.out.println("❌ Erro: Entrada inválida.");
        }
    }

    public void gerarRelatorioAgendamentosPorPeriodo(MongoDatabase db) {
        try {
            Scanner input = new Scanner(System.in);
            System.out.println("Digite o período para o relatório de agendamentos:");
            System.out.print("Data de Início (AAAA-MM-DD): "); Date dataInicio = Date.valueOf(input.nextLine());
            System.out.print("Data de Fim (AAAA-MM-DD): "); Date dataFim = Date.valueOf(input.nextLine());
            RelatorioModel.relatorioAgendamentosPorPeriodo(dataInicio, dataFim, db);
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Erro: Formato de data inválido. Use AAAA-MM-DD.");
        } catch (Exception e) {
            System.out.println("❌ Erro inesperado: " + e.getMessage());
        }
    }

    public void gerarRelatorioAnimaisPorCliente(MongoDatabase db) {
        try {
            Scanner input = new Scanner(System.in);
            new ClienteController().listarClientes(db);
            System.out.print("Digite o ID do cliente para gerar o relatório: ");
            int idCliente = Integer.parseInt(input.nextLine());
            RelatorioModel.relatorioAnimaisPorCliente(idCliente, db);
        } catch (Exception e) {
            System.out.println("❌ Erro: Entrada inválida.");
        }
    }
}