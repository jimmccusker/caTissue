<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<table summary="" cellpadding="0" cellspacing="0" border="0" width="190" height="100%">
  <tiles:insert attribute="submenu"></tiles:insert>            
  <tr>
    <td class="subMenuPrimaryTitle" height="21">
    	<bean:message key="app.quickLinks" />
    <!-- anchor to skip sub menu -->
    	<a href="#content">
    		<img src="images/shim.gif" alt="Skip Menu" width="1" height="1" border="0" />
    	</a>
    </td>
  </tr>
  
  <tr>
  	<td class="subMenuSecondaryTitle" onmouseover="changeMenuStyle(this,'subMenuSecondaryTitleOver'),showCursor()" onmouseout="changeMenuStyle(this,'subMenuSecondaryTitle'),hideCursor()" height="20" onclick="document.location.href='#'">
		<a class="subMenuSecondary" href="#">
			<bean:message key="app.cabigHome" />
		</a>
	</td>
  </tr>
  
  <tr>
  	<td class="subMenuSecondaryTitle" onmouseover="changeMenuStyle(this,'subMenuSecondaryTitleOver'),showCursor()" onmouseout="changeMenuStyle(this,'subMenuSecondaryTitle'),hideCursor()" height="20" onclick="document.location.href='#'">
  		<a class="subMenuSecondary" href="#">
  			<bean:message key="app.ncicbHome" />
  		</a>
  	</td>
  </tr>
  
  <tr>
  	<td class="subMenuSecondaryTitle" onmouseover="changeMenuStyle(this,'subMenuSecondaryTitleOver'),showCursor()" onmouseout="changeMenuStyle(this,'subMenuSecondaryTitle'),hideCursor()" height="20" onclick="document.location.href='#'">
  		<a class="subMenuSecondary" href="#">
  			<bean:message key="app.siteHome" />
  		</a>
  	</td>
  </tr>
  
  <tr>
  	<td class="subMenuFill" height="100%">
  		&nbsp;
  	</td>
  </tr>
  <tr>
  	<td class="subMenuFooter" height="22">
  		&nbsp;
  	</td>
  </tr>
</table>