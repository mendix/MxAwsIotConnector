// This file was generated by Mendix Modeler.
//
// WARNING: Code you write here will be lost the next time you deploy the project.

package testmxawsiot.proxies;

public class Thing
{
	private final com.mendix.systemwideinterfaces.core.IMendixObject thingMendixObject;

	private final com.mendix.systemwideinterfaces.core.IContext context;

	/**
	 * Internal name of this entity
	 */
	public static final java.lang.String entityName = "TestMxAwsIoT.Thing";

	/**
	 * Enum describing members of this entity
	 */
	public enum MemberNames
	{
		Name("Name"),
		Location("Location"),
		HasTemperatureSensor("HasTemperatureSensor"),
		DefaultClientId("DefaultClientId");

		private java.lang.String metaName;

		MemberNames(java.lang.String s)
		{
			metaName = s;
		}

		@java.lang.Override
		public java.lang.String toString()
		{
			return metaName;
		}
	}

	public Thing(com.mendix.systemwideinterfaces.core.IContext context)
	{
		this(context, com.mendix.core.Core.instantiate(context, "TestMxAwsIoT.Thing"));
	}

	protected Thing(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixObject thingMendixObject)
	{
		if (thingMendixObject == null)
			throw new java.lang.IllegalArgumentException("The given object cannot be null.");
		if (!com.mendix.core.Core.isSubClassOf("TestMxAwsIoT.Thing", thingMendixObject.getType()))
			throw new java.lang.IllegalArgumentException("The given object is not a TestMxAwsIoT.Thing");

		this.thingMendixObject = thingMendixObject;
		this.context = context;
	}

	/**
	 * @deprecated Use 'Thing.load(IContext, IMendixIdentifier)' instead.
	 */
	@java.lang.Deprecated
	public static testmxawsiot.proxies.Thing initialize(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixIdentifier mendixIdentifier) throws com.mendix.core.CoreException
	{
		return testmxawsiot.proxies.Thing.load(context, mendixIdentifier);
	}

	/**
	 * Initialize a proxy using context (recommended). This context will be used for security checking when the get- and set-methods without context parameters are called.
	 * The get- and set-methods with context parameter should be used when for instance sudo access is necessary (IContext.createSudoClone() can be used to obtain sudo access).
	 */
	public static testmxawsiot.proxies.Thing initialize(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixObject mendixObject)
	{
		return new testmxawsiot.proxies.Thing(context, mendixObject);
	}

	public static testmxawsiot.proxies.Thing load(com.mendix.systemwideinterfaces.core.IContext context, com.mendix.systemwideinterfaces.core.IMendixIdentifier mendixIdentifier) throws com.mendix.core.CoreException
	{
		com.mendix.systemwideinterfaces.core.IMendixObject mendixObject = com.mendix.core.Core.retrieveId(context, mendixIdentifier);
		return testmxawsiot.proxies.Thing.initialize(context, mendixObject);
	}

	/**
	 * Commit the changes made on this proxy object.
	 */
	public final void commit() throws com.mendix.core.CoreException
	{
		com.mendix.core.Core.commit(context, getMendixObject());
	}

	/**
	 * Commit the changes made on this proxy object using the specified context.
	 */
	public final void commit(com.mendix.systemwideinterfaces.core.IContext context) throws com.mendix.core.CoreException
	{
		com.mendix.core.Core.commit(context, getMendixObject());
	}

	/**
	 * Delete the object.
	 */
	public final void delete()
	{
		com.mendix.core.Core.delete(context, getMendixObject());
	}

	/**
	 * Delete the object using the specified context.
	 */
	public final void delete(com.mendix.systemwideinterfaces.core.IContext context)
	{
		com.mendix.core.Core.delete(context, getMendixObject());
	}
	/**
	 * @return value of Name
	 */
	public final java.lang.String getName()
	{
		return getName(getContext());
	}

	/**
	 * @param context
	 * @return value of Name
	 */
	public final java.lang.String getName(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.Name.toString());
	}

	/**
	 * Set value of Name
	 * @param name
	 */
	public final void setName(java.lang.String name)
	{
		setName(getContext(), name);
	}

	/**
	 * Set value of Name
	 * @param context
	 * @param name
	 */
	public final void setName(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String name)
	{
		getMendixObject().setValue(context, MemberNames.Name.toString(), name);
	}

	/**
	 * @return value of Location
	 */
	public final java.lang.String getLocation()
	{
		return getLocation(getContext());
	}

	/**
	 * @param context
	 * @return value of Location
	 */
	public final java.lang.String getLocation(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.Location.toString());
	}

	/**
	 * Set value of Location
	 * @param location
	 */
	public final void setLocation(java.lang.String location)
	{
		setLocation(getContext(), location);
	}

	/**
	 * Set value of Location
	 * @param context
	 * @param location
	 */
	public final void setLocation(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String location)
	{
		getMendixObject().setValue(context, MemberNames.Location.toString(), location);
	}

	/**
	 * @return value of HasTemperatureSensor
	 */
	public final java.lang.Boolean getHasTemperatureSensor()
	{
		return getHasTemperatureSensor(getContext());
	}

	/**
	 * @param context
	 * @return value of HasTemperatureSensor
	 */
	public final java.lang.Boolean getHasTemperatureSensor(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.Boolean) getMendixObject().getValue(context, MemberNames.HasTemperatureSensor.toString());
	}

	/**
	 * Set value of HasTemperatureSensor
	 * @param hastemperaturesensor
	 */
	public final void setHasTemperatureSensor(java.lang.Boolean hastemperaturesensor)
	{
		setHasTemperatureSensor(getContext(), hastemperaturesensor);
	}

	/**
	 * Set value of HasTemperatureSensor
	 * @param context
	 * @param hastemperaturesensor
	 */
	public final void setHasTemperatureSensor(com.mendix.systemwideinterfaces.core.IContext context, java.lang.Boolean hastemperaturesensor)
	{
		getMendixObject().setValue(context, MemberNames.HasTemperatureSensor.toString(), hastemperaturesensor);
	}

	/**
	 * @return value of DefaultClientId
	 */
	public final java.lang.String getDefaultClientId()
	{
		return getDefaultClientId(getContext());
	}

	/**
	 * @param context
	 * @return value of DefaultClientId
	 */
	public final java.lang.String getDefaultClientId(com.mendix.systemwideinterfaces.core.IContext context)
	{
		return (java.lang.String) getMendixObject().getValue(context, MemberNames.DefaultClientId.toString());
	}

	/**
	 * Set value of DefaultClientId
	 * @param defaultclientid
	 */
	public final void setDefaultClientId(java.lang.String defaultclientid)
	{
		setDefaultClientId(getContext(), defaultclientid);
	}

	/**
	 * Set value of DefaultClientId
	 * @param context
	 * @param defaultclientid
	 */
	public final void setDefaultClientId(com.mendix.systemwideinterfaces.core.IContext context, java.lang.String defaultclientid)
	{
		getMendixObject().setValue(context, MemberNames.DefaultClientId.toString(), defaultclientid);
	}

	/**
	 * @return the IMendixObject instance of this proxy for use in the Core interface.
	 */
	public final com.mendix.systemwideinterfaces.core.IMendixObject getMendixObject()
	{
		return thingMendixObject;
	}

	/**
	 * @return the IContext instance of this proxy, or null if no IContext instance was specified at initialization.
	 */
	public final com.mendix.systemwideinterfaces.core.IContext getContext()
	{
		return context;
	}

	@java.lang.Override
	public boolean equals(Object obj)
	{
		if (obj == this)
			return true;

		if (obj != null && getClass().equals(obj.getClass()))
		{
			final testmxawsiot.proxies.Thing that = (testmxawsiot.proxies.Thing) obj;
			return getMendixObject().equals(that.getMendixObject());
		}
		return false;
	}

	@java.lang.Override
	public int hashCode()
	{
		return getMendixObject().hashCode();
	}

	/**
	 * @return String name of this class
	 */
	public static java.lang.String getType()
	{
		return "TestMxAwsIoT.Thing";
	}

	/**
	 * @return String GUID from this object, format: ID_0000000000
	 * @deprecated Use getMendixObject().getId().toLong() to get a unique identifier for this object.
	 */
	@java.lang.Deprecated
	public java.lang.String getGUID()
	{
		return "ID_" + getMendixObject().getId().toLong();
	}
}
