package edu.wustl.catissuecore.reportloader;

public abstract class Parser
{
	/**
	 * @param fileName name of the file
	 * @throws Exception while parsing the report  
	 */
	public abstract void parse(String fileName)throws Exception;
	
	public static final String MSH = "MSH";
	public static final String PID = "PID";
	public static final String OBX = "OBX";
	public static final String OBR = "OBR";
	public static final String FTS = "FTS";
	
	public static final String MALE = "M";
	public static final String FEMALE = "F";
	
	//Report observation section constants  
	public static final String  FINAL_SECTION="FIN";
	public static final String  TEXT_SECTION="TX";
	
	public static final int  NAME_SECTION_INDEX=3;
	public static final int  DOCUMENT_FRAGMENT_INDEX=5;
	
	public static final int  PARTICIPANT_ETHNICITY_INDEX=10;
	public static final int  PARTICIPANT_MEDICAL_RECORD_INDEX=3;
	public static final int  PARTICIPANT_NAME_INDEX=5;
	public static final int  PARTICIPANT_DATEOFBIRTH_INDEX=7;
	public static final int  PARTICIPANT_GENDER_INDEX=8;
	public static final int  PARTICIPANT_SSN_INDEX=19;
	public static final int  PARTICIPANT_SITE_INDEX=2;

	public static final int  REPORT_ACCESSIONNUMBER_INDEX=3;
	public static final int  REPORT_DATE_INDEX=14;
	
	//Parser Types
	public static final String HL7_PARSER="HL7";
	
	
	//Constants for report status
	public static final String PENDING_FOR_DEID="PENDING_FOR_DEID";
	public static final String PENDING_FOR_XML="PENDING_FOR_XML";
	public static final String IDENTIFIED="IDENTIFIED";
	public static final String DEIDENTIFIED="DEIDENTIFIED";
	public static final String DEID_PROCESS_FAILED="DEID_PROCESS_FAILED";
	
	
	//constans for report queue
	public static final String PENDING="PENDING";
	public static final String NEW="ADDED_TO_QUEUE";
	public static final String FAILURE="FAILURE";
	public static final String CONFLICT = "CONFLICT";
	public static final String SITE_NOT_FOUND="SITE_NOT_FOUND";
	public static final String INVALID_REPORT_SECTION="INVALID_REPORT_SECTION";
	public static final String DB_ERROR="DB_ERROR";
	
	//dir constants
	public static final String INPUT_DIR="inputDir";
	public static final String BAD_FILE_DIR="badFilesDir";
	public static final String PROCESSED_FILE_DIR="processFileDir";
	
	public static final String POLLER_SLEEP="pollersleeptime";
	public static final String REPORTLOADER_QUEUE_SLEEP="reportloaderqueuesleep";
	public static final String SESSION_DATA="sessiondataloginname"; 
	
	//constants for Site info
	public static final String SITE_NAME="SITE_NAME";
	public static final String SITE_ABBRIVIATION="SITE_ABBRIVIATION";
	
	//constants for tag names in de-identified report elelment
	public static final String REPORT="Report";
	public static final String REPORT_TYPE="Report_Type";
	public static final String REPORT_HEADER="Report_Header";
	public static final String REPORT_ID="Report_ID";
	public static final String PATIENT_ID="Patient_ID";
	public static final String PARTICIPANT_NAME="participant_name";
	public static final String PARTICIPANT_ROLE="participant";
	public static final String PARTICIPANT_MRN="participant_mrn";
	public static final String REPORT_TEXT="Report_Text";
	public static final String HEADER_PERSON="Header_Person";
	public static final String HEADER_DATA="Header_Data";
	public static final String VARIABLE="Header_Variable";
	public static final String VALUE="Header_Value";
	public static final String ROLE="role";
	public static final String REPORT_ACCESSION_NUMBER="accession_number";
	
	public static final String REPORT_TYPE_VALUE="SP";

}
