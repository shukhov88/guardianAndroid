package by.oxagile.guardian.helpers;


import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

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

    public String getCallStatus(String callId) {
        MongoDatabase db = connection.getDatabase("guardian-assist");
        MongoCollection<org.bson.Document> calls = db.getCollection("calls");
        BasicDBObject query = new BasicDBObject("_id",new ObjectId(callId));
        FindIterable<Document> call = calls.find(query);
        return call.iterator().next().get("status").toString();

    }

    public void waitForCallInitiated(String callId) {
        while (getCallStatus(callId).equals("REQUESTED")) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public String getLastCallID() {

        MongoDatabase db = connection.getDatabase("guardian-assist");
        MongoCollection<org.bson.Document> calls = db.getCollection("calls");
        MongoCursor<org.bson.Document> cursor = calls.find().iterator();

        ArrayList<String> callIDs = new ArrayList<>();
        try {
            while (cursor.hasNext()) {
                callIDs.add(cursor.next().getObjectId("_id").toString());
            }
        } finally {
            cursor.close();
        }
        int lastCallID = callIDs.size() - 1;
        String callId = callIDs.get(lastCallID);
        waitForCallInitiated(callId);
        return callId;

    }

    public String getLastCallStatus() {
        MongoDatabase db = connection.getDatabase("guardian-assist");
        MongoCollection<org.bson.Document> calls = db.getCollection("calls");
        MongoCursor<org.bson.Document> cursor = calls.find().iterator();

        ArrayList<org.bson.Document> callObjects = new ArrayList<>();
        try {
            while (cursor.hasNext()) {
                callObjects.add(cursor.next());
            }
        } finally {
            cursor.close();
        }
        int lastObject = callObjects.size() - 1;

        JsonElement parsed = new JsonParser().parse(callObjects.get(lastObject).toJson());
        return parsed.getAsJsonObject().get("status").getAsString();
    }
}
