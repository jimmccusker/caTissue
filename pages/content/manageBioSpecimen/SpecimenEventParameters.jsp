<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%@ page import="java.util.List"%>
<%@ page import="edu.wustl.catissuecore.util.global.Constants"%>

<link href="runtime/styles/xp/grid.css" rel="stylesheet" type="text/css" ></link>
<script src="runtime/lib/grid.js"></script>
<script src="runtime/formats/date.js"></script>
<script src="runtime/formats/string.js"></script>
<script src="runtime/formats/number.js"></script>

<head>
<style>
.active-column-1 {width:200px}
</style>
<%
	String[] columnList = Constants.EVENT_PARAMETERS_COLUMNS;
	String title = null;
	List dataList = (List) request.getAttribute(Constants.SPREADSHEET_DATA_LIST);
	String pageOf = (String)request.getAttribute(Constants.PAGEOF);
	String specimenIdentifier = (String)request.getAttribute(Constants.SPECIMEN_ID);
	if(specimenIdentifier == null || specimenIdentifier.equals("0"))
		specimenIdentifier = (String)request.getParameter(Constants.SPECIMEN_ID);
if(dataList!=null && dataList.size() != 0)
{
%>

<script>
var myData = [<%int xx;%><%for (xx=0;xx<(dataList.size()-1);xx++){%>
<%
	List row = (List)dataList.get(xx);
  	int j;
%>
[<%for (j=0;j < (row.size()-1);j++){%>"<%=row.get(j)%>",<%}%>"<%=row.get(j)%>"],<%}%>
<%
	List row = (List)dataList.get(xx);
  	int j;
%>
[<%for (j=0;j < (row.size()-1);j++){%>"<%=row.get(j)%>",<%}%>"<%=row.get(j)%>"]
];

var columns = [<%int k;%><%for (k=0;k < (columnList.length-1);k++){%>"<%=columnList[k]%>",<%}%>"<%=columnList[k]%>"];
</script>

<%}%>

<script language="JavaScript">

	function onParameterChange(element)
	{
		var action = "";
		var iFrame = document.getElementById("newEventFrame");
		//var addNew = document.getElementById("sepAdd");
		
		if(element.value == "Cell Specimen Review")
			action = "CellSpecimenReviewParameters.do?operation=add&pageOf=pageOfCellSpecimenReviewParameters";
		else if(element.value == "Check In Check Out")
			action = "CheckInCheckOutEventParameters.do?operation=add&pageOf=pageOfCheckInCheckOutEventParameters";
		else if(element.value == "Collection")
			action = "CollectionEventParameters.do?operation=add&pageOf=pageOfCollectionEventParameters";
		else if(element.value == "Disposal")
			action = "DisposalEventParameters.do?operation=add&pageOf=pageOfDisposalEventParameters";
		else if(element.value == "Embedded")
			action = "EmbeddedEventParameters.do?operation=add&pageOf=pageOfEmbeddedEventParameters";
		else if(element.value == "Fixed")
			action = "FixedEventParameters.do?operation=add&pageOf=pageOfFixedEventParameters";
		else if(element.value == "Fluid Specimen Review")
			action = "FluidSpecimenReviewEventParameters.do?operation=add&pageOf=pageOfFluidSpecimenReviewParameters";
		else if(element.value == "Frozen")
			action = "FrozenEventParameters.do?operation=add&pageOf=pageOfFrozenEventParameters";
		else if(element.value == "Molecular Specimen Review")
			action = "MolecularSpecimenReviewParameters.do?operation=add&pageOf=pageOfMolecularSpecimenReviewParameters";
		else if(element.value == "Procedure")
			action = "ProcedureEventParameters.do?operation=add&pageOf=pageOfProcedureEventParameters";
		else if(element.value == "Received")
			action = "ReceivedEventParameters.do?operation=add&pageOf=pageOfReceivedEventParameters";
		else if(element.value == "Spun")
			action = "SpunEventParameters.do?operation=add&pageOf=pageOfSpunEventParameters";
		else if(element.value == "Thaw")
			action = "ThawEventParameters.do?operation=add&pageOf=pageOfThawEventParameters";
		else if(element.value == "Tissue Specimen Review")
			action = "TissueSpecimenReviewEventParameters.do?operation=add&pageOf=pageOfTissueSpecimenReviewParameters";
		else if(element.value == "Transfer")
		{
			action = "TransferEventParameters.do?operation=add&pageOf=pageOfTransferEventParameters";			
		}	
		
		var specimenIdentifier = "<%=specimenIdentifier%>";
		action = action + "&specimenId=" + specimenIdentifier;
		//addNew.href = action;
		iFrame.src = action;
		
		if(element.value == "<%=Constants.SELECT_OPTION%>")
		{
			iFrame.src = "";
			//addNew.href = "#";
			//addNew.target="_parent";
		}
	}
</script>

</head>

<html:errors />
<html:messages id="messageKey" message="true" header="messages.header" footer="messages.footer">
	<%=messageKey%>
</html:messages>

<html:form action="<%=Constants.SPECIMEN_ADD_ACTION%>">
<table summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="500">
<tr>
	<td>		
		&nbsp;
	</td>
</tr>

<tr>
 <td>
  	 <table summary="" cellpadding="3" cellspacing="0" border="0" width="500">

<%
	if(dataList!=null && dataList.size() != 0)
	{
		title = "Specimen Event Parameters List for Identifier : " + specimenIdentifier;
%>

   	 	<tr>
			<td class="formTitle" height="20">
				<%--bean:message key="specimenEventParameters.list"/--%>
				<%=title%>
			</td>
		</tr>
		
   	 	<tr>
			<td>
				<div STYLE="overflow: auto; width:550; height: 200; padding:0px; margin: 0px; border: 1px solid" id="eventGrid">
				<script>
				
					//	create ActiveWidgets Grid javascript object.
					var obj = new Active.Controls.Grid;
					var date  = new Active.Formats.Date; 
			 		var string  = new Active.Formats.String;
					var number  = new Active.Formats.Number; 

					date.setTextFormat("mm-dd-yyyy HH:mm");
					number.setTextFormat("#*");
					
					//	set number of rows/columns.
					obj.setRowProperty("count", <%=dataList.size()%>);
					obj.setColumnProperty("count", <%=columnList.length-1%>);
					var formats = [number,string,string,date];
					
					//	provide cells and headers text
					//obj.setDataProperty("text", function(i, j){return formats[j].dataToValue(myData[i][j])});
					obj.setDataText(function(i, j){return formats[j].dataToText(myData[i][j])});
					obj.setColumnProperty("text", function(i){return columns[i]});
					obj.sort(3,'descending');
					
					//	set headers width/height.
					obj.setRowHeaderWidth("28px");
					obj.setColumnHeaderHeight("20px");
			
					var row = new Active.Templates.Row;
					row.setEvent("ondblclick", function(){this.action("myAction")}); 
					
					obj.setTemplate("row", row);
			   		obj.setAction("myAction", 
						function(src){var frame = document.getElementById("newEventFrame"); frame.src = 'SearchObject.do?pageOf=' + myData[this.getSelectionProperty("index")][<%=columnList.length-1%>] + '&operation=search&systemIdentifier=' + myData[this.getSelectionProperty("index")][0]}); 
			
					//	write grid html to the page.
					document.write(obj);
				</script>
				</div>
			</td>
		</tr>
<% } else
   {
		title = "No Specimen Event Paremeters are available for Identifier : " + specimenIdentifier;
%>
		<tr>
			<td class="formTitle" height="20">
				<%--bean:message key="specimenEventParameters.noSpecimen"/--%>
				<%=title%>
			</td>
		</tr>
<% } %>	
	</table>
 </td>
</tr>

<tr>
	<td>&nbsp;</td>
</tr>

<tr>
	<td>
	  	 <table summary="" cellpadding="3" cellspacing="0" border="0" width="550">
			<tr>
				<td class="formTitle" height="20">
					<bean:message key="specimenEventParameters.caption"/>
				</td>
			</tr>
		</table>	
	</td>	
</tr>
<tr>
	<td><b><bean:message key="specimenEventParameters.label"/></b> &nbsp;
		<html:select property="specimenEventParameter" styleClass="formFieldSized15" styleId="className" size="1" disabled="false" onchange="onParameterChange(this)">
			<html:options name="<%=Constants.EVENT_PARAMETERS_LIST%>" labelName="<%=Constants.EVENT_PARAMETERS_LIST%>"/>
		</html:select>
	</td>
</tr>

<tr>
	<td>&nbsp;</td>
</tr>

<tr>
	<td>
		<iframe name="newEventFrame" id="newEventFrame" src="" width="650" height="400" frameborder="0" scrolling="auto">
		</iframe>
	</td>
</tr>

</table>
</html:form>