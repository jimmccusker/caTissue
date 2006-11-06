<!-- 
	This JSP page is to create SpecimenArrayAliquots from/of Parent Specimen Array.
	Author : Jitendra Agrawal
	Date   : September 21, 2006
-->

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/nlevelcombo.tld" prefix="ncombo" %>
<%@ page import="edu.wustl.catissuecore.util.global.Constants"%>
<%@ page import="edu.wustl.catissuecore.actionForm.SpecimenArrayAliquotForm"%>
<%@ page import="edu.wustl.common.util.tag.ScriptGenerator" %>
<%@ page import="java.util.*"%>


<head>
	<script src="jss/Hashtable.js" type="text/javascript"></script>
	<script language="JavaScript" type="text/javascript" src="jss/CustomListBox.js"></script>
	
	<script language="JavaScript">
		function onSubmit()
		{
			var action = '<%=Constants.SPECIMEN_ARRAY_CREATE_ALIQUOT_ACTION%>';
			document.forms[0].action = action + "?pageOf=" + '<%=Constants.PAGEOF_SPECIMEN_ARRAY_CREATE_ALIQUOT%>' + "&buttonClicked=submit&menuSelected=20";
			document.forms[0].submit();
		}
		
		function onRadioButtonClick(element)
		{
			if(element.value == 1)
			{
				document.forms[0].parentSpecimenArrayLabel.disabled = false;
				document.forms[0].barcode.disabled = true;
			}
			else
			{
				document.forms[0].barcode.disabled = false;
				document.forms[0].parentSpecimenArrayLabel.disabled = true;
			}
		}
		
		function onCreate()
		{
			var action = '<%=Constants.SPECIMEN_ARRAY_CREATE_ALIQUOT_ACTION%>';
			document.forms[0].submittedFor.value = "ForwardTo";
			document.forms[0].action = action + "?pageOf=" + '<%=Constants.PAGEOF_SPECIMEN_ARRAY_CREATE_ALIQUOT%>' + "&operation=add&menuSelected=20&buttonClicked=create";
		    document.forms[0].submit();
		}		
		
	</script>
</head>

<html:messages id="messageKey" message="true" header="messages.header" footer="messages.footer">
	<%=messageKey%>
</html:messages>

<html:errors/>

<%
	String pageOf = (String)request.getAttribute(Constants.PAGEOF);
	String buttonKey = "";
	String exceedsMaxLimit = (String)request.getAttribute(Constants.EXCEEDS_MAX_LIMIT);
	if(Constants.PAGEOF_SPECIMEN_ARRAY_ALIQUOT.equals(pageOf))
	{
		buttonKey = "buttons.submit";
	}
	else if(Constants.PAGEOF_SPECIMEN_ARRAY_CREATE_ALIQUOT.equals(pageOf))
	{
		buttonKey = "buttons.resubmit";
	}
	System.out.println("pageOf---"+pageOf);
%>


<html:form action="<%=Constants.SPECIMEN_ARRAY_ALIQUOT_ACTION%>">
<table summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="660">
	<tr>
		<td>
			<table summary="" cellpadding="3" cellspacing="0" border="0" width="660">
			
				<tr>
					<html:hidden property="id"/>					
					<html:hidden property="submittedFor"/>
					<html:hidden property="specimenArrayId"/>
					<td class="formMessage" colspan="3">* indicates a required field</td>
				</tr>
				
				<tr>
					<td class="formTitle" height="20" colspan="7">
						<bean:message key="specimenArrayAliquots.createTitle"/>
					</td>
				</tr>
				
				<tr>
					<td class="formRequiredNoticeNoBottom">*
						<html:radio styleClass="" styleId="checkedButton" property="checkedButton" value="1" onclick="onRadioButtonClick(this)">							
						</html:radio>
					</td>
					<td class="formRequiredLabelLeftBorder" nowrap>
							<label for="parentSpecimenArrayLabel">
								<bean:message key="specimenArrayAliquots.parentLabel"/>
							</label>
					</td>
					<td class="formField">
						<logic:equal name="specimenArrayAliquotForm" property="checkedButton" value="1">
							<html:text styleClass="formFieldSized10"  maxlength="50"  size="30" styleId="parentSpecimenArrayLabel" property="parentSpecimenArrayLabel" disabled="false"/>
						</logic:equal>
						
						<logic:equal name="specimenArrayAliquotForm" property="checkedButton" value="2">
							<html:text styleClass="formFieldSized10"  maxlength="50"  size="30" styleId="parentSpecimenArrayLabel" property="parentSpecimenArrayLabel" disabled="true"/>
						</logic:equal>
					</td>
					
					<td class="formRequiredLabelBoth" width="5">*</td>
					<td class="formRequiredLabel">
						<bean:message key="specimenArrayAliquots.noOfAliquots"/>
					</td>
					<td class="formField">
						<html:text styleClass="formFieldSized5"  maxlength="50"  size="30" styleId="aliquotCount" property="aliquotCount"/>
					</td>
				</tr>
				
				<tr>
					<td class="formRequiredNotice"><span class="hideText">*</span>
						<html:radio styleClass="" styleId="checkedButton" property="checkedButton" value="2" onclick="onRadioButtonClick(this)">							
						</html:radio>
					</td>
					<td class="formRequiredLabel" >
							<label for="barcode">
								<bean:message key="specimenArrayAliquots.parentBarcode"/>
							</label>
					</td>
					<td class="formField">
						<logic:equal name="specimenArrayAliquotForm" property="checkedButton" value="1">
							<html:text styleClass="formFieldSized10"  maxlength="50"  size="30" styleId="barcode" property="barcode" disabled="true"/>
						</logic:equal>
						
						<logic:equal name="specimenArrayAliquotForm" property="checkedButton" value="2">
							<html:text styleClass="formFieldSized10"  maxlength="50"  size="30" styleId="barcode" property="barcode"/>
						</logic:equal>
					</td>
					<td class="formRequiredLabel" width="5" colspan="3">&nbsp;</td>
					
				</tr>
				
				<tr>
					<td colspan="5">
						&nbsp;
					</td>
					<td align="right">
						<html:button styleClass="actionButton" property="submitPage" onclick="onSubmit()">
							<bean:message key="<%=buttonKey%>"/>
						</html:button>
					</td>
				</tr>
								
			</table>			
		</td>
	</tr>
	
	<%	
	SpecimenArrayAliquotForm form = (SpecimenArrayAliquotForm)request.getAttribute("specimenArrayAliquotForm");
	if(!Constants.PAGEOF_SPECIMEN_ARRAY_ALIQUOT.equals(pageOf))
	{
	%>
	
	<tr>
		<td>
			<table summary="" cellpadding="3" cellspacing="0" border="0" width="660">
				<tr>						
					<td class="formTitle" height="20" colspan="3">
						<bean:message key="specimenArrayAliquots.title"/>
					</td>
				</tr>
				
				<tr>
					<td class="formRequiredNotice" width="5">*</td>
					<td class="formRequiredLabel">
						<label for="specimenArrayType">
							<bean:message key="specimenArrayAliquots.specimenArrayType"/> 
						</label>
					</td>
					<td class="formField">
						<html:text styleClass="formFieldSized10"  maxlength="50"  size="30" styleId="specimenArrayType" property="specimenArrayType" readonly="true"/>						
					</td>
				</tr>
				
				<tr>
					<td class="formRequiredNotice" width="5">*</td>
					<td class="formRequiredLabel">
						<label for="specimenClass">
							<bean:message key="specimenArrayAliquots.specimenClass"/> 
						</label>
					</td>
					<td class="formField">
						<html:text styleClass="formFieldSized10"  maxlength="50"  size="30" styleId="specimenClass" property="specimenClass" readonly="true"/>
					</td>
				</tr>
				
				<tr>
					<td class="formRequiredNotice" width="5">*</td>
					<td class="formRequiredLabel">
						<label for="specimenType">
							<bean:message key="specimenArrayAliquots.specimenType"/> 
						</label>
					</td>
					<td class="formField">						
						<html:select property="specimenTypes" styleClass="formFieldVerySmallSized" styleId="state" size="4" multiple="true" disabled="true">
							<html:options collection="<%=Constants.SPECIMEN_TYPE_LIST%>" labelProperty="name" property="value"/>
						</html:select>
					</td>
				</tr>
			</table>
			
			<table summary="" cellpadding="3" cellspacing="0" border="0" width="660">
				<tr>
					<td class="formLeftSubTableTitle" width="5">
				     	#
				    </td>
				    <td class="formRightSubTableTitle">*
						<bean:message key="specimenArrayAliquots.label"/>
					</td>					
					<td class="formRightSubTableTitle">&nbsp;
						<bean:message key="specimenArrayAliquots.barcode"/>
					</td>
					<td class="formRightSubTableTitle">*
						<bean:message key="specimenArrayAliquots.location"/>
					</td>
				</tr>
				
				<%=ScriptGenerator.getJSForOutermostDataTable()%>
	
				<%
					Map aliquotMap = new HashMap();
					int counter=0;

					if(form != null)
					{
						counter = Integer.parseInt(form.getAliquotCount());
						aliquotMap = form.getSpecimenArrayAliquotMap();
					}

					/* Retrieving a map of available containers */
					Map dataMap = (Map) request.getAttribute(Constants.AVAILABLE_CONTAINER_MAP);
					String[] labelNames = Constants.STORAGE_CONTAINER_LABEL;

					for(int i=1;i<=counter;i++)
					{
						String labelKey = "value(SpecimenArray:" + i + "_label)";						
						String barKey = "value(SpecimenArray:" + i + "_barcode)";						
						
						String containerKey = "value(SpecimenArray:" + i + "_StorageContainer_id)";
						String pos1Key = "value(SpecimenArray:" + i + "_positionDimensionOne)";
						String pos2Key = "value(SpecimenArray:" + i + "_positionDimensionTwo)";						
						
						//Preparing data for custom tag
						String[] attrNames = {containerKey, pos1Key, pos2Key};

						String[] initValues = new String[3];
						initValues[0] = (String)aliquotMap.get("SpecimenArray:" + i + "_StorageContainer_id");
						initValues[1] = (String)aliquotMap.get("SpecimenArray:" + i + "_positionDimensionOne");
						initValues[2] = (String)aliquotMap.get("SpecimenArray:" + i + "_positionDimensionTwo");

						String rowNumber = String.valueOf(i);
						String noOfEmptyCombos = "3";
						String styClass = "formFieldSized5";
						String tdStyleClass = "customFormField";
						String onChange = "onCustomListBoxChange(this)";

						String containerStyleId = "customListBox_" + rowNumber + "_0";
						String pos1StyleId = "customListBox_" + rowNumber + "_1";
						String pos2StyleId = "customListBox_" + rowNumber + "_2";

						String buttonOnClicked = "javascript:NewWindow('ShowFramedPage.do?pageOf=pageOfSpecimenArray&amp;containerStyleId=" + containerStyleId + "&amp;xDimStyleId=" + pos1StyleId + "&amp;yDimStyleId=" + pos2StyleId + "','name','810','320','yes');return false";
				%>
				<%=ScriptGenerator.getJSEquivalentFor(dataMap,rowNumber)%>
					<tr>
						<td class="formSerialNumberField" width="5">
					     	<%=i%>.
					    </td>
					    <td class="formField" nowrap>
							<html:text styleClass="formFieldSized10"  maxlength="50"  size="30" styleId="label" property="<%=labelKey%>" disabled="false"/>
						</td>
						<td class="formField">
							<html:text styleClass="formFieldSized10"  maxlength="50"  size="30" styleId="barcodes" property="<%=barKey%>" disabled="false"/>
						</td>
						<td class="formField" nowrap>
							
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
												 buttonOnClick = "<%=buttonOnClicked%>"
												 formLabelStyle="formLabelBorderless"
												 buttonStyleClass="actionButton" />
						</td>
						
					</tr>
					<logic:equal name="exceedsMaxLimit" value="true">
					<tr>
						<td>
							<bean:message key="container.maxView"/>
						</td>
					</tr>
				</logic:equal>
				<%
					} //For
				%>	
				
				<tr>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td colspan="4" align="right">
							<html:button styleClass="actionButton" property="submitButton" onclick="onCreate()">
								<bean:message key="buttons.create"/>
							</html:button>
					</td>				  
				</tr>				
			</table>	
		</td>
	</tr>	
	<%	
	}
	%>	
</table>
</html:form>
