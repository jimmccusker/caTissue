<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%@ page import="java.util.*"%>
<%@ page import="edu.wustl.catissuecore.actionForm.AdvanceSearchForm"%>
<%@ page import="edu.wustl.common.beans.NameValueBean"%>
<%@ page import="edu.wustl.catissuecore.util.global.Constants"%>
<%@ page import="edu.wustl.catissuecore.query.Operator"%>

<%@ include file="/pages/content/common/SpecimenCommonScripts.jsp" %>

<%
	String className = "value(Specimen:SPECIMEN_CLASS)";
	String type = "value(Specimen:TYPE)";
	String tissueSite = "value(SpecimenCharacteristics:TISSUE_SITE)";
	String tissueSide = "value(SpecimenCharacteristics:TISSUE_SIDE)";
	String pathologicalStatus = "value(SpecimenCharacteristics:PATHOLOGICAL_STATUS)";
	String concentration1 = "value(Specimen:CONCENTRATION)";
	String concentration2 = "value(Specimen:CONCENTRATION:HLIMIT)";
	String quantity1 = "value(Specimen:QUANTITY)";
	String quantity2 = "value(Specimen:QUANTITY:HLIMIT)";
	String biohazardType = "value(Biohazard:TYPE)";
	String biohazardName = "value(Biohazard:NAME)";

	String opClassName = "value(Operator:Specimen:SPECIMEN_CLASS)";
	String opType = "value(Operator:Specimen:TYPE)";
	String opTissueSite = "value(Operator:SpecimenCharacteristics:TISSUE_SITE)";
	String opTissueSide = "value(Operator:SpecimenCharacteristics:TISSUE_SIDE)";
	String opPathologicalStatus = "value(Operator:SpecimenCharacteristics:PATHOLOGICAL_STATUS)";
	String opConcentration = "value(Operator:Specimen:CONCENTRATION)";
	String opQuantity = "value(Operator:Specimen:QUANTITY)";
	String opBarcode = "value(Operator:Specimen:BARCODE)";
	String opBiohazardType = "value(Operator:Biohazard:TYPE)";
	String opBiohazardName = "value(Operator:Biohazard:NAME)";

	String unitSpecimen = "";

	Map tableColumnMap = (Map)request.getAttribute(Constants.TABLE_COLUMN_DATA_MAP);
	List aliasNameList = (List)request.getAttribute(Constants.ALIAS_NAME_TABLE_NAME_MAP);

	AdvanceSearchForm form = (AdvanceSearchForm)request.getAttribute("advanceSearchForm");
	Map specimenDataMap = form.getValues();
%>

<head>
	<script src="jss/script.js" type="text/javascript"></script>
	<script src="jss/Hashtable.js" type="text/javascript"></script>
	<script src="jss/AdvancedSearchScripts.js" type="text/javascript"></script>
		
	<script language="JavaScript">
		//This function changes the unit as per the class name selected. It also enbles the concentration fields
		//if the class name is "Molecular".
		function onClassChange(element)
		{
			var unit = document.getElementById("unitSpan");
			var unitSpecimen = "";

			var concCombo = document.getElementById("opConcentration");
			var concentration1 = document.getElementById("concentration1");
			var concentration2 = document.getElementById("concentration2");
			var opClassCombo = document.getElementById("opClassName");

			concCombo.options[0].selected = true;
			concCombo.disabled = true;
			concentration1.value = "";
			concentration2.value = "";
			concentration1.disabled = true;
			concentration2.disabled = true;
			
			var subtype = document.getElementById("type");
			var subtypeCombo = document.getElementById("opType");

			if(opClassCombo.options[opClassCombo.selectedIndex].value != "<%=Operator.NOT_EQUALS_CONDITION%>")
			{
				subtypeCombo.disabled = false;
			}
			
			if(element.value == "Tissue")
			{
				unitSpecimen = "<%=Constants.UNIT_GM%>";
				document.forms[0].unit.value = "<%=Constants.UNIT_GM%>";
				typeChange(TissueArray);
			}
			else if(element.value == "Fluid")
			{
				unitSpecimen = "<%=Constants.UNIT_ML%>";
				document.forms[0].unit.value = "<%=Constants.UNIT_ML%>";
				typeChange(FluidArray);
			}
			else if(element.value == "Cell")
			{
				unitSpecimen = "<%=Constants.UNIT_CC%>";
				document.forms[0].unit.value = "<%=Constants.UNIT_CC%>";
				subtype.options.length = 0;
				subtype.options[0] = new Option('<%=Constants.SELECT_OPTION%>','<%=Constants.SELECT_OPTION%>');
				
				subtype.disabled = true;
				subtypeCombo.options[0].selected = true;
				subtypeCombo.disabled = true;
			}
			else if(element.value == "Molecular")
			{
				unitSpecimen = "<%=Constants.UNIT_MG%>";
				document.forms[0].unit.value = "<%=Constants.UNIT_MG%>";
				typeChange(MolecularArray);
				if(opClassCombo.options[opClassCombo.selectedIndex].text == "<%=Operator.EQUALS_CONDITION%>")
					concCombo.disabled = false;
			}
			
			unit.innerHTML = unitSpecimen;
		}
		
		//This function enables the concentration fields if the class name selected is "Molecular" &
		//operator is "Equals", else the function disables the concentration fields.
		function afterChange(element)
		{
			var concCombo = document.getElementById("opConcentration");
			var concentration1 = document.getElementById("concentration1");
			var concentration2 = document.getElementById("concentration2");
			var classCombo = document.getElementById("className");
			
			var opTypeCombo = document.getElementById("opType");
			var typeCombo = document.getElementById("type");
			
			if(element.options[element.selectedIndex].text == "<%=Operator.EQUALS_CONDITION%>")
			{
				if(classCombo.options[classCombo.selectedIndex].text == "Molecular")
				{
					concCombo.disabled = false;
				}
				
				opTypeCombo.disabled = false;
			}
			else
			{
				concCombo.options[0].selected = true;
				concCombo.disabled = true;
				concentration1.value = "";
				concentration2.value = "";
				concentration1.disabled = true;
				concentration2.disabled = true;
				
				opTypeCombo.options[0].selected = true;
				typeCombo.options[0].selected = true;
				opTypeCombo.disabled = true;
				typeCombo.disabled = true;
			}
		}
	</script>
	
	<script language="JavaScript">
	
		//CONVERTING THE JAVA-HASHMAP TO JAVASCRIPT HASHTABLE
		
		var columnNames = new Hashtable(); //Temporary Hashtable that contains the column names
		var columnsOfTable = new Hashtable(); //Hashtable that contains the list of column names against table names
		
		//Populating the data into javascript Hashtable
		<%
			for(int i=1;i<aliasNameList.size();i++)
			{
				NameValueBean bean = (NameValueBean)aliasNameList.get(i);
				String key = bean.getValue();
				List columns = (List)tableColumnMap.get(key);
				for(int j=0;j<columns.size();j++)
				{
					NameValueBean columnBean = (NameValueBean)columns.get(j);
		%>
					columnNames.put("<%=columnBean.getName()%>","<%=columnBean.getValue()%>");
		<%
				}
		%>
				columnsOfTable.put("<%=key%>",columnNames);
				columnNames = new Hashtable();
		<%
			}
		%>
		
		//This function populates the Column-Combo box as per the event selected.
		function onEventChange(element)
		{
			var index = element.id.lastIndexOf("_") + 1;
			var comboBoxNo = element.id.substring(index,element.id.length);
		
			var i=0;
			var columnNameCombo = "EventColumnName_" + comboBoxNo;
			var eventColumns = document.getElementById(columnNameCombo);
			
			//To clear the combo box
			eventColumns.options.length = 0;
			
			if(element.value == "-1") //If option selected is "SELECT" then
			{
				eventColumns.options[0] = new Option("<%=Constants.SELECT_OPTION%>","-1");
			}
			else
			{
				coulumnNames = columnsOfTable.get(element.value);
				var keys = coulumnNames.keys();
				
				//Populating the combo box with columns as per the event selected
				for(i=0;i<keys.length;i++)
				{
					eventColumns.options[i] = new Option(keys[i],coulumnNames.get(keys[i]));
				}
			}
			
			onEventColumnChange(eventColumns);
		}
		
		//This function populates the Operator-Combo box as per the datatype of the column selected.
		function onEventColumnChange(element)
		{
			//Getting the combo box number to be populated
			var index = element.id.lastIndexOf("_") + 1;
			var comboBoxNo = element.id.substring(index,element.id.length);
			var operatorCombo = "EventColumnOperator_" + comboBoxNo;

			var columnValue = element.value;
			
			var opCombo = document.getElementById(operatorCombo);
			opCombo.options.length=0;
			
			//If the datatype of selected column "varchar" or "text"
			if(columnValue.match("varchar") == "varchar" || columnValue.match("text") == "text")
			{
				opCombo.options[0] = new Option("<%=Operator.STARTS_WITH%>","<%=Operator.STARTS_WITH%>");
				opCombo.options[1] = new Option("<%=Operator.ENDS_WITH%>","<%=Operator.ENDS_WITH%>");
				opCombo.options[2] = new Option("<%=Operator.CONTAINS%>","<%=Operator.CONTAINS%>");
				opCombo.options[3] = new Option("<%=Operator.EQUALS_CONDITION%>","<%=Operator.EQUALS_CONDITION%>");
				opCombo.options[4] = new Option("<%=Operator.NOT_EQUALS_CONDITION%>","<%=Operator.NOT_EQUALS_CONDITION%>");
			}
			else
			{
				opCombo.options[0] = new Option("<%=Operator.EQUALS_CONDITION%>","<%=Operator.EQUALS_CONDITION%>");
				opCombo.options[1] = new Option("<%=Operator.NOT_EQUALS_CONDITION%>","<%=Operator.NOT_EQUALS_CONDITION%>");
				opCombo.options[2] = new Option("<%=Operator.LESS_THAN%>","<%=Operator.LESS_THAN%>");
				opCombo.options[3] = new Option("<%=Operator.LESS_THAN_OR_EQUALS%>","<%=Operator.LESS_THAN_OR_EQUALS%>");
				opCombo.options[4] = new Option("<%=Operator.GREATER_THAN%>","<%=Operator.GREATER_THAN%>");
				opCombo.options[5] = new Option("<%=Operator.GREATER_THAN_OR_EQUALS%>","<%=Operator.GREATER_THAN_OR_EQUALS%>");
			}
		}
		
		//Function to add more rows of Event Parameters
		function addMore(divTag)
		{
			//Extracting no. of rows in Add More Block
			var val = parseInt(document.forms[0].eventCounter.value);
			
			//Removing the anchor link of previous row
			var prevDiv = "div" + val;
			var prevDivTag = document.getElementById(prevDiv);
			prevDivTag.innerHTML = "AND";
			
			//Setting the counter for newly added row
			val = val + 1;
			document.forms[0].eventCounter.value = val;
			
			var rows = new Array();
			rows = document.getElementById(divTag).rows;
			var rowNo = rows.length;
			var rowNum= rowNo + 1;
			
			var x = document.getElementById(divTag).insertRow(rowNo);
			
			//First Cell
			var firstCell=x.insertCell(0);
			firstCell.className="formSerialNumberField";
			var comboName = "EventName_" + val;
			var sname="<select name='eventMap(" + comboName + ")' size='1' onchange='onEventChange(this)' class='formFieldSized15' id='" + comboName + "'>";
			var opt = "";
			
			<%
				List tabColumnList = (List)request.getAttribute(Constants.ALIAS_NAME_TABLE_NAME_MAP);

				for(int i=0;i<tabColumnList.size();i++)
				{
					NameValueBean bean = (NameValueBean)tabColumnList.get(i);
			%>
					opt = "<option value='<%=bean.getValue()%>'><%=bean.getName()%></option>"
					sname = sname + opt;
			<%
				}
			%>
			
			sname = sname + "</select>";
			firstCell.innerHTML="" + sname;
			
			//Second Cell
			var secondCell = x.insertCell(1);
			secondCell.className="formField";
			comboName = "EventColumnName_" + val;
			sname = "<select name='eventMap(" + comboName + ")' size='1' onchange='onEventColumnChange(this)' class='formFieldSized15' id='" + comboName + "'><option value='-1'>-- Select --</option></select>";
			secondCell.innerHTML="" + sname;
			
			//Third Cell
			var thirdCell = x.insertCell(2);
			thirdCell.className="formField";
			comboName = "EventColumnOperator_" + val;
			sname = "<select name='eventMap(" + comboName + ")' size='1' class='formFieldSized10' id='" + comboName + "'><option value='<%=Operator.EQUALS_CONDITION%>'><%=Operator.EQUALS_CONDITION%></option><option value='<%=Operator.NOT_EQUALS_CONDITION%>'><%=Operator.NOT_EQUALS_CONDITION%></option><option value='<%=Operator.LESS_THAN%>'><%=Operator.LESS_THAN%></option><option value='<%=Operator.LESS_THAN_OR_EQUALS%>'><%=Operator.LESS_THAN_OR_EQUALS%></option><option value='<%=Operator.GREATER_THAN%>'><%=Operator.GREATER_THAN%></option><option value='<%=Operator.GREATER_THAN_OR_EQUALS%>'><%=Operator.GREATER_THAN_OR_EQUALS%></option></select>";
			thirdCell.innerHTML="" + sname;
			
			//Fourth Cell
			var fourthCell = x.insertCell(3);
			fourthCell.className="formField";
			comboName = "EventColumnValue_" + val;
			sname = "<input type='text' name='eventMap(" + comboName + ")' value='' class='formFieldSized10' id='" + comboName + "'> &nbsp;&nbsp;";
			var divId = "div" + val;
			sname = sname + " <span id='" + divId + "'><a href='#' onClick=addMore('" + divTag + "')>AND</a></span>";
			fourthCell.innerHTML="" + sname;
			
			document.forms[0].deleteRow.disabled = false;
		}
		
		function deleteLast(subdivtag)
	{
		/** element of tbody    **/
		var element = document.getElementById(subdivtag);
		
		/** getting table ref from tbody    **/
		pNode = element.parentNode;
		
		if (pNode.rows.length > 4) 
		{
			/** deleting row from table **/
			pNode.deleteRow(pNode.rows.length - 3);
			document.forms[0].eventCounter.value = parseInt(document.forms[0].eventCounter.value) - 1;
			document.forms[0].action = "SpecimenAdvanceSearch.do?pageOf=pageOfSpecimenAdvanceSearch";
			document.forms[0].submit();
		}
	}
	</script>
</head>

<html:errors />

<html:form action="AdvanceSearchS.do">
<table summary="" cellpadding="5" cellspacing="0" border="0" width="650">

<tr>
	<td><html:hidden property="objectName" value="Specimen"/></td>
	<td><html:hidden property="eventCounter"/><html:hidden property="selectedNode" /></td>
	<td><html:hidden property="itemNodeId" /></td>
</tr>
<!--  MAIN TITLE ROW -->
<tr>
	<td class="formTitle" height="25" nowrap>
	&nbsp;<img src="images/Specimen.GIF" alt="Specimen" /> &nbsp;<bean:message key="specimen.queryRule"/>
	</td>
	<td nowrap align="right"  colspan="2" class="formTitle">
		<html:submit property="addRule" styleClass="actionButton" >
			<bean:message key="buttons.addRule"/>
		</html:submit>
		&nbsp;&nbsp;
		<html:button property="resetQuery" styleClass="actionButton" onclick="">
			<bean:message key="buttons.resetQuery"/>
		</html:button>
	</td>
</tr>

<!--  FIRST ROW -->
<tr>
	<td class="formSerialNumberField" nowrap>
 		<label for="className">
 			<b><bean:message key="specimen.type"/>
 		</label>
	</td>
	<td class="formField">
		<html:select property="<%=opClassName%>" styleClass="formFieldSized10" styleId="opClassName" size="1" onchange="onOperatorChange('opClassName','className'); afterChange(this)">
			<html:options collection="<%=Constants.ENUMERATED_OPERATORS%>" labelProperty="name" property="value"/>
		</html:select>
	</td>
	
	<%
		String opValue = (String)specimenDataMap.get("Operator:Specimen:SPECIMEN_CLASS");
		boolean disabled = (opValue == null || opValue.equals(Constants.ANY));
	%>
	
	<td class="formField">
		<html:select property="<%=className%>" styleClass="formFieldSized10" styleId="className" size="1" disabled="<%=disabled%>" onchange="onClassChange(this)">
			<html:options collection="<%=Constants.SPECIMEN_CLASS_LIST%>" labelProperty="name" property="value"/>
		</html:select>
	</td>
</tr>

<!--  SECOND ROW -->
<tr>
	<td class="formSerialNumberField" nowrap>
 		<label for="type">
 			<b><bean:message key="specimen.subType"/>
 		</label>
	</td>
	
	<%
		String classValue = (String)specimenDataMap.get("Specimen:SPECIMEN_CLASS");
		disabled = ((opValue != null && opValue.equals(Operator.NOT_EQUALS_CONDITION)) || (classValue != null && classValue.equals("Cell")));
	%>
	
	<td class="formField">
		<html:select property="<%=opType%>" styleClass="formFieldSized10" styleId="opType" size="1" onchange="onOperatorChange('opType','type')" disabled="<%=disabled%>">
			<html:options collection="<%=Constants.ENUMERATED_OPERATORS%>" labelProperty="name" property="value"/>
		</html:select>
	</td>
	
	<%
		String opTypeValue = (String)specimenDataMap.get("Operator:Specimen:TYPE");
		disabled = (opTypeValue == null || opTypeValue.equals(Constants.ANY) || opValue == null || opValue.equals(Operator.NOT_EQUALS_CONDITION));

		if(!disabled)
		{
			disabled = (classValue != null && classValue.equals("Cell"));
		}

		List typesList = null;
	%>
	
	<td class="formField">
		<html:select property="<%=type%>" styleClass="formFieldSized10" styleId="type" size="1" disabled="<%=disabled%>">
		<%
			if(classValue != null)
			{
				typesList = (List)specimenTypeMap.get(classValue);
				pageContext.setAttribute(Constants.SPECIMEN_TYPE_LIST,typesList);
		%>
			<html:options collection="<%=Constants.SPECIMEN_TYPE_LIST%>" labelProperty="name" property="value"/>
		<%
			}
			else
			{
		%>
			<html:options collection="<%=Constants.SPECIMEN_TYPE_LIST%>" labelProperty="name" property="value"/>
		<% } %>
		</html:select>
	</td>
</tr>

<!--  THIRD ROW -->
<tr>
	<td class="formSerialNumberField" nowrap>
 		<label for="tissueSite">
 			<b><bean:message key="specimen.tissueSite"/>
 		</label>
	</td>
	<td class="formField">
		<html:select property="<%=opTissueSite%>" styleClass="formFieldSized10" styleId="opTissueSite" size="1" onchange="onOperatorChange('opTissueSite','tissueSite')">
			<html:options collection="<%=Constants.ENUMERATED_OPERATORS%>" labelProperty="name" property="value"/>
		</html:select>
	</td>
	
	<%
		opValue = (String)specimenDataMap.get("Operator:SpecimenCharacteristics:TISSUE_SITE");
		disabled = (opValue == null || opValue.equals(Constants.ANY));
	%>
	
	<td class="formField">
		<html:select property="<%=tissueSite%>" styleClass="formFieldSized10" styleId="tissueSite" size="1" disabled="<%=disabled%>">
			<html:options collection="<%=Constants.TISSUE_SITE_LIST%>" labelProperty="name" property="value"/>
		</html:select>
	</td>
</tr>

<!--  FOURTH ROW -->
<tr>
	<td class="formSerialNumberField" nowrap>
 		<label for="tissueSide">
 			<b><bean:message key="specimen.tissueSide"/>
 		</label>
	</td>
	<td class="formField">
		<html:select property="<%=opTissueSide%>" styleClass="formFieldSized10" styleId="opTissueSide" size="1" onchange="onOperatorChange('opTissueSide','tissueSide')">
			<html:options collection="<%=Constants.ENUMERATED_OPERATORS%>" labelProperty="name" property="value"/>
		</html:select>
	</td>
	
	<%
		opValue = (String)specimenDataMap.get("Operator:SpecimenCharacteristics:TISSUE_SIDE");
		disabled = (opValue == null || opValue.equals(Constants.ANY));
	%>
	
	<td class="formField">
		<html:select property="<%=tissueSide%>" styleClass="formFieldSized10" styleId="tissueSide" size="1" disabled="<%=disabled%>">
			<html:options collection="<%=Constants.TISSUE_SIDE_LIST%>" labelProperty="name" property="value"/>
		</html:select>
	</td>
</tr>

<!--  FIFTH ROW -->
<tr>
	<td class="formSerialNumberField" nowrap>
 		<label for="pathologicalStatus">
 			<b><bean:message key="specimen.pathologicalStatus"/>
 		</label>
	</td>
	<td class="formField">
		<html:select property="<%=opPathologicalStatus%>" styleClass="formFieldSized10" styleId="opPathologicalStatus" size="1" onchange="onOperatorChange('opPathologicalStatus','pathologicalStatus')">
			<html:options collection="<%=Constants.ENUMERATED_OPERATORS%>" labelProperty="name" property="value"/>
		</html:select>
	</td>
	
	<%
		opValue = (String)specimenDataMap.get("Operator:SpecimenCharacteristics:PATHOLOGICAL_STATUS");
		disabled = (opValue == null || opValue.equals(Constants.ANY));
	%>
	
	<td class="formField">
		<html:select property="<%=pathologicalStatus%>" styleClass="formFieldSized10" styleId="pathologicalStatus" size="1" disabled="<%=disabled%>">
			<html:options collection="<%=Constants.PATHOLOGICAL_STATUS_LIST%>" labelProperty="name" property="value"/>
		</html:select>
	</td>
</tr>

<!-- SIXTH ROW -->
<tr>
	<td class="formSerialNumberField" nowrap>
 		<label for="concentration">
 			<b><bean:message key="specimen.concentration"/>
 		</label>
	</td>
	
	<%
		boolean disabled2 = true;
		boolean opDisabled = false;
		opValue = (String)specimenDataMap.get("Operator:Specimen:SPECIMEN_CLASS");
		String opValConcentration = (String)specimenDataMap.get("Operator:Specimen:CONCENTRATION");
		
		if(classValue != null && classValue.equals("Molecular") && opValue.equals(Operator.EQUALS_CONDITION))
		{
			if(opValConcentration != null && (opValConcentration.equals(Operator.BETWEEN) || opValConcentration.equals(Operator.NOT_BETWEEN)))
			{
				disabled = false;
				disabled2 = false;
				opDisabled = false;
			}
			else if(opValConcentration != null && !opValConcentration.equals(Constants.ANY))
			{
				disabled = false;
				disabled2 = true;
				opDisabled = false;
			}
		}
		else
		{
			disabled = true;
			disabled2 = true;
			opDisabled = true;
		}
	%>
	
	<td class="formField">
		<html:select property="<%=opConcentration%>" styleClass="formFieldSized10" styleId="opConcentration" size="1" onchange="onDateOperatorChange(this,'concentration1','concentration2')" disabled="<%=opDisabled%>">
				<html:options collection="<%=Constants.DATE_NUMERIC_OPERATORS%>" labelProperty="name" property="value"/>
		</html:select>
	</td>
	
	<td class="formField">
		<html:text styleClass="formFieldSized10" styleId="concentration1" property="<%=concentration1%>" disabled="<%=disabled%>"/>
						&nbsp;To&nbsp;
		<html:text styleClass="formFieldSized10" styleId="concentration2" property="<%=concentration2%>" disabled="<%=disabled2%>"/>
		<bean:message key="specimen.concentrationUnit"/>
	</td>
</tr>

<!-- SEVENTH ROW -->
<tr>
	<td class="formSerialNumberField" nowrap>
 		<label for="quantity">
 			<b><bean:message key="specimen.quantity"/>
 		</label>
	</td>
	<td class="formField">
		<html:select property="<%=opQuantity%>" styleClass="formFieldSized10" styleId="opQuantity" size="1" onchange="onDateOperatorChange(this,'quantity1','quantity2')">
			<html:options collection="<%=Constants.DATE_NUMERIC_OPERATORS%>" labelProperty="name" property="value"/>
		</html:select>
	</td>
	
	<%
		opValue = (String)specimenDataMap.get("Operator:Specimen:QUANTITY");
		if(opValue == null || opValue.equals(Constants.ANY))
		{
			disabled = true;
			disabled2= true;
		}
		else if(opValue.equals(Operator.BETWEEN) || opValue.equals(Operator.NOT_BETWEEN))
		{
			disabled = false;
			disabled2= false;
		}
		else
		{
			disabled = false;
			disabled2= true;
		}
	%>
	
	<td class="formField" nowrap>
		<html:text styleClass="formFieldSized10" styleId="quantity1" property="<%=quantity1%>" disabled="<%=disabled%>"/>
						&nbsp;To&nbsp;
		<html:text styleClass="formFieldSized10" styleId="quantity2" property="<%=quantity2%>" disabled="<%=disabled2%>"/>
		<span id="unitSpan"><%=unitSpecimen%></span>
     	<%--html:hidden property="value(Specimen:unit)"/--%>
     	<input type="hidden" name="unit">
	</td>
</tr>

<!-- EIGHTH ROW -->
<tr>
	<td class="formSerialNumberField" nowrap>
 		<label for="biohazardType">
     		<b><bean:message key="specimen.biohazardType"/>
     	</label>
	</td>
	<td class="formField">
		<html:select property="<%=opBiohazardType%>" styleClass="formFieldSized10" styleId="opBiohazardType" size="1" onchange="onOperatorChange('opBiohazardType','biohazardType')">
			<html:options collection="<%=Constants.STRING_OPERATORS%>" labelProperty="name" property="value"/>
		</html:select>
	</td>
	
	<%
		opValue = (String)specimenDataMap.get("Operator:Biohazard:TYPE");
		disabled = (opValue == null || opValue.equals(Constants.ANY));
	%>
	
	<td class="formField">
		<html:select property="<%=biohazardType%>" styleClass="formFieldSized10" styleId="biohazardType" size="1" disabled="<%=disabled%>">
			<html:options collection="<%=Constants.BIOHAZARD_TYPE_LIST%>" labelProperty="name" property="value"/>
		</html:select>
	</td>
</tr>

<!-- NINETH ROW -->
<tr>
	<td class="formSerialNumberField" nowrap>
 		<label for="biohazardName">
     		<b><bean:message key="specimen.biohazardName"/>
     	</label>
	</td>
	<td class="formField">
		<html:select property="<%=opBiohazardName%>" styleClass="formFieldSized10" styleId="opBiohazardName" size="1" onchange="onOperatorChange('opBiohazardName','biohazardName')">
			<html:options collection="<%=Constants.STRING_OPERATORS%>" labelProperty="name" property="value"/>
		</html:select>
	</td>
	
	<%
		opValue = (String)specimenDataMap.get("Operator:Biohazard:NAME");
		disabled = (opValue == null || opValue.equals(Constants.ANY));
	%>
	
	<td class="formField">
		<html:text styleClass="formFieldSized10" styleId="biohazardName" property="<%=biohazardName%>" disabled="<%=disabled%>"/>
	</td>
</tr>
</table>

<!--  SPECIMEN EVENT PARAMETERS -->
<table summary="" cellpadding="5" cellspacing="0" border="0" width="650">
<tr>
	<td class="formTitle" height="25" nowrap colspan="4">
		<bean:message key="buttons.specimenEventParameters"/>
	</td>
</tr>

<%
	int counter = form.getEventCounter();
	Map eventParametersMap = (Map)form.getEventValues();
	
	for(int i=1;i<=counter;i++)
	{
		String eventClassName = "eventMap(EventName_" + i + ")";
		String eventColumn = "eventMap(EventColumnName_" + i + ")";
		String eventOperator = "eventMap(EventColumnOperator_" + i + ")";
		String eventValue = "eventMap(EventColumnValue_" + i + ")";

		String classNameId = "EventName_" + i;
		String columnId = "EventColumnName_" + i;
		String operatorId = "EventColumnOperator_" + i;
		String valueId = "EventColumnValue_" + i;

		String eventParameter = (String)eventParametersMap.get(classNameId);
		List columnNamesList = (List)tableColumnMap.get(eventParameter);
		String operatorType = (String)eventParametersMap.get(columnId);
		String divId = "div" + i;

		if(operatorType != null)
		{
			operatorType = operatorType.substring(operatorType.lastIndexOf(".") + 1);
		}
%>


<tr>
	<td class="formSerialNumberField" nowrap>
		<html:select property="<%=eventClassName%>" styleClass="formFieldSized15" styleId="<%=classNameId%>" size="1" onchange="onEventChange(this)">
			<html:options collection="<%=Constants.ALIAS_NAME_TABLE_NAME_MAP%>" labelProperty="name" property="value"/>
		</html:select>
	</td>
	<td class="formField" nowrap>
		<html:select property="<%=eventColumn%>" styleClass="formFieldSized15" styleId="<%=columnId%>" size="1" onchange="onEventColumnChange(this)">
			<html:option value="-1"><%=Constants.SELECT_OPTION%></html:option>
			<%
			if(columnNamesList != null)
			{
				for(int no=0;no<columnNamesList.size();no++)
				{
					NameValueBean nvBean = (NameValueBean)columnNamesList.get(no);
			%>
					<html:option value="<%=nvBean.getValue()%>"><%=nvBean.getName()%></html:option>
			<%
				}
			}
			%>
		</html:select>
	</td>
	<td class="formField" nowrap>
		<html:select property="<%=eventOperator%>" styleClass="formFieldSized10" styleId="<%=operatorId%>" size="1">
			<%
			if(operatorType != null)
			{
			if(operatorType.equals("varchar") || operatorType.equals("text"))
			{
			%>
				<html:option value="<%=Operator.STARTS_WITH%>"><%=Operator.STARTS_WITH%></html:option>
				<html:option value="<%=Operator.ENDS_WITH%>"><%=Operator.ENDS_WITH%></html:option>
				<html:option value="<%=Operator.CONTAINS%>"><%=Operator.CONTAINS%></html:option>
				<html:option value="<%=Operator.EQUALS_CONDITION%>"><%=Operator.EQUALS_CONDITION%></html:option>
				<html:option value="<%=Operator.NOT_EQUALS_CONDITION%>"><%=Operator.NOT_EQUALS_CONDITION%></html:option>
			<%
			}else{
			%>
				<html:option value="<%=Operator.EQUALS_CONDITION%>"><%=Operator.EQUALS_CONDITION%></html:option>
				<html:option value="<%=Operator.NOT_EQUALS_CONDITION%>"><%=Operator.NOT_EQUALS_CONDITION%></html:option>
				<html:option value="<%=Operator.LESS_THAN%>"><%=Operator.LESS_THAN%></html:option>
				<html:option value="<%=Operator.LESS_THAN_OR_EQUALS%>"><%=Operator.LESS_THAN_OR_EQUALS%></html:option>
				<html:option value="<%=Operator.GREATER_THAN%>"><%=Operator.GREATER_THAN%></html:option>
				<html:option value="<%=Operator.GREATER_THAN_OR_EQUALS%>"><%=Operator.GREATER_THAN_OR_EQUALS%></html:option>
			<%
			}
			}
			else
			{
			%>
				<html:option value="<%=Operator.EQUALS_CONDITION%>"><%=Operator.EQUALS_CONDITION%></html:option>
				<html:option value="<%=Operator.NOT_EQUALS_CONDITION%>"><%=Operator.NOT_EQUALS_CONDITION%></html:option>
				<html:option value="<%=Operator.LESS_THAN%>"><%=Operator.LESS_THAN%></html:option>
				<html:option value="<%=Operator.LESS_THAN_OR_EQUALS%>"><%=Operator.LESS_THAN_OR_EQUALS%></html:option>
				<html:option value="<%=Operator.GREATER_THAN%>"><%=Operator.GREATER_THAN%></html:option>
				<html:option value="<%=Operator.GREATER_THAN_OR_EQUALS%>"><%=Operator.GREATER_THAN_OR_EQUALS%></html:option>
			<%
			}
			%>
		</html:select>
	</td>
	<td class="formField" nowrap>
		<html:text styleClass="formFieldSized10" styleId="<%=valueId%>" property="<%=eventValue%>"/>
		&nbsp;&nbsp;
		<span id="<%=divId%>">
		<%
			if(i == counter)
			{
		%>
				<a href="#" onClick="addMore('newEventRow')">
					<bean:message key="simpleQuery.and"/>
				</a>
		<%
			}
			else
			{
		%>
				<bean:message key="simpleQuery.and"/>
		<%
			}
		%>
		</span>
	</td>
</tr>

<%
	}
%>

<tbody id="newEventRow">
</tbody>

<tr>
	<td colspan="4">&nbsp;</td>
</tr>

<!-- TENTH ROW -->
<tr>
	<td colspan="2">&nbsp</td>
	<td nowrap align="right">
		<html:submit property="addRule" styleClass="actionButton" >
			<bean:message key="buttons.addRule"/>
		</html:submit>
		&nbsp;&nbsp;
		<html:button property="resetQuery" styleClass="actionButton" onclick="">
			<bean:message key="buttons.resetQuery"/>
		</html:button>
		
	</td>
	<td>
		<html:button property="deleteRow" styleClass="actionButton" onclick="deleteLast('newEventRow')" disabled="<%=(counter == 1)%>">
			<bean:message key="buttons.deleteLast"/>
		</html:button>
	</td>
	
</tr>

</table>
</html:form>