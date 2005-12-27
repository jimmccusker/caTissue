package edu.wustl.catissuecore.action;


/**
 * Common action class used for initializing field's value of JSP 
 * @author namita_srivastava
 *
 */
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.tree.DefaultMutableTreeNode;

import edu.wustl.catissuecore.actionForm.AdvanceSearchForm;
import edu.wustl.catissuecore.bizlogic.QueryBizLogic;
import edu.wustl.catissuecore.query.AdvancedConditionsNode;
import edu.wustl.catissuecore.query.Condition;
import edu.wustl.catissuecore.query.DataElement;
import edu.wustl.catissuecore.query.Operator;
import edu.wustl.catissuecore.util.global.Constants;
import edu.wustl.catissuecore.vo.SearchFieldData;
import edu.wustl.common.util.logger.Logger;

public abstract class AdvanceSearchUIAction extends BaseAction 
{
	/**
	 * Used for setting value of map of form in edit case
	 * @param aForm Common form form for all Search Pages
	 * @param str represents id as string
	 * @param request HttpServletRequest
	 */
	protected void setMapValue(AdvanceSearchForm aForm,String str,HttpServletRequest request) throws Exception
    {
		//Represents checked checbox's id
    	Integer nodeId = Integer.decode(str);
    	HttpSession session = request.getSession();
    	
    	//ConditionNode repesents map of condition given by user in jsp page
    	Map ConditionNode = (HashMap)session.getAttribute(Constants.ADVANCED_CONDITION_NODES_MAP);
    	
    	//Represent conditions of according to id
    	DefaultMutableTreeNode node = (DefaultMutableTreeNode)ConditionNode.get(nodeId);
    	
    	AdvancedConditionsNode advConditionNode = (AdvancedConditionsNode)node.getUserObject();
    	
    	//Represent vector of conditions of paticular row
    	Vector vectorOfCondtions = advConditionNode.getObjectConditions();
    	
    	Condition con = null;
		DataElement dataElement = null;
		Operator op = null;
		
		int eventCounter = 1;
		
		//Fieldname of page
		String column = "";
		
		//Temporary variable for checking kay of map
		String temp ="";
		
		//Temporary variabale for putting value Between & Not between in page
		String tempOperator = "";
		
		//Provides condition of putting value of valueKey in the map
		boolean boolValuekey = false;
		
		//Temporary map for all the valyes of page
		Map map = new HashMap();
		Iterator it = vectorOfCondtions.iterator();
		
		while(it.hasNext())
		{
			con  = (Condition)it.next();
			dataElement = con.getDataElement();
			
			//Used for creating key of field
			String tableName = dataElement.getTableAliasName();
			StringTokenizer tableTokens = new StringTokenizer(tableName,".");
			String superTable = new String();
			if(tableTokens.countTokens()==2)
			{
				tableName = tableTokens.nextToken();
				superTable = tableTokens.nextToken();
			}
			
	        op = con.getOperator();
	        column = dataElement.getField();
	        String valuekey = "";
	       
	        //Condition for checking date values as operator keys are same
	       if(temp.equals(tableName+":"+column))
	        	{
	        	//Key for value field 
		        valuekey = tableName+":"+column + ":HLIMIT";
		        boolValuekey = true;
		               	
	        }
	        else 
	        {
	        	//Key for value field 
		        valuekey = tableName+":"+column;
		        boolValuekey = false;
		    }
	        
	        //Key for value field ie 3rd column
			String opKey = "Operator:" + tableName+":"+column;
			
			Logger.out.debug("opKey--"+opKey);
			Logger.out.debug("valuekey--"+valuekey);
			//Setting value of map in edit case
			map.put(valuekey,con.getValue());
			
			if(boolValuekey)
			{
//				Explicitly setting operator keys value as they are same
				if(tempOperator.equals(Operator.GREATER_THAN_OR_EQUALS))
					map.put(opKey,"Between");
				else
					map.put(opKey,"Not Between");
				temp = "";
			}
			else
			{
				map.put(opKey,op.getOperator());
				tempOperator = op.getOperator();
				temp = valuekey;
			}
			if(tableName.indexOf("Parameter")>0)
			{
				setEventParameterMap(aForm,tableName,column,op.getOperator(),con.getValue(),eventCounter,superTable);
				eventCounter++;
			}
			
		}
		Logger.out.debug("map in advSearchUIAction----******-->"+map);
		//Setting map in form
		aForm.setValues(map);
		
		
    }
	
	
	/**
	 * Used for setting value of map of form in Edit case
	 * @param searchFieldData Instance of class for setting field's value
	 * @param map Map from form containing keys of field
	 */
	
	protected void setIsDisableValue(SearchFieldData[] searchFieldData,Map map)
	{
		for(int i = 0; i < searchFieldData.length ; i++)
		{
			//Represents name of field i.e value(...)
			String temp = searchFieldData[i].getValueField().getName();
			
			//Removing brackets from valueField's name 
			String name = temp.substring(temp.indexOf("(")+1,temp.indexOf(")"));
			
			//Enabling or disabling the field according condition whether map contains value or not
			Object element = map.get(name);
			if( (element == null) || (  element.equals("Any")  ) )
			{
				searchFieldData[i].getValueField().setDisabled(true) ;
			}
			else
			{
				searchFieldData[i].getValueField().setDisabled(false) ;
			}
		}
	}
	
	protected void setEventParameterMap(AdvanceSearchForm aForm,String tableName,String column,String operatorVal,String value,int counter,String superTable) throws Exception
	{
//		Map map = new HashMap();
//		Map eventParametersMap = (Map)aForm.getEventValues();
//		Logger.out.debug("eventParametersMap in advSUI****-->"+eventParametersMap);
//		for(int i=1;i<=counter;i++)
//		{
			QueryBizLogic bizlogic = new QueryBizLogic();
			String eventClassName = "EventName_" + counter;
			String eventColumn = "EventColumnName_" + counter;
			String eventOperator = "EventColumnOperator_" + counter;
			String eventValue = "EventColumnValue_" + counter;
			String dataType = new String();
			if(superTable.equals(""))
				dataType = bizlogic.getAttributeType(column,tableName);
			else
				dataType = bizlogic.getAttributeType(column,superTable);
			Logger.out.debug("Key for event map"+tableName+"."+column+"."+dataType);
			if(superTable.equals(""))
				aForm.setEventMap(eventColumn,tableName+"."+column+"."+dataType);//bizlogic.getColumnDisplayNames(tableName,column
			else
				aForm.setEventMap(eventColumn,superTable+"."+column+"."+dataType);
			aForm.setEventMap(eventClassName,tableName);
			aForm.setEventMap(eventOperator,operatorVal);
			aForm.setEventMap(eventValue,value);
		//}
	}

}