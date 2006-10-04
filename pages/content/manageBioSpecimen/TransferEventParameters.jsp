<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/nlevelcombo.tld" prefix="ncombo" %>
<%@ page import="edu.wustl.catissuecore.util.global.Utility"%>
<%@ page import="edu.wustl.catissuecore.util.global.Constants"%>
<%@ page import="edu.wustl.common.util.tag.ScriptGenerator" %>
<%@ page import="java.util.*"%>
<%@ page import="edu.wustl.catissuecore.actionForm.TransferEventParametersForm"%>

<!-- Mandar : 434 : for tooltip -->
<script language="JavaScript" type="text/javascript" src="jss/Hashtable.js"></script>
<script language="JavaScript" type="text/javascript" src="jss/javaScript.js"></script>
<!-- Mandar 21-Aug-06 : For calendar changes -->
<script src="jss/calendarComponent.js"></script>
<SCRIPT>var imgsrc="images/";</SCRIPT>
<LINK href="css/calanderComponent.css" type=text/css rel=stylesheet>
<!-- Mandar 21-Aug-06 : calendar changes end -->

<%
		TransferEventParametersForm form = (TransferEventParametersForm)request.getAttribute("transferEventParametersForm");
        String operation = (String) request.getAttribute(Constants.OPERATION);
        String formName, specimenId=null , fromPositionData =null;
        String posOne = null, posTwo = null, storContId = null;

		specimenId = (String) request.getAttribute(Constants.SPECIMEN_ID);
		fromPositionData = (String) request.getAttribute(Constants.FROM_POSITION_DATA);
		posOne = (String) request.getAttribute(Constants.POS_ONE);
		posTwo = (String) request.getAttribute(Constants.POS_TWO);
		storContId = (String) request.getAttribute(Constants.STORAGE_CONTAINER_ID);
	
        boolean readOnlyValue;
        if (operation.equals(Constants.EDIT))
        {
            formName = Constants.TRANSFER_EVENT_PARAMETERS_EDIT_ACTION;
            readOnlyValue = true;
        }
        else
        {
            formName = Constants.TRANSFER_EVENT_PARAMETERS_ADD_ACTION;
			
            readOnlyValue = false;
        }

		Object obj = request.getAttribute("transferEventParametersForm");
		String currentEventParametersDate = ""; 
		if(obj != null && obj instanceof TransferEventParametersForm)
		{
			form = (TransferEventParametersForm)obj;
			currentEventParametersDate = form.getDateOfEvent();
			if(currentEventParametersDate == null)
				currentEventParametersDate = "";
		}

		
%>	
			
<html:errors/>
    
<table summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="600">

<html:form action="<%=Constants.TRANSFER_EVENT_PARAMETERS_ADD_ACTION%>">


	<!-- NEW TRANSFER_EVENT_PARAMETERS REGISTRATION BEGINS-->
	<tr>
	<td>
	
	<table summary="" cellpadding="3" cellspacing="0" border="0">
		<tr>
			<td><html:hidden property="operation" value="<%=operation%>"/></td>
		</tr>
		
		<tr>
			<td><html:hidden property="id" /></td>
		</tr>
		
		<tr>
			 <td class="formMessage" colspan="3">* indicates a required field</td>
		</tr>
		
		<tr>
			<td>
				<html:hidden property="specimenId" value="<%=specimenId%>"/>
			</td>
		</tr>

		<tr>
			<td class="formTitle" height="20" colspan="3">
				<logic:equal name="operation" value="<%=Constants.ADD%>">
					<bean:message key="transfereventparameters.title"/>
				</logic:equal>
				<logic:equal name="operation" value="<%=Constants.EDIT%>">
					<bean:message key="transfereventparameters.edittitle"/>
				</logic:equal>
			</td>
		</tr>
		
		<!-- Name of the transfereventparameters -->
<!-- User -->		
		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formRequiredLabel">
				<label for="type">
					<bean:message key="eventparameters.user"/> 
				</label>
			</td>
			<td class="formField">
<!-- Mandar : 434 : for tooltip -->
				<html:select property="userId" styleClass="formFieldSized" styleId="userId" size="1"
				 onmouseover="showTip(this.id)" onmouseout="hideTip(this.id)">
					<html:options collection="<%=Constants.USERLIST%>" labelProperty="name" property="value"/>
				</html:select>
			</td>
		</tr>

<!-- date -->		
		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formRequiredLabel">
				<label for="type">
					<bean:message key="eventparameters.dateofevent"/> 
				</label>
			</td>
			<td class="formField">
<%
if(currentEventParametersDate.trim().length() > 0)
{
	Integer eventParametersYear = new Integer(Utility.getYear(currentEventParametersDate ));
	Integer eventParametersMonth = new Integer(Utility.getMonth(currentEventParametersDate ));
	Integer eventParametersDay = new Integer(Utility.getDay(currentEventParametersDate ));
%>
<ncombo:DateTimeComponent name="dateOfEvent"
			  id="dateOfEvent"
			  formName="transferEventParametersForm"
			  month= "<%= eventParametersMonth %>"
			  year= "<%= eventParametersYear %>"
			  day= "<%= eventParametersDay %>"
			  value="<%=currentEventParametersDate %>"
			  styleClass="formDateSized10"
					/>
<% 
	}
	else
	{  
 %>
<ncombo:DateTimeComponent name="dateOfEvent"
			  id="dateOfEvent"
			  formName="transferEventParametersForm"
			  styleClass="formDateSized10"
					/>
<% 
	} 
%> 
<bean:message key="page.dateFormat" />&nbsp;


			</td>
		</tr>

<!-- fromPosition -->		
		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formRequiredLabel">
				<label for="type">
					<bean:message key="transfereventparameters.fromposition"/> 
				</label>
			</td>
			<td class="formField">
				<html:hidden property="fromPositionDimensionOne" value="<%=posOne%>" />
				<html:hidden property="fromPositionDimensionTwo" value="<%=posTwo%>" />
				<html:hidden property="fromStorageContainerId" value="<%=storContId%>" />
				<%
					if(fromPositionData == null)
					{
				%>
				<html:text styleClass="formDateSized" size="35" styleId="fromPosition" property="fromPosition" readonly="true" />
				<%
					}
					else
					{
				%>
				<html:text styleClass="formDateSized" size="35" styleId="fromPosition" property="fromPosition" value="<%=fromPositionData%>" readonly="true" />
				<%
					}
				%>
			</td>
		</tr>

<!-- toPosition -->		
		<tr>
			<td class="formRequiredNotice" width="5">*</td>
			<td class="formRequiredLabel">
				<label for="type">
					<bean:message key="transfereventparameters.toposition"/> 
				</label>
			</td>
<%--			<td class="formField">
			<%
				boolean isReadOnly = true ;
					if(operation.equals("add"))
					{
						isReadOnly = false ;
					}
					
			
			%>
				&nbsp;<bean:message key="storageContainer.parentID" />
     			<html:text styleClass="formFieldSized3" maxlength="10"  styleId="storageContainer" property="storageContainer" readonly="<%=isReadOnly%>" />
     			&nbsp;<bean:message key="storageContainer.positionOne" />
     			<html:text styleClass="formFieldSized3" maxlength="10"  styleId="positionDimensionOne" property="positionDimensionOne" readonly="<%=isReadOnly%>" />
     			&nbsp;<bean:message key="storageContainer.positionTwo" />
     			<html:text styleClass="formFieldSized3" maxlength="10"  styleId="positionDimensionTwo" property="positionDimensionTwo" readonly="<%=isReadOnly%>" />

				<html:hidden property="positionInStorageContainer" />
				<%
					if(operation.equals("add"))
					{
				%>
					<html:button property="mapButton" styleClass="actionButton" styleId="Map"
								onclick="javascript:NewWindow('ShowFramedPage.do?pageOf=pageOfSpecimen','name','810','320','yes');return false" >
						<bean:message key="buttons.map"/>
					</html:button>
				<%
					}
				%>				
				&nbsp;
			</td>  --%>
		
			<%-- n-combo-box start --%>
			<%
				Map dataMap = (Map) request.getAttribute(Constants.AVAILABLE_CONTAINER_MAP);
									
				String[] labelNames = {"ID","Pos1","Pos2"};
				labelNames = Constants.STORAGE_CONTAINER_LABEL;
				String[] attrNames = { "storageContainer", "positionDimensionOne", "positionDimensionTwo"};
				String[] initValues = new String[3];
				List initValuesList = (List)request.getAttribute("initValues");
				if(initValuesList != null)
				{
					initValues = (String[])initValuesList.get(0);
				}
									
				String rowNumber = "1";
				String styClass = "formFieldSized5";
				String tdStyleClass = "customFormField";
				boolean disabled = true;
				String onChange = "onCustomListBoxChange(this)";
				
				boolean buttonDisabled = true;
				
				String className = (String) request.getAttribute(Constants.SPECIMEN_CLASS_NAME);
				if (className==null)
					className="";
				
				String collectionProtocolId =(String) request.getAttribute(Constants.COLLECTION_PROTOCOL_ID);
				if (collectionProtocolId==null)
					collectionProtocolId="";

				String url = "ShowFramedPage.do?pageOf=pageOfSpecimen&amp;containerStyleId=customListBox_1_0&amp;xDimStyleId=customListBox_1_1&amp;yDimStyleId=customListBox_1_2"
						+ "&" + Constants.CAN_HOLD_SPECIMEN_CLASS+"="+className
						+ "&" + Constants.CAN_HOLD_COLLECTION_PROTOCOL +"=" + collectionProtocolId;				
				String buttonOnClicked  = "javascript:NewWindow('"+url+"','name','810','320','yes');return false";
				String noOfEmptyCombos = "3";
			%>
			
			<%=ScriptGenerator.getJSForOutermostDataTable()%>
			<%=ScriptGenerator.getJSEquivalentFor(dataMap,rowNumber)%>
			
			<script language="JavaScript" type="text/javascript" src="jss/CustomListBox.js"></script>
			
			<td class="formField" colSpan="2">
						<ncombo:containermap dataMap="<%=dataMap%>" 
											attributeNames="<%=attrNames%>" 
											initialValues="<%=initValues%>"  
											styleClass = "<%=styClass%>" 
											tdStyleClass = "<%=tdStyleClass%>" 
											labelNames="<%=labelNames%>" 
											rowNumber="<%=rowNumber%>" 
											onChange = "<%=onChange%>"
											noOfEmptyCombos = "<%=noOfEmptyCombos%>"
											
											buttonName="mapButton" 
											value="Map"
											buttonStyleClass="actionButton"
											formLabelStyle="formLabelBorderless"
											buttonOnClick = "<%=buttonOnClicked%>" />			
			</td>
<%--		 n-combo-box end --%>
					
		</tr>




<!-- comments -->		
		<tr>
			<td class="formRequiredNotice" width="5">&nbsp;</td>
			<td class="formLabel">
				<label for="type">
					<bean:message key="eventparameters.comments"/> 
				</label>
			</td>
			<td class="formField">
				<html:textarea styleClass="formFieldSized"  styleId="comments" property="comments" />
			</td>
		</tr>

<!-- buttons -->
		<tr>
		  <td align="right" colspan="3">
			<!-- action buttons begins -->
			<%
        		String changeAction = "setFormAction('" + formName + "');";
			%> 
			<table cellpadding="4" cellspacing="0" border="0">
				<tr>
					<td>
						<html:submit styleClass="actionButton" value="Submit" onclick="<%=changeAction%>" />
					</td>
					<%-- td><html:reset styleClass="actionButton"/></td --%> 
				</tr>
			</table>
			<!-- action buttons end -->
			</td>
		</tr>

		</table>
		
	  </td>
	 </tr>

	 <!-- NEW TRANSFER_EVENT_PARAMETERS ends-->
	 
	 </html:form>
 </table>