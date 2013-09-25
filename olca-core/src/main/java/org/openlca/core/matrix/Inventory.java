package org.openlca.core.matrix;

import org.openlca.core.math.IMatrix;
import org.openlca.core.model.AllocationMethod;
import org.openlca.expressions.FormulaInterpreter;

/**
 * Contains all the information of the inventory of a complete product system.
 */
public class Inventory {

	private ProductIndex productIndex;
	private FlowIndex flowIndex;
	private ExchangeMatrix technologyMatrix;
	private ExchangeMatrix interventionMatrix;
	private AllocationMethod allocationMethod;
	private FormulaInterpreter formulaInterpreter;

	public boolean isEmpty() {
		return productIndex == null || productIndex.size() == 0
				|| flowIndex == null || flowIndex.isEmpty()
				|| technologyMatrix == null || technologyMatrix.isEmpty()
				|| interventionMatrix == null || interventionMatrix.isEmpty();
	}

	public FormulaInterpreter getFormulaInterpreter() {
		return formulaInterpreter;
	}

	public void setFormulaInterpreter(FormulaInterpreter formulaInterpreter) {
		this.formulaInterpreter = formulaInterpreter;
	}

	public void setAllocationMethod(AllocationMethod allocationMethod) {
		this.allocationMethod = allocationMethod;
	}

	public AllocationMethod getAllocationMethod() {
		return allocationMethod;
	}

	public ProductIndex getProductIndex() {
		return productIndex;
	}

	public void setProductIndex(ProductIndex productIndex) {
		this.productIndex = productIndex;
	}

	public FlowIndex getFlowIndex() {
		return flowIndex;
	}

	public void setFlowIndex(FlowIndex flowIndex) {
		this.flowIndex = flowIndex;
	}

	public ExchangeMatrix getTechnologyMatrix() {
		return technologyMatrix;
	}

	public void setTechnologyMatrix(ExchangeMatrix technologyMatrix) {
		this.technologyMatrix = technologyMatrix;
	}

	public ExchangeMatrix getInterventionMatrix() {
		return interventionMatrix;
	}

	public void setInterventionMatrix(ExchangeMatrix interventionMatrix) {
		this.interventionMatrix = interventionMatrix;
	}

	/**
	 * Evaluates the formulas in the exchange matrices of this inventory using
	 * the formula interpreter that is bound to this inventory. Does nothing if
	 * there is no interpreter set or if the exchange matrices are NULL.
	 */
	public void evalFormulas() {
		if (formulaInterpreter == null)
			return;
		if (technologyMatrix != null)
			technologyMatrix.eval(formulaInterpreter);
		if (interventionMatrix != null)
			interventionMatrix.eval(formulaInterpreter);
	}

	public InventoryMatrix asMatrix() {
		evalFormulas();
		InventoryMatrix matrix = new InventoryMatrix();
		matrix.setFlowIndex(flowIndex);
		matrix.setProductIndex(productIndex);
		IMatrix enviMatrix = interventionMatrix.createRealMatrix();
		matrix.setInterventionMatrix(enviMatrix);
		IMatrix techMatrix = technologyMatrix.createRealMatrix();
		matrix.setTechnologyMatrix(techMatrix);
		return matrix;
	}

}
