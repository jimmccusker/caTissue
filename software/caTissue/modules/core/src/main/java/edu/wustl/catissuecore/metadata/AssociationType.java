//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2011.05.30 at 01:53:00 PM GMT+05:30
//


package edu.wustl.catissuecore.metadata;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for associationType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="associationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sourceEntityName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="targetEntityName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="associationName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="associationTye" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="roleName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="roleNameTable" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="isSwap" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="srcAssociationId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="targetAssociationId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="maxCardinality" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="isSystemGenerated" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="direction" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="manytomany" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "associationType", propOrder = {
    "sourceEntityName",
    "targetEntityName",
    "associationName",
    "associationTye",
    "roleName",
    "roleNameTable",
    "isSwap",
    "srcAssociationId",
    "targetAssociationId",
    "maxCardinality",
    "isSystemGenerated",
    "direction",
    "manytomany"
})
public class AssociationType {

    @XmlElement(required = true)
    protected String sourceEntityName;
    @XmlElement(required = true)
    protected String targetEntityName;
    @XmlElement(required = true)
    protected String associationName;
    @XmlElement(required = true)
    protected String associationTye;
    @XmlElement(required = true)
    protected String roleName;
    @XmlElement(required = true)
    protected String roleNameTable;
    protected boolean isSwap;
    @XmlElement(required = true)
    protected String srcAssociationId;
    @XmlElement(required = true)
    protected String targetAssociationId;
    protected int maxCardinality;
    protected boolean isSystemGenerated;
    @XmlElement(required = true)
    protected String direction;
    protected boolean manytomany;

    /**
     * Gets the value of the sourceEntityName property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSourceEntityName() {
        return sourceEntityName;
    }

    /**
     * Sets the value of the sourceEntityName property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSourceEntityName(String value) {
        this.sourceEntityName = value;
    }

    /**
     * Gets the value of the targetEntityName property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getTargetEntityName() {
        return targetEntityName;
    }

    /**
     * Sets the value of the targetEntityName property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setTargetEntityName(String value) {
        this.targetEntityName = value;
    }

    /**
     * Gets the value of the associationName property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getAssociationName() {
        return associationName;
    }

    /**
     * Sets the value of the associationName property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setAssociationName(String value) {
        this.associationName = value;
    }

    /**
     * Gets the value of the associationTye property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getAssociationTye() {
        return associationTye;
    }

    /**
     * Sets the value of the associationTye property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setAssociationTye(String value) {
        this.associationTye = value;
    }

    /**
     * Gets the value of the roleName property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Sets the value of the roleName property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setRoleName(String value) {
        this.roleName = value;
    }

    /**
     * Gets the value of the roleNameTable property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getRoleNameTable() {
        return roleNameTable;
    }

    /**
     * Sets the value of the roleNameTable property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setRoleNameTable(String value) {
        this.roleNameTable = value;
    }

    /**
     * Gets the value of the isSwap property.
     *
     */
    public boolean isIsSwap() {
        return isSwap;
    }

    /**
     * Sets the value of the isSwap property.
     *
     */
    public void setIsSwap(boolean value) {
        this.isSwap = value;
    }

    /**
     * Gets the value of the srcAssociationId property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSrcAssociationId() {
        return srcAssociationId;
    }

    /**
     * Sets the value of the srcAssociationId property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSrcAssociationId(String value) {
        this.srcAssociationId = value;
    }

    /**
     * Gets the value of the targetAssociationId property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getTargetAssociationId() {
        return targetAssociationId;
    }

    /**
     * Sets the value of the targetAssociationId property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setTargetAssociationId(String value) {
        this.targetAssociationId = value;
    }

    /**
     * Gets the value of the maxCardinality property.
     *
     */
    public int getMaxCardinality() {
        return maxCardinality;
    }

    /**
     * Sets the value of the maxCardinality property.
     *
     */
    public void setMaxCardinality(int value) {
        this.maxCardinality = value;
    }

    /**
     * Gets the value of the isSystemGenerated property.
     *
     */
    public boolean isIsSystemGenerated() {
        return isSystemGenerated;
    }

    /**
     * Sets the value of the isSystemGenerated property.
     *
     */
    public void setIsSystemGenerated(boolean value) {
        this.isSystemGenerated = value;
    }

    /**
     * Gets the value of the direction property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDirection() {
        return direction;
    }

    /**
     * Sets the value of the direction property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDirection(String value) {
        this.direction = value;
    }

    /**
     * Gets the value of the manytomany property.
     *
     */
    public boolean isManytomany() {
        return manytomany;
    }

    /**
     * Sets the value of the manytomany property.
     *
     */
    public void setManytomany(boolean value) {
        this.manytomany = value;
    }

}
