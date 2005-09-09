
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="edu.wustl.catissuecore.util.global.Constants"%>

<tiles:importAttribute />

<html>
<head>
<title><tiles:getAsString name="title" ignore="true" /></title>
<link rel="stylesheet" type="text/css" href="css/styleSheet.css" />
<script src="jss/script.js" type="text/javascript"></script>
<script src="jss/overlib_mini.js" type="text/javascript"></script>
<script src="jss/calender.js" type="text/javascript"></script>

</head>
<body>
<table summary="" cellpadding="0" cellspacing="0" border="0"
	width="100%" height="100%">

	<!-- caBIG hdr begins -->
	<tr>
		<td><tiles:insert attribute="header"></tiles:insert></td>
	</tr>
	<!-- caBIG hdr ends -->

	<tr>
		<td height="100%" valign="top">
		<table summary="" cellpadding="0" cellspacing="0" border="0"
			height="100%">
			<!-- application hdr begins -->
			<tr>
				<td colspan="2" height="50"><tiles:insert
					attribute="applicationheader"></tiles:insert></td>
			</tr>
			<!-- application hdr ends -->

			<tr>
				<td width="190" valign="top" class="subMenu"><!-- submenu begins -->
				<tiles:insert attribute="commonmenu">
					<tiles:put name="submenu" beanName="submenu" />
				</tiles:insert> <!-- submenu ends --></td>
				<td valign="top" width="100%">
				<table summary="" cellpadding="0" cellspacing="0" border="0"
					width="100%" height="100%">
					<tr>
						<td height="20" width="90%" class="mainMenu"><!-- main menu begins -->
						<tiles:insert attribute="mainmenu"></tiles:insert> <!-- main menu ends -->

						</td>
						<td height="20" class="mainMenu" align="right">
						<table summary="" cellpadding="0" cellspacing="0" border="0"
							height="20">
							<tr>
							
								<td height="20" class="mainMenuItem"
									onclick="document.location.href='Home.do'">
									<logic:empty scope="session" name="<%=Constants.SESSION_DATA%>">
									<html:link styleClass="mainMenuLink" page="/Home.do">
										<bean:message key="app.loginMessage" />
									</html:link>
									</logic:empty>
									<logic:notEmpty scope="session" name="<%=Constants.SESSION_DATA%>">
									<html:link styleClass="mainMenuLink" page="/Logout.do">
										<bean:message key="app.logoutMessage" />
									</html:link>
									</logic:notEmpty>
								</td>
							</tr>
						</table>
								
						</td>
					</tr>

					<!--_____ main content begins _____-->
					<tr>
						<td colspan="2" width="100%" valign="top"><!-- target of anchor to skip menus --><a
							name="content" /> <tiles:insert attribute="content"></tiles:insert></td>
					</tr>
					<!--_____ main content ends _____-->

					<tr>
						<td colspan="2" height="20" width="100%" class="footerMenu"><!-- application ftr begins -->
						<tiles:insert attribute="applicationfooter"></tiles:insert> <!-- application ftr ends -->

						</td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td><!-- footer begins --> <tiles:insert attribute="mainfooter"></tiles:insert>
		<!-- footer ends --></td>
	</tr>
</table>
</body>
</html>
