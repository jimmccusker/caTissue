/**
 * <p>Title: Biohazard Class>
 * <p>Description:  An attribute of a Specimen that renders 
 * it potentially harmful to a User.</p>
 * Copyright:    Copyright (c) year
 * Company: Washington University, School of Medicine, St. Louis.
 * @author Gautam Shetty
 * @author Aniruddha Phadnis
 * @version 1.00
 */

package edu.wustl.catissuecore.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

import edu.wustl.catissuecore.actionForm.BiohazardForm;
import edu.wustl.common.actionForm.AbstractActionForm;
import edu.wustl.common.domain.AbstractDomainObject;
import edu.wustl.common.util.logger.Logger;

/**
 * An attribute of a Specimen that renders 
 * it potentially harmful to a User.
 * @hibernate.class table="CATISSUE_BIOHAZARD"
 * @author gautam_shetty
 */
public class Biohazard extends AbstractDomainObject implements Serializable
{
    private static final long serialVersionUID = 1234567890L;

    /**
     * System generated unique id.
     */
    protected Long id;

    /**
     * Name of the biohazardous agent.
     */
    protected String name;

    /**
     * Comments about the biohazard.
     */
    protected String comments;

    /**
     * Type of biohazard (Infectious, Radioactive, Toxic, Carcinogen, Mutagen).
     */
    protected String type;
    
    /**
     *boolean for checking persisted Biohazard persisted value
     *
     */
    
    protected transient Boolean persisted;
    
    protected Collection specimenCollection = new HashSet();

    //Default Constructor
    public Biohazard()
    {    	
    }
    
    public Biohazard(AbstractActionForm form)
    {
    	setAllValues(form);
    }
    
    /**
     * Returns the system generated unique id.
     * @hibernate.id name="id" column="IDENTIFIER" type="long" length="30"
     * unsaved-value="null" generator-class="native"
     * @hibernate.generator-param name="sequence" value="CATISSUE_BIOHAZARD_SEQ"
     * @return the system generated unique id.
     * @see #setId(Long)
     * */
    public Long getId()
    {
        return id;
    }

    /**
     * Sets the system generated unique id.
     * @param id the system generated unique id.
     * @see #getId()
     * */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
     * Returns the name of the biohazardous agent.
     * @hibernate.property name="name" type="string" 
     * column="NAME" length="50" not-null="true" unique="true"
     * @return the name of the biohazardous agent.
     * @see #setName(String)
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the name of the biohazardous agent.
     * @param name the name of the biohazardous agent.
     * @see #getName()
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Returns the comments about the biohazard.
     * @hibernate.property name="comments" type="string" 
     * column="COMMENTS" length="500"
     * @return the comments about the biohazard.
     * @see #setComments(String)
     */
    public String getComments()
    {
        return comments;
    }

    /**
     * Sets the comments about the biohazard.
     * @param comments the comments about the biohazard.
     * @see #getComments()
     */
    public void setComments(String comments)
    {
        this.comments = comments;
    }

    /**
     * Returns the type of biohazard (Infectious, Radioactive, Toxic, Carcinogen, Mutagen).
     * @hibernate.property name="type" type="string" 
     * column="TYPE" length="50"
     * @return the type of biohazard (Infectious, Radioactive, Toxic, Carcinogen, Mutagen).
     * @see #setType(String)
     */
    public String getType()
    {
        return type;
    }

    /**
     * Sets the type of biohazard (Infectious, Radioactive, Toxic, Carcinogen, Mutagen).
     * @param type the type of biohazard (Infectious, Radioactive, Toxic, Carcinogen, Mutagen).
     * @see #getType()
     */
    public void setType(String type)
    {
        this.type = type;
    }
    
	/*
     * @hibernate.many-to-one column="SPECIMEN_ID"  class="edu.wustl.catissuecore.domain.Specimen" constrained="true"
	 * @see #setParticipant(Site)
     */
    /**
     * @hibernate.set name="specimenCollection" table="CATISSUE_SPECIMEN_BIOHZ_REL"
     * cascade="save-update" inverse="true" lazy="false"
     * @hibernate.collection-key column="BIOHAZARD_ID"
     * @hibernate.collection-many-to-many class="edu.wustl.catissuecore.domain.Specimen" column="SPECIMEN_ID"

     * */
	public Collection getSpecimenCollection() 
	{
		return specimenCollection;
	}
	
	/**
	 * @param specimen The specimen to set.
	 */
	public void setSpecimenCollection(Collection specimenCollection) 
	{
		this.specimenCollection = specimenCollection;
	}
	
	/**
     * This function Copies the data from an BiohazardForm object to a Biohazard object.
     * @param siteForm An SiteForm object containing the information about the site.  
     * */
    public void setAllValues(AbstractActionForm abstractForm)
    {
        try
        {
            BiohazardForm form 	= (BiohazardForm) abstractForm;
            this.comments = form.getComments();
            this.name = form.getName().trim() ;
            this.type = form.getType();
        }
        catch (Exception excp)
        {
            Logger.out.error(excp.getMessage());
        }
    }

	public Boolean getPersisted() {
		return persisted;
	}

	public void setPersisted(Boolean persisted) {
		this.persisted = persisted;
	}
	
	 /**
     * Returns message label to display on success add or edit
     * @return String
     */
	public String getMessageLabel() {		
		return this.name;
	}
}