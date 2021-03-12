package mxawsiot.impl;

import com.google.common.collect.ImmutableMap;
import com.mendix.core.Core;
import com.mendix.logging.ILogNode;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.ISession;
import org.eclipse.paho.client.mqttv3.*;

import java.util.HashMap;
import java.util.Iterator;

import static org.bouncycastle.crypto.tls.ConnectionEnd.client;

/**
 * Created by ako on 12-8-2016.
 */
public class MxMqttCallback implements MqttCallback {
    private ILogNode logger = null;
    private MqttClient client = null;
    private HashMap<String, MqttSubscription> subscriptions = null;
    private MqttConnectOptions options = null;
    private int RETRIES = 10;

    public MxMqttCallback(ILogNode logger, MqttClient client, HashMap<String, MqttSubscription> subscriptions, MqttConnectOptions options) {
        this.logger = logger;
        this.client = client;
        this.subscriptions = subscriptions;
        this.options = options;
    }

    @Override
    public void connectionLost(Throwable throwable) {
        logger.info(String.format("connectionLost: %s, %s", throwable.getMessage(), client.getClientId()));
        logger.warn(throwable);
        int retries = RETRIES;
        while(retries > 0) {
            try {
                logger.info("retrying connection...");
                IMqttToken token = client.connectWithResult(options);
                token.waitForCompletion();
                if (client.isConnected()){
                    logger.info("connection re-established");
                    break;
                }else {
                    retries--;
                    waitUntilNextTry();
                }

            } catch (MqttException e) {
                logger.error("retry failed. waiting before retry...");
                retries--;
                waitUntilNextTry();
            }
        }
        if (!client.isConnected())
            logger.error("connection retry failed. quitting...");

    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        try {
            logger.info(String.format("messageArrived: %s, %s, %s", topic, new String(mqttMessage.getPayload()), client.getClientId()));
            IContext ctx = Core.createSystemContext();
            ISession session = ctx.getSession();
            MqttSubscription subscription = getSubscriptionForTopic(topic);
            if(subscription != null) {
                String microflow = subscription.getOnMessageMicroflow();
                logger.info(String.format("Calling onMessage microflow: %s, %s", microflow, client.getClientId()));
                //Core.executeAsync(ctx, microflow, true, ImmutableMap.of("Topic", s, "Payload", new String(mqttMessage.getPayload())));
                final ImmutableMap map = ImmutableMap.of("Topic", topic, "Payload", new String(mqttMessage.getPayload()));
                logger.info("Parameter map: " + map);
                Core.execute(ctx, microflow, true, map);
            } else {
                logger.error(String.format("Cannot find microflow for message received on topic %s",topic));
            }
        } catch (Exception e) {
            logger.error(e);
        }
    }
    /**
     * find possibly wildcarded subscription for specific topic
     *
     */
    private MqttSubscription getSubscriptionForTopic(String topic) {

        logger.info("getSubscriptionForTopic: " + topic);
        Iterator<String> subscriptionTopics = subscriptions.keySet().iterator();
        while(subscriptionTopics.hasNext()){
            String topicWithWildcards = subscriptionTopics.next();
            String topicWithWildcardsRe = topicWithWildcards.replaceAll("\\+","[^/]+").replaceAll("/#","\\(|/.*\\)");
            topicWithWildcardsRe = topicWithWildcardsRe.replace("$","\\$");
            logger.info(String.format("Comparing topic %s with subscription %s as regex %s",topic,topicWithWildcards,topicWithWildcardsRe));
            if(topic.matches(topicWithWildcardsRe)){
                logger.info("Found subscription " + topicWithWildcards);
                return subscriptions.get(topicWithWildcards);
            }
        }
        logger.info("No subscription found for topic " + topic);
        return null;
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        logger.info(String.format("deliveryComplete: %s", client.getClientId()));
    }

    private void waitUntilNextTry()
    {
        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException iex) { }
    }
}
