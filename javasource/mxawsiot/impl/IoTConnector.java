package mxawsiot.impl;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.iot.AWSIotClient;
import com.amazonaws.services.iot.model.ListThingsRequest;
import com.amazonaws.services.iot.model.ListThingsResult;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequestWithBody;
import com.mendix.logging.ILogNode;
import com.mendix.systemwideinterfaces.core.IMendixObject;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by ako on 4/20/2016.
 */
public class IoTConnector {
    private ILogNode logger = null;
    private List<IMendixObject> buckets;
    private String awsSecretAccessKey;
    private String awsAccessKeyId;
    private String region;
    private String endpoint; //endpoint for accessing thing specific info

    public IoTConnector() {
    }

    public void setCredentials(String awsAccessKeyId, String awsSecretAccessKey) {
        logger.info(String.format("setCredentials: %s, %s", awsAccessKeyId, awsSecretAccessKey));
        this.awsAccessKeyId = awsAccessKeyId;
        this.awsSecretAccessKey = awsSecretAccessKey;
    }

    public void setLogger(ILogNode logNode) {
        logger = logNode;
    }

    /**
     * List IoT things using AWS SDK
     */
    public void listThingsSdk() {
        logger.info("listThingsSdk");
        AWSCredentials credentials = null;
        logger.info("Connecting to aws using: " + awsAccessKeyId + ", " + awsSecretAccessKey);
        credentials = new BasicAWSCredentials(awsAccessKeyId, awsSecretAccessKey);
        ClientConfiguration config = new ClientConfiguration();
        AWSIotClient client = new AWSIotClient(credentials, config);
        client.setRegion(Region.getRegion(Regions.fromName(region)));
        ListThingsRequest request = new ListThingsRequest();

        ListThingsResult result = client.listThings(request);
    }

    /**
     * Get list of all things registered with AWS
     *
     * @return
     * @throws UnirestException
     * @throws MalformedURLException
     * @param attributeName
     * @param attributeValue
     */
    public String listThingsRest(String attributeName, String attributeValue) throws UnirestException, MalformedURLException {
        logger.info("listThingsRest");

        HttpClientBuilder clientBuilder = HttpClientBuilder.create();
        HttpClient httpClient = clientBuilder.build();
        Unirest.setHttpClient(httpClient);

        GetRequest request = Unirest.get("https://iot." + this.region + ".amazonaws.com/things");

        if(attributeName != null && !attributeName.equals("")){
            //Map<String, Object> pars = new TreeMap<String, Object>(String.CASE_INSENSITIVE_ORDER);
            Map<String, Object> pars = new HashMap<String, Object>();
            pars.put("attributeName",attributeName);
            pars.put("attributeValue",attributeValue);
            request.queryString(pars);
        }

        logger.info("Request created: " + request.toString());

        UnirestAwsV4Signer signer = new UnirestAwsV4Signer();
        request = signer.sign(request, awsAccessKeyId, awsSecretAccessKey, this.region, "execute-api");

        HttpResponse<JsonNode> jsonResponse = request.asJson();
        logger.info(String.format("Response: %d, %s", jsonResponse.getStatus(), jsonResponse.getStatusText()));
        return jsonResponse.getBody().toString();
    }

    /**
     * Get list of all things registered with AWS
     *
     * @return
     * @throws UnirestException
     * @throws MalformedURLException
     */
    public JsonNode listThingsRestJson() throws UnirestException, MalformedURLException {
        logger.info("listThingsRest");

        HttpClientBuilder clientBuilder = HttpClientBuilder.create();
        HttpClient httpClient = clientBuilder.build();
        Unirest.setHttpClient(httpClient);

        GetRequest request = Unirest.get("https://iot." + this.region + ".amazonaws.com/things");
        logger.info("Request created: " + request.toString());

        UnirestAwsV4Signer signer = new UnirestAwsV4Signer();
        request = signer.sign(request, awsAccessKeyId, awsSecretAccessKey, this.region, "execute-api");

        HttpResponse<JsonNode> jsonResponse = request.asJson();
        logger.info(String.format("Response: %d, %s", jsonResponse.getStatus(), jsonResponse.getStatusText()));
        return jsonResponse.getBody();
    }
    /**
     * Get description of a single IoT thing
     *
     * @param thingName
     * @return
     * @throws MalformedURLException
     * @throws UnirestException
     */
    public String describeThing(String thingName) throws MalformedURLException, UnirestException {
        logger.info("describeThingRest");

        HttpClientBuilder clientBuilder = HttpClientBuilder.create();
        HttpClient httpClient = clientBuilder.build();
        Unirest.setHttpClient(httpClient);

        GetRequest request = Unirest.get(String.format("https://iot." + this.region + ".amazonaws.com/things/%s", thingName));
        logger.info("Request created: " + request.toString());

        UnirestAwsV4Signer signer = new UnirestAwsV4Signer();
        request = signer.sign(request, awsAccessKeyId, awsSecretAccessKey, this.region, "execute-api");

        HttpResponse<JsonNode> jsonResponse = request.asJson();
        logger.info(String.format("Response: %d, %s", jsonResponse.getStatus(), jsonResponse.getStatusText()));
        return jsonResponse.getBody().toString();
    }

    /**
     * Get description of a single IoT thing
     *
     * @return
     * @throws MalformedURLException
     * @throws UnirestException
     */
    public String describeEndpoint() throws MalformedURLException, UnirestException {
        logger.info("describeEndpoint");

        HttpClientBuilder clientBuilder = HttpClientBuilder.create();
        HttpClient httpClient = clientBuilder.build();
        Unirest.setHttpClient(httpClient);

        GetRequest request = Unirest.get("https://iot." + this.region + ".amazonaws.com/endpoint");
        logger.info("Request created: " + request.toString());

        UnirestAwsV4Signer signer = new UnirestAwsV4Signer();
        request = signer.sign(request, awsAccessKeyId, awsSecretAccessKey, this.region, "execute-api");

        HttpResponse<JsonNode> jsonResponse = request.asJson();
        logger.info(String.format("Response: %d, %s", jsonResponse.getStatus(), jsonResponse.getStatusText()));
        logger.info("Response: " + jsonResponse.getBody().toString());
        String endpoint = jsonResponse.getBody().getObject().getString("endpointAddress");
        return endpoint;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * Returns the state of a thing as known by AWS IoT cloud
     *
     * @param thingName
     * @return
     */
    public String getThingShadow(String thingName) throws MalformedURLException, UnirestException {
        logger.info("getThingShadow");

        HttpClientBuilder clientBuilder = HttpClientBuilder.create();
        HttpClient httpClient = clientBuilder.build();
        Unirest.setHttpClient(httpClient);

        GetRequest request = Unirest.get(String.format("https://%s/things/%s/shadow", endpoint, thingName));
        logger.info("Request created: " + request.toString());

        UnirestAwsV4Signer signer = new UnirestAwsV4Signer();
        request = signer.sign(request, awsAccessKeyId, awsSecretAccessKey, this.region, "iotdata");

        HttpResponse<JsonNode> jsonResponse = request.asJson();
        logger.info(String.format("Response: %d, %s", jsonResponse.getStatus(), jsonResponse.getStatusText()));
        logger.info("Response: " + jsonResponse.getBody().toString());
        return jsonResponse.getBody().toString();
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    /**
     * Request of thing state change. This will update the thing shadow, and a message will
     * be sent to the thing with the desired state.
     *
     * More info:
     *   - http://docs.aws.amazon.com/iot/latest/developerguide/using-thing-shadows.html#update-thing-shadow
     *   - http://docs.aws.amazon.com/iot/latest/developerguide/thing-shadow-rest-api.html
     *
     * @param thingName
     * @param requestedStateJson
     * @return
     */
    public String updateThingShadow(String thingName, String requestedStateJson) throws IOException, UnirestException, AwsSecurityException, AwsException {
        logger.info("updateThingShadow: " + thingName + ", " + requestedStateJson);

        HttpClientBuilder clientBuilder = HttpClientBuilder.create();
        HttpClient httpClient = clientBuilder.build();
        Unirest.setHttpClient(httpClient);

        HttpRequestWithBody request = Unirest.post(String.format("https://%s/things/%s/shadow", endpoint, thingName));
        request.body(requestedStateJson);
        logger.info("Request created: " + request.toString());

        UnirestAwsV4Signer signer = new UnirestAwsV4Signer();
        request = signer.sign(request, awsAccessKeyId, awsSecretAccessKey, this.region, "iotdata");

        HttpResponse<JsonNode> jsonResponse = request.asJson();
        logger.info(String.format("Response: %d, %s", jsonResponse.getStatus(), jsonResponse.getStatusText()));
        logger.info("Response: " + jsonResponse.getBody().toString());
        if(jsonResponse.getStatus() == 403){
            // forbidden, incorrect authentication
            throw new AwsSecurityException(jsonResponse.getStatus(),jsonResponse.getStatusText());
        }
        if(jsonResponse.getStatus() == 400){
            // forbidden, incorrect authentication
            throw new AwsException(jsonResponse.getStatus(),jsonResponse.getStatusText());
        }
        return jsonResponse.getBody().toString();
    }
}
