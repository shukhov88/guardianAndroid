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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;

public class MongoHelper {

    private final MongoClient connection;
    String login = "guardian-stage";
    String pass = "guardian12345";
    String database = "guardian-assist";
    char[] password = pass.toCharArray();

    Logger logger = LoggerFactory.getLogger(MongoHelper.class);

    public MongoHelper() {

        MongoCredential creds = MongoCredential.createCredential(login, database, password);
        connection = new MongoClient(new ServerAddress("192.168.32.149", 27017), Arrays.asList(creds));
    }

    public String getCallStatus(String callId) {
        logger.info("Fetching status of call: " + callId);
        MongoDatabase db = connection.getDatabase("guardian-assist");
        MongoCollection<org.bson.Document> calls = db.getCollection("calls");
        BasicDBObject query = new BasicDBObject("_id",new ObjectId(callId));
        FindIterable<Document> call = calls.find(query);
        String status = call.iterator().next().get("status").toString();
        logger.info("Status of call " + callId + " fetched: " + status);
        return status;
    }


    public void waitForCallInitiated(String callId) {
        logger.info("Waiting for call status to become INITIATED");
        while (getCallStatus(callId).equals("REQUESTED")) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        logger.info("Call status has became INITIATED");
    }

    public String getLastCallID() {
        logger.info("Fetching call ID...");
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
        logger.info("Call ID fetched: " + callId);
        waitForCallInitiated(callId);
        return callId;

    }

    public String getLastCallStatus() {
        logger.info("Fetching call status...");
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
        String status = parsed.getAsJsonObject().get("status").getAsString();
        logger.info("Call status fetched: " + status);
        return status;
    }
}
