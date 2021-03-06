package org.openlca.jsonld.output;

import java.lang.reflect.Type;

import org.openlca.core.model.FlowPropertyFactor;
import org.openlca.jsonld.EntityStore;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

class FlowPropertyFactorWriter implements JsonSerializer<FlowPropertyFactor> {

	private EntityStore store;

	public FlowPropertyFactorWriter() {
	}

	public FlowPropertyFactorWriter(EntityStore store) {
		this.store = store;
	}

	@Override
	public JsonElement serialize(FlowPropertyFactor factor, Type type,
			JsonSerializationContext context) {
		JsonObject obj = store == null ? new JsonObject() : store.initJson();
		map(factor, obj);
		return obj;
	}

	void map(FlowPropertyFactor factor, JsonObject obj) {
		if (factor == null || obj == null)
			return;
		obj.addProperty("@type", "FlowPropertyFactor");
		if (factor.getId() != 0)
			obj.addProperty("@id", factor.getId());
		obj.add("flowProperty", Out.put(factor.getFlowProperty(), store));
		obj.addProperty("value", factor.getConversionFactor());
	}
}
