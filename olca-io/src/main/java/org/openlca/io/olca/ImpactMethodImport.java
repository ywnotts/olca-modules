package org.openlca.io.olca;

import org.openlca.core.database.IDatabase;
import org.openlca.core.database.ImpactMethodDao;
import org.openlca.core.model.Flow;
import org.openlca.core.model.ImpactCategory;
import org.openlca.core.model.ImpactFactor;
import org.openlca.core.model.ImpactMethod;
import org.openlca.core.model.NwSet;
import org.openlca.core.model.descriptors.ImpactMethodDescriptor;
import org.openlca.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ImpactMethodImport {

	private Logger log = LoggerFactory.getLogger(getClass());

	private ImpactMethodDao srcDao;
	private ImpactMethodDao destDao;
	private RefSwitcher refs;
	private Sequence seq;

	ImpactMethodImport(IDatabase source, IDatabase dest, Sequence seq) {
		this.srcDao = new ImpactMethodDao(source);
		this.destDao = new ImpactMethodDao(dest);
		this.refs = new RefSwitcher(source, dest, seq);
		this.seq = seq;
	}

	public void run() {
		log.trace("import LCIA methods");
		try {
			for (ImpactMethodDescriptor descriptor : srcDao.getDescriptors()) {
				if (seq.contains(seq.IMPACT_METHOD, descriptor.getRefId()))
					continue;
				createMethod(descriptor);
			}
		} catch (Exception e) {
			log.error("failed to import LCIA methods", e);
		}
	}

	private void createMethod(ImpactMethodDescriptor descriptor) {
		ImpactMethod srcMethod = srcDao.getForId(descriptor.getId());
		ImpactMethod destMethod = srcMethod.clone();
		destMethod.setRefId(srcMethod.getRefId());
		destMethod.setCategory(refs.switchRef(srcMethod.getCategory()));
		switchFactorReferences(destMethod);
		// we need to set the reference IDs from the source as they are
		// generated
		// new in the clone method.
		switchImpactRefIds(srcMethod, destMethod);
		switchNwSetRefIds(srcMethod, destMethod);
		destMethod = destDao.insert(destMethod);
		seq.put(seq.IMPACT_METHOD, srcMethod.getRefId(), destMethod.getId());
		for (NwSet nwSet : destMethod.getNwSets())
			seq.put(seq.NW_SET, nwSet.getRefId(), nwSet.getId());
	}

	private void switchFactorReferences(ImpactMethod destMethod) {
		for (ImpactCategory category : destMethod.getImpactCategories()) {
			for (ImpactFactor factor : category.getImpactFactors()) {
				factor.setFlow(refs.switchRef(factor.getFlow()));
				factor.setUnit(refs.switchRef(factor.getUnit()));
				Flow destFlow = factor.getFlow(); // already switched
				factor.setFlowPropertyFactor(refs.switchRef(
						factor.getFlowPropertyFactor(), destFlow));
			}
		}
	}

	private void switchImpactRefIds(ImpactMethod srcMethod,
			ImpactMethod destMethod) {
		for (ImpactCategory srcCat : srcMethod.getImpactCategories()) {
			for (ImpactCategory destCat : destMethod.getImpactCategories()) {
				if (areEqual(srcCat, destCat)) {
					destCat.setRefId(srcCat.getRefId());
					break;
				}
			}
		}
	}

	private boolean areEqual(ImpactCategory srcCat, ImpactCategory destCat) {
		return Strings.nullOrEqual(srcCat.getName(), destCat.getName())
				&& Strings.nullOrEqual(srcCat.getReferenceUnit(),
						destCat.getReferenceUnit())
				&& Strings.nullOrEqual(srcCat.getDescription(),
						destCat.getDescription())
				&& (srcCat.getImpactFactors().size() == destCat
						.getImpactFactors().size());
	}

	private void switchNwSetRefIds(ImpactMethod srcMethod,
			ImpactMethod destMethod) {
		for (NwSet srcNwSet : srcMethod.getNwSets()) {
			for (NwSet destNwSet : destMethod.getNwSets()) {
				if (areEqual(srcNwSet, destNwSet)) {
					destNwSet.setRefId(srcNwSet.getRefId());
					break;
				}
			}
		}
	}

	private boolean areEqual(NwSet srcNwSet, NwSet destNwSet) {
		return Strings.nullOrEqual(srcNwSet.getName(), destNwSet.getName())
				&& Strings.nullOrEqual(srcNwSet.getDescription(),
						destNwSet.getName())
				&& Strings.nullOrEqual(srcNwSet.getWeightedScoreUnit(),
						destNwSet.getWeightedScoreUnit());
	}

}
