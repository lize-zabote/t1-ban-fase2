package clinica;

import com.mongodb.client.MongoDatabase;
import java.util.List;
import java.util.Scanner;

class FuncionarioController {
    public void createFuncionario(MongoDatabase db) {
        Scanner input = new Scanner(System.in);
        System.out.println("Insira os seguintes dados para um novo funcionário:");
        System.out.print("Nome: "); String nome = input.nextLine();
        System.out.print("Sobrenome: "); String sobrenome = input.nextLine();
        System.out.print("Rua: "); String rua = input.nextLine();
        System.out.print("CEP: "); String cep = input.nextLine();
        System.out.print("Bairro: "); String bairro = input.nextLine();
        System.out.print("Especialidade: "); String especialidade = input.nextLine();
        System.out.print("Cargo: "); String cargo = input.nextLine();
        
        FuncionarioBean fb = new FuncionarioBean(nome, sobrenome, rua, cep, bairro, especialidade, cargo);
        FuncionarioModel.create(fb, db);
        System.out.println("✅ Funcionário criado com sucesso!!");
    }

    public void listarFuncionarios(MongoDatabase db) {
        List<FuncionarioBean> funcionarios = FuncionarioModel.listAll(db);
        System.out.println("\n--- Lista de Funcionários ---");
        if(funcionarios.isEmpty()) System.out.println("Nenhum funcionário cadastrado.");
        for (FuncionarioBean fb : funcionarios) System.out.println(fb);
        System.out.println("---------------------------\n");
    }

    public void updateFuncionario(MongoDatabase db) {
        try {
            Scanner input = new Scanner(System.in);
            listarFuncionarios(db);
            System.out.print("Informe o ID do funcionário que deseja alterar: ");
            int id = Integer.parseInt(input.nextLine());
            
            System.out.println("Insira os novos dados:");
            System.out.print("Nome: "); String nome = input.nextLine();
            System.out.print("Sobrenome: "); String sobrenome = input.nextLine();
            System.out.print("Rua: "); String rua = input.nextLine();
            System.out.print("CEP: "); String cep = input.nextLine();
            System.out.print("Bairro: "); String bairro = input.nextLine();
            System.out.print("Especialidade: "); String especialidade = input.nextLine();
            System.out.print("Cargo: "); String cargo = input.nextLine();
            
            FuncionarioBean fb = new FuncionarioBean(id, nome, sobrenome, rua, cep, bairro, especialidade, cargo);
            FuncionarioModel.update(fb, db);
            System.out.println("✅ Funcionário alterado com sucesso!!");
        } catch (Exception e) {
            System.out.println("❌ Erro ao atualizar: " + e.getMessage());
        }
    }
    
    public void deleteFuncionario(MongoDatabase db) {
        try {
            Scanner input = new Scanner(System.in);
            listarFuncionarios(db);
            System.out.print("Informe o ID do funcionário a ser removido: ");
            int id = Integer.parseInt(input.nextLine());
            
            FuncionarioModel.remove(id, db);
            System.out.println("✅ Funcionário removido com sucesso!!");
        } catch (Exception e) {
            System.out.println("❌ ERRO AO EXCLUIR: " + e.getMessage());
        }
    }
}

