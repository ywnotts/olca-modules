package org.openlca.ilcd.tests.network;

import org.openlca.ilcd.contacts.Contact;
import org.openlca.ilcd.flowproperties.FlowProperty;
import org.openlca.ilcd.flows.Flow;
import org.openlca.ilcd.io.DataStoreException;
import org.openlca.ilcd.io.NetworkClient;
import org.openlca.ilcd.io.XmlBinder;
import org.openlca.ilcd.processes.Process;
import org.openlca.ilcd.sources.Source;
import org.openlca.ilcd.units.UnitGroup;
import org.openlca.ilcd.util.ContactBag;
import org.openlca.ilcd.util.FlowBag;
import org.openlca.ilcd.util.FlowPropertyBag;
import org.openlca.ilcd.util.ProcessBag;
import org.openlca.ilcd.util.SourceBag;
import org.openlca.ilcd.util.UnitGroupBag;

/** Helper class for uploading the sample data sets into the test instance. */
class DataSets {

	static void upload() throws Exception {
		if (!Network.isAppAlive())
			return;
		NetworkClient client = Network.createClient();
		putUnitGroup(client);
		putFlowProperty(client);
		putFlow(client);
		putProcess(client);
		putSource(client);
		putContact(client);
	}

	private static void putContact(NetworkClient client) throws Exception {
		Contact contact = load(Contact.class, "contact.xml");
		ContactBag bag = new ContactBag(contact);
		if (!client.contains(Contact.class, bag.getId()))
			client.put(contact, bag.getId());
	}

	private static void putFlowProperty(NetworkClient client) throws Exception {
		FlowProperty flowProperty = load(FlowProperty.class, "flowproperty.xml");
		FlowPropertyBag bag = new FlowPropertyBag(flowProperty);
		if (!client.contains(FlowProperty.class, bag.getId()))
			client.put(flowProperty, bag.getId());
	}

	private static void putFlow(NetworkClient client) throws Exception {
		Flow flow = load(Flow.class, "flow.xml");
		FlowBag bag = new FlowBag(flow);
		if (!client.contains(Flow.class, bag.getId()))
			client.put(flow, bag.getId());
	}

	private static void putProcess(NetworkClient client) throws Exception {
		Process process = load(Process.class, "process.xml");
		ProcessBag bag = new ProcessBag(process);
		if (!client.contains(Process.class, bag.getId()))
			client.put(process, bag.getId());
	}

	private static void putSource(NetworkClient client) throws Exception {
		Source source = load(Source.class, "source.xml");
		SourceBag bag = new SourceBag(source);
		if (!client.contains(Source.class, bag.getId()))
			client.put(source, bag.getId());
	}

	private static void putUnitGroup(NetworkClient client) throws Exception,
			DataStoreException {
		UnitGroup group = load(UnitGroup.class, "unit.xml");
		UnitGroupBag bag = new UnitGroupBag(group);
		if (!client.contains(UnitGroup.class, bag.getId()))
			client.put(group, bag.getId());
	}

	private static <T> T load(Class<T> clazz, String file) throws Exception {
		XmlBinder binder = new XmlBinder();
		return binder.fromStream(clazz,
				DataSets.class.getResourceAsStream(file));
	}

}
