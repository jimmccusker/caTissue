<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="edu.wustl.catissuecore.util.global.Constants"%>
<html:errors/>
<script language="javascript">
var cpId = window.parent.frames[0].document.getElementById("cpId").value;
var participantId = window.parent.frames[0].document.getElementById("participantId").value;
window.parent.frames[1].location="showTree.do?<%=Constants.CP_SEARCH_CP_ID%>="+cpId+"&<%=Constants.CP_SEARCH_PARTICIPANT_ID%>="+participantId;
window.parent.frames[0].location="showCpAndParticipants.do?cpId="+cpId+"&participantId="+participantId;
</script>

<table summary="" cellpadding="0" cellspacing="0" border="0" class="contentPage" width="600">
	<tr>
		<td colspan="2">
		   <ul>
				<li><font color="#000000" size="3" face="Verdana"><strong>This is user Workspace</strong></font></li>
		   </ul>
		</td>
	</tr>
</table>