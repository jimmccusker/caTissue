							<!-- action buttons begins -->
							<table cellpadding="4" cellspacing="0" border="0">
								<tr>
									<td>
									
										<table>
											<logic:equal name="<%=Constants.SUBMITTED_FOR%>" value="AddNew">
												<% 
													isAddNew=true;
												%>
											</logic:equal>
											<tr>
											
												<td nowrap class="formFieldNoBorders">
													<html:button styleClass="actionButton" 
															property="submitPage" 
															title="<%=Constants.SPECIMEN_BUTTON_TIPS[0]%>"
															value="<%=Constants.SPECIMEN_FORWARD_TO_LIST[0][0]%>" 
															onclick="<%=normalSubmit%>">
						  				     	    
											     	</html:button>
												</td>
												<logic:notEqual name="<%=Constants.PAGEOF%>" value="<%=Constants.QUERY%>">
												<td nowrap class="formFieldNoBorders">
													<html:button styleClass="actionButton"  
															property="submitPage"
															title="<%=Constants.SPECIMEN_BUTTON_TIPS[1]%>"
															value="<%=Constants.SPECIMEN_FORWARD_TO_LIST[1][0]%>" 
															disabled="<%=isAddNew%>" 
															onclick="<%=deriveNewSubmit%>">
						  				     	    
											     	</html:button>
												</td>	
												</logic:notEqual>
<!-- Commented as per bug 2115. Mandar. 10-July-06
											</tr>
											<tr>							
											<logic:notEqual name="<%=Constants.PAGEOF%>" value="<%=Constants.QUERY%>">
												<td class="formFieldNoBorders" nowrap>
													<html:button styleClass="actionButton"  
															property="submitPage"
															title="<%=Constants.SPECIMEN_BUTTON_TIPS[2]%>"
															value="<%=Constants.SPECIMEN_FORWARD_TO_LIST[2][0]%>" 
															disabled="<%=isAddNew%>" 
															onclick="<%=addEventsSubmit%>">
						  				     	    
											     	</html:button>
												</td>
-->
												<td class="formFieldNoBorders" nowrap>
													<html:button styleClass="actionButton"  
															property="submitPage"
															title="<%=Constants.SPECIMEN_BUTTON_TIPS[3]%>"
															value="<%=Constants.SPECIMEN_FORWARD_TO_LIST[3][0]%>" 
															disabled="<%=isAddNew%>" 
															onclick="<%=addMoreSubmit%>">
						  				     	   
											     	</html:button>
												</td>		
												</logic:notEqual>
											</tr>
										</table>
									
									</td>					
						   			
									
								</tr>
							</table>
							<!-- action buttons end -->