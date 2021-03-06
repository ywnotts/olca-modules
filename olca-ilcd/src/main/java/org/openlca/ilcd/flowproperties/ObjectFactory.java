package org.openlca.ilcd.flowproperties;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the org.openlca.ilcd.flowproperties package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

	private final static QName _FlowPropertyDataSet_QNAME = new QName(
			"http://lca.jrc.it/ILCD/FlowProperty", "flowPropertyDataSet");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: org.openlca.ilcd.flowproperties
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link FlowProperty }
	 * 
	 */
	public FlowProperty createFlowProperty() {
		return new FlowProperty();
	}

	/**
	 * Create an instance of {@link QuantitativeReference }
	 * 
	 */
	public QuantitativeReference createQuantitativeReference() {
		return new QuantitativeReference();
	}

	/**
	 * Create an instance of {@link DataSetInformation }
	 * 
	 */
	public DataSetInformation createDataSetInformation() {
		return new DataSetInformation();
	}

	/**
	 * Create an instance of {@link ComplianceDeclaration }
	 * 
	 */
	public ComplianceDeclaration createComplianceDeclaration() {
		return new ComplianceDeclaration();
	}

	/**
	 * Create an instance of {@link ModellingAndValidation }
	 * 
	 */
	public ModellingAndValidation createModellingAndValidation() {
		return new ModellingAndValidation();
	}

	/**
	 * Create an instance of {@link Publication }
	 * 
	 */
	public Publication createPublication() {
		return new Publication();
	}

	/**
	 * Create an instance of {@link FlowPropertyInformation }
	 * 
	 */
	public FlowPropertyInformation createFlowPropertyInformation() {
		return new FlowPropertyInformation();
	}

	/**
	 * Create an instance of {@link DataEntry }
	 * 
	 */
	public DataEntry createDataEntry() {
		return new DataEntry();
	}

	/**
	 * Create an instance of {@link Representativeness }
	 * 
	 */
	public Representativeness createRepresentativeness() {
		return new Representativeness();
	}

	/**
	 * Create an instance of {@link ComplianceDeclarationList }
	 * 
	 */
	public ComplianceDeclarationList createComplianceDeclarationList() {
		return new ComplianceDeclarationList();
	}

	/**
	 * Create an instance of {@link AdministrativeInformation }
	 * 
	 */
	public AdministrativeInformation createAdministrativeInformation() {
		return new AdministrativeInformation();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link FlowProperty }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://lca.jrc.it/ILCD/FlowProperty", name = "flowPropertyDataSet")
	public JAXBElement<FlowProperty> createFlowPropertyDataSet(
			FlowProperty value) {
		return new JAXBElement<>(_FlowPropertyDataSet_QNAME,
				FlowProperty.class, null, value);
	}

}
