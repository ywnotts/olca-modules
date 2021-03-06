package org.openlca.io.xls.results;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.openlca.core.database.EntityCache;
import org.openlca.core.matrix.FlowIndex;
import org.openlca.core.matrix.LongIndex;
import org.openlca.core.model.ProjectVariant;
import org.openlca.core.model.descriptors.FlowDescriptor;
import org.openlca.core.model.descriptors.ImpactCategoryDescriptor;
import org.openlca.io.CategoryPair;
import org.openlca.util.Strings;

class Utils {

	static List<FlowDescriptor> getSortedFlows(FlowIndex flowIndex,
			EntityCache cache) {
		List<FlowDescriptor> flows = new ArrayList<>();
		for (long flowId : flowIndex.getFlowIds()) {
			FlowDescriptor flow = cache.get(FlowDescriptor.class, flowId);
			if (flow != null)
				flows.add(flow);
		}
		return sortFlows(flows, cache);
	}

	static List<ImpactCategoryDescriptor> getSortedImpacts(
			LongIndex impactIndex, EntityCache cache) {
		List<ImpactCategoryDescriptor> impacts = new ArrayList<>();
		for (long id : impactIndex.getKeys()) {
			ImpactCategoryDescriptor impact = cache.get(
					ImpactCategoryDescriptor.class, id);
			impacts.add(impact);
		}
		return sortImpacts(impacts);
	}

	static List<ImpactCategoryDescriptor> sortImpacts(
			Collection<ImpactCategoryDescriptor> impacts) {
		List<ImpactCategoryDescriptor> list = new ArrayList<>(impacts);
		Collections.sort(list, new Comparator<ImpactCategoryDescriptor>() {
			@Override
			public int compare(ImpactCategoryDescriptor o1,
					ImpactCategoryDescriptor o2) {
				return Strings.compare(o1.getName(), o2.getName());
			}
		});
		return list;
	}

	static List<ProjectVariant> sortVariants(Collection<ProjectVariant> variants) {
		List<ProjectVariant> list = new ArrayList<>(variants);
		Collections.sort(list, new Comparator<ProjectVariant>() {
			@Override
			public int compare(ProjectVariant o1, ProjectVariant o2) {
				return Strings.compare(o1.getName(), o2.getName());
			}
		});
		return list;
	}

	static List<FlowDescriptor> sortFlows(Collection<FlowDescriptor> flows,
			EntityCache cache) {
		if (flows == null)
			return Collections.emptyList();
		ArrayList<FlowDescriptor> sorted = new ArrayList<>(flows);
		Collections.sort(sorted, new FlowSorter(cache));
		return sorted;
	}

	private static class FlowSorter implements Comparator<FlowDescriptor> {

		private HashMap<Long, CategoryPair> flowCategories = new HashMap<>();
		private EntityCache cache;

		private FlowSorter(EntityCache cache) {
			this.cache = cache;
		}

		@Override
		public int compare(FlowDescriptor o1, FlowDescriptor o2) {
			CategoryPair cat1 = flowCategory(o1);
			CategoryPair cat2 = flowCategory(o2);
			int c = cat1.compareTo(cat2);
			if (c != 0)
				return c;
			return Strings.compare(o1.getName(), o2.getName());
		}

		private CategoryPair flowCategory(FlowDescriptor flow) {
			CategoryPair pair = flowCategories.get(flow.getId());
			if (pair != null)
				return pair;
			pair = CategoryPair.create(flow, cache);
			flowCategories.put(flow.getId(), pair);
			return pair;
		}
	}

}
