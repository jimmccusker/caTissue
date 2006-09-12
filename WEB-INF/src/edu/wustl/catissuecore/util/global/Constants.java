/**
 * <p>Title: Constants Class>
 * <p>Description:  This class stores the constants used in the operations in the application.</p>
 * Copyright:    Copyright (c) year
 * Company: Washington University, School of Medicine, St. Louis.
 * @author Gautam Shetty
 * @version 1.00
 * Created on Mar 16, 2005
 */

package edu.wustl.catissuecore.util.global;


/**
 * This class stores the constants used in the operations in the application.
 * @author gautam_shetty
 */

public class Constants extends edu.wustl.common.util.global.Constants
{
	public static final String MAX_IDENTIFIER = "maxIdentifier";
    public static final String AND_JOIN_CONDITION = "AND";
	public static final String OR_JOIN_CONDITION = "OR";
	//Sri: Changed the format for displaying in Specimen Event list (#463)
	public static final String TIMESTAMP_PATTERN = "MM-dd-yyyy HH:mm";
	public static final String DATE_PATTERN_YYYY_MM_DD = "yyyy-MM-dd";
	
	// Mandar: Used for Date Validations in Validator Class
	public static final String DATE_SEPARATOR = "-";
	public static final String DATE_SEPARATOR_DOT = ".";
	public static final String MIN_YEAR = "1900";
	public static final String MAX_YEAR = "9999";
	
	public static final String VIEW = "view";
	public static final String DELETE = "delete";
	public static final String EXPORT = "export";
	public static final String SHOPPING_CART_ADD = "shoppingCartAdd";
	public static final String SHOPPING_CART_DELETE = "shoppingCartDelete";
	public static final String SHOPPING_CART_EXPORT = "shoppingCartExport";
	public static final String NEWUSERFORM = "newUserForm";
	public static final String REDIRECT_TO_SPECIMEN = "specimenRedirect";
	public static final String CALLED_FROM = "calledFrom";
	
	//Constants required for Forgot Password
	public static final String FORGOT_PASSWORD = "forgotpassword";
	
	public static final String LOGINNAME = "loginName";
	public static final String LASTNAME = "lastName";
	public static final String FIRSTNAME = "firstName";
	public static final String INSTITUTION = "institution";
	public static final String EMAIL = "email";
	public static final String DEPARTMENT = "department";
	public static final String ADDRESS = "address";
	public static final String CITY = "city";
	public static final String STATE = "state";
	public static final String COUNTRY = "country";
	public static final String NEXT_CONTAINER_NO = "startNumber";
	public static final String CSM_USER_ID = "csmUserId";
	
	public static final String INSTITUTIONLIST = "institutionList";
	public static final String DEPARTMENTLIST = "departmentList";
	public static final String STATELIST = "stateList";
	public static final String COUNTRYLIST = "countryList";
	public static final String ROLELIST = "roleList";
	public static final String ROLEIDLIST = "roleIdList";
	public static final String CANCER_RESEARCH_GROUP_LIST = "cancerResearchGroupList";
	public static final String GENDER_LIST = "genderList";
	public static final String GENOTYPE_LIST = "genotypeList";
	public static final String ETHNICITY_LIST = "ethnicityList";
	public static final String PARTICIPANT_MEDICAL_RECORD_SOURCE_LIST = "participantMedicalRecordSourceList";
	public static final String RACELIST = "raceList";
	public static final String VITAL_STATUS_LIST = "vitalStatusList";
	public static final String PARTICIPANT_LIST = "participantList";
	public static final String PARTICIPANT_ID_LIST = "participantIdList";
	public static final String PROTOCOL_LIST = "protocolList";
	public static final String TIMEHOURLIST = "timeHourList";
	public static final String TIMEMINUTESLIST = "timeMinutesList";
	public static final String TIMEAMPMLIST = "timeAMPMList";
	public static final String RECEIVEDBYLIST = "receivedByList";
	public static final String COLLECTEDBYLIST = "collectedByList";
	public static final String COLLECTIONSITELIST = "collectionSiteList";
	public static final String RECEIVEDSITELIST = "receivedSiteList";
	public static final String RECEIVEDMODELIST = "receivedModeList";
	public static final String ACTIVITYSTATUSLIST = "activityStatusList";
	public static final String USERLIST = "userList";
	public static final String SITETYPELIST = "siteTypeList";
	public static final String STORAGETYPELIST="storageTypeList";
	public static final String STORAGECONTAINERLIST="storageContainerList";
	public static final String SITELIST="siteList";
//	public static final String SITEIDLIST="siteIdList";
	public static final String USERIDLIST = "userIdList";
	public static final String STORAGETYPEIDLIST="storageTypeIdList";
	public static final String SPECIMENCOLLECTIONLIST="specimentCollectionList";
	public static final String PARTICIPANT_IDENTIFIER_IN_CPR = "participant";
	public static final String APPROVE_USER_STATUS_LIST = "statusList";
	public static final String EVENT_PARAMETERS_LIST = "eventParametersList";
		
	//New Specimen lists.
	public static final String SPECIMEN_COLLECTION_GROUP_LIST = "specimenCollectionGroupIdList";
	public static final String SPECIMEN_TYPE_LIST = "specimenTypeList";
	public static final String SPECIMEN_SUB_TYPE_LIST = "specimenSubTypeList";
	public static final String TISSUE_SITE_LIST = "tissueSiteList";
	public static final String TISSUE_SIDE_LIST = "tissueSideList";
	public static final String PATHOLOGICAL_STATUS_LIST = "pathologicalStatusList";
	public static final String BIOHAZARD_TYPE_LIST = "biohazardTypeList";
	public static final String BIOHAZARD_NAME_LIST = "biohazardNameList";
	public static final String BIOHAZARD_ID_LIST = "biohazardIdList";
	public static final String BIOHAZARD_TYPES_LIST = "biohazardTypesList";
	public static final String PARENT_SPECIMEN_ID_LIST = "parentSpecimenIdList";
	public static final String RECEIVED_QUALITY_LIST = "receivedQualityList";
	
	//SpecimenCollecionGroup lists.
	public static final String PROTOCOL_TITLE_LIST = "protocolTitleList";
	public static final String PARTICIPANT_NAME_LIST = "participantNameList";
	public static final String PROTOCOL_PARTICIPANT_NUMBER_LIST = "protocolParticipantNumberList";
	//public static final String PROTOCOL_PARTICIPANT_NUMBER_ID_LIST = "protocolParticipantNumberIdList";
	public static final String STUDY_CALENDAR_EVENT_POINT_LIST = "studyCalendarEventPointList";
   	//public static final String STUDY_CALENDAR_EVENT_POINT_ID_LIST="studyCalendarEventPointIdList";
	public static final String PARTICIPANT_MEDICAL_IDNETIFIER_LIST = "participantMedicalIdentifierArray";
	//public static final String PARTICIPANT_MEDICAL_IDNETIFIER_ID_LIST = "participantMedicalIdentifierIdArray";
	public static final String SPECIMEN_COLLECTION_GROUP_ID = "specimenCollectionGroupId";
	public static final String REQ_PATH = "redirectTo";
	
	public static final String CLINICAL_STATUS_LIST = "cinicalStatusList";
	public static final String SPECIMEN_CLASS_LIST = "specimenClassList";
	public static final String SPECIMEN_CLASS_ID_LIST = "specimenClassIdList";
	public static final String SPECIMEN_TYPE_MAP = "specimenTypeMap";
	//Simple Query Interface Lists
	public static final String OBJECT_COMPLETE_NAME_LIST = "objectCompleteNameList";
	public static final String SIMPLE_QUERY_INTERFACE_TITLE = "simpleQueryInterfaceTitle";
	
	public static final String STORAGE_CONTAINER_GRID_OBJECT = "storageContainerGridObject";
	public static final String STORAGE_CONTAINER_CHILDREN_STATUS = "storageContainerChildrenStatus";
	public static final String START_NUMBER = "startNumber";
	public static final String CHILD_CONTAINER_SYSTEM_IDENTIFIERS = "childContainerIds";
	public static final int STORAGE_CONTAINER_FIRST_ROW = 1;
	public static final int STORAGE_CONTAINER_FIRST_COLUMN = 1;
	public static final String MAP_COLLECTION_PROTOCOL_LIST = "collectionProtocolList";
	public static final String MAP_SPECIMEN_CLASS_LIST = "specimenClassList";
	
	//event parameters lists
	public static final String METHOD_LIST = "methodList";
	public static final String HOUR_LIST = "hourList";
	public static final String MINUTES_LIST = "minutesList";
	public static final String EMBEDDING_MEDIUM_LIST = "embeddingMediumList";
	public static final String PROCEDURE_LIST = "procedureList";
	public static final String PROCEDUREIDLIST = "procedureIdList";
	public static final String CONTAINER_LIST = "containerList";
	public static final String CONTAINERIDLIST = "containerIdList";
	public static final String FROMCONTAINERLIST="fromContainerList";
	public static final String TOCONTAINERLIST="toContainerList";
	public static final String FIXATION_LIST = "fixationList";	
	public static final String FROM_SITE_LIST="fromsiteList";
	public static final String TO_SITE_LIST="tositeList";	
	public static final String ITEMLIST="itemList";
	public static final String DISTRIBUTIONPROTOCOLLIST="distributionProtocolList";
	public static final String TISSUE_SPECIMEN_ID_LIST="tissueSpecimenIdList";
	public static final String MOLECULAR_SPECIMEN_ID_LIST="molecularSpecimenIdList";
	public static final String CELL_SPECIMEN_ID_LIST="cellSpecimenIdList";
	public static final String FLUID_SPECIMEN_ID_LIST="fluidSpecimenIdList";
	public static final String STORAGE_STATUS_LIST="storageStatusList";
	public static final String CLINICAL_DIAGNOSIS_LIST = "clinicalDiagnosisList";
	public static final String HISTOLOGICAL_QUALITY_LIST="histologicalQualityList";
	
	//For Specimen Event Parameters.
	public static final String SPECIMEN_ID = "specimenId";
	public static final String FROM_POSITION_DATA = "fromPositionData";
	public static final String POS_ONE ="posOne";
	public static final String POS_TWO ="posTwo";
	public static final String STORAGE_CONTAINER_ID ="storContId";	
	public static final String IS_RNA = "isRNA";
	public static final String RNA = "RNA";
	
	//	New Participant Event Parameters
	public static final String PARTICIPANT_ID="participantId";
	
	//Constants required in User.jsp Page
	public static final String USER_SEARCH_ACTION = "UserSearch.do";
	public static final String USER_ADD_ACTION = "UserAdd.do";
	public static final String USER_EDIT_ACTION = "UserEdit.do";
	public static final String APPROVE_USER_ADD_ACTION = "ApproveUserAdd.do";
	public static final String APPROVE_USER_EDIT_ACTION = "ApproveUserEdit.do";
	public static final String SIGNUP_USER_ADD_ACTION = "SignUpUserAdd.do";
	public static final String USER_EDIT_PROFILE_ACTION = "UserEditProfile.do";
	public static final String UPDATE_PASSWORD_ACTION = "UpdatePassword.do";
	
	//Constants required in Accession.jsp Page
	public static final String ACCESSION_SEARCH_ACTION = "AccessionSearch.do";
	public static final String ACCESSION_ADD_ACTION = "AccessionAdd.do";
	public static final String ACCESSION_EDIT_ACTION = "AccessionEdit.do";
	
	//Constants required in StorageType.jsp Page
	public static final String STORAGE_TYPE_SEARCH_ACTION = "StorageTypeSearch.do";
	public static final String STORAGE_TYPE_ADD_ACTION = "StorageTypeAdd.do";
	public static final String STORAGE_TYPE_EDIT_ACTION = "StorageTypeEdit.do";
	
	//Constants required in StorageContainer.jsp Page
	public static final String STORAGE_CONTAINER_SEARCH_ACTION = "StorageContainerSearch.do";
	public static final String STORAGE_CONTAINER_ADD_ACTION = "StorageContainerAdd.do";
	public static final String STORAGE_CONTAINER_EDIT_ACTION = "StorageContainerEdit.do";
	
	public static final String HOLDS_LIST1 = "HoldsList1";
	public static final String HOLDS_LIST2 = "HoldsList2";
	public static final String HOLDS_LIST3 = "HoldsList3";
	//Constants required in Site.jsp Page
	public static final String SITE_SEARCH_ACTION = "SiteSearch.do";
	public static final String SITE_ADD_ACTION = "SiteAdd.do";
	public static final String SITE_EDIT_ACTION = "SiteEdit.do";
	
	//Constants required in Site.jsp Page
	public static final String BIOHAZARD_SEARCH_ACTION = "BiohazardSearch.do";
	public static final String BIOHAZARD_ADD_ACTION = "BiohazardAdd.do";
	public static final String BIOHAZARD_EDIT_ACTION = "BiohazardEdit.do";
	
	//Constants required in Partcipant.jsp Page
	public static final String PARTICIPANT_SEARCH_ACTION = "ParticipantSearch.do";
	public static final String PARTICIPANT_ADD_ACTION = "ParticipantAdd.do";
	public static final String PARTICIPANT_EDIT_ACTION = "ParticipantEdit.do";
	public static final String PARTICIPANT_LOOKUP_ACTION= "ParticipantLookup.do";
	
	
	//Constants required in Institution.jsp Page
	public static final String INSTITUTION_SEARCH_ACTION = "InstitutionSearch.do";
	public static final String INSTITUTION_ADD_ACTION = "InstitutionAdd.do";
	public static final String INSTITUTION_EDIT_ACTION = "InstitutionEdit.do";

	//Constants required in Department.jsp Page
	public static final String DEPARTMENT_SEARCH_ACTION = "DepartmentSearch.do";
	public static final String DEPARTMENT_ADD_ACTION = "DepartmentAdd.do";
	public static final String DEPARTMENT_EDIT_ACTION = "DepartmentEdit.do";
	
    //Constants required in CollectionProtocolRegistration.jsp Page
	public static final String COLLECTION_PROTOCOL_REGISTRATION_SEARCH_ACTION = "CollectionProtocolRegistrationSearch.do";
	public static final String COLLECTIONP_ROTOCOL_REGISTRATION_ADD_ACTION = "CollectionProtocolRegistrationAdd.do";
	public static final String COLLECTION_PROTOCOL_REGISTRATION_EDIT_ACTION = "CollectionProtocolRegistrationEdit.do";
	
	//Constants required in CancerResearchGroup.jsp Page
	public static final String CANCER_RESEARCH_GROUP_SEARCH_ACTION = "CancerResearchGroupSearch.do";
	public static final String CANCER_RESEARCH_GROUP_ADD_ACTION = "CancerResearchGroupAdd.do";
	public static final String CANCER_RESEARCH_GROUP_EDIT_ACTION = "CancerResearchGroupEdit.do";
	
	//Constants required for Approve user
	public static final String USER_DETAILS_SHOW_ACTION = "UserDetailsShow.do";
	public static final String APPROVE_USER_SHOW_ACTION = "ApproveUserShow.do";
	
	//Reported Problem Constants
	public static final String REPORTED_PROBLEM_ADD_ACTION = "ReportedProblemAdd.do";
	public static final String REPORTED_PROBLEM_EDIT_ACTION = "ReportedProblemEdit.do";
	public static final String PROBLEM_DETAILS_ACTION = "ProblemDetails.do";
	public static final String REPORTED_PROBLEM_SHOW_ACTION = "ReportedProblemShow.do";
	
	//Query Results view Actions
	public static final String TREE_VIEW_ACTION = "TreeView.do";
	public static final String DATA_VIEW_FRAME_ACTION = "SpreadsheetView.do";
	public static final String SIMPLE_QUERY_INTERFACE_URL = "SimpleQueryInterface.do?pageOf=pageOfSimpleQueryInterface&menuSelected=17";
	
	//New Specimen Data Actions.
	public static final String SPECIMEN_ADD_ACTION = "NewSpecimenAdd.do";
	public static final String SPECIMEN_EDIT_ACTION = "NewSpecimenEdit.do";
	public static final String SPECIMEN_SEARCH_ACTION = "NewSpecimenSearch.do";
	
	public static final String SPECIMEN_EVENT_PARAMETERS_ACTION = "ListSpecimenEventParameters.do";
	
	//Create Specimen Data Actions.
	public static final String CREATE_SPECIMEN_ADD_ACTION = "CreateSpecimenAdd.do";
	public static final String CREATE_SPECIMEN_EDIT_ACTION = "CreateSpecimenEdit.do";
	public static final String CREATE_SPECIMEN_SEARCH_ACTION = "CreateSpecimenSearch.do";
	
	//ShoppingCart Actions.
	public static final String SHOPPING_CART_OPERATION = "ShoppingCartOperation.do";

	public static final String SPECIMEN_COLLECTION_GROUP_SEARCH_ACTION = "SpecimenCollectionGroup.do";
	public static final String SPECIMEN_COLLECTION_GROUP_ADD_ACTION = "SpecimenCollectionGroupAdd.do";
	public static final String SPECIMEN_COLLECTION_GROUP_EDIT_ACTION = "SpecimenCollectionGroupEdit.do";

	//Constants required in FrozenEventParameters.jsp Page
	public static final String FROZEN_EVENT_PARAMETERS_SEARCH_ACTION = "FrozenEventParametersSearch.do";
	public static final String FROZEN_EVENT_PARAMETERS_ADD_ACTION = "FrozenEventParametersAdd.do";
	public static final String FROZEN_EVENT_PARAMETERS_EDIT_ACTION = "FrozenEventParametersEdit.do";

	//Constants required in CheckInCheckOutEventParameters.jsp Page
	public static final String CHECKIN_CHECKOUT_EVENT_PARAMETERS_SEARCH_ACTION = "CheckInCheckOutEventParametersSearch.do";
	public static final String CHECKIN_CHECKOUT_EVENT_PARAMETERS_ADD_ACTION = "CheckInCheckOutEventParametersAdd.do";
	public static final String CHECKIN_CHECKOUT_EVENT_PARAMETERS_EDIT_ACTION = "CheckInCheckOutEventParametersEdit.do";

	//Constants required in ReceivedEventParameters.jsp Page
	public static final String RECEIVED_EVENT_PARAMETERS_SEARCH_ACTION = "receivedEventParametersSearch.do";
	public static final String RECEIVED_EVENT_PARAMETERS_ADD_ACTION = "ReceivedEventParametersAdd.do";
	public static final String RECEIVED_EVENT_PARAMETERS_EDIT_ACTION = "receivedEventParametersEdit.do";

	//Constants required in FluidSpecimenReviewEventParameters.jsp Page
	public static final String FLUID_SPECIMEN_REVIEW_EVENT_PARAMETERS_SEARCH_ACTION = "FluidSpecimenReviewEventParametersSearch.do";
	public static final String FLUID_SPECIMEN_REVIEW_EVENT_PARAMETERS_ADD_ACTION = "FluidSpecimenReviewEventParametersAdd.do";
	public static final String FLUID_SPECIMEN_REVIEW_EVENT_PARAMETERS_EDIT_ACTION = "FluidSpecimenReviewEventParametersEdit.do";

	//Constants required in CELLSPECIMENREVIEWParameters.jsp Page
	public static final String CELL_SPECIMEN_REVIEW_PARAMETERS_SEARCH_ACTION = "CellSpecimenReviewParametersSearch.do";
	public static final String CELL_SPECIMEN_REVIEW_PARAMETERS_ADD_ACTION = "CellSpecimenReviewParametersAdd.do";
	public static final String CELL_SPECIMEN_REVIEW_PARAMETERS_EDIT_ACTION = "CellSpecimenReviewParametersEdit.do";

	//Constants required in tissue SPECIMEN REVIEW event Parameters.jsp Page
	public static final String TISSUE_SPECIMEN_REVIEW_EVENT_PARAMETERS_SEARCH_ACTION = "TissueSpecimenReviewEventParametersSearch.do";
	public static final String TISSUE_SPECIMEN_REVIEW_EVENT_PARAMETERS_ADD_ACTION = "TissueSpecimenReviewEventParametersAdd.do";
	public static final String TISSUE_SPECIMEN_REVIEW_EVENT_PARAMETERS_EDIT_ACTION = "TissueSpecimenReviewEventParametersEdit.do";

	//	Constants required in DisposalEventParameters.jsp Page	
	public static final String DISPOSAL_EVENT_PARAMETERS_SEARCH_ACTION = "DisposalEventParametersSearch.do";
	public static final String DISPOSAL_EVENT_PARAMETERS_ADD_ACTION = "DisposalEventParametersAdd.do";
	public static final String DISPOSAL_EVENT_PARAMETERS_EDIT_ACTION = "DisposalEventParametersEdit.do";
	
	//	Constants required in ThawEventParameters.jsp Page
	public static final String THAW_EVENT_PARAMETERS_SEARCH_ACTION = "ThawEventParametersSearch.do";
	public static final String THAW_EVENT_PARAMETERS_ADD_ACTION = "ThawEventParametersAdd.do";
	public static final String THAW_EVENT_PARAMETERS_EDIT_ACTION = "ThawEventParametersEdit.do";

	//	Constants required in MOLECULARSPECIMENREVIEWParameters.jsp Page
	public static final String MOLECULAR_SPECIMEN_REVIEW_PARAMETERS_SEARCH_ACTION = "MolecularSpecimenReviewParametersSearch.do";
	public static final String MOLECULAR_SPECIMEN_REVIEW_PARAMETERS_ADD_ACTION = "MolecularSpecimenReviewParametersAdd.do";
	public static final String MOLECULAR_SPECIMEN_REVIEW_PARAMETERS_EDIT_ACTION = "MolecularSpecimenReviewParametersEdit.do";

	//	Constants required in CollectionEventParameters.jsp Page
	public static final String COLLECTION_EVENT_PARAMETERS_SEARCH_ACTION = "CollectionEventParametersSearch.do";
	public static final String COLLECTION_EVENT_PARAMETERS_ADD_ACTION = "CollectionEventParametersAdd.do";
	public static final String COLLECTION_EVENT_PARAMETERS_EDIT_ACTION = "CollectionEventParametersEdit.do";
	
	//	Constants required in SpunEventParameters.jsp Page
	public static final String SPUN_EVENT_PARAMETERS_SEARCH_ACTION = "SpunEventParametersSearch.do";
	public static final String SPUN_EVENT_PARAMETERS_ADD_ACTION = "SpunEventParametersAdd.do";
	public static final String SPUN_EVENT_PARAMETERS_EDIT_ACTION = "SpunEventParametersEdit.do";
	
	//	Constants required in EmbeddedEventParameters.jsp Page
	public static final String EMBEDDED_EVENT_PARAMETERS_SEARCH_ACTION = "EmbeddedEventParametersSearch.do";
	public static final String EMBEDDED_EVENT_PARAMETERS_ADD_ACTION = "EmbeddedEventParametersAdd.do";
	public static final String EMBEDDED_EVENT_PARAMETERS_EDIT_ACTION = "EmbeddedEventParametersEdit.do";
	
	//	Constants required in TransferEventParameters.jsp Page
	public static final String TRANSFER_EVENT_PARAMETERS_SEARCH_ACTION = "TransferEventParametersSearch.do";
	public static final String TRANSFER_EVENT_PARAMETERS_ADD_ACTION = "TransferEventParametersAdd.do";
	public static final String TRANSFER_EVENT_PARAMETERS_EDIT_ACTION = "TransferEventParametersEdit.do";

//	Constants required in FixedEventParameters.jsp Page
	public static final String FIXED_EVENT_PARAMETERS_SEARCH_ACTION = "FixedEventParametersSearch.do";
	public static final String FIXED_EVENT_PARAMETERS_ADD_ACTION = "FixedEventParametersAdd.do";
	public static final String FIXED_EVENT_PARAMETERS_EDIT_ACTION = "FixedEventParametersEdit.do";

//	Constants required in ProcedureEventParameters.jsp Page
	public static final String PROCEDURE_EVENT_PARAMETERS_SEARCH_ACTION = "ProcedureEventParametersSearch.do";
	public static final String PROCEDURE_EVENT_PARAMETERS_ADD_ACTION = "ProcedureEventParametersAdd.do";
	public static final String PROCEDURE_EVENT_PARAMETERS_EDIT_ACTION = "ProcedureEventParametersEdit.do";
	
	//	Constants required in Distribution.jsp Page
	public static final String DISTRIBUTION_SEARCH_ACTION = "DistributionSearch.do";
	public static final String DISTRIBUTION_ADD_ACTION = "DistributionAdd.do";
	public static final String DISTRIBUTION_EDIT_ACTION = "DistributionEdit.do";
	
	public static final String SPECIMENARRAYTYPE_ADD_ACTION = "SpecimenArrayTypeAdd.do?operation=add";
	public static final String SPECIMENARRAYTYPE_EDIT_ACTION = "SpecimenArrayTypeEdit.do?operation=edit";
	public static final String ARRAY_DISTRIBUTION_ADD_ACTION = "ArrayDistributionAdd.do";
	
	
	//Spreadsheet Export Action
	public static final String SPREADSHEET_EXPORT_ACTION = "SpreadsheetExport.do";
	
	//Aliquots Action
	public static final String ALIQUOT_ACTION = "Aliquots.do";
	public static final String CREATE_ALIQUOT_ACTION = "CreateAliquots.do";
	
	//Constants related to Aliquots functionality
	public static final String PAGEOF_ALIQUOT = "pageOfAliquot";
	public static final String PAGEOF_CREATE_ALIQUOT = "pageOfCreateAliquot";
	public static final String PAGEOF_ALIQUOT_SUMMARY = "pageOfAliquotSummary";
	public static final String AVAILABLE_CONTAINER_MAP = "availableContainerMap";

	//Constants related to QuickEvents functionality
	public static final String QUICKEVENTS_ACTION = "QuickEventsSearch.do";	
	public static final String QUICKEVENTSPARAMETERS_ACTION = "ListSpecimenEventParameters.do";	
	
	//SimilarContainers Action
	public static final String SIMILAR_CONTAINERS_ACTION = "SimilarContainers.do";
	public static final String CREATE_SIMILAR_CONTAINERS_ACTION = "CreateSimilarContainers.do";
	public static final String SIMILAR_CONTAINERS_ADD_ACTION = "SimilarContainersAdd.do";
	
	//Constants related to Similar Containsers
	public static final String PAGEOF_SIMILAR_CONTAINERS = "pageOfSimilarContainers";
	public static final String PAGEOF_CREATE_SIMILAR_CONTAINERS = "pageOfCreateSimilarContainers";
	public static final String PAGEOF_STORAGE_CONTAINER = "pageOfStorageContainer";
	
	//Levels of nodes in query results tree.
	public static final int MAX_LEVEL = 5;
	public static final int MIN_LEVEL = 1;
	
	public static final String TABLE_NAME_COLUMN = "TABLE_NAME";
	
	//Spreadsheet view Constants in DataViewAction.
	public static final String PARTICIPANT = "Participant";
	public static final String ACCESSION = "Accession";
	public static final String QUERY_PARTICIPANT_SEARCH_ACTION = "QueryParticipantSearch.do?id=";
	public static final String QUERY_PARTICIPANT_EDIT_ACTION = "QueryParticipantEdit.do";
	public static final String QUERY_COLLECTION_PROTOCOL_SEARCH_ACTION = "QueryCollectionProtocolSearch.do?id=";
	public static final String QUERY_COLLECTION_PROTOCOL_EDIT_ACTION = "QueryCollectionProtocolEdit.do";
	public static final String QUERY_SPECIMEN_COLLECTION_GROUP_SEARCH_ACTION = "QuerySpecimenCollectionGroupSearch.do?id=";
	public static final String QUERY_SPECIMEN_COLLECTION_GROUP_EDIT_ACTION = "QuerySpecimenCollectionGroupEdit.do";
	public static final String QUERY_SPECIMEN_SEARCH_ACTION = "QuerySpecimenSearch.do?id=";
	public static final String QUERY_SPECIMEN_EDIT_ACTION = "QuerySpecimenEdit.do";
	//public static final String QUERY_ACCESSION_SEARCH_ACTION = "QueryAccessionSearch.do?id=";
	
	public static final String SPECIMEN = "Specimen";
	public static final String SEGMENT = "Segment";
	public static final String SAMPLE = "Sample";
	public static final String COLLECTION_PROTOCOL_REGISTRATION = "CollectionProtocolRegistration";
	public static final String PARTICIPANT_ID_COLUMN = "PARTICIPANT_ID";
	public static final String ACCESSION_ID_COLUMN = "ACCESSION_ID";
	public static final String SPECIMEN_ID_COLUMN = "SPECIMEN_ID";
	public static final String SEGMENT_ID_COLUMN = "SEGMENT_ID";
	public static final String SAMPLE_ID_COLUMN = "SAMPLE_ID";
	
	//For getting the tables for Simple Query and Fcon Query.
	public static final int ADVANCE_QUERY_TABLES = 2;
	
	//Identifiers for various Form beans
	public static final int DEFAULT_BIZ_LOGIC = 0;
	public static final int USER_FORM_ID = 1;
	public static final int ACCESSION_FORM_ID = 3;
	public static final int REPORTED_PROBLEM_FORM_ID = 4;
	public static final int INSTITUTION_FORM_ID = 5;
	public static final int APPROVE_USER_FORM_ID = 6;
	public static final int ACTIVITY_STATUS_FORM_ID = 7;
	public static final int DEPARTMENT_FORM_ID = 8;
	public static final int COLLECTION_PROTOCOL_FORM_ID = 9;
	public static final int DISTRIBUTIONPROTOCOL_FORM_ID = 10;
	public static final int STORAGE_CONTAINER_FORM_ID = 11;
	public static final int STORAGE_TYPE_FORM_ID = 12;
	public static final int SITE_FORM_ID = 13;
	public static final int CANCER_RESEARCH_GROUP_FORM_ID = 14;
	public static final int BIOHAZARD_FORM_ID = 15;
	public static final int FROZEN_EVENT_PARAMETERS_FORM_ID = 16;
	public static final int CHECKIN_CHECKOUT_EVENT_PARAMETERS_FORM_ID = 17;
	public static final int RECEIVED_EVENT_PARAMETERS_FORM_ID = 18;
	public static final int FLUID_SPECIMEN_REVIEW_EVENT_PARAMETERS_FORM_ID = 21;
	public static final int CELL_SPECIMEN_REVIEW_PARAMETERS_FORM_ID =23;
	public static final int TISSUE_SPECIMEN_REVIEW_EVENT_PARAMETERS_FORM_ID = 24;
	public static final int DISPOSAL_EVENT_PARAMETERS_FORM_ID = 25;
	public static final int THAW_EVENT_PARAMETERS_FORM_ID = 26;
	public static final int MOLECULAR_SPECIMEN_REVIEW_PARAMETERS_FORM_ID = 27;
	public static final int COLLECTION_EVENT_PARAMETERS_FORM_ID = 28;
	public static final int TRANSFER_EVENT_PARAMETERS_FORM_ID = 29;
	public static final int SPUN_EVENT_PARAMETERS_FORM_ID = 30;
	public static final int EMBEDDED_EVENT_PARAMETERS_FORM_ID = 31;
	public static final int FIXED_EVENT_PARAMETERS_FORM_ID = 32;	
	public static final int PROCEDURE_EVENT_PARAMETERS_FORM_ID = 33;
	public static final int CREATE_SPECIMEN_FORM_ID = 34;
	public static final int FORGOT_PASSWORD_FORM_ID = 35;
	public static final int SIGNUP_FORM_ID = 36;
	public static final int DISTRIBUTION_FORM_ID = 37;
	public static final int SPECIMEN_EVENT_PARAMETERS_FORM_ID = 38;
	public static final int SHOPPING_CART_FORM_ID = 39;
	public static final int CONFIGURE_RESULT_VIEW_ID = 41;
	public static final int ADVANCE_QUERY_INTERFACE_ID = 42;
	public static final int COLLECTION_PROTOCOL_REGISTRATION_FORM_ID = 19;
	public static final int PARTICIPANT_FORM_ID = 2;
	public static final int SPECIMEN_COLLECTION_GROUP_FORM_ID = 20;
	public static final int NEW_SPECIMEN_FORM_ID = 22;
	public static final int ALIQUOT_FORM_ID = 44;
	public static final int QUICKEVENTS_FORM_ID = 45;
	public static final int LIST_SPECIMEN_EVENT_PARAMETERS_FORM_ID = 46;
	public static final int SIMILAR_CONTAINERS_FORM_ID = 47;                  // chetan (13-07-2006)
	public static final int SPECIMEN_ARRAY_TYPE_FORM_ID = 48;
	public static final int ARRAY_DISTRIBUTION_FORM_ID = 49;
	public static final int SPECIMEN_ARRAY_FORM_ID = 50;
	
	//Misc
	public static final String SEPARATOR = " : ";
	
	//Identifiers for JDBC DAO.
	public static final int QUERY_RESULT_TREE_JDBC_DAO = 1;
	
	//Activity Status values
	public static final String ACTIVITY_STATUS_APPROVE = "Approve";
	public static final String ACTIVITY_STATUS_REJECT = "Reject";
	public static final String ACTIVITY_STATUS_NEW = "New";
	public static final String ACTIVITY_STATUS_PENDING = "Pending";
	
	//Approve User status values.
	public static final String APPROVE_USER_APPROVE_STATUS = "Approve";
	public static final String APPROVE_USER_REJECT_STATUS = "Reject";
	public static final String APPROVE_USER_PENDING_STATUS = "Pending";
	
	//Approve User Constants
	public static final int ZERO = 0;
	public static final int START_PAGE = 1;
	public static final int NUMBER_RESULTS_PER_PAGE = 5;
	public static final String PAGE_NUMBER = "pageNum";
	public static final String RESULTS_PER_PAGE = "numResultsPerPage"; 
	public static final String TOTAL_RESULTS = "totalResults";
	public static final String PREVIOUS_PAGE = "prevpage";
	public static final String NEXT_PAGE = "nextPage";
	public static final String ORIGINAL_DOMAIN_OBJECT_LIST = "originalDomainObjectList";
	public static final String SHOW_DOMAIN_OBJECT_LIST = "showDomainObjectList";
	public static final String USER_DETAILS = "details";
	public static final String CURRENT_RECORD = "currentRecord";
	public static final String APPROVE_USER_EMAIL_SUBJECT = "Your membership status in caTISSUE Core.";
	
	//Query Interface Results View Constants
	public static final String PAGEOF_APPROVE_USER = "pageOfApproveUser";
	public static final String PAGEOF_SIGNUP = "pageOfSignUp";
	public static final String PAGEOF_USERADD = "pageOfUserAdd";
	public static final String PAGEOF_USER_ADMIN = "pageOfUserAdmin";
	public static final String PAGEOF_USER_PROFILE = "pageOfUserProfile";
	public static final String PAGEOF_CHANGE_PASSWORD = "pageOfChangePassword";
	
	//For Simple Query Interface and Edit.
	public static final String PAGEOF_EDIT_OBJECT = "pageOfEditObject";
	
	//Query results view temporary table columns.
	public static final String QUERY_RESULTS_PARTICIPANT_ID = "PARTICIPANT_ID";
	public static final String QUERY_RESULTS_COLLECTION_PROTOCOL_ID = "COLLECTION_PROTOCOL_ID";
	public static final String QUERY_RESULTS_COLLECTION_PROTOCOL_EVENT_ID = "COLLECTION_PROTOCOL_EVENT_ID";
	public static final String QUERY_RESULTS_SPECIMEN_COLLECTION_GROUP_ID = "SPECIMEN_COLLECTION_GROUP_ID";
	public static final String QUERY_RESULTS_SPECIMEN_ID = "SPECIMEN_ID";
	public static final String QUERY_RESULTS_SPECIMEN_TYPE = "SPECIMEN_TYPE";
	
	// Assign Privilege Constants.
	public static final boolean PRIVILEGE_DEASSIGN = false;
	public static final String OPERATION_DISALLOW = "Disallow";
	
	//Constants for default column names to be shown for query result.
	public static final String[] DEFAULT_SPREADSHEET_COLUMNS = {
//	        	QUERY_RESULTS_PARTICIPANT_ID,QUERY_RESULTS_COLLECTION_PROTOCOL_ID,
//	        	QUERY_RESULTS_COLLECTION_PROTOCOL_EVENT_ID,QUERY_RESULTS_SPECIMEN_COLLECTION_GROUP_ID,
//	        	QUERY_RESULTS_SPECIMEN_ID,QUERY_RESULTS_SPECIMEN_TYPE
	        "IDENTIFIER","TYPE","ONE_DIMENSION_LABEL"
	};
	
	//Query results edit constants - MakeEditableAction.
	public static final String EDITABLE = "editable";
	
	//URL paths for Applet in TreeView.jsp
	public static final String QUERY_TREE_APPLET = "edu/wustl/common/treeApplet/TreeApplet.class";
	public static final String APPLET_CODEBASE = "Applet";
	
	//Shopping Cart
	public static final String SHOPPING_CART = "shoppingCart";
	
	public static final int SELECT_OPTION_VALUE = -1;
	
	public static final String [] TIME_HOUR_AMPM_ARRAY = {"AM","PM"}; 
	
//	Constants required in CollectionProtocol.jsp Page
	public static final String COLLECTIONPROTOCOL_SEARCH_ACTION = "CollectionProtocolSearch.do";
	public static final String COLLECTIONPROTOCOL_ADD_ACTION = "CollectionProtocolAdd.do";
	public static final String COLLECTIONPROTOCOL_EDIT_ACTION = "CollectionProtocolEdit.do";

//	Constants required in DistributionProtocol.jsp Page
	public static final String DISTRIBUTIONPROTOCOL_SEARCH_ACTION = "DistributionProtocolSearch.do";
	public static final String DISTRIBUTIONPROTOCOL_ADD_ACTION = "DistributionProtocolAdd.do";
	public static final String DISTRIBUTIONPROTOCOL_EDIT_ACTION = "DistributionProtocolEdit.do";
	
	public static final String [] ACTIVITY_STATUS_VALUES = {
	        SELECT_OPTION,
	        "Active",
	        "Closed",
			"Disabled"
	};

	public static final String [] SITE_ACTIVITY_STATUS_VALUES = {
	        SELECT_OPTION,
	        "Active",
	        "Closed"
	};

	public static final String [] USER_ACTIVITY_STATUS_VALUES = {
	        SELECT_OPTION,
	        "Active",
	        "Closed"
	};
	
	public static final String [] APPROVE_USER_STATUS_VALUES = {
	        SELECT_OPTION,
	        APPROVE_USER_APPROVE_STATUS,
	        APPROVE_USER_REJECT_STATUS,
	        APPROVE_USER_PENDING_STATUS,
	};

	public static final String [] REPORTED_PROBLEM_ACTIVITY_STATUS_VALUES = {
	        SELECT_OPTION,
	        "Closed",
	        "Pending"
	};

	public static final String TISSUE = "Tissue";
	public static final String FLUID = "Fluid";
	public static final String CELL = "Cell";
	public static final String MOLECULAR = "Molecular";
	
	public static final String [] SPECIMEN_TYPE_VALUES = {
	        SELECT_OPTION,
	        TISSUE,
	        FLUID,
	        CELL,
			MOLECULAR
	};
	
	public static final String [] HOUR_ARRAY = {
			"00",
			"1",
	        "2",
	        "3",
	        "4",
	        "5",
	        "6",
	        "7",
	        "8",
	        "9",
	        "10",
	        "11",
	        "12",
	        "13",
	        "14",
	        "15",
	        "16",
	        "17",
	        "18",
	        "19",
	        "20",
	        "21",
	        "22",
	        "23"
	        
	};

	
	public static final String [] MINUTES_ARRAY = {
	 		"00",
			"01",
			"02",
			"03",
			"04",
			"05",
			"06",
			"07",
			"08",
			"09",
			"10",
			"11",
			"12",
			"13",
			"14",
			"15",
			"16",
			"17",
			"18",
			"19",
			"20",
			"21",
			"22",
			"23",
			"24",
			"25",
			"26",
			"27",
			"28",
			"29",
			"30",
			"31",
			"32",
			"33",
			"34",
			"35",
			"36",
			"37",
			"38",
			"39",
			"40",
			"41",
			"42",
			"43",
			"44",
			"45",
			"46",
			"47",
			"48",
			"49",
			"50",
			"51",
			"52",
			"53",
			"54",
			"55",
			"56",
			"57",
			"58",
			"59"
	};
	
	public static final String UNIT_GM = "gm";
	public static final String UNIT_ML = "ml";
	public static final String UNIT_CC = "cell count";
	public static final String UNIT_MG = "�g";
	public static final String UNIT_CN = "count";
	public static final String UNIT_CL = "cells";
	
	public static final String CDE_NAME_CLINICAL_STATUS = "Clinical Status";
	public static final String CDE_NAME_GENDER = "Gender";
	public static final String CDE_NAME_GENOTYPE = "Genotype";
	public static final String CDE_NAME_SPECIMEN_CLASS = "Specimen";
	public static final String CDE_NAME_SPECIMEN_TYPE = "Specimen Type";
	public static final String CDE_NAME_TISSUE_SIDE = "Tissue Side";
	public static final String CDE_NAME_PATHOLOGICAL_STATUS = "Pathological Status";
	public static final String CDE_NAME_RECEIVED_QUALITY = "Received Quality";
	public static final String CDE_NAME_FIXATION_TYPE = "Fixation Type";
	public static final String CDE_NAME_COLLECTION_PROCEDURE = "Collection Procedure";
	public static final String CDE_NAME_CONTAINER = "Container";
	public static final String CDE_NAME_METHOD = "Method";
	public static final String CDE_NAME_EMBEDDING_MEDIUM = "Embedding Medium";
	public static final String CDE_NAME_BIOHAZARD = "Biohazard";
	public static final String CDE_NAME_ETHNICITY = "Ethnicity";
	public static final String CDE_NAME_RACE = "Race";
	public static final String CDE_VITAL_STATUS = "Vital Status";
	public static final String CDE_NAME_CLINICAL_DIAGNOSIS = "Clinical Diagnosis";
	public static final String CDE_NAME_SITE_TYPE = "Site Type";
	public static final String CDE_NAME_COUNTRY_LIST = "Countries";
	public static final String CDE_NAME_STATE_LIST = "States";
	public static final String CDE_NAME_HISTOLOGICAL_QUALITY = "Histological Quality";
	//Constants for Advanced Search
	public static final String STRING_OPERATORS = "StringOperators";
	public static final String DATE_NUMERIC_OPERATORS = "DateNumericOperators";
	public static final String ENUMERATED_OPERATORS = "EnumeratedOperators";
	public static final String MULTI_ENUMERATED_OPERATORS = "MultiEnumeratedOperators";
	
	public static final String [] STORAGE_STATUS_ARRAY = {
	        SELECT_OPTION,
			"CHECK IN",
			"CHECK OUT"
	};

	
	// constants for Data required in query
	public static final String ALIAS_NAME_TABLE_NAME_MAP="objectTableNames";
	public static final String SYSTEM_IDENTIFIER_COLUMN_NAME = "IDENTIFIER";
	public static final String NAME = "name";
	
	
	public static final String EVENT_PARAMETERS[] = {	Constants.SELECT_OPTION,
							"Cell Specimen Review",	"Check In Check Out", "Collection",
							"Disposal", "Embedded", "Fixed", "Fluid Specimen Review",
							"Frozen", "Molecular Specimen Review", "Procedure", "Received",
							"Spun", "Thaw", "Tissue Specimen Review", "Transfer" };
	
	public static final String EVENT_PARAMETERS_COLUMNS[] = { "Identifier",
											"Event Parameter", "User", "Date / Time", "PageOf"};
	
	public static final String [] SHOPPING_CART_COLUMNS = {"","Identifier", 
													"Type", "Subtype", "Tissue Site", "Tissue Side", "Pathological Status"}; 
	
	//Constants required in AssignPrivileges.jsp
	public static final String ASSIGN = "assignOperation";
	public static final String PRIVILEGES = "privileges";
	public static final String OBJECT_TYPES = "objectTypes";
	public static final String OBJECT_TYPE_VALUES = "objectTypeValues";
	public static final String RECORD_IDS = "recordIds";
	public static final String ATTRIBUTES = "attributes";
	public static final String GROUPS = "groups";
	public static final String USERS_FOR_USE_PRIVILEGE = "usersForUsePrivilege";
	public static final String USERS_FOR_READ_PRIVILEGE = "usersForReadPrivilege";
	public static final String ASSIGN_PRIVILEGES_ACTION = "AssignPrivileges.do";
	public static final int CONTAINER_IN_ANOTHER_CONTAINER = 2;
	    /**
     * @param id
     * @return
     */
    public static String getUserPGName(Long identifier)
    {
        if(identifier == null)
	    {
	        return "USER_";
	    }
	    return "USER_"+identifier;
    }

    /**
     * @param id
     * @return
     */
    public static String getUserGroupName(Long identifier)
    {
        if(identifier == null)
	    {
	        return "USER_";
	    }
	    return "USER_"+identifier;
    }
	
	//Mandar 25-Apr-06 : bug 1414 : Tissue units as per type
	// tissue types with unit= count
	public static final String FROZEN_TISSUE_BLOCK = "Frozen Tissue Block";	// PREVIOUS FROZEN BLOCK 
	public static final String FROZEN_TISSUE_SLIDE = "Frozen Tissue Slide";	// SLIDE	 
	public static final String FIXED_TISSUE_BLOCK = "Fixed Tissue Block";	// PARAFFIN BLOCK	 
	public static final String NOT_SPECIFIED = "Not Specified";
	// tissue types with unit= g
	public static final String FRESH_TISSUE = "Fresh Tissue";			 
	public static final String FROZEN_TISSUE = "Frozen Tissue";			 
	public static final String FIXED_TISSUE = "Fixed Tissue";			 
	public static final String FIXED_TISSUE_SLIDE = "Fixed Tissue Slide";
	//tissue types with unit= cc
	public static final String MICRODISSECTED = "Microdissected"; 
	
//	 constants required for Distribution Report
	public static final String CONFIGURATION_TABLES = "configurationTables";
	public static final String DISTRIBUTION_TABLE_AlIAS[] = {"CollectionProtReg","Participant","Specimen",
															 "SpecimenCollectionGroup","DistributedItem"};
	public static final String TABLE_COLUMN_DATA_MAP = "tableColumnDataMap";
	public static final String CONFIGURE_RESULT_VIEW_ACTION = "ConfigureResultView.do";
	public static final String TABLE_NAMES_LIST = "tableNamesList";
	public static final String COLUMN_NAMES_LIST = "columnNamesList";
	public static final String DISTRIBUTION_ID = "distributionId";
	public static final String CONFIGURE_DISTRIBUTION_ACTION = "ConfigureDistribution.do";
	public static final String DISTRIBUTION_REPORT_ACTION = "DistributionReport.do";
	public static final String ARRAY_DISTRIBUTION_REPORT_ACTION = "ArrayDistributionReport.do";
	
	public static final String DISTRIBUTION_REPORT_SAVE_ACTION="DistributionReportSave.do";
	public static final String SELECTED_COLUMNS[] = {"Specimen.IDENTIFIER.Identifier : Specimen",
													"Specimen.TYPE.Type : Specimen",
													"SpecimenCharacteristics.TISSUE_SITE.Tissue Site : Specimen",
													"SpecimenCharacteristics.TISSUE_SIDE.Tissue Side : Specimen",
													//"SpecimenCharacteristics.PATHOLOGICAL_STATUS.Pathological Status : Specimen",
													"DistributedItem.QUANTITY.Quantity : Distribution"};
	public static final String ARRAY_SELECTED_COLUMNS[] = {"SpecimenArray.IDENTIFIER.Identifier : SpecimenArray",
		"SpecimenArray.barcode.Barcode : SpecimenArray",
    };

	public static final String SPECIMEN_ID_LIST = "specimenIdList";
	public static final String DISTRIBUTION_ACTION = "Distribution.do?pageOf=pageOfDistribution";
	public static final String DISTRIBUTION_REPORT_NAME = "Distribution Report.csv";
	public static final String DISTRIBUTION_REPORT_FORM="distributionReportForm";
	public static final String DISTRIBUTED_ITEMS_DATA = "distributedItemsData";
	public static final String DISTRIBUTED_ITEM = "DistributedItem";
	//constants for Simple Query Interface Configuration
	public static final String CONFIGURE_SIMPLE_QUERY_ACTION = "ConfigureSimpleQuery.do";
	public static final String CONFIGURE_SIMPLE_QUERY_VALIDATE_ACTION = "ConfigureSimpleQueryValidate.do";
	public static final String CONFIGURE_SIMPLE_SEARCH_ACTION = "ConfigureSimpleSearch.do";
	public static final String SIMPLE_SEARCH_ACTION = "SimpleSearch.do";
	public static final String SIMPLE_SEARCH_AFTER_CONFIGURE_ACTION = "SimpleSearchAfterConfigure.do";
	public static final String PAGEOF_DISTRIBUTION = "pageOfDistribution";
	public static final String RESULT_VIEW_VECTOR = "resultViewVector";
	//public static final String SIMPLE_QUERY_COUNTER = "simpleQueryCount";
	
	public static final String UNDEFINED = "Undefined";
	public static final String UNKNOWN = "Unknown";
	public static final String UNSPECIFIED = "UnSpecified";
	public static final String SEARCH_RESULT = "SearchResult.csv";
	
//	Mandar : LightYellow and Green colors for CollectionProtocol SpecimenRequirements. Bug id: 587 
//	public static final String ODD_COLOR = "#FEFB85";
//	public static final String EVEN_COLOR = "#A7FEAB";
//	Light and dark shades of GREY.
	public static final String ODD_COLOR = "#E5E5E5";
	public static final String EVEN_COLOR = "#F7F7F7"; 
		
	
	
	// TO FORWARD THE REQUEST ON SUBMIT IF STATUS IS DISABLED
	public static final String BIO_SPECIMEN = "/ManageBioSpecimen.do";
	public static final String ADMINISTRATIVE = "/ManageAdministrativeData.do";
	public static final String PARENT_SPECIMEN_ID = "parentSpecimenId";
	public static final String COLLECTION_REGISTRATION_ID = "collectionProtocolId";
	
	public static final String FORWARDLIST = "forwardToList";
	public static final String [][] SPECIMEN_FORWARD_TO_LIST = {
			{"Submit",				"success"},
			{"Derive",			"createNew"},
			{"Add Events",				"eventParameters"},
			{"More",	"sameCollectionGroup"},
			{"Submit and Distribute", "distribution" }
	};
	public static final String [] SPECIMEN_BUTTON_TIPS = {
		"Submit only",
		"Submit and derive",
		"Submit and add events",
		"Submit and add more to same group",
		"Submit and distribute"
	};

	public static final String [][] SPECIMEN_COLLECTION_GROUP_FORWARD_TO_LIST = {
			{"Submit",				"success"},
			{"Add Specimen",			"createNewSpecimen"}
	};
	
	public static final String [][] PROTOCOL_REGISTRATION_FORWARD_TO_LIST = {
			{"Submit",							"success"},
			{"Specimen Collection Group",			"createSpecimenCollectionGroup"}
	};

	public static final String [][] PARTICIPANT_FORWARD_TO_LIST = {
			{"Submit Only",					"success"},
			{"Submit and Register to Protocol",	"createParticipantRegistration"}
	};
	
	public static final String [][] STORAGE_TYPE_FORWARD_TO_LIST = {
			{"Submit Only",					"success"},
			{"Submit and Add Container",	"storageContainer"}
	};
	
	//Constants Required for Advance Search
	//Tree related
	//public static final String PARTICIPANT ='Participant';
	public static final String[] ADVANCE_QUERY_TREE_HEIRARCHY={ //Represents the Advance Query tree Heirarchy.
			Constants.PARTICIPANT,
			Constants.COLLECTION_PROTOCOL,
			Constants.SPECIMEN_COLLECTION_GROUP,
			Constants.SPECIMEN
			};
	public static final String MENU_COLLECTION_PROTOCOL ="Collection Protocol";
	public static final String MENU_SPECIMEN_COLLECTION_GROUP ="Specimen Collection Group";
	public static final String MENU_DISTRIBUTION_PROTOCOL = "Distribution Protocol";
	
	public static final String SPECIMEN_COLLECTION_GROUP ="SpecimenCollectionGroup";
	public static final String DISTRIBUTION = "Distribution";
	public static final String DISTRIBUTION_PROTOCOL = "DistributionProtocol";
	public static final String CP = "CP";
	public static final String SCG = "SCG";
	public static final String D = "D";
	public static final String DP = "DP";
	public static final String C = "C";
	public static final String S = "S";
	public static final String P = "P";
	public static final String ADVANCED_CONDITIONS_QUERY_VIEW = "advancedCondtionsQueryView";
	public static final String ADVANCED_SEARCH_ACTION = "AdvanceSearch.do";
	public static final String ADVANCED_SEARCH_RESULTS_ACTION = "AdvanceSearchResults.do";
	public static final String CONFIGURE_ADVANCED_SEARCH_RESULTS_ACTION = "ConfigureAdvanceSearchResults.do";
	public static final String ADVANCED_QUERY_ADD = "Add";
	public static final String ADVANCED_QUERY_EDIT = "Edit";
	public static final String ADVANCED_QUERY_DELETE = "Delete";
	public static final String ADVANCED_QUERY_OPERATOR = "Operator";
	public static final String ADVANCED_QUERY_OR = "OR";
	public static final String ADVANCED_QUERY_AND = "pAND";
	public static final String EVENT_CONDITIONS = "eventConditions";
	
	public static final String IDENTIFIER_COLUMN_ID_MAP = "identifierColumnIdsMap";
	public static final String PAGEOF_PARTICIPANT_QUERY_EDIT= "pageOfParticipantQueryEdit";
	public static final String PAGEOF_COLLECTION_PROTOCOL_QUERY_EDIT= "pageOfCollectionProtocolQueryEdit";
	public static final String PAGEOF_SPECIMEN_COLLECTION_GROUP_QUERY_EDIT= "pageOfSpecimenCollectionGroupQueryEdit";
	public static final String PAGEOF_SPECIMEN_QUERY_EDIT= "pageOfSpecimenQueryEdit";
	public static final String PARTICIPANT_COLUMNS = "particpantColumns";
	public static final String COLLECTION_PROTOCOL_COLUMNS = "collectionProtocolColumns";
	public static final String SPECIMEN_COLLECTION_GROUP_COLUMNS = "SpecimenCollectionGroupColumns";
	public static final String SPECIMEN_COLUMNS = "SpecimenColumns";
	public static final String USER_ID_COLUMN = "USER_ID";
	
	public static final String GENERIC_SECURITYMANAGER_ERROR = "The Security Violation error occured during a database operation. Please report this problem to the adminstrator";
	
	public static final String BOOLEAN_YES = "Yes";
	public static final String BOOLEAN_NO = "No";
	
	public static final String PACKAGE_DOMAIN = "edu.wustl.catissuecore.domain";
	
	//Constants for isAuditable and isSecureUpdate required for Dao methods in Bozlogic
	public static final boolean IS_AUDITABLE_TRUE = true;
	public static final boolean IS_SECURE_UPDATE_TRUE = true;
	public static final boolean HAS_OBJECT_LEVEL_PRIVILEGE_FALSE = false;
	
	//Constants for HTTP-API
	public static final String CONTENT_TYPE = "CONTENT-TYPE";
	
	// For StorageContainer isFull status
	public static final String IS_CONTAINER_FULL_LIST = "isContainerFullList";
	public static final String [] IS_CONTAINER_FULL_VALUES = {
	        SELECT_OPTION,
	        "True",
	        "False"
	};
	
	public static final String STORAGE_CONTAINER_DIM_ONE_LABEL = "oneDimLabel";
	public static final String STORAGE_CONTAINER_DIM_TWO_LABEL = "twoDimLabel";
	
//    public static final String SPECIMEN_TYPE_TISSUE = "Tissue";
//    public static final String SPECIMEN_TYPE_FLUID = "Fluid";
//    public static final String SPECIMEN_TYPE_CELL = "Cell";
//    public static final String SPECIMEN_TYPE_MOL = "Molecular";
    public static final String SPECIMEN_TYPE_COUNT = "Count";
    public static final String SPECIMEN_TYPE_QUANTITY = "Quantity";
    public static final String SPECIMEN_TYPE_DETAILS = "Details";
    public static final String SPECIMEN_COUNT = "totalSpecimenCount";
    public static final String TOTAL = "Total";
    public static final String SPECIMENS = "Specimens";

	//User Roles
	public static final String TECHNICIAN = "Technician";
	public static final String SUPERVISOR = "Supervisor";
	public static final String SCIENTIST = "Scientist";
	
	public static final String CHILD_CONTAINER_TYPE = "childContainerType";
	public static final String UNUSED = "Unused";
	public static final String TYPE = "Type";
	
	//Mandar: 28-Apr-06 Bug 1129
	public static final String DUPLICATE_SPECIMEN="duplicateSpecimen";
	
	
	//Constants required in ParticipantLookupAction
	public static final String PARTICIPANT_LOOKUP_PARAMETER="ParticipantLookupParameter";
	public static final String PARTICIPANT_LOOKUP_CUTOFF="lookup.cutoff";
	public static final String PARTICIPANT_LOOKUP_ALGO="ParticipantLookupAlgo";
	public static final String PARTICIPANT_LOOKUP_SUCCESS="success";
	public static final String PARTICIPANT_ADD_FORWARD="participantAdd";
	public static final String PARTICIPANT_SYSTEM_IDENTIFIER="IDENTIFIER";
	public static final String PARTICIPANT_LAST_NAME="LAST_NAME";
	public static final String PARTICIPANT_FIRST_NAME="FIRST_NAME";
	public static final String PARTICIPANT_MIDDLE_NAME="MIDDLE_NAME";
	public static final String PARTICIPANT_BIRTH_DATE="BIRTH_DATE";
	public static final String PARTICIPANT_DEATH_DATE="DEATH_DATE";
	public static final String PARTICIPANT_VITAL_STATUS="VITAL_STATUS";
	public static final String PARTICIPANT_GENDER="GENDER";
	public static final String PARTICIPANT_SEX_GENOTYPE="SEX_GENOTYPE";
	public static final String PARTICIPANT_RACE="RACE";
	public static final String PARTICIPANT_ETHINICITY="ETHINICITY";
	public static final String PARTICIPANT_SOCIAL_SECURITY_NUMBER="SOCIAL_SECURITY_NUMBER";
	public static final String PARTICIPANT_PROBABLITY_MATCH="Probability";
	
	//Constants for integration of caTies and CAE with caTissue Core
	public static final String LINKED_DATA = "linkedData";
	public static final String APPLICATION_ID = "applicationId";
	public static final String CATIES = "caTies";
	public static final String CAE = "cae";
	public static final String EDIT_TAB_LINK = "editTabLink";
	public static final String CATIES_PUBLIC_SERVER_NAME = "CaTiesPublicServerName";
	public static final String CATIES_PRIVATE_SERVER_NAME = "CaTiesPrivateServerName";
	
	//Constants for StorageContainerMap Applet
	public static final String CONTAINER_STYLEID = "containerStyleId";
	public static final String XDIM_STYLEID = "xDimStyleId";
	public static final String YDIM_STYLEID = "yDimStyleId";
	
	//Constants for QuickEvents
	public static final String EVENT_SELECTED = "eventSelected";
	
	//Constant for SpecimenEvents page.
	public static final String EVENTS_TITLE_MESSAGE = "Existing events for this specimen";
	public static final String SURGICAL_PATHOLOGY_REPORT = "Surgical Pathology Report";
	public static final String CLINICAL_ANNOTATIONS = "Clinical Annotations";
	
	//Constants for Specimen Collection Group name- new field 
	public static final String RESET_NAME ="resetName";
	
	// Labels for Storage Containers
	public static final String[] STORAGE_CONTAINER_LABEL = {" Name"," Pos1"," Pos2"};
	//Constans for Any field
	public static final String HOLDS_ANY = "--All--";
	
	//Constants : Specimen -> lineage
	public static final String NEW_SPECIMEN = "New";
	public static final String DERIVED_SPECIMEN = "Derived";
	public static final String ALIQUOT = "Aliquot";
	
	//Constant for length of messageBody in Reported problem page  
	public static final int messageLength= 500;
	
//	public static final String getCollectionProtocolPIGroupName(Long identifier)
//	{
//	    if(identifier == null)
//	    {
//	        return "PI_COLLECTION_PROTOCOL_";
//	    }
//	    return "PI_COLLECTION_PROTOCOL_"+identifier;
//	}
//	
//	public static final String getCollectionProtocolCoordinatorGroupName(Long identifier)
//	{
//	    if(identifier == null)
//	    {
//	        return "COORDINATORS_COLLECTION_PROTOCOL_";
//	    }
//	    return "COORDINATORS_COLLECTION_PROTOCOL_"+identifier;
//	}
//	
//	public static final String getDistributionProtocolPIGroupName(Long identifier)
//	{
//	    if(identifier == null)
//	    {
//	        return "PI_DISTRIBUTION_PROTOCOL_";
//	    }
//	    return "PI_DISTRIBUTION_PROTOCOL_"+identifier;
//	}
//	
//	public static final String getCollectionProtocolPGName(Long identifier)
//	{
//	    if(identifier == null)
//	    {
//	        return "COLLECTION_PROTOCOL_";
//	    }
//	    return "COLLECTION_PROTOCOL_"+identifier;
//	}
//	
//	public static final String getDistributionProtocolPGName(Long identifier)
//	{
//	    if(identifier == null)
//	    {
//	        return "DISTRIBUTION_PROTOCOL_";
//	    }
//	    return "DISTRIBUTION_PROTOCOL_"+identifier;
//	}
	public static final String ALL = "All";
	
	public static final int SPECIMEN_DISTRIBUTION_TYPE = 1;
	public static final int SPECIMEN_ARRAY_DISTRIBUTION_TYPE = 2;

	public static final int BARCODE_BASED_DISTRIBUTION = 1;
	public static final int LABEL_BASED_DISTRIBUTION = 2;
	public static final String DISTRIBUTION_TYPE_LIST = "DISTRIBUTION_TYPE_LIST";
	public static final String DISTRIBUTION_BASED_ON = "DISTRIBUTION_BASED_ON";
}