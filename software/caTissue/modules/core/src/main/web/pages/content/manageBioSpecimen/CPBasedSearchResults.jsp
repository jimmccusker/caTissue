<%@ page import="edu.wustl.catissuecore.util.global.Constants"%>
<%
      String access = (String)session.getAttribute("Access");
	  boolean mac = false;
      Object os = request.getHeader("user-agent");
      if(os!=null && os.toString().toLowerCase().indexOf("mac")!=-1)
      {
          mac = true;
      }

	String URLCollectionProtocolId  = request.getParameter("URLCollectionProtocolId");
	String URLParticipantId = request.getParameter("URLParticipantId");
	String URLScgId = request.getParameter("URLId");
	String URLCollectionEventId = request.getParameter("URLCollectionEventId");
	String frame1Ysize = "99.9%";
	String frame2Ysize = "99.9%";
	String frame3Ysize = "99.9%";

	String cpAndParticipantViewFrameHeight="50%";
	if(access != null && access.equals("Denied"))
	{
		cpAndParticipantViewFrameHeight="15%";
	}

	if(mac)
	{
		frame1Ysize = "180";
		frame2Ysize = "180";
		frame3Ysize = "520";


		if(access != null && access.equals("Denied"))
		{
			frame1Ysize = "80";
			frame2Ysize = "320";
		}
	}
	else
	{
		frame3Ysize = "650";
	}

%>
<script type="text/javascript">
</script>

<script>
//Set the alope for the IFrame
if ( document.getElementById && !(document.all) )
{
	var slope=10;
}
else
{
	var slope=-10;
}

var ua = navigator.userAgent;
if(navigator.userAgent.indexOf('Mac')<0)
{
window.onload = function() { adjFrmHt('cpFrameNew', .9,slope);adjFrmHt('<%=Constants.CP_AND_PARTICIPANT_VIEW%>', .9,slope); }
window.onresize = function() { adjFrmHt('cpFrameNew', .9,slope); adjFrmHt('<%=Constants.CP_AND_PARTICIPANT_VIEW%>', .9,slope); }
}
else
{
	window.onload = function() {macRelated(); }
	window.onresize = function() { macRelated();}
}

</script>
<script language="JavaScript" type="text/javascript" src="jss/caTissueSuite.js"></script>
<script language="JavaScript" type="text/javascript" src="jss/javaScript.js"></script>
<body>
<script>
function changeMenuStyle(obj, new_style) {
if (objclick != obj)
  obj.className = new_style;
}

function showCursor(){
	document.body.style.cursor='hand';
}

function hideCursor(){
	document.body.style.cursor='default';
}
</script>
<!-- Mandar : 25Nov08  -->
		<TABLE border=1 width="100%" height="100%" cellpadding="0" cellspacing="0">
		<TR>
			<TD width="27%" valign="top" height="100%" id='sideMenuTd' style="display: block; height: 100%;">
		<div id="SearchLeftPart" style=" width:100%;height:100%; overflow:none;">
				<iframe id="<%=Constants.CP_AND_PARTICIPANT_VIEW%>" name="<%=Constants.CP_AND_PARTICIPANT_VIEW%>" src="<%=Constants.SHOW_CP_AND_PARTICIPANTS_ACTION%>?pageOf=<%=Constants.PAGE_OF_CP_QUERY_RESULTS%>&URLCollectionProtocolId=<%=URLCollectionProtocolId%>&URLParticipantId=<%=URLParticipantId%>&URLScgId=<%=URLScgId%>&URLCollectionEventId=<%=URLCollectionEventId%>" scrolling="no" frameborder="0" width="100%" height="<%= frame3Ysize %>">
						Your Browser doesn't support IFrames.
				</iframe>
		</div>
			</td>
			<TD id=menucontainer width="2" class="subMenuPrimaryTitle" valign="middle" align="center" onmouseover="changeMenuStyle(this,'subMenuSecondaryTitleOver'),showCursor()"
						onmouseout="changeMenuStyle(this,'subMenuPrimaryTitle'),hideCursor()"
						onclick="toggleSplitterStatus()">
						<SPAN id="splitter"><img src="images/leftPane_collapseButton.gif" /></SPAN>
			</TD>
			<td width="73%" valign="top" height="100%" id="contentTd">
			<div id="SearchLeftPart2" style=" width:100%;height:100%; overflow:none;">
				<iframe id="cpFrameNew" name="<%=Constants.DATA_DETAILS_VIEW%>" src="<%=Constants.BLANK_SCREEN_ACTION%>" scrolling="none" frameborder="0" width="100%" height="100%">
				Your Browser doesn't support IFrames.
			</iframe>
			</div>
			</TD>
		</TR>
		</TABLE>
		<script>
			SearchResult=document.getElementById('SearchLeftPart');
			SearchResult.style.height = (document.body.clientHeight - 60) + 'px';
			SearchResult2=document.getElementById('SearchLeftPart2');
			SearchResult2.style.height = (document.body.clientHeight - 60) + 'px';
		</script>
</body>