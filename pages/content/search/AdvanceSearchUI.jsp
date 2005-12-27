<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="edu.wustl.catissuecore.vo.SearchFieldData"%>
<%@ page import="edu.wustl.catissuecore.vo.AdvanceSearchUI"%>
<%@ page import="edu.wustl.catissuecore.util.global.Constants"%>
<%@ page import="edu.wustl.catissuecore.util.SearchUtil"%>

<%
	AdvanceSearchUI advSearch = (AdvanceSearchUI)request.getAttribute("AdvanceSearchUI");
	SearchFieldData[] searchFieldData = advSearch.getSearchFieldData();
	int div = 0;
	String tempDiv = "overDiv";
	String overDiv = tempDiv;
	String actionName = "";
	String iconText = advSearch.getIconAltText();
	
	if(iconText.equals(Constants.PARTICIPANT))
	{
		actionName = "AdvanceSearchP.do";
	}
	else if(iconText.equals(Constants.COLLECTION_PROTOCOL))
	{
		actionName = "AdvanceSearchCP.do";
	}
	else if(iconText.equals(Constants.SPECIMEN_COLLECTION_GROUP))
	{
		actionName = "AdvanceSearchSCG.do";
	}

	System.out.println("formname--"+actionName);
	
	
%>

<head>
	<script src="jss/script.js" type="text/javascript"></script>
	<script src="jss/AdvancedSearchScripts.js" type="text/javascript"></script>
</head>

<html:errors />
<html:form action="<%=actionName%>">
<table summary="" cellpadding="5" cellspacing="0" border="0" width="600">
<tr>
	<td><html:hidden property="objectName" value="<%=advSearch.getIconAltText()%>"/></td>
	<td><html:hidden property="selectedNode" /></td>
	<td><html:hidden property="itemNodeId" /></td>
</tr>
<!--  MAIN TITLE ROW -->
<tr>
	<td class="formTitle" height="25" nowrap>
	    &nbsp;<img src="<%=advSearch.getIconSrc()%>" alt="<%=advSearch.getIconAltText()%>" /> &nbsp;
	    <bean:message key="<%=advSearch.getTitleKey()%>"/>
	</td>
	<td class="formTitle" nowrap align="right" colspan="2">
		<html:submit property="addRule" styleClass="actionButton" >
			<bean:message key="buttons.addRule"/>
		</html:submit>
		
		<%--html:button property="search" styleClass="actionButton" onclick="">
			<bean:message key="buttons.search"/>
		</html:button--%>
		&nbsp;&nbsp;
		<html:button property="resetQuery" styleClass="actionButton" onclick="">
			<bean:message key="buttons.resetQuery"/>
		</html:button>
	</td>
</tr>

<% for(int i = 0; i < searchFieldData.length; i++) 
	{
%>
		<tr>
			<td class="formSerialNumberField" nowrap>
		 		<label for="<%=searchFieldData[i].getOprationField().getId()%>">
		 			<b><bean:message key="<%=searchFieldData[i].getLabelKey()%>"/>
		 		</label>
			</td>
			
			<td class="formField">
				<html:select property="<%=searchFieldData[i].getOprationField().getName()%>" styleClass="formFieldSized10" styleId="<%=searchFieldData[i].getOprationField().getId()%>" size="1" onchange="<%= searchFieldData[i].getFunctionName()%>">
					<html:options collection="<%=searchFieldData[i].getOprationField().getDataListName()%>" labelProperty="name" property="value"/>
				</html:select>
			</td>
		
		<%
		if((searchFieldData[i].getDataType()).equals(SearchUtil.STRING))
		{
			if((searchFieldData[i].getValueField().getDataListName()).equals(""))
			{
		%>
				<td class="formField">
					<html:text styleClass="formFieldSized10" styleId="<%=searchFieldData[i].getValueField().getId()%>" property="<%=searchFieldData[i].getValueField().getName()%>" disabled="<%=searchFieldData[i].getValueField().isDisabled()%>"/>
				</td>
		<%
			}
			else
			{	
		%>
			<td class="formField">
				<html:select property="<%=searchFieldData[i].getValueField().getName()%>" styleClass="formFieldSized10" styleId="<%=searchFieldData[i].getValueField().getId()%>" size="1" disabled="<%=searchFieldData[i].getValueField().isDisabled()%>">
					<html:options collection="<%=searchFieldData[i].getValueField().getDataListName()%>" labelProperty="name" property="value"/>
				</html:select>
			</td>
		<%
			}
		}
		else if((searchFieldData[i].getDataType()).equals(SearchUtil.DATE))
		{
			String temp = searchFieldData[i].getValueField().getName();
			String hlimitName = temp.replaceFirst("[)]",":HLIMIT)");
			 
		%>
			<td class="formField" nowrap>
				<div id="<%=overDiv%>" style="position:absolute; visibility:hidden; z-index:1000;"></div>
				<html:text styleClass="formDateSized10" size="10" styleId="<%=searchFieldData[i].getValueField().getId() + 1%>" property="<%=searchFieldData[i].getValueField().getName()%>" disabled="<%=searchFieldData[i].getValueField().isDisabled()%>"/>
							 &nbsp;
				<a href="javascript:onDate('<%=searchFieldData[i].getOprationField().getId() %>','<%=(advSearch.getFormName() +"." + searchFieldData[i].getValueField().getId() + 1)%>',false);">
					<img src="images\calendar.gif" width=24 height=22 border=0></a>&nbsp;Thru&nbsp;
				<html:text styleClass="formDateSized10" size="10" styleId="<%=searchFieldData[i].getValueField().getId() + 2%>" property="<%=hlimitName%>" disabled="<%=searchFieldData[i].getValueField().isDisabled()%>"/>
							 &nbsp;
				<a href="javascript:onDate('<%=searchFieldData[i].getOprationField().getId()%>','<%=(advSearch.getFormName() +"." + searchFieldData[i].getValueField().getId() + 2)%>',true);">
					<img src="images\calendar.gif" width=24 height=22 border=0>
				</a>
			</td>
		<%
			//For different id of div tag
			div++;
			overDiv = tempDiv + div;
		}
		else if((searchFieldData[i].getDataType()).equals(SearchUtil.NUMERIC))
		{
		%>
			<td class="formField">
				<html:text styleClass="formFieldSized10" styleId="<%=searchFieldData[i].getValueField().getId() + 1%>" property="<%=searchFieldData[i].getValueField().getName() + 1%>" disabled="<%=searchFieldData[i].getValueField().isDisabled()%>"/> 
								&nbsp;To&nbsp;
				<html:text styleClass="formFieldSized10" styleId="<%=searchFieldData[i].getValueField().getId() + 2%>" property="<%=searchFieldData[i].getValueField().getName() + 2%>" disabled="<%=searchFieldData[i].getValueField().isDisabled()%>"/> 
				<bean:message key="<%=searchFieldData[i].getUnitFieldKey()%>"/>
			</td>
		<%
		}
	}
	%>
	
</tr>

<tr>
	<td colspan="3">&nbsp;</td>
</tr>


<!-- TENTH ROW -->
<tr>
	<td colspan="2">&nbsp</td>
	<td nowrap align="right">
		<html:submit property="addRule" styleClass="actionButton" >
			<bean:message key="buttons.addRule"/>
		</html:submit>
		
		<%--html:button property="search" styleClass="actionButton" onclick="">
			<bean:message key="buttons.search"/>
		</html:button--%>
		&nbsp;&nbsp;
		<html:button property="resetQuery" styleClass="actionButton" onclick="">
			<bean:message key="buttons.resetQuery"/>
		</html:button>
	</td>
</tr>

</table>
</html:form>