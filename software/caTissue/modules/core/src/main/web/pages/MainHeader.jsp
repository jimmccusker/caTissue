<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="edu.wustl.catissuecore.util.global.Constants"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="edu.wustl.common.util.XMLPropertyHandler"%>
<%@ page import="edu.wustl.catissuecore.util.global.Variables"%>
<script src="jss/titli.js"></script>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="5%" valign="top"><img
			src="images/uIEnhancementImages/top_bg1.jpg" width="53" height="20" /></td>
		<td width="95%" valign="top"
			background="images/uIEnhancementImages/top_bg.jpg"
			style="background-repeat:repeat-x;">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td width="86%" align="right" valign="top">
				<logic:notEmpty name="<%=Constants.SESSION_DATA%>">
				<%
				String searchString = request.getParameter("searchString");
				if(searchString == null)
				{
					searchString = "Keyword Search";
				}
				%>
				<input style = "font-size:0.6em" value = "<%=searchString%>" type ="text" name = "searchString" id = "searchString" onkeypress="return titliOnEnter(event, this, document.getElementById('go'))" />
				<a class = "white"  id="go" href ="TitliInitialiseSearch.do" onclick = "this.href= this.href + '?searchString='+document.getElementById('searchString').value">Keyword Search</a>&nbsp;
				</logic:notEmpty>
					<a
					href="ReportProblem.do?operation=add" class="white"> <img
					src="images/uIEnhancementImages/ic_report.gif" width="15"
					height="12" hspace="2" vspace="0"><bean:message
					key="app.reportedProblems" /> </a> &nbsp;<a
					href="ContactUs.do?PAGE_TITLE_KEY=app.contactUs&FILE_NAME_KEY=app.contactUs.file"
					class="white"><img
					src="images/uIEnhancementImages/ic_mail.gif" alt="Summary"
					width="16" height="12" hspace="3" vspace="0" border="0" /><bean:message key="app.contactUs" /></a>&nbsp; <a
					href="Summary.do" class="white"> <img
					src="images/uIEnhancementImages/ic_summary.gif" alt="Summary"
					width="10" height="10" hspace="5" vspace="0" border="0" /><bean:message key="app.summary" /></a></td>

<%
boolean isCasAvlbl = Variables.isCasAvl;
String logOutURL ="";
if(isCasAvlbl)
{
	logOutURL = "CasLogout.do";
}
else
{
	logOutURL = "Logout.do";
}
%>
				<logic:notEmpty scope="session" name="<%=Constants.SESSION_DATA%>">
					<td width="14%" align="right" valign="top"><a
						href="<%=logOutURL%>"> <img
						src="images/uIEnhancementImages/logout_button1.gif" name="Image1"
						width="86" height="19" id="Image1"
						onmouseover="MM_swapImage('Image1','','images/uIEnhancementImages/logout_button.gif',1)"
						onmouseout="MM_swapImgRestore()" /> </a></td>
				</logic:notEmpty>
				<logic:empty scope="session" name="<%=Constants.SESSION_DATA%>">
					<td width="14%" valign="middle" align="right"><a
						href="Home.do" class="white"> <bean:message
						key="app.loginMessage" /> </a> <img
						src="images/uIEnhancementImages/spacer.gif" width="10" height="10"
						align="absmiddle" /></td>
				</logic:empty>
			</tr>
		</table>
		</td>
	</tr>
</table>