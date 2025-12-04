package clinica;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

public class SequenceManager {
    
    // Simula o auto_increment do SQL
    public static int getNextId(MongoDatabase db, String sequenceName) {
        MongoCollection<Document> counters = db.getCollection("counters");
        
        // Procura o contador pelo nome e incrementa 'seq' em 1 atomicamente
        Document doc = counters.findOneAndUpdate(
            Filters.eq("_id", sequenceName),
            Updates.inc("seq", 1)
        );
        
        // Se não existir o contador, cria e retorna 1
        if (doc == null) {
            Document newCounter = new Document("_id", sequenceName).append("seq", 1);
            counters.insertOne(newCounter);
            return 1;
        }
        
        // Retorna o valor APÓS o incremento (que está no doc retornado se configurado, 
        // ou fazemos a lógica +1 se o returnOriginal for true). 
        // Por padrão findOneAndUpdate retorna o documento ANTES do update. Então somamos +1.
        return doc.getInteger("seq") + 1;
    }
}