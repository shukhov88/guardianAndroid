package by.oxagile.guardian.helpers;

import by.oxagile.guardian.managers.AssistManager;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import by.oxagile.guardian.models.RequestBody;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class HttpSession {
    private CloseableHttpClient httpclient;
    private AssistManager assist;

    Logger logger = LoggerFactory.getLogger(HttpSession.class);

    public HttpSession(AssistManager assist) {
        this.assist = assist;
        httpclient = HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy()).build();
    }

    private String getTextFrom(CloseableHttpResponse response) throws IOException {
        try {
            return EntityUtils.toString(response.getEntity());
        } finally {
            response.close();
        }
    }

    public String requestCall(String recipientID, String deviceToken, String platform) throws IOException {

        RequestBody body = new RequestBody();
        String jsonBody = body.setRecipientID(recipientID).setDeviceToken(deviceToken).setPlatform(platform).toJsonString();

        String json = Request.Post(assist.getProperty("environment.url") + "/v1/calls")
                .addHeader("Authorization", assist.getProperty("1111.Token"))
                .bodyString(jsonBody, ContentType.APPLICATION_JSON)
                .execute()
                .returnContent()
                .asString();

        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("token").getAsString();
    }

    public String joinCall(String callID) throws IOException {
        logger.debug("Fetching TOKEN for ASSIST to join call ID: " + callID);
        String json = Request.Get(assist.getProperty("environment.url") + "/v1/calls/" + callID + "/join")
                .addHeader("Authorization", assist.getProperty("Assist.Token"))
                .addHeader("Content-Type", "application/json")
                .execute()
                .returnContent()
                .asString();

        JsonElement parsed = new JsonParser().parse(json);
        String token = parsed.getAsJsonObject().get("token").getAsString();
        logger.debug("TOKEN for ASSIST fetched: " + token);
        return token;
    }

}
