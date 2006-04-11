<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="edu.wustl.catissuecore.actionForm.SpecimenCollectionGroupForm"%>
<%@ page import="edu.wustl.catissuecore.util.global.Constants"%>

<%@ include file="/pages/content/common/BioSpecimenCommonCode.jsp" %>
<script src="jss/script.js" type="text/javascript"></script>

<% 
		String operation = (String)request.getAttribute(Constants.OPERATION);
		String reqPath = (String)request.getAttribute(Constants.REQ_PATH);
		String pageOf = (String)request.getAttribute(Constants.PAGEOF);
		String appendingPath = "/SpecimenCollectionGroup.do?operation=add&pageOf=pageOfSpecimenCollectionGroup";
		if (reqPath != null)
			appendingPath = reqPath + "|/SpecimenCollectionGroup.do?operation=add&pageOf=pageOfSpecimenCollectionGroup";
	
	   		Object obj = request.getAttribute("specimenCollectionGroupForm");
			SpecimenCollectionGroupForm form =null;
	
			if(obj != null && obj instanceof SpecimenCollectionGroupForm)
			{
				form = (SpecimenCollectionGroupForm)obj;
			}	
	
	   	if(!operation.equals("add") )
	   	{
	   		obj = request.getAttribute("specimenCollectionGroupForm");
	   		
			if(obj != null && obj instanceof SpecimenCollectionGroupForm)
			{
				form = (SpecimenCollectionGroupForm)obj;
		   		appendingPath = "/SpecimenCollectionGroupSearch.do?operation=search&pageOf=pageOfSpecimenCollectionGroup&systemIdentifier="+form.getSystemIdentifier() ;
		   		int checkedButton1 = form.getCheckedButton();
		   	}
	   	}
			
		String formName, pageView = operation ,editViewButton="buttons."+Constants.EDIT;
		boolean readOnlyValue=false,readOnlyForAll=false;

		if(operation.equals(Constants.EDIT))
		{
			editViewButton="buttons."+Constants.VIEW;
			formName = Constants.SPECIMEN_COLLECTION_GROUP_EDIT_ACTION;
			readOnlyValue=true;
			if(pageOf.equals(Constants.QUERY))
				formName = Constants.QUERY_SPECIMEN_COLLECTION_GROUP_EDIT_ACTION + "?pageOf="+pageOf;

		}
		else
		{
			formName = Constants.SPECIMEN_COLLECTION_GROUP_ADD_ACTION;
			readOnlyValue=false;
		}

%>


<head>
	<script language="JavaScript" type="text/javascript" src="jss/javaScript.js"></script>
     <script language="JavaScript">
     
    	function onRadioButtonClick(element)
		{
			if(element.value == 1)
			{
				document.forms[0].participantId.disabled = false;
				document.forms[0].protocolParticipantIdentifier.disabled = true;
				document.forms[0].participantsMedicalIdentifierId.disabled = false;
			}
			else
			{
				document.forms[0].participantId.disabled = true;
				document.forms[0].protocolParticipantIdentifier.disabled = false;

				
				//disable Medical Record number field.
				document.forms[0].participantsMedicalIdentifierId.disabled = true;
			}
		} 
		
        function onChangeEvent(element)
		{
			var action = "SpecimenCollectionGroup.do?operation=<%=operation%>&pageOf=pageOfSpecimenCollectionGroup&isOnChange=true";
			document.forms[0].action = action;
			document.forms[0].submit();
		}
		
	</script>
</head>

<html:errors />
<html:messages id="messageKey" message="true" header="messages.header" footer="messages.footer">
	<%=messageKey%>
</html:messages>

<html:form action="<%=formName%>">

	<table summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="600">
		<!-- NEW SPECIMEN COLLECTION GROUP REGISTRATION BEGINS-->

	    <tr><td>
			<table summary="" cellpadding="3" cellspacing="0" border="0" width="100%">
				 <tr>
					<td><html:hidden property="<%=Constants.OPERATION%>" value="<%=operation%>"/></td>
				 </tr>
				 
				 <tr>
					<td><html:hidden property="systemIdentifier"/></td>
					<td><html:hidden property="onSubmit"/></td>
					<td><html:hidden property="redirectTo" value="<%=reqPath%>"/></td>
				 </tr>

				 <tr>
				 	<td class="formMessage" colspan="4">* indicates a required field</td>
				 </tr>
				 
					<tr>
					<td class="formTitle" height="20" colspan="4">
						<%String title = "specimenCollectionGroup."+pageView+".title";%>
							<bean:message key="<%=title%>"/>
						<%
						if(pageView.equals("edit"))
						{
							%>
								&nbsp;<bean:message key="for.identifier"/>&nbsp;<bean:write name="specimenCollectionGroupForm" property="systemIdentifier" />
						    <%
						}
						%>
					</td>
				</tr>

				 
				 <!--Collection Protocol -->
				 <tr>
			     	<td class="formRequiredNotice" width="5">*</td>
				    <td class="formRequiredLabel">
						<label for="collectionProtocolId">
							<bean:message key="specimenCollectionGroup.protocolTitle"/>
						</label>
					</td>
					
					<td class="formField">
				     	<html:select property="collectionProtocolId" styleClass="formFieldSized" styleId="collectionProtocolId" size="1" disabled="<%=readOnlyForAll%>" onchange="onChangeEvent(this)">
							<html:options collection="<%=Constants.PROTOCOL_LIST%>" labelProperty="name" property="value"/>
						</html:select>
		        	</td>
				 </tr>

				 <tr>
 			     	<td class="formRequiredNotice" width="5">*</td>
				    <td class="formRequiredLabel">
						<label for="siteId">
							<bean:message key="specimenCollectionGroup.site"/>
						</label>
					</td>
					
					<td class="formField">
				     	<html:select property="siteId" styleClass="formFieldSized" styleId="siteId" size="1" disabled="<%=readOnlyForAll%>">
  							<html:options collection="<%=Constants.SITELIST%>" labelProperty="name" property="value"/>
						</html:select>
		        	</td>
				 </tr>
				 
				 <tr>
				 	<td class="formRequiredNoticeNoBottom">
				     	<html:radio styleClass=""  property="checkedButton" value="1" onclick="onRadioButtonClick(this)">
  				     	    <label for="participantId">
								<%--<bean:message key="specimenCollectionGroup.collectedByParticipant" />--%>
							</label>
				     	</html:radio>
 				    </td>
 				    <td class="formRequiredLabelRightBorder"  width="186">
 				    	<label for="participantId">
					        <bean:message key="specimenCollectionGroup.collectedByParticipant" />
						</label>
  					</td>
  					<td class="formField">
  						<logic:equal name="specimenCollectionGroupForm" property="checkedButton" value="1">
				     	     <html:select property="participantId" styleClass="formFieldSized" styleId="ParticipantId" size="1" onchange="onChangeEvent(this)">
                         	     <html:options collection="<%=Constants.PARTICIPANT_LIST%>" labelProperty="name" property="value"/>				     	
  						     </html:select>
  						</logic:equal>     
						<logic:equal name="specimenCollectionGroupForm" property="checkedButton" value="2">
				     	     <html:select property="participantId" styleClass="formFieldSized" styleId="ParticipantId" size="1" onchange="onChangeEvent(this)" disabled="true">
                         	     <html:options collection="<%=Constants.PARTICIPANT_LIST%>" labelProperty="name" property="value"/>				     	
  						     </html:select>
  						</logic:equal>
						<%
							//String url = "/Participant.do?operation=add&pageOf=pageOfParticipant";
							String url = "/CollectionProtocolRegistration.do?operation=add&pageOf=pageOfCollectionProtocolRegistration";
							String onClickPath = "changeUrl(this,'"+appendingPath+"')";
						%>
						<logic:notEqual name="<%=Constants.PAGEOF%>" value="<%=Constants.QUERY%>">
			  		    <html:link page="<%=url%>" styleId="newParticipant" onclick="<%=onClickPath%>">
	 						<bean:message key="buttons.addNew" />
 						</html:link>
 						</logic:notEqual>
					</td>
  					
				 </tr>
				 
				 <tr>
				    <td class="formRequiredNotice">
				       	<html:radio styleClass="" property="checkedButton" value="2" onclick="onRadioButtonClick(this)">
  				       	    <label for="protocolParticipantIdentifier">
								<%--<bean:message key="specimenCollectionGroup.collectedByProtocolParticipantNumber" />--%>
							</label>
				     	</html:radio>
				    </td>
				    <td class="formRequiredLabel"  width="186">
						<label for="protocolParticipantIdentifier">
							<bean:message key="specimenCollectionGroup.collectedByProtocolParticipantNumber" />
						</label>
					</td>
					
  			        <td class="formField">
  					<%-- LOGIC TAG FOR PARTICPANT NUMBER --%> 												
                        <logic:equal name="specimenCollectionGroupForm" property="checkedButton" value="1">						
   						 	<html:select property="protocolParticipantIdentifier" styleClass="formFieldSized" styleId="protocolParticipantIdentifier" size="1" disabled="true">
                         		<html:options collection="<%=Constants.PROTOCOL_PARTICIPANT_NUMBER_LIST%>" labelProperty="name" property="value"/>				     					     	
							</html:select>
 						</logic:equal>
 						
 						<logic:equal name="specimenCollectionGroupForm" property="checkedButton" value="2">						
   						 	<html:select property="protocolParticipantIdentifier" styleClass="formFieldSized" styleId="protocolParticipantIdentifier" size="1" >
                         		<html:options collection="<%=Constants.PROTOCOL_PARTICIPANT_NUMBER_LIST%>" labelProperty="name" property="value"/>				     					     	
							</html:select>
 						</logic:equal>

						<%
							String url1 = "/CollectionProtocolRegistration.do?operation=add&pageOf=pageOfCollectionProtocolRegistration";
						%>
						<logic:notEqual name="<%=Constants.PAGEOF%>" value="<%=Constants.QUERY%>">
 						<html:link page="<%=url1%>" styleId="newParticpantRegistration" onclick="<%=onClickPath%>">
		 						<bean:message key="buttons.addNew" />
	 					</html:link>
	 					</logic:notEqual>
		        	</td>
				 </tr>
				 
				 <tr>
				 	<td class="formRequiredNotice" width="5">*</td>
				    
				    <td class="formRequiredLabel">
						<label for="collectionProtocolEventId">
							<bean:message key="specimenCollectionGroup.studyCalendarEventPoint"/>
						</label>
					</td>
				    <td class="formField">
				     	<html:select property="collectionProtocolEventId" styleClass="formFieldSized" styleId="collectionProtocolEventId" size="1" onchange="onChangeEvent(this)" >
                         	<html:options collection="<%=Constants.STUDY_CALENDAR_EVENT_POINT_LIST%>" labelProperty="name" property="value"/>				     					     					     	
						</html:select>&nbsp;
						<bean:message key="collectionprotocol.studycalendarcomment"/>
		        	</td>
				 </tr>
				 
				 <tr>
				     <td class="formRequiredNotice" width="5">*</td>
				     <td class="formRequiredLabel">
						<label for="clinicalDiagnosis">
							<bean:message key="specimenCollectionGroup.clinicalDiagnosis"/>
						</label>
					 </td>
				     <td class="formField">
						<html:select property="clinicalDiagnosis" styleClass="formFieldSized" styleId="clinicalDiagnosis" size="1" >
							<html:options collection="<%=Constants.CLINICAL_DIAGNOSIS_LIST%>" labelProperty="name" property="value"/>				     					     					     	
						</html:select>
		        	 </td>
				 </tr>
				 
				 <tr>
				     <td class="formRequiredNotice" width="5">*</td>
				     <td class="formRequiredLabel">
						<label for="clinicalStatus">
							<bean:message key="specimenCollectionGroup.clinicalStatus"/>
						</label>
					 </td>
					 
				     <td class="formField">
				     	<html:select property="clinicalStatus" styleClass="formFieldSized" styleId="clinicalStatus" size="1" disabled="<%=readOnlyForAll%>">
							<html:options collection="<%=Constants.CLINICAL_STATUS_LIST%>" labelProperty="name" property="value"/>		
						</html:select>
		        	  </td>
				 </tr>
				 
				 <tr>
			     	<td class="formRequiredNotice" width="5">&nbsp;</td>
				    <td class="formLabel">
						<label for="participantsMedicalIdentifierId">
							<bean:message key="specimenCollectionGroup.medicalRecordNumber"/>
						</label>
					</td>
                    <td class="formField">
   						<logic:equal name="specimenCollectionGroupForm" property="checkedButton" value="1">
				     		<html:select property="participantsMedicalIdentifierId" styleClass="formFieldSized" styleId="participantsMedicalIdentifierId" size="1" disabled="<%=readOnlyForAll%>">
                         		<html:options collection="<%=Constants.PARTICIPANT_MEDICAL_IDNETIFIER_LIST%>" labelProperty="name" property="value"/>
							</html:select>
						</logic:equal>
						<logic:equal name="specimenCollectionGroupForm" property="checkedButton" value="2">
					     	<html:select property="participantsMedicalIdentifierId" styleClass="formFieldSized" styleId="participantsMedicalIdentifierId" size="1" disabled="true">
                	         	<html:options collection="<%=Constants.PARTICIPANT_MEDICAL_IDNETIFIER_LIST%>" labelProperty="name" property="value"/>
							</html:select>
						</logic:equal>
		        	</td>					
				 </tr>
				 
				 <tr>
					<td class="formRequiredNotice" width="5">&nbsp;</td>
					<td class="formLabel">
						<label for="surgicalPathologyNumber">
							<bean:message key="specimenCollectionGroup.surgicalPathologyNumber"/>
						</label>
					</td>
				    <td class="formField" noWrap="true">
				     	<html:text styleClass="formFieldSized" size="30"  maxlength="50"  styleId="surgicalPathologyNumber" property="surgicalPathologyNumber" readonly="<%=readOnlyForAll%>"/>
					     	&nbsp;
							<html:submit styleClass="actionButton" disabled="true">
								<bean:message key="buttons.getPathologyReport"/>
							</html:submit>
				    </td>
				 </tr>

				<!-- activitystatus -->	
				<logic:equal name="<%=Constants.OPERATION%>" value="<%=Constants.EDIT%>">
				<tr>
					<td class="formRequiredNotice" width="5">*</td>
					<td class="formRequiredLabel" >
						<label for="activityStatus">
							<bean:message key="site.activityStatus" />
						</label>
					</td>
					<td class="formField">
						<html:select property="activityStatus" styleClass="formFieldSized10" styleId="activityStatus" size="1" onchange="<%=strCheckStatus%>">
							<html:options name="<%=Constants.ACTIVITYSTATUSLIST%>" labelName="<%=Constants.ACTIVITYSTATUSLIST%>" />
						</html:select>
					</td>
				</tr>
				</logic:equal>
					
				 		<tr>
				  		<td align="right" colspan="3">
							<%
								String changeAction = "setFormAction('"+formName+"')";
				 			%>
							<!-- action buttons begins -->
							<table cellpadding="4" cellspacing="0" border="0">
								<tr>
								<logic:notEqual name="<%=Constants.PAGEOF%>" value="<%=Constants.QUERY%>">
									<td>
										<table>
											<tr>
												<td class="formFieldNoBorders" nowrap>
													<label for="proceedWith">
														<bean:message key="proceedWith"/>
													</label>
												</td>
												<td nowrap class="formFieldNoBorders">
													<html:radio styleClass="" property="forwardTo" value="<%=Constants.SPECIMEN_COLLECTION_GROUP_FORWARD_TO_LIST[0][1]%>" >
						  				     	    <label for="<%=Constants.SPECIMEN_COLLECTION_GROUP_FORWARD_TO_LIST[0][0]%>">
														<%=Constants.SPECIMEN_COLLECTION_GROUP_FORWARD_TO_LIST[0][0]%>
													</label>
											     	</html:radio>
												</td>
												<td nowrap class="formFieldNoBorders">
													<html:radio styleClass=""  property="forwardTo" value="<%=Constants.SPECIMEN_COLLECTION_GROUP_FORWARD_TO_LIST[1][1]%>">
						  				     	    <label for="<%=Constants.SPECIMEN_COLLECTION_GROUP_FORWARD_TO_LIST[1][0]%>">
														<%=Constants.SPECIMEN_COLLECTION_GROUP_FORWARD_TO_LIST[1][0]%>
													</label>
											     	</html:radio>
												</td>		
											</tr>
										</table>
									</td>					
									</logic:notEqual>
						   			<!-- td>
						   				<html:submit styleClass="actionButton" onclick="<%=changeAction%>">
						   					<bean:message key="buttons.submit"/>
						   				</html:submit>
						   			</td-->
						   			<td>
						   			<%
						   				String action = "confirmDisable('" + formName +"',document.forms[0].activityStatus)";
						   			%>
										<html:button styleClass="actionButton" property="submitPage" onclick="<%=action%>">
											<bean:message key="buttons.submit"/>
										</html:button>
									</td>
									
									<td>
										<html:reset styleClass="actionButton">
											<bean:message key="buttons.reset"/>
										</html:reset>
									</td> 
								</tr>
							</table>
							<!-- action buttons end -->
				  		</td>
				 	</tr>
				 
			</table>
		</td></tr>
		<!-- NEW SPECIMEN COLLECTION GROUP REGISTRATION ENDS-->
	</table>
</html:form>		