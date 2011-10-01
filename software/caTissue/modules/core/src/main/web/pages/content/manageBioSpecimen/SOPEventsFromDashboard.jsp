<script type="text/javascript" src="jss/ext-base.js"></script>
<script type="text/javascript" src="jss/ext-all.js"></script>
	<table width="100%" height="100%" border="0" cellpadding="3" cellspacing="0" class="whitetable_bg">
		<tr>
			<td class="bottomtd" colspan="3" align="left" >
			</td>
		</tr>
		<tr>
			<td colspan="3" align="left" >
				<%@ include file="/pages/content/common/ActionErrors.jsp" %>
			</td>
		</tr>
		<%
		if(Boolean.parseBoolean((String) request.getAttribute("showSPPHeader")))
		{
		%>
		<tr class="tr_bg_blue1">
			<td align="left" class="tr_bg_blue1">
				<span class="blue_ar_b">&nbsp;SPP Label&nbsp;&nbsp;</span>
				<span class="blue_ar_b" style="font-weight: normal">&nbsp;&nbsp;&nbsp;${requestScope.nameOfSelectedSop}</span>
			</td>
		</tr>
		<%
		}
			if(Boolean.parseBoolean((String) request.getAttribute("showSkipEventCheckBoxes")))
			{
			%>
		<tr>
			<td class="bottomtd" colspan="3" height="5px"><span id="skipAllEventCheckBox" style="display:block"><input type="checkbox" id="skipAllEvents" name="skipAllEvents" checked="true" onClick="checkUncheckAllCheckBoxes();"/><span class="blue_ar_b">&nbsp;All Events performed</span></span></td>
		</tr>
		<%}
		if(Boolean.parseBoolean((String) request.getAttribute("showSPPDropdown")))
		{
		%>
			<tr>
				<td align="left" class="black_ar">Associated SPPs &nbsp;&nbsp;&nbsp;
					<html:select property="sppName" styleClass="formFieldSized15" styleId="className" size="1" disabled="false" onchange="onParameterChange(this)" onmouseover="showTip(this.id)" onmouseout="hideTip(this.id)">
						<html:options collection="sppNameList" property="value" labelProperty="name" />
					</html:select>
				</td>
				<td align="left" valign="top">&nbsp;</td>
			</tr>
		<%}%>
		<tr height="100%">
			<td height="100%">
				<table width="100%" height="100%" style="overflow:no;" border="0" cellpadding="3" cellspacing="0" class="whitetable_bg">
					<tr>
						<td>
							<div style="overflow:no; height: 100%;float: none" align="left" title="sopForms" id ="sopForms" ></div>
						</td>
					</tr>
					<tr height="10%">
						<td class="buttonbg">
							<html:submit styleId="SOPEventSubmitBtn" styleClass="blue_ar_b" value="Submit" onclick="submitSOPEvents();"/>
						</td>
					</tr>
					<tr>
						<td id="blankTd" height="2">&nbsp;
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<script>
		function getiframeheight()
		{
			var theHeight;
			if (window.innerHeight)
			{
			   theHeight = window.innerHeight;
			}
			else if (document.documentElement && document.documentElement.clientHeight)
			{
				theHeight = document.documentElement.clientHeight;
			}
			else if (document.body)
			{
				theHeight = document.body.clientHeight;
			}
			return theHeight;
		}

		function checkUncheckAllCheckBoxes()
		{
			var skipAllEvents = document.getElementById('skipAllEvents').checked;
			var iframeList = document.getElementsByTagName('iframe');
			for(j =0;j<iframeList.length;j++)
			{
				var containerId= iframeList[j].name
				var oDoc = iframeList[j].contentWindow || iframeList[j].contentDocument;
				if (oDoc.document) {
					oDoc = oDoc.document;
				}
				oDoc.getElementById('isSkipEvent').checked= skipAllEvents;
				oDoc.getElementById('isSkipEvent').value= skipAllEvents;
			}
		}

		var globalListSize=0;
		function calculatePageHtForNonIEBrowser(listsize)
		{
			globalListSize = globalListSize +1;
			if(globalListSize == listsize)
			{
				calculatePageHeight();
			}
		}

		function calculatePageHeightForIE()
		{
			calculatePageHeight();
		}

		function calculatePageHeight()
		{
			var sopEventDiv = document.getElementById('sopForms');
			var iframeList = document.getElementsByTagName('iframe');
			var allFrameHeights = 0;
			for(j =0;j<iframeList.length;j++)
			{
				var oDoc = iframeList[j].contentWindow || iframeList[j].contentDocument;
				if (oDoc.document)
				{
					oDoc = oDoc.document;
				}
				var innerIframe = oDoc.getElementsByTagName('iframe')[0].contentWindow;
				if (innerIframe.document)
				{
					innerIframe = innerIframe.document;
				}
				var inputCollection = innerIframe.getElementsByTagName('input');
				var decontrols =0;
				for(i=0; i<inputCollection.length ;i++)
				{
					var search = 'Control';
					if(inputCollection[i].name.indexOf(search) == 0)
					{
						decontrols=  decontrols +1;
					}
				}
				var divHeight = decontrols * 35;
				var frmHeight = (decontrols * 40)+20;
				if(innerIframe.getElementById('dataEntryFormDiv')!=null)
				{
					oDoc.getElementsByTagName('iframe')[0].height=frmHeight+'px';
					//innerIframe.getElementById('dataEntryFormDiv').style.height = divHeight+'px';
				}
				//alert(divHeight);
				var outerfrmHt = frmHeight+150;
				//alert(oDoc.body.scrollHeight);
				//alert(parent.document.getElementById('cpFrameNew').contentWindow.document.body.scrollHeight);
				iframeList[j].style.height= outerfrmHt+'px';
				allFrameHeights = allFrameHeights+ outerfrmHt;
			}
			if(allFrameHeights != 0)
			{
				sopEventDiv.style.height=allFrameHeights+"px";
			}
		}
		document.getElementById('sopForms').style.height = getiframeheight();
	</script>


	<%
List<Map<String, Object>> sopEventDataList = (List<Map<String, Object>>) request.getAttribute("SOPEvents");
if(sopEventDataList!=null && !sopEventDataList.isEmpty())
{
	int listSize = sopEventDataList.size();
	String height = 100/listSize+"%";
	int count = 0;
	for(Map<String, Object> sopDataList : sopEventDataList)
	{
		++count;
		Object actionAppId = sopDataList.get(Constants.ID);
		Object formContextId = sopDataList.get("formContextId");
		String eventName = sopDataList.get(Constants.CONTAINER_IDENTIFIER).toString();
		String pageOfStr = sopDataList.get(Constants.PAGE_OF).toString();
		String caption = sopDataList.get("Caption").toString();
		String eventDate = null;
		if(sopDataList.get(Constants.EVENT_DATE)!=null)
		{
			eventDate = sopDataList.get(Constants.EVENT_DATE).toString();
		}
%>
<script>
	var sopEventDiv = document.getElementById('sopForms');
	var formName = "<%=formContextId%>";

	var formContextIdElement = document.createElement("input");
	formContextIdElement.id = "formContextId";
	formContextIdElement.type= "hidden";
	formContextIdElement.name ="formContextId";
	formContextIdElement.value=<%=formContextId%>;
	sopEventDiv.appendChild(formContextIdElement);

	var formIframe = document.createElement("iframe");
	formIframe.id = formName;
	formIframe.name= formName;
	formIframe.style.border ="0";
	formIframe.style.width ="100%";
	formIframe.style.height ="<%=height%>";
	formIframe.scrolling="no";
	sopEventDiv.appendChild(formIframe);
	<%
		String url;
		if("0".equals(actionAppId.toString()))
		{
			String eventURL = getEventAction(caption, specimenIdentifier);
			url = eventURL+"&hideSubmitButton=true&showDefaultValues=true&formContextId="+formContextId;
		}
		else
		{
			url = "SearchObject.do?pageOf=pageOfDynamicEvent&operation=search&hideSubmitButton=true&id="+actionAppId+"&formContextId="+formContextId;
		}

		if(Boolean.parseBoolean((String) request.getAttribute("showSkipEventCheckBoxes")))
		{
			url = url + "&allowToSkipEvents=true";
		}
		if(request.getAttribute(Globals.ERROR_KEY)!=null)
		{
			url = url + "&containsErrors=true";
		}
	%>

	var iframeElement = document.getElementById(formName);
	formIframe.src = "<%=url%>";

	if(Ext.isIE)
	{
		<%
			if(listSize < 3)
			{
		%>
				document.getElementById('blankTd').style.height = "20%";
		<%
			}
			if(count == listSize)
			{
		%>
				iframeElement.onload = function(){
					calculatePageHeight();
				}
		<%
			}
		%>
	}
	else
	{
		//alert("NON IE browser"+globalListSize);
		iframeElement.onload = function(){
			calculatePageHtForNonIEBrowser(<%=listSize%>);
		}
	}
</script>
<%
}
}
else
{
%>
<script>
	document.getElementById('SOPEventSubmitBtn').disabled="disabled";
</script>
<%
}
%>
