package org.openlca.jsonld.output;

import java.lang.reflect.Type;

import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

import org.openlca.core.model.Category;
import org.openlca.core.model.ModelType;
import org.openlca.jsonld.EntityStore;

class CategoryWriter implements Writer<Category> {

	private EntityStore store;

	public CategoryWriter() {
	}

	public CategoryWriter(EntityStore store) {
		this.store = store;
	}

	@Override
	public void write(Category category) {
		if (category == null || store == null)
			return;
		if (store.contains(ModelType.CATEGORY, category.getRefId()))
			return;
		JsonObject obj = serialize(category, null, null);
		store.add(ModelType.CATEGORY, category.getRefId(), obj);
	}

	@Override
	public JsonObject serialize(Category category, Type type,
			JsonSerializationContext context) {
		JsonObject json = store == null ? new JsonObject() : store.initJson();
		map(category, json);
		return json;
	}

	private void map(Category category, JsonObject json) {
		JsonWriter.addAttributes(category, json, store);
		ModelType modelType = category.getModelType();
		if (modelType != null)
			json.addProperty("modelType", modelType.name());
		JsonObject parentRef = Out.put(category.getParentCategory(), store);
		json.add("parentCategory", parentRef);
	}
}
