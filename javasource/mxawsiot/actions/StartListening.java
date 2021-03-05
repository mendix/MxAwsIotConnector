// This file was generated by Mendix Modeler.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package mxawsiot.actions;

import com.mendix.core.Core;
import com.mendix.logging.ILogNode;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.webui.CustomJavaAction;
import mxawsiot.impl.MqttConnector;

public class StartListening extends CustomJavaAction<java.lang.Boolean>
{
	private java.lang.String BrokerHost;
	private java.lang.Long BrokerPort;
	private java.lang.String ClientId;

	public StartListening(IContext context, java.lang.String BrokerHost, java.lang.Long BrokerPort, java.lang.String ClientId)
	{
		super(context);
		this.BrokerHost = BrokerHost;
		this.BrokerPort = BrokerPort;
		this.ClientId = ClientId;
	}

	@java.lang.Override
	public java.lang.Boolean executeAction() throws Exception
	{
		// BEGIN USER CODE
        MqttConnector connector = new MqttConnector();
        ILogNode logger = Core.getLogger(SubscribeToTopic.class.getName());
        logger.info("executeAction: start listening: " + this.ClientId);
        connector.startListening(
                this.BrokerHost
                , this.BrokerPort
                , this.ClientId
        );
        return true;
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 */
	@java.lang.Override
	public java.lang.String toString()
	{
		return "StartListening";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}