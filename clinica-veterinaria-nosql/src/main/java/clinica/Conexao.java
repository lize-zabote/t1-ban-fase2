package clinica;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexao {
    private MongoClient client;
    private MongoDatabase database;
    
    public Conexao() {
        // Desativar logs excessivos do Mongo Driver no console
        Logger.getLogger("org.mongodb.driver").setLevel(Level.SEVERE);
        
        try {
            // Conecta ao MongoDB Local na porta padrão
            this.client = MongoClients.create("mongodb://localhost:27017");
            // Seleciona o banco de dados "clinica"
            this.database = client.getDatabase("clinica");
        } catch (Exception ex) {
            System.err.println("Erro ao conectar ao MongoDB: " + ex.getMessage());
            System.exit(1);
        }
    }
    
    // Retorna o Banco de Dados Mongo em vez de java.sql.Connection
    public MongoDatabase getDatabase() {
        return database;
    }
    
    public void closeConnection(){
        if (this.client != null) {
            this.client.close();
        }
    }
}