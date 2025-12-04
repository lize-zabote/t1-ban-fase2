package clinica;

import com.mongodb.client.MongoDatabase;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

class ServicoController {
    public void createServico(MongoDatabase db) {
        try {
            Scanner input = new Scanner(System.in);
            System.out.println("Insira os dados para um novo serviço:");
            System.out.print("Descrição: "); String descricao = input.nextLine();
            System.out.print("Valor (Ex: 50.00): "); 
            BigDecimal valor = new BigDecimal(input.nextLine().replace(",", "."));
            
            ServicoModel.create(new ServicoBean(valor, descricao), db);
            System.out.println("✅ Serviço criado com sucesso!!");
        } catch (Exception e) {
            System.out.println("❌ Erro ao criar serviço (verifique o valor numérico): " + e.getMessage());
        }
    }

    public void listarServicos(MongoDatabase db) {
        List<ServicoBean> servicos = ServicoModel.listAll(db);
        System.out.println("\n--- Lista de Serviços ---");
        if(servicos.isEmpty()) System.out.println("Nenhum serviço cadastrado.");
        for (ServicoBean sb : servicos) System.out.println(sb);
        System.out.println("-------------------------\n");
    }

    public void updateServico(MongoDatabase db) {
        try {
            Scanner input = new Scanner(System.in);
            listarServicos(db);
            System.out.print("Informe o ID do serviço que deseja alterar: ");
            int id = Integer.parseInt(input.nextLine());
            
            System.out.print("Nova Descrição: "); String descricao = input.nextLine();
            System.out.print("Novo Valor: "); 
            BigDecimal valor = new BigDecimal(input.nextLine().replace(",", "."));
            
            ServicoModel.update(new ServicoBean(id, valor, descricao), db);
            System.out.println("✅ Serviço alterado com sucesso!!");
        } catch (Exception e) {
            System.out.println("❌ Erro ao atualizar serviço: " + e.getMessage());
        }
    }

    public void deleteServico(MongoDatabase db) {
        try {
            Scanner input = new Scanner(System.in);
            listarServicos(db);
            System.out.print("Informe o ID do serviço a ser removido: ");
            int id = Integer.parseInt(input.nextLine());
            
            ServicoModel.remove(id, db);
            System.out.println("✅ Serviço removido com sucesso!!");
        } catch (Exception e) {
            System.out.println("❌ ERRO AO EXCLUIR: " + e.getMessage());
        }
    }
}
