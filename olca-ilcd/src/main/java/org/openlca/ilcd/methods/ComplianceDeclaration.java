
package org.openlca.ilcd.methods;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;
import org.openlca.ilcd.commons.Compliance;
import org.openlca.ilcd.commons.DataSetReference;
import org.openlca.ilcd.commons.Other;


/**
 * <p>Java class for ComplianceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ComplianceType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{http://lca.jrc.it/ILCD/Common}ComplianceGroup"/>
 *         &lt;element ref="{http://lca.jrc.it/ILCD/Common}nomenclatureCompliance" minOccurs="0"/>
 *         &lt;element ref="{http://lca.jrc.it/ILCD/Common}methodologicalCompliance" minOccurs="0"/>
 *         &lt;element ref="{http://lca.jrc.it/ILCD/Common}reviewCompliance" minOccurs="0"/>
 *         &lt;element ref="{http://lca.jrc.it/ILCD/Common}documentationCompliance" minOccurs="0"/>
 *         &lt;element ref="{http://lca.jrc.it/ILCD/Common}qualityCompliance" minOccurs="0"/>
 *         &lt;element ref="{http://lca.jrc.it/ILCD/Common}other" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;anyAttribute processContents='lax' namespace='##other'/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ComplianceType", propOrder = {
    "referenceToComplianceSystem",
    "approvalOfOverallCompliance",
    "nomenclatureCompliance",
    "methodologicalCompliance",
    "reviewCompliance",
    "documentationCompliance",
    "qualityCompliance",
    "other"
})
public class ComplianceDeclaration
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(namespace = "http://lca.jrc.it/ILCD/Common", required = true)
    protected DataSetReference referenceToComplianceSystem;
    @XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
    protected Compliance approvalOfOverallCompliance;
    @XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
    protected Compliance nomenclatureCompliance;
    @XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
    protected Compliance methodologicalCompliance;
    @XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
    protected Compliance reviewCompliance;
    @XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
    protected Compliance documentationCompliance;
    @XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
    protected Compliance qualityCompliance;
    @XmlElement(namespace = "http://lca.jrc.it/ILCD/Common")
    protected Other other;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes =  new HashMap<>();

    /**
     * Gets the value of the referenceToComplianceSystem property.
     * 
     * @return
     *     possible object is
     *     {@link DataSetReference }
     *     
     */
    public DataSetReference getReferenceToComplianceSystem() {
        return referenceToComplianceSystem;
    }

    /**
     * Sets the value of the referenceToComplianceSystem property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataSetReference }
     *     
     */
    public void setReferenceToComplianceSystem(DataSetReference value) {
        this.referenceToComplianceSystem = value;
    }

    /**
     * Gets the value of the approvalOfOverallCompliance property.
     * 
     * @return
     *     possible object is
     *     {@link Compliance }
     *     
     */
    public Compliance getApprovalOfOverallCompliance() {
        return approvalOfOverallCompliance;
    }

    /**
     * Sets the value of the approvalOfOverallCompliance property.
     * 
     * @param value
     *     allowed object is
     *     {@link Compliance }
     *     
     */
    public void setApprovalOfOverallCompliance(Compliance value) {
        this.approvalOfOverallCompliance = value;
    }

    /**
     * Nomenclature compliance of this data set with the respective requirements set by the "compliance system" refered to.
     * 
     * @return
     *     possible object is
     *     {@link Compliance }
     *     
     */
    public Compliance getNomenclatureCompliance() {
        return nomenclatureCompliance;
    }

    /**
     * Sets the value of the nomenclatureCompliance property.
     * 
     * @param value
     *     allowed object is
     *     {@link Compliance }
     *     
     */
    public void setNomenclatureCompliance(Compliance value) {
        this.nomenclatureCompliance = value;
    }

    /**
     * Methodological compliance of this data set with the respective requirements set by the "compliance system" refered to.
     * 
     * @return
     *     possible object is
     *     {@link Compliance }
     *     
     */
    public Compliance getMethodologicalCompliance() {
        return methodologicalCompliance;
    }

    /**
     * Sets the value of the methodologicalCompliance property.
     * 
     * @param value
     *     allowed object is
     *     {@link Compliance }
     *     
     */
    public void setMethodologicalCompliance(Compliance value) {
        this.methodologicalCompliance = value;
    }

    /**
     * Review/Verification compliance of this data set with the respective requirements set by the "compliance system" refered to.
     * 
     * @return
     *     possible object is
     *     {@link Compliance }
     *     
     */
    public Compliance getReviewCompliance() {
        return reviewCompliance;
    }

    /**
     * Sets the value of the reviewCompliance property.
     * 
     * @param value
     *     allowed object is
     *     {@link Compliance }
     *     
     */
    public void setReviewCompliance(Compliance value) {
        this.reviewCompliance = value;
    }

    /**
     * Documentation/Reporting compliance of this data set with the respective requirements set by the "compliance system" refered to.
     * 
     * @return
     *     possible object is
     *     {@link Compliance }
     *     
     */
    public Compliance getDocumentationCompliance() {
        return documentationCompliance;
    }

    /**
     * Sets the value of the documentationCompliance property.
     * 
     * @param value
     *     allowed object is
     *     {@link Compliance }
     *     
     */
    public void setDocumentationCompliance(Compliance value) {
        this.documentationCompliance = value;
    }

    /**
     * Quality compliance of this data set with the respective requirements set by the "compliance system" refered to.
     * 
     * @return
     *     possible object is
     *     {@link Compliance }
     *     
     */
    public Compliance getQualityCompliance() {
        return qualityCompliance;
    }

    /**
     * Sets the value of the qualityCompliance property.
     * 
     * @param value
     *     allowed object is
     *     {@link Compliance }
     *     
     */
    public void setQualityCompliance(Compliance value) {
        this.qualityCompliance = value;
    }

    /**
     * Gets the value of the other property.
     * 
     * @return
     *     possible object is
     *     {@link Other }
     *     
     */
    public Other getOther() {
        return other;
    }

    /**
     * Sets the value of the other property.
     * 
     * @param value
     *     allowed object is
     *     {@link Other }
     *     
     */
    public void setOther(Other value) {
        this.other = value;
    }

    /**
     * Gets a map that contains attributes that aren't bound to any typed property on this class.
     * 
     * <p>
     * the map is keyed by the name of the attribute and 
     * the value is the string value of the attribute.
     * 
     * the map returned by this method is live, and you can add new attribute
     * by updating the map directly. Because of this design, there's no setter.
     * 
     * 
     * @return
     *     always non-null
     */
    public Map<QName, String> getOtherAttributes() {
        return otherAttributes;
    }

}
