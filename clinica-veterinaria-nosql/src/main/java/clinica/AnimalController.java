package clinica;

import com.mongodb.client.MongoDatabase;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

class AnimalController {
    public void createAnimal(MongoDatabase db) {
        try {
            Scanner input = new Scanner(System.in);
            System.out.println("Insira os dados para um novo animal:");
            System.out.print("Nome: "); String nome = input.nextLine();
            System.out.print("Data de Nascimento (AAAA-MM-DD): "); 
            Date dataNascimento = Date.valueOf(input.nextLine());
            
            new ClienteController().listarClientes(db);
            System.out.print("ID do Cliente (Dono): "); int idCliente = Integer.parseInt(input.nextLine());
            
            new EspecieController().listarEspecies(db);
            System.out.print("ID da Espécie: "); int idEspecie = Integer.parseInt(input.nextLine());
            
            AnimalBean ab = new AnimalBean(nome, dataNascimento, idEspecie, idCliente);
            AnimalModel.create(ab, db);
            System.out.println("✅ Animal criado com sucesso!!");
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Erro: Data inválida. Use o formato AAAA-MM-DD.");
        } catch (Exception e) {
            System.out.println("❌ Erro ao criar animal: " + e.getMessage());
        }
    }

    public void listarAnimais(MongoDatabase db) {
        List<AnimalBean> animais = AnimalModel.listAll(db);
        System.out.println("\n--- Lista de Animais ---");
        if(animais.isEmpty()) System.out.println("Nenhum animal cadastrado.");
        for (AnimalBean ab : animais) System.out.println(ab);
        System.out.println("------------------------\n");
    }

    public void updateAnimal(MongoDatabase db) {
        try {
            Scanner input = new Scanner(System.in);
            listarAnimais(db);
            System.out.print("Informe o ID do animal que deseja alterar: ");
            int id = Integer.parseInt(input.nextLine());
            
            System.out.println("Insira os novos dados:");
            System.out.print("Nome: "); String nome = input.nextLine();
            System.out.print("Data de Nascimento (AAAA-MM-DD): "); 
            Date dataNascimento = Date.valueOf(input.nextLine());
            
            new ClienteController().listarClientes(db);
            System.out.print("ID do Cliente (Dono): "); int idCliente = Integer.parseInt(input.nextLine());
            
            new EspecieController().listarEspecies(db);
            System.out.print("ID da Espécie: "); int idEspecie = Integer.parseInt(input.nextLine());
            
            AnimalBean ab = new AnimalBean(id, nome, dataNascimento, idEspecie, idCliente);
            AnimalModel.update(ab, db);
            System.out.println("✅ Animal alterado com sucesso!!");
        } catch (Exception e) {
            System.out.println("❌ Erro ao atualizar animal: " + e.getMessage());
        }
    }

    public void deleteAnimal(MongoDatabase db) {
        try {
            Scanner input = new Scanner(System.in);
            listarAnimais(db);
            System.out.print("Informe o ID do animal a ser removido: ");
            int id = Integer.parseInt(input.nextLine());
            
            AnimalModel.remove(id, db);
            System.out.println("✅ Animal removido com sucesso!!");
        } catch (Exception e) {
            System.out.println("❌ ERRO AO EXCLUIR: " + e.getMessage());
        }
    }
}
