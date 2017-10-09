package by.oxagile.guardian.helpers;


import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import javax.swing.text.Document;
import java.util.ArrayList;
import java.util.Arrays;

public class MongoHelper {

    private final MongoClient connection;
    String login = "guardian-stage";
    String pass = "guardian12345";
    String database = "guardian-assist";
    char[] password = pass.toCharArray();

    public MongoHelper() {

        MongoCredential creds = MongoCredential.createCredential(login, database, password);
        connection = new MongoClient(new ServerAddress("192.168.32.149", 27017), Arrays.asList(creds));
    }

    public String getLastCallID() {

        MongoDatabase db = connection.getDatabase("guardian-assist");
        MongoCollection<org.bson.Document> calls = db.getCollection("calls");
        MongoCursor<org.bson.Document> cursor = calls.find().iterator();

        /*JsonElement parsed = new JsonParser().parse(cursor.next().toString());
        String callID = parsed.getAsJsonObject().get("?").getAsString();*/

        ArrayList<String> callIDs = new ArrayList<>();
        try {
            while (cursor.hasNext()) {
                callIDs.add(cursor.next().getObjectId("_id").toString());
            }
        } finally {
            cursor.close();
        }
        int lastCallID = callIDs.size() - 1;
        return callIDs.get(lastCallID);
    }

}
