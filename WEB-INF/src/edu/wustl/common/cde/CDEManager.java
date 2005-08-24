/*
 * Created on Jul 26, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package edu.wustl.common.cde;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import edu.wustl.catissuecore.util.global.Variables;
import edu.wustl.common.cde.xml.XMLCDE;
import edu.wustl.common.cde.xml.XMLCDECACHE;
import edu.wustl.common.util.logger.Logger;

/**@author kapil_kaveeshwar
 *  This is a communication link between the caTISSUE Core Application and the caDSR interface. */
public class CDEManager 
{
	public static CDEManager cdeManager = new CDEManager();
	private Map cdeXMLMAP; 
	private CDEHandler cdeHandler;
	public CDEManager()
	{
		cdeXMLMAP = new HashMap();
		
		try
		{
            // create a JAXBContext capable of handling classes generated into
            // the pspl.cde package
            JAXBContext jc = JAXBContext.newInstance( "edu.wustl.common.cde.xml" );
            
            // create an Unmarshaller
            Unmarshaller u = jc.createUnmarshaller();
            
            // unmarshal a root instance document into a tree of Java content
            // objects composed of classes from the pspl.cde package.
            XMLCDECACHE root = 
                (XMLCDECACHE)u.unmarshal( new FileInputStream( "CDEConfig.xml" ) );
                
            // display the cde details
			List xmlCDEList = root.getXMLCDE();
			Iterator iterator = xmlCDEList.iterator();
			while(iterator.hasNext())
			{
				XMLCDE cdeobj = (XMLCDE)iterator.next();
				cdeXMLMAP.put(cdeobj.getName(),cdeobj);
			}
        }
		catch( JAXBException je )
		{
            je.printStackTrace();
        }
		catch( IOException ioe )
		{
            ioe.printStackTrace();
        }
		cdeHandler = new CDEHandler(cdeXMLMAP);
	}
	
	/** Retrieves live CDEs from the caDSR and updates the database cache. */
	public void refreshCache()
	{
		CDECacheManager cdeCacheManager = new CDECacheManager();
		cdeCacheManager.refresh(cdeXMLMAP);
	}
	
	/**
	 * Populates the �in-memory cache� from the �database cache�. The loading
	 * operation will be performed at application startup time. For faster startup
	 * time of the application, all CDE�s can be configured for lazy loading at
	 * the application level as well as individual CDE level. In lazy loading,
	 * required CDE�s will be loaded in-memory in first call to that CDE.
	 */
	public void loadCDEInMemory()
	{
		cdeHandler.loadCDE();
	 }
	
	/** 
	 * Returns CDE for given CDE Name. 
	 * */
	public CDE getCDE(String CDEName)
	{
		return cdeHandler.retrieve(CDEName);
	}
	
	public static void main(String[] args)
	{
		Variables.catissueHome = System.getProperty("user.dir");
		Logger.configure("Application.properties");
		
		CDEManager aCDEManager = new CDEManager();
		aCDEManager.refreshCache();
	}
}