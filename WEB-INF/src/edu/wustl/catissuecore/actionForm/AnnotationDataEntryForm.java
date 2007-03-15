/*
 * Created on Jan 17, 2007
 * @author
 *
 */

package edu.wustl.catissuecore.actionForm;

import java.io.Serializable;
import java.util.List;

import org.apache.struts.action.ActionForm;

import edu.wustl.common.actionForm.AbstractActionForm;
import edu.wustl.common.domain.AbstractDomainObject;

/**
 * @author preeti_munot
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AnnotationDataEntryForm extends AbstractActionForm
        implements
            Serializable
{

    private static final long serialVersionUID = 5496516855615880241L;
    protected List annotationsList;
    protected String definedAnnotationsDataXML;
    protected String selectedAnnotation;
    protected String parentEntityId;
    private String selectedRecords;
    private String selectedStaticEntityId;
    private String selectedStaticEntityRecordId;
    
    public String getSelectedStaticEntityId()
    {
        return selectedStaticEntityId;
    }

    
    public void setSelectedStaticEntityId(String selectedStaticEntityId)
    {
        this.selectedStaticEntityId = selectedStaticEntityId;
    }

    
    public String getSelectedStaticEntityRecordId()
    {
        return selectedStaticEntityRecordId;
    }

    
    public void setSelectedStaticEntityRecordId(String selectedStaticEntityRecordId)
    {
        this.selectedStaticEntityRecordId = selectedStaticEntityRecordId;
    }

    public String getSelectedRecords()
    {
        return selectedRecords;
    }

    public void setSelectedRecords(String selectedRecords)
    {
        this.selectedRecords = selectedRecords;
    }

    public String getDefinedAnnotationsDataXML()
    {
        return this.definedAnnotationsDataXML;
    }

    public void setDefinedAnnotationsDataXML(String definedAnnotationsDataXML)
    {
        this.definedAnnotationsDataXML = definedAnnotationsDataXML;
    }

    public List getAnnotationsList()
    {
        return this.annotationsList;
    }

    public void setAnnotationsList(List annotationsList)
    {
        this.annotationsList = annotationsList;
    }

    public String getSelectedAnnotation()
    {
        return this.selectedAnnotation;
    }

    public void setSelectedAnnotation(String selectedAnnotation)
    {
        this.selectedAnnotation = selectedAnnotation;
    }

    public String getParentEntityId()
    {
        return this.parentEntityId;
    }

    public void setParentEntityId(String parentEntityId)
    {
        this.parentEntityId = parentEntityId;
    }

    public int getFormId()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    public void setAllValues(AbstractDomainObject abstractDomain)
    {
        // TODO Auto-generated method stub

    }

    protected void reset()
    {
        // TODO Auto-generated method stub

    }
}
