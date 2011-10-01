<jsp:directive.page import="edu.wustl.common.util.global.ApplicationProperties"/>
<script type="text/javascript" src="jss/ext-base.js"></script>
<script type="text/javascript" src="jss/ext-all.js"></script>
<link rel="stylesheet" type="text/css" href="css/clinicalstudyext-all.css" />
<%@ taglib uri="/WEB-INF/AutoCompleteTag.tld" prefix="autocomplete" %>

<%@ include file="/pages/content/common/AutocompleterCommon.jsp" %>
<%@ taglib uri="/WEB-INF/nlevelcombo.tld" prefix="ncombo" %>
<%@ page import="edu.wustl.catissuecore.bean.CollectionProtocolBean"%>
<%@ page language="java" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="css/catissue_suite.css" rel="stylesheet" type="text/css" />


<%@ page import="edu.wustl.catissuecore.util.global.AppUtility"%>
<%@ page import="edu.wustl.catissuecore.bizlogic.SOPBizLogic"%>
<%@ page import="java.util.*"%>
<script>var imgsrc="images/de/";</script>
<script language="JavaScript" type="text/javascript" src="javascripts/de/scr.js"></script>
<script language="JavaScript" type="text/javascript" src="javascripts/de/combobox.js"></script>
<script language="JavaScript" type="text/javascript" src="javascripts/de/ajax.js"></script>
<script language="JavaScript" type="text/javascript" src="/jss/multiselectUsingCombo.js"></script>


<script>

Ext.onReady(function(){
   Ext.QuickTips.init();
   var processingSOPForSpecimen;
   if(document.getElementById('processingSOPForSpecimen') != null)
   	processingSOPForSpecimen = document.getElementById('processingSOPForSpecimen').value

  var myUrl= 'ClinincalStatusComboAction.do?requestFor=specimenEvent&processingSPPName='+processingSOPForSpecimen;
  var ds = new Ext.data.Store({proxy: new Ext.data.HttpProxy({url: myUrl}),
        reader: new Ext.data.JsonReader({root: 'row',totalProperty: 'totalCount',id: 'id'
        }, [{name: 'excerpt', mapping: 'field'}])
    });
      var combo = new Ext.form.ComboBox({
        store: ds,displayField:'excerpt',typeAhead: false,width: 120, pageSize:15,forceSelection: true ,
		queryParam : 'query',mode: 'remote',emptyText:'--Select--',triggerAction: 'all',minChars : 3,queryDelay : 300,
		typeAheadDelay : 900,selectOnFocus:true,tpl: '<tpl for="."><div ext:qtip="{excerpt}" class="x-combo-list-item">{excerpt}</div></tpl>',
        applyTo: 'creationEventForAliquot',fields: ['id_cp'],id: 'aliquot_CE'
   });
   combo.on("expand", function(){if(Ext.isIE || Ext.isIE7){combo.list.setStyle("width", "250");combo.innerList.setStyle("width", "250");}else{combo.list.setStyle("width", "auto");combo.innerList.setStyle("width", "auto");}}, {single: true});

});

</script>

 <tr>
              <td class="cp_tabtable">
                  <br>
                  <table width="100%" border="0" cellpadding="3" cellspacing="0" bgcolor="#FFFFFF">

	                  <tr>
                      <td colspan="3" align="left">
					  <table width="100%" border="0" cellpadding="3" cellspacing="0">
		                <tr>
			               <td width="1%" align="center" class="black_ar"><span class="blue_ar_b"><img src="images/uIEnhancementImages/star.gif" alt="Mandatory" width="6" height="6" hspace="0" vspace="0" /></span></td>
						   <td width="22%" align="left" class="black_ar"><bean:message key="specimen.type"/> </td>
						   <td width="33%" align="left"  class="black_ar">
			<%
				String classValue = (String)form.getClassName();
				specimenTypeList = (List)specimenTypeMap.get(classValue);
				boolean subListEnabled = false;
				if(specimenTypeList == null)
				{
					specimenTypeList = new ArrayList();
					specimenTypeList.add(new NameValueBean(Constants.SELECT_OPTION,"-1"));
				}
				if(Constants.ALIQUOT.equals(form.getLineage()))
				{
					specimenTypeList = new ArrayList();
					specimenTypeList.add(new NameValueBean(form.getType(),form.getType()));
				}
				pageContext.setAttribute(Constants.SPECIMEN_TYPE_LIST, specimenTypeList);
				String subTypeFunctionName ="onSubTypeChangeUnit('className',this,'unitSpan')";
				String readOnlyForAliquot = "false";
				String readOnlyForSpecimen = "false";
				if(Constants.ALIQUOT.equals(form.getLineage())&&operation.equals(Constants.EDIT))
				{
				      readOnlyForAliquot = "true";
				}
				if(!Constants.DERIVED_SPECIMEN.equals(form.getLineage())&&operation.equals(Constants.EDIT))
				{
				      readOnlyForSpecimen = "true";
				}
			%>
									<autocomplete:AutoCompleteTag property="className"
										  optionsList = "<%=request.getAttribute(Constants.SPECIMEN_CLASS_LIST)%>"
										  initialValue="<%=form.getClassName()%>"
										  readOnly="<%=readOnlyForSpecimen%>"
										  onChange="onTypeChange(this);clearTypeCombo()"
										  styleClass="black_ar"
										  size="20"

										/>
								</td>
                                <td width="1%" align="center"><span class="blue_ar_b"><img src="images/uIEnhancementImages/star.gif" alt="Mandatory" width="6" height="6" hspace="0" vspace="0" /></span></td>
							    <td width="15%" align="left"><label for="type" class="black_ar"><bean:message key="specimen.subType"/></label></td>
                                <td width="28%" align="left" class="black_ar">
                                <div id="specimenTypeId">
									<autocomplete:AutoCompleteTag property="type"
									  optionsList = "<%=request.getAttribute(Constants.SPECIMEN_TYPE_MAP)%>"
									  initialValue="<%=form.getType()%>"
									  onChange="<%=subTypeFunctionName%>"
									  readOnly="<%=readOnlyForAliquot%>"
									  dependsOn="<%=form.getClassName()%>"
									  styleClass="black_ar"
									  size="20"

							        />
							        </div>
								</td>
                              </tr>
                              <tr>
                                 <td align="center" class="black_ar"><span class="blue_ar_b"><img src="images/uIEnhancementImages/star.gif" alt="Mandatory" width="6" height="6" hspace="0" vspace="0" /></span></td>
                                 <td align="left" class="black_ar"><bean:message key="specimen.tissueSite"/></td>
                                 <td width="30%" align="left" class="black_new" >
									<autocomplete:AutoCompleteTag property="tissueSite"
									  optionsList = "<%=request.getAttribute(Constants.TISSUE_SITE_LIST)%>"
									  initialValue="<%=form.getTissueSite()%>"
									  readOnly="<%=readOnlyForAliquot%>"
									  styleClass="black_ar"
									  size="20"

									/>
									<span class="black_ar">
				<%
					String url = "TissueSiteTree.do?pageOf=pageOfTissueSite&propertyName=tissueSite&cdeName=Tissue Site";
				%>
									<a href="#" onclick="javascript:NewWindow('<%=url%>','name','360','525','no');return false">
										<img src="images/uIEnhancementImages/ic_cl_diag.gif" border="0" width="16" height="16" title='Tissue Site Selector' alt="Clinical Diagnosis"></a></span></td>
                               <td align="center" class="black_ar"><span class="blue_ar_b"><img src="images/uIEnhancementImages/star.gif" alt="Mandatory" width="6" height="6" hspace="0" vspace="0" /></span></td>
                               <td align="left" class="black_ar"><bean:message key="specimen.tissueSide"/></td>
                                <td align="left" class="black_ar">
									<autocomplete:AutoCompleteTag property="tissueSide"
										optionsList = "<%=request.getAttribute(Constants.TISSUE_SIDE_LIST)%>"
									    initialValue="<%=form.getTissueSide()%>"
									    readOnly="<%=readOnlyForAliquot%>"
										styleClass="black_ar"
										size="20"
								    />
								</td>
                              </tr>
                              <tr>
                                <td align="center" class="black_ar"><span class="blue_ar_b"><img src="images/uIEnhancementImages/star.gif" alt="Mandatory" width="6" height="6" hspace="0" vspace="0" /></span></td>
                                <td align="left" class="black_ar"><bean:message key="specimen.pathologicalStatus"/> </td>
                                <td width="30%" align="left" class="black_ar">
									<autocomplete:AutoCompleteTag property="pathologicalStatus"
									  optionsList = "<%=request.getAttribute(Constants.PATHOLOGICAL_STATUS_LIST)%>"
									  initialValue="<%=form.getPathologicalStatus()%>"
									  readOnly="<%=readOnlyForAliquot%>"
									  styleClass="black_ar"
									  size="20"
									/>
								</td>
                               <td align="center" class="black_ar"><span class="blue_ar_b"><img src="images/uIEnhancementImages/star.gif" alt="Mandatory" width="6" height="6" hspace="0" vspace="0" /></span></td>
                               <td align="left" class="black_ar"><bean:message key="cpbasedentry.storagelocation"/></td>
                                <td align="left" class="black_ar">
									<autocomplete:AutoCompleteTag property="storageLocationForSpecimen"
									  optionsList = '<%=request.getAttribute("storageContainerList")%>'
									  initialValue="<%=form.getStorageLocationForSpecimen()%>"
									  styleClass="black_ar"
									  size="20"
									/>
								</td>
                              </tr>
                              <tr>
                                <td align="center" class="black_ar"><span class="blue_ar_b"><img src="images/uIEnhancementImages/star.gif" alt="Mandatory" width="6" height="6" hspace="0" vspace="0" /></span></td>
                                <td align="left" class="black_ar"><bean:message key="specimen.quantity"/></td>
                                <td align="left" class="black_ar_s">	<html:text styleClass="black_ar" size="10" maxlength="10"styleId="quantity" property="quantity" style="text-align:right"/><span id="unitSpan">&nbsp;<%=unitSpecimen%></span><html:hidden property="unit"/></td>
                                <td align="center" class="black_ar">&nbsp;</td>
                                <td align="left" class="black_ar"><bean:message key="specimen.concentration"/></td>
                                <td align="left" class="black_ar_s">
			<%
				boolean concentrationDisabled = true;
				if(form.getClassName().equals("Molecular") && !Constants.ALIQUOT.equals(form.getLineage()))
				concentrationDisabled = false;
			%>
     									<html:text styleClass="black_ar" maxlength="10"  size="10"	styleId="concentration" property="concentration"  readonly="<%=readOnlyForAll%>" disabled="<%=concentrationDisabled%>" style="text-align:right"/>&nbsp;<bean:message key="specimen.concentrationUnit" /></td>
                              </tr>
							  <tr>
                               <td align="center" class="black_ar">&nbsp;</td>
                              <td align="left" class="black_ar">Specimen Creation Event</td>
                                <td align="left" class="black_ar">
									<autocomplete:AutoCompleteTag property="creationEventForSpecimen"
									  optionsList = '<%=request.getAttribute("sopEventList")%>'
									  initialValue="<%=form.getCreationEventForSpecimen()%>"
									  styleClass="black_ar"
									  size="30"
									/>
								</td>

                              <tr>

								<td align="center" class="black_ar">&nbsp;</td>
                                <td align="left" class="black_ar"><label for="institutionId">Processing SPP</label></td>
                                <td align="left" class="black_ar">
									<autocomplete:AutoCompleteTag property="processingSOPForSpecimen"
									  optionsList = "<%=new SOPBizLogic().getAllSOPNames()%>"
									  initialValue="<%=form.getProcessingSOPForSpecimen()%>"
									  styleClass="black_ar"
									  onChange="resetDSforDerive(this)"
									  size="20"
									/>
								</td>
                              </tr>
							  <tr>
							  			<%
									if(Variables.isTemplateBasedLblGeneratorAvl)
									{
								%>
                                <td align="center" class="black_ar">&nbsp;</td>
                                <td align="left" class="black_ar"><label>Label Format</label></td>
                                <td align="left">


								<html:text styleClass="black_ar" property="labelFormat" maxlength="255" styleId="labelFormat"  size="23"/>

								</td>
								<%}
								else{%>
								<td colspan="3" />
								<%}%>
								</tr>
                            </table>
                            <br>

					</td>
                   </tr>
                   <tr onclick="javascript:showHide('derive_specimen')">
					 <td width="97%" align="left" class="tr_bg_blue1">
						<span class="blue_ar_b">
							<bean:message key="cpbasedentry.derivespecimens"/>
						</span>
					  </td>
                      <td width="3%" align="right" class="tr_bg_blue1">
						<a href="#" id="imgArrow_derive_specimen">
							<img src="images/uIEnhancementImages/dwn_arrow1.gif" alt="Show Details" border="0" width="80" height="9" hspace="10" vspace="0"/>
						</a>
					  </td>
                     </tr>
                       <td colspan="2" class="showhide1">
							<div id="derive_specimen" style="display:none" >
								<table width="100%" border="0" cellspacing="0" cellpadding="4">
								<%
								if(Variables.isTemplateBasedLblGeneratorAvl)
								{
							%>
									<tr>
                              <td width="2%" class="tableheading"><span class="black_ar_b">

                              </span></td>
                              <td width="12%" class="tableheading"><span class="black_ar_b"><span class="blue_ar_b"><img src="images/uIEnhancementImages/star.gif" alt="Mandatory" width="6" height="6" hspace="0" vspace="0" /> </span><bean:message key="collectionprotocol.specimenclass" /> </span></td>
                              <td width="12%" class="tableheading"><span class="black_ar_b"><span class="blue_ar_b"><img src="images/uIEnhancementImages/star.gif" alt="Mandatory" width="6" height="6" hspace="0" vspace="0" /></span> <bean:message key="collectionprotocol.specimetype" /> </span></td>
                              <td width="12%" class="tableheading"><span class="black_ar_b"><span class="blue_ar_b"><img src="images/uIEnhancementImages/star.gif" alt="Mandatory" width="6" height="6" hspace="0" vspace="0" /></span> <bean:message key="cpbasedentry.storagelocation"/></span></td>
                              <td width="6%" class="tableheading"><span class="black_ar_b"><bean:message key="collectionprotocol.quantity" /></span></td>
                              <td width="6%" class="tableheading"><span class="black_ar_b"><span class="blue_ar_b"><img src="images/uIEnhancementImages/star.gif" alt="Mandatory" width="6" height="6" hspace="0" vspace="0" /></span> Conc.</span></td>
							  <td width="12%" class="tableheading"><span class="black_ar_b">Specimen Creation Event</span></td>
							  <td width="12%" class="tableheading"><span class="black_ar_b">Processing SPP</span></td>

							   <td width="25%" class="tableheading"><span class="black_ar_b">Label Format</span></td>
                            </tr>
							<%
								}
							   else
							   {%>
								<tr>
                              <td width="2%" class="tableheading"><span class="black_ar_b">

                              </span></td>
                              <td width="17%" class="tableheading"><span class="black_ar_b"><span class="blue_ar_b"><img src="images/uIEnhancementImages/star.gif" alt="Mandatory" width="6" height="6" hspace="0" vspace="0" /> </span><bean:message key="collectionprotocol.specimenclass" /> </span></td>
                              <td width="17%" class="tableheading"><span class="black_ar_b"><span class="blue_ar_b"><img src="images/uIEnhancementImages/star.gif" alt="Mandatory" width="6" height="6" hspace="0" vspace="0" /></span> <bean:message key="collectionprotocol.specimetype" /> </span></td>
                              <td width="17%" class="tableheading"><span class="black_ar_b"><span class="blue_ar_b"><img src="images/uIEnhancementImages/star.gif" alt="Mandatory" width="6" height="6" hspace="0" vspace="0" /></span> <bean:message key="cpbasedentry.storagelocation"/></span></td>
                              <td width="6%" class="tableheading"><span class="black_ar_b"><bean:message key="collectionprotocol.quantity" /></span></td>
                              <td width="6%" class="tableheading"><span class="black_ar_b"><span class="blue_ar_b"><img src="images/uIEnhancementImages/star.gif" alt="Mandatory" width="6" height="6" hspace="0" vspace="0" /></span> Conc. </span></td>
				<td width="17%" class="tableheading"><span class="black_ar_b">Specimen Creation Event</span></td>
							  <td width="17%" class="tableheading"><span class="black_ar_b">Processing SPP</span></td>

                            </tr>
							   <%}%>
						  <script> document.forms[0].noOfDeriveSpecimen.value = <%=noOfDeriveSpecimen%> </script>

										<TBODY id="DeriveSpecimenBean">
			<%

				for(int rowno=1;rowno<=noOfDeriveSpecimen;rowno++)
				{
					String id = "deriveSpecimenValue(DeriveSpecimenBean:" + rowno + "_id)";
					String specimenClass = "deriveSpecimenValue(DeriveSpecimenBean:" + rowno + "_specimenClass)";
					String classKey = "DeriveSpecimenBean:" + rowno + "_specimenClass";
					String unit = "deriveSpecimenValue(DeriveSpecimenBean:" + rowno + "_unit)";
					String specimenUnit = "DeriveSpecimenBean:" + rowno + "_unit";
					String specimenType = "deriveSpecimenValue(DeriveSpecimenBean:" + rowno + "_specimenType)";
					String srSubTypeKeyName = "DeriveSpecimenBean:" + rowno + "_specimenType";
					String storageLocation = "deriveSpecimenValue(DeriveSpecimenBean:" + rowno + "_storageLocation)";
					String quantity = "deriveSpecimenValue(DeriveSpecimenBean:" + rowno + "_quantity)";
					String quantityvalue = "DeriveSpecimenBean:" + rowno + "_quantity";
					String concentration = "deriveSpecimenValue(DeriveSpecimenBean:" + rowno + "_concentration)";
					String creationEventForSpecimen = "deriveSpecimenValue(DeriveSpecimenBean:" + rowno + "_creationEvent)";
					String processingSOPForSpecimen = "deriveSpecimenValue(DeriveSpecimenBean:" + rowno + "_processingSOP)";
//					String labelType = "deriveSpecimenValue(DeriveSpecimenBean:" + rowno + "_labelGenType)";

					String labelFormat = "deriveSpecimenValue(DeriveSpecimenBean:" + rowno + "_labelFormat)";

					String MolecularConc = "DeriveSpecimenBean:" + rowno + "_concentration";
					String chk = "checkBox_" + rowno;
					String changeClass = "changeUnit('"+specimenClass+"','"+unit+"','"+concentration+"','"+specimenType+"')";
					String changeType = "onSubTypeChangeUnitforCP('"+specimenClass+"','" + unit+ "')";
					String comboDataStorName = "ds_"+rowno;

					//String changeLabelGenType = "labelGenTypechangedWithId('"+labelType+"','"+labelFormat+"')";
			%>
<tr>



					<html:hidden property="<%=id%>" />

			<%
					String className = (String)form.getDeriveSpecimenValue(classKey);
					String typeclassValue = (String)form.getDeriveSpecimenValue(srSubTypeKeyName);
					specimenTypeList = (List)specimenTypeMap.get(className);

					boolean subListEnabled1 = false;
					if(specimenTypeList == null)
					{
						specimenTypeList = new ArrayList();
						specimenTypeList.add(new NameValueBean(Constants.SELECT_OPTION,"-1"));
						specimenClassList.add(0,new NameValueBean(Constants.SELECT_OPTION,"-1"));
					}
					pageContext.setAttribute(Constants.SPECIMEN_TYPE_LIST, specimenTypeList);
					pageContext.setAttribute(Constants.SPECIMEN_CLASS_LIST, specimenClassList);
				%>
								<td class="black_ar"><label>
                                  <input type=checkbox name="<%=chk%>" id="<%=chk%>" >
                                </label></td>
                                <td class="black_ar" >
								<input type="text" name="combo_<%=specimenClass%>" id="combo_<%=specimenClass%>" size="6" value='<%=form.getDeriveSpecimenValue("DeriveSpecimenBean:" + rowno + "_specimenClass")%>'/>
									<script>

									Ext.onReady(function(){var myUrl= 'ClinincalStatusComboAction.do?requestFor=specimenClass';var ds = new Ext.data.Store({proxy: new Ext.data.HttpProxy({url: myUrl}),reader: new Ext.data.JsonReader({root: 'row',totalProperty: 'totalCount',id: 'id'}, [{name: 'id', mapping: 'id'},{name: 'excerpt', mapping: 'field'}])});var combo = new Ext.form.ComboBox({store: ds,hiddenName: '<%=specimenClass%>',displayField:'excerpt',valueField: 'id',typeAhead: 'false',pageSize:15,forceSelection: 'true',queryParam : 'query',mode: 'remote',triggerAction: 'all',minChars : 3,queryDelay:500,lazyInit:true,emptyText:'--Select--',selectOnFocus:'true',applyTo: 'combo_<%=specimenClass%>'});combo.on("expand", function() {if(Ext.isIE || Ext.isIE7){combo.list.setStyle("width", "250");combo.innerList.setStyle("width", "250");}else{combo.list.setStyle("width", "auto");combo.innerList.setStyle("width", "auto");}}, {single: true});ds.on('load',function(){if (this.getAt(0) != null && this.getAt(0).get('excerpt').toLowerCase().startsWith(combo.getRawValue().toLowerCase())) {combo.typeAheadDelay=50;} else {combo.typeAheadDelay=60000}});combo.on('select',function(){setStudyFormValues(combo.value,'<%=rowno%>');});});
									</script>
								</td>
                                <td class="black_ar" >
								<input type="text" name="combo_<%=specimenType%>" id="combo_<%=specimenType%>" size="6" value='<%=form.getDeriveSpecimenValue("DeriveSpecimenBean:" + rowno + "_specimenType")%>'/>
									<script>
									Ext.onReady(function(){var myUrl= 'ClinincalStatusComboAction.do?requestFor=specimenType';var ds = new Ext.data.Store({proxy: new Ext.data.HttpProxy({url: myUrl}),reader: new Ext.data.JsonReader({root: 'row',totalProperty: 'totalCount',id: 'id'}, [{name: 'id', mapping: 'id'},{name: 'excerpt', mapping: 'field'}])});var combo = new Ext.form.ComboBox({store: ds,hiddenName: '<%=specimenType%>',displayField:'excerpt',valueField: 'id',typeAhead: 'false',pageSize:15,forceSelection: 'true',queryParam : 'query',mode: 'remote',triggerAction: 'all',minChars : 3,queryDelay:500,lazyInit:true,emptyText:'--Select--',selectOnFocus:'true',applyTo: 'combo_<%=specimenType%>',fields: ['id_cp'],id: '<%=comboDataStorName%>'});combo.on("expand", function() {if(Ext.isIE || Ext.isIE7){combo.list.setStyle("width", "250");combo.innerList.setStyle("width", "250");}else{combo.list.setStyle("width", "auto");combo.innerList.setStyle("width", "auto");}}, {single: true});ds.on('load',function(){if (this.getAt(0) != null && this.getAt(0).get('excerpt').toLowerCase().startsWith(combo.getRawValue().toLowerCase())) {combo.typeAheadDelay=50;} else {combo.typeAheadDelay=60000}});});
									</script>

								</td>
                                <td class="black_ar" >
								<input type="text" name="combo_<%=storageLocation%>" id="combo_<%=storageLocation%>" size="6" value='<%=form.getDeriveSpecimenValue("DeriveSpecimenBean:" + rowno + "_storageLocation")%>'/>
									<script>
									Ext.onReady(function(){var myUrl= 'ClinincalStatusComboAction.do?requestFor=storageLocation';var ds = new Ext.data.Store({proxy: new Ext.data.HttpProxy({url: myUrl}),reader: new Ext.data.JsonReader({root: 'row',totalProperty: 'totalCount',id: 'id'}, [{name: 'id', mapping: 'id'},{name: 'excerpt', mapping: 'field'}])});var combo = new Ext.form.ComboBox({store: ds,hiddenName: '<%=storageLocation%>',displayField:'excerpt',valueField: 'id',typeAhead: 'false',pageSize:15,forceSelection: 'true',queryParam : 'query',mode: 'remote',triggerAction: 'all',minChars : 3,queryDelay:500,lazyInit:true,emptyText:'--Select--',selectOnFocus:'true',applyTo: 'combo_<%=storageLocation%>'});combo.on("expand", function() {if(Ext.isIE || Ext.isIE7){combo.list.setStyle("width", "250");combo.innerList.setStyle("width", "250");}else{combo.list.setStyle("width", "auto");combo.innerList.setStyle("width", "auto");}}, {single: true});ds.on('load',function(){if (this.getAt(0) != null && this.getAt(0).get('excerpt').toLowerCase().startsWith(combo.getRawValue().toLowerCase())) {combo.typeAheadDelay=50;} else {combo.typeAheadDelay=60000}});});
									</script>

								</td>
                                <td class="black_ar">
			<%

					String qtyValue = (String)form.getDeriveSpecimenValue(quantityvalue);
					String concValue = (String)form.getDeriveSpecimenValue(MolecularConc);
					String strHiddenUnitValue = "" + changeUnit(className,typeclassValue);
					if(qtyValue == null || qtyValue.equals(""))
					{
						qtyValue="0";
					}
					if(concValue == null || concValue.equals(""))
					{
						concValue="0";
					}
				%>
									<html:text styleClass="black_ar" size="3"  maxlength="10"
										styleId="<%=quantity%>" property="<%=quantity%>"
										value="<%=qtyValue%>" style="text-align:right"/>
									<span id="<%=unit%>">
										<%=strHiddenUnitValue%>
									</span>
								</td>
                                <td class="black_ar">
									<html:text styleClass="black_ar" size="3"  maxlength="10"
										styleId="<%=concentration%>" property="<%=concentration%>"
										disabled="<%=concReadOnly%>" value="<%=concValue%>" style="text-align:right"/>
								</td>

								 <td class="black_ar" >
								 <input type="text" name="combo_<%=creationEventForSpecimen%>" id="combo_<%=creationEventForSpecimen%>" size="6" value='<%=form.getDeriveSpecimenValue("DeriveSpecimenBean:" + rowno + "_creationEvent")%>'/>
									<script>
									var processingSPPName = "";
<%
							comboDataStorName = "ds_ce_"+rowno;
%>
		if(document.getElementById('processingSOPForSpecimen') != null)
	   			processingSPPName = document.getElementById('processingSOPForSpecimen').value;
									Ext.onReady(function(){var myUrl= 'ClinincalStatusComboAction.do?requestFor=specimenEvent&processingSPPName='+processingSPPName;var ds = new Ext.data.Store({proxy: new Ext.data.HttpProxy({url: myUrl}),reader: new Ext.data.JsonReader({root: 'row',totalProperty: 'totalCount',id: 'id'}, [{name: 'id', mapping: 'id'},{name: 'excerpt', mapping: 'field'}])});var combo = new Ext.form.ComboBox({store: ds,hiddenName: '<%=creationEventForSpecimen%>',displayField:'excerpt',valueField: 'id',typeAhead: 'false',pageSize:15,forceSelection: 'true',queryParam : 'query',mode: 'remote',triggerAction: 'all',minChars : 3,queryDelay:500,lazyInit:true,emptyText:'--Select--',selectOnFocus:'true',applyTo: 'combo_<%=creationEventForSpecimen%>', fields: ['id_cp'],id: '<%=comboDataStorName%>'});combo.on("expand", function() {if(Ext.isIE || Ext.isIE7){combo.list.setStyle("width", "250");combo.innerList.setStyle("width", "250");}else{combo.list.setStyle("width", "auto");combo.innerList.setStyle("width", "auto");}}, {single: true});ds.on('load',function(){if (this.getAt(0) != null && this.getAt(0).get('excerpt').toLowerCase().startsWith(combo.getRawValue().toLowerCase())) {combo.typeAheadDelay=50;} else {combo.typeAheadDelay=60000}});});
									</script>

								</td>

								 <td class="black_ar" >
								 <input type="text" name="combo_<%=processingSOPForSpecimen%>" id="combo_<%=processingSOPForSpecimen%>" size="6" value='<%=form.getDeriveSpecimenValue("DeriveSpecimenBean:" + rowno + "_processingSOP")%>'/>
									<script>
									Ext.onReady(function(){var myUrl= 'ClinincalStatusComboAction.do?requestFor=processingSPP';var ds = new Ext.data.Store({proxy: new Ext.data.HttpProxy({url: myUrl}),reader: new Ext.data.JsonReader({root: 'row',totalProperty: 'totalCount',id: 'id'}, [{name: 'id', mapping: 'id'},{name: 'excerpt', mapping: 'field'}])});var combo = new Ext.form.ComboBox({store: ds,hiddenName: '<%=processingSOPForSpecimen%>',displayField:'excerpt',valueField: 'id',typeAhead: 'false',pageSize:15,forceSelection: 'true',queryParam : 'query',mode: 'remote',triggerAction: 'all',minChars : 3,queryDelay:500,lazyInit:true,emptyText:'--Select--',selectOnFocus:'true',applyTo: 'combo_<%=processingSOPForSpecimen%>'});combo.on("expand", function() {if(Ext.isIE || Ext.isIE7){combo.list.setStyle("width", "250");combo.innerList.setStyle("width", "250");}else{combo.list.setStyle("width", "auto");combo.innerList.setStyle("width", "auto");}}, {single: true});ds.on('load',function(){if (this.getAt(0) != null && this.getAt(0).get('excerpt').toLowerCase().startsWith(combo.getRawValue().toLowerCase())) {combo.typeAheadDelay=50;} else {combo.typeAheadDelay=60000}});});
									</script>


								</td>

						<%
								if(Variables.isTemplateBasedLblGeneratorAvl)
								{
							%>
								 <td class="black_ar">


								<html:text styleClass="black_ar" maxlength="255" property="<%=labelFormat%>" styleId="<%=labelFormat%>"  size="25"/>



								</td>
						<%}%>
								</tr>



			<%
				}
			%>
							</tbody>
							<%
							String deleteSpecimenRequirements = "deleteChecked('DeriveSpecimenBean','CreateSpecimenTemplate.do?operation="+operation+"&pageOf=delete&key="+mapKey+"',document.forms[0].noOfDeriveSpecimen,'checkBox_',false)";
			%>
							<tr>
							<%
								if (Variables.isTemplateBasedLblGeneratorAvl)
								{
							%>
									<td colspan="9">
							<%
								}
								else
								{
							%>
                              		<td colspan="8">
							<%
								}
							%>
									<html:button property="addSpecimenReq" styleClass="black_ar" value="Add More" onclick="insRow('DeriveSpecimenBean')"/>

									<html:button property="deleteSpecimenReq" styleClass="black_ar" onclick="<%=deleteSpecimenRequirements %>" ><bean:message key="buttons.delete"/>
									</html:button>
								</td>
                              </tr>
                          </table>
						</div>
					</td>
                  </tr>
                   <tr>
                      <td colspan="2" class="bottomtd"></td>
                    </tr>
                  <tr onclick="javascript:showHide('aliquot')">
                     <td align="left" class="tr_bg_blue1"><span class="blue_ar_b"><bean:message key="cpbasedentry.aliquots"/></span></td>
                    <td align="right" class="tr_bg_blue1">
						<a href="#" id="imgArrow_aliquot">
							<img src="images/uIEnhancementImages/dwn_arrow1.gif" alt="Show Details" border="0" width="80" height="9" hspace="10" vspace="0"/>
						</a>
					</td>
                 </tr>
                 <tr>
                    <td colspan="2" align="left" class="showhide1">
						<div id="aliquot" style="display:none" >
							<table width="100%" border="0" cellspacing="0" cellpadding="4">
									<%
									String storageLoc = form.getStorageLocationForAliquotSpecimen();
									if(form.getStorageLocationForAliquotSpecimen() == null)
									{
									storageLoc ="";
									}
								if(Variables.isTemplateBasedLblGeneratorAvl)
								{
							%>
								<tr>

									<td width="10%" class="black_ar" >
									<bean:message key="aliquots.noOfAliquots"/>
									</td>
									<td width="10%" class="black_ar">
									<bean:message key="aliquots.qtyPerAliquot"/>
									</td>
									<td width="15%" class="black_ar">
									<bean:message key="cpbasedentry.storagelocation"/>
									</td>
									<td width="20%" class="black_ar">
									Specimen Creation Event
									</td>
									<td width="25%" class="black_ar">
									Processing SPP
									</td>

									<td width="25%" align="left" class="black_ar" >Label Format</td>
								</tr>
								<tr>
		                           <td class="black_ar" >

										 <html:text styleClass="black_ar" styleId="noOfAliquots" size="8" property="noOfAliquots" style="text-align:right" maxlength="50" />
									</td>
		                            <td class="black_ar">

										 <html:text styleClass="black_ar" styleId="quantityPerAliquot" size="8" property="quantityPerAliquot" style="text-align:right" maxlength="50" />
									</td>
		                            <td class="black_ar">

									<input type="text" name="combo_storageLocationForAliquotSpecimen" id="combo_storageLocationForAliquotSpecimen" size="10" value="<%=storageLoc%>"/>
										<script>
									Ext.onReady(function(){var myUrl= 'ClinincalStatusComboAction.do?requestFor=storageLocation';var ds = new Ext.data.Store({proxy: new Ext.data.HttpProxy({url: myUrl}),reader: new Ext.data.JsonReader({root: 'row',totalProperty: 'totalCount',id: 'id'}, [{name: 'id', mapping: 'id'},{name: 'excerpt', mapping: 'field'}])});var combo = new Ext.form.ComboBox({store: ds,hiddenName: 'storageLocationForAliquotSpecimen',displayField:'excerpt',valueField: 'id',typeAhead: 'false',pageSize:15,forceSelection: 'true',queryParam : 'query',mode: 'remote',triggerAction: 'all',minChars : 3,queryDelay:500,lazyInit:true,emptyText:'--Select--',selectOnFocus:'true',applyTo: 'combo_storageLocationForAliquotSpecimen'});combo.on("expand", function() {if(Ext.isIE || Ext.isIE7){combo.list.setStyle("width", "250");combo.innerList.setStyle("width", "250");}else{combo.list.setStyle("width", "auto");combo.innerList.setStyle("width", "auto");}}, {single: true});ds.on('load',function(){if (this.getAt(0) != null && this.getAt(0).get('excerpt').toLowerCase().startsWith(combo.getRawValue().toLowerCase())) {combo.typeAheadDelay=50;} else {combo.typeAheadDelay=60000}});});
									</script>


									</td>

									 <td align="left" class="black_ar">
						<input property="creationEventForAliquot" type="text" id="creationEventForAliquot" name="creationEventForAliquot" value="<%=form.getCreationEventForAliquot()%>" onmouseover="showTip(this.id)"/>
						</td>

									 <td class="black_ar">

										<input type="text" name="combo_processingSOPForAliquot" id="combo_processingSOPForAliquot" size="12" value="<%=form.getProcessingSOPForAliquot()%>"/>
										<script>
									Ext.onReady(function(){var myUrl= 'ClinincalStatusComboAction.do?requestFor=processingSPP';var ds = new Ext.data.Store({proxy: new Ext.data.HttpProxy({url: myUrl}),reader: new Ext.data.JsonReader({root: 'row',totalProperty: 'totalCount',id: 'id'}, [{name: 'id', mapping: 'id'},{name: 'excerpt', mapping: 'field'}])});var combo = new Ext.form.ComboBox({store: ds,hiddenName: 'processingSOPForAliquot',displayField:'excerpt',valueField: 'id',typeAhead: 'false',pageSize:15,forceSelection: 'true',queryParam : 'query',mode: 'remote',triggerAction: 'all',minChars : 3,queryDelay:500,lazyInit:true,emptyText:'--Select--',selectOnFocus:'true',applyTo: 'combo_processingSOPForAliquot'});combo.on("expand", function() {if(Ext.isIE || Ext.isIE7){combo.list.setStyle("width", "250");combo.innerList.setStyle("width", "250");}else{combo.list.setStyle("width", "auto");combo.innerList.setStyle("width", "auto");}}, {single: true});ds.on('load',function(){if (this.getAt(0) != null && this.getAt(0).get('excerpt').toLowerCase().startsWith(combo.getRawValue().toLowerCase())) {combo.typeAheadDelay=50;} else {combo.typeAheadDelay=60000}});});
									</script>
									</td>

								 <td align="left" class="black_ar">

								<html:text styleClass="black_ar" maxlength="255" property="labelFormatForAliquot" styleId="labelFormatForAliquot"  size="20"/>



								</td>

								</tr>
								<%}
								else
								{%>
									<tr>

									<td width="10%" class="black_ar" >
									<bean:message key="aliquots.noOfAliquots"/>
									</td>
									<td width="10%" class="black_ar">
									<bean:message key="aliquots.qtyPerAliquot"/>
									</td>
									<td width="10%" class="black_ar">
									<bean:message key="cpbasedentry.storagelocation"/>
									</td>
									<td width="20%" class="black_ar">
									Specimen Creation Event
									</td>
									<td width="20%" class="black_ar">
									Processing SPP
									</td>


								</tr>
								<tr>
		                           <td class="black_ar" >

										 <html:text styleClass="black_ar" styleId="noOfAliquots" size="8" property="noOfAliquots" style="text-align:right" maxlength="20" />
									</td>
		                            <td class="black_ar">

										 <html:text styleClass="black_ar" styleId="quantityPerAliquot" size="8" property="quantityPerAliquot" style="text-align:right" maxlength="20" />
									</td>
		                            <td class="black_ar">

										<input type="text" name="combo_storageLocationForAliquotSpecimen" id="combo_storageLocationForAliquotSpecimen" size="10" value="<%=storageLoc%>"/>
										<script>
									Ext.onReady(function(){var myUrl= 'ClinincalStatusComboAction.do?requestFor=storageLocation';var ds = new Ext.data.Store({proxy: new Ext.data.HttpProxy({url: myUrl}),reader: new Ext.data.JsonReader({root: 'row',totalProperty: 'totalCount',id: 'id'}, [{name: 'id', mapping: 'id'},{name: 'excerpt', mapping: 'field'}])});var combo = new Ext.form.ComboBox({store: ds,hiddenName: 'storageLocationForAliquotSpecimen',displayField:'excerpt',valueField: 'id',typeAhead: 'false',pageSize:15,forceSelection: 'true',queryParam : 'query',mode: 'remote',triggerAction: 'all',minChars : 3,queryDelay:500,lazyInit:true,emptyText:'--Select--',selectOnFocus:'true',applyTo: 'combo_storageLocationForAliquotSpecimen'});combo.on("expand", function() {if(Ext.isIE || Ext.isIE7){combo.list.setStyle("width", "250");combo.innerList.setStyle("width", "250");}else{combo.list.setStyle("width", "auto");combo.innerList.setStyle("width", "auto");}}, {single: true});ds.on('load',function(){if (this.getAt(0) != null && this.getAt(0).get('excerpt').toLowerCase().startsWith(combo.getRawValue().toLowerCase())) {combo.typeAheadDelay=50;} else {combo.typeAheadDelay=60000}});});
									</script>
									</td>

									 <td class="black_ar">

										<input property="creationEventForAliquot" type="text" id="creationEventForAliquot" name="creationEventForAliquot" value="<%=form.getCreationEventForAliquot()%>" onmouseover="showTip(this.id)"/>
									</td>

									 <td class="black_ar">

									 <input type="text" name="combo_processingSOPForAliquot" id="combo_processingSOPForAliquot" size="12" value="<%=form.getProcessingSOPForAliquot()%>"/>
										<script>
									Ext.onReady(function(){var myUrl= 'ClinincalStatusComboAction.do?requestFor=processingSPP';var ds = new Ext.data.Store({proxy: new Ext.data.HttpProxy({url: myUrl}),reader: new Ext.data.JsonReader({root: 'row',totalProperty: 'totalCount',id: 'id'}, [{name: 'id', mapping: 'id'},{name: 'excerpt', mapping: 'field'}])});var combo = new Ext.form.ComboBox({store: ds,hiddenName: 'processingSOPForAliquot',displayField:'excerpt',valueField: 'id',typeAhead: 'false',pageSize:15,forceSelection: 'true',queryParam : 'query',mode: 'remote',triggerAction: 'all',minChars : 3,queryDelay:500,lazyInit:true,emptyText:'--Select--',selectOnFocus:'true',applyTo: 'combo_processingSOPForAliquot'});combo.on("expand", function() {if(Ext.isIE || Ext.isIE7){combo.list.setStyle("width", "250");combo.innerList.setStyle("width", "250");}else{combo.list.setStyle("width", "auto");combo.innerList.setStyle("width", "auto");}}, {single: true});ds.on('load',function(){if (this.getAt(0) != null && this.getAt(0).get('excerpt').toLowerCase().startsWith(combo.getRawValue().toLowerCase())) {combo.typeAheadDelay=50;} else {combo.typeAheadDelay=60000}});});
									</script>


									</td>

								</tr>
								<%}%>
                          </table>
						</div>
                      </td>
                    </tr>
                    <tr>
                      <td colspan="2" class="bttomtd"></td>
                    </tr>
                         <tr>
                    	   <td colspan="2" class="buttonbg">
								<html:button styleClass="blue_ar_b" property="submitPage" onclick="saveSpecimens()"	>
								<bean:message key="cpbasedentry.savespecimenrequirements"/>
								</html:button>
							<logic:equal name="operation" value="edit">
								&nbsp;|
								<html:button styleClass="blue_ar_b" property="submitPage" onclick="deleteEvent()">
										<bean:message key="buttons.delete"/>
								</html:button>
							</logic:equal>
							<logic:notEqual name="operation" value="edit">
								&nbsp;
							</logic:notEqual>
							</td>
                    </tr>
             </table>
		</td>
	</tr>