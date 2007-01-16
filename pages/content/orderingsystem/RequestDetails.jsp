<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ page import="edu.wustl.catissuecore.util.global.Constants" %>
<%@ page import="edu.wustl.common.util.global.ApplicationProperties" %>
<%@ page import="java.util.List"%>
<%@ page import="edu.wustl.catissuecore.bean.RequestViewBean" %>

<html:messages id="messageKey" message="true" header="messages.header" footer="messages.footer">
	<%=messageKey%>
</html:messages>
<html:errors/>

<head>
<script language="JavaScript" type="text/javascript" src="jss/javaScript.js"></script>
<script language="JavaScript" type="text/javascript" src="jss/ajax.js"></script>
<script language="JavaScript" type="text/javascript" src="jss/OrderingSystem.js"></script>
<script language="javascript">
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
 </script>
</head>  
<body>
	<html:form action="<%=Constants.SUBMIT_REQUEST_DETAILS_ACTION%>" >
		<table summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" id="table1_OrderRequestHeader">
		<tr>
			<td>
				<table summary="" cellpadding="3" cellspacing="0" border="0" width="100%">				
				<tr>
				    <jsp:useBean id="requestDetailsForm" class="edu.wustl.catissuecore.actionForm.RequestDetailsForm" scope="request"/>	
				  	<!-- Order Request Header Begins -->
					<td class="dataTablePrimaryLabel" height="20" colspan="5">
						<bean:message key='requestdetails.header.orderRequestName.label'/> <bean:write name="<%= Constants.REQUEST_HEADER_OBJECT  %>" property="orderName" scope="request" />
					</td>
					
				</tr>
				<tr>
					<td class="formMessage" colspan="5">
						<bean:message key="requiredfield.message"/>  
					</td>
				</tr>
				<tr>
					<td class="formRequiredLabel" width="30%" style="border-left:1px solid #5C5C5C;border-top:1px solid #5C5C5C"><bean:message key='requestdetails.header.label.RequestorName'/> : <a href='mailto:<bean:write name='<%=  Constants.REQUEST_HEADER_OBJECT  %>' property='email' scope='request'/>' ><bean:write name="<%=  Constants.REQUEST_HEADER_OBJECT %>" property="requestedBy" scope="request"/> <a/></td>
									 
					<td class="formRequiredLabel" width="30%" style="border-top:1px solid #5C5C5C"><bean:message key='requestlist.dataTabel.label.RequestDate'/> : <bean:write name="<%=  Constants.REQUEST_HEADER_OBJECT  %>" property="requestedDate" scope="request"/> </td>
					
					<td class="formRequiredLabel" width="40%" style="border-top:1px solid #5C5C5C"><bean:message key='requestlist.dataTabel.DistributionProtocol.label'/> : <bean:write name="<%=  Constants.REQUEST_HEADER_OBJECT  %>" property="distributionProtocol" scope="request"/></td>
					
					
				</tr>
				<tr>
			   
					<td class="formRequiredLabel" colspan="" style="border-left:1px solid #5C5C5C"><bean:message key='requestdetails.header.label.Comments'/> : <html:textarea name="<%=  Constants.REQUEST_HEADER_OBJECT  %>" styleClass="formFieldSized15" cols="300" rows="2" property="comments"  readonly="true" /> <!-- <bean:write name="<%=  Constants.REQUEST_HEADER_OBJECT  %>" property="comments" scope="request" /> </td> -->
					
				 	<td class="formRequiredLabel" align="right" width="30%">*
						<bean:message key='requestlist.dataTabel.label.Site'/> : 
						<html:select property="site" name="requestDetailsForm" styleClass="formFieldSized15" styleId="siteId" size="1" 
						 			onmouseover="showTip(this.id)" onmouseout="hideTip(this.id)">
							<html:options collection="<%= Constants.SITE_LIST_OBJECT  %>" labelProperty="name" property="value"/>		
						</html:select> 						
					</td>		
					
					<td class="formRequiredLabel" align="right" width="30%">
						<bean:message key='requestlist.dataTabel.label.Status'/> : 
						<html:select property="status" name="requestDetailsForm" styleClass="formFieldSized15" styleId="nextStatusId" size="1" 
						 			onmouseover="showTip(this.id)" onmouseout="hideTip(this.id)">
							<html:options collection="<%= Constants.REQUESTED_ITEMS_STATUS_LIST %>" labelProperty="name" property="value"/>		
						</html:select> 
						<html:button value="Update All" property="changeStatusButton" styleClass="actionButton" onclick="updateAllStatus()" title="<%= ApplicationProperties.getValue("requestdetails.tooltip.updateAllRequestStatus") %>">
					</html:button>
					</td>
					
				</tr>
				</table>
			    <!-- Order Request Header Ends -->
		     </td>
	    </tr>
        </table> 
        <!-- Tab Page For Specimen requests-->
		<table summary="" cellpadding="0" cellspacing="0" border="0" height="20" class="tabPage" width="100%" id="table2_TabPage">
		<tr>
			<td height="20" class="tabMenuItemSelected" onclick="gotoSpecimenRequestTab()" id="specimenRequestTab">
				<bean:message key='requestdetails.tabname.label.specimenRequests'/>
			</td>
			<td height="20" class="tabMenuItem" onmouseover="changeMenuStyle(this,'tabMenuItemOver'),showCursor()" onmouseout="changeMenuStyle(this,'tabMenuItem'),hideCursor()" onClick="gotoArrayRequestTab()" id="arrayRequestTab">
				<bean:message key='requestdetails.tabname.label.arrayRequests'/>
			</td>	
			<td width="450" class="tabMenuSeparator" colspan="3" >
				&nbsp;
			</td>
			
		</tr>
		<!-- Request Details List  -->
		
		<tr>
			<td class="tabField" colspan="6">
			<table summary="" cellpadding="3" cellspacing="0" border="0" class="contentPage" width="100%" id="table3_specimenDataTab">
				<tr>
					<td>
					<table summary="" cellpadding="0" cellspacing="0" border="0" width="100%" >
						<tr>
							<td>
						 	<table summary="" cellpadding="3" cellspacing="0" border="0" class="dataTable" width="100%">
								 <tr>	
									<th class="dataTableHeader" scope="col" align="left" width="30%" colspan='2'>
										<bean:message key='requestdetails.datatable.label.RequestItem'/>
									</th>
								
									<th class="dataTableHeader" scope="col" align="left" width="25%">
										<bean:message key='requestdetails.datatable.label.RequestFor'/>
									</th>
								
									<th class="dataTableHeader" scope="col" align="left" width="5%">
										<bean:message key='requestdetails.datatable.label.RequestedQty'/>
									</th>
								
									<th class="dataTableHeader" scope="col" align="left" width="5%" colspan="2">
										<bean:message key='requestdetails.datatable.label.AvailableQty'/>
									</th>
								
									<th class="dataTableHeader" scope="col" align="left" width="10%">
										<bean:message key='requestdetails.datatable.label.AssignQty'/>
									</th>
								
									<th class="dataTableHeader" scope="col" align="left" width="20%">
										<bean:message key='requestdetails.datatable.label.AssignStatus'/>
									</th>					
							 	 </tr>						 									
								 <% 
								 	List requestDetailsList = (List) request.getAttribute(Constants.REQUEST_DETAILS_LIST);						 	
									
									if(requestDetailsList != null)
									{
									    session.setAttribute(Constants.REQUEST_DETAILS_LIST,requestDetailsList);
			
									 	int i = 0; 
								 %>
								 	<tbody id="tbody">									 	
								 <logic:iterate id="requestDetailsBeanObj"  collection="<%= requestDetailsList%>" type="edu.wustl.catissuecore.bean.RequestDetailsBean">
								 <% 
								 	String requestFor = "value(RequestDetailsBean:"+i+"_requestFor)"; 
								 	String assignQty = "value(RequestDetailsBean:"+i+"_assignedQty)"; 
								 	String assignStatus = "value(RequestDetailsBean:"+i+"_assignedStatus)"; 	
									String description = "value(RequestDetailsBean:"+i+"_description)";
									String instanceOf = "value(RequestDetailsBean:"+i+"_instanceOf)";
									String orderItemId = "value(RequestDetailsBean:"+i+"_orderItemId)";
									String specimenIdInMap = "value(RequestDetailsBean:"+i+"_specimenId)";
									boolean disableRow = false;
									if(requestDetailsBeanObj.getAssignedStatus().trim().equalsIgnoreCase(Constants.ORDER_REQUEST_STATUS_DISTRIBUTED))
									{
										disableRow=true;
									}
								 	//Added By Ramya.Construct corresponding rowids for request row expansion purpose.								 	
									String data = "data" + i;
									String switchText = "switch" + i;
									
									//Added By Ramya.Construct select element ids for corresponding rows.
									//This is to update all rows' status in one shot.
									String select = "select_" +	i;
									//This is to update available qty for the specimen selected from requestFor drop down.
									String updateAvaiQty = "avaiQty" + i;
									String requestForId = "requestFor" + i;
									String onChangeValueForRequestFor = "updateQuantity('"+ requestForId  +"')";
									
								 %>		 
								  <tr class="dataRowLight">
									 	<td class="dataCellText">
											<a id="<%=switchText%>" style="text-decoration:none" href="javascript:switchOrderList('<%=i%>');">  
									 		<img src="images/nolines_plus.gif" border="0"/>
									 	</td> 
										<td class="dataCellText" width="30%">
										<% if(requestDetailsBeanObj.getInstanceOf().trim().equalsIgnoreCase("Derived"))
											{
											%>											
											<img src="images\Distribution.GIF" border="0"/>
											<%   }
									 		%>
									 		<bean:write name="requestDetailsBeanObj" property="requestedItem" />									 	
									 	</td>
									 	<%
									 		if(requestDetailsBeanObj.getInstanceOf().trim().equalsIgnoreCase("Existing"))
									 		{
									 			disableRow=true;
									 		}%>
									 	<!-- Added By Ramya for Tree View -->
									 	<td class="formField" width="20%">
									 		<html:select property="<%= requestFor %>" name="requestDetailsForm" styleClass="formFieldSized10" styleId="<%= requestForId %>" 					 								
									 		 	onmouseover="showTip(this.id)" onmouseout="hideTip(this.id)" disabled="<%= disableRow%>" onchange="<%= onChangeValueForRequestFor %>">
									 			<html:optionsCollection property="specimenList" name="requestDetailsBeanObj" label="name" value="value"/>
									 		</html:select>
									 		<bean:define id="specimenType" name="requestDetailsBeanObj" property="type" type="java.lang.String"/>
											<bean:define id="specimenClass" name="requestDetailsBeanObj" property="className" type="java.lang.String"/>
											<bean:define id="specimenObjId" name="requestDetailsBeanObj" property="specimenId" type="java.lang.String"/>
											<bean:define id="specimenCollGrpId" name="requestDetailsBeanObj" property="specimenCollGroupId" type="java.lang.String" />
									 		<%
									 		
									 			if(!specimenObjId.equals(""))
										 			session.setAttribute(Constants.SPECIMEN_TREE_SPECIMEN_ID,specimenObjId);
										 		else
										 			session.setAttribute(Constants.SPECIMEN_TREE_SPECCOLLGRP_ID,specimenCollGrpId);
										 			
												String url = "ShowFramedPage.do?pageOf=pageOfSpecimenTree&propertyName=" + requestFor + "&type=" + specimenType + "&specimenClass=" + specimenClass;
												String appletVar = "";
												if(disableRow == false)
													appletVar = "javascript:NewWindow('"+url+"','name','375','330','yes');return false";
											%>
											
											<a href="#" onclick="<%= appletVar %>">
												<img src="images\Tree.gif" border="0" width="24" height="18" title="<%= ApplicationProperties.getValue("requestdetails.tooltip.specimenTreeTooltip") %>">
											</a>
									 	</td>
									 	<%
									 	if((requestDetailsBeanObj.getInstanceOf().trim().equalsIgnoreCase("Existing") || requestDetailsBeanObj.getInstanceOf().trim().equalsIgnoreCase("Pathological")) && !requestDetailsBeanObj.getAssignedStatus().trim().equalsIgnoreCase(Constants.ORDER_REQUEST_STATUS_DISTRIBUTED))
									 		{
									 			disableRow=false;												
									 		}
									 		String className = requestDetailsBeanObj.getClassName();
									 		String type = requestDetailsBeanObj.getType();
									 	%>
									 	<td class="dataCellText">										 		
									 		<bean:write name="requestDetailsBeanObj" property="requestedQty" />
									 		<span>		
												<script>
													var v= getUnit('<%= className %>','<%= type %>');
													document.write(v);
												</script>
											</span>	
									 	</td>
									 	<td class="dataCellText" style="border-right:0px">	
										 	<div id="<%= updateAvaiQty %>">									 		
										 		<bean:write name="requestDetailsBeanObj" property="availableQty" />
										 	</div>
										 </td>
										 <td class="dataCellText">
									 		<span>		
												<script>													
													var v= getUnit('<%= className %>','<%= type %>');
													document.write(v);
												</script>
											</span>	
									 	</td>
									 	<%									 		
									 		String assignedQtyValue = requestDetailsBeanObj.getAssignedQty() ;									 		
									 	%>	
									 	<td class="dataCellText"> 										 		
									 		<html:text name="requestDetailsForm" styleClass="formFieldSized3" maxlength="4"  styleId="<%=assignQty%>" property="<%=assignQty %>" disabled="<%= disableRow %>" value="<%= assignedQtyValue %>"/>
									 		<span>		
												<script>
													var v= getUnit('<%= className %>','<%= type %>');
													document.write(v);
												</script>
											</span>	
									 	</td>
									 	
									 	<td class="dataCellText" width="30%">									 											 		
									 		<html:select property="<%=assignStatus %>" name="requestDetailsForm" styleClass="formFieldSized15" styleId="<%=select%>"  
					 								onmouseover="showTip(this.id)" onmouseout="hideTip(this.id)" disabled="<%= disableRow %>">
					 									<%if((requestDetailsBeanObj.getInstanceOf().trim().equalsIgnoreCase("Derived") || requestDetailsBeanObj.getInstanceOf().trim().equalsIgnoreCase("Pathological")) && (requestDetailsBeanObj.getSpecimenList().size() == 0))
					 								{
					 								 %>
					 								 	<html:options collection="<%=Constants.ITEM_STATUS_LIST_WO_DISTRIBUTE%>" labelProperty="name" property="value"/>											 				   
					 								 <%}else{%>					 								
						 								<html:options collection="<%=Constants.ITEM_STATUS_LIST%>" labelProperty="name" property="value"/>											 				   
					 								<%}%>
									 		</html:select>
									 	</td>
									 </tr>
									 
									<!-- Added By Ramya -->
									<!-- Block for row expansion starts here -->
									
									 <tr id="<%=data%>" style="display:none">
									   <td colspan='8'>
									   	<table class="rowExpansionTable" width="100%"> 
									   		
									   		<tr>
									   			<td width="15%" nowrap>
									   				<label for="LabelType">
									   					<bean:message key="specimen.type" />
									   				</label> : <bean:write name="requestDetailsBeanObj" property="className" />
									   			</td>
									   			<td width="10%" nowrap>
									   				<label for="LabelDescription">
									    				<bean:message key="orderingSystem.requestdetails.label.description" />
									   				</label> : 				
									   			</td>
												<td rowspan='2' nowrap>												 
													<html:textarea styleId="description" styleClass="formFieldSized2"  property="<%= description%>" cols='60' rows='2' disabled="<%= disableRow %>"/>
												</td>
									   		</tr>
									   		<tr>
									   			<td width="15%" nowrap>
									   				<label for="LabelSubType">
									    				<bean:message key="specimen.subType" />
									   				</label> :  <bean:write name="requestDetailsBeanObj" property="type" />
									   			</td>									   												   			
									   		</tr>	

									   	</table>									   		
									   </td> 									 									  
									</tr>
									
								  <!-- Block for row expansion ends here -->
								  <!--  Flag for instanceOf  -->
								  <% String itemId = requestDetailsBeanObj.getOrderItemId();
								  	 String instanceOfObj = requestDetailsBeanObj.getInstanceOf();
								  	 String specimenId = requestDetailsBeanObj.getSpecimenId();
								  %>
								<html:hidden name="requestDetailsForm" property="<%= instanceOf %>" value="<%= instanceOfObj %>"/>
								<html:hidden name="requestDetailsForm" property="<%= orderItemId %>" value="<%= itemId %>" />
								<html:hidden name="requestDetailsForm" property="<%= specimenIdInMap %>" value="<%= specimenId %>" />
									 <% i++; %>
								 </logic:iterate>
								 <% } //End Outer IF %>
							</table>
							</td>
						</tr>
					</table> <!-- table3_specimenDataTab ends here -->
				</td>
			</tr>
		</table> <!-- table2_TabPage ends here -->
		
		<%-- Include ArrayRequest page for ArrayRequests tab --%>
				<%@ include file="/pages/content/orderingsystem/ArrayRequests.jsp" %>
		
		<table summary="" cellpadding="0" cellspacing="0" border="0" height="20" class="tabPage" width="100%" id="table4_pageFooter">
			<tr>
 				<td class="formLabel" width="10%" style="border-right:0px;border-bottom:0px ">
 					<bean:message key="requestdetails.header.label.Comments" />
 				</td>
 				<td class="formField" style="border-right:0px;border-bottom:0px ">
 					<html:textarea name="requestDetailsForm" styleClass="formFieldSized" cols="300" rows="2" property="administratorComments"  />
		 		</td>
		 		<td class="" align="right" >
					<input type="button" value="Done" onclick="submitPage()"/>
					
					<html:hidden name="requestDetailsForm" property="id" />
					<%							
						String operationUpdate = "update";
						String distributionProtocol = ((RequestViewBean)request.getAttribute(Constants.REQUEST_HEADER_OBJECT)).getDistributionProtocolId();  
					%>
					<html:hidden name="requestDetailsForm" property="operation" value="<%= operationUpdate %>"/>
					<html:hidden name="requestDetailsForm" property="distributionProtocolId" value="<%= distributionProtocol %>"/>							
				</td>
			</tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
			<tr></tr>
		</table> <!-- table4_pageFooter ends here -->
		</td>
	  </tr>
	  
   </table>		<!-- table1_OrderRequestHeader ends here -->
 </html:form>
</body>

