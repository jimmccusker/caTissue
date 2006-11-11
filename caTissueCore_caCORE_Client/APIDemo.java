
import java.text.ParseException;
import java.util.Collection;
import java.util.HashSet;

import edu.wustl.catissuecore.domain.Address;
import edu.wustl.catissuecore.domain.Biohazard;
import edu.wustl.catissuecore.domain.CancerResearchGroup;
import edu.wustl.catissuecore.domain.Capacity;
import edu.wustl.catissuecore.domain.ClinicalReport;
import edu.wustl.catissuecore.domain.CollectionEventParameters;
import edu.wustl.catissuecore.domain.CollectionProtocol;
import edu.wustl.catissuecore.domain.CollectionProtocolEvent;
import edu.wustl.catissuecore.domain.CollectionProtocolRegistration;
import edu.wustl.catissuecore.domain.Department;
import edu.wustl.catissuecore.domain.DistributedItem;
import edu.wustl.catissuecore.domain.Distribution;
import edu.wustl.catissuecore.domain.DistributionProtocol;
import edu.wustl.catissuecore.domain.ExternalIdentifier;
import edu.wustl.catissuecore.domain.Institution;
import edu.wustl.catissuecore.domain.MolecularSpecimen;
import edu.wustl.catissuecore.domain.Participant;
import edu.wustl.catissuecore.domain.Quantity;
import edu.wustl.catissuecore.domain.ReceivedEventParameters;
import edu.wustl.catissuecore.domain.Site;
import edu.wustl.catissuecore.domain.Specimen;
import edu.wustl.catissuecore.domain.SpecimenArray;
import edu.wustl.catissuecore.domain.SpecimenArrayContent;
import edu.wustl.catissuecore.domain.SpecimenArrayType;
import edu.wustl.catissuecore.domain.SpecimenCharacteristics;
import edu.wustl.catissuecore.domain.SpecimenCollectionGroup;
import edu.wustl.catissuecore.domain.SpecimenRequirement;
import edu.wustl.catissuecore.domain.StorageContainer;
import edu.wustl.catissuecore.domain.StorageType;
import edu.wustl.catissuecore.domain.User;
import edu.wustl.common.util.Utility;
import edu.wustl.common.util.global.Constants;

/**
 * @author ashish_gupta
 *
 */
public class APIDemo
{

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception
	{
		/*
		Variables.applicationHome = System.getProperty("user.dir");
		Logger.out = org.apache.log4j.Logger.getLogger("");
		PropertyConfigurator.configure(Variables.applicationHome + "\\WEB-INF\\src\\"
				+ "ApplicationResources.properties");

		System
				.setProperty("gov.nih.nci.security.configFile",
						"C:/jboss-4.0.0/server/default/catissuecore-properties/ApplicationSecurityConfig.xml");
		System
				.setProperty("app.propertiesFile",
						"C:/jboss-4.0.0/server/default/catissuecore-properties/caTissueCore_Properties.xml");
		CDEManager.init();
		XMLPropertyHandler.init("caTissueCore_Properties.xml");
		ApplicationProperties.initBundle("ApplicationResources");

		APIDemo apiDemo = new APIDemo();
		SessionDataBean sessionDataBean = new SessionDataBean();
		sessionDataBean.setUserName("admin@admin.com");

		BizLogicFactory bizLogicFactory = BizLogicFactory.getInstance();

		//				Department dept = apiDemo.initDepartment();
		//				apiDemo.addData(bizLogicFactory, sessionDataBean, dept);
		//		
		//				Biohazard hazardObj=apiDemo.initBioHazard();
		//				apiDemo.addData(bizLogicFactory, sessionDataBean, hazardObj);
		//		
		//				CancerResearchGroup cancerResearchGroup = apiDemo.initCancerResearchGroup();
		//				apiDemo.addData(bizLogicFactory, sessionDataBean, cancerResearchGroup);
		//		
		//				Institution institution = apiDemo.initInstitution();
		//				apiDemo.addData(bizLogicFactory, sessionDataBean, institution);
		//		
		//				Site site = apiDemo.initSite();
		//				apiDemo.addData(bizLogicFactory, sessionDataBean, site);
		//		
		//				StorageType storageType = apiDemo.initStorageType();
		//				apiDemo.addData(bizLogicFactory, sessionDataBean, storageType);
		//		
		//				SpecimenArrayType specimenArrayType = apiDemo.initSpecimenArrayType();
		//				apiDemo.addData(bizLogicFactory, sessionDataBean, specimenArrayType);
		//		
		//				User user = apiDemo.initUser();
		//				apiDemo.addData(bizLogicFactory, sessionDataBean, user);
		//		
		//				Participant participant = apiDemo.initParticipant();
		//				apiDemo.addData(bizLogicFactory, sessionDataBean, participant);
		//		
		//				StorageContainer storageContainer = apiDemo.initStorageContainer();
		//				apiDemo.addData(bizLogicFactory, sessionDataBean, storageContainer);
		//		
		//				CollectionProtocol collectionProtocol = apiDemo.initCollectionProtocol();
		//				apiDemo.addData(bizLogicFactory, sessionDataBean, collectionProtocol);
		//		
		//				DistributionProtocol distributionProtocol = apiDemo.initDistributionProtocol();
		//				apiDemo.addData(bizLogicFactory, sessionDataBean, distributionProtocol);
		//		
		//			    CollectionProtocolRegistration collectionProtocolRegistration = apiDemo.initCollectionProtocolRegistration();
		//				apiDemo.addData(bizLogicFactory, sessionDataBean, collectionProtocolRegistration);

		//				SpecimenCollectionGroup specimenCollectionGroup = apiDemo.initSpecimenCollectionGroup();
		//				apiDemo.addData(bizLogicFactory, sessionDataBean, specimenCollectionGroup);

//						Specimen specimen = apiDemo.initSpecimen();
//						apiDemo.addData(bizLogicFactory, sessionDataBean, specimen);

						Distribution distribution = apiDemo.initDistribution();
						apiDemo.addData(bizLogicFactory, sessionDataBean, distribution);
		*/				
	}

	/**
	 * @param bizLogicFactory
	 * @param sessionDataBean
	 * @param obj
	 * @throws Exception
	private void addData(BizLogicFactory bizLogicFactory, SessionDataBean sessionDataBean,
			Object obj) throws Exception
	{
		IBizLogic bizLogic = bizLogicFactory.getBizLogic(obj.getClass().getName());
		bizLogic.insert(obj, sessionDataBean, Constants.HIBERNATE_DAO);
	}
	 */

	/**
	 * @return Department
	 */
	public Department initDepartment()
	{
		Department dept = new Department();
		dept.setName("dt" + UniqueKeyGeneratorUtil.getUniqueKey());
		return dept;
	}

	/**
	 * @return Biohazard
	 */
	public Biohazard initBioHazard()
	{
		Biohazard bioHazard = new Biohazard();
		bioHazard.setComments("NueroToxicProtein");
		bioHazard.setName("bh" + UniqueKeyGeneratorUtil.getUniqueKey());
		bioHazard.setType("Toxic");
		return bioHazard;
	}

	/**
	 * @return CancerResearchGroup
	 */
	public CancerResearchGroup initCancerResearchGroup()
	{
		CancerResearchGroup cancerResearchGroup = new CancerResearchGroup();
		cancerResearchGroup.setName("crg" + UniqueKeyGeneratorUtil.getUniqueKey());
		return cancerResearchGroup;
	}

	/**
	 * @return Institution
	 */
	public Institution initInstitution()
	{
		Institution institutionObj = new Institution();
		institutionObj.setName("inst" + UniqueKeyGeneratorUtil.getUniqueKey());
		return institutionObj;
	}

	/**
	 * @return User
	 */
	public User initUser()
	{
		User userObj = new User();
		userObj.setEmailAddress(UniqueKeyGeneratorUtil.getUniqueKey()+ "@admin.com");
		userObj.setLoginName(userObj.getEmailAddress());
		userObj.setLastName("last" + UniqueKeyGeneratorUtil.getUniqueKey());
		userObj.setFirstName("name" + UniqueKeyGeneratorUtil.getUniqueKey());

		Address address = new Address();
		address.setStreet("Main street");
		address.setCity("New hampshier");
		address.setState("D.C.");
		address.setZipCode("12345");
		address.setCountry("United States");
		address.setPhoneNumber("21222324");
		address.setFaxNumber("21222324");		
 
		userObj.setAddress(address);

//		Institution institution = new Institution();
//		institution.setId(new Long(1));
		Institution institution = (Institution) ClientDemo.dataModelObjectMap.get("Institution");
		userObj.setInstitution(institution);

//		Department department = new Department();
//		department.setId(new Long(1));
		Department department = (Department)ClientDemo.dataModelObjectMap.get("Department");
		userObj.setDepartment(department);

//		CancerResearchGroup cancerResearchGroup = new CancerResearchGroup();
//		cancerResearchGroup.setId(new Long(1));
		CancerResearchGroup cancerResearchGroup = (CancerResearchGroup)ClientDemo.dataModelObjectMap.get("CancerResearchGroup");
		userObj.setCancerResearchGroup(cancerResearchGroup);

		//userObj.setRoleId("1");
		//userObj.setComments("");
		userObj.setPageOf(Constants.PAGEOF_SIGNUP);
		//userObj.setActivityStatus("Active");
		//userObj.setCsmUserId(new Long(1));
		//userObj.setFirstTimeLogin(Boolean.valueOf(false));
		return userObj;
	}
	
	public User initAdminUser()
	{
		User userObj = new User();
		userObj.setEmailAddress(UniqueKeyGeneratorUtil.getUniqueKey()+ "@admin.com");
		userObj.setLoginName(userObj.getEmailAddress());
		userObj.setLastName("last" + UniqueKeyGeneratorUtil.getUniqueKey());
		userObj.setFirstName("name" + UniqueKeyGeneratorUtil.getUniqueKey());

		Address address = new Address();
		address.setStreet("Main street");
		address.setCity("New hampshier");
		address.setState("D.C.");
		address.setZipCode("12345");
		address.setCountry("United States");
		address.setPhoneNumber("21222324");
		address.setFaxNumber("21222324");		
 
		userObj.setAddress(address);

//		Institution institution = new Institution();
//		institution.setId(new Long(1));
		Institution institution = (Institution) ClientDemo.dataModelObjectMap.get("Institution");
		userObj.setInstitution(institution);

//		Department department = new Department();
//		department.setId(new Long(1));
		Department department = (Department)ClientDemo.dataModelObjectMap.get("Department");
		userObj.setDepartment(department);

//		CancerResearchGroup cancerResearchGroup = new CancerResearchGroup();
//		cancerResearchGroup.setId(new Long(1));
		CancerResearchGroup cancerResearchGroup = (CancerResearchGroup)ClientDemo.dataModelObjectMap.get("CancerResearchGroup");
		userObj.setCancerResearchGroup(cancerResearchGroup);

		userObj.setRoleId("1");
		userObj.setActivityStatus("Active");
		//userObj.setComments("");
		userObj.setPageOf(Constants.PAGEOF_USER_ADMIN);		
		//userObj.setCsmUserId(new Long(1));
		//userObj.setFirstTimeLogin(Boolean.valueOf(false));
		return userObj;
	}

	/**
	 * @return StorageType
	 */
	public StorageType initStorageType()
	{
		StorageType storageTypeObj = new StorageType();
		Capacity capacity = new Capacity();

		storageTypeObj.setName("st" + UniqueKeyGeneratorUtil.getUniqueKey());
		storageTypeObj.setDefaultTempratureInCentigrade(new Double(-30));
		storageTypeObj.setOneDimensionLabel("label 1");
		storageTypeObj.setTwoDimensionLabel("label 2");

		capacity.setOneDimensionCapacity(new Integer(3));
		capacity.setTwoDimensionCapacity(new Integer(3));
		storageTypeObj.setCapacity(capacity);
		//storageTypeObj.setId(new Long(20));

		Collection holdsStorageTypeCollection = new HashSet();
		holdsStorageTypeCollection.add(storageTypeObj);

		storageTypeObj.setHoldsStorageTypeCollection(holdsStorageTypeCollection);
		storageTypeObj.setActivityStatus("Active");

		Collection holdsSpecimenClassCollection = new HashSet();
		holdsSpecimenClassCollection.add("Tissue");
		holdsSpecimenClassCollection.add("Fluid");
		holdsSpecimenClassCollection.add("Molecular");
		storageTypeObj.setHoldsSpecimenClassCollection(holdsSpecimenClassCollection);
		return storageTypeObj;
	}

	/**
	 * @return SpecimenArrayType
	 */
	public SpecimenArrayType initSpecimenArrayType()
	{
		SpecimenArrayType specimenArrayType = new SpecimenArrayType();
		specimenArrayType.setName("sat" + UniqueKeyGeneratorUtil.getUniqueKey());
		specimenArrayType.setSpecimenClass("Molecular");

		Collection specimenTypeCollection = new HashSet();
		specimenTypeCollection.add("DNA");
		specimenTypeCollection.add("RNA");
		specimenArrayType.setSpecimenTypeCollection(specimenTypeCollection);
		specimenArrayType.setComment("");
		Capacity capacity = new Capacity();
		capacity.setOneDimensionCapacity(new Integer(4));
		capacity.setTwoDimensionCapacity(new Integer(4));
		specimenArrayType.setCapacity(capacity);
		return specimenArrayType;
	}

	/**
	 * @return StorageContainer
	 */
	public StorageContainer initStorageContainer()
	{
		StorageContainer storageContainer = new StorageContainer();
		storageContainer.setName("sc" + UniqueKeyGeneratorUtil.getUniqueKey());

		StorageType storageType = (StorageType) ClientDemo.dataModelObjectMap.get("StorageType");
		/*	
		new StorageType();
		storageType.setId(new Long(4));
		*/
		storageContainer.setStorageType(storageType);
		Site site = (Site) ClientDemo.dataModelObjectMap.get("Site"); 
		/*new Site();
		site.setId(new Long(1));
		*/
		storageContainer.setSite(site);

		Integer conts = new Integer(1);
		storageContainer.setNoOfContainers(conts);
		storageContainer.setTempratureInCentigrade(new Double(-30));
		storageContainer.setBarcode("barc" + UniqueKeyGeneratorUtil.getUniqueKey());

		Capacity capacity = new Capacity();
		capacity.setOneDimensionCapacity(new Integer(1));
		capacity.setTwoDimensionCapacity(new Integer(2));
		storageContainer.setCapacity(capacity);

		CollectionProtocol collectionProtocol = (CollectionProtocol) ClientDemo.dataModelObjectMap.get("CollectionProtocol");
		
		/*	
		new CollectionProtocol();
		collectionProtocol.setId(new Long(3));
		*/
		Collection collectionProtocolCollection = new HashSet();
		collectionProtocolCollection.add(collectionProtocol);
		storageContainer.setCollectionProtocolCollection(collectionProtocolCollection);

		Collection holdsStorageTypeCollection = new HashSet();
		holdsStorageTypeCollection.add(storageType);
		storageContainer.setHoldsStorageTypeCollection(holdsStorageTypeCollection);

		Collection holdsSpecimenClassCollection = new HashSet();
		holdsSpecimenClassCollection.add("Tissue");
		holdsSpecimenClassCollection.add("Molecular");
		storageContainer.setHoldsSpecimenClassCollection(holdsSpecimenClassCollection);
		
/*		Container parent = new Container();
		parent.setId(new Long(2));
		storageContainer.setParent(parent);    
*/
		storageContainer.setPositionDimensionOne(new Integer(1));
		storageContainer.setPositionDimensionTwo(new Integer(2));

		storageContainer.setActivityStatus("Active");
		storageContainer.setFull(Boolean.valueOf(false));
		return storageContainer;
	}

	/**
	 * @return Site
	 */
	public Site initSite()
	{
		Site siteObj = new Site();
//		User userObj = new User();
//		userObj.setId(new Long(1));
		User userObj = (User) ClientDemo.dataModelObjectMap.get("User");

		siteObj.setEmailAddress("admin@admin.com");
		siteObj.setName("sit" + UniqueKeyGeneratorUtil.getUniqueKey());
		siteObj.setType("Laboratory");
		siteObj.setActivityStatus("Active");
		siteObj.setCoordinator(userObj);
		
	 
		Address addressObj = new Address();
		addressObj.setCity("Saint Louis");
		addressObj.setCountry("United States");
		addressObj.setFaxNumber("555-55-5555");
		addressObj.setPhoneNumber("123678");
		addressObj.setState("Missouri");
		addressObj.setStreet("4939 Children's Place");
		addressObj.setZipCode("63110");
		siteObj.setAddress(addressObj);
		return siteObj;
	}

	/**
	 * @return Participant
	 */
	public Participant initParticipant()
	{
		Participant participant = new Participant();
		participant.setLastName("last" + UniqueKeyGeneratorUtil.getUniqueKey());
		participant.setFirstName("frst" + UniqueKeyGeneratorUtil.getUniqueKey());
		participant.setMiddleName("mdl" + UniqueKeyGeneratorUtil.getUniqueKey());

		/*try
		{
			System.out.println("-----------------------");
			participant.setBirthDate(Utility.parseDate("08/15/1975", Utility
					.datePattern("08/15/1975")));
		}
		catch (ParseException e)
		{
			// TODO Auto-generated catch block
			System.out.println("-----------------------"+e);
			e.printStackTrace();
		}		
		try
		{
			System.out.println("-----------------------");
			participant.setDeathDate(Utility.parseDate("08/15/1974", Utility
					.datePattern("08/15/1974")));
		}
		catch (ParseException e)
		{
			// TODO Auto-generated catch block
			System.out.println("-----------------------"+e);
			e.printStackTrace();
		}*/
		
		participant.setVitalStatus("Dead");
		participant.setGender("Female");
		participant.setSexGenotype("XX");

		Collection raceCollection = new HashSet();
		raceCollection.add("White");
		raceCollection.add("Asian");
		participant.setRaceCollection(raceCollection);
		participant.setActivityStatus("Active");
		participant.setEthnicity("Hispanic or Latino");
		//participant.setSocialSecurityNumber("333-33-3333");

//		Collection participantMedicalIdentifierCollection = new HashSet();
//		/*participantMedicalIdentifierCollection.add("Washington University School of Medicine");
//		 participantMedicalIdentifierCollection.add("1111");
//		 */
//		participant.setParticipantMedicalIdentifierCollection(participantMedicalIdentifierCollection);
		return participant;
	}

	/**
	 * @return CollectionProtocol
	 */
	public CollectionProtocol initCollectionProtocol()
	{
		CollectionProtocol collectionProtocol = new CollectionProtocol();

		collectionProtocol.setAliqoutInSameContainer(new Boolean(true));
		collectionProtocol.setDescriptionURL("");
		collectionProtocol.setActivityStatus("Active");
		collectionProtocol.setEndDate(null);
		collectionProtocol.setEnrollment(null);
		collectionProtocol.setIrbIdentifier("77777");
		collectionProtocol.setTitle("cp" + UniqueKeyGeneratorUtil.getUniqueKey());
		collectionProtocol.setShortTitle("pc!");
		
		try
		{
			collectionProtocol.setStartDate(Utility.parseDate("08/15/1975", Utility
					.datePattern("08/15/1975")));
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}

		Collection collectionProtocolEventCollectionSet = new HashSet();
	 
//		CollectionProtocolEvent collectionProtocolEvent = new CollectionProtocolEvent();
		CollectionProtocolEvent collectionProtocolEvent = (CollectionProtocolEvent)ClientDemo.dataModelObjectMap.get("CollectionProtocolEvent"); 
		collectionProtocolEvent.setClinicalStatus("New Diagnosis");
		collectionProtocolEvent.setStudyCalendarEventPoint(new Double(1));
	 

		Collection specimenRequirementCollection = new HashSet();
//		SpecimenRequirement specimenRequirement = new SpecimenRequirement();
//		specimenRequirement.setSpecimenClass("Molecular");
//		specimenRequirement.setSpecimenType("DNA");
//		specimenRequirement.setTissueSite("Placenta");
//		specimenRequirement.setPathologyStatus("Malignant");
//		Quantity quantity = new Quantity();
//		quantity.setValue(new Double(10));
//		specimenRequirement.setQuantity(quantity);
		
		SpecimenRequirement specimenRequirement  =(SpecimenRequirement)ClientDemo.dataModelObjectMap.get("SpecimenRequirement");
		specimenRequirementCollection.add(specimenRequirement);
		
		collectionProtocolEvent.setSpecimenRequirementCollection(specimenRequirementCollection);

		collectionProtocolEventCollectionSet.add(collectionProtocolEvent);
		collectionProtocol
				.setCollectionProtocolEventCollection(collectionProtocolEventCollectionSet);

//		User principalInvestigator = new User();
//		principalInvestigator.setId(new Long(1));
		User principalInvestigator = (User)ClientDemo.dataModelObjectMap.get("User");
		collectionProtocol.setPrincipalInvestigator(principalInvestigator);
		
//		User protocolCordinator = new User();
//		protocolCordinator.setId(new Long(principalInvestigator.getId().longValue()-1));
		User protocolCordinator = (User)ClientDemo.dataModelObjectMap.get("User1");
		Collection protocolCordinatorCollection = new HashSet();
		protocolCordinatorCollection.add(protocolCordinator);
		collectionProtocol.setUserCollection(protocolCordinatorCollection);
		return collectionProtocol;
	}

	/**
	 * @return Specimen
	 */
	public Specimen initSpecimen()
	{
		MolecularSpecimen molecularSpecimen = new MolecularSpecimen();

//		SpecimenCollectionGroup specimenCollectionGroup = new SpecimenCollectionGroup();
//		specimenCollectionGroup.setId(new Long(2));
		SpecimenCollectionGroup specimenCollectionGroup = (SpecimenCollectionGroup)ClientDemo.dataModelObjectMap.get("SpecimenCollectionGroup");
		molecularSpecimen.setSpecimenCollectionGroup(specimenCollectionGroup);
		
		molecularSpecimen.setLabel("spec" + UniqueKeyGeneratorUtil.getUniqueKey());
		molecularSpecimen.setBarcode("bar" + UniqueKeyGeneratorUtil.getUniqueKey());
		molecularSpecimen.setType("DNA");
		molecularSpecimen.setAvailable(new Boolean(true));
		molecularSpecimen.setActivityStatus("Active");
//		SpecimenCharacteristics specimenCharacteristics = new SpecimenCharacteristics();
//		specimenCharacteristics.setTissueSide("Left");
//		specimenCharacteristics.setTissueSite("Placenta");
//		specimenCharacteristics.setId(new Long(1));
		SpecimenCharacteristics specimenCharacteristics = (SpecimenCharacteristics)ClientDemo.dataModelObjectMap.get("SpecimenCharacteristics");
		molecularSpecimen.setSpecimenCharacteristics(specimenCharacteristics);
		molecularSpecimen.setPathologicalStatus("Malignant");

		Quantity quantity = new Quantity();
		quantity.setValue(new Double(10));
		molecularSpecimen.setQuantity(quantity);
		molecularSpecimen.setAvailableQuantity(quantity);
		molecularSpecimen.setConcentrationInMicrogramPerMicroliter(new Double(10));
		molecularSpecimen.setComments("");
		molecularSpecimen.setLineage("Aliquot");
		// Is virtually located
		
//		StorageContainer sto = new StorageContainer();
//		sto.setId(new Long("1"));
		
		molecularSpecimen.setStorageContainer(null); 
		molecularSpecimen.setPositionDimensionOne(null);
		molecularSpecimen.setPositionDimensionTwo(null);
		
		Collection externalIdentifierCollection = new HashSet();
		ExternalIdentifier externalIdentifier = new ExternalIdentifier();
		externalIdentifier.setName("eid" + UniqueKeyGeneratorUtil.getUniqueKey());
		externalIdentifier.setValue("11");
		externalIdentifier.setSpecimen(molecularSpecimen);
		
		externalIdentifierCollection.add(externalIdentifier);
		molecularSpecimen.setExternalIdentifierCollection(externalIdentifierCollection);
		CollectionEventParameters collectionEventParameters = new CollectionEventParameters();
		collectionEventParameters.setComments("");
//		User user = new User();
//		user.setId(new Long(1));
	 //	collectionEventParameters.setId(new Long(0));
		User user = (User)ClientDemo.dataModelObjectMap.get("User");	
		collectionEventParameters.setUser(user);
		
		try
		{
			collectionEventParameters.setTimestamp(Utility.parseDate("08/15/1975", Utility
					.datePattern("08/15/1975")));
					
		}
		catch (ParseException e1)
		{
			System.out.println(" exception in APIDemo");
			e1.printStackTrace();
		}
		
		collectionEventParameters.setContainer("No Additive Vacutainer");
		collectionEventParameters.setCollectionProcedure("Needle Core Biopsy");
		ReceivedEventParameters receivedEventParameters = new ReceivedEventParameters();
		receivedEventParameters.setUser(user);
		//receivedEventParameters.setId(new Long(0));
		
		try
		{
			System.out.println("--- Start ---- 10");
			receivedEventParameters.setTimestamp(Utility.parseDate("08/15/1975", Utility
					.datePattern("08/15/1975")));
		}
		catch (ParseException e)
		{
			System.out.println("APIDemo");
			e.printStackTrace();
		}
		
		receivedEventParameters.setReceivedQuality("Acceptable");
		receivedEventParameters.setComments("");
		Collection specimenEventCollection = new HashSet();
		specimenEventCollection.add(collectionEventParameters);
		specimenEventCollection.add(receivedEventParameters);
		molecularSpecimen.setSpecimenEventCollection(specimenEventCollection);

//		Biohazard biohazard = new Biohazard();
//		biohazard.setName("Biohazard1");
//		biohazard.setType("Toxic");
//		biohazard.setId(new Long(1));
		Biohazard biohazard = (Biohazard)ClientDemo.dataModelObjectMap.get("Biohazard");
		Collection biohazardCollection = new HashSet();
		biohazardCollection.add(biohazard);
		molecularSpecimen.setBiohazardCollection(biohazardCollection);
		System.out.println(" -------- end -----------");

		return molecularSpecimen;
	}

	/**
	 * @return SpecimenCollectionGroup
	 */
	public SpecimenCollectionGroup initSpecimenCollectionGroup()
	{
		SpecimenCollectionGroup specimenCollectionGroup = new SpecimenCollectionGroup();

//		Site site = new Site();
//		site.setId(new Long(1));
		Site site = (Site)ClientDemo.dataModelObjectMap.get("Site");
		specimenCollectionGroup.setSite(site);

		specimenCollectionGroup.setClinicalDiagnosis("Abdominal fibromatosis");
		specimenCollectionGroup.setClinicalStatus("Operative");
		specimenCollectionGroup.setActivityStatus("Active");

//		CollectionProtocolEvent collectionProtocol = new CollectionProtocolEvent();
//		collectionProtocol.setId(new Long(1));
		CollectionProtocolEvent collectionProtocolEvent = (CollectionProtocolEvent)ClientDemo.dataModelObjectMap.get("CollectionProtocolEvent");	
		specimenCollectionGroup.setCollectionProtocolEvent(collectionProtocolEvent);

//		CollectionProtocolRegistration collectionProtocolRegistration = new CollectionProtocolRegistration();

//		Participant participant = new Participant();
//		participant.setId(new Long(1));
	
//		collectionProtocolRegistration.setParticipant(participant);
//		collectionProtocolRegistration.setId(new Long(1));
		
		
		//collectionProtocolRegistration.setProtocolParticipantIdentifier("");
		CollectionProtocolRegistration collectionProtocolRegistration = (CollectionProtocolRegistration)ClientDemo.dataModelObjectMap.get("CollectionProtocolRegistration");
		
//		CollectionProtocolRegistration collectionProtocolRegistration = new CollectionProtocolRegistration();
//		CollectionProtocol collectionProtocol = new CollectionProtocol();
//		collectionProtocol.setId(new Long("1000"));
//		Participant participant = new Participant();
//		participant.setId(new Long("1000"));
//		collectionProtocolRegistration.setCollectionProtocol(collectionProtocol);
//		collectionProtocolRegistration.setParticipant(participant);
		specimenCollectionGroup.setCollectionProtocolRegistration(collectionProtocolRegistration);

		specimenCollectionGroup.setName("scg" + UniqueKeyGeneratorUtil.getUniqueKey());

		ClinicalReport clinicalReport = new ClinicalReport();
		clinicalReport.setSurgicalPathologyNumber("");
		//clinicalReport.setId(new Long(1));
		specimenCollectionGroup.setClinicalReport(clinicalReport);

		return specimenCollectionGroup;
	}

	/**
	 * @return Distribution
	 */
	public Distribution initDistribution()
	{
		Distribution distribution = new Distribution();

		distribution.setActivityStatus("Active");

		Specimen specimen = (Specimen) ClientDemo.dataModelObjectMap.get("Specimen");
		
		/*
		= new MolecularSpecimen();
	//	specimen.setBarcode("");
	//	specimen.setLabel("new label");
		specimen.setId(new Long(10));
		Quantity quantity = new Quantity();
		quantity.setValue(new Double(15));
		specimen.setAvailableQuantity(quantity);
		*/
		
		
		
		DistributedItem distributedItem = new DistributedItem();
		distributedItem.setQuantity(new Double(2));
		distributedItem.setSpecimen(specimen);
		Collection distributedItemCollection = new HashSet();
		distributedItemCollection.add(distributedItem);
		distribution.setDistributedItemCollection(distributedItemCollection);
		
		DistributionProtocol distributionProtocol = (DistributionProtocol) ClientDemo.dataModelObjectMap.get("DistributionProtocol");		
		distribution.setDistributionProtocol(distributionProtocol);
		
		Site toSite = (Site) ClientDemo.dataModelObjectMap.get("Site");
		//toSite.setId(new Long("1000"));
		distribution.setToSite(toSite);
		/*	
		new Site();
		toSite.setId(new Long(1));
		distribution.setToSite(toSite);
		*/
		/*
		try
		{
			distribution.setTimestamp(Utility.parseDate("08/15/1975", Utility
					.datePattern("08/15/1975")));
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		*/
		distribution.setComments("");

		User user = (User) ClientDemo.dataModelObjectMap.get("User");	
		/*	
		new User();
		user.setId(new Long(1));
		*/
		distribution.setUser(user);
	 
		return distribution;
	}

	/**
	 * @return DistributionProtocol
	 */
	public DistributionProtocol initDistributionProtocol()
	{
		DistributionProtocol distributionProtocol = new DistributionProtocol();

		User principalInvestigator = (User) ClientDemo.dataModelObjectMap.get("User");
		/*	
		new User();
		principalInvestigator.setId(new Long(1));
		*/
		distributionProtocol.setPrincipalInvestigator(principalInvestigator);
		distributionProtocol.setTitle("DP"+ UniqueKeyGeneratorUtil.getUniqueKey());
		distributionProtocol.setShortTitle("DP1");
		distributionProtocol.setIrbIdentifier("55555");
		
		try
		{
			distributionProtocol.setStartDate(Utility.parseDate("08/15/1975", Utility
					.datePattern("08/15/1975")));
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		
		distributionProtocol.setDescriptionURL("");
		distributionProtocol.setEnrollment(new Integer(10));

		SpecimenRequirement specimenRequirement = (SpecimenRequirement) ClientDemo.dataModelObjectMap.get("SpecimenRequirement");
		/*	
		new SpecimenRequirement();
		specimenRequirement.setPathologyStatus("Malignant");
		specimenRequirement.setTissueSite("Placenta");
		specimenRequirement.setSpecimenType("DNA");
		specimenRequirement.setSpecimenClass("Molecular");
		Quantity quantity = new Quantity();
		quantity.setValue(new Double(10));
		specimenRequirement.setQuantity(quantity);
		*/
			
		Collection specimenRequirementCollection = new HashSet();
		specimenRequirementCollection.add(specimenRequirement);
		distributionProtocol.setSpecimenRequirementCollection(specimenRequirementCollection);

		distributionProtocol.setActivityStatus("Active");
		return distributionProtocol;
	}

	/**
	 * @return CollectionProtocolRegistration
	 */
	public CollectionProtocolRegistration initCollectionProtocolRegistration()
	{
		CollectionProtocolRegistration collectionProtocolRegistration = new CollectionProtocolRegistration();

//		CollectionProtocol collectionProtocol = new CollectionProtocol();
//		collectionProtocol.setId(new Long(1));
		CollectionProtocol collectionProtocol = (CollectionProtocol)ClientDemo.dataModelObjectMap.get("CollectionProtocol");
		collectionProtocolRegistration.setCollectionProtocol(collectionProtocol);

//		Participant participant = new Participant();
//		participant.setId(new Long(1));
		Participant participant = (Participant)ClientDemo.dataModelObjectMap.get("Participant");
		collectionProtocolRegistration.setParticipant(participant);

		collectionProtocolRegistration.setProtocolParticipantIdentifier("");
		collectionProtocolRegistration.setActivityStatus("Active");
		/*
		try
		{
			collectionProtocolRegistration.setRegistrationDate(Utility.parseDate("08/15/1975",
					Utility.datePattern("08/15/1975")));
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		*/
		return collectionProtocolRegistration;
	}
	public SpecimenArray initSpecimenArray()
	{
		SpecimenArray specimenArray = new SpecimenArray();
		
		SpecimenArrayType specimenArrayType = (SpecimenArrayType) ClientDemo.dataModelObjectMap.get("SpecimenArrayType");
		/*	
		new SpecimenArrayType();
		specimenArrayType.setId(new Long(9));
		*/
		specimenArray.setSpecimenArrayType(specimenArrayType);
		
		specimenArray.setBarcode("bar" + UniqueKeyGeneratorUtil.getUniqueKey());
		specimenArray.setName("sa" + UniqueKeyGeneratorUtil.getUniqueKey()); 
		
		User createdBy = (User) ClientDemo.dataModelObjectMap.get("User");
		/*
		new User();
		createdBy.setId(new Long(1));
		*/
		specimenArray.setCreatedBy(createdBy);
		
		Capacity capacity = specimenArrayType.getCapacity();
/*		capacity.setOneDimensionCapacity(1);
		capacity.setTwoDimensionCapacity(2);
*/		specimenArray.setCapacity(capacity);
		
		specimenArray.setComment("");
		StorageContainer storageContainer = (StorageContainer) ClientDemo.dataModelObjectMap.get("StorageContainer");
		/*	
		new StorageContainer();
		storageContainer.setId(new Long(1));		
		storageContainer=new StorageContainer();
		storageContainer.setId(new Long(1));
		*/
		specimenArray.setStorageContainer(storageContainer);
		specimenArray.setPositionDimensionOne(new Integer(1));
		specimenArray.setPositionDimensionTwo(new Integer(1));
		
		Collection specimenArrayContentCollection = new HashSet();
		SpecimenArrayContent specimenArrayContent = new SpecimenArrayContent();
		
		Specimen specimen = (Specimen) ClientDemo.dataModelObjectMap.get("Specimen");
		
/*		specimen.setLabel("Specimen 12");
		//specimen.setType("DNA");
		specimen.setId(new Long(10));
*/		
		specimenArrayContent.setSpecimen(specimen);
		specimenArrayContent.setPositionDimensionOne(new Integer(1));
		specimenArrayContent.setPositionDimensionTwo(new Integer(1));
		Quantity quantity = new Quantity();
		quantity.setValue(new Double(2));
		specimenArrayContent.setInitialQuantity(quantity);
		specimenArrayContentCollection.add(specimenArrayContent);
		specimenArray.setSpecimenArrayContentCollection(specimenArrayContentCollection);
		return specimenArray;
	}
	
	public SpecimenCharacteristics initSpecimenCharacteristics()
	{
		SpecimenCharacteristics specimenCharacteristics = new SpecimenCharacteristics();
		specimenCharacteristics.setTissueSide("Left");
		specimenCharacteristics.setTissueSite("Placenta");
		specimenCharacteristics.setId(new Long(1));
		
		return specimenCharacteristics;
	}
	public SpecimenRequirement initSpecimenRequirement()
	{
		SpecimenRequirement specimenRequirement = new SpecimenRequirement();
		specimenRequirement.setSpecimenClass("Molecular");
		specimenRequirement.setSpecimenType("DNA");
		specimenRequirement.setTissueSite("Placenta");
		specimenRequirement.setPathologyStatus("Malignant");
		Quantity quantity = new Quantity();
		quantity.setValue(new Double(10));
		specimenRequirement.setQuantity(quantity);
		return specimenRequirement;
	}
	public CollectionProtocolEvent initCollectionProtocolEvent()
	{
		CollectionProtocolEvent collectionProtocolEvent = new CollectionProtocolEvent();
		collectionProtocolEvent.setId(new Long(1));
		return collectionProtocolEvent;
	}
	
	//Update methods starts
	public void updateInstitution(Institution institution)
	{
		institution.setName("inst"+UniqueKeyGeneratorUtil.getUniqueKey());
	}
	
	public void updateDepartment(Department department)
	{
		department.setName("dt"+UniqueKeyGeneratorUtil.getUniqueKey());
	}
	
	public void updateCancerResearchGroup(CancerResearchGroup cancerResearchGroup)
	{
		cancerResearchGroup.setName("crg"+UniqueKeyGeneratorUtil.getUniqueKey());		
	}
	
	public void updateBiohazard(Biohazard bioHazard)
	{
		bioHazard.setComments("Radioactive");
		bioHazard.setName("bh" + UniqueKeyGeneratorUtil.getUniqueKey());
		bioHazard.setType("Radioactive"); //Toxic
	}
	
	public void updateSite(Site siteObj)
	{
		siteObj.setEmailAddress("admin1@admin.com");
		siteObj.setName("sit" + UniqueKeyGeneratorUtil.getUniqueKey());
		siteObj.setType("Repository");
		siteObj.setActivityStatus("Active");		
		siteObj.getAddress().setCity("Saint Louis1");
		siteObj.getAddress().setCountry("United States");
		siteObj.getAddress().setFaxNumber("555-55-55551");
		siteObj.getAddress().setPhoneNumber("1236781");
		siteObj.getAddress().setState("Missouri");
		siteObj.getAddress().setStreet("4939 Children's Place1");
		siteObj.getAddress().setZipCode("63111");		
	}
	
	public void updateCollectionProtocolRegistration(CollectionProtocolRegistration collectionProtocolRegistration)
	{		
		CollectionProtocol collectionProtocol = (CollectionProtocol)ClientDemo.dataModelObjectMap.get("CollectionProtocol");
		collectionProtocolRegistration.setCollectionProtocol(collectionProtocol);

		Participant participant = (Participant)ClientDemo.dataModelObjectMap.get("Participant");
		collectionProtocolRegistration.setParticipant(null);

		collectionProtocolRegistration.setProtocolParticipantIdentifier("11111");
		collectionProtocolRegistration.setActivityStatus("Active");
	}
	
	public void updateSpecimenCollectionGroup(SpecimenCollectionGroup specimenCollectionGroup)
	{
		Site site = (Site)ClientDemo.dataModelObjectMap.get("Site");
		specimenCollectionGroup.setSite(site);
		specimenCollectionGroup.setClinicalDiagnosis("Dentinoma");//Abdominal fibromatosis
		specimenCollectionGroup.setClinicalStatus("New Diagnosis"); //Operative
		specimenCollectionGroup.setActivityStatus("Active");

		CollectionProtocolEvent collectionProtocolEvent = (CollectionProtocolEvent)ClientDemo.dataModelObjectMap.get("CollectionProtocolEvent");	
		specimenCollectionGroup.setCollectionProtocolEvent(collectionProtocolEvent);

		CollectionProtocolRegistration collectionProtocolRegistration = (CollectionProtocolRegistration)ClientDemo.dataModelObjectMap.get("CollectionProtocolRegistration");
		
		specimenCollectionGroup.setCollectionProtocolRegistration(collectionProtocolRegistration);

		specimenCollectionGroup.setName("scg" + UniqueKeyGeneratorUtil.getUniqueKey());
		
		//clinicalReport = new ClinicalReport();
		//clinicalReport.setSurgicalPathologyNumber("123");
		specimenCollectionGroup.getClinicalReport().setSurgicalPathologyNumber("1234");

	}	
	
	
	public void updateParticipant(Participant participant)
	{
		participant.setLastName("last" + UniqueKeyGeneratorUtil.getUniqueKey());
		participant.setFirstName("frst" + UniqueKeyGeneratorUtil.getUniqueKey());
		participant.setMiddleName("mdl" + UniqueKeyGeneratorUtil.getUniqueKey());

		/*try
		{
			System.out.println("-----------------------");
			participant.setBirthDate(Utility.parseDate("08/15/1975", Utility
					.datePattern("08/15/1975")));
		}
		catch (ParseException e)
		{
			// TODO Auto-generated catch block
			System.out.println("-----------------------"+e);
			e.printStackTrace();
		}		
		try
		{
			System.out.println("-----------------------");
			participant.setDeathDate(Utility.parseDate("08/15/1974", Utility
					.datePattern("08/15/1974")));
		}
		catch (ParseException e)
		{
			// TODO Auto-generated catch block
			System.out.println("-----------------------"+e);
			e.printStackTrace();
		}*/
		
		participant.setVitalStatus("Alive"); //Dead
		participant.setGender("Male"); //
		participant.setSexGenotype(""); //XX

		Collection raceCollection = new HashSet();
		raceCollection.add("Black or African American"); //White
		raceCollection.add("Unknown"); //Asian
		participant.setRaceCollection(raceCollection);
		participant.setActivityStatus("Active"); //Active
		participant.setEthnicity("Unknown"); //Hispanic or Latino
		//participant.setSocialSecurityNumber("333-33-3333");

		Collection participantMedicalIdentifierCollection = new HashSet();
		/*participantMedicalIdentifierCollection.add("Washington University School of Medicine");
		 participantMedicalIdentifierCollection.add("1111");
		 */
		participant
				.setParticipantMedicalIdentifierCollection(participantMedicalIdentifierCollection);
	}
	
	public void updateDistributionProtocol(DistributionProtocol distributionProtocol)
	{
		User principalInvestigator = (User) ClientDemo.dataModelObjectMap.get("User");
		/*	
		new User();
		principalInvestigator.setId(new Long(1));
		*/
		distributionProtocol.setPrincipalInvestigator(principalInvestigator);
		distributionProtocol.setTitle("DP"+ UniqueKeyGeneratorUtil.getUniqueKey());
		distributionProtocol.setShortTitle("DP"); //DP1
		distributionProtocol.setIrbIdentifier("11111");//55555
		
		try
		{
			distributionProtocol.setStartDate(Utility.parseDate("08/15/1976", Utility
					.datePattern("08/15/1976"))); //08/15/1975
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		
		distributionProtocol.setDescriptionURL("");
		distributionProtocol.setEnrollment(new Integer(20)); //10

		SpecimenRequirement specimenRequirement = (SpecimenRequirement) ClientDemo.dataModelObjectMap.get("SpecimenRequirement");
		specimenRequirement.setPathologyStatus("Non-Malignant"); //Malignant
		specimenRequirement.setTissueSite("Anal canal"); //Placenta
		specimenRequirement.setSpecimenType("Bile"); //DNA
		specimenRequirement.setSpecimenClass("Fluid"); //Molecular
		Quantity quantity = new Quantity();
		quantity.setValue(new Double(20)); //10
		specimenRequirement.setQuantity(quantity);
			
		Collection specimenRequirementCollection = new HashSet();
		specimenRequirementCollection.add(specimenRequirement);
		distributionProtocol.setSpecimenRequirementCollection(specimenRequirementCollection);

		distributionProtocol.setActivityStatus("Active"); //Active
	}
	
	public void updateCollectionProtocol(CollectionProtocol collectionProtocol)
	{
		collectionProtocol.setAliqoutInSameContainer(new Boolean(false)); //true
		collectionProtocol.setDescriptionURL("");
		collectionProtocol.setActivityStatus("Active"); //Active
		collectionProtocol.setEndDate(null);
		collectionProtocol.setEnrollment(null);
		collectionProtocol.setIrbIdentifier("11111");//77777
		collectionProtocol.setTitle("cp" + UniqueKeyGeneratorUtil.getUniqueKey());
		collectionProtocol.setShortTitle("cp"); //pc!
		
		try
		{
			collectionProtocol.setStartDate(Utility.parseDate("08/15/1975", Utility
					.datePattern("08/15/1975")));
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}

		Collection collectionProtocolEventCollectionSet = new HashSet();
	 
		//CollectionProtocolEvent collectionProtocolEvent = new CollectionProtocolEvent();
		CollectionProtocolEvent collectionProtocolEvent = (CollectionProtocolEvent)ClientDemo.dataModelObjectMap.get("CollectionProtocolEvent"); 
		collectionProtocolEvent.setClinicalStatus("Not Specified");//New Diagnosis
		collectionProtocolEvent.setStudyCalendarEventPoint(new Double(2)); //1
	 

		Collection specimenRequirementCollection = new HashSet();
		//SpecimenRequirement specimenRequirement = new SpecimenRequirement();
		//specimenRequirement.setSpecimenClass("Molecular");
		//specimenRequirement.setSpecimenType("DNA");
		//specimenRequirement.setTissueSite("Placenta");
		//specimenRequirement.setPathologyStatus("Malignant");
		//Quantity quantity = new Quantity();
		//quantity.setValue(new Double(10));
		//specimenRequirement.setQuantity(quantity);
		
		SpecimenRequirement specimenRequirement  =(SpecimenRequirement)ClientDemo.dataModelObjectMap.get("SpecimenRequirement");
		specimenRequirement.setSpecimenClass("Fluid"); //Molecular
		specimenRequirement.setSpecimenType("Bile"); //DNA
		specimenRequirement.setTissueSite("Anal canal"); //Placenta
		specimenRequirement.setPathologyStatus("Non-Malignant");//Malignant
		specimenRequirementCollection.add(specimenRequirement);
		
		collectionProtocolEvent.setSpecimenRequirementCollection(specimenRequirementCollection);

		collectionProtocolEventCollectionSet.add(collectionProtocolEvent);
		collectionProtocol
				.setCollectionProtocolEventCollection(collectionProtocolEventCollectionSet);

		//User principalInvestigator = new User();
		//principalInvestigator.setId(new Long(1));
		User principalInvestigator = (User)ClientDemo.dataModelObjectMap.get("User");
		collectionProtocol.setPrincipalInvestigator(principalInvestigator);
		
		User protocolCordinator = new User();
		protocolCordinator.setId(new Long(principalInvestigator.getId().longValue()-1));
		Collection protocolCordinatorCollection = new HashSet();
		protocolCordinatorCollection.add(protocolCordinator);
		collectionProtocol.setUserCollection(protocolCordinatorCollection);
		
	}
	public void updateSpecimen(Specimen updateSpecimen)
	{				
		SpecimenCollectionGroup specimenCollectionGroup = (SpecimenCollectionGroup)ClientDemo.dataModelObjectMap.get("SpecimenCollectionGroup");
		updateSpecimen.setSpecimenCollectionGroup(specimenCollectionGroup);
		
		//updateSpecimen.setLabel("spec" + UniqueKeyGeneratorUtil.getUniqueKey());
		updateSpecimen.setBarcode("bar" + UniqueKeyGeneratorUtil.getUniqueKey());
		updateSpecimen.setType("DNA");
		updateSpecimen.setAvailable(new Boolean(true));
		updateSpecimen.setActivityStatus("Active");
		
		SpecimenCharacteristics specimenCharacteristics = (SpecimenCharacteristics)ClientDemo.dataModelObjectMap.get("SpecimenCharacteristics");
		updateSpecimen.setSpecimenCharacteristics(specimenCharacteristics);
		
		updateSpecimen.setPathologicalStatus("Non-Malignant"); //Malignant
		
		//updateSpecimen.setAvailableQuantity(quantity);
		//updateSpecimen.setConcentrationInMicrogramPerMicroliter(new Double(10));
		updateSpecimen.setComments("");
		
		updateSpecimen.setStorageContainer(null); 
		updateSpecimen.setPositionDimensionOne(null);
		updateSpecimen.setPositionDimensionTwo(null);
		
		Collection externalIdentifierCollection = new HashSet();
		ExternalIdentifier externalIdentifier = new ExternalIdentifier();
		externalIdentifier.setName("eid" + UniqueKeyGeneratorUtil.getUniqueKey());
		externalIdentifier.setValue("11");
		externalIdentifier.setSpecimen(updateSpecimen);
		
		externalIdentifierCollection.add(externalIdentifier);
		updateSpecimen.setExternalIdentifierCollection(externalIdentifierCollection);
		CollectionEventParameters collectionEventParameters = new CollectionEventParameters();
		collectionEventParameters.setComments("");
		
		User user = (User)ClientDemo.dataModelObjectMap.get("User");	
		collectionEventParameters.setUser(user);
		
		try
		{
			collectionEventParameters.setTimestamp(Utility.parseDate("08/15/1976", Utility
					.datePattern("08/15/1976"))); //08/15/1975
					
		}
		catch (ParseException e1)
		{
			System.out.println(" exception in APIDemo");
			e1.printStackTrace();
		}
		
		collectionEventParameters.setContainer("ACD Vacutainer"); //No Additive Vacutainer
		collectionEventParameters.setCollectionProcedure("Lavage");
		ReceivedEventParameters receivedEventParameters = new ReceivedEventParameters();
		receivedEventParameters.setUser(user);
		
		try
		{
			System.out.println("--- Start ---- 10");
			receivedEventParameters.setTimestamp(Utility.parseDate("08/15/1976", Utility
					.datePattern("08/15/1976"))); //08/15/1976
		}
		catch (ParseException e)
		{
			System.out.println("APIDemo");
			e.printStackTrace();
		}
		
		receivedEventParameters.setReceivedQuality("Clotted"); //Acceptable
		receivedEventParameters.setComments("");
		Collection specimenEventCollection = new HashSet();
		specimenEventCollection.add(collectionEventParameters);
		specimenEventCollection.add(receivedEventParameters);
		updateSpecimen.setSpecimenEventCollection(specimenEventCollection);
		
		Biohazard biohazard = (Biohazard)ClientDemo.dataModelObjectMap.get("Biohazard");
		Collection biohazardCollection = new HashSet();
		biohazardCollection.add(biohazard);
		updateSpecimen.setBiohazardCollection(biohazardCollection);		
		
	}
	
	
	public void updateStorageType(StorageType updateStorageType)
	{		
		Capacity capacity = updateStorageType.getCapacity();
		
		updateStorageType.setDefaultTempratureInCentigrade(new Double(30));//-30
		updateStorageType.setOneDimensionLabel("Label-1"); //label 1
		updateStorageType.setTwoDimensionLabel("Label-2"); //label 2

		capacity.setOneDimensionCapacity(new Integer(2));//3
		capacity.setTwoDimensionCapacity(new Integer(2));//3
		updateStorageType.setCapacity(capacity);
	

//		Collection holdsStorageTypeCollection = new HashSet();
//		holdsStorageTypeCollection.add(updateStorageType);
//
//		updateStorageType.setHoldsStorageTypeCollection(holdsStorageTypeCollection);
//		updateStorageType.setActivityStatus("Active");
//
//		Collection holdsSpecimenClassCollection = new HashSet();		
//		holdsSpecimenClassCollection.add("Fluid");
//		holdsSpecimenClassCollection.add("Molecular");
//		updateStorageType.setHoldsSpecimenClassCollection(holdsSpecimenClassCollection);
		
	}
	
	/**
	 * @return StorageContainer
	 */
	public void updateStorageContainer(StorageContainer storageContainer)
	{	
		StorageType storageType = (StorageType) ClientDemo.dataModelObjectMap.get("StorageType");		
		storageContainer.setStorageType(storageType);
		
		Site site = (Site) ClientDemo.dataModelObjectMap.get("Site"); 		
		storageContainer.setSite(site);
		
		storageContainer.setTempratureInCentigrade(new Double(30)); //-30
		storageContainer.setBarcode("barc" + UniqueKeyGeneratorUtil.getUniqueKey());

//		Capacity capacity = storageContainer.getCapacity();
//		capacity.setOneDimensionCapacity(new Integer(1));
//		capacity.setTwoDimensionCapacity(new Integer(2));
//		storageContainer.setCapacity(capacity);

		CollectionProtocol collectionProtocol = (CollectionProtocol) ClientDemo.dataModelObjectMap.get("CollectionProtocol");		
		
		Collection collectionProtocolCollection = new HashSet();
		collectionProtocolCollection.add(collectionProtocol);
		storageContainer.setCollectionProtocolCollection(collectionProtocolCollection);

		Collection holdsStorageTypeCollection = new HashSet();
		holdsStorageTypeCollection.add(storageType);
		storageContainer.setHoldsStorageTypeCollection(holdsStorageTypeCollection);

		Collection holdsSpecimenClassCollection = new HashSet();
		holdsSpecimenClassCollection.add("Tissue");
		holdsSpecimenClassCollection.add("Molecular");
		storageContainer.setHoldsSpecimenClassCollection(holdsSpecimenClassCollection);
		
		storageContainer.setPositionDimensionOne(new Integer(1));
		storageContainer.setPositionDimensionTwo(new Integer(2));

		storageContainer.setActivityStatus("Active");
		storageContainer.setFull(Boolean.valueOf(false));		
	}
	
	/**
	 * @return SpecimenArrayType
	 */
	public void updateSpecimenArrayType(SpecimenArrayType specimenArrayType)
	{		
		specimenArrayType.setSpecimenClass("Molecular"); 

		Collection specimenTypeCollection = new HashSet();
		specimenTypeCollection.add("DNA"); //
		specimenTypeCollection.add("RNA"); //
		specimenArrayType.setSpecimenTypeCollection(specimenTypeCollection);
		specimenArrayType.setComment("");
		Capacity capacity = specimenArrayType.getCapacity();
		capacity.setOneDimensionCapacity(new Integer(3)); //4
		capacity.setTwoDimensionCapacity(new Integer(3)); //4
		specimenArrayType.setCapacity(capacity);		
	}
	
	public void updateSpecimenArray(SpecimenArray specimenArray)
	{		
		SpecimenArrayType specimenArrayType = (SpecimenArrayType) ClientDemo.dataModelObjectMap.get("SpecimenArrayType");
		specimenArray.setSpecimenArrayType(specimenArrayType);
		
		specimenArray.setBarcode("bar" + UniqueKeyGeneratorUtil.getUniqueKey());
		specimenArray.setName("sa" + UniqueKeyGeneratorUtil.getUniqueKey()); 
		
		User createdBy = (User) ClientDemo.dataModelObjectMap.get("User");
		specimenArray.setCreatedBy(createdBy);
		
//		Capacity capacity = specimenArrayType.getCapacity();
//		specimenArray.setCapacity(capacity);
		
		specimenArray.setComment("");
		StorageContainer storageContainer = (StorageContainer) ClientDemo.dataModelObjectMap.get("StorageContainer");
		
		specimenArray.setStorageContainer(storageContainer);
		specimenArray.setPositionDimensionOne(new Integer(1));
		specimenArray.setPositionDimensionTwo(new Integer(1));
		
//		Collection specimenArrayContentCollection = new HashSet();
//		SpecimenArrayContent specimenArrayContent = new SpecimenArrayContent();
//		
//		Specimen specimen = (Specimen) ClientDemo.dataModelObjectMap.get("Specimen");
//		specimenArrayContent.setSpecimen(specimen);
//		specimenArrayContent.setPositionDimensionOne(new Integer(1));
//		specimenArrayContent.setPositionDimensionTwo(new Integer(1));
//		Quantity quantity = new Quantity();
//		quantity.setValue(new Double(2));
//		specimenArrayContent.setInitialQuantity(quantity);
//		specimenArrayContentCollection.add(specimenArrayContent);
//		specimenArray.setSpecimenArrayContentCollection(specimenArrayContentCollection);		
	}
	
	
	
	
	
	
	private int getUniqueId()
	{
		return 1; 
	}
}
