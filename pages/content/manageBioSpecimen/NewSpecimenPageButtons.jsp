							<!-- action buttons begins -->
							<table cellpadding="4" cellspacing="0" border="0">
								<tr>
									<td>
									<logic:notEqual name="<%=Constants.PAGEOF%>" value="<%=Constants.QUERY%>">
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
															
															value="<%=Constants.SPECIMEN_FORWARD_TO_LIST[0][0]%>" 
															onclick="<%=normalSubmit%>">
						  				     	    
											     	</html:button>
												</td>
												<td nowrap class="formFieldNoBorders">
													<html:button styleClass="actionButton"  
															property="submitPage"
															
															value="<%=Constants.SPECIMEN_FORWARD_TO_LIST[1][0]%>" 
															disabled="<%=isAddNew%>" 
															onclick="<%=deriveNewSubmit%>">
						  				     	    
											     	</html:button>
												</td>		
											</tr>
											<tr>							
												<td class="formFieldNoBorders" nowrap>
													<html:button styleClass="actionButton"  
															property="submitPage"
															
															value="<%=Constants.SPECIMEN_FORWARD_TO_LIST[2][0]%>" 
															disabled="<%=isAddNew%>" 
															onclick="<%=addEventsSubmit%>">
						  				     	    
											     	</html:button>
												</td>
												<td class="formFieldNoBorders" nowrap>
													<html:button styleClass="actionButton"  
															property="submitPage"
															
															value="<%=Constants.SPECIMEN_FORWARD_TO_LIST[3][0]%>" 
															disabled="<%=isAddNew%>" 
															onclick="<%=addMoreSubmit%>">
						  				     	   
											     	</html:button>
												</td>								
											</tr>
										</table>
									</logic:notEqual>
									</td>					
						   			
									
								</tr>
							</table>
							<!-- action buttons end -->