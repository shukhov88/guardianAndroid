package by.oxagile.guardian.models;


import com.google.gson.Gson;

public class RequestBody {

    public String recipient_id;
    public String instance_id;
    public String platform;

    public RequestBody() {
    }

    public RequestBody setRecipientID(String recipient_id) {
        this.recipient_id = recipient_id;
        return this;
    }

    public RequestBody setDeviceToken(String instance_id) {
        this.instance_id = instance_id;
        return this;
    }

    public RequestBody setPlatform(String platform) {
        this.platform = platform;
        return this;
    }

    public String toJsonString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}


