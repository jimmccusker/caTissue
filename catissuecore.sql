drop table if exists CATISSUE_COLL_COORDINATORS
drop table if exists CATISSUE_CANCER_RESEARCH_GROUP
drop table if exists CATISSUE_COLLECTION_PROTOCOL
drop table if exists CATISSUE_EVENT_PARAM
drop table if exists CATISSUE_TRANSFER_EVENT_PARAM
drop table if exists CATISSUE_STOR_CONT_SPEC_CLASS
drop table if exists CATISSUE_COLL_EVENT_PARAM
drop table if exists CATISSUE_PASSWORD
drop table if exists CATISSUE_SPECIMEN_BIOHZ_REL
drop table if exists CATISSUE_MOL_SPE_REVIEW_PARAM
drop table if exists CATISSUE_STORAGE_TYPE
drop table if exists CATISSUE_CONTAINER
drop table if exists CATISSUE_DISTRIBUTION_SPE_REQ
drop table if exists CATISSUE_SITE
drop table if exists CATISSUE_EMBEDDED_EVENT_PARAM
drop table if exists CATISSUE_IN_OUT_EVENT_PARAM
drop table if exists CATISSUE_COLL_DISTRIBUTION_REL
drop table if exists CATISSUE_DISPOSAL_EVENT_PARAM
drop table if exists CATISSUE_SPECIMEN_REQUIREMENT
drop table if exists CATISSUE_SPECI_ARRAY_CONTENT
drop table if exists CATISSUE_INSTITUTION
drop table if exists CATISSUE_SPECIMEN_PROTOCOL
drop table if exists CATISSUE_FLUID_SPE_EVENT_PARAM
drop table if exists CATISSUE_BIOHAZARD
drop table if exists CATISSUE_QUANTITY
drop table if exists CATISSUE_SPUN_EVENT_PARAMETERS
drop table if exists CATISSUE_RECEIVED_EVENT_PARAM
drop table if exists CATISSUE_RACE
drop table if exists CATISSUE_CLINICAL_REPORT
drop table if exists CATISSUE_COLL_SPECIMEN_REQ
drop table if exists CATISSUE_ST_CONT_ST_TYPE_REL
drop table if exists CATISSUE_ADDRESS
drop table if exists CATISSUE_REPORTED_PROBLEM
drop table if exists CATISSUE_SPECIMEN_ARRAY
drop table if exists CATISSUE_CONT_HOLDS_SPARRTYPE
drop table if exists CATISSUE_ST_CONT_COLL_PROT_REL
drop table if exists CATISSUE_SPECIMEN_ARRAY_TYPE
drop table if exists CATISSUE_DISTRIBUTED_ITEM
drop table if exists CATISSUE_PARTICIPANT
drop table if exists CATISSUE_SPECIMEN_CHAR
drop table if exists CATISSUE_SPECIMEN_EVENT_PARAM
drop table if exists CATISSUE_STOR_TYPE_SPEC_CLASS
drop table if exists CATISSUE_COLL_PROT_EVENT
drop table if exists CATISSUE_CONTAINER_TYPE
drop table if exists CATISSUE_STOR_TYPE_HOLDS_TYPE
drop table if exists CATISSUE_CAPACITY
drop table if exists CATISSUE_PART_MEDICAL_ID
drop table if exists CATISSUE_CELL_SPE_REVIEW_PARAM
drop table if exists CATISSUE_STORAGE_CONTAINER
drop table if exists CATISSUE_DISTRIBUTION
drop table if exists CATISSUE_STORTY_HOLDS_SPARRTY
drop table if exists CATISSUE_PROCEDURE_EVENT_PARAM
drop table if exists CATISSUE_DISTRIBUTION_PROTOCOL
drop table if exists CATISSUE_DEPARTMENT
drop table if exists CATISSUE_EXTERNAL_IDENTIFIER
drop table if exists CATISSUE_FIXED_EVENT_PARAM
drop table if exists CATISSUE_THAW_EVENT_PARAMETERS
drop table if exists CATISSUE_COLL_PROT_REG
drop table if exists CATISSUE_FROZEN_EVENT_PARAM
drop table if exists CATISSUE_SPECIMEN
drop table if exists CATISSUE_USER
drop table if exists CATISSUE_TIS_SPE_EVENT_PARAM
drop table if exists CATISSUE_SPECIMEN_COLL_GROUP
drop table if exists CATISSUE_SPECIMEN_TYPE
create table CATISSUE_COLL_COORDINATORS (
   COLLECTION_PROTOCOL_ID BIGINT not null,
   USER_ID BIGINT not null,
   primary key (USER_ID, COLLECTION_PROTOCOL_ID)
)
create table CATISSUE_CANCER_RESEARCH_GROUP (
   IDENTIFIER BIGINT NOT NULL AUTO_INCREMENT,
   NAME VARCHAR(50) not null unique,
   primary key (IDENTIFIER)
)
create table CATISSUE_COLLECTION_PROTOCOL (
   IDENTIFIER BIGINT not null,
   ALIQUOT_IN_SAME_CONTAINER BIT,
   primary key (IDENTIFIER)
)
create table CATISSUE_EVENT_PARAM (
   IDENTIFIER BIGINT not null,
   primary key (IDENTIFIER)
)
create table CATISSUE_TRANSFER_EVENT_PARAM (
   IDENTIFIER BIGINT not null,
   FROM_POSITION_DIMENSION_ONE INTEGER,
   FROM_POSITION_DIMENSION_TWO INTEGER,
   TO_POSITION_DIMENSION_ONE INTEGER,
   TO_POSITION_DIMENSION_TWO INTEGER,
   TO_STORAGE_CONTAINER_ID BIGINT,
   FROM_STORAGE_CONTAINER_ID BIGINT,
   primary key (IDENTIFIER)
)
create table CATISSUE_STOR_CONT_SPEC_CLASS (
   STORAGE_CONTAINER_ID BIGINT not null,
   SPECIMEN_CLASS VARCHAR(50)
)
create table CATISSUE_COLL_EVENT_PARAM (
   IDENTIFIER BIGINT not null,
   COLLECTION_PROCEDURE VARCHAR(50),
   CONTAINER VARCHAR(50),
   primary key (IDENTIFIER)
)
create table CATISSUE_PASSWORD (
   IDENTIFIER BIGINT NOT NULL AUTO_INCREMENT,
   PASSWORD VARCHAR(50),
   UPDATE_DATE DATE,
   USER_ID BIGINT,
   primary key (IDENTIFIER)
)
create table CATISSUE_SPECIMEN_BIOHZ_REL (
   BIOHAZARD_ID BIGINT not null,
   SPECIMEN_ID BIGINT not null,
   primary key (SPECIMEN_ID, BIOHAZARD_ID)
)
create table CATISSUE_MOL_SPE_REVIEW_PARAM (
   IDENTIFIER BIGINT not null,
   GEL_IMAGE_URL VARCHAR(200),
   QUALITY_INDEX VARCHAR(50),
   LANE_NUMBER VARCHAR(50),
   GEL_NUMBER INTEGER,
   ABSORBANCE_AT_260 DOUBLE PRECISION,
   ABSORBANCE_AT_280 DOUBLE PRECISION,
   RATIO_28S_TO_18S DOUBLE PRECISION,
   primary key (IDENTIFIER)
)
create table CATISSUE_STORAGE_TYPE (
   IDENTIFIER BIGINT not null,
   DEFAULT_TEMPERATURE DOUBLE PRECISION,
   primary key (IDENTIFIER)
)
create table CATISSUE_CONTAINER (
   IDENTIFIER BIGINT NOT NULL AUTO_INCREMENT,
   ACTIVITY_STATUS VARCHAR(20),
   BARCODE VARCHAR(100),
   CAPACITY_ID BIGINT,
   PARENT_CONTAINER_ID BIGINT,
   COMMENTS TEXT,
   FULL BIT,
   NAME VARCHAR(100),
   POSITION_DIMENSION_ONE INTEGER,
   POSITION_DIMENSION_TWO INTEGER,
   primary key (IDENTIFIER)
)
create table CATISSUE_DISTRIBUTION_SPE_REQ (
   DISTRIBUTION_PROTOCOL_ID BIGINT not null,
   SPECIMEN_REQUIREMENT_ID BIGINT not null,
   primary key (SPECIMEN_REQUIREMENT_ID, DISTRIBUTION_PROTOCOL_ID)
)
create table CATISSUE_SITE (
   IDENTIFIER BIGINT NOT NULL AUTO_INCREMENT,
   NAME VARCHAR(50) not null unique,
   TYPE VARCHAR(50),
   EMAIL_ADDRESS VARCHAR(150),
   USER_ID BIGINT,
   ACTIVITY_STATUS VARCHAR(50),
   ADDRESS_ID BIGINT,
   primary key (IDENTIFIER)
)
create table CATISSUE_EMBEDDED_EVENT_PARAM (
   IDENTIFIER BIGINT not null,
   EMBEDDING_MEDIUM VARCHAR(50),
   primary key (IDENTIFIER)
)
create table CATISSUE_IN_OUT_EVENT_PARAM (
   IDENTIFIER BIGINT not null,
   STORAGE_STATUS VARCHAR(100) not null,
   primary key (IDENTIFIER)
)
create table CATISSUE_COLL_DISTRIBUTION_REL (
   COLLECTION_PROTOCOL_ID BIGINT not null,
   DISTRIBUTION_PROTOCOL_ID BIGINT not null,
   primary key (DISTRIBUTION_PROTOCOL_ID, COLLECTION_PROTOCOL_ID)
)
create table CATISSUE_DISPOSAL_EVENT_PARAM (
   IDENTIFIER BIGINT not null,
   REASON VARCHAR(50) not null,
   primary key (IDENTIFIER)
)
create table CATISSUE_SPECIMEN_REQUIREMENT (
   IDENTIFIER BIGINT NOT NULL AUTO_INCREMENT,
   SPECIMEN_TYPE VARCHAR(50),
   TISSUE_SITE VARCHAR(150),
   PATHOLOGY_STATUS VARCHAR(50),
   QUANTITY_ID BIGINT,
   SPECIMEN_CLASS VARCHAR(50),
   primary key (IDENTIFIER)
)
create table CATISSUE_SPECI_ARRAY_CONTENT (
   IDENTIFIER BIGINT NOT NULL AUTO_INCREMENT,
   CONC_IN_MICROGM_PER_MICROLTR DOUBLE PRECISION,
   INITIAL_QUANTITY_ID BIGINT,
   POSITION_DIMENSION_ONE INTEGER,
   POSITION_DIMENSION_TWO INTEGER,
   SPECIMEN_ID BIGINT,
   SPECIMEN_ARRAY_ID BIGINT,
   primary key (IDENTIFIER)
)
create table CATISSUE_INSTITUTION (
   IDENTIFIER BIGINT NOT NULL AUTO_INCREMENT,
   NAME VARCHAR(50) not null unique,
   primary key (IDENTIFIER)
)
create table CATISSUE_SPECIMEN_PROTOCOL (
   IDENTIFIER BIGINT NOT NULL AUTO_INCREMENT,
   PRINCIPAL_INVESTIGATOR_ID BIGINT,
   TITLE VARCHAR(150) not null unique,
   SHORT_TITLE VARCHAR(50),
   IRB_IDENTIFIER VARCHAR(50),
   START_DATE DATE,
   END_DATE DATE,
   ENROLLMENT INTEGER,
   DESCRIPTION_URL VARCHAR(200),
   ACTIVITY_STATUS VARCHAR(50),
   primary key (IDENTIFIER)
)
create table CATISSUE_FLUID_SPE_EVENT_PARAM (
   IDENTIFIER BIGINT not null,
   CELL_COUNT DOUBLE PRECISION,
   primary key (IDENTIFIER)
)
create table CATISSUE_BIOHAZARD (
   IDENTIFIER BIGINT NOT NULL AUTO_INCREMENT,
   NAME VARCHAR(50) not null unique,
   COMMENTS TEXT,
   TYPE VARCHAR(50),
   primary key (IDENTIFIER)
)
create table CATISSUE_QUANTITY (
   IDENTIFIER BIGINT NOT NULL AUTO_INCREMENT,
   QUANTITY DOUBLE PRECISION,
   primary key (IDENTIFIER)
)
create table CATISSUE_SPUN_EVENT_PARAMETERS (
   IDENTIFIER BIGINT not null,
   GFORCE DOUBLE PRECISION,
   DURATION_IN_MINUTES INTEGER,
   primary key (IDENTIFIER)
)
create table CATISSUE_RECEIVED_EVENT_PARAM (
   IDENTIFIER BIGINT not null,
   RECEIVED_QUALITY VARCHAR(255),
   primary key (IDENTIFIER)
)
create table CATISSUE_RACE (
   PARTICIPANT_ID BIGINT not null,
   RACE_NAME VARCHAR(50)
)
create table CATISSUE_CLINICAL_REPORT (
   IDENTIFIER BIGINT NOT NULL AUTO_INCREMENT,
   SURGICAL_PATHOLOGICAL_NUMBER VARCHAR(50),
   PARTICIPENT_MEDI_IDENTIFIER_ID BIGINT,
   primary key (IDENTIFIER)
)
create table CATISSUE_COLL_SPECIMEN_REQ (
   COLLECTION_PROTOCOL_EVENT_ID BIGINT not null,
   SPECIMEN_REQUIREMENT_ID BIGINT not null,
   primary key (SPECIMEN_REQUIREMENT_ID, COLLECTION_PROTOCOL_EVENT_ID)
)
create table CATISSUE_ST_CONT_ST_TYPE_REL (
   STORAGE_CONTAINER_ID BIGINT not null,
   STORAGE_TYPE_ID BIGINT not null,
   primary key (STORAGE_CONTAINER_ID, STORAGE_TYPE_ID)
)
create table CATISSUE_ADDRESS (
   IDENTIFIER BIGINT NOT NULL AUTO_INCREMENT,
   STREET VARCHAR(50),
   CITY VARCHAR(50),
   STATE VARCHAR(50),
   COUNTRY VARCHAR(50),
   ZIPCODE VARCHAR(30),
   PHONE_NUMBER VARCHAR(50),
   FAX_NUMBER VARCHAR(50),
   primary key (IDENTIFIER)
)
create table CATISSUE_REPORTED_PROBLEM (
   IDENTIFIER BIGINT NOT NULL AUTO_INCREMENT,
   AFFILIATION VARCHAR(200) not null,
   NAME_OF_REPORTER VARCHAR(200) not null,
   REPORTERS_EMAIL_ID VARCHAR(50) not null,
   MESSAGE_BODY TEXT not null,
   SUBJECT VARCHAR(100),
   REPORTED_DATE DATE,
   ACTIVITY_STATUS VARCHAR(100),
   COMMENTS TEXT,
   primary key (IDENTIFIER)
)
create table CATISSUE_SPECIMEN_ARRAY (
   IDENTIFIER BIGINT not null,
   CREATED_BY_ID BIGINT,
   SPECIMEN_ARRAY_TYPE_ID BIGINT,
   STORAGE_CONTAINER_ID BIGINT,
   AVAILABLE BIT,
   DISTRIBUTION_ID BIGINT,
   primary key (IDENTIFIER)
)
create table CATISSUE_CONT_HOLDS_SPARRTYPE (
   STORAGE_CONTAINER_ID BIGINT not null,
   SPECIMEN_ARRAY_TYPE_ID BIGINT not null,
   primary key (STORAGE_CONTAINER_ID, SPECIMEN_ARRAY_TYPE_ID)
)
create table CATISSUE_ST_CONT_COLL_PROT_REL (
   STORAGE_CONTAINER_ID BIGINT not null,
   COLLECTION_PROTOCOL_ID BIGINT not null,
   primary key (STORAGE_CONTAINER_ID, COLLECTION_PROTOCOL_ID)
)
create table CATISSUE_SPECIMEN_ARRAY_TYPE (
   IDENTIFIER BIGINT not null,
   SPECIMEN_CLASS VARCHAR(50),
   primary key (IDENTIFIER)
)
create table CATISSUE_DISTRIBUTED_ITEM (
   IDENTIFIER BIGINT NOT NULL AUTO_INCREMENT,
   QUANTITY DOUBLE PRECISION,
   SPECIMEN_ID BIGINT,
   DISTRIBUTION_ID BIGINT,
   primary key (IDENTIFIER)
)
create table CATISSUE_PARTICIPANT (
   IDENTIFIER BIGINT NOT NULL AUTO_INCREMENT,
   LAST_NAME VARCHAR(50),
   FIRST_NAME VARCHAR(50),
   MIDDLE_NAME VARCHAR(50),
   BIRTH_DATE DATE,
   GENDER VARCHAR(20),
   GENOTYPE VARCHAR(50),
   ETHNICITY VARCHAR(50),
   SOCIAL_SECURITY_NUMBER VARCHAR(50) unique,
   ACTIVITY_STATUS VARCHAR(20),
   DEATH_DATE DATE,
   VITAL_STATUS VARCHAR(50),
   primary key (IDENTIFIER)
)
create table CATISSUE_SPECIMEN_CHAR (
   IDENTIFIER BIGINT NOT NULL AUTO_INCREMENT,
   TISSUE_SITE VARCHAR(150),
   TISSUE_SIDE VARCHAR(50),
   primary key (IDENTIFIER)
)
create table CATISSUE_SPECIMEN_EVENT_PARAM (
   IDENTIFIER BIGINT NOT NULL AUTO_INCREMENT,
   SPECIMEN_ID BIGINT,
   EVENT_TIMESTAMP DATETIME,
   USER_ID BIGINT,
   COMMENTS TEXT,
   primary key (IDENTIFIER)
)
create table CATISSUE_STOR_TYPE_SPEC_CLASS (
   STORAGE_TYPE_ID BIGINT not null,
   SPECIMEN_CLASS VARCHAR(50)
)
create table CATISSUE_COLL_PROT_EVENT (
   IDENTIFIER BIGINT NOT NULL AUTO_INCREMENT,
   CLINICAL_STATUS VARCHAR(50),
   STUDY_CALENDAR_EVENT_POINT DOUBLE PRECISION,
   COLLECTION_PROTOCOL_ID BIGINT,
   primary key (IDENTIFIER)
)
create table CATISSUE_CONTAINER_TYPE (
   IDENTIFIER BIGINT NOT NULL AUTO_INCREMENT,
   CAPACITY_ID BIGINT,
   NAME VARCHAR(100),
   ONE_DIMENSION_LABEL VARCHAR(100),
   TWO_DIMENSION_LABEL VARCHAR(100),
   COMMENTS TEXT,
   ACTIVITY_STATUS VARCHAR(30),
   primary key (IDENTIFIER)
)
create table CATISSUE_STOR_TYPE_HOLDS_TYPE (
   HOLDS_STORAGE_TYPE_ID BIGINT not null,
   STORAGE_TYPE_ID BIGINT not null,
   primary key (HOLDS_STORAGE_TYPE_ID, STORAGE_TYPE_ID)
)
create table CATISSUE_CAPACITY (
   IDENTIFIER BIGINT NOT NULL AUTO_INCREMENT,
   ONE_DIMENSION_CAPACITY INTEGER,
   TWO_DIMENSION_CAPACITY INTEGER,
   primary key (IDENTIFIER)
)
create table CATISSUE_PART_MEDICAL_ID (
   IDENTIFIER BIGINT NOT NULL AUTO_INCREMENT,
   MEDICAL_RECORD_NUMBER VARCHAR(50),
   SITE_ID BIGINT,
   PARTICIPANT_ID BIGINT,
   primary key (IDENTIFIER)
)
create table CATISSUE_CELL_SPE_REVIEW_PARAM (
   IDENTIFIER BIGINT not null,
   NEOPLASTIC_CELLULARITY_PER DOUBLE PRECISION,
   VIABLE_CELL_PERCENTAGE DOUBLE PRECISION,
   primary key (IDENTIFIER)
)
create table CATISSUE_STORAGE_CONTAINER (
   IDENTIFIER BIGINT not null,
   SITE_ID BIGINT,
   TEMPERATURE DOUBLE PRECISION,
   STORAGE_TYPE_ID BIGINT,
   primary key (IDENTIFIER)
)
create table CATISSUE_DISTRIBUTION (
   IDENTIFIER BIGINT NOT NULL AUTO_INCREMENT,
   TO_SITE_ID BIGINT,
   DISTRIBUTION_PROTOCOL_ID BIGINT,
   ACTIVITY_STATUS VARCHAR(50),
   SPECIMEN_ID BIGINT,
   EVENT_TIMESTAMP DATETIME,
   USER_ID BIGINT,
   COMMENTS TEXT,
   primary key (IDENTIFIER)
)
create table CATISSUE_STORTY_HOLDS_SPARRTY (
   STORAGE_TYPE_ID BIGINT not null,
   SPECIMEN_ARRAY_TYPE_ID BIGINT not null,
   primary key (STORAGE_TYPE_ID, SPECIMEN_ARRAY_TYPE_ID)
)
create table CATISSUE_PROCEDURE_EVENT_PARAM (
   IDENTIFIER BIGINT not null,
   URL VARCHAR(200) not null,
   NAME VARCHAR(50) not null,
   primary key (IDENTIFIER)
)
create table CATISSUE_DISTRIBUTION_PROTOCOL (
   IDENTIFIER BIGINT not null,
   primary key (IDENTIFIER)
)
create table CATISSUE_DEPARTMENT (
   IDENTIFIER BIGINT NOT NULL AUTO_INCREMENT,
   NAME VARCHAR(50) not null unique,
   primary key (IDENTIFIER)
)
create table CATISSUE_EXTERNAL_IDENTIFIER (
   IDENTIFIER BIGINT NOT NULL AUTO_INCREMENT,
   NAME VARCHAR(50),
   VALUE VARCHAR(50),
   SPECIMEN_ID BIGINT,
   primary key (IDENTIFIER)
)
create table CATISSUE_FIXED_EVENT_PARAM (
   IDENTIFIER BIGINT not null,
   FIXATION_TYPE VARCHAR(50) not null,
   DURATION_IN_MINUTES INTEGER,
   primary key (IDENTIFIER)
)
create table CATISSUE_THAW_EVENT_PARAMETERS (
   IDENTIFIER BIGINT not null,
   primary key (IDENTIFIER)
)
create table CATISSUE_COLL_PROT_REG (
   IDENTIFIER BIGINT NOT NULL AUTO_INCREMENT,
   PROTOCOL_PARTICIPANT_ID VARCHAR(50),
   REGISTRATION_DATE DATE,
   PARTICIPANT_ID BIGINT,
   COLLECTION_PROTOCOL_ID BIGINT,
   ACTIVITY_STATUS VARCHAR(20),
   primary key (IDENTIFIER)
)
create table CATISSUE_FROZEN_EVENT_PARAM (
   IDENTIFIER BIGINT not null,
   METHOD VARCHAR(50),
   primary key (IDENTIFIER)
)
create table CATISSUE_SPECIMEN (
   IDENTIFIER BIGINT NOT NULL AUTO_INCREMENT,
   SPECIMEN_CLASS VARCHAR(255) not null,
   TYPE VARCHAR(50),
   AVAILABLE BIT,
   POSITION_DIMENSION_ONE INTEGER,
   POSITION_DIMENSION_TWO INTEGER,
   BARCODE VARCHAR(50) unique,
   COMMENTS VARCHAR(200),
   ACTIVITY_STATUS VARCHAR(50),
   PARENT_SPECIMEN_ID BIGINT,
   STORAGE_CONTAINER_IDENTIFIER BIGINT,
   SPECIMEN_COLLECTION_GROUP_ID BIGINT,
   SPECIMEN_CHARACTERISTICS_ID BIGINT,
   AVAILABLE_QUANTITY DOUBLE PRECISION,
   QUANTITY DOUBLE PRECISION,
   PATHOLOGICAL_STATUS VARCHAR(50),
   LABEL VARCHAR(50),
   LINEAGE VARCHAR(50),
   CONCENTRATION DOUBLE PRECISION,
   primary key (IDENTIFIER)
)
create table CATISSUE_USER (
   IDENTIFIER BIGINT NOT NULL AUTO_INCREMENT,
   EMAIL_ADDRESS VARCHAR(100),
   FIRST_NAME VARCHAR(50),
   LAST_NAME VARCHAR(50),
   LOGIN_NAME VARCHAR(50) not null unique,
   START_DATE DATE,
   ACTIVITY_STATUS VARCHAR(50),
   DEPARTMENT_ID BIGINT,
   CANCER_RESEARCH_GROUP_ID BIGINT,
   INSTITUTION_ID BIGINT,
   ADDRESS_ID BIGINT,
   CSM_USER_ID BIGINT,
   STATUS_COMMENT TEXT,
   primary key (IDENTIFIER)
)
create table CATISSUE_TIS_SPE_EVENT_PARAM (
   IDENTIFIER BIGINT not null,
   NEOPLASTIC_CELLULARITY_PER DOUBLE PRECISION,
   NECROSIS_PERCENTAGE DOUBLE PRECISION,
   LYMPHOCYTIC_PERCENTAGE DOUBLE PRECISION,
   TOTAL_CELLULARITY_PERCENTAGE DOUBLE PRECISION,
   HISTOLOGICAL_QUALITY VARCHAR(50),
   primary key (IDENTIFIER)
)
create table CATISSUE_SPECIMEN_COLL_GROUP (
   IDENTIFIER BIGINT NOT NULL AUTO_INCREMENT,
   NAME VARCHAR(55),
   CLINICAL_DIAGNOSIS VARCHAR(150),
   CLINICAL_STATUS VARCHAR(50),
   ACTIVITY_STATUS VARCHAR(50),
   SITE_ID BIGINT,
   COLLECTION_PROTOCOL_EVENT_ID BIGINT,
   CLINICAL_REPORT_ID BIGINT,
   COLLECTION_PROTOCOL_REG_ID BIGINT,
   COLL_PROT_REG_ID BIGINT,
   primary key (IDENTIFIER)
)
create table CATISSUE_SPECIMEN_TYPE (
   SPECIMEN_ARRAY_TYPE_ID BIGINT not null,
   SPECIMEN_TYPE VARCHAR(50)
)
alter table CATISSUE_COLL_COORDINATORS add index (COLLECTION_PROTOCOL_ID), add constraint FKE490E33A48304401 foreign key (COLLECTION_PROTOCOL_ID) references CATISSUE_COLLECTION_PROTOCOL (IDENTIFIER)
alter table CATISSUE_COLL_COORDINATORS add index (USER_ID), add constraint FKE490E33A2206F20F foreign key (USER_ID) references CATISSUE_USER (IDENTIFIER)
alter table CATISSUE_COLLECTION_PROTOCOL add index (IDENTIFIER), add constraint FK32DC439DBC7298A9 foreign key (IDENTIFIER) references CATISSUE_SPECIMEN_PROTOCOL (IDENTIFIER)
alter table CATISSUE_EVENT_PARAM add index (IDENTIFIER), add constraint FK90C79AECBC7298A9 foreign key (IDENTIFIER) references CATISSUE_SPECIMEN_EVENT_PARAM (IDENTIFIER)
alter table CATISSUE_TRANSFER_EVENT_PARAM add index (TO_STORAGE_CONTAINER_ID), add constraint FK71F9AC103C2DAC61 foreign key (TO_STORAGE_CONTAINER_ID) references CATISSUE_STORAGE_CONTAINER (IDENTIFIER)
alter table CATISSUE_TRANSFER_EVENT_PARAM add index (FROM_STORAGE_CONTAINER_ID), add constraint FK71F9AC1099DF0A92 foreign key (FROM_STORAGE_CONTAINER_ID) references CATISSUE_STORAGE_CONTAINER (IDENTIFIER)
alter table CATISSUE_TRANSFER_EVENT_PARAM add index (IDENTIFIER), add constraint FK71F9AC10BC7298A9 foreign key (IDENTIFIER) references CATISSUE_SPECIMEN_EVENT_PARAM (IDENTIFIER)
alter table CATISSUE_STOR_CONT_SPEC_CLASS add index (STORAGE_CONTAINER_ID), add constraint FKE7F5E8C2B3DFB11D foreign key (STORAGE_CONTAINER_ID) references CATISSUE_STORAGE_CONTAINER (IDENTIFIER)
alter table CATISSUE_COLL_EVENT_PARAM add index (IDENTIFIER), add constraint FKF9888F91BC7298A9 foreign key (IDENTIFIER) references CATISSUE_SPECIMEN_EVENT_PARAM (IDENTIFIER)
alter table CATISSUE_PASSWORD add index (USER_ID), add constraint FKDE1F38972206F20F foreign key (USER_ID) references CATISSUE_USER (IDENTIFIER)
alter table CATISSUE_SPECIMEN_BIOHZ_REL add index (BIOHAZARD_ID), add constraint FK7A3F5539F398D480 foreign key (BIOHAZARD_ID) references CATISSUE_BIOHAZARD (IDENTIFIER)
alter table CATISSUE_SPECIMEN_BIOHZ_REL add index (SPECIMEN_ID), add constraint FK7A3F553960773DB2 foreign key (SPECIMEN_ID) references CATISSUE_SPECIMEN (IDENTIFIER)
alter table CATISSUE_MOL_SPE_REVIEW_PARAM add index (IDENTIFIER), add constraint FK5280ECEBC7298A9 foreign key (IDENTIFIER) references CATISSUE_EVENT_PARAM (IDENTIFIER)
alter table CATISSUE_STORAGE_TYPE add index (IDENTIFIER), add constraint FKE9A0629ABC7298A9 foreign key (IDENTIFIER) references CATISSUE_CONTAINER_TYPE (IDENTIFIER)
alter table CATISSUE_CONTAINER add index (PARENT_CONTAINER_ID), add constraint FK49B8DE5DB097B2E foreign key (PARENT_CONTAINER_ID) references CATISSUE_CONTAINER (IDENTIFIER)
alter table CATISSUE_CONTAINER add index (CAPACITY_ID), add constraint FK49B8DE5DAC76C0 foreign key (CAPACITY_ID) references CATISSUE_CAPACITY (IDENTIFIER)
alter table CATISSUE_DISTRIBUTION_SPE_REQ add index (SPECIMEN_REQUIREMENT_ID), add constraint FKE34A3688BE10F0CE foreign key (SPECIMEN_REQUIREMENT_ID) references CATISSUE_SPECIMEN_REQUIREMENT (IDENTIFIER)
alter table CATISSUE_DISTRIBUTION_SPE_REQ add index (DISTRIBUTION_PROTOCOL_ID), add constraint FKE34A36886B1F36E7 foreign key (DISTRIBUTION_PROTOCOL_ID) references CATISSUE_DISTRIBUTION_PROTOCOL (IDENTIFIER)
alter table CATISSUE_SITE add index (ADDRESS_ID), add constraint FKB024C3436CD94566 foreign key (ADDRESS_ID) references CATISSUE_ADDRESS (IDENTIFIER)
alter table CATISSUE_SITE add index (USER_ID), add constraint FKB024C3432206F20F foreign key (USER_ID) references CATISSUE_USER (IDENTIFIER)
alter table CATISSUE_EMBEDDED_EVENT_PARAM add index (IDENTIFIER), add constraint FKD356182FBC7298A9 foreign key (IDENTIFIER) references CATISSUE_SPECIMEN_EVENT_PARAM (IDENTIFIER)
alter table CATISSUE_IN_OUT_EVENT_PARAM add index (IDENTIFIER), add constraint FK4F0FAEB9BC7298A9 foreign key (IDENTIFIER) references CATISSUE_SPECIMEN_EVENT_PARAM (IDENTIFIER)
alter table CATISSUE_COLL_DISTRIBUTION_REL add index (DISTRIBUTION_PROTOCOL_ID), add constraint FK1483BCB56B1F36E7 foreign key (DISTRIBUTION_PROTOCOL_ID) references CATISSUE_DISTRIBUTION_PROTOCOL (IDENTIFIER)
alter table CATISSUE_COLL_DISTRIBUTION_REL add index (COLLECTION_PROTOCOL_ID), add constraint FK1483BCB548304401 foreign key (COLLECTION_PROTOCOL_ID) references CATISSUE_COLLECTION_PROTOCOL (IDENTIFIER)
alter table CATISSUE_DISPOSAL_EVENT_PARAM add index (IDENTIFIER), add constraint FK1BC818D6BC7298A9 foreign key (IDENTIFIER) references CATISSUE_SPECIMEN_EVENT_PARAM (IDENTIFIER)
alter table CATISSUE_SPECIMEN_REQUIREMENT add index (QUANTITY_ID), add constraint FK39AFE96861A1C94F foreign key (QUANTITY_ID) references CATISSUE_QUANTITY (IDENTIFIER)
alter table CATISSUE_SPECI_ARRAY_CONTENT add index (INITIAL_QUANTITY_ID), add constraint FK363E164692AB74B4 foreign key (INITIAL_QUANTITY_ID) references CATISSUE_QUANTITY (IDENTIFIER)
alter table CATISSUE_SPECI_ARRAY_CONTENT add index (SPECIMEN_ARRAY_ID), add constraint FK363E1646C4A3C438 foreign key (SPECIMEN_ARRAY_ID) references CATISSUE_SPECIMEN_ARRAY (IDENTIFIER)
alter table CATISSUE_SPECI_ARRAY_CONTENT add index (SPECIMEN_ID), add constraint FK363E164660773DB2 foreign key (SPECIMEN_ID) references CATISSUE_SPECIMEN (IDENTIFIER)
alter table CATISSUE_SPECIMEN_PROTOCOL add index (PRINCIPAL_INVESTIGATOR_ID), add constraint FKB8481373870EB740 foreign key (PRINCIPAL_INVESTIGATOR_ID) references CATISSUE_USER (IDENTIFIER)
alter table CATISSUE_FLUID_SPE_EVENT_PARAM add index (IDENTIFIER), add constraint FK70565D20BC7298A9 foreign key (IDENTIFIER) references CATISSUE_EVENT_PARAM (IDENTIFIER)
alter table CATISSUE_SPUN_EVENT_PARAMETERS add index (IDENTIFIER), add constraint FK312D77BCBC7298A9 foreign key (IDENTIFIER) references CATISSUE_SPECIMEN_EVENT_PARAM (IDENTIFIER)
alter table CATISSUE_RECEIVED_EVENT_PARAM add index (IDENTIFIER), add constraint FKA7139D06BC7298A9 foreign key (IDENTIFIER) references CATISSUE_SPECIMEN_EVENT_PARAM (IDENTIFIER)
alter table CATISSUE_RACE add index (PARTICIPANT_ID), add constraint FKB0242ECD87E5ADC7 foreign key (PARTICIPANT_ID) references CATISSUE_PARTICIPANT (IDENTIFIER)
alter table CATISSUE_CLINICAL_REPORT add index (PARTICIPENT_MEDI_IDENTIFIER_ID), add constraint FK54A4264515246F7 foreign key (PARTICIPENT_MEDI_IDENTIFIER_ID) references CATISSUE_PART_MEDICAL_ID (IDENTIFIER)
alter table CATISSUE_COLL_SPECIMEN_REQ add index (SPECIMEN_REQUIREMENT_ID), add constraint FK860E6ABEBE10F0CE foreign key (SPECIMEN_REQUIREMENT_ID) references CATISSUE_SPECIMEN_REQUIREMENT (IDENTIFIER)
alter table CATISSUE_COLL_SPECIMEN_REQ add index (COLLECTION_PROTOCOL_EVENT_ID), add constraint FK860E6ABE53B01F66 foreign key (COLLECTION_PROTOCOL_EVENT_ID) references CATISSUE_COLL_PROT_EVENT (IDENTIFIER)
alter table CATISSUE_ST_CONT_ST_TYPE_REL add index (STORAGE_CONTAINER_ID), add constraint FKF3393727B3DFB11D foreign key (STORAGE_CONTAINER_ID) references CATISSUE_STORAGE_CONTAINER (IDENTIFIER)
alter table CATISSUE_ST_CONT_ST_TYPE_REL add index (STORAGE_TYPE_ID), add constraint FKF339372759A3CE5C foreign key (STORAGE_TYPE_ID) references CATISSUE_STORAGE_TYPE (IDENTIFIER)
alter table CATISSUE_SPECIMEN_ARRAY add index (CREATED_BY_ID), add constraint FKECBF8B3E64B129CC foreign key (CREATED_BY_ID) references CATISSUE_USER (IDENTIFIER)
alter table CATISSUE_SPECIMEN_ARRAY add index (DISTRIBUTION_ID), add constraint FKECBF8B3EF8278B6 foreign key (DISTRIBUTION_ID) references CATISSUE_DISTRIBUTION (IDENTIFIER)
alter table CATISSUE_SPECIMEN_ARRAY add index (IDENTIFIER), add constraint FKECBF8B3EBC7298A9 foreign key (IDENTIFIER) references CATISSUE_CONTAINER (IDENTIFIER)
alter table CATISSUE_SPECIMEN_ARRAY add index (STORAGE_CONTAINER_ID), add constraint FKECBF8B3EB3DFB11D foreign key (STORAGE_CONTAINER_ID) references CATISSUE_STORAGE_CONTAINER (IDENTIFIER)
alter table CATISSUE_SPECIMEN_ARRAY add index (SPECIMEN_ARRAY_TYPE_ID), add constraint FKECBF8B3EECE89343 foreign key (SPECIMEN_ARRAY_TYPE_ID) references CATISSUE_SPECIMEN_ARRAY_TYPE (IDENTIFIER)
alter table CATISSUE_CONT_HOLDS_SPARRTYPE add index (SPECIMEN_ARRAY_TYPE_ID), add constraint FKDC7E31E2ECE89343 foreign key (SPECIMEN_ARRAY_TYPE_ID) references CATISSUE_SPECIMEN_ARRAY_TYPE (IDENTIFIER)
alter table CATISSUE_CONT_HOLDS_SPARRTYPE add index (STORAGE_CONTAINER_ID), add constraint FKDC7E31E2B3DFB11D foreign key (STORAGE_CONTAINER_ID) references CATISSUE_STORAGE_CONTAINER (IDENTIFIER)
alter table CATISSUE_ST_CONT_COLL_PROT_REL add index (COLLECTION_PROTOCOL_ID), add constraint FK5A00CB6948304401 foreign key (COLLECTION_PROTOCOL_ID) references CATISSUE_COLLECTION_PROTOCOL (IDENTIFIER)
alter table CATISSUE_ST_CONT_COLL_PROT_REL add index (STORAGE_CONTAINER_ID), add constraint FK5A00CB69B3DFB11D foreign key (STORAGE_CONTAINER_ID) references CATISSUE_STORAGE_CONTAINER (IDENTIFIER)
alter table CATISSUE_SPECIMEN_ARRAY_TYPE add index (IDENTIFIER), add constraint FKD36E0B9BBC7298A9 foreign key (IDENTIFIER) references CATISSUE_CONTAINER_TYPE (IDENTIFIER)
alter table CATISSUE_DISTRIBUTED_ITEM add index (SPECIMEN_ID), add constraint FKA7C3ED4B60773DB2 foreign key (SPECIMEN_ID) references CATISSUE_SPECIMEN (IDENTIFIER)
alter table CATISSUE_DISTRIBUTED_ITEM add index (DISTRIBUTION_ID), add constraint FKA7C3ED4BF8278B6 foreign key (DISTRIBUTION_ID) references CATISSUE_DISTRIBUTION (IDENTIFIER)
alter table CATISSUE_SPECIMEN_EVENT_PARAM add index (SPECIMEN_ID), add constraint FK753F33AD60773DB2 foreign key (SPECIMEN_ID) references CATISSUE_SPECIMEN (IDENTIFIER)
alter table CATISSUE_SPECIMEN_EVENT_PARAM add index (USER_ID), add constraint FK753F33AD2206F20F foreign key (USER_ID) references CATISSUE_USER (IDENTIFIER)
alter table CATISSUE_STOR_TYPE_SPEC_CLASS add index (STORAGE_TYPE_ID), add constraint FK1BCF33BA59A3CE5C foreign key (STORAGE_TYPE_ID) references CATISSUE_STORAGE_TYPE (IDENTIFIER)
alter table CATISSUE_COLL_PROT_EVENT add index (COLLECTION_PROTOCOL_ID), add constraint FK7AE7715948304401 foreign key (COLLECTION_PROTOCOL_ID) references CATISSUE_COLLECTION_PROTOCOL (IDENTIFIER)
alter table CATISSUE_CONTAINER_TYPE add index (CAPACITY_ID), add constraint FKCBBC9954DAC76C0 foreign key (CAPACITY_ID) references CATISSUE_CAPACITY (IDENTIFIER)
alter table CATISSUE_STOR_TYPE_HOLDS_TYPE add index (STORAGE_TYPE_ID), add constraint FK185C50B59A3CE5C foreign key (STORAGE_TYPE_ID) references CATISSUE_STORAGE_TYPE (IDENTIFIER)
alter table CATISSUE_STOR_TYPE_HOLDS_TYPE add index (HOLDS_STORAGE_TYPE_ID), add constraint FK185C50B81236791 foreign key (HOLDS_STORAGE_TYPE_ID) references CATISSUE_STORAGE_TYPE (IDENTIFIER)
alter table CATISSUE_PART_MEDICAL_ID add index (SITE_ID), add constraint FK349E77F9A7F77D13 foreign key (SITE_ID) references CATISSUE_SITE (IDENTIFIER)
alter table CATISSUE_PART_MEDICAL_ID add index (PARTICIPANT_ID), add constraint FK349E77F987E5ADC7 foreign key (PARTICIPANT_ID) references CATISSUE_PARTICIPANT (IDENTIFIER)
alter table CATISSUE_CELL_SPE_REVIEW_PARAM add index (IDENTIFIER), add constraint FK52F40EDEBC7298A9 foreign key (IDENTIFIER) references CATISSUE_EVENT_PARAM (IDENTIFIER)
alter table CATISSUE_STORAGE_CONTAINER add index (IDENTIFIER), add constraint FK28429D01BC7298A9 foreign key (IDENTIFIER) references CATISSUE_CONTAINER (IDENTIFIER)
alter table CATISSUE_STORAGE_CONTAINER add index (SITE_ID), add constraint FK28429D01A7F77D13 foreign key (SITE_ID) references CATISSUE_SITE (IDENTIFIER)
alter table CATISSUE_STORAGE_CONTAINER add index (STORAGE_TYPE_ID), add constraint FK28429D0159A3CE5C foreign key (STORAGE_TYPE_ID) references CATISSUE_STORAGE_TYPE (IDENTIFIER)
alter table CATISSUE_DISTRIBUTION add index (USER_ID), add constraint FK542766802206F20F foreign key (USER_ID) references CATISSUE_USER (IDENTIFIER)
alter table CATISSUE_DISTRIBUTION add index (DISTRIBUTION_PROTOCOL_ID), add constraint FK542766806B1F36E7 foreign key (DISTRIBUTION_PROTOCOL_ID) references CATISSUE_DISTRIBUTION_PROTOCOL (IDENTIFIER)
alter table CATISSUE_DISTRIBUTION add index (TO_SITE_ID), add constraint FK542766801DBE834F foreign key (TO_SITE_ID) references CATISSUE_SITE (IDENTIFIER)
alter table CATISSUE_DISTRIBUTION add index (SPECIMEN_ID), add constraint FK5427668060773DB2 foreign key (SPECIMEN_ID) references CATISSUE_SPECIMEN (IDENTIFIER)
alter table CATISSUE_STORTY_HOLDS_SPARRTY add index (STORAGE_TYPE_ID), add constraint FK70F57E4459A3CE5C foreign key (STORAGE_TYPE_ID) references CATISSUE_STORAGE_TYPE (IDENTIFIER)
alter table CATISSUE_STORTY_HOLDS_SPARRTY add index (SPECIMEN_ARRAY_TYPE_ID), add constraint FK70F57E44ECE89343 foreign key (SPECIMEN_ARRAY_TYPE_ID) references CATISSUE_SPECIMEN_ARRAY_TYPE (IDENTIFIER)
alter table CATISSUE_PROCEDURE_EVENT_PARAM add index (IDENTIFIER), add constraint FKEC6B4260BC7298A9 foreign key (IDENTIFIER) references CATISSUE_SPECIMEN_EVENT_PARAM (IDENTIFIER)
alter table CATISSUE_DISTRIBUTION_PROTOCOL add index (IDENTIFIER), add constraint FKC8999977BC7298A9 foreign key (IDENTIFIER) references CATISSUE_SPECIMEN_PROTOCOL (IDENTIFIER)
alter table CATISSUE_EXTERNAL_IDENTIFIER add index (SPECIMEN_ID), add constraint FK5CF2FA2160773DB2 foreign key (SPECIMEN_ID) references CATISSUE_SPECIMEN (IDENTIFIER)
alter table CATISSUE_FIXED_EVENT_PARAM add index (IDENTIFIER), add constraint FKE0F1781BC7298A9 foreign key (IDENTIFIER) references CATISSUE_SPECIMEN_EVENT_PARAM (IDENTIFIER)
alter table CATISSUE_THAW_EVENT_PARAMETERS add index (IDENTIFIER), add constraint FKD8890A48BC7298A9 foreign key (IDENTIFIER) references CATISSUE_SPECIMEN_EVENT_PARAM (IDENTIFIER)
alter table CATISSUE_COLL_PROT_REG add index (PARTICIPANT_ID), add constraint FK5EB25F1387E5ADC7 foreign key (PARTICIPANT_ID) references CATISSUE_PARTICIPANT (IDENTIFIER)
alter table CATISSUE_COLL_PROT_REG add index (COLLECTION_PROTOCOL_ID), add constraint FK5EB25F1348304401 foreign key (COLLECTION_PROTOCOL_ID) references CATISSUE_COLLECTION_PROTOCOL (IDENTIFIER)
alter table CATISSUE_FROZEN_EVENT_PARAM add index (IDENTIFIER), add constraint FK52627245BC7298A9 foreign key (IDENTIFIER) references CATISSUE_SPECIMEN_EVENT_PARAM (IDENTIFIER)
alter table CATISSUE_SPECIMEN add index (SPECIMEN_CHARACTERISTICS_ID), add constraint FK1674810456906F39 foreign key (SPECIMEN_CHARACTERISTICS_ID) references CATISSUE_SPECIMEN_CHAR (IDENTIFIER)
alter table CATISSUE_SPECIMEN add index (SPECIMEN_COLLECTION_GROUP_ID), add constraint FK1674810433BF33C5 foreign key (SPECIMEN_COLLECTION_GROUP_ID) references CATISSUE_SPECIMEN_COLL_GROUP (IDENTIFIER)
alter table CATISSUE_SPECIMEN add index (PARENT_SPECIMEN_ID), add constraint FK16748104B189E99D foreign key (PARENT_SPECIMEN_ID) references CATISSUE_SPECIMEN (IDENTIFIER)
alter table CATISSUE_SPECIMEN add index (STORAGE_CONTAINER_IDENTIFIER), add constraint FK1674810432B31EAB foreign key (STORAGE_CONTAINER_IDENTIFIER) references CATISSUE_STORAGE_CONTAINER (IDENTIFIER)
alter table CATISSUE_USER add index (INSTITUTION_ID), add constraint FKB025CFC71792AD22 foreign key (INSTITUTION_ID) references CATISSUE_INSTITUTION (IDENTIFIER)
alter table CATISSUE_USER add index (CANCER_RESEARCH_GROUP_ID), add constraint FKB025CFC7FFA96920 foreign key (CANCER_RESEARCH_GROUP_ID) references CATISSUE_CANCER_RESEARCH_GROUP (IDENTIFIER)
alter table CATISSUE_USER add index (ADDRESS_ID), add constraint FKB025CFC76CD94566 foreign key (ADDRESS_ID) references CATISSUE_ADDRESS (IDENTIFIER)
alter table CATISSUE_USER add index (DEPARTMENT_ID), add constraint FKB025CFC7F30C2528 foreign key (DEPARTMENT_ID) references CATISSUE_DEPARTMENT (IDENTIFIER)
alter table CATISSUE_TIS_SPE_EVENT_PARAM add index (IDENTIFIER), add constraint FKBB9648F4BC7298A9 foreign key (IDENTIFIER) references CATISSUE_EVENT_PARAM (IDENTIFIER)
alter table CATISSUE_SPECIMEN_COLL_GROUP add index (SITE_ID), add constraint FKDEBAF167A7F77D13 foreign key (SITE_ID) references CATISSUE_SITE (IDENTIFIER)
alter table CATISSUE_SPECIMEN_COLL_GROUP add index (CLINICAL_REPORT_ID), add constraint FKDEBAF1674CE21DDA foreign key (CLINICAL_REPORT_ID) references CATISSUE_CLINICAL_REPORT (IDENTIFIER)
alter table CATISSUE_SPECIMEN_COLL_GROUP add index (COLLECTION_PROTOCOL_EVENT_ID), add constraint FKDEBAF16753B01F66 foreign key (COLLECTION_PROTOCOL_EVENT_ID) references CATISSUE_COLL_PROT_EVENT (IDENTIFIER)
alter table CATISSUE_SPECIMEN_COLL_GROUP add index (COLL_PROT_REG_ID), add constraint FKDEBAF1674AD77FCB foreign key (COLL_PROT_REG_ID) references CATISSUE_COLL_PROT_REG (IDENTIFIER)
alter table CATISSUE_SPECIMEN_COLL_GROUP add index (COLLECTION_PROTOCOL_REG_ID), add constraint FKDEBAF1677E07C4AC foreign key (COLLECTION_PROTOCOL_REG_ID) references CATISSUE_COLL_PROT_REG (IDENTIFIER)
alter table CATISSUE_SPECIMEN_TYPE add index (SPECIMEN_ARRAY_TYPE_ID), add constraint FKFF69C195ECE89343 foreign key (SPECIMEN_ARRAY_TYPE_ID) references CATISSUE_SPECIMEN_ARRAY_TYPE (IDENTIFIER)