package clinica;

import com.mongodb.client.MongoDatabase;
import java.util.Scanner;

public class Clinica {

    public static void main(String[] args) {
        Conexao c = new Conexao();
        MongoDatabase db = c.getDatabase();
        Scanner input = new Scanner(System.in);

        int op;
        do {
            op = menuPrincipal(input);
            try {
                switch (op) {
                    case 1:
                        menuGerenciamento(input, db);
                        break;
                    case 2:
                        menuProcessos(input, db);
                        break;
                    case 3:
                        menuRelatorios(input, db);
                        break;
                    case 0:
                        System.out.println("Saindo do sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (Exception ex) {
                System.err.println("ERRO INESPERADO: " + ex.getMessage());
                ex.printStackTrace();
            }
        } while (op != 0);

        c.closeConnection();
        input.close();
    }

    private static int menuPrincipal(Scanner input) {
        System.out.println("\n===== CLÍNICA VETERINÁRIA (NoSQL) =====");
        System.out.println("1 - Gerenciamento (CRUD)");
        System.out.println("2 - Processos de Negócio");
        System.out.println("3 - Relatórios");
        System.out.println("0 - Sair");
        System.out.print("Sua opção: ");
        return input.nextInt();
    }

    private static void menuGerenciamento(Scanner input, MongoDatabase db) {
        int op;
        do {
            System.out.println("\n--- Menu de Gerenciamento ---");
            System.out.println("1 - Clientes");
            System.out.println("2 - Animais");
            System.out.println("3 - Funcionários");
            System.out.println("4 - Serviços");
            System.out.println("5 - Espécies");
            System.out.println("6 - Agendamentos");
            System.out.println("0 - Voltar ao Menu Principal");
            System.out.print("Sua opção: ");
            op = input.nextInt();

            if (op != 0) {
                menuCRUD(op, input, db);
            }

        } while (op != 0);
    }
    
    private static void menuCRUD(int tabela, Scanner input, MongoDatabase db) {
        int op;
        do {
            System.out.println("\n--- Ações ---");
            System.out.println("1 - Criar");
            System.out.println("2 - Listar");
            System.out.println("3 - Alterar");
            System.out.println("4 - Deletar");
            System.out.println("0 - Voltar");
            System.out.print("Sua opção: ");
            op = input.nextInt();

            switch (tabela) {
                case 1: // Clientes
                    ClienteController cc = new ClienteController();
                    if(op == 1) cc.createCliente(db);
                    else if(op == 2) cc.listarClientes(db);
                    else if(op == 3) cc.updateCliente(db);
                    else if(op == 4) cc.deleteCliente(db);
                    break;
                case 2: // Animais
                    AnimalController ac = new AnimalController();
                    if(op == 1) ac.createAnimal(db);
                    else if(op == 2) ac.listarAnimais(db);
                    else if(op == 3) ac.updateAnimal(db);
                    else if(op == 4) ac.deleteAnimal(db);
                    break;
                case 3: // Funcionários
                    FuncionarioController fc = new FuncionarioController();
                    if(op == 1) fc.createFuncionario(db);
                    else if(op == 2) fc.listarFuncionarios(db);
                    else if(op == 3) fc.updateFuncionario(db);
                    else if(op == 4) fc.deleteFuncionario(db);
                    break;
                case 4: // Serviços
                    ServicoController sc = new ServicoController();
                    if(op == 1) sc.createServico(db);
                    else if(op == 2) sc.listarServicos(db);
                    else if(op == 3) sc.updateServico(db);
                    else if(op == 4) sc.deleteServico(db);
                    break;
                case 5: // Espécies
                    EspecieController ec = new EspecieController();
                    if(op == 1) ec.createEspecie(db);
                    else if(op == 2) ec.listarEspecies(db);
                    else if(op == 3) ec.updateEspecie(db);
                    else if(op == 4) ec.deleteEspecie(db);
                    break;
                case 6: // Agendamentos
                    AgendamentoController agc = new AgendamentoController();
                    if(op == 1) agc.createAgendamento(db);
                    else if(op == 2) agc.listarAgendamentos(db);
                    else if(op == 3) agc.updateAgendamento(db);
                    else if(op == 4) agc.deleteAgendamento(db);
                    break;
            }
        } while (op != 0);
    }
    
    private static void menuProcessos(Scanner input, MongoDatabase db) {
        int op;
        do {
            System.out.println("\n--- Processos de Negócio ---");
            System.out.println("1 - Efetuar Atendimento");
            System.out.println("2 - Listar Histórico de Atendimentos");
            System.out.println("0 - Voltar ao Menu Principal");
            System.out.print("Sua opção: ");
            op = input.nextInt();
            
            AtendimentoController atc = new AtendimentoController();
            if(op == 1) atc.efetuarAtendimento(db);
            else if(op == 2) atc.listarAtendimentos(db);

        } while (op != 0);
    }

    private static void menuRelatorios(Scanner input, MongoDatabase db) {
        int op;
        do {
            System.out.println("\n--- Menu de Relatórios ---");
            System.out.println("1 - Atendimentos por Funcionário");
            System.out.println("2 - Agendamentos por Período");
            System.out.println("3 - Animais por Cliente");
            System.out.println("0 - Voltar ao Menu Principal");
            System.out.print("Sua opção: ");
            op = input.nextInt();
            
            RelatorioController rc = new RelatorioController();
            if(op == 1) rc.gerarRelatorioAnimaisPorFuncionario(db);
            else if(op == 2) rc.gerarRelatorioAgendamentosPorPeriodo(db);
            else if(op == 3) rc.gerarRelatorioAnimaisPorCliente(db);
            
        } while (op != 0);
    }
}