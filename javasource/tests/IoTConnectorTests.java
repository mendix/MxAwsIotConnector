package tests;

import ca.szc.configparser.Ini;
import com.mashape.unirest.http.exceptions.UnirestException;
import mxawsiot.impl.AwsException;
import mxawsiot.impl.AwsSecurityException;
import mxawsiot.impl.IoTConnector;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by ako on 4/1/2016.
 */
public class IoTConnectorTests {
    @Test
    public void listThingsTest() throws UnirestException, MalformedURLException {
        IoTConnector connector = new IoTConnector();
        connector.setLogger(new TestLogNode(IoTConnectorTests.class.getName()));
        connector.setCredentials(this.accessKeyId,this.secretAccessKey);
        connector.setRegion(region);
        String result = connector.listThingsRest("", "");
        System.err.print(result);
    }
    @Test
    public void listThingsSdkTest() throws UnirestException, MalformedURLException {
        IoTConnector connector = new IoTConnector();
        connector.setLogger(new TestLogNode(IoTConnectorTests.class.getName()));
        connector.setCredentials(this.accessKeyId,this.secretAccessKey);
        connector.setRegion(region);
        connector.listThingsSdk();
        //System.err.print(result);
    }
    @Test
    public void describeThingTest() throws UnirestException, MalformedURLException {
        IoTConnector connector = new IoTConnector();
        connector.setLogger(new TestLogNode(IoTConnectorTests.class.getName()));
        connector.setCredentials(this.accessKeyId,this.secretAccessKey);
        String thingName = "edison-mx-rnd-2";
        String result = connector.describeThing(thingName);
        System.err.print(result);
    }
    @Test
    public void describeEndpointTest() throws UnirestException, MalformedURLException {
        IoTConnector connector = new IoTConnector();
        connector.setLogger(new TestLogNode(IoTConnectorTests.class.getName()));
        connector.setCredentials(this.accessKeyId,this.secretAccessKey);
        String result = connector.describeEndpoint();
        System.err.print(result);
    }
    @Test
    public void getThingShadow() throws UnirestException, MalformedURLException {
        IoTConnector connector = new IoTConnector();
        connector.setLogger(new TestLogNode(IoTConnectorTests.class.getName()));
        connector.setCredentials(this.accessKeyId,this.secretAccessKey);
        connector.setRegion(region);
        String endpoint = connector.describeEndpoint();
        connector.setEndpoint(endpoint);
        String result = connector.getThingShadow("edison-mx-rnd-2");
        System.err.print(result);
    }

    @Test
    public void updateThingShadow() throws UnirestException, IOException, AwsSecurityException, AwsException {
        IoTConnector connector = new IoTConnector();
        connector.setLogger(new TestLogNode(IoTConnectorTests.class.getName()));
        connector.setCredentials(this.accessKeyId,this.secretAccessKey);
        connector.setRegion(region);
        String endpoint = connector.describeEndpoint();
        connector.setEndpoint(endpoint);
        String requestedStateJson = "{\"state\": {\"desired\": {\"temp\": 30}}}";
        String result = connector.updateThingShadow("edison-mx-rnd-2",requestedStateJson);
        System.err.print(result);
    }

    private static String accessKeyId = null;
    private static String secretAccessKey = null;
    private static String region = null;

    @BeforeClass
    public static void initConfiguration() {
        Path configurationFile = Paths.get(System.getProperty("user.home"), ".awsconnector.cfg");
        System.err.println("Reading aws credentials from : " + configurationFile.toString());
        try {
            Ini ini = new Ini().read(configurationFile);
            accessKeyId = ini.getValue("DynamoDB", "aws_access_key_id");
            secretAccessKey = ini.getValue("DynamoDB", "aws_secret_access_key");
            region = ini.getValue("DynamoDB", "region");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
