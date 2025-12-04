package clinica;

import com.mongodb.client.MongoDatabase;
import java.util.List;
import java.util.Scanner;

class EspecieController {
    public void createEspecie(MongoDatabase db) {
        Scanner input = new Scanner(System.in);
        System.out.print("Insira a descrição da nova espécie: ");
        String descricao = input.nextLine();
        EspecieModel.create(new EspecieBean(descricao), db);
        System.out.println("✅ Espécie criada com sucesso!!");
    }

    public void listarEspecies(MongoDatabase db) {
        List<EspecieBean> especies = EspecieModel.listAll(db);
        System.out.println("\n--- Lista de Espécies ---");
        if(especies.isEmpty()) System.out.println("Nenhuma espécie cadastrada.");
        for (EspecieBean eb : especies) System.out.println(eb);
        System.out.println("-------------------------\n");
    }

    public void updateEspecie(MongoDatabase db) {
        try {
            Scanner input = new Scanner(System.in);
            listarEspecies(db);
            System.out.print("Informe o ID da espécie que deseja alterar: ");
            int id = Integer.parseInt(input.nextLine());
            
            System.out.print("Insira a nova descrição: ");
            String descricao = input.nextLine();
            EspecieModel.update(new EspecieBean(id, descricao), db);
            System.out.println("✅ Espécie alterada com sucesso!!");
        } catch (Exception e) {
            System.out.println("❌ Erro ao atualizar espécie: " + e.getMessage());
        }
    }

    public void deleteEspecie(MongoDatabase db) {
        try {
            Scanner input = new Scanner(System.in);
            listarEspecies(db);
            System.out.print("Informe o ID da espécie a ser removida: ");
            int id = Integer.parseInt(input.nextLine());
            
            EspecieModel.remove(id, db);
            System.out.println("✅ Espécie removida com sucesso!!");
        } catch (Exception e) {
            System.out.println("❌ ERRO AO EXCLUIR: " + e.getMessage());
        }
    }
}