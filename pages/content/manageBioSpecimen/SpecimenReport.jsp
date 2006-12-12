<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/nlevelcombo.tld" prefix="ncombo"%>

<%@ page import="edu.wustl.catissuecore.util.global.Constants"%>
<%@ page import="edu.wustl.catissuecore.domain.Specimen"%>
<%@ page import="java.util.*"%>


<head>
<script language="JavaScript" type="text/javascript"
	src="jss/javascript.js"></script>
	
<script language="JavaScript">
	
	var newWindow;
	function showNewPage(action)
	{
	   	if(newWindow!=null)
		{
		   newWindow.close();
		}
	     newWindow = window.open(action,'','scrollbars=yes,status=yes,resizable=yes,width=860, height=600');
	     newWindow.focus(); 
	
    }
		
		
		
	</script>
	
</head>

<html:messages id="messageKey" message="true" header="messages.header"
	footer="messages.footer">
	<%=messageKey%>
</html:messages>

<%Collection specimenCollection = (Collection) request
					.getAttribute(Constants.SAVED_SPECIMEN_COLLECTION);
%>

<html:errors />

<html:form action="<%=Constants.ALIQUOT_ACTION%>">

	<table summary="" cellpadding="0" cellspacing="0" border="0"
		class="contentPage" width="660">
		<tr>
			<td>
			<table summary="" cellpadding="3" cellspacing="0" border="0"
				width="660">

				<tr>
					<td class="formTitle" height="20" colspan="8"><bean:message
						key="multipleSpecimen.report.specimens" /></td>
				</tr>
				<tr>
					<td class="formSerialNumberField" width="10">#</td>
					<td class="formRequiredLabel">&nbsp;<bean:message key="specimen.label" /></td>
					<td class="formRequiredLabel">&nbsp;<bean:message key="specimen.barcode" /></td>
					<td class="formRequiredLabel">&nbsp;<bean:message key="specimen.type" /></td>
					<td class="formRequiredLabel">&nbsp;<bean:message key="specimen.subType" /></td>
					<td class="formRequiredLabel">&nbsp;<bean:message key="buttons.addNew" /></td>
					<td class="formRequiredLabel">&nbsp;<bean:message key="buttons.addNew" /></td>
					<td class="formRequiredLabel">&nbsp;<bean:message key="link.Distribute" /></td>
				</tr>
				<%int i = 0;
				Iterator specimenItr = specimenCollection.iterator();
				while (specimenItr.hasNext())
				{
					i++;
					Specimen specimen = (Specimen) specimenItr.next();
                    String specimenLabel = specimen.getLabel();
					
					
					String onClickSpecimenFunction = "showNewPage('SearchObject.do?pageOf=pageOfNewSpecimen&operation=search&id=" + specimen.getId() + "')";
					String onClickAliquotFunction = "showNewPage('Aliquots.do?pageOf=pageOfAliquot&menuSelected=15&label=" + specimenLabel + "')";
					String onClickDeriveFunction = "showNewPage('CreateSpecimen.do?operation=add&pageOf=&menuSelected=15&virtualLocated=true&parentSpecimenLabel=" + specimenLabel + "')";
					String onClickDistributeFunction = "showNewPage('Distribution.do?operation=add&pageOf=pageOfDistribution&menuSelected=16&label=" + specimenLabel + "')";
					String barcode = specimen.getBarcode();
					if(barcode==null)
					{
					    barcode = "";
					}
					%>
				<tr>
					<td class="formSerialNumberField" width="5"><%=i%></td>
					<td class="formField">&nbsp;
					<html:link href="#" styleId="specimen" onclick="<%=onClickSpecimenFunction%>">
					<%=specimen.getLabel()%>
					</html:link></td>
					<td class="formField">&nbsp;
					&nbsp;<%=barcode%>
					</td>
					<td class="formField">&nbsp;
					&nbsp;<%=specimen.getClassName()%>
					</td>
					<td class="formField">&nbsp;
					&nbsp;<%=specimen.getType()%>
					</td>
					<td class="formField">&nbsp;
					<html:link href="#" styleId="aliquot" onclick="<%=onClickAliquotFunction%>">
					<bean:message key="link.Aliquot" />
					</html:link></td>
					<td class="formField">&nbsp;
					<html:link href="#" styleId="derive" onclick="<%=onClickDeriveFunction%>">
					<bean:message key="link.Derive" />
					</html:link></td>
					<td class="formField">&nbsp;
					<html:link href="#" styleId="derive" onclick="<%=onClickDistributeFunction%>">
					<bean:message key="link.Distribute" />
					</html:link></td>
				</tr>
				<%}

			%>
			</table>
			</td>
		</tr>
	</table>
</html:form>
