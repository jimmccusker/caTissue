/**
 * <p>Title: ReviewEventParameters Class>
 * <p>Description:  Attributes related to quality review event of a specimen. </p>
 * Copyright:    Copyright (c) year
 * Company: Washington University, School of Medicine, St. Louis.
 * @author Aniruddha Phadnis
 * @version 1.00
 * Created on Apr 7, 2005
 */
package edu.wustl.catissuecore.domain;

/**
 * Attributes related to quality review event of a specimen.
 * @hibernate.joined-subclass table="CATISSUE_REVIEW_EVENT_PARAMETERS"
 * @hibernate.joined-subclass-key column="IDENTIFIER" 
 * @author Aniruddha Phadnis
 */
public abstract class ReviewEventParameters extends SpecimenEventParameters
		implements java.io.Serializable
{
	private static final long serialVersionUID = 1234567890L;
}