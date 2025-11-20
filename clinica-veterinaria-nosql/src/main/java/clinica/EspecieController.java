package clinica;

import com.mongodb.client.MongoDatabase;
import java.sql.Date;
import java.sql.Time;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

// --- ESPECIE CONTROLLER ---
class EspecieController {
    public void createEspecie(MongoDatabase db) {
        Scanner input = new Scanner(System.in);
        System.out.print("Insira a descrição da nova espécie: ");
        String descricao = input.nextLine();
        EspecieModel.create(new EspecieBean(descricao), db);
        System.out.println("Espécie criada com sucesso!!");
    }

    public void listarEspecies(MongoDatabase db) {
        List<EspecieBean> especies = EspecieModel.listAll(db);
        System.out.println("\n--- Lista de Espécies ---");
        for (EspecieBean eb : especies) System.out.println(eb);
        System.out.println("-------------------------\n");
    }

    public void updateEspecie(MongoDatabase db) {
        Scanner input = new Scanner(System.in);
        listarEspecies(db);
        System.out.print("Informe o ID da espécie que deseja alterar: ");
        int id = input.nextInt(); input.nextLine();
        System.out.print("Insira a nova descrição: ");
        String descricao = input.nextLine();
        EspecieModel.update(new EspecieBean(id, descricao), db);
        System.out.println("Espécie alterada com sucesso!!");
    }

    public void deleteEspecie(MongoDatabase db) {
        Scanner input = new Scanner(System.in);
        listarEspecies(db);
        System.out.print("Informe o ID da espécie a ser removida: ");
        int id = input.nextInt();
        EspecieModel.remove(id, db);
        System.out.println("Espécie removida com sucesso!!");
    }
}