package ar.com.sondeos.batch.integration.processor.batch;

import ar.com.sondeos.batch.integration.processor.domain.AgencyContact;
import com.google.gson.JsonObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WriterAgencyContact implements ItemWriter<AgencyContact> {

    @Value("${services.api.url}")
    String host;

    @Override
    public void write(List<? extends AgencyContact> agencyContacts) throws Exception {

        //Creating a HttpClient object
        CloseableHttpClient httpclient = HttpClients.createDefault();

        //Creating a HttpPost object
        HttpPost httpPost = new HttpPost(host);

        //Creating a HttpPost object
        for (AgencyContact agencyContact : agencyContacts) {
            int dni = agencyContact.getDni();
            int topicId = agencyContact.getTopicId();
            String dniToString = Integer.toString(dni);
            String topicIdToString = Integer.toString(topicId);
            // Create new JSON Object
            JsonObject agencyContactBody = new JsonObject();
            agencyContactBody.addProperty("dni", dniToString);
            agencyContactBody.addProperty("topic_id", topicIdToString);

            System.out.println(agencyContactBody.toString());

            String json = agencyContactBody.toString();
            StringEntity entity = new StringEntity(json);
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            //Executing the Post request
            HttpResponse response = httpclient.execute(httpPost);
            System.out.println("Response Status: " + response.getStatusLine().getStatusCode());
        }

        //Printing the method used
        System.out.println("Request Type: " + httpPost.getMethod());


        httpclient.close();

    }
}
