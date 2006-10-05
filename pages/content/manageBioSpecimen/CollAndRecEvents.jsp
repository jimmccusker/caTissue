
<table summary="" cellpadding="3" cellspacing="0" border="0" width="100%">					
					<!-- Mandar AutoEvents start -->		

					<tr>						
						<td colspan="3" width="500" class="formTitle">
							<bean:message key="specimen.collectedevents.title"/>	
						</td>
						<td colspan="3" width="500" class="formTitle">
							<bean:message key="specimen.receivedevents.title"/>						
						</td>
					</tr>
					<!-- User -->
					<tr>						
						<!-- CollectionEvent fields -->	
						
						<html:hidden property="collectionEventId" />
						<html:hidden property="collectionEventSpecimenId" />
						<td class="formRequiredNotice" width="5">*</td>
	 					<td class="formRequiredLabel"> 
							<label for="user">
								<bean:message key="specimen.collectedevents.username"/> 
							</label>
						</td>						
						<td class="formField">
							<!-- Mandar : 434 : for tooltip -->
							<html:select property="collectionEventUserId" styleClass="formFieldSized15" styleId="collectionEventUserId" size="1"
							 onmouseover="showTip(this.id)" onmouseout="hideTip(this.id)">
								<html:options collection="<%=Constants.USERLIST%>" labelProperty="name" property="value"/>
							</html:select>
						</td>		

						<!-- RecievedEvent fields -->
						<html:hidden property="receivedEventId" />
						<html:hidden property="receivedEventSpecimenId" />
						<td class="formRequiredNotice" width="5">*</td>
						<td class="formRequiredLabel">
							<label for="type">
								<bean:message key="specimen.receivedevents.username"/> 
							</label>
						</td>
						<td class="formField">
							<!-- Mandar : 434 : for tooltip -->
							<html:select property="receivedEventUserId" styleClass="formFieldSized15" styleId="receivedEventUserId" size="1"
							 onmouseover="showTip(this.id)" onmouseout="hideTip(this.id)">
								<html:options collection="<%=Constants.USERLIST%>" labelProperty="name" property="value"/>
							</html:select>
						</td>
						
					</tr>
					
					<!-- date -->
					<tr>
						
						<!-- CollectionEvent fields -->	
						<td class="formRequiredNotice" width="5">*</td>
						<td class="formRequiredLabel" nowrap> 
							<label for="date">
								<bean:message key="eventparameters.dateofevent"/>															
							</label>
							<!-- <BR><small><bean:message key="page.dateFormat" /></small>  -->
						</td>
						
						<td class="formField">
							<%
							if(currentCollectionDate.trim().length() > 0)
							{
								Integer collectionYear = new Integer(Utility.getYear(currentCollectionDate ));
								Integer collectionMonth = new Integer(Utility.getMonth(currentCollectionDate ));
								Integer collectionDay = new Integer(Utility.getDay(currentCollectionDate ));
							%>
							<ncombo:DateTimeComponent name="collectionEventdateOfEvent"
										  id="collectionEventdateOfEvent"
										  formName="<%=formNameForCal%>"
										  month= "<%= collectionMonth %>"
										  year= "<%= collectionYear %>"
										  day= "<%= collectionDay %>"
										  value="<%=currentCollectionDate %>"
										  styleClass="formDateSized10"
												/>
							<% 
								}
								else
								{  
							 %>
							<ncombo:DateTimeComponent name="collectionEventdateOfEvent"
										  id="collectionEventdateOfEvent"
										  formName="<%=formNameForCal %>"
										  styleClass="formDateSized10"
												/>
							<% 
								} 
							%>
							<small><bean:message key="scecimen.dateformat" /></small>	
						</td>	

						<!-- RecievedEvent fields -->
						<td class="formRequiredNotice" width="5">*</td>
						<td class="formRequiredLabel" nowrap>
							<label for="date">
								<bean:message key="eventparameters.dateofevent"/> 
							</label>
							<!-- <BR><small><bean:message key="scecimen.dateformat" /></small> -->
						</td>						
						<td class="formField">
								<%
								if(currentReceivedDate.trim().length() > 0)
								{
									Integer receivedYear = new Integer(Utility.getYear(currentReceivedDate ));
									Integer receivedMonth = new Integer(Utility.getMonth(currentReceivedDate ));
									Integer receivedDay = new Integer(Utility.getDay(currentReceivedDate ));
								%>
								<ncombo:DateTimeComponent name="receivedEventDateOfEvent"
											  id="receivedEventDateOfEvent"
											  month= "<%= receivedMonth %>"
											  year= "<%= receivedYear %>"
											  day= "<%= receivedDay %>"
											  value="<%=currentReceivedDate %>"
											  formName= "<%=formNameForCal %>"
											  styleClass="formDateSized10"
													/>
								<% 
									}
									else
									{  
								 %>
								<ncombo:DateTimeComponent name="receivedEventDateOfEvent"
											  id="receivedEventDateOfEvent"
											  formName="<%=formNameForCal %>"
											  styleClass="formDateSized10"
													/>
								<% 
									} 
								%> 
								<small><bean:message key="scecimen.dateformat" /></small>	
						</td>	
					</tr>	
					
					<tr>					
						
						<!-- CollectionEvent fields -->	
						<td class="formRequiredNotice" width="5">*</td>
						<td class="formRequiredLabel">
							<label for="collectionprocedure">
								<bean:message key="collectioneventparameters.collectionprocedure"/> 
							</label>
						</td>
						<td class="formField">
								<!-- Mandar : 434 : for tooltip -->
								<html:select property="collectionEventCollectionProcedure" styleClass="formFieldSized15" styleId="collectionEventCollectionProcedure" size="1"
									onmouseover="showTip(this.id)" onmouseout="hideTip(this.id)">
									<!--html:options name="<%=Constants.PROCEDURE_LIST%>" labelName="<%=Constants.PROCEDURE_LIST%>" /-->
									<html:options collection="<%=Constants.PROCEDURE_LIST%>" labelProperty="name" property="value"/>
								</html:select>							
						</td>						
						
						<!-- RecievedEvent fields -->
						<td class="formRequiredNotice" width="5"rowspan="2">*</td>
						<td class="formRequiredLabel"rowspan="2"> 
							<label for="quality">
								<bean:message key="receivedeventparameters.receivedquality"/> 
							</label>
						</td>						
						<!-- receivedeventparameters.receivedquality -->
						<td class="formField"rowspan="2">
							<!-- Mandar : 434 : for tooltip -->
							<html:select property="receivedEventReceivedQuality" styleClass="formFieldSized15" styleId="receivedEventReceivedQuality" size="1"
							 onmouseover="showTip(this.id)" onmouseout="hideTip(this.id)">
								<html:options collection="<%=Constants.RECEIVED_QUALITY_LIST%>" labelProperty="name" property="value"/>
							</html:select>
						</td>
					</tr>
					
					<!-- CollectionEvent fields -->	
					<tr>							
							<td class="formRequiredNotice" width="5">*</td>
							<td class="formRequiredLabel">
								<label for="container">
									<bean:message key="collectioneventparameters.container"/> 
								</label>
							</td>
							<td class="formField">
								<html:select property="collectionEventContainer" styleClass="formFieldSized15" styleId="collectionEventContainer" size="1"
									 onmouseover="showTip(this.id)" onmouseout="hideTip(this.id)">
										<!--html:options name="<%=Constants.CONTAINER_LIST%>" labelName="<%=Constants.CONTAINER_LIST%>" /-->
										<html:options collection="<%=Constants.CONTAINER_LIST%>" labelProperty="name" property="value"/>
								</html:select>
							</td>
					</tr>	
					
					<!-- comments -->
					<tr>	
						<!-- CollectionEvent fields -->	
						<td class="formRequiredNotice" width="5">&nbsp;</td>
						<td class="formLabel">
							<label for="comments">
								<bean:message key="eventparameters.comments"/> 
							</label>
						</td>
						<td class="formField">
							<html:textarea styleClass="formFieldSized20"  styleId="collectionEventComments" property="collectionEventComments" />
						</td>						
						
						<!-- RecievedEvent fields -->
						<td class="formRequiredNotice" width="5">&nbsp;</td>
						<td class="formLabel">
							<label for="comments">
								<bean:message key="eventparameters.comments"/> 
							</label>
						</td>						
						<td class="formField">
							<html:textarea styleClass="formFieldSized20"  styleId="receivedEventComments" property="receivedEventComments" />
						</td>						
						
				 	</tr>

				
				<!-- Mandar: 11-July-06 AutoEvents end  -->
				</table>	
				
