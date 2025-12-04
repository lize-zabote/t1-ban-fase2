package clinica;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

public class SequenceManager {
    
    public static int getNextId(MongoDatabase db, String sequenceName) {
        MongoCollection<Document> counters = db.getCollection("counters");
        
        Document doc = counters.findOneAndUpdate(
            Filters.eq("_id", sequenceName),
            Updates.inc("seq", 1)
        );
        
        if (doc == null) {
            Document newCounter = new Document("_id", sequenceName).append("seq", 1);
            counters.insertOne(newCounter);
            return 1;
        }
        
        return doc.getInteger("seq") + 1;
    }
}