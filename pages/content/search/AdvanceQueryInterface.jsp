<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<head>
	<script language="JavaScript">

	</script>
</head>

<html:errors />
<% 
	//String operation = request.getAttribute("operation");
%>
<table summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="100%" height="600">
	<tr>

		<td>
			<iframe name="searchPageFrame" id="searchPageFrame" src="/catissuecore/AdvanceQuery.do?pageOf=pageOfSimpleQueryInterface" width="100%" height="300" frameborder="0" scrolling="auto">
			</iframe>
		</td>
	</tr>

	<tr>
		<td>
			<iframe name="queryFrame" id="queryFrame" src="/catissuecore/AdvanceQueryView.do" width="100%" height="300" frameborder="0" scrolling="auto">
			</iframe>
		</td>
	</tr>
</table>