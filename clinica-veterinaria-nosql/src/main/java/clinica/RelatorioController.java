package clinica;

import com.mongodb.client.MongoDatabase;
import java.sql.Date;
import java.sql.Time;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

// --- RELATORIO CONTROLLER ---
class RelatorioController {
    public void gerarRelatorioAnimaisPorFuncionario(MongoDatabase db) {
        Scanner input = new Scanner(System.in);
        new FuncionarioController().listarFuncionarios(db);
        System.out.print("Digite o ID do funcionário para gerar o relatório: ");
        int idFuncionario = input.nextInt();
        RelatorioModel.relatorioAnimaisPorFuncionario(idFuncionario, db);
    }

    public void gerarRelatorioAgendamentosPorPeriodo(MongoDatabase db) {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o período para o relatório de agendamentos:");
        System.out.print("Data de Início (AAAA-MM-DD): "); Date dataInicio = Date.valueOf(input.nextLine());
        System.out.print("Data de Fim (AAAA-MM-DD): "); Date dataFim = Date.valueOf(input.nextLine());
        RelatorioModel.relatorioAgendamentosPorPeriodo(dataInicio, dataFim, db);
    }

    public void gerarRelatorioAnimaisPorCliente(MongoDatabase db) {
        Scanner input = new Scanner(System.in);
        new ClienteController().listarClientes(db);
        System.out.print("Digite o ID do cliente para gerar o relatório: ");
        int idCliente = input.nextInt();
        RelatorioModel.relatorioAnimaisPorCliente(idCliente, db);
    }
}