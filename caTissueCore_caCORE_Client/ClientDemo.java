import edu.wustl.catissuecore.domain.Biohazard;
import edu.wustl.catissuecore.domain.CancerResearchGroup;
import edu.wustl.catissuecore.domain.CollectionProtocol;
import edu.wustl.catissuecore.domain.CollectionProtocolEvent;
import edu.wustl.catissuecore.domain.CollectionProtocolRegistration;
import edu.wustl.catissuecore.domain.Department;
import edu.wustl.catissuecore.domain.Distribution;
import edu.wustl.catissuecore.domain.DistributionProtocol;
import edu.wustl.catissuecore.domain.Institution;
import edu.wustl.catissuecore.domain.Participant;
import edu.wustl.catissuecore.domain.Site;
import edu.wustl.catissuecore.domain.Specimen;
import edu.wustl.catissuecore.domain.SpecimenArray;
import edu.wustl.catissuecore.domain.SpecimenArrayType;
import edu.wustl.catissuecore.domain.SpecimenCharacteristics;
import edu.wustl.catissuecore.domain.SpecimenCollectionGroup;
import edu.wustl.catissuecore.domain.SpecimenRequirement;
import edu.wustl.catissuecore.domain.StorageContainer;
import edu.wustl.catissuecore.domain.StorageType;
import edu.wustl.catissuecore.domain.User;
import edu.wustl.common.util.logger.Logger;
import gov.nih.nci.system.applicationservice.ApplicationService;
import gov.nih.nci.system.applicationservice.ApplicationServiceProvider;
import gov.nih.nci.system.comm.client.ClientSession;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/*
import edu.wustl.common.util.Utility;
import edu.wustl.common.util.XMLPropertyHandler;
import edu.wustl.common.util.global.ApplicationProperties;
import edu.wustl.common.util.global.Constants;
import edu.wustl.common.util.global.Variables;
import org.apache.log4j.PropertyConfigurator;
*/
/**
 * <!-- LICENSE_TEXT_START -->
* Copyright 2001-2004 SAIC. Copyright 2001-2003 SAIC. This software was developed in conjunction with the National Cancer Institute,
* and so to the extent government employees are co-authors, any rights in such works shall be subject to Title 17 of the United States Code, section 105.
* Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
* 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the disclaimer of Article 3, below. Redistributions
* in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other
* materials provided with the distribution.
* 2. The end-user documentation included with the redistribution, if any, must include the following acknowledgment:
* "This product includes software developed by the SAIC and the National Cancer Institute."
* If no such end-user documentation is to be included, this acknowledgment shall appear in the software itself,
* wherever such third-party acknowledgments normally appear.
* 3. The names "The National Cancer Institute", "NCI" and "SAIC" must not be used to endorse or promote products derived from this software.
* 4. This license does not authorize the incorporation of this software into any third party proprietary programs. This license does not authorize
* the recipient to use any trademarks owned by either NCI or SAIC-Frederick.
* 5. THIS SOFTWARE IS PROVIDED "AS IS," AND ANY EXPRESSED OR IMPLIED WARRANTIES, (INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
* MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE) ARE DISCLAIMED. IN NO EVENT SHALL THE NATIONAL CANCER INSTITUTE,
* SAIC, OR THEIR AFFILIATES BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
* PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
* WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * <!-- LICENSE_TEXT_END -->
 */

/**
 * @author caBIO Team
 * @version 1.0
 */



/**
	* ClientDemo.java demonstartes various ways to execute searches with and without
      * using Application Service Layer (convenience layer that abstracts building criteria
      * Uncomment different scenarios below to demonstrate the various types of searches
*/

public class ClientDemo 
{
		static ApplicationService appService = null;
		static APIDemo api = new APIDemo();
		public static Map dataModelObjectMap = new HashMap();
		private static ReportWriter reportWriter = null;
		private static int totalOperations;
		private static int successfullOperations;
		private static int failureOperations;
		private static String tabSpacing = "\t\t\t";
		private static String newLine = "\n";
		private String separator = ",";
		private static StringBuffer reportContents = null;
		private String insertOperation = " insert ";
		private String insertValidateOperation = " validation at insertion time ";
		private String updateOperation = " update ";
		private String updateValidateOperation = " validation at updation time ";
		private String searchOperation = " serach ";
		private String successMessage = " pass ";
		private String failureMessage = " fail ";
		private static String filePath = "";
		
    public static void main(String[] args) 
	{
	
		System.out.println("*** ClientDemo...");
		try
		{
			//ApplicationServiceProvider applicationServiceProvider = new ApplicationServiceProvider(); 
			appService = ApplicationServiceProvider.getApplicationService();
			ClientSession cs = ClientSession.getInstance();
			try
			{ 
				cs.startSession("admin@admin.com", "Login123");
			} 
			catch (Exception ex) 
			{ 
				System.out.println(ex.getMessage()); 
				ex.printStackTrace();
				return;
			}

			try 
			{
				ClientDemo testClient = new ClientDemo();
				reportWriter = ReportWriter.getInstance();
				String dirFullPath = reportWriter.getDirPath(); 
				reportWriter.createDir(dirFullPath);
				filePath = reportWriter.getFileName(dirFullPath);
				reportWriter.createFile(filePath);
				writeHeaderContentsToReport();
				reportContents = new StringBuffer();
				testClient.createObjects();
				testClient.serachObject();
				testClient.updateObjects();
				writeFooterContentsToReport();
				reportWriter.closeFile();
				//testClient.sendMail();
/*				testClient.testAddInstitution();
				testClient.testAddDepartment();
				testClient.testAddCancerResearchGroup();
				testClient.testAddBioHazard();
				testClient.testAddUser();
				
*/			}
			catch (RuntimeException e2) 
			{
				e2.printStackTrace();
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println("Test client throws Exception = "+ ex);
		}
	}
    //////////////////////// Start Add operation //////
	private void createObjects()
	{
	try
	{
		   // Add sub domain objects	
	 	   Object obj = api.initSpecimenCharacteristics();
	 	   SpecimenCharacteristics specimenCharacteristics = (SpecimenCharacteristics) appService.createObject(obj);
	 	   dataModelObjectMap.put("SpecimenCharacteristics",specimenCharacteristics);
	 	   
	 	   obj = api.initSpecimenRequirement();
	 	   SpecimenRequirement specimenRequirement = (SpecimenRequirement) appService.createObject(obj);
	 	   dataModelObjectMap.put("SpecimenRequirement",specimenRequirement);
	
	 	   obj = api.initCollectionProtocolEvent();
	 	   CollectionProtocolEvent collectionProtocolEvent = (CollectionProtocolEvent) appService.createObject(obj);
	 	   dataModelObjectMap.put("CollectionProtocolEvent",collectionProtocolEvent);
	 	    
			testAddInstitution();
			testAddDepartment();			
			testAddCancerResearchGroup();
			testAddUser();
			testAddSite();
			testAddBioHazard();
			testAddCollectionProtocol();
			testAddDistributionProtocol();
			testAddParticipant();
			testAddCollectionProtocolRegistration();
			testAddSpecimenCollectionGroup();
			testAddStorageType();
			testAddStorageContainer();
			testAddSpecimenArrayType();
			testAddSpecimen();
			testAddSpecimenArray();
			testAddDistribution();
			
			
			testAddInstitutionWithWrongData();
			testAddDepartmentWithWrongData();
			testAddCancerResearchGroupWithWrongData();
			testAddUserWithWrongData();
			testAddSiteWithWrongData();
			testAddBioHazardWithWrongData();
			testAddCollectionProtocolWithWrongData();				
			testAddDistributionProtocolWithWrongData();			
			testAddParticipantWithWrongData();			
			testAddCollectionProtocolRegistrationWithWrongData();			
			testAddSpecimenCollectionGroupWithWrongData();			
			//testAddStorageTypeForNullObject();
			testAddStorageTypeForNoName();
			testAddStorageTypeForNoOneDimensionLabel();				
			//testAddStorageContainerForNullObject();
			testAddStorageContainerWithNoName();
			testAddSpecimenArrayTypeWithWrongData();
			testAddSpecimenWithWrongData();		
			testAddSpecimenArrayWithWrongData();
			testAddDistributionWithWrongData();
			
								
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println("Test client throws Exception = "+ ex);
		}
	}
    
	private void testAddDepartment()
	{
		try
		{
			Department departmentObj = (Department) api.initDepartment();
	    	setLogger(departmentObj);
	    	departmentObj =  (Department) appService.createObject(departmentObj);
	    	dataModelObjectMap.put("Department",departmentObj);
	    	writeSuccessfullOperationToReport(departmentObj,insertOperation);
			Logger.out.info(" Domain Object is successfully added ----> Name:: " + departmentObj.getName());
		//+ departmentObj.getId().longValue() + " ::  Name :: " + departmentObj.getName());
		}
		catch(Exception e)
		{
			writeFailureOperationsToReport("Department",insertOperation);
			Logger.out.error(e.getMessage(),e);
			e.printStackTrace();
		}
	}
	
	private void testAddDepartmentWithWrongData()
	{
		Department departmentObj = null;
		try
		{
			departmentObj = (Department) api.initDepartment();
			departmentObj.setName(null);
	    	setLogger(departmentObj);
	    	departmentObj =  (Department) appService.createObject(departmentObj);
	    	dataModelObjectMap.put("Department",departmentObj);
	    	writeFailureOperationsToReport("Department",insertValidateOperation);
			//Logger.out.info(" Domain Object is successfully added ----> Name:: " + departmentObj.getName());		
		}
		catch(Exception e)
		{
			writeSuccessfullOperationToReport(departmentObj,insertValidateOperation);
			Logger.out.error(e.getMessage(),e);
			e.printStackTrace();
		}
	}
	
	private void testAddBioHazard()
	{
		try
		{
			Biohazard bioHazardObj = (Biohazard) api.initBioHazard();
	    	setLogger(bioHazardObj);
	    	bioHazardObj =  (Biohazard) appService.createObject(bioHazardObj);
	    	System.out.println("-----------------" + bioHazardObj);
	    	dataModelObjectMap.put("Biohazard",bioHazardObj);
	    	writeSuccessfullOperationToReport(bioHazardObj,insertOperation);
			Logger.out.info(" Domain Object is successfully added ---->  Name:: " + bioHazardObj.getName());
		//+ bioHazardObj.getId().longValue() + " ::  Name :: " + bioHazardObj.getName());
		}
		catch(Exception e)
		{
			writeFailureOperationsToReport("Biohazard",insertOperation);
			Logger.out.error(e.getMessage(),e);
			e.printStackTrace();
		}
	}
	
	/*private void testAddBioHazardWithWrongData()
	{
		try
		{
			Biohazard bioHazardObj = (Biohazard) api.initBioHazard();
			bioHazardObj.setName(null);
	    	setLogger(bioHazardObj);
	    	bioHazardObj =  (Biohazard) appService.createObject(bioHazardObj);
	    	System.out.println("-----------------" + bioHazardObj);
	    	dataModelObjectMap.put("Biohazard",bioHazardObj);
			Logger.out.info(" Domain Object is successfully added ---->  Name:: " + bioHazardObj.getName());
		//+ bioHazardObj.getId().longValue() + " ::  Name :: " + bioHazardObj.getName());
		}
		catch(Exception e)
		{
			Logger.out.error(e.getMessage(),e);
			e.printStackTrace();
		}
	}*/
	
	private void testAddBioHazardWithWrongData()
	{
		Biohazard bioHazardObj = null;
		try
		{
			bioHazardObj = (Biohazard) api.initBioHazard();
			bioHazardObj.setType("abc");
	    	setLogger(bioHazardObj);
	    	bioHazardObj =  (Biohazard) appService.createObject(bioHazardObj);
	    	dataModelObjectMap.put("Biohazard",bioHazardObj);
	    	writeFailureOperationsToReport("Biohazard",insertValidateOperation);
			//Logger.out.info(" Domain Object is successfully added ---->  Name:: " + bioHazardObj.getName());
		//+ bioHazardObj.getId().longValue() + " ::  Name :: " + bioHazardObj.getName());
		}
		catch(Exception e)
		{
			writeSuccessfullOperationToReport(bioHazardObj,insertValidateOperation);
			Logger.out.error(e.getMessage(),e);
			e.printStackTrace();
		}
	}
	
	
	private void testAddUser()
	{
		try
		{
			User userObj = (User) api.initAdminUser();
	    	setLogger(userObj);
			userObj =  (User) appService.createObject(userObj);
			dataModelObjectMap.put("User",userObj);
			writeSuccessfullOperationToReport(userObj,insertOperation);
			Logger.out.info(" Domain Object is successfully added ---->  LoginName:: " + userObj.getId().longValue() + " ::  Name :: " + userObj.getFirstName());
			
			User userObj1 = (User) api.initAdminUser();
			setLogger(userObj1);
			userObj1 =  (User) appService.createObject(userObj1);
			dataModelObjectMap.put("User1",userObj1);
			Logger.out.info(" Domain Object is successfully added ---->  LoginName:: " + userObj1.getId().longValue() + " ::  Name :: " + userObj1.getFirstName());
		
		}
		catch(Exception e)
		{
			writeFailureOperationsToReport("User",insertOperation);
			Logger.out.error(e.getMessage(),e);
			e.printStackTrace();
		}
	}
	
	private void testAddUserWithWrongData()
	{
		User userObj = null;
		try
		{
			userObj = (User) api.initUser();
			userObj.setEmailAddress(null);
	    	setLogger(userObj);
			userObj =  (User) appService.createObject(userObj);
			writeFailureOperationsToReport("User",insertValidateOperation);
			//dataModelObjectMap.put("User",userObj);
			//Logger.out.info(" Domain Object is successfully added ---->  LoginName:: " + userObj.getId().longValue() + " ::  Name :: " + userObj.getFirstName());
		}
		catch(Exception e)
		{
			writeSuccessfullOperationToReport(userObj,insertValidateOperation);
			Logger.out.error(e.getMessage(),e);
			e.printStackTrace();
		}
	}
	
	private void testAddParticipant()
	{
		try
		{
			Participant participantObj = (Participant) api.initParticipant();
			setLogger(participantObj);
			participantObj =  (Participant) appService.createObject(participantObj);
			dataModelObjectMap.put("Participant",participantObj);
			writeSuccessfullOperationToReport(participantObj,insertOperation);
			Logger.out.info(" Domain Object is successfully added ---->  ID:: " + participantObj.getId().longValue());
		}
		catch(Exception e)
		{
			writeFailureOperationsToReport("Participant",insertOperation);
			Logger.out.error(e.getMessage(),e);
			e.printStackTrace();
		}
	}
	
	private void testAddParticipantWithWrongData()
	{
		Participant participantObj = null;
		try
		{
			participantObj = (Participant) api.initParticipant();
			setLogger(participantObj);
			participantObj.setGender("wrong");
			participantObj =  (Participant) appService.createObject(participantObj);
			writeFailureOperationsToReport("Participant",insertValidateOperation);
			//dataModelObjectMap.put("Participant",participantObj);
			//Logger.out.info(" Domain Object is successfully added ---->  ID:: " + participantObj.getId().longValue());
		}
		catch(Exception e)
		{
			writeSuccessfullOperationToReport(participantObj,insertValidateOperation);
			Logger.out.error(e.getMessage(),e);
			e.printStackTrace();
		}
	}

	
	private void testAddInstitution()
	{
		try
		{
			Institution institutionObj = (Institution) api.initInstitution();			
	    	setLogger(institutionObj);
	    	Logger.out.info("Inserting domain object------->"+institutionObj);
	    	institutionObj =  (Institution) appService.createObject(institutionObj);
	    	dataModelObjectMap.put("Institution",institutionObj);
			Logger.out.info(" Domain Object is successfully added ---->  Name:: " + institutionObj.getName());
			writeSuccessfullOperationToReport(institutionObj,insertOperation);
			//+ institutionObj.getId().longValue() + " ::  Name :: " + institutionObj.getName());
		}
		catch(Exception e)
		{
			writeFailureOperationsToReport("Institution",insertOperation);
			Logger.out.error(e.getMessage(),e);
			e.printStackTrace();
		}
	}
	
	private void testAddInstitutionWithWrongData()
	{
		Institution institutionObj = null;
		try
		{			
			institutionObj = (Institution) api.initInstitution();	
			institutionObj.setName(null);
	    	setLogger(institutionObj);
	    	Logger.out.info("Inserting domain object------->"+institutionObj);
	    	institutionObj =  (Institution) appService.createObject(institutionObj);
	    	writeFailureOperationsToReport("Institution",insertValidateOperation);	    	
	    	//dataModelObjectMap.put("Institution",institutionObj);
			//Logger.out.info(" Domain Object is successfully added ---->  Name:: " + institutionObj.getName());
			
			//+ institutionObj.getId().longValue() + " ::  Name :: " + institutionObj.getName());
		}
		catch(Exception e)
		{
			writeSuccessfullOperationToReport(institutionObj,insertValidateOperation);
			Logger.out.error(e.getMessage(),e);
			e.printStackTrace();
		}
	}
	
	private void testAddCancerResearchGroup()
	{
		try
		{
			CancerResearchGroup cancerResearchGroupObj = (CancerResearchGroup) api.initCancerResearchGroup();
	    	setLogger(cancerResearchGroupObj);
	    	Logger.out.info("Inserting domain object------->"+cancerResearchGroupObj);
	    	cancerResearchGroupObj =  (CancerResearchGroup) appService.createObject(cancerResearchGroupObj);
	    	dataModelObjectMap.put("CancerResearchGroup",cancerResearchGroupObj);
	    	writeSuccessfullOperationToReport(cancerResearchGroupObj,insertOperation);
			Logger.out.info(" Domain Object is successfully added ---->  Name:: " + cancerResearchGroupObj.getName());
		//+ cancerResearchGroupObj.getId().longValue() + " ::  Name :: " + cancerResearchGroupObj.getName());
		}
		catch(Exception e)
		{
			//writeFailureOperationsToReport("Ca");
			writeFailureOperationsToReport("CancerResearchGroup",insertOperation);
			Logger.out.error(e.getMessage(),e);
			e.printStackTrace();
		}
	}
	
	private void testAddCancerResearchGroupWithWrongData()
	{
		CancerResearchGroup cancerResearchGroupObj = null;
		try
		{
			cancerResearchGroupObj = (CancerResearchGroup) api.initCancerResearchGroup();
			cancerResearchGroupObj.setName(null);
	    	setLogger(cancerResearchGroupObj);
	    	Logger.out.info("Inserting domain object------->"+cancerResearchGroupObj);
	    	cancerResearchGroupObj =  (CancerResearchGroup) appService.createObject(cancerResearchGroupObj);	    	
	    	writeFailureOperationsToReport("CancerResearchGroup",insertValidateOperation);
	    	//dataModelObjectMap.put("CancerResearchGroup",cancerResearchGroupObj);
			//Logger.out.info(" Domain Object is successfully added ---->  Name:: " + cancerResearchGroupObj.getName());		
		}
		catch(Exception e)
		{
			writeSuccessfullOperationToReport(cancerResearchGroupObj,insertValidateOperation);
			Logger.out.error(e.getMessage(),e);
			e.printStackTrace();
		}
	}
	
	private void testAddSite()
	{
		try
		{
			Site siteObj = (Site) api.initSite();
	    	setLogger(siteObj);
	    	Logger.out.info("Inserting domain object------->"+siteObj);
			siteObj =  (Site) appService.createObject(siteObj);
			dataModelObjectMap.put("Site",siteObj);
			writeSuccessfullOperationToReport(siteObj,insertOperation);
			Logger.out.info(" Domain Object is successfully added ----> ID:: " + siteObj.getId().toString());
		//+ siteObj.getId().longValue() + " ::  Name :: " + siteObj.getName());
		}
		catch(Exception e)
		{
			writeFailureOperationsToReport("Site",insertOperation);
			Logger.out.error(e.getMessage(),e);
			e.printStackTrace();
		}
	}
	
	private void testAddSiteWithWrongData()
	{
		Site siteObj = null;
		try
		{
			siteObj = (Site) api.initSite();
			siteObj.setName(null);
	    	setLogger(siteObj);
	    	Logger.out.info("Inserting domain object------->"+siteObj);
			siteObj =  (Site) appService.createObject(siteObj);
			writeFailureOperationsToReport("Site",insertValidateOperation);
			//dataModelObjectMap.put("Site",siteObj);
			//Logger.out.info(" Domain Object is successfully added ----> ID:: " + siteObj.getId().toString());		
		}
		catch(Exception e)
		{
			writeSuccessfullOperationToReport(siteObj,insertValidateOperation);
			Logger.out.error(e.getMessage(),e);
			e.printStackTrace();
		}
	}
	
	private void testAddDistribution()
	{
		try
		{			
			Distribution distributionObj = (Distribution)api.initDistribution();
	    	setLogger(distributionObj);
	    	Logger.out.info("Inserting domain object------->"+distributionObj);
			distributionObj =  (Distribution) appService.createObject(distributionObj);
			dataModelObjectMap.put("Distribution",distributionObj);
			writeSuccessfullOperationToReport(distributionObj,insertOperation);
			Logger.out.info(" Domain Object is successfully added ---->  ID:: " + distributionObj.getId().toString());
		//+ distributionObj.getId().longValue() + " ::  Name :: " + distributionObj.getName());
		}
		catch(Exception e)
		{
			writeFailureOperationsToReport("Distribution",insertOperation);
			Logger.out.error(e.getMessage(),e);
			e.printStackTrace();
		}
	}
	
	private void testAddDistributionWithWrongData()
	{
		Distribution distributionObj = null;
		try
		{			
			distributionObj = (Distribution)api.initDistribution();
	    	setLogger(distributionObj);
	    	Logger.out.info("Inserting domain object------->"+distributionObj);
	    	distributionObj.setDistributionProtocol(null);
	    	distributionObj.setSpecimenArrayCollection(null);
	    	distributionObj.setDistributedItemCollection(null);
	    	
			distributionObj =  (Distribution) appService.createObject(distributionObj);
			writeFailureOperationsToReport("Distribution",insertValidateOperation);
			//dataModelObjectMap.put("Distribution",distributionObj);
			//Logger.out.info(" Domain Object is successfully added ---->  ID:: " + distributionObj.getId().toString());
		//+ distributionObj.getId().longValue() + " ::  Name :: " + distributionObj.getName());
		}
		catch(Exception e)
		{
			writeSuccessfullOperationToReport(distributionObj,insertValidateOperation);
			Logger.out.error(e.getMessage(),e);
			e.printStackTrace();
		}
	}
	
	
	private void testAddDistributionProtocol()
	{
		try
		{
			DistributionProtocol distributionProtocolObj =(DistributionProtocol)api.initDistributionProtocol();
	    	setLogger(distributionProtocolObj);
	    	Logger.out.info("Inserting domain object------->"+distributionProtocolObj);
			distributionProtocolObj =  (DistributionProtocol) appService.createObject(distributionProtocolObj);
			dataModelObjectMap.put("DistributionProtocol",distributionProtocolObj);
			writeSuccessfullOperationToReport(distributionProtocolObj,insertOperation);
			Logger.out.info(" Domain Object is successfully added ----> ID:: " + distributionProtocolObj.getId().toString());
		//+ distributionProtocolObj.getId().longValue() + " ::  Name :: " + distributionProtocolObj.getName());
		}
		catch(Exception e)
		{
			writeFailureOperationsToReport("DistributionProtocol",insertOperation);
			Logger.out.error(e.getMessage(),e);
			e.printStackTrace();
		}
	}
	
	private void testAddDistributionProtocolWithWrongData()
	{
		DistributionProtocol distributionProtocolObj = null;
		try
		{
			distributionProtocolObj =(DistributionProtocol)api.initDistributionProtocol();
	    	setLogger(distributionProtocolObj);
	    	Logger.out.info("Inserting domain object------->"+distributionProtocolObj);
	    	distributionProtocolObj.setShortTitle(null);
			distributionProtocolObj =  (DistributionProtocol) appService.createObject(distributionProtocolObj);
			writeFailureOperationsToReport("DistributionProtocol",insertValidateOperation);
			//dataModelObjectMap.put("DistributionProtocol",distributionProtocolObj);
			//Logger.out.info(" Domain Object is successfully added ----> ID:: " + distributionProtocolObj.getId().toString());
		//+ distributionProtocolObj.getId().longValue() + " ::  Name :: " + distributionProtocolObj.getName());
		}
		catch(Exception e)
		{
			writeSuccessfullOperationToReport(distributionProtocolObj,insertValidateOperation);
			Logger.out.error(e.getMessage(),e);
			e.printStackTrace();
		}
	}
	private void testAddCollectionProtocol()
	{
		try
		{
			CollectionProtocol collectionProtocolObj = (CollectionProtocol)api.initCollectionProtocol();
			setLogger(collectionProtocolObj);
			Logger.out.info("Inserting domain object------->"+collectionProtocolObj);
			collectionProtocolObj =  (CollectionProtocol) appService.createObject(collectionProtocolObj);
			dataModelObjectMap.put("CollectionProtocol",collectionProtocolObj);
			writeSuccessfullOperationToReport(collectionProtocolObj,insertOperation);
			Logger.out.info(" Domain Object is successfully added ---->  ID:: " + collectionProtocolObj.getId().toString());
		//+ collectionProtocolObj.getId().longValue() + " ::  Name :: " + collectionProtocolObj.getName());
		}
		catch(Exception e)
		{
			writeFailureOperationsToReport("CollectionProtocol",insertOperation);
			Logger.out.error(e.getMessage(),e);
			e.printStackTrace();
		}
	}
	
	private void testAddCollectionProtocolWithWrongData()
	{
		CollectionProtocol collectionProtocolObj = null;
		try
		{
			collectionProtocolObj = (CollectionProtocol)api.initCollectionProtocol();
			collectionProtocolObj.setTitle(null);
			setLogger(collectionProtocolObj);
			Logger.out.info("Inserting domain object------->"+collectionProtocolObj);
			collectionProtocolObj =  (CollectionProtocol) appService.createObject(collectionProtocolObj);
			writeFailureOperationsToReport("CollectionProtocol",insertValidateOperation);
			//dataModelObjectMap.put("CollectionProtocol",collectionProtocolObj);
			//Logger.out.info(" Domain Object is successfully added ---->  ID:: " + collectionProtocolObj.getId().toString());
		//+ collectionProtocolObj.getId().longValue() + " ::  Name :: " + collectionProtocolObj.getName());
		}
		catch(Exception e)
		{
			writeSuccessfullOperationToReport(collectionProtocolObj,insertValidateOperation);
			Logger.out.error(e.getMessage(),e);
			e.printStackTrace();
		}
	}
	private void testAddCollectionProtocolRegistration()
	{
		try
		{
		//	System.out.println("ClientDemo....................");
			CollectionProtocolRegistration collectionProtocolRegistrationObj =(CollectionProtocolRegistration) api.initCollectionProtocolRegistration();
	    	setLogger(collectionProtocolRegistrationObj);
	    	Logger.out.info("Inserting domain object------->"+collectionProtocolRegistrationObj);
			collectionProtocolRegistrationObj =  (CollectionProtocolRegistration) appService.createObject(collectionProtocolRegistrationObj);
			dataModelObjectMap.put("CollectionProtocolRegistration",collectionProtocolRegistrationObj);
			writeSuccessfullOperationToReport(collectionProtocolRegistrationObj,insertOperation);
			Logger.out.info(" Domain Object is successfully added ---->   ID:: " + collectionProtocolRegistrationObj.getId().toString());
		//+ collectionProtocolRegistrationObj.getId().longValue() + " ::  Name :: " + collectionProtocolRegistrationObj.getName());
		}
		catch(Exception e)
		{
			writeFailureOperationsToReport("CollectionProtocolRegistration",insertOperation);
			Logger.out.error(e.getMessage(),e);
			e.printStackTrace();
		}
	}
	
	private void testAddCollectionProtocolRegistrationWithWrongData()
	{
		CollectionProtocolRegistration collectionProtocolRegistrationObj = null;
		try
		{
		//	System.out.println("ClientDemo....................");
			collectionProtocolRegistrationObj =(CollectionProtocolRegistration) api.initCollectionProtocolRegistration();
	    	setLogger(collectionProtocolRegistrationObj);
	    	Logger.out.info("Inserting domain object------->"+collectionProtocolRegistrationObj);
	    	collectionProtocolRegistrationObj.setProtocolParticipantIdentifier(null);
	    	collectionProtocolRegistrationObj.setParticipant(null);
			collectionProtocolRegistrationObj =  (CollectionProtocolRegistration) appService.createObject(collectionProtocolRegistrationObj);
			writeFailureOperationsToReport("CollectionProtocolRegistration",insertValidateOperation);
			//dataModelObjectMap.put("CollectionProtocolRegistration",collectionProtocolRegistrationObj);
			//Logger.out.info(" Domain Object is successfully added ---->   ID:: " + collectionProtocolRegistrationObj.getId().toString());
		//+ collectionProtocolRegistrationObj.getId().longValue() + " ::  Name :: " + collectionProtocolRegistrationObj.getName());
		}
		catch(Exception e)
		{
			writeSuccessfullOperationToReport(collectionProtocolRegistrationObj,insertValidateOperation);
			Logger.out.error(e.getMessage(),e);
			e.printStackTrace();
		}
	}
		
	private void testAddStorageType()
	{
		try
		{
			StorageType storageTypeObj =(StorageType) api.initStorageType();
	    	setLogger(storageTypeObj);
	    	Logger.out.info("Inserting domain object------->"+storageTypeObj);
			storageTypeObj =  (StorageType) appService.createObject(storageTypeObj);
			dataModelObjectMap.put("StorageType",storageTypeObj);
			writeSuccessfullOperationToReport(storageTypeObj,insertOperation);
			Logger.out.info(" Domain Object is successfully added ---->  ID:: " + storageTypeObj.getId().toString());
		//+ storageTypeObj.getId().longValue() + " ::  Name :: " + storageTypeObj.getName());
		}
		catch(Exception e)
		{
			writeFailureOperationsToReport("StorageType",insertOperation);
			Logger.out.error(e.getMessage(),e);
			e.printStackTrace();
		}
	}

//	private void testAddStorageTypeForNullObject()
//	{
//		StorageType storageTypeObj = null;
//		try
//		{
//			storageTypeObj =(StorageType) api.initStorageType();
//			setLogger(storageTypeObj);
//			Logger.out.info("Inserting domain object**************------->"+storageTypeObj);
//	    	storageTypeObj = null;
//	    	//storageTypeObj.setName("");
//	    	//storageTypeObj.setOneDimensionLabel("");
//			storageTypeObj =  (StorageType) appService.createObject(storageTypeObj);
//			writeFailureOperationsToReport("StorageType",insertValidateOperation);
//			//dataModelObjectMap.put("StorageType",storageTypeObj);
//			//Logger.out.info(" Domain Object should not get added when it is null");
//		
//		}
//		catch(Exception e)
//		{
//			writeSuccessfullOperationToReport(storageTypeObj,insertValidateOperation);
//			Logger.out.error(e);
//			e.printStackTrace();
//		}
//	}

	private void testAddStorageTypeForNoName()
	{
		StorageType storageTypeObj = null;
		try
		{
			storageTypeObj =(StorageType) api.initStorageType();
			setLogger(storageTypeObj);
	    	storageTypeObj.setName("");
	    	//storageTypeObj.setOneDimensionLabel("");
			storageTypeObj =  (StorageType) appService.createObject(storageTypeObj);
			writeFailureOperationsToReport("StorageType",insertValidateOperation + "with No Name");
			//dataModelObjectMap.put("StorageType",storageTypeObj);
			//Logger.out.info(" Storage Type should not get added when it's name is empty or null");
		
		}
		catch(Exception e)
		{
			writeSuccessfullOperationToReport(storageTypeObj,insertValidateOperation + "with No Name");
			Logger.out.error(e);
			e.printStackTrace();
		}
	}

	private void testAddStorageTypeForNoOneDimensionLabel()
	{
		StorageType storageTypeObj= null;
		try
		{
			storageTypeObj =(StorageType) api.initStorageType();
			setLogger(storageTypeObj);
	    	storageTypeObj.setOneDimensionLabel("");
			storageTypeObj =  (StorageType) appService.createObject(storageTypeObj);
			writeFailureOperationsToReport("StorageType",insertValidateOperation + "with No One Dimension Label");
			//dataModelObjectMap.put("StorageType",storageTypeObj);
			//Logger.out.info(" Storage Type should not get added when it's One Dimension Label is not given");
		
		}
		catch(Exception e)
		{
			writeSuccessfullOperationToReport(storageTypeObj,insertValidateOperation + "with No One Dimension Label");
			Logger.out.error(e);
			e.printStackTrace();
		}
	}

	private void testAddStorageContainer()
	{
		try
		{
			StorageContainer storageContainerObj =(StorageContainer) api.initStorageContainer();
			setLogger(storageContainerObj);
			Logger.out.info("Type of storagecontainer:"+storageContainerObj.getStorageType());
			storageContainerObj =  (StorageContainer) appService.createObject(storageContainerObj);
			Logger.out.info("After insert domain object Storage Container ******* ------->"+storageContainerObj.getCapacity().getId());
			dataModelObjectMap.put("StorageContainer",storageContainerObj);
			writeSuccessfullOperationToReport(storageContainerObj,insertOperation);
			Logger.out.info(" Domain Object is successfully added ---->    ID:: " + storageContainerObj.getId().toString());
		//+ storageContainerObj.getId().longValue() + " ::  Name :: " + storageContainerObj.getName());
		}
		catch(Exception e)
		{
			writeFailureOperationsToReport("StorageContainer",insertOperation);
			Logger.out.error(e);
			e.printStackTrace();
		}
	}
	
//	private void testAddStorageContainerForNullObject()
//	{
//		StorageContainer storageContainerObj = null;
//		try
//		{		
//			storageContainerObj =  (StorageContainer) appService.createObject(storageContainerObj);
//			setLogger(storageContainerObj);
//			storageContainerObj = null;
//			writeFailureOperationsToReport("StorageContainer",insertValidateOperation);
//			//dataModelObjectMap.put("StorageContainer",storageContainerObj);
//			//Logger.out.info(" Domain Object should not get added when it is null");
//		//+ storageContainerObj.getId().longValue() + " ::  Name :: " + storageContainerObj.getName());
//		}
//		catch(Exception e)
//		{
//			writeSuccessfullOperationToReport(storageContainerObj,insertValidateOperation);
//			Logger.out.error(e);
//			e.printStackTrace();
//		}
//	}
	
	private void testAddStorageContainerWithNoName()
	{
		StorageContainer storageContainerObj = null;
		try
		{
			storageContainerObj =(StorageContainer) api.initStorageContainer();
			setLogger(storageContainerObj);
			storageContainerObj.setName("");
			storageContainerObj =  (StorageContainer) appService.createObject(storageContainerObj);
			writeFailureOperationsToReport("StorageContainer",insertValidateOperation);
			//dataModelObjectMap.put("StorageContainer",storageContainerObj);
			//Logger.out.info(" Storage Container should not get added when it's name is not given");
		//+ storageContainerObj.getId().longValue() + " ::  Name :: " + storageContainerObj.getName());
		}
		catch(Exception e)
		{
			writeSuccessfullOperationToReport(storageContainerObj,insertValidateOperation);
			Logger.out.error(e);
			e.printStackTrace();
		}
	}

	
	 private void testAddSpecimen()
		{
			try
			{
				Specimen specimenObj = (Specimen) api.initSpecimen();				
				setLogger(specimenObj);
				Logger.out.info("Inserting domain object------->"+specimenObj);
				specimenObj =  (Specimen) appService.createObject(specimenObj);
				dataModelObjectMap.put("Specimen",specimenObj);
				writeSuccessfullOperationToReport(specimenObj,insertOperation);
				Logger.out.info(" Domain Object is successfully added ---->    ID:: " + specimenObj.getId().toString());
				Logger.out.info(" Domain Object is successfully added ---->    Name:: " + specimenObj.getLabel());
				//Logger.out.info(" Domain Object is successfully added ---->  ID:: " + specimenObj.getId().longValue() + " ::  Name :: " + specimenObj.getLabel());
			}
			catch(Exception e)
			{
				writeFailureOperationsToReport("Specimen",insertOperation);
				Logger.out.error(e.getMessage(),e);
				e.printStackTrace();
			}
		}	 
	
	 	private void testAddSpecimenWithWrongData()
		{
	 		Specimen specimenObj = null;
			try
			{
				specimenObj = (Specimen) api.initSpecimen();				
				setLogger(specimenObj);
				Logger.out.info("Inserting domain object------->"+specimenObj);				
				specimenObj.setPathologicalStatus("wrongData");
				specimenObj =  (Specimen) appService.createObject(specimenObj);
				writeFailureOperationsToReport("Specimen",insertValidateOperation);				
				//dataModelObjectMap.put("Specimen",specimenObj);
				//Logger.out.info(" Domain Object is successfully added ---->    ID:: " + specimenObj.getId().toString());
				//Logger.out.info(" Domain Object is successfully added ---->  ID:: " + specimenObj.getId().longValue() + " ::  Name :: " + specimenObj.getLabel());
			}
			catch(Exception e)
			{
				writeSuccessfullOperationToReport(specimenObj,insertValidateOperation);
				Logger.out.error(e.getMessage(),e);
				e.printStackTrace();
			}
		}	 
	 
	 
	 private void testAddSpecimenArrayType()
		{
			try
			{
				SpecimenArrayType specimenArrayTypeObj = (SpecimenArrayType) api.initSpecimenArrayType();
				setLogger(specimenArrayTypeObj);
				Logger.out.info("Inserting domain object------->"+specimenArrayTypeObj);
				specimenArrayTypeObj =  (SpecimenArrayType) appService.createObject(specimenArrayTypeObj);
				dataModelObjectMap.put("SpecimenArrayType",specimenArrayTypeObj);
				writeSuccessfullOperationToReport(specimenArrayTypeObj,insertOperation);
				Logger.out.info(" Domain Object is successfully added ---->    ID:: " + specimenArrayTypeObj.getId().toString());
				//Logger.out.info(" Domain Object is successfully added ---->  ID:: " + specimenObj.getId().longValue() + " ::  Name :: " + specimenObj.getLabel());
			}
			catch(Exception e)
			{
				writeFailureOperationsToReport("SpecimenArrayType",insertOperation);
				Logger.out.error(e.getMessage(),e);
				e.printStackTrace();
			}
		}
	 
	private void testAddSpecimenArrayTypeWithWrongData()
	{
		SpecimenArrayType specimenArrayTypeObj = null;
		try
		{
			specimenArrayTypeObj = (SpecimenArrayType) api.initSpecimenArrayType();
			setLogger(specimenArrayTypeObj);
			Logger.out.info("Inserting domain object------->"+specimenArrayTypeObj);
			specimenArrayTypeObj.setSpecimenClass(null);
			specimenArrayTypeObj =  (SpecimenArrayType) appService.createObject(specimenArrayTypeObj);			
			writeFailureOperationsToReport("SpecimenArrayType",insertValidateOperation);
			//dataModelObjectMap.put("SpecimenArrayType",specimenArrayTypeObj);
			//Logger.out.info(" Domain Object is successfully added ---->    ID:: " + specimenArrayTypeObj.getId().toString());
			//Logger.out.info(" Domain Object is successfully added ---->  ID:: " + specimenObj.getId().longValue() + " ::  Name :: " + specimenObj.getLabel());
		}
		catch(Exception e)
		{
			
			writeSuccessfullOperationToReport(specimenArrayTypeObj,insertValidateOperation);
			Logger.out.error(e.getMessage(),e);
			e.printStackTrace();
		}
	}
 
	 private void testAddSpecimenArray()
		{
			try
			{
				SpecimenArray specimenArrayObj = (SpecimenArray) api.initSpecimenArray();
				setLogger(specimenArrayObj);
				Logger.out.info("Inserting domain object------->"+specimenArrayObj);
				specimenArrayObj =  (SpecimenArray) appService.createObject(specimenArrayObj);
				dataModelObjectMap.put("SpecimenArray",specimenArrayObj);
				writeSuccessfullOperationToReport(specimenArrayObj,insertOperation);
				Logger.out.info(" Domain Object is successfully added ---->    ID:: " + specimenArrayObj.getId().toString());
		//		Logger.out.info(" Domain Object is successfully added ---->  ID:: " + specimenArrayObj.getId().longValue() + " ::  Name :: " + specimenArrayObj.getName());
			}
			catch(Exception e)
			{
				writeFailureOperationsToReport("SpecimenArray",insertOperation);
				Logger.out.error(e.getMessage(),e);
				e.printStackTrace();
			}
		}
	 
	 private void testAddSpecimenArrayWithWrongData()
		{
	 	SpecimenArray specimenArrayObj = null;
			try
			{
				specimenArrayObj = (SpecimenArray) api.initSpecimenArray();
				setLogger(specimenArrayObj);
				Logger.out.info("Inserting domain object------->"+specimenArrayObj);
				specimenArrayObj.setPositionDimensionOne(null);
				specimenArrayObj =  (SpecimenArray) appService.createObject(specimenArrayObj);
				writeFailureOperationsToReport("SpecimenArray",insertValidateOperation);
				//dataModelObjectMap.put("SpecimenArray",specimenArrayObj);				
				//Logger.out.info(" Domain Object is successfully added ---->    ID:: " + specimenArrayObj.getId().toString());
		//		Logger.out.info(" Domain Object is successfully added ---->  ID:: " + specimenArrayObj.getId().longValue() + " ::  Name :: " + specimenArrayObj.getName());
			}
			catch(Exception e)
			{				
				writeSuccessfullOperationToReport(specimenArrayObj,insertValidateOperation);				
				Logger.out.error(e.getMessage(),e);
				e.printStackTrace();
			}
		}
	 
	 	private void testAddSpecimenCollectionGroup()
		{
			try
			{
				SpecimenCollectionGroup specimenCollectionGroupObj = (SpecimenCollectionGroup) api.initSpecimenCollectionGroup();
		    	setLogger(specimenCollectionGroupObj);
		    	Logger.out.info("Inserting domain object------->"+specimenCollectionGroupObj);
				specimenCollectionGroupObj =  (SpecimenCollectionGroup) appService.createObject(specimenCollectionGroupObj);
				dataModelObjectMap.put("SpecimenCollectionGroup",specimenCollectionGroupObj);
				writeSuccessfullOperationToReport(specimenCollectionGroupObj,insertOperation);
				Logger.out.info(" Domain Object is successfully added ---->    ID:: " + specimenCollectionGroupObj.getId().toString());
			//+ specimenCollectionGroupObj.getId().longValue() + " ::  Name :: " + specimenCollectionGroupObj.getName());
			}
			catch(Exception e)
			{
				writeFailureOperationsToReport("SpecimenCollectionGroup",insertOperation);
				Logger.out.error(e.getMessage(),e);
				e.printStackTrace();
			}
		}
	 	
	 	private void testAddSpecimenCollectionGroupWithWrongData()
		{
	 		SpecimenCollectionGroup specimenCollectionGroupObj = null;
			try
			{
				specimenCollectionGroupObj = (SpecimenCollectionGroup) api.initSpecimenCollectionGroup();
		    	setLogger(specimenCollectionGroupObj);
		    	Logger.out.info("Inserting domain object------->"+specimenCollectionGroupObj);
		    	specimenCollectionGroupObj.setClinicalDiagnosis("wrongData");
				specimenCollectionGroupObj =  (SpecimenCollectionGroup) appService.createObject(specimenCollectionGroupObj);
				writeFailureOperationsToReport("SpecimenCollectionGroup",insertValidateOperation);
				//dataModelObjectMap.put("SpecimenCollectionGroup",specimenCollectionGroupObj);
				//Logger.out.info(" Domain Object is successfully added ---->    ID:: " + specimenCollectionGroupObj.getId().toString());
			//+ specimenCollectionGroupObj.getId().longValue() + " ::  Name :: " + specimenCollectionGroupObj.getName());
			}
			catch(Exception e)
			{
				writeSuccessfullOperationToReport(specimenCollectionGroupObj,insertValidateOperation);
				Logger.out.error(e.getMessage(),e);
				e.printStackTrace();
			}
		}
	 	
	 	
////////////////////////////////  End Add operation /////////////////	 	
    
    private void serachObject()
    {
		reportContents.append(newLine);
    	api = new APIDemo();
    	testSearchInstitution();
    	testSearchDepartment();
    	testSearchCancerResearchGroup();
    	testSearchBioHazard();
    	testSearchUser();    	
    	testSearchSite();
    	testSearchCollectionProtocol();
    	testSearchDistributionProtocol();
    	testSearchStorageType();
    	testSearchStorageContainer();
    	testSearchSpecimenArrayType();    	
    	testSearchParticipant();
    	testSearchCollectionProtocolRegistration();
    	testSearchSpecimenCollectionGroup();
    	testSearchSpecimen();
    	testSearchSpecimenArray();
    	//testSearchDistribution();
    	
/*    	Department department = api.initDepartment();
    	department.setId(new Long(2));
		try 
		{
			List resultList = appService.search(Department.class, department);
			System.out.println(" Result list " + resultList);
			for (Iterator resultsIterator = resultList.iterator(); resultsIterator.hasNext();) 
			{
				Department returneddepartment = (Department) resultsIterator.next();
				System.out.println(" Name:  " + returneddepartment.getName());
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
*/    	
    	
//    	try
//    	{
//    		
//    	MolecularSpecimen specimen = new MolecularSpecimen();
//    	specimen.setId(new Long(1));
//    	
//    	List list = appService.search(MolecularSpecimen.class,specimen);
//    	if(list != null && list.size() != 0)
//    	{
//    		System.out.println("List Size : " + list.size());
//    		specimen = (MolecularSpecimen)list.get(0);
//    		System.out.println("Type : " + specimen.getType() + " :: Id :" + 
//    	specimen.getId());
//    		System.out.println( " "  + specimen.getSpecimenCollectionGroup().getClinicalReport().getSurgicalPathologyNumber());
//    	}
//    	else
//    	{
//    		System.out.println("List is empty.");
//    	}
//    	} 
//    	catch(ApplicationException e)
//    	{
//    		e.printStackTrace();
//    	}
   }
	
    /*    
    private void testSearchDepartment()
    {
    	Department department = api.initDepartment();
    	department.setId(new Long(1));
		try 
		{
			List resultList = appService.search(Department.class, department);
			for (Iterator resultsIterator = resultList.iterator(); resultsIterator.hasNext();) 
			{
				Department returneddepartment = (Department) resultsIterator.next();
				System.out.println(returneddepartment.getName());
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
    }

    private void testSearchSpecimenArrayType()
    {
		SpecimenArrayType spec = new SpecimenArrayType();
		spec.setId(new Long(2));
		spec.setName("Any");
		//spec.setCapacity(null);
		//spec.setSpecimenTypeCollection(null);
		
		//spec.setId(new Long(1));
		//System.out.println(" List of TissueSpecimen objects::  " + results);
		try 
		{
			List resultList = appService.search(SpecimenArrayType.class, spec);
			System.out.println(" List of SpecimenArrayType objects::  " + resultList);
			for (Iterator resultsIterator = resultList.iterator(); resultsIterator.hasNext();) 
			{
				SpecimenArrayType returneddepartment = (SpecimenArrayType) resultsIterator.next();
				System.out.println(returneddepartment.getName());
				System.out.println(returneddepartment.getId().toString());
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
    }

    private void testSearchBioHazard()
    {
		Biohazard biohazard = new Biohazard();
		biohazard.setId(new Long(1));
		//spec.setCapacity(null);
		//spec.setSpecimenTypeCollection(null);
		
		//spec.setId(new Long(1));
		//System.out.println(" List of TissueSpecimen objects::  " + results);
		try 
		{
			List resultList = appService.search(Biohazard.class, biohazard);
			System.out.println(" List of SpecimenArrayType objects::  " + resultList);
			for (Iterator resultsIterator = resultList.iterator(); resultsIterator.hasNext();) 
			{
				Biohazard returneddepartment = (Biohazard) resultsIterator.next();
				System.out.println(returneddepartment.getName());
				System.out.println(returneddepartment.getId().toString());
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
    }
    */
    
    /////////////////// Start default data insertion ///////
    /////////////////////////// ----------------------- Start test Search operation
    
    private void testSearchDepartment()
    {
        //Department department = api.initDepartment();
    	Department cachedObject = (Department) dataModelObjectMap.get("Department"); 
    	Department department = new Department();
    	department.setId(cachedObject.getId());
    	setLogger(department);
     	Logger.out.info(" searching domain object");
         try {
        	 List resultList = appService.search(Department.class, department);
        	 for (Iterator resultsIterator = resultList.iterator(); resultsIterator.hasNext();) {
        		 Department returneddepartment = (Department) resultsIterator.next();
        		 Logger.out.info(" Domain Object is found by Serach operation:: Name --- >" + returneddepartment.getName());
        		 writeSuccessfullOperationToReport(returneddepartment,searchOperation);
             }
          }
          catch (Exception e) {
        	  writeFailureOperationsToReport("Department",searchOperation);
        	  Logger.out.error(e.getMessage(),e);
        	  e.printStackTrace();
          }

    }
    private void testSearchCancerResearchGroup()
    {
    	CancerResearchGroup cachedObject =(CancerResearchGroup) dataModelObjectMap.get("CancerResearchGroup");
    	CancerResearchGroup cancerResearchGroup = new CancerResearchGroup();
    	cancerResearchGroup.setId(cachedObject.getId());
    	setLogger(cancerResearchGroup);
     	Logger.out.info(" searching domain object");
     	//cancerResearchGroup.setId(new Long(1));
         try {
        	 List resultList = appService.search(CancerResearchGroup.class, cancerResearchGroup);
        	 for (Iterator resultsIterator = resultList.iterator(); resultsIterator.hasNext();) {
        		 CancerResearchGroup returnedcancerResearchGroup = (CancerResearchGroup) resultsIterator.next();
        		 writeSuccessfullOperationToReport(returnedcancerResearchGroup,searchOperation);
        		 Logger.out.info(" Domain Object is found by Serach operation:: Name --- >" + returnedcancerResearchGroup.getName());
             }
          } 
          catch (Exception e) {
        	 writeFailureOperationsToReport("CancerResearchGroup",searchOperation);
          	 Logger.out.error(e.getMessage(),e);
  	 		 e.printStackTrace();
          }

    }
	   
    private void testSearchSite()
    {
    	Site cachedObject = (Site) dataModelObjectMap.get("Site");
    	Site site = new Site();
    	site.setId(cachedObject.getId());
   	 	setLogger(site);
     	Logger.out.info(" searching domain object");
     	//site.setId(new Long(1));
         try {
        	 List resultList = appService.search(Site.class,site);
        	 for (Iterator resultsIterator = resultList.iterator(); resultsIterator.hasNext();) {
        		 Site returnedsite = (Site) resultsIterator.next();
        		 writeSuccessfullOperationToReport(returnedsite,searchOperation);
        		 Logger.out.info(" Domain Object is successfully Found ---->  :: " + returnedsite.getName());
             }
          } 
          catch (Exception e) {
        	writeFailureOperationsToReport("Site",searchOperation);
          	Logger.out.error(e.getMessage(),e);
	 		e.printStackTrace();
          }

    }
    private void testSearchUser()
    {
    	 User cachedObject = (User) dataModelObjectMap.get("User");
    	 User user = (User) new User();
     	 setLogger(user);
     	 Logger.out.info(" searching domain object");
    	 user.setId(cachedObject.getId());
    	 //user.setId(new Long(1));
    	 
         try {
        	 List resultList = appService.search(User.class,user);
        	 for (Iterator resultsIterator = resultList.iterator(); resultsIterator.hasNext();) {
        		 User returneduser = (User) resultsIterator.next();
        		 writeSuccessfullOperationToReport(returneduser,searchOperation);
        		 Logger.out.info(" Domain Object is successfully Found ---->  :: " + returneduser.getEmailAddress());
             }
          } 
          catch (Exception e) {
        	writeFailureOperationsToReport("User",searchOperation);  
          	Logger.out.error(e.getMessage(),e);
	 		e.printStackTrace();
          }

    } 
    private void testSearchParticipant()
    {
    	 Participant cachedObject = (Participant)dataModelObjectMap.get("Participant");
    	 Participant participant = new Participant();
     	 setLogger(participant);
    	 Logger.out.info(" searching domain object");
    	 participant.setId(cachedObject.getId());
    	 //participant.setId(new Long(3));
         try {
        	 List resultList = appService.search(Participant.class,participant);
        	 for (Iterator resultsIterator = resultList.iterator(); resultsIterator.hasNext();) {
        		 Participant returnedparticipant = (Participant) resultsIterator.next();
        		 writeSuccessfullOperationToReport(returnedparticipant,searchOperation);
        		 Logger.out.info(" Domain Object is successfully Found ---->  :: " + returnedparticipant.getFirstName());
             }
          } 
          catch (Exception e) {
        	writeFailureOperationsToReport("Participant",searchOperation);  
          	Logger.out.error(e.getMessage(),e);
	 		e.printStackTrace();
          }

    }
    private void testSearchInstitution()
    {
    	Institution cachedObject = (Institution)dataModelObjectMap.get("Institution");
    	Institution institution = new Institution();
    	setLogger(institution);
    	Logger.out.info(" searching domain object");
    	institution.setId(cachedObject.getId());//institution.setId(new Long(1));
         try {
        	 List resultList = appService.search(Institution.class,institution);
        	 for (Iterator resultsIterator = resultList.iterator(); resultsIterator.hasNext();) {
        		 Institution returnedinstitution = (Institution) resultsIterator.next();
        		 writeSuccessfullOperationToReport(returnedinstitution,searchOperation);
        		 Logger.out.info(" Domain Object is successfully Found ---->  :: " + returnedinstitution.getName());
             }
          } 
          catch (Exception e) {
        	writeFailureOperationsToReport("Institution",searchOperation);  
          	Logger.out.error(e.getMessage(),e);
	 		e.printStackTrace();
          }

    }
    private void testSearchBioHazard()
    {
    	Biohazard cachedObject = (Biohazard)dataModelObjectMap.get("Biohazard");
    	Biohazard biohazard = new Biohazard();
    	setLogger(biohazard);
    	Logger.out.info(" searching domain object");
    	biohazard.setId(cachedObject.getId());    	
         try {
        	 List resultList = appService.search(Biohazard.class,biohazard);
        	 for (Iterator resultsIterator = resultList.iterator(); resultsIterator.hasNext();) {
        		 Biohazard returnedbiohazard = (Biohazard) resultsIterator.next();
        		 writeSuccessfullOperationToReport(returnedbiohazard,searchOperation);
        		 Logger.out.info(" Domain Object is successfully Found ---->  :: " + returnedbiohazard.getName());
             }
          } 
          catch (Exception e) {
        	writeFailureOperationsToReport("Biohazard",searchOperation);    
          	Logger.out.error(e.getMessage(),e);
	 		e.printStackTrace();
          }

    }
    private void testSearchDistribution()
    {
    	Distribution cachedDistribution =(Distribution)dataModelObjectMap.get("Distribution");
    	Distribution distribution = new Distribution();
    	setLogger(distribution);
    	Logger.out.info(" searching Distribution object id-----------" + cachedDistribution.getId());
    	distribution.setId(cachedDistribution.getId());
         try {
        	 List resultList = appService.search(Distribution.class,distribution);
        	 Logger.out.info(" Domain Object is successfully Found size ---->  :: " + resultList.size());
        	 for (Iterator resultsIterator = resultList.iterator(); resultsIterator.hasNext();) {
        		 Distribution returneddistribution = (Distribution) resultsIterator.next();        		 
        		 writeSuccessfullOperationToReport(returneddistribution,searchOperation);
        		 Logger.out.info(" Domain Object is successfully Found ---->  :: " + returneddistribution.getId());        		 
        		 Logger.out.info(" Domain Object is successfully Found ---->  :: " + returneddistribution.getMessageLabel());
             }
          } 
          catch (Exception e) {
        	writeFailureOperationsToReport("Distribution",searchOperation);    
          	Logger.out.error(e.getMessage(),e);
	 		e.printStackTrace();
          }

    }
    private void testSearchDistributionProtocol()
    {
    	DistributionProtocol cacheddistributionProtocol =(DistributionProtocol)dataModelObjectMap.get("DistributionProtocol");
    	DistributionProtocol distributionProtocol =new DistributionProtocol();
    	setLogger(distributionProtocol);
    	Logger.out.info(" searching domain object");
    	distributionProtocol.setId(cacheddistributionProtocol.getId());
         try {
        	 List resultList = appService.search(DistributionProtocol.class,distributionProtocol);
        	 for (Iterator resultsIterator = resultList.iterator(); resultsIterator.hasNext();) {
        		 DistributionProtocol returneddistributionprotocol = (DistributionProtocol) resultsIterator.next();
        		 writeSuccessfullOperationToReport(returneddistributionprotocol,searchOperation);
        		 Logger.out.info(" Domain Object is successfully Found ---->  :: " + returneddistributionprotocol.getTitle());
             }
          } 
          catch (Exception e) {
        	writeFailureOperationsToReport("DistributionProtocol",searchOperation);   
          	Logger.out.error(e.getMessage(),e);
	 		e.printStackTrace();
          }

    }
    private void testSearchCollectionProtocol()
    {
    	CollectionProtocol cachedcollectionProtocol = (CollectionProtocol)dataModelObjectMap.get("CollectionProtocol");
    	CollectionProtocol collectionProtocol = new CollectionProtocol();
    	setLogger(collectionProtocol);
    	Logger.out.info(" searching domain object");
    	collectionProtocol.setId(cachedcollectionProtocol.getId());
         try {
        	 List resultList = appService.search(CollectionProtocol.class,collectionProtocol);
        	 for (Iterator resultsIterator = resultList.iterator(); resultsIterator.hasNext();) {
        		 CollectionProtocol returnedcollectionprotocol = (CollectionProtocol) resultsIterator.next();
        		 writeSuccessfullOperationToReport(returnedcollectionprotocol,searchOperation);
        		 Logger.out.info(" Domain Object is successfully Found ---->  :: " + returnedcollectionprotocol.getTitle());
             }
          } 
          catch (Exception e) {
        	  writeFailureOperationsToReport("CollectionProtocol",searchOperation);  
        	Logger.out.error(e.getMessage(),e);
	 		e.printStackTrace();
          }

    }
    private void testSearchCollectionProtocolRegistration()
    {
    	CollectionProtocolRegistration cachedcollectionProtocolRegistration =(CollectionProtocolRegistration)dataModelObjectMap.get("CollectionProtocolRegistration");
    	CollectionProtocolRegistration collectionProtocolRegistration  =new CollectionProtocolRegistration();
    	setLogger(collectionProtocolRegistration);
    	Logger.out.info(" searching domain object");
	    collectionProtocolRegistration.setId(cachedcollectionProtocolRegistration.getId());
         try {
        	 List resultList = appService.search(CollectionProtocolRegistration.class,collectionProtocolRegistration);
        	 for (Iterator resultsIterator = resultList.iterator(); resultsIterator.hasNext();) {
        		 CollectionProtocolRegistration returnedcollectionprotocolregistration = (CollectionProtocolRegistration) resultsIterator.next();
        		 writeSuccessfullOperationToReport(returnedcollectionprotocolregistration,searchOperation);
        		 Logger.out.info(" Domain Object is successfully Found ---->  :: " + returnedcollectionprotocolregistration.getMessageLabel());
             }
          } 
          catch (Exception e) {
        	  writeFailureOperationsToReport("CollectionProtocolRegistration",searchOperation);    
          	Logger.out.error(e.getMessage(),e);
	 		e.printStackTrace();
          }
    }
    private void testSearchStorageType()
    {
    	StorageType cachedstorageType =(StorageType)dataModelObjectMap.get("StorageType");
    	StorageType storageType =new StorageType();
    	setLogger(storageType);
    	Logger.out.info(" searching domain object");
    	storageType.setId(cachedstorageType.getId());
    	
         try {
        	 List resultList = appService.search(StorageType.class,storageType);
        	 for (Iterator resultsIterator = resultList.iterator(); resultsIterator.hasNext();) {
        		 StorageType returnedstoragetype = (StorageType) resultsIterator.next();
        		 writeSuccessfullOperationToReport(returnedstoragetype,searchOperation);
        		 Logger.out.info(" Domain Object is successfully Found ---->  :: " +returnedstoragetype.getName());
             }
          } 
          catch (Exception e) {
          	Logger.out.error(e.getMessage(),e);
	 		e.printStackTrace();
          }
    }
    private void testSearchStorageContainer()
    {
    	 StorageContainer cachedstorageContainer =(StorageContainer)dataModelObjectMap.get("StorageContainer");
    	 StorageContainer storageContainer =new StorageContainer();
      	 setLogger(storageContainer);
     	Logger.out.info(" searching domain object");
     	 storageContainer.setId(cachedstorageContainer.getId());
         try {
        	 List resultList = appService.search(StorageContainer.class,storageContainer);
        	 for (Iterator resultsIterator = resultList.iterator(); resultsIterator.hasNext();) {
        		 StorageContainer returnedstoragecontainer = (StorageContainer) resultsIterator.next();
        		 writeSuccessfullOperationToReport(returnedstoragecontainer,searchOperation);
        		 Logger.out.info(" Domain Object is successfully Found ---->  :: " + returnedstoragecontainer.getName());
             }
          } 
          catch (Exception e) {
        	  writeFailureOperationsToReport("StorageContainer",searchOperation);   
          	Logger.out.error(e.getMessage(),e);
	 		e.printStackTrace();
          }

    }
    private void testSearchSpecimen()
    {
    	Specimen cachedspecimen =(Specimen)dataModelObjectMap.get("Specimen");
    	Specimen specimen = new Specimen();
     	setLogger(specimen);
    	Logger.out.info(" searching domain object");
    	specimen.setId(cachedspecimen.getId());
         try {
        	 List resultList = appService.search(Specimen.class,specimen);
        	 for (Iterator resultsIterator = resultList.iterator(); resultsIterator.hasNext();) {
        		 Specimen returnedspecimen = (Specimen) resultsIterator.next();
        		 writeSuccessfullOperationToReport(returnedspecimen,searchOperation);
        		 Logger.out.info(" Domain Object is successfully Found ---->  :: " + returnedspecimen.getLabel());
             }
          } 
          catch (Exception e) {
        	  writeFailureOperationsToReport("Specimen",searchOperation);  
          	Logger.out.error(e.getMessage(),e);
	 		e.printStackTrace();
          }

    }
    private void testSearchSpecimenArrayType()
    {
    	SpecimenArrayType cachedspecimenArrayType =(SpecimenArrayType)dataModelObjectMap.get("SpecimenArrayType");
    	SpecimenArrayType specimenArrayType = new SpecimenArrayType();
     	setLogger(specimenArrayType);
    	Logger.out.info(" searching domain object");
    	specimenArrayType.setId(cachedspecimenArrayType.getId());
         try {
        	 List resultList = appService.search(SpecimenArrayType.class,specimenArrayType);
        	 for (Iterator resultsIterator = resultList.iterator(); resultsIterator.hasNext();) {
        		 SpecimenArrayType returnedspecimenarraytype = (SpecimenArrayType) resultsIterator.next();
        		 writeSuccessfullOperationToReport(returnedspecimenarraytype,searchOperation);
        		 Logger.out.info(" Domain Object is successfully Found ---->  :: " + returnedspecimenarraytype.getName());
             }
          } 
          catch (Exception e) {
        	  writeFailureOperationsToReport("SpecimenArrayType",searchOperation);   
          	Logger.out.error(e.getMessage(),e);
	 		e.printStackTrace();
          }

    }
    private void testSearchSpecimenArray()
    {
    	SpecimenArray cachedspecimenArray = (SpecimenArray) dataModelObjectMap.get("SpecimenArray");
    	SpecimenArray specimenArray = new SpecimenArray();
     	setLogger(specimenArray);
    	Logger.out.info(" searching domain object");
		specimenArray.setId(cachedspecimenArray.getId());
         try {
        	 List resultList = appService.search(SpecimenArray.class,specimenArray);
        	 for (Iterator resultsIterator = resultList.iterator(); resultsIterator.hasNext();) {
        		 SpecimenArray returnedspecimenarray = (SpecimenArray) resultsIterator.next();
        		 writeSuccessfullOperationToReport(returnedspecimenarray,searchOperation);
        		 Logger.out.info(" Domain Object is successfully Found ---->  :: " +returnedspecimenarray.getName());
             }
          } 
          catch (Exception e) {
        	  writeFailureOperationsToReport("SpecimenArray",searchOperation);     
          	Logger.out.error(e.getMessage(),e);
	 		e.printStackTrace();
          }

    }
    private void testSearchSpecimenCollectionGroup()
    {
    	SpecimenCollectionGroup cachedspecimenCollectionGroup = (SpecimenCollectionGroup)dataModelObjectMap.get("SpecimenCollectionGroup");
    	SpecimenCollectionGroup specimenCollectionGroup = new SpecimenCollectionGroup();
     	setLogger(specimenCollectionGroup);
    	Logger.out.info(" searching domain object");
    	specimenCollectionGroup.setId(cachedspecimenCollectionGroup.getId());
         try {
        	 List resultList = appService.search(SpecimenCollectionGroup.class,specimenCollectionGroup);
        	 for (Iterator resultsIterator = resultList.iterator(); resultsIterator.hasNext();) {
        		 SpecimenCollectionGroup returnedspecimencollectiongroup = (SpecimenCollectionGroup) resultsIterator.next();
        		 writeSuccessfullOperationToReport(returnedspecimencollectiongroup,searchOperation);
        		 Logger.out.info(" Domain Object is successfully Found ---->  :: " + returnedspecimencollectiongroup.getName());
             }
          } 
          catch (Exception e) {
        	writeFailureOperationsToReport("SpecimenCollectionGroup",searchOperation);    
          	Logger.out.error(e.getMessage(),e);
	 		e.printStackTrace();
          }
    }

    /*
    private void testSearchSpecimenArrayType()
    {
		Specimen spec = new Specimen();
		spec.setId(new Long(2));
		//spec.setName("Any");
		//spec.setCapacity(null);
		//spec.setSpecimenTypeCollection(null);
		
		//spec.setId(new Long(1));
		//System.out.println(" List of TissueSpecimen objects::  " + results);
		try 
		{
			List resultList = appService.search(SpecimenArrayType.class, spec);
			System.out.println(" List of SpecimenArrayType objects::  " + resultList);
			for (Iterator resultsIterator = resultList.iterator(); resultsIterator.hasNext();) 
			{
				SpecimenArrayType returneddepartment = (SpecimenArrayType) resultsIterator.next();
				System.out.println(returneddepartment.getName());
				System.out.println(returneddepartment.getId().toString());
			}
		} 
		catch (Exception e) 
		{
			Logger.out.error(e);
		}
    }
    */
    
    ////////////////// --- End Search operation
	private void updateObjects()
	{
				reportContents.append(newLine);
				
				testUpdateInstitution();
				testUpdateDepartment();
				testUpdateCancerResearchGroup();
				testUpdateBiohazard();
				testUpdateSite();				
				testUpdateCollectionProtocol();
				testUpdateDistributionProtocol();				
				testUpdateParticipant();
				testUpdateCollectionProtocolRegistration();
				testUpdateSpecimenCollectionGroup();
				testUpdateSpecimen();
				testUpdateStorageType();
				testUpdateStorageContainer();
				testUpdateSpecimenArrayType();
				testUpdateSpecimenArray();
				
				
				testUpdateInstitutionWithWrongData();
				testUpdateDepartmentWithWrongData();
				testUpdateCancerResearchGroupWithWrongData();
				testUpdateBiohazardWithWrongData();
				testUpdateSiteWithWrongData();				
				testUpdateCollectionProtocolWithWrongData();				
				testUpdateDistributionProtocolWithWrongData();				
				testUpdateParticipantWithWrongData();				
				testUpdateCollectionProtocolRegistrationWithWrongData();
				testUpdateSpecimenCollectionGroupWithWrongData();						
				testUpdateSpecimenWithWrongData();
				
				/*Object obj = api.initCancerResearchGroup();
				AbstractDomainObject domainObject = setId(obj,new Long(1)) ;
				appService.updateObject(domainObject);

				obj = api.initDepartment();
				AbstractDomainObject domainObject = setId(obj,new Long(1)) ;
				appService.updateObject(domainObject);

				obj = api.initBioHazard();
				AbstractDomainObject domainObject = setId(obj,new Long(1)) ;
				appService.updateObject(domainObject);

				obj = api.initInstitution();
				AbstractDomainObject domainObject = setId(obj,new Long(1)) ;
				appService.updateObject(domainObject);

				obj = api.initSite();
				AbstractDomainObject domainObject = setId(obj,new Long(1)) ;
				appService.updateObject(domainObject);

				obj = api.initStorageType();
				AbstractDomainObject domainObject = setId(obj,new Long(1)) ;
				appService.updateObject(domainObject);

				obj = api.initSpecimenArrayType();
				AbstractDomainObject domainObject = setId(obj,new Long(1)) ;
				appService.updateObject(domainObject);

				obj = api.initStorageContainer();
				AbstractDomainObject domainObject = setId(obj,new Long(1)) ;
				appService.updateObject(domainObject);

				obj = api.initUser();
				AbstractDomainObject domainObject = setId(obj,new Long(1)) ;
				appService.updateObject(domainObject);

				obj = api.initParticipant();
				AbstractDomainObject domainObject = setId(obj,new Long(1)) ;
				appService.updateObject(domainObject);

				obj = api.initCollectionProtocol();
				AbstractDomainObject domainObject = setId(obj,new Long(1)) ;
				appService.updateObject(domainObject);

				obj = api.initSpecimen();
				AbstractDomainObject domainObject = setId(obj,new Long(1)) ;
				appService.updateObject(domainObject);

				obj = api.initSpecimenCollectionGroup();
				AbstractDomainObject domainObject = setId(obj,new Long(1)) ;
				appService.updateObject(domainObject);

				obj = api.initDistribution();
				AbstractDomainObject domainObject = setId(obj,new Long(1)) ;
				appService.updateObject(domainObject);

				obj = api.initDistributionProtocol();
				AbstractDomainObject domainObject = setId(obj,new Long(1)) ;
				appService.updateObject(domainObject);

				obj = api.initCollectionProtocolRegistration();
				AbstractDomainObject domainObject = setId(obj,new Long(1)) ;
				appService.updateObject(domainObject);

				obj = api.initSpecimenArray();
				AbstractDomainObject domainObject = setId(obj,new Long(1)) ;
				appService.updateObject(domainObject);*/

	}

	/*private AbstractDomainObject setId(Object obj,Long id)
	{
		AbstractDomainObject domainObject = (AbstractDomainObject) obj;
		domainObject.setId(id);
		return domainObject;
	}*/

	private void testUpdateInstitution()
	{
		Institution institution = (Institution)dataModelObjectMap.get("Institution");
    	setLogger(institution);
    	Logger.out.info("updating domain object------->"+institution);
	    try 
		{
	    	APIDemo apiDemo = new APIDemo();
	    	apiDemo.updateInstitution(institution);	  
	    	//institution.setName("inst"+UniqueKeyGeneratorUtil.getUniqueKey());
	     	Institution updatedInstitution = (Institution) appService.updateObject(institution);
	     	writeSuccessfullOperationToReport(updatedInstitution,updateOperation);
	     	Logger.out.info("Domain object successfully updated ---->"+updatedInstitution);
	    } 
	    catch (Exception e) {
	    	writeFailureOperationsToReport("Institution",updateOperation);
	    	Logger.out.error(e.getMessage(),e);
	 		e.printStackTrace();
	    }
	}
	
	private void testUpdateInstitutionWithWrongData()
	{
		Institution institution = (Institution)dataModelObjectMap.get("Institution");
    	setLogger(institution);
    	Logger.out.info("updating domain object------->"+institution);
	    try 
		{
	    	APIDemo apiDemo = new APIDemo();
	    	institution.setName(null);
	    	//apiDemo.updateInstitution(institution);	  
	    	//institution.setName("inst"+UniqueKeyGeneratorUtil.getUniqueKey());
	     	Institution updatedInstitution = (Institution) appService.updateObject(institution);
	     	writeFailureOperationsToReport("Institution",updateValidateOperation);
	     	//Logger.out.info("Domain object successfully updated ---->"+updatedInstitution);
	    } 
	    catch (Exception e) {
	    	writeSuccessfullOperationToReport(institution,updateValidateOperation);
	    	Logger.out.error(e.getMessage(),e);
	 		e.printStackTrace();
	    }
	}
	
	private void testUpdateDepartment()
	{
		Department department = (Department)dataModelObjectMap.get("Department");
    	setLogger(department);
    	Logger.out.info("updating domain object------->"+department);
	    try 
		{
	    	APIDemo apiDemo = new APIDemo();
	    	apiDemo.updateDepartment(department);	    	
	    	//department.setName("dt"+UniqueKeyGeneratorUtil.getUniqueKey());
	    	Department updatedDepartment = (Department) appService.updateObject(department);
	    	writeSuccessfullOperationToReport(updatedDepartment,updateOperation);
	     	Logger.out.info("Domain object successfully updated ---->"+updatedDepartment);
	    } 
	    catch (Exception e) {
	    	writeFailureOperationsToReport("Department",updateOperation);
	    	Logger.out.error(e.getMessage(),e);
	 		e.printStackTrace();
	    }
	}
	
	private void testUpdateDepartmentWithWrongData()
	{
		Department department = (Department)dataModelObjectMap.get("Department");
    	setLogger(department);
    	Logger.out.info("updating domain object------->"+department);
	    try 
		{
	    	APIDemo apiDemo = new APIDemo();
	    	department.setName(null);
	    	//apiDemo.updateDepartment(department);	    	
	    	//department.setName("dt"+UniqueKeyGeneratorUtil.getUniqueKey());
	    	Department updatedDepartment = (Department) appService.updateObject(department);
	    	writeFailureOperationsToReport("Department",updateValidateOperation);
	     	//Logger.out.info("Domain object successfully updated ---->"+updatedDepartment);
	    } 
	    catch (Exception e) {
	    	writeSuccessfullOperationToReport(department,updateValidateOperation);
	    	Logger.out.error(e.getMessage(),e);
	 		e.printStackTrace();
	    }
	}
	
	private void testUpdateCancerResearchGroup()
	{
		CancerResearchGroup cancerResearchGroup = (CancerResearchGroup)dataModelObjectMap.get("CancerResearchGroup");
    	setLogger(cancerResearchGroup);
    	Logger.out.info("updating domain object------->"+cancerResearchGroup);
	    try 
		{
	    	APIDemo apiDemo = new APIDemo();
	    	apiDemo.updateCancerResearchGroup(cancerResearchGroup);	    	
	    	//cancerResearchGroup.setName("crg"+UniqueKeyGeneratorUtil.getUniqueKey());
	    	CancerResearchGroup updatedCancerResearchGroup = (CancerResearchGroup) appService.updateObject(cancerResearchGroup);
	    	writeSuccessfullOperationToReport(updatedCancerResearchGroup,updateOperation);
	    	Logger.out.info("Domain object successfully updated ---->"+updatedCancerResearchGroup);
	    } 
	    catch (Exception e) {
	    	writeFailureOperationsToReport("CancerResearchGroup",updateOperation);
	    	Logger.out.error(e.getMessage(),e);
	 		e.printStackTrace();
	    }
	}
	
	private void testUpdateCancerResearchGroupWithWrongData()
	{
		CancerResearchGroup cancerResearchGroup = (CancerResearchGroup)dataModelObjectMap.get("CancerResearchGroup");
    	setLogger(cancerResearchGroup);
    	Logger.out.info("updating domain object------->"+cancerResearchGroup);
	    try 
		{
	    	APIDemo apiDemo = new APIDemo();
	    	cancerResearchGroup.setName(null);
	    	//apiDemo.updateCancerResearchGroup(cancerResearchGroup);	    	
	    	//cancerResearchGroup.setName("crg"+UniqueKeyGeneratorUtil.getUniqueKey());
	    	CancerResearchGroup updatedCancerResearchGroup = (CancerResearchGroup) appService.updateObject(cancerResearchGroup);
	    	writeFailureOperationsToReport("CancerResearchGroup",updateValidateOperation);
	     	//Logger.out.info("Domain object successfully updated ---->"+updatedCancerResearchGroup);
	    } 
	    catch (Exception e) {
	    	writeSuccessfullOperationToReport(cancerResearchGroup,updateValidateOperation);
	    	Logger.out.error(e.getMessage(),e);
	 		e.printStackTrace();
	    }
	}
	
	private void testUpdateBiohazard()
	{
		Biohazard biohazard = (Biohazard)dataModelObjectMap.get("Biohazard");
    	setLogger(biohazard);
    	Logger.out.info("updating domain object------->"+biohazard);
	    try 
		{
	    	APIDemo apiDemo = new APIDemo();
	    	apiDemo.updateBiohazard(biohazard);
	    	Biohazard updatedBiohazard = (Biohazard) appService.updateObject(biohazard);
	    	writeSuccessfullOperationToReport(updatedBiohazard,updateOperation);
	    	Logger.out.info("Domain object successfully updated ---->"+updatedBiohazard);
	    } 
	    catch (Exception e) {
	    	writeFailureOperationsToReport("Biohazard",updateOperation);
	    	Logger.out.error(e.getMessage(),e);
	 		e.printStackTrace();
	    }
	}
	
	
	private void testUpdateBiohazardWithWrongData()
	{
		Biohazard biohazard = (Biohazard)dataModelObjectMap.get("Biohazard");
    	setLogger(biohazard);
    	Logger.out.info("updating domain object------->"+biohazard);
	    try 
		{
	    	APIDemo apiDemo = new APIDemo();
	    	biohazard.setType("wrongData");
	    	//apiDemo.updateBiohazard(biohazard);
	    	Biohazard updatedBiohazard = (Biohazard) appService.updateObject(biohazard);
	    	writeSuccessfullOperationToReport(updatedBiohazard,updateValidateOperation);
	     	//Logger.out.info("Domain object successfully updated ---->"+updatedBiohazard);
	    } 
	    catch (Exception e) {
	    	writeSuccessfullOperationToReport(biohazard,updateValidateOperation);
	    	Logger.out.error(e.getMessage(),e);
	 		e.printStackTrace();
	    }
	}
	
	private void testUpdateSite()
	{
		Site site = (Site)dataModelObjectMap.get("Site");
    	setLogger(site);
    	Logger.out.info("updating domain object------->"+site);
	    try 
		{
	    	APIDemo apiDemo = new APIDemo();
	    	apiDemo.updateSite(site);
	    	Site updatedSite = (Site) appService.updateObject(site);
	    	writeSuccessfullOperationToReport(updatedSite,updateOperation);
	    	Logger.out.info("Domain object successfully updated ---->"+updatedSite);
	    } 
	    catch (Exception e) {
	    	writeFailureOperationsToReport("Site",updateOperation);
	    	Logger.out.error(e.getMessage(),e);
	 		e.printStackTrace();
	    }
	}
	
	private void testUpdateSiteWithWrongData()
	{
		Site site = (Site)dataModelObjectMap.get("Site");
    	setLogger(site);
    	Logger.out.info("updating domain object------->"+site);
	    try 
		{
	    	APIDemo apiDemo = new APIDemo();
	    	site.setType("wrongData");
	    	//apiDemo.updateSite(site);
	    	Site updatedSite = (Site) appService.updateObject(site);
	    	writeFailureOperationsToReport("Site",updateValidateOperation);
	     	//Logger.out.info("Domain object successfully updated ---->"+updatedSite);
	    } 
	    catch (Exception e) {
	    	writeSuccessfullOperationToReport(site,updateValidateOperation);
	    	Logger.out.error(e.getMessage(),e);
	 		e.printStackTrace();
	    }
	}
	
	private void testUpdateCollectionProtocol()
	{
		CollectionProtocol collectionProtocol = (CollectionProtocol)dataModelObjectMap.get("CollectionProtocol");
    	setLogger(collectionProtocol);
    	Logger.out.info("updating domain object------->"+collectionProtocol);
	    try 
		{
	    	APIDemo apiDemo = new APIDemo();
	    	apiDemo.updateCollectionProtocol(collectionProtocol);
	    	CollectionProtocol updatedCollectionProtocol = (CollectionProtocol) appService.updateObject(collectionProtocol);
	    	writeSuccessfullOperationToReport(updatedCollectionProtocol,updateOperation);
	    	Logger.out.info("Domain object successfully updated ---->"+updatedCollectionProtocol);
	    } 
	    catch (Exception e) {
	    	writeFailureOperationsToReport("CollectionProtocol",updateOperation);
	    	Logger.out.error(e.getMessage(),e);
	 		e.printStackTrace();
	    }
	}
	
	private void testUpdateCollectionProtocolWithWrongData()
	{
		CollectionProtocol collectionProtocol = (CollectionProtocol)dataModelObjectMap.get("CollectionProtocol");
    	setLogger(collectionProtocol);
    	Logger.out.info("updating domain object------->"+collectionProtocol);
	    try 
		{
	    	APIDemo apiDemo = new APIDemo();
	    	collectionProtocol.setTitle(null);
	    	//apiDemo.updateCollectionProtocol(collectionProtocol);
	    	CollectionProtocol updatedCollectionProtocol = (CollectionProtocol) appService.updateObject(collectionProtocol);
	    	writeFailureOperationsToReport("CollectionProtocol",updateValidateOperation);
	    	//Logger.out.info("Domain object successfully updated ---->"+updatedCollectionProtocol);
	    } 
	    catch (Exception e) {
	    	writeSuccessfullOperationToReport(collectionProtocol,updateValidateOperation);
	    	Logger.out.error(e.getMessage(),e);
	 		e.printStackTrace();
	    }
	}
	
	private void testUpdateDistributionProtocol()
	{
		DistributionProtocol distributionProtocol = (DistributionProtocol)dataModelObjectMap.get("DistributionProtocol");
    	setLogger(distributionProtocol);
    	Logger.out.info("updating domain object------->"+distributionProtocol);
	    try 
		{
	    	APIDemo apiDemo = new APIDemo();
	    	apiDemo.updateDistributionProtocol(distributionProtocol);
	    	DistributionProtocol updatedDistributionProtocol = (DistributionProtocol) appService.updateObject(distributionProtocol);
	    	writeSuccessfullOperationToReport(updatedDistributionProtocol,updateOperation);
	    	Logger.out.info("Domain object successfully updated ---->"+updatedDistributionProtocol);
	    } 
	    catch (Exception e) {
	    	writeFailureOperationsToReport("DistributionProtocol",updateOperation);
	    	Logger.out.error(e.getMessage(),e);
	 		e.printStackTrace();
	    }
	}
	
	private void testUpdateDistributionProtocolWithWrongData()
	{
		DistributionProtocol distributionProtocol = (DistributionProtocol)dataModelObjectMap.get("DistributionProtocol");
    	setLogger(distributionProtocol);
    	Logger.out.info("updating domain object------->"+distributionProtocol);
	    try 
		{
	    	APIDemo apiDemo = new APIDemo();
	    	distributionProtocol.setShortTitle(null);
	    	//apiDemo.updateDistributionProtocol(distributionProtocol);
	    	DistributionProtocol updatedDistributionProtocol = (DistributionProtocol) appService.updateObject(distributionProtocol);
	    	writeFailureOperationsToReport("DistributionProtocol",updateValidateOperation);
	    	//Logger.out.info("Domain object successfully updated ---->"+updatedDistributionProtocol);
	    } 
	    catch (Exception e) {
	    	writeSuccessfullOperationToReport(distributionProtocol,updateValidateOperation);
	    	Logger.out.error(e.getMessage(),e);
	 		e.printStackTrace();
	    }
	}
	
	private void testUpdateParticipant()
	{
		Participant participant = (Participant) dataModelObjectMap.get("Participant");
		setLogger(participant);
    	Logger.out.info("updating domain object------->"+participant);
	    try 
		{
	    	APIDemo apiDemo = new APIDemo();
	    	apiDemo.updateParticipant(participant);
	    	Participant updatedParticipant = (Participant) appService.updateObject(participant);
	    	writeSuccessfullOperationToReport(updatedParticipant,updateOperation);
	    	Logger.out.info("Domain object successfully updated ---->"+updatedParticipant);
	    } 
	    catch (Exception e) {
	    	writeFailureOperationsToReport("Participant",updateOperation);
	    	Logger.out.error(e.getMessage(),e);
	 		e.printStackTrace();
	    }
	}
	
	private void testUpdateParticipantWithWrongData()
	{
		Participant participant = (Participant) dataModelObjectMap.get("Participant");
		setLogger(participant);
    	Logger.out.info("updating domain object------->"+participant);
	    try 
		{
	    	APIDemo apiDemo = new APIDemo();
	    	participant.setGender("wrongData");
	    	//apiDemo.updateParticipant(participant);
	    	Participant updatedParticipant = (Participant) appService.updateObject(participant);
	    	writeFailureOperationsToReport("Participant",updateValidateOperation);
	     	//Logger.out.info("Domain object successfully updated ---->"+updatedParticipant);
	    } 
	    catch (Exception e) {
	    	writeSuccessfullOperationToReport(participant,updateValidateOperation);
	    	Logger.out.error(e.getMessage(),e);
	 		e.printStackTrace();
	    }
	}
	
	private void testUpdateSpecimenCollectionGroup()
	{
		SpecimenCollectionGroup specimenCollectionGroup = (SpecimenCollectionGroup) dataModelObjectMap.get("SpecimenCollectionGroup");
		setLogger(specimenCollectionGroup);
		Logger.out.info("updating domain object------->"+specimenCollectionGroup);
		try 
		{
			APIDemo apiDemo = new APIDemo();
			apiDemo.updateSpecimenCollectionGroup(specimenCollectionGroup);
			SpecimenCollectionGroup updatedSpecimenCollectionGroup = (SpecimenCollectionGroup) appService.updateObject(specimenCollectionGroup);
			writeSuccessfullOperationToReport(updatedSpecimenCollectionGroup,updateOperation);
			Logger.out.info("Domain object successfully updated ---->"+updatedSpecimenCollectionGroup);
		} 
		catch (Exception e) {
			writeFailureOperationsToReport("SpecimenCollectionGroup",updateOperation);
			Logger.out.error(e.getMessage(),e);
	 		e.printStackTrace();
		}
	}
	
	private void testUpdateSpecimenCollectionGroupWithWrongData()
	{
		SpecimenCollectionGroup specimenCollectionGroup = (SpecimenCollectionGroup) dataModelObjectMap.get("SpecimenCollectionGroup");
		setLogger(specimenCollectionGroup);
		Logger.out.info("updating domain object------->"+specimenCollectionGroup);
		try 
		{
			APIDemo apiDemo = new APIDemo();
			specimenCollectionGroup.setClinicalStatus("wrongData");
			//apiDemo.updateSpecimenCollectionGroup(specimenCollectionGroup);
			SpecimenCollectionGroup updatedSpecimenCollectionGroup = (SpecimenCollectionGroup) appService.updateObject(specimenCollectionGroup);
			writeFailureOperationsToReport("SpecimenCollectionGroup",updateValidateOperation);
			//Logger.out.info("Domain object successfully updated ---->"+updatedSpecimenCollectionGroup);
		} 
		catch (Exception e) {
			writeSuccessfullOperationToReport(specimenCollectionGroup,updateValidateOperation);
			Logger.out.error(e.getMessage(),e);
	 		e.printStackTrace();
		}
	}
	
	private void testUpdateCollectionProtocolRegistration()
	{		
		CollectionProtocolRegistration collectionProtocolRegistration = (CollectionProtocolRegistration) dataModelObjectMap.get("CollectionProtocolRegistration");
		setLogger(collectionProtocolRegistration);
		Logger.out.info("updating domain object------->"+collectionProtocolRegistration);
		try 
		{
			APIDemo apiDemo = new APIDemo();
			apiDemo.updateCollectionProtocolRegistration(collectionProtocolRegistration);
			CollectionProtocolRegistration updatedCollectionProtocolRegistration = (CollectionProtocolRegistration) appService.updateObject(collectionProtocolRegistration);
			writeSuccessfullOperationToReport(updatedCollectionProtocolRegistration,updateOperation);
			Logger.out.info("Domain object successfully updated ---->"+updatedCollectionProtocolRegistration);
		} 
		catch (Exception e) {
			writeFailureOperationsToReport("CollectionProtocolRegistration",updateOperation);
			Logger.out.error(e.getMessage(),e);
	 		e.printStackTrace();
		}
	}
	
	private void testUpdateCollectionProtocolRegistrationWithWrongData()
	{		
		CollectionProtocolRegistration collectionProtocolRegistration = (CollectionProtocolRegistration) dataModelObjectMap.get("CollectionProtocolRegistration");
		setLogger(collectionProtocolRegistration);
		Logger.out.info("updating domain object------->"+collectionProtocolRegistration);
		try 
		{
			APIDemo apiDemo = new APIDemo();	
			
			collectionProtocolRegistration.setParticipant(null);
			collectionProtocolRegistration.setProtocolParticipantIdentifier(null);
			
			//apiDemo.updateCollectionProtocolRegistration(collectionProtocolRegistration);
			CollectionProtocolRegistration updatedCollectionProtocolRegistration = (CollectionProtocolRegistration) appService.updateObject(collectionProtocolRegistration);
			writeFailureOperationsToReport("CollectionProtocolRegistration",updateValidateOperation);
			//Logger.out.info("Domain object successfully updated ---->"+updatedCollectionProtocolRegistration);
		} 
		catch (Exception e) {
			writeSuccessfullOperationToReport(collectionProtocolRegistration,updateValidateOperation);
			Logger.out.error(e.getMessage(),e);
	 		e.printStackTrace();
		}
	}
	
	private void testUpdateSpecimen()
	{
		Specimen specimen = (Specimen)dataModelObjectMap.get("Specimen");
    	setLogger(specimen);
    	Logger.out.info("updating domain object------->"+specimen);
	    try 
		{
	    	APIDemo apiDemo = new APIDemo();
	    	apiDemo.updateSpecimen(specimen);
	    	Specimen updatedSpecimen = (Specimen) appService.updateObject(specimen);
	    	writeSuccessfullOperationToReport(updatedSpecimen,updateOperation);
	    	Logger.out.info("Domain object successfully updated ---->"+updatedSpecimen);
	    } 
	    catch (Exception e) {
	    	writeFailureOperationsToReport("Specimen",updateOperation);
	    	Logger.out.error(e.getMessage(),e);
	 		e.printStackTrace();
	    }
	}
	
	private void testUpdateSpecimenWithWrongData()
	{
		Specimen specimen = (Specimen)dataModelObjectMap.get("Specimen");
    	setLogger(specimen);
    	Logger.out.info("updating domain object------->"+specimen);
	    try 
		{
	    	APIDemo apiDemo = new APIDemo();	    	
	    	specimen.setPathologicalStatus("wrongData");
	    	
	    	//apiDemo.updateSpecimen(specimen);
	    	Specimen updatedSpecimen = (Specimen) appService.updateObject(specimen);
	    	writeFailureOperationsToReport("Specimen",updateValidateOperation);	    	
	    	//Logger.out.info("Domain object successfully updated ---->"+updatedSpecimen);
	    } 
	    catch (Exception e) {
	    	writeSuccessfullOperationToReport(specimen,updateValidateOperation);
	    	Logger.out.error(e.getMessage(),e);
	 		e.printStackTrace();
	    }
	}
	
	
	private void testUpdateStorageType()
	{
		StorageType storageType = (StorageType)dataModelObjectMap.get("StorageType");
    	setLogger(storageType);
    	Logger.out.info("updating domain object------->"+storageType);
	    try 
		{
	    	APIDemo apiDemo = new APIDemo();
	    	apiDemo.updateStorageType(storageType);
	    	StorageType updatedStorageType = (StorageType) appService.updateObject(storageType);
	    	writeSuccessfullOperationToReport(updatedStorageType,updateOperation);
	    	Logger.out.info("Domain object successfully updated ---->"+updatedStorageType);
	    } 
	    catch (Exception e) {
	    	writeFailureOperationsToReport("StorageType",updateOperation);
	    	Logger.out.error(e.getMessage(),e);
	 		e.printStackTrace();
	    }
	}	
	
	private void testUpdateStorageContainer()
	{
		StorageContainer storageContainer = (StorageContainer)dataModelObjectMap.get("StorageContainer");
    	setLogger(storageContainer);
    	Logger.out.info("updating domain object------->"+storageContainer);
    	Logger.out.info("updating domain object Storage Container ******* ------->"+storageContainer.getCapacity().getId());
	    try 
		{
	    	APIDemo apiDemo = new APIDemo();
	    	apiDemo.updateStorageContainer(storageContainer);
	    	StorageContainer updatedStorageContainer = (StorageContainer) appService.updateObject(storageContainer);
	    	writeSuccessfullOperationToReport(updatedStorageContainer,updateOperation);
	    	Logger.out.info("Domain object successfully updated ---->"+updatedStorageContainer);
	    } 
	    catch (Exception e) {
	    	writeFailureOperationsToReport("StorageContainer",updateOperation);
	    	Logger.out.error(e.getMessage(),e);
	 		e.printStackTrace();
	    }
	}	
	
	private void testUpdateSpecimenArrayType()
	{
		SpecimenArrayType specimenArrayType = (SpecimenArrayType)dataModelObjectMap.get("SpecimenArrayType");
    	setLogger(specimenArrayType);
    	Logger.out.info("updating domain object------->"+specimenArrayType);
	    try 
		{
	    	APIDemo apiDemo = new APIDemo();
	    	apiDemo.updateSpecimenArrayType(specimenArrayType);
	    	SpecimenArrayType updatedSpecimenArrayType = (SpecimenArrayType) appService.updateObject(specimenArrayType);
	    	writeSuccessfullOperationToReport(updatedSpecimenArrayType,updateOperation);
	    	Logger.out.info("Domain object successfully updated ---->"+updatedSpecimenArrayType);
	    } 
	    catch (Exception e) {
	    	writeFailureOperationsToReport("SpecimenArrayType",updateOperation);
	    	Logger.out.error(e.getMessage(),e);
	 		e.printStackTrace();
	    }
	}		
	
	private void testUpdateSpecimenArray()
	{
		SpecimenArray specimenArray = (SpecimenArray)dataModelObjectMap.get("SpecimenArray");
    	setLogger(specimenArray);
    	Logger.out.info("updating domain object------->"+specimenArray);
	    try 
		{
	    	APIDemo apiDemo = new APIDemo();
	    	apiDemo.updateSpecimenArray(specimenArray);
	    	SpecimenArray updatedSpecimenArray = (SpecimenArray) appService.updateObject(specimenArray);
	    	writeSuccessfullOperationToReport(updatedSpecimenArray,updateOperation);
	    	Logger.out.info("Domain object successfully updated ---->"+updatedSpecimenArray);
	    } 
	    catch (Exception e) {
	    	writeFailureOperationsToReport("SpecimenArray",updateOperation);
	    	Logger.out.error(e.getMessage(),e);
	 		e.printStackTrace();
	    }
	}		
	
	
	
	 private static void setLogger(Object object)
	{
			Logger.out = org.apache.log4j.Logger.getLogger(object.getClass());
	}

	private static void writeHeaderContentsToReport()
	{
			Date date = new Date();
			StringBuffer headerContent = new StringBuffer( tabSpacing + "\tNightly Build Report of caTissueCore run on Date " + date.toString() + newLine);
			headerContent.append(tabSpacing + " --------------------------------------------------------------------------------" + newLine);
			reportWriter.writeToFile(headerContent.toString());
	}
	
	/**
	 * 
	 */
	private static void writeFooterContentsToReport()
	{
			//StringBuffer headerContent = new StringBuffer(tabSpacing + "-----------------------------------------------------------------------------------" + newLine); 
			StringBuffer headerContent = new StringBuffer();
			headerContent.append(newLine);
			headerContent.append("   Summary of Nightly Build report " + newLine);
			headerContent.append("-------------------------------------" + newLine);
			headerContent.append("Total Operations Perfomed ==> " + totalOperations + newLine);
			headerContent.append("No. of Successful Operations ==> " + successfullOperations + " 		Ratio(%) ==> "+ calculateRatioInPercentage(successfullOperations) + "%"+newLine );
			headerContent.append("No. of  Failure Operations ==> " + failureOperations + " 		Ratio(%) ==> "+ calculateRatioInPercentage(failureOperations) + "%" +newLine );
			headerContent.append("-------------------------------------" + newLine);
			reportWriter.writeToFile(headerContent.toString());
			reportWriter.writeToFile(newLine);
			reportWriter.writeToFile(reportContents.toString());
	}
	
	/**
	 * @param operation
	 * @return
	 */
	private static double calculateRatioInPercentage(int operation)
	{
		return (((double) operation) / totalOperations) * 100; 
	}
	
	/**
	 * @param object
	 */
	private void writeSuccessfullOperationToReport(Object object,String operation)
	{
		successfullOperations++;
		totalOperations++;
		reportContents.append(object.getClass().getSimpleName() + separator + operation + separator + successMessage + newLine);
		//reportWriter.writeToFile(object.getClass().getSimpleName() + separator + operation + separator + successMessage + newLine);
	}
	
	/**
	 * @param objectName
	 */
	private void writeFailureOperationsToReport(String objectName,String operation)
	{
		failureOperations++;
		totalOperations++;
		reportContents.append(objectName + separator + operation + separator + failureMessage + newLine);
		//reportWriter.writeToFile(objectName + separator + operation + separator + failureMessage + newLine);
	}
	
	private void sendMail()
	{
		SendBuildReport report = SendBuildReport.getInstance();
		String to = "catissue@persistent.co.in";
		String from = "ashwin_gupta@persistent.co.in";
		String cc = "munesh_gupta@persistent.co.in";
		String host = "mail.persistent.co.in";
        String subject = "Nightly Build Report";
        String body = "nightly build report run for database MySQL";
        //String filePath = "F:/nightly_build/catissuecore/caTissueCore_caCORE_Client/log/catissuecoreclient.log";
        report.sendmail(to,cc,null,from,host,subject,body,filePath);
 	}

}


