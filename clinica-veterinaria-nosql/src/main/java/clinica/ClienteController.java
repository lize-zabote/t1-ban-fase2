package clinica;

import com.mongodb.client.MongoDatabase;
import java.sql.Date;
import java.sql.Time;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;


// --- CLIENTE CONTROLLER ---
class ClienteController {
    public void createCliente(MongoDatabase db) {
        Scanner input = new Scanner(System.in);
        System.out.println("Insira os dados para um novo cliente:");
        System.out.print("Nome: "); String nome = input.nextLine();
        System.out.print("Sobrenome: "); String sobrenome = input.nextLine();
        System.out.print("Telefone: "); String telefone = input.nextLine();
        System.out.print("CEP: "); String cep = input.nextLine();
        System.out.print("Bairro: "); String bairro = input.nextLine();
        System.out.print("Rua: "); String rua = input.nextLine();
        ClienteBean cb = new ClienteBean(nome, sobrenome, telefone, cep, bairro, rua);
        ClienteModel.create(cb, db);
        System.out.println("Cliente criado com sucesso!!");
    }

    public void listarClientes(MongoDatabase db) {
        List<ClienteBean> clientes = ClienteModel.listAll(db);
        System.out.println("\n--- Lista de Clientes ---");
        for (ClienteBean cb : clientes) System.out.println(cb);
        System.out.println("-------------------------\n");
    }

    public void updateCliente(MongoDatabase db) {
        Scanner input = new Scanner(System.in);
        listarClientes(db);
        System.out.print("Informe o ID do cliente que deseja alterar: ");
        int id = input.nextInt(); input.nextLine(); 
        System.out.println("Insira os novos dados:");
        System.out.print("Nome: "); String nome = input.nextLine();
        System.out.print("Sobrenome: "); String sobrenome = input.nextLine();
        System.out.print("Telefone: "); String telefone = input.nextLine();
        System.out.print("CEP: "); String cep = input.nextLine();
        System.out.print("Bairro: "); String bairro = input.nextLine();
        System.out.print("Rua: "); String rua = input.nextLine();
        ClienteBean cb = new ClienteBean(id, nome, sobrenome, telefone, cep, bairro, rua);
        ClienteModel.update(cb, db);
        System.out.println("Cliente alterado com sucesso!!");
    }

    public void deleteCliente(MongoDatabase db) {
        Scanner input = new Scanner(System.in);
        listarClientes(db);
        System.out.print("Informe o ID do cliente a ser removido: ");
        int id = input.nextInt();
        ClienteModel.remove(id, db);
        System.out.println("Cliente removido com sucesso!!");
    }
}