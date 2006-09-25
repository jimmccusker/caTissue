<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%@ page import="edu.wustl.catissuecore.util.global.Constants"%>
<%@ page import="edu.wustl.catissuecore.actionForm.MultipleSpecimenForm"%>

<%
  String action = Constants.NEW_MULTIPLE_SPECIMEN_ACTION + "?method=showMultipleSpecimen&amp;menuSelected=15";
%>

<html:errors />
<html:messages id="messageKey" message="true" header="messages.header"
	footer="messages.footer">
	<%=messageKey%>
</html:messages>

<html:form action="<%=action%>">
	<table summary="" cellpadding="0" cellspacing="0" border="0"
		class="contentPage" width="600">
		<tr>
			<td>
			<table summary="" cellpadding="3" cellspacing="0" border="0"
				width="100%">
				<tr>
					<td class="formMessage" colspan="6"><bean:message
						key="app.requiredFieldNote" /></td>
				</tr>
				<tr>
					<td class="formTitle" " colspan="6" height="20"><bean:message
						key="multipleSpecimen.mainTitle" /></td>
				</tr>
				<tr>
					<td class="formRequiredNotice" width="5">*</td>
					<td class="formLabel" colspan="2"><bean:message
						key="multipleSpecimen.numberOfSpecimen" /></td>
					<td class="formField" colspan="3"><html:text
						styleClass="formFieldSized5" maxlength="50" size="30"
						styleId="numberOfSpecimen" property="numberOfSpecimen"  /></td>
				</tr>
				<tr>
					<td colspan="5">&nbsp;</td>
					<td align="right"><html:submit styleClass="actionButton"
						property="submitPage" >
						<bean:message key="buttons.create" />
					</html:submit></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</html:form>
