package ar.com.sondeos.batch.integration.processor.batch;

import ar.com.sondeos.batch.integration.processor.domain.AgencyContact;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProcessorAgencyContact implements ItemProcessor<AgencyContact, AgencyContact> {

    @Value("${services.topics.url}")
    String host;

    private static final Logger log = LoggerFactory.getLogger(ProcessorAgencyContact.class);

    @Override
    public AgencyContact process(AgencyContact agencyContact) throws Exception {

        //Creating a HttpClient object
        CloseableHttpClient httpclient = HttpClients.createDefault();

        //Creating a HttpGet object
        String agencyName = agencyContact.getAgencyName();
        HttpGet httpget = new HttpGet(host + agencyName + "/topic_id");

        //Printing the method used
        System.out.println("Request Type: " + httpget.getMethod());

        //Executing the Get request
        HttpResponse httpresponse = httpclient.execute(httpget);

        //Now pull back the response object
        HttpEntity httpEntity = httpresponse.getEntity();
        String id = EntityUtils.toString(httpEntity);

        int topicId = Integer.parseInt(id);
        System.out.println("TopicId: " + topicId);

        int dni = agencyContact.getDni();

        AgencyContact transformedAgencyContact = new AgencyContact(dni, topicId);

        log.info("Converting (" + agencyContact + ") into (" + transformedAgencyContact + ")");

        return transformedAgencyContact;
    }
}
