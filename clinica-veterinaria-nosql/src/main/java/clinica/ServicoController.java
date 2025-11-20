package clinica;

import com.mongodb.client.MongoDatabase;
import java.sql.Date;
import java.sql.Time;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

// --- SERVICO CONTROLLER ---
class ServicoController {
    public void createServico(MongoDatabase db) {
        Scanner input = new Scanner(System.in);
        System.out.println("Insira os dados para um novo serviço:");
        System.out.print("Descrição: "); String descricao = input.nextLine();
        System.out.print("Valor: "); BigDecimal valor = input.nextBigDecimal();
        ServicoModel.create(new ServicoBean(valor, descricao), db);
        System.out.println("Serviço criado com sucesso!!");
    }

    public void listarServicos(MongoDatabase db) {
        List<ServicoBean> servicos = ServicoModel.listAll(db);
        System.out.println("\n--- Lista de Serviços ---");
        for (ServicoBean sb : servicos) System.out.println(sb);
        System.out.println("-------------------------\n");
    }

    public void updateServico(MongoDatabase db) {
        Scanner input = new Scanner(System.in);
        listarServicos(db);
        System.out.print("Informe o ID do serviço que deseja alterar: ");
        int id = input.nextInt(); input.nextLine();
        System.out.print("Nova Descrição: "); String descricao = input.nextLine();
        System.out.print("Novo Valor: "); BigDecimal valor = input.nextBigDecimal();
        ServicoModel.update(new ServicoBean(id, valor, descricao), db);
        System.out.println("Serviço alterado com sucesso!!");
    }

    public void deleteServico(MongoDatabase db) {
        Scanner input = new Scanner(System.in);
        listarServicos(db);
        System.out.print("Informe o ID do serviço a ser removido: ");
        int id = input.nextInt();
        ServicoModel.remove(id, db);
        System.out.println("Serviço removido com sucesso!!");
    }
}