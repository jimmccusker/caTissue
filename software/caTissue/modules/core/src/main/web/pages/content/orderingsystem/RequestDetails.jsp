<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ page import="edu.wustl.catissuecore.util.global.Constants" %>
<%@ page import="edu.wustl.common.util.global.ApplicationProperties" %>
<%@ page import="edu.wustl.catissuecore.domain.OrderDetails"%>
<%@ page import="java.util.List"%>
<%@ page import="edu.wustl.catissuecore.bean.RequestViewBean" %>
<script src="jss/ajax.js"></script>
<script type="text/javascript" src="jss/dhtmlwindow.js"></script>
<script type="text/javascript" src="jss/modal.js"></script>
<script type="text/javascript" src="jss/ext-base.js"></script>
<script type="text/javascript" src="jss/ext-all.js"></script>
<script type="text/javascript" src="jss/distributeOrderDetails.js"></script>
<link rel="stylesheet" type="text/css" href="css/clinicalstudyext-all.css" />
<link rel="stylesheet" type="text/css" href="css/catissue_suite.css" />

<head>
<%
List requestDetailsList = new ArrayList();
if(request.getAttribute(Constants.REQUEST_DETAILS_LIST) != null )
	requestDetailsList = (List) request.getAttribute(Constants.REQUEST_DETAILS_LIST);
else
	requestDetailsList = (List) session.getAttribute(Constants.REQUEST_DETAILS_LIST);

	RequestViewBean requestViewBean = (RequestViewBean)request.getAttribute(Constants.REQUEST_HEADER_OBJECT);
	String distributionProtocolName = "";
	if(requestViewBean != null)
	{
		distributionProtocolName = requestViewBean.getDistributionProtocol();
		OrderDetails orderForDP = (OrderDetails)request.getAttribute("orderDetailsForDP");
		if(orderForDP!= null && orderForDP.getDistributionProtocol()!= null && orderForDP.getDistributionProtocol().getIrbIdentifier()!= null && orderForDP.getDistributionProtocol().getIrbIdentifier().length()!=0)
		{
			distributionProtocolName = distributionProtocolName+"("+orderForDP.getDistributionProtocol().getIrbIdentifier()+")";
		}
	}
int disabledStatus;
int count=0;
if(requestDetailsList!=null && requestDetailsList.size()>0 )
{
 count=requestDetailsList.size();
}
String checkQuantityforAll = "checkQuantityforAll("+count+")";
String form_action = Constants.SUBMIT_REQUEST_DETAILS_ACTION+"?submittedFor=ForwardTo&noOfRecords="+count;
%>
<script language="JavaScript" type="text/javascript" src="jss/javaScript.js"></script>
<script language="JavaScript" type="text/javascript" src="jss/ajax.js"></script>
<script language="JavaScript" type="text/javascript" src="jss/OrderingSystem.js"></script>
<script language="javascript">
var newWindow;

function changeCreateButtonStatus(noOfItems,arrayRowCounter,assignStatusArraycount)
{
	var buttonCreateArrayId = "buttonCreateArrayId"+arrayRowCounter;
	var obj = document.getElementById(buttonCreateArrayId);
	obj.disabled=false;
	var index = assignStatusArraycount-noOfItems;
	for(i=index;i<assignStatusArraycount;i++)
	{

		var assignStatusArrayId = "value(DefinedArrayDetailsBean:"+i+"_assignedStatus)"+arrayRowCounter;
		var assignStatusArrayValue =  document.getElementById(assignStatusArrayId).value;
		if(assignStatusArrayValue!="Ready For Array Preparation")
		{
			obj.disabled=true;
			break;
		}

	}

}

function tabToDisplay()
{
	var tabIndex = document.getElementById("tabIndexId").value;
	if(tabIndex == 1)
	{
		gotoSpecimenRequestTab();
	}
	else
	{
		gotoArrayRequestTab();
	}
}
	function getUnit(classname,type)
		{
			if(classname == "Tissue")
			{
				if(type == "<%=Constants.FROZEN_TISSUE_SLIDE%>" || type =="<%=Constants.FIXED_TISSUE_BLOCK%>" || type == "<%=Constants.FROZEN_TISSUE_BLOCK%>" || type == "<%=Constants.NOT_SPECIFIED%>" || type == "<%=Constants.FIXED_TISSUE_SLIDE%>")
				{
					return("<%=Constants.UNIT_CN%>");
				}
				else
				{
						if(type == "<%=Constants.MICRODISSECTED%>")
						{
							return("<%=Constants.UNIT_CL%>");
						}
						else
						{
							return("<%=Constants.UNIT_GM%>");
						}
				}
			}
			else if(classname == "Fluid")
			{
				return("<%=Constants.UNIT_ML%>");
			}
			else if(classname == "Cell")
			{
				return("<%=Constants.UNIT_CC%>");
			}
			else if(classname == "Molecular")
			{
				return("<%=Constants.UNIT_MG%>");
			}

		}
function showSpecimenDetails(id)
{
	var id = "requestFor"+id;
	var objId = document.getElementById(id).value;

	if(objId != "#")
	{
		showNewPage('SearchObject.do?pageOf=pageOfNewSpecimen&operation=search&target=orderDetails&id=' + objId );

	} else {

		alert("No specimen selected : Select specimen to view details.");
	}
}

function viewSpecimenDetails(id)
{
	showNewPage('SearchObject.do?pageOf=pageOfNewSpecimen&operation=search&target=orderDetails&id=' + id );
}


function createAliquots(id)
{

	var id = "requestFor"+id;
	var objId = document.getElementById(id).value;
	if(objId != "#")
	{
		var url = 'createAliquotSpecimen.do?specimenId='+objId;
		window.parent.location.href=url;
	} else {

		alert("No specimen selected : Select specimen to create Aliquot");
	}
	//showNewPage('createAliquotSpecimen.do?pageOf=pageOfCreateAliquot&operation=add&id=' + objId );
}

function createDerivatives(id)
{
	var id = "requestFor"+id;
	var objId = document.getElementById(id).value;
	if(objId != "#")
	{
		var url = 'createDerivedSpecimen.do?specimenId='+objId;
		window.parent.location.href=url;
	} else {

		alert("No specimen selected : Select specimen to create derivative");
	}
	//showNewPage('createDerivedSpecimen.do?rowNumber='+1);
//	showNewPage('CreateSpecimen.do?pageOf=success&operation=add&id=' + objId );
}

function createSpecimen(id)
{
	var url = "createSpecimenFromOrder.do?rowNumber="+id;
	window.parent.location.href=url;

}

function showSpecimenArrayDetails(id)
{
	showNewPage('SearchObject.do?pageOf=pageOfSpecimenArray&operation=search&id=' + id );
}
function showNewPage(action)
{
   	if(newWindow!=null)
	{
	   newWindow.close();
	}
     newWindow = window.open(action,'','scrollbars=yes,status=yes,resizable=yes,width=860, height=600');
     newWindow.focus();

}
function submitAndNotify()
{
	document.getElementById("mailNotification").value= "true";
	document.forms[0].submit();
}

function directDistribute()
{
	document.getElementById("isDirectDistribution").value= "true";
	onDistriProtSet();
}

//this function used to view all the consents
function showAllSpecimen(count)
{

	var speciemnIdValue="";
	var labelIndexCount="";
	var verifiedRows="";
	var iCount=0;

	for(i=0;i<count;i++)
	{
		var consentVerificationkey= "value(RequestDetailsBean:"+i+"_consentVerificationkey)";
		var rowstatus= "value(RequestDetailsBean:"+i+"_rowStatuskey)";

		var statusValue =  document.getElementById(rowstatus).value;

		var status = document.getElementById(consentVerificationkey).value;

		if(status=="<%=Constants.VIEW_CONSENTS%>"||status=="<%=Constants.VERIFIED%>" && statusValue!="disable")
		{
			//var specimenkey= "value(RequestDetailsBean:"+i+"_specimenId)";
			//var specimenObj= document.getElementById(specimenkey);

			var id = "requestFor"+i;
        	var specimenIdentifier = document.getElementById(id).value;

			if(specimenIdentifier != "#")
			{
			  speciemnIdValue= speciemnIdValue+specimenIdentifier;
			  labelIndexCount=labelIndexCount+i;
            }

			if(i!=count && specimenIdentifier != "#")
			{
				speciemnIdValue=speciemnIdValue+"|";
				labelIndexCount=labelIndexCount+"|";
			}
			if(status=="<%=Constants.VERIFIED%>")
			{
				verifiedRows=verifiedRows+(i-iCount)+",";
			}

		}
		else
		{
			iCount=iCount+1;
		}

	}

	if(speciemnIdValue == "")
	{
		alert("No specimen selected : Select specimen to view consents");
	} else
	{

		if(count==(iCount))
		{
			alert("No consents available");
		}
		else
		{
			var url= 'ConsentVerification.do?operation=&pageOf=pageOfOrdering&specimenConsents=yes&verifiedRows='+verifiedRows+'&noOfRows='+count+'&speciemnIdValue='+speciemnIdValue+'&labelIndexCount='+labelIndexCount;

			//model window popup
			allConsentWindow=dhtmlmodal.open('Institution', 'iframe', url,'Consent Form', 'width=600px,height=300px,center=1,resize=0,scrolling=1')
			allConsentWindow.onclose=function()
		  {
				return true;
		  }

		}
	}
}
//This function called to view the consent page
function showNewConsentPage(specimenIdentifierkey,labelStatus,consentVerificationkey,id)
{
	var status = document.getElementById(consentVerificationkey).value;
	var id = "requestFor"+id;
	var specimenIdentifier = document.getElementById(id).value;

	var url ='ConsentVerification.do?operation=&pageOf=pageOfOrdering&status='+status+'&labelStatusId='+labelStatus+'&consentVerificationkey='+consentVerificationkey+'&showConsents=yes&specimenId='+specimenIdentifier;

	if(specimenIdentifier != "#")
	{
		if(status!="No Consents")
		{

		  consentWindow=dhtmlmodal.open('Institution', 'iframe', url,'Consent Form', 'width=600px,height=260px,center=1,resize=0,scrolling=1')
		  consentWindow.onclose=function()
		  {
				return true;
		  }
		}

	} else {

		alert("No specimen selected : Select specimen to view consents");
	}



}

function checkQuantity(index)
{

	var availableQty = document.getElementById("availableQtyId" + index).value;
	var requiredQty = document.getElementById("requestedQtyId" + index).value;
	var status = document.getElementById("select_" + index).value;
	var canDistribute = "value(RequestDetailsBean:"+index+"_canDistribute)";
	var answer;

	if(status == "<%=Constants.ORDER_REQUEST_STATUS_DISTRIBUTED%>" || status == "<%=Constants.ORDER_REQUEST_STATUS_DISTRIBUTED_AND_CLOSE%>" )
	{

		if(availableQty > requiredQty)
		{
			answer= confirm("Available Quantity is greater then the Ordered Quantity :Select OK if you still want to distribute the specimen");


		}else if(availableQty < requiredQty)
		{
			answer= confirm("Available Quantity is less than the Ordered Quantity :Select OK if you still want to distribute the specimen");
		}

		if(availableQty == requiredQty || answer)
		{
			document.getElementById(canDistribute).value="true";
		}
	} else
	{
		document.getElementById(canDistribute).value="false";
	}


}

function checkQuantityforAll(count)
{
	var status = document.getElementById("nextStatusId").value;
	var availableQty;
	var requiredQty ;
	var canDistribute;
	var answer;
	var isQuantityValid = true;

	if(status == "<%=Constants.ORDER_REQUEST_STATUS_DISTRIBUTED%>" || status == "<%=Constants.ORDER_REQUEST_STATUS_DISTRIBUTED_AND_CLOSE%>")
	{
		for(i=0;i<count;i++)
		{
			availableQty = document.getElementById("availableQtyId" + i).value;
			requiredQty = document.getElementById("requestedQtyId" + i).value;
			if(availableQty != requiredQty) {
				answer= confirm("Some of the specimens have Available Quantity greater then or less then the Ordered Quantity : Select Ok if you still want to distribute the specimen");
				isQuantityValid = false;
				break;
			}

		}
		if(answer || isQuantityValid)
		{
			for(i=0;i<count;i++)
			{
				canDistribute = "value(RequestDetailsBean:"+i+"_canDistribute)";
				document.getElementById(canDistribute).value="true";
			}
			updateAllStatus();
		}

	} else {
		updateAllStatus();
	}

}



 /***  code using ajax :gets the emailAddress of the coordinator without refreshing the whole page  ***/
	function onSpecimenChange(iden)
	{
		var id = "requestFor"+iden;
		request = newXMLHTTPReq();
		var actionURL;
		var handlerFunction = getReadyStateHandler(request,onResponseUpdate,true);
		request.onreadystatechange = handlerFunction;
		actionURL = "specimenId="+ document.getElementById(id).value+"&isOnChange=true"+"&identifier="+iden;
		var url = "RequestDetails.do";
		<!-- Open connection to servlet -->
		request.open("POST",url,true);
		request.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		request.send(actionURL);
	}

	function onResponseUpdate(response)
	{
		var values = response.split("#@#");

		var selectedSpecimenTypeId = "selectedSpecimenTypeId" +  values[0];
		var availableQtyId = "availableQtyId" + values[0];
        var specimenQuantityUnitId = "specimenQuantityUnitId" +  values[0];

		document.getElementById(selectedSpecimenTypeId).value	= values[2];
		if(values[2] != "NA")
		{
			document.getElementById(availableQtyId).value	= values[1];
			document.getElementById(specimenQuantityUnitId).value	= values[3];
		} else {
			document.getElementById(availableQtyId).value	= "";
			document.getElementById(specimenQuantityUnitId).value	= "";
		}

		var consentVerificationkey= "value(RequestDetailsBean:"+values[0]+"_consentVerificationkey)";
		var labelStatus="labelStatus"+values[0];
		var parentId=window.parent.document.getElementById(labelStatus);
		parentId.innerHTML="View"+"<input type='hidden' name='" + consentVerificationkey + "' value='View' id='" + consentVerificationkey + "'/>";


	}

	/*** code using ajax  ***/


	/***  code using ajax :to get the requester name  ***/
	function onDistriProtSet()
	{

		request = newXMLHTTPReq();
		var actionURL;
		var handlerFunction = getReadyStateHandler(request,onResponseSetRequester,true);
		request.onreadystatechange = handlerFunction;
		actionURL = "distributionProtId="+ document.getElementById("distributionProtocolId").value+"&isOnChange=true"+"&identifier=";
		var url = "RequestDetails.do";
		<!-- Open connection to servlet -->
		request.open("POST",url,true);
		request.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		request.send(actionURL);
	}

	function onResponseSetRequester(response)
	{
		document.getElementById("requesterName").value	= response;
	}

	/*** code using ajax  ***/


</script>
</head>
<body onload="tabToDisplay()">
<script type="text/javascript" src="jss/wz_tooltip.js"></script>

<html:form action="<%=form_action%>">

<table width="100%" border="0" cellpadding="0" cellspacing="0" class="maintable">
  <tr>
    <td class="td_color_bfdcf3"><table border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td class="td_table_head"><span class="wh_ar_b">
								<bean:message key="requestdetails.header" />
							</span></td>
        <td><img src="images/uIEnhancementImages/table_title_corner2.gif" alt="Page Title - Order" width="31" height="24" /></td>
      </tr>
    </table></td>
  </tr>

  <tr>
    <td class="tablepadding"><table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="90%" valign="bottom" class="td_tab_bg">&nbsp;</td>
      </tr>
    </table>
    <table width="100%" border="0" cellpadding="3" cellspacing="0" class="whitetable_bg">
    <tr>
       <td align="left" class=" bottomtd"><%@ include file="/pages/content/common/ActionErrors.jsp" %>
		</td>
        </tr>
        <tr>
          <td align="left" class="tr_bg_blue1"><span class="blue_ar_b">&nbsp;<bean:message key="requestdetails.name" />
		</span></td>
        </tr>
        <tr>

  			<jsp:useBean id="requestDetailsForm" class="edu.wustl.catissuecore.actionForm.RequestDetailsForm" scope="request"/>
			<% session.setAttribute("REQUEST_DETAILS_FORM",requestDetailsForm);%>

          <td align="left" class="showhide"><table width="100%" border="0" cellspacing="0" cellpadding="3" >

 				<logic:equal name="requestDetailsForm" property="isDirectDistribution" value="true">
				<tr>
					<td class="noneditable"><span class="noneditable"><img src="images/uIEnhancementImages/star.gif" alt="Mandatory"  /></span>
						<strong>
						<bean:message key='requestlist.dataTabel.OrderName.label'/>
						</strong>
					</td>
					<td >
					 	<html:text styleClass="black_ar" maxlength="50" size="30" styleId="orderName" property="orderName"/>
					</td>

  				</tr>
				<tr>
					<td class="noneditable"><span class="noneditable"><img src="images/uIEnhancementImages/star.gif" alt="Mandatory"  /></span>
                	<strong>
						<bean:message key='requestlist.dataTabel.DistributionProtocol.label'/>
					</strong>
					</td>

					<td>
										<div>
										<input property="distributionProtocolId" type="text" id="distributionProtocolId" name="distributionProtocolId"
										 onmouseover="showTip(this.id)"/>
										</div>
									</td>
					<!--td>

						<html:select property="distributionProtocolId" styleClass="formFieldSizedNew" styleId="distributionProtocolId"
									size="1" onmouseover="showTip(this.id)" onmouseout="hideTip(this.id)" onchange="directDistribute()">
								<html:options collection="<%=Constants.DISTRIBUTIONPROTOCOLLIST%>" labelProperty="name" property="value"/>
						</html:select>

					</td-->
				</tr>

 				<tr>

						<td width="17%" class="noneditable"><strong>
								<bean:message key='requestdetails.header.label.RequestorName'/>
							</strong>
						</td>

			            <td class="noneditable">-
						<i>To be implemented</i>
			            <html:text styleClass="formFieldSmallNoBorderlargeSize"
						name="<%=  Constants.REQUEST_HEADER_OBJECT %>" styleId="requesterName" property="requestedBy" readonly="true" style="background-color:#f6f6f6;"/>

			            <!--  <span class="link">
								<a class="view" href='mailto:<bean:write name='<%=  Constants.REQUEST_HEADER_OBJECT  %>'  property='email' scope='request' />' >
									<bean:write name="<%=  Constants.REQUEST_HEADER_OBJECT %>" property="requestedBy" scope="request"/>
								</a>
							</span>-->
						</td>
              </tr>


				</logic:equal>

				<logic:notEqual  name="requestDetailsForm" property="isDirectDistribution" value="true">
				<tr>
					<td class="noneditable">
						<strong>
						<bean:message key='requestlist.dataTabel.OrderName.label'/>
						</strong>
					</td>

	                <td class="noneditable">-
	                		<bean:write name="<%=  Constants.REQUEST_HEADER_OBJECT  %>" property="orderName" scope="request"/>
					</td>
				</tr>
				<tr>
					<td class="noneditable">
                		<strong>
						<bean:message key='requestlist.dataTabel.DistributionProtocol.label'/>
						</strong>
					</td>
 					<!--td class="noneditable">-
							<bean:write name="<%=  Constants.REQUEST_HEADER_OBJECT  %>" property="distributionProtocol" scope="request"/>
					</td-->
					<td>
										<div>
										<input property="distributionProtocolId" type="text" id="distributionProtocolId" name="distributionProtocolId" value = "<%=distributionProtocolName%>" 
										 onmouseover="showTip(this.id)"/>
										</div>
									</td>
				</tr>

				<tr>

						<td width="17%" class="noneditable"><strong>
								<bean:message key='requestdetails.header.label.RequestorName'/>
							</strong>
						</td>

			            <td class="noneditable">-
							<i>To be implemented</i>
				           <!--span class="link">
								<a class="view" href='mailto:<bean:write name='<%=  Constants.REQUEST_HEADER_OBJECT  %>'  property='email' scope='request' />' >
									<bean:write name="<%=  Constants.REQUEST_HEADER_OBJECT %>" property="requestedBy" scope="request"/>
								</a>
							</span-->

						</td>
              </tr>


				</logic:notEqual>

           <tr>
                <td class="noneditable"><strong>
								<bean:message key='requestlist.dataTabel.label.RequestDate'/>
							</strong></td>
                <td>-
				<html:text styleClass="black_ar" maxlength="50" size="30" name="<%=  Constants.REQUEST_HEADER_OBJECT  %>" styleId="requestedDate" property="requestedDate"/>
							<!--bean:write name="<%=  Constants.REQUEST_HEADER_OBJECT  %>" property="requestedDate" scope="request"/-->
						</td>

              </tr>

			<tr >

					<td class="noneditable"><strong>
							<bean:message key='requestlist.dataTabel.label.Site'/>
 					</strong></td>
					<td>
							<html:select property="site" name="requestDetailsForm" styleClass="formFieldSized51" styleId="siteId" size="1" onmouseover="showTip(this.id)" onmouseout="hideTip(this.id)">
							<html:options collection="<%= Constants.SITE_LIST_OBJECT  %>" labelProperty="name" property="value"/>
							</html:select>

					</td>

			</tr>
			<tr>
 					<td valign="top" class="noneditable"><strong>&nbsp;<br />
                  
 						<bean:message key="requestdetails.header.label.Comments" />
	 				<strong></td>
 					<td valign="top" class="black_ar"><br />
 						<html:textarea name="requestDetailsForm" styleClass="black_ar" cols="90" rows="3" property="administratorComments"  />
		 			</td>
					<td valign="top" class="black_ar"></td>
              </tr>
 			<!--tr>
				<logic:notEqual  name="requestDetailsForm" property="isDirectDistribution" value="true">
                <td class="noneditable"><strong>
								<bean:message key='requestdetails.header.label.Comments'/>
								<bean:write name="<%=  Constants.REQUEST_HEADER_OBJECT  %>"  property="comments"  scope="request" />
							</strong></td>
                <td class="noneditable">&nbsp;</td>
				</logic:notEqual>
              </tr-->
          </table></td>
        </tr>
		 </table>
	     <table width="100%" border="0" cellpadding="0" cellspacing="0" class="whitetable_bg">
        <tr>
          <td align="left">
		<!-----------------------table for the tabs------------>
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="13%" style="border-bottom:1px solid #61a1e3" >&nbsp;</td>
                <td width="6%" valign="bottom" onclick="gotoSpecimenRequestTab()" id="specimenRequestTab" >
							<img src="images/uIEnhancementImages/tab_specimentR2_user.gif" alt="Specimen Request" width="124" height="20" />
						</td>
			            <td width="6%" valign="bottom" onClick="gotoArrayRequestTab()" id="arrayRequestTab" >
							<img src="images/uIEnhancementImages/tab_arrayR2_user1.gif" alt="Array Request" width="101" height="20" />
						</td>
			            <td valign="bottom" style="border-bottom:1px solid #61a1e3">&nbsp;</td>
              </tr>
          </table></td>
        </tr>

		  <tr >
          <td colspan="4"><table width="100%" border="0" cellpadding="3" cellspacing="0">
              <tr>
                <td colspan="3" align="left" class="showhide" style="border-bottom:1px solid #e3e2e2 "><table width="100%" border="0" cellspacing="0" cellpadding="4">

					<tr>
						<td >
					<div id="specimenDataTab">
						<table border="0" width="100%" cellpadding="3" cellspacing="0">
						<tr>
						<td colspan="11" align="right" valign="top">
							<img src="images/uIEnhancementImages/viewall_icon.gif" alt="View All" />

							<a href="javascript:showAllSpecimen('<%=count%>')" class="view" >
													<bean:message key="requestdetails.link.ViewAllConsents" />
							                    </a>
						</td>
					</tr>
					<tr>
						<td class="bottomtd" colspan="11"></td>
					</tr>
					<tr>
						  <%
									if(requestDetailsList != null)
									{
									    session.setAttribute(Constants.REQUEST_DETAILS_LIST,requestDetailsList);
									 	int i = 0;
										String rowStatusValue ="";
						 %>
								<td colspan="3" align="center" valign="top" class="tableheading" width="30%">
									<strong>
									<bean:message key="requestdetails.header.orderedSpecimenDetails" />
									</strong>
								</td>
					             <td colspan="3" align="center" valign="top" class="tableheading" width="30%">
									<strong>
										<bean:message key="requestdetails.header.RequestedSpecimenDetails" />
									</strong>
								 </td>

				                <td width="10%" rowspan="2" valign="top" class="tableheading">
									<strong>
										<bean:message key="consent.consentforspecimen"/>
									</strong>
								</td>
				                <td width="18%" rowspan="2" valign="top" class="tableheading">
									<strong>
										<bean:message key='requestdetails.datatable.label.AssignStatus'/>
									</strong>
									</br>
					                <span class="black_new">
									     <html:select property="status" name="requestDetailsForm" styleClass="formFieldSized11" styleId="nextStatusId" size="1" onmouseover="showTip(this.id)" onmouseout="hideTip(this.id)" onchange="<%=checkQuantityforAll%>">
											<html:options collection="<%= Constants.REQUESTED_ITEMS_STATUS_LIST %>" labelProperty="name" property="value" />
									     </html:select>
								</td>
				                <td rowspan="2" valign="top" class="tableheading" width="10%">
									<strong>
										<bean:message key="requestdetails.header.label.Comments" />
									</strong>
								</td>
				              </tr>
				              <tr>
								<td width="15%" class="subtd" >
									<bean:message key='requestdetails.datatable.label.RequestItem'/>
								</td>
								<td class="subtd" width="12%">
								<bean:message key="orderingSystem.tableheader.label.type" />,
								<bean:message key='requestdetails.datatable.label.AvailableQty'/>
								</td>
								<!--<td class="subtd" width="10%">

								</td>-->
								<td bgcolor="#d7d7d7" width="2%">&nbsp;
								</td>

								<td width="17%" class="subtd"  valign="top">
									<bean:message key='requestdetails.datatable.label.RequestFor'/>
								</td>

								<td width="12%" class="subtd"  valign="top">
									<strong>
										<bean:message key="orderingSystem.tableheader.label.type" />,
										<bean:message key='requestdetails.datatable.label.AvailableQty'/>
									</strong>
								</td>

				                <td width="1%" align="left" bgcolor="#d7d7d7" >&nbsp;
								</td>
				              </tr>
   <!----------------rows for the specimen request tab------------------>
							  <tbody id="tbody">
				<%          int rows=0;
				%>
					<logic:iterate id="requestDetailsBeanObj" collection="<%= requestDetailsList%>" type="edu.wustl.catissuecore.bean.RequestDetailsBean">
				<%			String fontStyle="black_ar";
							String bgStyle="background-color:#ffffff;";
							rows++;
							if(rows<=count)
							if(rows%2 == 0)
							{
								fontStyle="tabletd1";
								bgStyle="background-color:#f6f6f6;";
							}

						 	String requestFor = "value(RequestDetailsBean:"+i+"_requestFor)";
						 	String assignQty = "value(RequestDetailsBean:"+i+"_assignedQty)";
						 	String assignStatus = "value(RequestDetailsBean:"+i+"_assignedStatus)";
							String description = "value(RequestDetailsBean:"+i+"_description)";
							String instanceOf = "value(RequestDetailsBean:"+i+"_instanceOf)";
							String orderItemId = "value(RequestDetailsBean:"+i+"_orderItemId)";
							String specimenIdInMap = "value(RequestDetailsBean:"+i+"_specimenId)";
							String consentVerificationkey = "value(RequestDetailsBean:"+i+"_consentVerificationkey)";
							String rowStatuskey = "value(RequestDetailsBean:"+i+"_rowStatuskey)";
							String specimenQuantityUnit = "value(RequestDetailsBean:"+i+"_specimenQuantityUnit)";
							String labelStatus="labelStatus"+i;
							String selectedSpecimenType="value(RequestDetailsBean:"+i+"_selectedSpecimenType)";
							String selectedSpecimenQuantity="value(RequestDetailsBean:"+i+"_selectedSpecimenQuantity)";
							String requestedItem = "value(RequestDetailsBean:"+i+"_requestedItem)";
							String requestedQty = "value(RequestDetailsBean:"+i+"_requestedQty)";
							String availableQty = "value(RequestDetailsBean:"+i+"_availableQty)";
							String spClass = "value(RequestDetailsBean:"+i+"_className)";
							String spType = "value(RequestDetailsBean:"+i+"_type)";
							String distributedItemId = "value(RequestDetailsBean:"+i+"_distributedItemId)";
							String specimenList = "requestFor(RequestForDropDownList:"+i+")";
							String specimenCollGroupId = "value(RequestDetailsBean:"+i+"_specimenCollGroupId)";
							String actualSpecimenType = "value(RequestDetailsBean:"+i+"_actualSpecimenType)";
							String actualSpecimenClass = "value(RequestDetailsBean:"+i+"_actualSpecimenClass)";
							String canDistribute = "value(RequestDetailsBean:"+i+"_canDistribute)";
							String specimenClickFunction = "showSpecimenDetails("+i+")";
							String viewSpecimenDetailsFunction = "viewSpecimenDetails("+requestDetailsBeanObj.getSpecimenId()+")";
							String createSpecimenFunction = "createSpecimen("+i+")";
							String aliquoteClickFunction = "createAliquots("+i+")";
							String derivativeCreateFunction = "createDerivatives("+i+")";
							String changeAvailableQuantity = " onSpecimenChange("+i+")";
							String checkQuantity = "checkQuantity("+i+")";
							//added for consent page:
							String showNewConsentPageFunction = "showNewConsentPage("+requestDetailsBeanObj.getSpecimenId()+")";
							boolean disableRow = false;
							if(((((String)(requestDetailsForm.getValue("RequestDetailsBean:"+i+"_assignedStatus"))).trim().equalsIgnoreCase(Constants.ORDER_REQUEST_STATUS_DISTRIBUTED)) || (((String)(requestDetailsForm.getValue("RequestDetailsBean:"+i+"_assignedStatus"))).trim().equalsIgnoreCase(Constants.ORDER_REQUEST_STATUS_DISTRIBUTED_AND_CLOSE)))
							&& (!((String)(requestDetailsForm.getValue("RequestDetailsBean:"+i+"_distributedItemId"))).trim().equals("")))
							{
										disableRow=true;
										disabledStatus=i;
										rowStatusValue =	"disable";
				%>

								<html:hidden name="requestDetailsForm" property="<%= assignStatus %>" />
								<html:hidden name="requestDetailsForm" property="<%= requestFor %>" />
								<html:hidden name="requestDetailsForm" property="<%= description %>" />
								<html:hidden name="requestDetailsForm" property="<%= assignQty %>" />

								<!--	<html:hidden name="requestDetailsForm" property="<%= consentVerificationkey %>"/>	-->
				<%
							}
							else{ rowStatusValue =	"enable";
							}
								 	//Added By Ramya.Construct corresponding rowids for request row expansion purpose.
							String data = "data" + i;
							String switchText = "switch" + i;
							//Added By Ramya.Construct select element ids for corresponding rows.
									//This is to update all rows' status in one shot.
							String select = "select_" +	i;
									//This is to update available qty for the specimen selected from requestFor drop down.
							String updateAvaiQty = "avaiQty" + i;
							String availableQtyId = "availableQtyId"+i;
							String selectedSpecimenTypeId = "selectedSpecimenTypeId"+i;
							String specimenQuantityUnitId = "specimenQuantityUnitId"+i;
							String requestedQtyId = "requestedQtyId"+i;
							String requestForId = "requestFor" + i;
							String onChangeValueForRequestFor = "updateQuantity('"+ requestForId  +"')";
							if(((requestDetailsForm.getValue("RequestDetailsBean:"+i+"_specimenId"))) != null && !((String)(requestDetailsForm.getValue("RequestDetailsBean:"+i+"_specimenId"))).equals(""))
							{
				%>
								<html:hidden name="requestDetailsForm" property="<%= specimenIdInMap %>" />
				<%			}
							else
							{
				%>
								<html:hidden name="requestDetailsForm" property="<%= specimenCollGroupId %>" />
				<%			}
			    %>	<!-- Html hidden variables for all static fields -->

								 <html:hidden name="requestDetailsForm" property="<%= requestedItem %>" />
								 <html:hidden name="requestDetailsForm" property="<%= requestedQty %>" />
								 <html:hidden name="requestDetailsForm" property="<%= availableQty %>" />
								 <html:hidden name="requestDetailsForm" property="<%= spClass %>" />
								 <html:hidden name="requestDetailsForm" property="<%= spType %>" />
								 <html:hidden name="requestDetailsForm" property="<%= actualSpecimenClass %>" />
								 <html:hidden name="requestDetailsForm" property="<%= actualSpecimenType %>" />
								 <html:hidden name="requestDetailsForm" property="<%=canDistribute%>"  styleId="<%=canDistribute%>" />

								<tr>
									<td class="<%=fontStyle%>" >
									<%													                                                     if(((String)(requestDetailsForm.getValue("RequestDetailsBean:"+i+"_instanceOf"))).trim(            ).equalsIgnoreCase("Derived"))
								{
					%>
										<img src="images/Distribution.GIF" border="0"/>

									 		<!-- bean:write name="requestDetailsBeanObj" property="requestedItem" /-->
			 		<%			}
								else																					if(((String)(requestDetailsForm.getValue("RequestDetailsBean:"+i+"_instanceOf"))			).trim().equalsIgnoreCase("DerivedPathological")
										|| ((String)(requestDetailsForm.getValue("RequestDetailsBean:"+i+"_instanceOf"))).trim().equalsIgnoreCase("Pathological"))
									{
					%>
										<img src="images/Participant.GIF" border="0"/>
					<%				}
										String toolTipTypeClass = "Type:"+((String)(requestDetailsForm.getValue("RequestDetailsBean:"+i+"_actualSpecimenType")))+", Quantity:"+((String)(requestDetailsForm.getValue("RequestDetailsBean:"+i+"_availableQty")).toString()); %>

					<%				if(((String)(requestDetailsForm.getValue("RequestDetailsBean:"+i+"_instanceOf"))).trim().equalsIgnoreCase("DerivedPathological")
												|| ((String)(requestDetailsForm.getValue("RequestDetailsBean:"+i+"_instanceOf"))).trim().equalsIgnoreCase("Pathological"))
									{
					%>

											<bean:write name="requestDetailsForm" property="<%= requestedItem%>" />
					<%
									}
									else
									{
					%>
									<span title="<%= toolTipTypeClass %>">
										<html:link href="#" styleClass="view" styleId="label" onclick="<%=viewSpecimenDetailsFunction%>">
									 		<bean:write name="requestDetailsForm" property="<%= requestedItem %>" />
										</html:link>
					<%
									}
									String className = ((String)(requestDetailsForm.getValue("RequestDetailsBean:"+i+"_className")));
									String type = ((String)(requestDetailsForm.getValue("RequestDetailsBean:"+i+"_type")));
					%>
							 		</span>
									</td>

			 						<td class="<%=fontStyle%>" >
										<bean:write name="requestDetailsForm" property="<%= spType %>" />,

					<%
										if((((String)(requestDetailsForm.getValue("RequestDetailsBean:"+i+"_instanceOf"))).trim().equalsIgnoreCase("Existing")||((String)(requestDetailsForm.getValue("RequestDetailsBean:"+i+"_instanceOf"))).trim().equalsIgnoreCase("Pathological"))&&(((String)(requestDetailsForm.getValue("RequestDetailsBean:"+i+"_distributedItemId"))).trim().equals("")))
										{
											disableRow=false;
										}

					%>
										</br>
											<html:text styleClass="formFieldSmallNoBorder" styleId="<%=requestedQtyId%>" property="<%= requestedQty %>" readonly="true" style="<%=bgStyle%>"/>
											<span>
													<script>
															var v= getUnit('<%= className %>','<%= type %>');
															document.write(v);
														</script>
											</span>
										</td>

									<td class="<%=fontStyle%>">
									</td>


								<td class="<%=fontStyle%>" >


										<html:select property="<%= requestFor %>" name="requestDetailsForm" size="l" styleClass="formFieldSizedText" styleId="<%= requestForId %>" onmouseover="showTip(this.id)" onmouseout="hideTip(this.id)" onchange="<%= changeAvailableQuantity%>" disabled="<%= disableRow %>">
										<html:optionsCollection property="<%=specimenList%>" name="requestDetailsForm" label="name" value="value"/>
										</html:select>
										</br>
										<logic:equal name="requestDetailsForm" property="<%=rowStatuskey%>" value="enable">

											<logic:equal name="requestDetailsForm" property="<%=instanceOf%>" value="Pathological">
												<a href="#" onclick="<%=createSpecimenFunction%>">
												   <img src="images/Cycle_col.gif" border="0" alt="Create Specimen"  title="Create Specimen"/>
												</a>
											</logic:equal>
										<logic:equal name="requestDetailsForm" property="<%=instanceOf%>" value="DerivedPathological">
											<a href="#" onclick="<%=createSpecimenFunction%>">
												   <img src="images/Cycle_col.gif" border="0" alt="Create Specimen"  title="Create Specimen"/>
												</a>
										</logic:equal>

										<a href="#" onclick="<%=specimenClickFunction%>">
											<img src="images/uIEnhancementImages/ic_specimen.gif" border="0" alt="View Specimen"  title="View Specimen"/>
										</a>
										<a href="#" onclick="<%=aliquoteClickFunction%>">
											<img src="images/uIEnhancementImages/a.gif" border="0"  alt="Create Aliquot" title="Create Aliquot"/>
										</a>
										<a href="#" onclick="<%=derivativeCreateFunction%>">
											<img src="images/uIEnhancementImages/ic_d.gif" border="0" alt="Create Derivative"  title="Create Derivative"/>
										</a>
										</logic:equal>

		  					 	</td>

							 	<td class="<%=fontStyle%>">


									<html:text styleClass="formFieldSmallNoBorderlargeSize" styleId="<%=selectedSpecimenTypeId%>" property="<%= selectedSpecimenType %>" readonly="true" style="<%=bgStyle%>"/>
										</br>

									<html:text styleClass="formFieldSmallNoBorder" styleId="<%=availableQtyId%>" property="<%=selectedSpecimenQuantity%>" readonly="true" style="<%=bgStyle%>" />

									<html:text styleClass="formFieldSmallNoBorder" styleId="<%=specimenQuantityUnitId%>" property="<%=specimenQuantityUnit%>" readonly="true" style="<%=bgStyle%>"/>

								</td>

								<td class="<%=fontStyle%>">
									</td>


			   <%
								String consentVerificationStatus=
										((String)(requestDetailsForm.getValue("value(RequestDetailsBean:"+i+"_consentVerificationkey)")));
								String specimenIdValue=((String)(requestDetailsForm.getValue("value(RequestDetailsBean:"+i+"_specimenId)")));

				%>


								<td class="<%=fontStyle%>"  >
									<span class="view">
									<html:hidden property="<%=specimenIdInMap%>" styleId="<%=specimenIdInMap%>"  value="<%=specimenIdValue%>"/>
									<html:hidden property="<%=rowStatuskey%>" styleId="<%=rowStatuskey%>"  value="<%=rowStatusValue%>"/>


				<%
					        if(
							((String)(requestDetailsForm.getValue("RequestDetailsBean:"+i+"_instanceOf"))).trim().equalsIgnoreCase("Pathological") ||((String)(requestDetailsForm.getValue("RequestDetailsBean:"+i+"_instanceOf"))).trim().equalsIgnoreCase("DerivedPathological"))
							{

				%>
								<%=Constants.NO_CONSENTS%>
								<html:hidden property="<%=consentVerificationkey%>" styleId="<%=consentVerificationkey%>"  value="<%=Constants.NO_CONSENTS%>"/>

				<%			}
				%>

				<%
							if(((String)(requestDetailsForm.getValue("RequestDetailsBean:"+i+"_instanceOf"))).trim().equalsIgnoreCase("Derived") ||((String)(requestDetailsForm.getValue("RequestDetailsBean:"+i+"_instanceOf"))).trim().equalsIgnoreCase("Existing"))
							{
				%>

				<%
								if(!disableRow)
								{
				%>
									<logic:equal name="requestDetailsForm" property="<%=consentVerificationkey%>" value="<%=Constants.NO_CONSENTS%>">
											<%=Constants.NO_CONSENTS%>
											<html:hidden property="<%=consentVerificationkey%>" styleId="<%=consentVerificationkey%>"  value="<%=Constants.NO_CONSENTS%>"/>
									</logic:equal>
									<logic:notEqual name="requestDetailsForm" property="<%=consentVerificationkey%>" value="<%=Constants.NO_CONSENTS%>">
											<a  id="<%=labelStatus%>" class="view" href="javascript:showNewConsentPage('<%=specimenIdInMap%>','<%=labelStatus%>','<%=consentVerificationkey%>','<%=i%>')">
									<logic:notEmpty name="requestDetailsForm" property="<%=consentVerificationkey%>">
												<bean:write name="requestDetailsForm" property="<%=consentVerificationkey%>"/>
									</logic:notEmpty>
									<logic:empty name="requestDetailsForm" property="<%=consentVerificationkey%>">
												<%=Constants.VIEW_CONSENTS %>
									</logic:empty>
									<logic:notEmpty name="requestDetailsForm" property="<%=consentVerificationkey%>">
												<html:hidden property="<%=consentVerificationkey%>" styleId="<%=consentVerificationkey%>"  value="<%=consentVerificationStatus%>"/>
									</logic:notEmpty>
									<logic:empty name="requestDetailsForm" property="<%=consentVerificationkey%>">
												<html:hidden property="<%=consentVerificationkey%>" styleId="<%=consentVerificationkey%>"  value="<%=Constants.VIEW_CONSENTS %>"/>
									</logic:empty>
										   </a>
									</logic:notEqual>
				<%
								}
								else
								{
				%>
										<%=Constants.VERIFIED%>
										<html:hidden property="<%=consentVerificationkey%>" styleId="<%=consentVerificationkey%>"  value="<%=Constants.VERIFIED%>"/>
				<%
								}
				%>				</span>
								</td>
				<%		}
				%>

							 	<td class="<%=fontStyle%>" >	<span class="black_new">
								 		<html:select property="<%=assignStatus %>" name="requestDetailsForm" styleClass="formFieldSized11" styleId="<%=select%>" onmouseover="showTip(this.id)" onmouseout="hideTip(this.id)" disabled="<%= disableRow %>" onclick="<%=checkQuantity%>">
					 					<html:options collection="<%=Constants.ITEM_STATUS_LIST%>" labelProperty="name" property="value"/>		</html:select>
										</span>
									 	</td>
										<td class="<%=fontStyle%>" >
											<html:textarea styleId="description" styleClass="black_ar"  property="<%= description%>" cols='20' rows='2' disabled="<%= disableRow %>"/>
								</td>
							 </tr>

								  <!-- Block for row expansion ends here -->
								  <!--  Flag for instanceOf  -->

										<html:hidden name="requestDetailsForm" property="<%= instanceOf %>" />
										<html:hidden name="requestDetailsForm" property="<%= orderItemId %>" />
										<html:hidden name="requestDetailsForm" property="<%= distributedItemId %>" />
									 <% i++; %>
					 </logic:iterate>
				<%	 } //End Outer IF
				%>
						</table>

						</div>

						<div id="arrayDataTable" style="display:none" >
		<%-- Include ArrayRequest page for ArrayRequests tab --%>
						<%@ include file="/pages/content/orderingsystem/ArrayRequests.jsp" %>
						</div>

					</td>
				</tr>
				</table>
				</td>
				</tr>
             <!-- <tr >
                <td valign="top" class="black_ar">&nbsp;
						<bean:message key='requestlist.dataTabel.label.Site'/>
 					</td>
					<td valign="top" class="black_new">
						<html:select property="site" name="requestDetailsForm" styleClass="formFieldSized51" styleId="siteId" size="1" onmouseover="showTip(this.id)" onmouseout="hideTip(this.id)">
							<html:options collection="<%= Constants.SITE_LIST_OBJECT  %>" labelProperty="name" property="value"/>
						</html:select>
					</td>
					<td valign="top" class="black_ar"></td>


        			</tr>
        				 -->
		<%
			  String operationUpdate = "update";
			  String distributionProtocol = ((RequestViewBean)request.getAttribute(Constants.REQUEST_HEADER_OBJECT)).getDistributionProtocolId();
			  String orderName = ((RequestViewBean)request.getAttribute(Constants.REQUEST_HEADER_OBJECT)).getOrderName();
			  String requestedDate = ((RequestViewBean)request.getAttribute(Constants.REQUEST_HEADER_OBJECT)).getRequestedDate();
		%>
						<html:hidden name="requestDetailsForm" property="id" />
						<html:hidden name="requestDetailsForm" property="operation" value="<%= operationUpdate %>"/>
						<html:hidden name="requestDetailsForm" property="distributionProtocolId" value="<%= distributionProtocol %>"/>
						<html:hidden name="requestDetailsForm" property="orderName" styleId="orderName" value="<%= orderName %>"/>
						<html:hidden name="requestDetailsForm" property="tabIndex" styleId="tabIndexId"/>
						<html:hidden name="requestDetailsForm" property="mailNotification" styleId="mailNotification"/>
						<html:hidden name="requestDetailsForm" property="isDirectDistribution" styleId="isDirectDistribution"/>
						<html:hidden name="requestDetailsForm" property="requestedDate" styleId="requestedDate" value="<%= requestedDate %>"/>


          </table></td>
        </tr>
        <tr>
          <td class="bottomtd"></td>
        </tr>
        <tr>
          <td class="buttonbg" style="padding:6px;">
					<input type="button" class="blue_ar_b" value="Submit" onclick="submitPage()" accesskey="Enter">
					<input type="button" class="blue_ar_c" value="Submit And Notify" onclick="submitAndNotify()">
					</td>
				</tr>
      </table>
    </td>
  </tr>
</table>
   <!-- main table ends here -->
</html:form>
</body>

<!----------------------------------------------->