<table cellpadding="4" cellspacing="0" border="0">
	<logic:equal name="<%=Constants.SUBMITTED_FOR%>" value="AddNew">
		<% 
			isAddNew = true; 
		%>
	</logic:equal>
	
	<tr>
		<td nowrap class="formFieldNoBorders">
			<html:button styleClass="actionButton" 
					property="submitPage" 
					value="<%=Constants.PROTOCOL_REGISTRATION_FORWARD_TO_LIST[0][0]%>" 
					onclick="<%=normalSubmit%>">				  				     	    
	     	</html:button>
		</td>
		
		<td nowrap class="formFieldNoBorders"> 
			<html:button styleClass="actionButton"  
					property="submitPage" 
					value="<%=Constants.PROTOCOL_REGISTRATION_FORWARD_TO_LIST[1][0]%>"
					disabled="<%=isAddNew%>" 
					onclick="<%=forwardToSubmit%>">
	     	</html:button>
		</td>
	</tr>
</table>