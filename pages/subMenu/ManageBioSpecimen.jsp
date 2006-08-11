<td class="subMenuPrimaryItemsWithBorder" onmouseover="changeMenuStyle(this,'subMenuPrimaryItemsWithBorderOver')" onmouseout="changeMenuStyle(this,'subMenuPrimaryItemsWithBorder')">		
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="edu.wustl.catissuecore.util.global.Utility"%>

<%@ include file="/pages/subMenu/SelectMenu.jsp" %>
<tr>
	<td class="subMenuPrimaryTitle" height="22">
		<a href="#content">
    		<img src="images/shim.gif" alt="Skip Menu" width="1" height="1" border="0" />
    	</a>
	</td>
</tr>


<!-- menu id : 12 -->
<%
	strMouseOut = Utility.setSelectedMenuItem(selectMenuID, 12, normalMenuClass , selectedMenuClass , hoverMenuClass);
%>
<tr>
	<%=strMouseOut%>	
			<!--img src="images/subMenuArrow.gif" width="7" height="7" alt="" /--> 
				<b> <bean:message key="app.participant" /> </b>
		</div>		
		<div>
			<a class="subMenuPrimary" href="Participant.do?operation=add&pageOf=pageOfParticipant&menuSelected=12"><bean:message key="app.add" /></a> | 
			<a class="subMenuPrimary" href="SimpleQueryInterface.do?pageOf=pageOfParticipant&aliasName=Participant&menuSelected=12" >
				<bean:message key="app.edit" />
			</a>
		</div>
	</td>
</tr>
<!-- menu id : 13 -->
<%
	strMouseOut = Utility.setSelectedMenuItem(selectMenuID, 13, normalMenuClass , selectedMenuClass , hoverMenuClass);
%>
<tr>
	<%=strMouseOut%>
		<div>
			<!--img src="images/subMenuArrow.gif" width="7" height="7" alt="" /--> 
				<b> <bean:message key="app.participantregister" /> </b>
		</div>
		<div>
			<a class="subMenuPrimary" href="CollectionProtocolRegistration.do?operation=add&pageOf=pageOfCollectionProtocolRegistration&menuSelected=13"><bean:message key="app.add" /></a> | 
			<a class="subMenuPrimary" href="SimpleQueryInterface.do?pageOf=pageOfCollectionProtocolRegistration&aliasName=CollectionProtReg&menuSelected=13" >
				<bean:message key="app.edit" />
			</a>
		</div>
	</td>
</tr>
</tr>
<!-- menu id : 14 -->
<%
	strMouseOut = Utility.setSelectedMenuItem(selectMenuID, 14, normalMenuClass , selectedMenuClass , hoverMenuClass);
%>
<tr>
	<%=strMouseOut%>
		<div>
			<!--img src="images/subMenuArrow.gif" width="7" height="7" alt="" /--> 
				<b> <bean:message key="app.specimencollectiongroup" /></b>
		</div>
		<div>
		<a class="subMenuPrimary" href="SpecimenCollectionGroup.do?operation=add&amp;pageOf=pageOfSpecimenCollectionGroup&menuSelected=14"><bean:message key="app.add" /></a> | 
		<a class="subMenuPrimary" href="SimpleQueryInterface.do?pageOf=pageOfSpecimenCollectionGroup&aliasName=SpecimenCollectionGroup&menuSelected=14" >
			<bean:message key="app.edit" />
		</a>
		</div>
	</td>
</tr>
<!-- menu id : 15 -->
<%
	strMouseOut = Utility.setSelectedMenuItem(selectMenuID, 15, normalMenuClass , selectedMenuClass , hoverMenuClass);
%>
<tr>
	<%=strMouseOut%>
		<div>
			<!--img src="images/subMenuArrow.gif" width="7" height="7" alt="" /--> 
				<b><bean:message key="app.newSpecimen" /> </b>
		</div>
		<div>
			<a class="subMenuPrimary" href="NewSpecimen.do?operation=add&amp;pageOf=pageOfNewSpecimen&menuSelected=15">
				<bean:message key="app.add" />
			</a> | 
			<a class="subMenuPrimary" href="SimpleQueryInterface.do?pageOf=pageOfNewSpecimen&aliasName=Specimen&menuSelected=15" ><bean:message key="app.edit" /></a>
			 | <a class="subMenuPrimary" href="CreateSpecimen.do?operation=add&amp;pageOf=&menuSelected=15">
				<bean:message key="app.createSpecimen" />	</a> 
		</div>
		<div>
			<a class="subMenuPrimary" href="Aliquots.do?pageOf=pageOfAliquot&menuSelected=15">
				<bean:message key="app.aliquots" />
			</a> | 
			<a class="subMenuPrimary" href="QuickEvents.do?operation=add&menuSelected=15">
				<bean:message key="quickEvents.link" />
			</a>
		</div>
	</td>
</tr>
<%
	strMouseOut = Utility.setSelectedMenuItem(selectMenuID, 20, normalMenuClass , selectedMenuClass , hoverMenuClass);
%>
<tr>
		<%=strMouseOut%>
		<div>
			<!--img src="images/subMenuArrow.gif" width="7" height="7" alt="" /--> 
				<b><bean:message key="app.newSpecimenArray" /> </b>
		</div>
		<div>
			<a class="subMenuPrimary" href="Array.do?operation=add&menuSelected=20">
				<bean:message key="app.add" />
			</a> |
				<bean:message key="app.edit"/>
		</div>
	</td>
</tr>

<!-- menu id : 16 -->
<%
	strMouseOut = Utility.setSelectedMenuItem(selectMenuID, 16, normalMenuClass , selectedMenuClass , hoverMenuClass);
%>
<tr>
	<%=strMouseOut%>
		<div>
			<%--img src="images/subMenuArrow.gif" width="7" height="7" alt="" /--%> 
				<b><bean:message key="app.distribution" /></b>
		</div>
		<div>
			<a class="subMenuPrimary" href="Distribution.do?operation=add&pageOf=pageOfDistribution&menuSelected=16"><bean:message key="app.add" /></a> | 
			<a class="subMenuPrimary" href="SimpleQueryInterface.do?pageOf=pageOfDistribution&aliasName=Distribution&menuSelected=16">
				<bean:message key="app.edit" />
			</a>
		</div>
	</td>
</tr>

<!-- tr>
	<td class="subMenuPrimaryItemsWithBorder" onmouseover="changeMenuStyle(this,'subMenuPrimaryItemsWithBorderOver')" onmouseout="changeMenuStyle(this,'subMenuPrimaryItemsWithBorder')">				
		
		<div>
				<a class="subMenuPrimary" href="SpecimenEventParameters.do?pageOf=pageOfSpecimenEventParameters">
					<b><bean:message key="app.specimeneventparameters" /></b>
				</a>
		</div>
	</td>
</tr> -->

