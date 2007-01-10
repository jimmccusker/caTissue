/*This is the wrapper function over show_calendar() that allows to select the date only if the operator is Not 'ANY'
opratorListId : Id of the date-operators list box
formNameAndDateFieldId : A string that contains the form name & date field's id
isSecondDateField : A boolean variable that tells whether the date field is first or second
*/
function onDate(operatorListId,formNameAndDateFieldId,isSecondDateField)
{
var dateCombo = document.getElementById(operatorListId);

if(dateCombo.options[dateCombo.selectedIndex].value != "Any")
{
	if(!isSecondDateField)
	{
		show_calendar(formNameAndDateFieldId,null,null,'MM-DD-YYYY')
	}
	else
	{
		if(dateCombo.options[dateCombo.selectedIndex].value == "Between" || dateCombo.options[dateCombo.selectedIndex].value == "Not Between")
		{
			show_calendar(formNameAndDateFieldId,null,null,'MM-DD-YYYY');
		}
	}
}
}
function operatorChanged(rowId,dataType)
{

var textBoxId = rowId+"_textBox1";
var calendarId1 = rowId+"_calendar1";
var opId =  rowId+"_combobox";
if(document.all) {
		// IE.
		var op = document.getElementById(opId).value;
	} else if(document.layers) {
		// Netspace 4
	var op = document.getElementById(opId).value;
	} else {
		// Mozilla
		op = document.forms['categorySearchForm'].elements[opId].value;
	}	
if(op == "Between")
{
	if(document.all) {
		// IE.
		document.getElementById(textBoxId).style.display="block";		
		if(dataType == "true")
		{
			document.getElementById(calendarId1).style.display="block";		
		}
	} else if(document.layers) {
		// Netspace 4
		document.elements[textBoxId].visibility="visible";
	} else {
		// Mozilla
		var textBoxId1 = document.getElementById(textBoxId);
		textBoxId1.style.display="block";
		if(dataType == "true")
		{
			var calId = document.getElementById(calendarId1);
			calId.style.display="block";
		}
	}	
}
else 
{
	if(document.all) {
		// IE.
		document.getElementById(textBoxId).style.display="none";		
			if(dataType == "true")
		{
		document.getElementById(calendarId1).style.display="none";		
		}
	} else if(document.layers) {
		// Netspace 4
		document.elements[textBoxId].visibility="none";
	} else {
		// Mozilla
		var textBoxId1 = document.getElementById(textBoxId);
		textBoxId1.style.display="none";
		if(dataType == "true")
		{
			var calId = document.getElementById(calendarId1);
			calId.style.display="none";
		}
	}	
}
}



	function expand()
	{			
		switchObj = document.getElementById('image');
		dataObj = document.getElementById('collapsableTable');
		resultSetDivObj = document.getElementById('resultSetDiv');
	
		if(dataObj.style.display != 'none') //Clicked on - image
		{
			dataObj.style.display = 'none';				
			switchObj.innerHTML = '<img src="images/nolines_plus.gif" border="0"/>';
			resultSetDivObj.height = 400;
		}
		else  							   //Clicked on + image
		{
			if(navigator.appName == "Microsoft Internet Explorer")
			{					
				dataObj.style.display = 'block';

			}
			else
			{
				dataObj.style.display = 'table-row';
				dataObj.style.display = 'block';

			}
			switchObj.innerHTML = '<img src="images/nolines_minus.gif" border="0"/>';
			resultSetDivObj.height = 320;
		}
	}
	/*function expandSearchHeader()
			{			
				switchObj = document.getElementById('imageCategorySearch');
			//	collapsableHeaderObj = document.getElementById('collapsableHeader');
				collapsableTableObj = document.getElementById('collapsableTable');
				resultSetDivObj = document.getElementById('resultSetDiv');
			
				if(collapsableTableObj.style.display != 'none') //Clicked on - image
				{
				//	collapsableHeaderObj.style.display = 'none';	
					collapsableTableObj.style.display= 'none';
					switchObj.innerHTML = '<img src="images/nolines_plus.gif" border="0"/>';
					resultSetDivObj.height = 450;
				}
				else  							   //Clicked on + image
				{
					if(navigator.appName == "Microsoft Internet Explorer")
					{					
					//	collapsableHeaderObj.style.display = 'block';
						collapsableTableObj.style.display= 'block';
					}
					else
					{
					//	collapsableHeaderObj.style.display = 'table-row';
						collapsableTableObj.style.display = 'table-row';

					}
					switchObj.innerHTML = '<img src="images/nolines_minus.gif" border="0"/>';
					resultSetDivObj.height = 350;
				}
			}*/



		function retriveSearchedEntities(url,nameOfFormToPost) 
		{
			var request = newXMLHTTPReq();				
			var actionURL;
			var handlerFunction = getReadyStateHandler(request,onResponseUpdate,true);
			
			var textFieldValue = document.forms[0].textField.value;

			var classCheckStatus = document.forms[0].classChecked.checked;
			var attributeCheckStatus = document.forms[0].attributeChecked.checked;
			var permissibleValuesCheckStatus = document.forms[0].permissibleValuesChecked.checked;
			var radioCheckStatus;

			request.onreadystatechange = handlerFunction;
					
			if(document.forms[0].selected[0].checked)
				radioCheckStatus = "rb1";
			if(document.forms[0].selected[1].checked)
				radioCheckStatus = "rb2";

			actionURL = "textField=" + textFieldValue + "&attributeChecked=" + attributeCheckStatus + "&classChecked=" + classCheckStatus + "&permissibleValuesChecked=" + permissibleValuesCheckStatus + "&selected=" + radioCheckStatus;

			if(!(classCheckStatus || attributeCheckStatus || permissibleValuesCheckStatus) ) 
			{
				alert("Please select any of the checkbox ");
				onResponseUpdate(" ");
			}
			else if(textFieldValue == "")
			{
				alert("Please Enter the String to search.");
				onResponseUpdate(" ");
			}
			else if(radioCheckStatus == null)
			{
				alert("Please select any of the radio button : 'based on' criteria");
				onResponseUpdate(" ");
			}
				
			else
			{
				<!-- Open connection to servlet -->
				request.open("POST",url,true);	
				request.setRequestHeader("Content-Type","application/x-www-form-urlencoded");	
				request.send(actionURL);
			}
		//	showEntityInformation("");
			//onProduceQueryUpdate("");
		}

		function onResponseUpdate(text)
		{
			if(text == "")
		{
			alert("No results found.");
		}
	
			var element = document.getElementById('resultSet');
			var allElementsArray = text.split(";");
			var row ='<table width="100%" border="0" bordercolor="#FFFFFF" cellspacing="0" cellpadding="0">';

			for(i=0; i<allElementsArray.length; i++)
			{
				var functionCall = "retriveEntityInformation('loadDefineSearchRules.do','categorySearchForm','"+allElementsArray[i]+"')";
				row = row+'<tr><td bgcolor="#FFFFFF"><a href="javascript:'+functionCall+'">' +allElementsArray[i]+ '</a></td></tr>';

			}
			row = row+'</table>';
			element.innerHTML =row;
		}
		
		function retriveEntityInformation(url,nameOfFormToPost,entityName) 
		{	
			var request = newXMLHTTPReq();			
			var actionURL;
			var handlerFunction = getReadyStateHandler(request,showEntityInformation,true);	
			request.onreadystatechange = handlerFunction;				
			actionURL = "entityName=" + entityName;				
			
			<!-- Open connection to servlet -->
			request.open("POST",url,true);	
			request.setRequestHeader("Content-Type","application/x-www-form-urlencoded");	
			request.send(actionURL);		
		} 
		
		function showEntityInformation(text)
		{					
			
			onProduceQueryUpdate("");
			var element = document.getElementById('addLimits');
			element.innerHTML =text;
		}
		
	
		
		function produceQuery(url,nameOfFormToPost, entityName , attributesList) 
		{
			
			//var element = document.getElementById('query');
			var strToCreateQueyObject ="";
			var a = attributesList.split(";");
			var textThis = "";
			queryString ="";
			var stringQuery = "";
			for(i=1; i<a.length; i++)
			{
				var opId =  a[i]+"_combobox";
				var textBoxId = a[i]+"_textBox";
				var textId = document.getElementById(textBoxId).value;
				var textBoxId1 = a[i]+"_textBox1";
				var textId1 = document.getElementById(textBoxId1).value;
				if(navigator.appName == "Microsoft Internet Explorer")
					{					
						var op = document.getElementById(opId).value;
					}
					else
					{
						var op = document.forms[nameOfFormToPost].elements[opId].value;

					}					
				//var queryString =queryString +  "condition"+i+"="+ a[i] + "*" + op + "*" + textId +"; AND ";
				if(op != "Between")
				{
					if(textId != "")
					{
						if(i!=a.length-1)
						{
							textThis = textThis + " "+ a[i] + " " + op + " " + textId + ";  ";								
						}
						if(i==a.length-1)
						{
							textThis = textThis + " "+ a[i] + " " + op + " " + textId+ ";  ";			
						}
						strToCreateQueyObject = strToCreateQueyObject + "condition="+ a[i] + "*" + op + "*" + textId +";";
					}
				}
				if(op == "Between")
				{
					if(textId != "" && textId1!= "")
					{
						if(i!=a.length-1)
						{
							textThis = textThis + " "+ a[i] + " " + op + " (" + textId +", "+textId1 +") ;  ";					
						}
						if(i==a.length-1)
						{
							textThis = textThis + " "+ a[i] + " " + op + " (" + textId +", "+textId1 +") ;  ";	
						}
							strToCreateQueyObject =  strToCreateQueyObject + "condition="+ a[i] + "*" + op + "*" + textId +"*"+textId1+";";
					}
		
				}
			}
			var queryStr = "";
			if(textThis != "")
			{
				queryStr = " " +entityName + " where " + textThis;
			}
			stringQuery = stringQuery + queryStr;
			var request = newXMLHTTPReq();				
			var actionURL;
			var handlerFunction = getReadyStateHandler(request,onProduceQueryUpdate,true);

			request.onreadystatechange = handlerFunction;

			actionURL = "entityName=" + entityName + "&stringToCreateQueryObject=" + strToCreateQueyObject + "&tempStr="+ stringQuery;

			<!-- Open connection to servlet -->
			request.open("POST",url,true);	
			request.setRequestHeader("Content-Type","application/x-www-form-urlencoded");	
			request.send(actionURL);
			//clearForm1(attributesList);
		}

		function onProduceQueryUpdate(text)
		{
		//showEntityInformation("");	
		var element = document.getElementById('queryTableTd');
		element.innerHTML = "";
/*		var messagesTd = document.getElementById('messagesTd');
		var messagesTr = document.getElementById('messagesTr');
		if(text.indexOf("QueryString : ") == -1)
			{
				//var element = document.getElementById('messages');
				
				if(text != "undefined")
				{
					messagesTd.style.visibility="";	
					messagesTd.height ="1px"
					messagesTr.style.visibility="";	
					//element.innerHTML = "<APPLET CODE='DiagrammaticViewApplet.class' WIDTH='150' HEIGHT='25'></APPLET>"
					messagesTd.innerHTML = text;
				}
			} 
		else
			{			
				messagesTd.innerHTML = "";*/
				if(text != "undefined")
				{
					//element.innerHTML = "<APPLET CODE='DiagrammaticViewApplet.class' WIDTH='150' HEIGHT='25'></APPLET>"
					element.innerHTML = text;
				}
		//	}
		}
		function clearForm1(attributesList)
		{
			var a = attributesList.split(";");
			for(i=1; i<a.length; i++)
			{
				var opId =  a[i]+"_combobox";
				var textBoxId = a[i]+"_textBox";
				var textId = document.getElementById(textBoxId).value="";
				var textBoxId1 = a[i]+"_textBox1";
				var textId1 = document.getElementById(textBoxId1).value="";
				var op = document.forms['categorySearchForm'].elements[opId].value="";
			}
		}

		function viewSearchResults()
		{
			document.forms['categorySearchForm'].submit();
		}
		function defineSearchResultsView()
		{
			document.forms['categorySearchForm'].action='DefineSearchResultsView.do';
			document.forms['categorySearchForm'].submit();
		}
		function viewAddLimitsPage()
		{
			document.forms['categorySearchForm'].action = "SearchCategory.do";
			document.forms['categorySearchForm'].submit();
		}