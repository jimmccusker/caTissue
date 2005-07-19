<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<tr>
	<td class="subMenuPrimaryTitle" height="22">
		<a href="#content">
    		<img src="images/shim.gif" alt="Skip Menu" width="1" height="1" border="0" />
    	</a>
	</td>
</tr>

<tr>
	<td class="subMenuPrimaryItems">
		<div>
			<img src="images/subMenuArrow.gif" width="7" height="7" alt=""/> 
				<bean:message key="app.collectionProtocol" />
		</div>
		<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<a class="subMenuPrimary" href="#">
						<bean:message key="app.add" /> 
					</a> | 
					<a class="subMenuPrimary" href="#">
						<bean:message key="app.edit" /> 
					</a>
		</div>
		
		<div>
			<img src="images/subMenuArrow.gif" width="7" height="7" alt=""/> 
				<bean:message key="app.distributionProtocol" />
		</div>
		<div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<a class="subMenuPrimary" href="#">
						<bean:message key="app.add" /> 
					</a> | 
					<a class="subMenuPrimary" href="#">
						<bean:message key="app.edit" /> 
					</a>
		</div>
		
		<div>
			<img src="images/subMenuArrow.gif" width="7" height="7" alt="" /> 
				<bean:message key="app.site" />
					<a class="subMenuPrimary" href="#">
						<bean:message key="app.add" />
					</a> | 
					<a class="subMenuPrimary" href="#">
						<bean:message key="app.edit" />
					</a>
		</div>
		
		<div>
			<img src="images/subMenuArrow.gif" width="7" height="7" alt="" /> 
				<bean:message key="app.user" />
					<a class="subMenuPrimary" href="User.do?operation=add&amp;pageOf=">
						<bean:message key="app.add" />
					</a> | 
					<a class="subMenuPrimary" href="User.do?operation=search&amp;pageOf=">
						<bean:message key="app.edit" />
					</a>
		</div>
		
		<div>
			<img src="images/subMenuArrow.gif" width="7" height="7" alt="" /> 
				<bean:message key="app.institute" />
					<a class="subMenuPrimary" href="Institute.do?operation=add">
						<bean:message key="app.add" />
					</a> | 
					<a class="subMenuPrimary" href="Institute.do?operation=search">
						<bean:message key="app.edit" />
					</a>
		</div>
		<div>
			<img src="images/subMenuArrow.gif" width="7" height="7" alt="" /> 
				<bean:message key="app.department" />
					<a class="subMenuPrimary" href="Department.do?operation=add">
						<bean:message key="app.add" />
					</a> | 
					<a class="subMenuPrimary" href="Department.do?operation=search">
						<bean:message key="app.edit" />
					</a>
		</div>
		
		<div>
			<img src="images/subMenuArrow.gif" width="7" height="7" alt="" />
				<a class="subMenuPrimary" href="ApproveUserShow.do?pageNum=1"> 
					<bean:message key="app.approveUser" />
				</a>
		</div>
		
		<div>
			<img src="images/subMenuArrow.gif" width="7" height="7" alt="" />
				<a class="subMenuPrimary" href="ReportedProblemShow.do?pageNum=1">
					<bean:message key="app.reportedProblems" />
				</a>
		</div>

	</td>
</tr>