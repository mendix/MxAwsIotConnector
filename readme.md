# Mendix AWS IoT connector

This connector enables you to easily connect to IoT devices and data on [AWS IoT][3].

## Usage

The connector consists of types of microflow actions:
* Actions to determine the devices connected to AWS IoT.
* Actions to get and set the shadow of devices connected to AWS IoT through the REST Api.
* Actions to get and set the shadow of devices connected to AWS IoT through MQTT messages.

 ![Mendix AWS IoT toolbox actions][16]

### Configuration

The actions need AWS credentials. Most use an AWS access key id, and an AWS secret access key.

For the MQTT actions you need to provide Certificates as provided by AWS IoT. These should be stored
in the resources folder of your Mendix project.

 ![Certificates][5]

### Things

To get a list of all things registered at AWS IoT you can use *Things - get list*. This will return a
JSON document as provided by AWS IoT. You can use a JSON import mapping to create entities from the
JSON returned

 ![List things microflow][1]

 ![List things configuration][2]

To get the details of a device you can use the *Thing - describe* action.

 ![Thing - describe][4]

### State

AWS IoT has a shadow of the device state. It provides a REST Api to this shadow. Through this API you can d
determine the last known state of a device, or request a state change of this device.

To read the last known status of a device use the *Data - get shadow* action. This action needs the endpoint of the
REST API. You can copy, paste this from AWS IoT, or you can use the *Thing - get endpoint* action to determine the
endpoint.

 ![Thing - get shadow][6]

To change the status of a device you can update the shadow using the *Data - change shadow* action.

 ![Thing - update shadow][7]

### Messages

In addition to the REST API you can also work with the state of devices through MQTT messages.

To receive messages from devices you need to subscribe to the required topic using the *Data - subscribe to MQTT topic*.
Typically you'll want to do this when you Mendix app is started.

 ![MQTT subscribe unsubscribe][8]

To subscribe you need to provide certificate information as provided by AWS IoT.

 ![MQTT subscribe configuration][12]

When a message is received the microflow you specified will be called using two string parameters: topic and payload. The
payload contains a JSON document that you can import using JSON import mappings. The format of this message depends on
the data send by the device.

 ![MQTT on message microflow][9]

To publish a message on an MQTT topic you can use the *Data - publish MQTT message* action:

 ![MQTT publish mqtt message microflow][10]

Configuration of this action also needs the certificates provided by AWS IoT:

 ![MQTT publish mqtt message configuration][11]

## Development

The sourcecode of this connector is on Github: [Github AWS IoT connector][15].

All java jar dependencies are managed using an ivy file. You can download all dependencies by running runivy.cmd. This will save all jars in the userlib folder. There are two different scripts to run ivy:

 * runivy.cmd - downloads all dependencies required for running and testing the project
 * runivy-export.cmd - downloads only the dependencies required for distributing the connector mpk.

Before you start to develop the connector you need to run runivy.cmd. After you validate everything works, run runivy-export.cmd. This will delete all jars in the userlib folder and only download the jars required for creating the connector mpk.

## Attribution

This project uses source code from:
* [AWS IoT demo for Danbo][13] (Fabio Silva)
* [JavaQuery - AWS Version 4 Signing process][14] (Vicky Thakor)

## License

This connector is licensed under the Apache v2 license.

## Changelog

* 0.9 - 2016-07-13 - initial public release

* 0.9.1 - 2016-08-15 - Bug fixes
  * Client id should be unique per client
  * Fixed problem with subscribing to multiple topics, messages are now delivered to the correct microflow

* 0.9.2 - 2016-08-24 - Bug fixes
  * Fixed MQTT topic wildcard subscriptions

* 0.9.3 - 2016-08-25 - Bug fixes
  * Fixed MQTT topic with $ sign subscriptions

 [1]: docs/images/list-things-mf.png
 [2]: docs/images/list-things-conf.png
 [3]: https://aws.amazon.com/iot/
 [4]: docs/images/get-thing-mf.png
 [5]: docs/images/certificates-resources-folder.png
 [6]: docs/images/thing-get-shadow.png
 [7]: docs/images/thing-update-shadow.png
 [8]: docs/images/mqtt-subscribe-unsubscribe.png
 [9]: docs/images/mqtt-on-message-mf.png
 [10]: docs/images/publish-mqtt-message-mf.png
 [11]: docs/images/publish-mqtt-message-configuration.png
 [12]: docs/images/subscribe-mqtt-topic-configuration.png
 [13]: https://github.com/awslabs/aws-iot-demo-for-danbo
 [14]: http://www.javaquery.com/2016/01/aws-version-4-signing-process-complete.html
 [15]: https://github.com/mendix/MxAwsIotConnector
 [16]: docs/images/mx-aws-iot-toolbox.png
