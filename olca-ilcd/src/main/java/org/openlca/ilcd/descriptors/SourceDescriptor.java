package org.openlca.ilcd.descriptors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.ilcd-network.org/ILCD/ServiceAPI}uuid" minOccurs="0"/>
 *         &lt;element ref="{http://www.ilcd-network.org/ILCD/ServiceAPI}permanentUri" minOccurs="0"/>
 *         &lt;element ref="{http://www.ilcd-network.org/ILCD/ServiceAPI}dataSetVersion" minOccurs="0"/>
 *         &lt;element ref="{http://www.ilcd-network.org/ILCD/ServiceAPI}name" minOccurs="0"/>
 *         &lt;element ref="{http://www.ilcd-network.org/ILCD/ServiceAPI}shortName" minOccurs="0"/>
 *         &lt;element ref="{http://www.ilcd-network.org/ILCD/ServiceAPI}classification" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.ilcd-network.org/ILCD/ServiceAPI}generalComment" minOccurs="0"/>
 *         &lt;element ref="{http://www.ilcd-network.org/ILCD/ServiceAPI/Source}citation" minOccurs="0"/>
 *         &lt;element ref="{http://www.ilcd-network.org/ILCD/ServiceAPI/Source}publicationType" minOccurs="0"/>
 *         &lt;element ref="{http://www.ilcd-network.org/ILCD/ServiceAPI/Source}file" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.ilcd-network.org/ILCD/ServiceAPI/Source}belongsTo" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute ref="{http://www.w3.org/1999/xlink}href"/>
 *       &lt;attribute ref="{http://www.ilcd-network.org/ILCD/ServiceAPI}sourceId"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "uuid", "permanentUri", "dataSetVersion",
		"name", "shortName", "classification", "generalComment", "citation",
		"publicationType", "file", "belongsTo" })
public class SourceDescriptor implements Serializable {

	private final static long serialVersionUID = 1L;
	@XmlElement(namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI")
	protected String uuid;
	@XmlElement(namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI")
	@XmlSchemaType(name = "anyURI")
	protected String permanentUri;
	@XmlElement(namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI")
	protected String dataSetVersion;
	@XmlElement(namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI")
	protected LangString name;
	@XmlElement(namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI")
	protected String shortName;
	@XmlElement(namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI")
	protected List<Classification> classification;
	@XmlElement(namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI")
	protected LangString generalComment;
	@XmlElement(namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI/Source")
	protected LangString citation;
	@XmlElement(namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI/Source")
	protected PublicationTypeValues publicationType;
	@XmlElement(namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI/Source")
	protected List<DataSetReference> file;
	@XmlElement(namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI/Source")
	protected DataSetReference belongsTo;
	@XmlAttribute(name = "href", namespace = "http://www.w3.org/1999/xlink")
	@XmlSchemaType(name = "anyURI")
	protected String href;
	@XmlAttribute(name = "sourceId", namespace = "http://www.ilcd-network.org/ILCD/ServiceAPI")
	protected String sourceId;

	/**
	 * Gets the value of the uuid property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * Sets the value of the uuid property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setUuid(String value) {
		this.uuid = value;
	}

	/**
	 * Gets the value of the permanentUri property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPermanentUri() {
		return permanentUri;
	}

	/**
	 * Sets the value of the permanentUri property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPermanentUri(String value) {
		this.permanentUri = value;
	}

	/**
	 * Gets the value of the dataSetVersion property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDataSetVersion() {
		return dataSetVersion;
	}

	/**
	 * Sets the value of the dataSetVersion property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDataSetVersion(String value) {
		this.dataSetVersion = value;
	}

	/**
	 * Gets the value of the name property.
	 * 
	 * @return possible object is {@link LangString }
	 * 
	 */
	public LangString getName() {
		return name;
	}

	/**
	 * Sets the value of the name property.
	 * 
	 * @param value
	 *            allowed object is {@link LangString }
	 * 
	 */
	public void setName(LangString value) {
		this.name = value;
	}

	/**
	 * Gets the value of the shortName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getShortName() {
		return shortName;
	}

	/**
	 * Sets the value of the shortName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setShortName(String value) {
		this.shortName = value;
	}

	/**
	 * Gets the value of the classification property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the classification property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getClassification().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link Classification }
	 * 
	 * 
	 */
	public List<Classification> getClassification() {
		if (classification == null) {
			classification = new ArrayList<>();
		}
		return this.classification;
	}

	/**
	 * Gets the value of the generalComment property.
	 * 
	 * @return possible object is {@link LangString }
	 * 
	 */
	public LangString getGeneralComment() {
		return generalComment;
	}

	/**
	 * Sets the value of the generalComment property.
	 * 
	 * @param value
	 *            allowed object is {@link LangString }
	 * 
	 */
	public void setGeneralComment(LangString value) {
		this.generalComment = value;
	}

	/**
	 * Gets the value of the citation property.
	 * 
	 * @return possible object is {@link LangString }
	 * 
	 */
	public LangString getCitation() {
		return citation;
	}

	/**
	 * Sets the value of the citation property.
	 * 
	 * @param value
	 *            allowed object is {@link LangString }
	 * 
	 */
	public void setCitation(LangString value) {
		this.citation = value;
	}

	/**
	 * Gets the value of the publicationType property.
	 * 
	 * @return possible object is {@link PublicationTypeValues }
	 * 
	 */
	public PublicationTypeValues getPublicationType() {
		return publicationType;
	}

	/**
	 * Sets the value of the publicationType property.
	 * 
	 * @param value
	 *            allowed object is {@link PublicationTypeValues }
	 * 
	 */
	public void setPublicationType(PublicationTypeValues value) {
		this.publicationType = value;
	}

	/**
	 * Gets the value of the file property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the file property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getFile().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list
	 * {@link DataSetReference }
	 * 
	 * 
	 */
	public List<DataSetReference> getFile() {
		if (file == null) {
			file = new ArrayList<>();
		}
		return this.file;
	}

	/**
	 * Gets the value of the belongsTo property.
	 * 
	 * @return possible object is {@link DataSetReference }
	 * 
	 */
	public DataSetReference getBelongsTo() {
		return belongsTo;
	}

	/**
	 * Sets the value of the belongsTo property.
	 * 
	 * @param value
	 *            allowed object is {@link DataSetReference }
	 * 
	 */
	public void setBelongsTo(DataSetReference value) {
		this.belongsTo = value;
	}

	/**
	 * Gets the value of the href property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getHref() {
		return href;
	}

	/**
	 * Sets the value of the href property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setHref(String value) {
		this.href = value;
	}

	/**
	 * Gets the value of the sourceId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSourceId() {
		return sourceId;
	}

	/**
	 * Sets the value of the sourceId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSourceId(String value) {
		this.sourceId = value;
	}

}
