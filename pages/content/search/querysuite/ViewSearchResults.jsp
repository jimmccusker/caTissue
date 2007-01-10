<%@ page import="edu.wustl.catissuecore.actionForm.CategorySearchForm"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="edu.wustl.catissuecore.util.global.Constants"%>
<%@ page import="edu.wustl.catissuecore.util.global.Utility"%>
<%@ page import="edu.wustl.catissuecore.util.global.Variables"%>
<%@ page import="edu.wustl.common.tree.QueryTreeNodeData"%>
<html>
<head>

<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
</head>
	<link rel="STYLESHEET" type="text/css" href="dhtml_comp/css/dhtmlXGrid.css"/>
	<link rel="STYLESHEET" type="text/css" href="dhtml_comp/css/dhtmlXTree.css">
	<script  src="dhtml_comp/jss/dhtmlXCommon.js"></script>
	<script  src="dhtml_comp/jss/dhtmlXGrid.js"></script>		
	<script  src="dhtml_comp/jss/dhtmlXGridCell.js"></script>	
	<script language="JavaScript" type="text/javascript" src="dhtml_comp/jss/dhtmXTreeCommon.js"></script>
	<script language="JavaScript" type="text/javascript" src="dhtml_comp/jss/dhtmlXTree.js"></script>

	

<script>
var columns ;
var colWidth;
var colTypes 
<%
List columnList = (List) request.getAttribute(Constants.SPREADSHEET_COLUMN_LIST);
List dataList = (List) request.getAttribute(Constants.SPREADSHEET_DATA_LIST);
Vector treeData = (Vector)request.getAttribute("treeData");
 %>


// --------------------  FUNCTION SECTION
			//checks or unchecks all the check boxes in the grid.
					function checkAll(element)
					{
						var state=element.checked;
						rowCount = mygrid.getRowsNum();
						//alert("rowCount : "+ rowCount);
						for(i=1;i<=rowCount;i++)
						{
							var cl = mygrid.cells(i,0);
							if(cl.isCheckbox())
							cl.setChecked(state);
						}
					}

					//function to update hidden fields as per check box selections.
		function updateHiddenFields()
		{
			var isChecked = "false";
			var checkedRows = mygrid.getCheckedRows(0);
			if(checkedRows.length > 0)
			{
	        	isChecked = "true";
				var cb = checkedRows.split(",");
				rowCount = mygrid.getRowsNum();
				for(i=1;i<=rowCount;i++)
				{
					var cl = mygrid.cells(i,0);
					if(cl.isChecked())
					{
						var cbvalue = document.getElementById(""+(i-1));
						cbvalue.value="1";
						cbvalue.disabled=false;
					}
					else
					{
						var cbvalue = document.getElementById(""+(i-1));
						cbvalue.value="0";
						cbvalue.disabled=true;
					}
				}
			}
			else
			{
				isChecked = "false";
			}
			return isChecked;
		}	

// ------------------------------  FUNCTION SECTION END
<% if (columnList != null && columnList.size()!= 0 && dataList != null && dataList.size() != 0)
{ %>
var myData = [<%int i;%><%for (i=0;i<(dataList.size()-1);i++){%>
<%
	List row = (List)dataList.get(i);
  	int j;
%>
<%="\""%><%for (j=0;j < (row.size()-1);j++){%><%=Utility.toNewGridFormat(row.get(j))%>,<%}%><%=Utility.toNewGridFormat(row.get(j))%><%="\""%>,<%}%>
<%
	List row = (List)dataList.get(i);
  	int j;
%>
<%="\""%><%for (j=0;j < (row.size()-1);j++){%><%=Utility.toNewGridFormat(row.get(j))%>,<%}%><%=Utility.toNewGridFormat(row.get(j))%><%="\""%>
];


var columns = <%="\""%><%int col;%><%for(col=0;col<(columnList.size()-1);col++){%><%=columnList.get(col)%>,<%}%><%=columnList.get(col)%><%="\""%>;

var colWidth = <%="\""%><%for(col=0;col<(columnList.size()-1);col++){%><%=100%>,<%}%><%=100%><%="\""%>;
var colTypes = <%="\""%><%=Variables.prepareColTypes(dataList,true)%><%="\""%>;

var colDataTypes = colTypes;

while(colDataTypes.indexOf("str") !=-1)
colDataTypes=colDataTypes.replace(/str/,"ro");
<% } %>
</script>


<script>
function initGridView()
{<% if (columnList != null && columnList.size()!= 0 && dataList != null && dataList.size() != 0)
{ %>
	mygrid = new dhtmlXGridObject('gridbox');
	mygrid.setImagePath("dhtml_comp/imgs/");
	mygrid.setHeader(columns);
	//mygrid.setEditable("FALSE");
	mygrid.enableAutoHeigth(false);

	mygrid.setInitWidths(colWidth);
	
	mygrid.setColTypes(colDataTypes);
	mygrid.enableMultiselect(true);
//	mygrid.chNoState = "0";

	//mygrid.setColAlign("left,left")
	mygrid.setColSorting(colTypes);
	//mygrid.enableMultiselect(true)
	mygrid.init();

/*
	mygrid.loadXML("dhtmlxgrid/grid.xml");
	
//		clears the dummy data and refreshes the grid.
	mygrid.clearAll();
*/
	for(var row=0;row<myData.length;row++)
	{
		data = "0,"+myData[row];
		mygrid.addRow(row+1,data,row+1);
	}
	//fix for grid display on IE for first time.
	mygrid.setSizes();

//Tree component
	tree=new dhtmlXTreeObject("treebox","100%","100%",0);
	tree.setImagePath("dhtml_comp/imgs/");
	tree.setOnClickHandler(tonclick);
		<%
			
				if(treeData != null && treeData.size() != 0)
				{
					Iterator itr  = treeData.iterator();
					while(itr.hasNext())
					{
						QueryTreeNodeData data = (QueryTreeNodeData) itr.next();
						String parentId = "0";	
						if(!data.getParentIdentifier().equals("0"))
						{
							parentId = data.getParentObjectName() + "_"+ data.getParentIdentifier().toString();		
						}
						String nodeId = data.getObjectName() + "_"+data.getIdentifier().toString();
						String img = "plus.GIF";
						if(data.getObjectName().equals(Constants.SPECIMEN_COLLECTION_GROUP))
						{
							img = "plus.GIF";
						}

				
			%>
					tree.insertNewChild("<%=parentId%>","<%=nodeId%>","<%=data.getDisplayName()%>",0,"","","","");
					tree.setUserData("<%=nodeId%>","<%=nodeId%>","<%=data%>");	
			<%	
					}
				}
}
			%>	
}
function tonclick(id)
{

	
};

</script>

<% if (columnList != null && columnList.size()!= 0 && dataList != null && dataList.size() != 0)
{ %>
<body onload="initGridView()">
	<% }  else{ %>
	<body>	
<% } %>
<html:errors />
<%
	String formAction = Constants.ViewSearchResultsAction;
%>
<html:form method="GET" action="<%=formAction%>">
<html:hidden property="stringToCreateQueryObject" value="" />
<table bordercolor="#000000" border="0" width="100%" cellspacing="2" cellpadding="2"  height="100%">
	<tr>
	<td>
	<table border="1" width="100%" cellspacing="0" cellpadding="0" height="100%" id="table1">
	<tr>
		<td>
		<table border="0" width="100%" cellspacing="0" cellpadding="0" height="100%" bordercolor="#000000" id="table1" >
		<tr  height="2%">
			<td height="2%" colspan="5" bgcolor="#000000"><font color="#FFFFFF">&nbsp;&nbsp;Search Data</font></td>
		</tr>
		<tr  class="trStyle">
			<td width="20%" height="5%" class="queryModuleTabMenuItem">
				<bean:message key="query.chooseSearchCategory"/>
			</td>

			<td width="20%" height="5%" class="queryModuleTabMenuItem" >
				<bean:message key="query.addLimits"/>
			</td>

			<td width="20%" height="5%" class="queryModuleTabMenuItem" >
				<bean:message key="query.defineSearchResultsViews"/>
			</td>

			<td width="20%" height="5%" class="queryTabMenuItemSelected">
				<bean:message key="query.viewSearchResults"/>
			</td>

			<td width="20%" height="5%" class="queryModuleTabMenuItem">
				<bean:message key="query.dataList"/>
			</td>			
		</tr>
		<tr>
			<td height="500" width="100%" colspan="5">

				<table border="0"  height="100%" width="100%" cellpadding="1" cellspacing="3">
					<tr>
						<td valign="top" width="20%" height="100%">
							<table border="1" width="100%" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" height="100%" bordercolorlight="#000000" >
								<tr>
									<td valign="top"><div id="treebox" width="100%" height="100%" style="background-color:white;overflow:hidden">
								<%	if(treeData != null && treeData.size() == 0)
										{ %>
											<bean:message key="simpleQuery.noRecordsFound"/>
									<% } %>
									</div>
									</td>
								</tr>									
							</table>
						</td>
						<td valign="top" height="100%">
							<table border="1" width="100%" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF" height="100%" bordercolorlight="#000000" id="table11">
								<tr>
								<td valign="top" height="100%" colspan="2">
									<div id="gridbox" width="100%" height="100%" style="background-color:white;overflow:hidden">
									<% if (dataList != null && dataList.size() == 0)
										{ %>
									<bean:message key="simpleQuery.noRecordsFound"/>
									<% } %>
									</div>
								</td>
							</tr>
					<tr bgcolor="#FFFFFF"  height="40">
					<td valign="bottom" height="40">
					<table border="0" width="100%" cellspacing="0" cellpadding="0" bgcolor="" height="100%" bordercolorlight="#000000" >
					<tr valign="center">
					 <td width="2%" valign="center">&nbsp;</td>
						<td width="2%" ><html:button property="Button"><bean:message key="query.addToDataList"/></html:button></td>
					 <td width="2%" valign="center">&nbsp;</td>
						<td ><html:button property="Button"><bean:message key="query.export"/></html:button></td>
					</tr>
				</table>
				
			</td>
		</tr>
						</table>
				</td>
		</tr>
					
				</table>
				</td>
					</tr>
					<tr bgcolor="#DFE9F3">
					<td colspan="5" valign="bottom" height="30">
					<table border="0" width="100%" cellspacing="0" cellpadding="0" bgcolor="#EAEAEA" height="100%" bordercolorlight="#000000" >
					<tr height="35" valign="center">
					 <td width="2%" valign="center">&nbsp;</td>
						<td valign="center" width="75%"><html:button property="Button"><bean:message key="query.saveButton"/></html:button></td>
				
						<td  align="right" valign="center"><html:button property="Button"><bean:message key="query.perviousButton"/></html:button></td>
						<td align="right" valign="center"><html:button property="Button" onclick="viewSearchResults()"><bean:message 		key="query.nextButton"/></html:button>
						</td>
						<td width="2%">&nbsp;</td>
					</tr>
				</table>
				
			</td>
		</tr>
	
			</table>
			</td>
			</tr>
			</table>
			</td></tr>
</table>
</html:form>
</body>
</html> 