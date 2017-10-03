package by.oxagile.guardian.helpers;


import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.util.Arrays;

public class MongoHelper {

    private final MongoClient connection;
    String login = "guardian";
    String pass = "guardian";
    String database = "guardian-assist";
    char[] password = pass.toCharArray();

    public MongoHelper() {

        MongoCredential creds = MongoCredential.createCredential(login, database, password);
        connection = new MongoClient(new ServerAddress("192.168.32.148", 27017), Arrays.asList(creds));
    }

    public void printCalls() {

        MongoDatabase db = connection.getDatabase("guardian-assist");
        MongoCollection<org.bson.Document> calls = db.getCollection("calls");
        MongoCursor<org.bson.Document> cursor = calls.find().iterator();

        try {
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        } finally {
            cursor.close();
        }
    }

}
