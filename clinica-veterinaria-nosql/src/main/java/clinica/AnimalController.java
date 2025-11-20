package clinica;

import com.mongodb.client.MongoDatabase;
import java.sql.Date;
import java.sql.Time;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

// --- ANIMAL CONTROLLER ---
class AnimalController {
    public void createAnimal(MongoDatabase db) {
        Scanner input = new Scanner(System.in);
        System.out.println("Insira os dados para um novo animal:");
        System.out.print("Nome: "); String nome = input.nextLine();
        System.out.print("Data de Nascimento (AAAA-MM-DD): "); Date dataNascimento = Date.valueOf(input.nextLine());
        new ClienteController().listarClientes(db);
        System.out.print("ID do Cliente (Dono): "); int idCliente = input.nextInt();
        new EspecieController().listarEspecies(db);
        System.out.print("ID da Espécie: "); int idEspecie = input.nextInt();
        AnimalBean ab = new AnimalBean(nome, dataNascimento, idEspecie, idCliente);
        AnimalModel.create(ab, db);
        System.out.println("Animal criado com sucesso!!");
    }

    public void listarAnimais(MongoDatabase db) {
        List<AnimalBean> animais = AnimalModel.listAll(db);
        System.out.println("\n--- Lista de Animais ---");
        for (AnimalBean ab : animais) System.out.println(ab);
        System.out.println("------------------------\n");
    }

    public void updateAnimal(MongoDatabase db) {
        Scanner input = new Scanner(System.in);
        listarAnimais(db);
        System.out.print("Informe o ID do animal que deseja alterar: ");
        int id = input.nextInt(); input.nextLine();
        System.out.println("Insira os novos dados:");
        System.out.print("Nome: "); String nome = input.nextLine();
        System.out.print("Data de Nascimento (AAAA-MM-DD): "); Date dataNascimento = Date.valueOf(input.nextLine());
        new ClienteController().listarClientes(db);
        System.out.print("ID do Cliente (Dono): "); int idCliente = input.nextInt();
        new EspecieController().listarEspecies(db);
        System.out.print("ID da Espécie: "); int idEspecie = input.nextInt();
        AnimalBean ab = new AnimalBean(id, nome, dataNascimento, idEspecie, idCliente);
        AnimalModel.update(ab, db);
        System.out.println("Animal alterado com sucesso!!");
    }

    public void deleteAnimal(MongoDatabase db) {
        Scanner input = new Scanner(System.in);
        listarAnimais(db);
        System.out.print("Informe o ID do animal a ser removido: ");
        int id = input.nextInt();
        AnimalModel.remove(id, db);
        System.out.println("Animal removido com sucesso!!");
    }
}