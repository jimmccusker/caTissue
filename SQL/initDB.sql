drop TABLE CATISSUE_TABLE_RELATION;
CREATE TABLE CATISSUE_TABLE_RELATION
(
      PARENT_TABLE_ID bigint,      
      
      CHILD_TABLE_ID bigint
);

drop table CATISSUE_QUERY_INTERFACE_TABLE_DATA;
CREATE TABLE CATISSUE_QUERY_INTERFACE_TABLE_DATA
(
      TABLE_ID bigint not null auto_increment, 
	  
      TABLE_NAME varchar(50),

      DISPLAY_NAME varchar(50),
	
      ALIAS_NAME varchar(50),
      
      primary key (TABLE_ID)
);


drop table CATISSUE_QUERY_INTERFACE_COLUMN_DATA;
CREATE TABLE CATISSUE_QUERY_INTERFACE_COLUMN_DATA
(
	  IDENTIFIER bigint not null auto_increment,

      TABLE_ID bigint not null,

      COLUMN_NAME varchar(50),

      DISPLAY_NAME varchar(50),
      
      ATTRIBUTE_TYPE varchar(30),
      
	  primary key (IDENTIFIER)
);

insert into CATISSUE_QUERY_INTERFACE_TABLE_DATA  ( TABLE_ID, TABLE_NAME,DISPLAY_NAME,ALIAS_NAME) values (1,'CATISSUE_STORAGE_TYPE','Storage Type','StorageType');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 1, 'IDENTIFIER' , 'Storage Type Identifier' , 'bigint' );
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 1, 'TYPE' , 'Storage Type' , 'varchar');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 1, 'DEFAULT_TEMPERATURE_IN_CENTIGRADE' , 'Default Temperature In Centigrade' , 'double');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 1, 'ONE_DIMENSION_LABEL' , 'One Dimension Label' , 'varchar');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 1, 'TWO_DIMENSION_LABEL' , 'Two Dimension Label' , 'varchar');

insert into CATISSUE_QUERY_INTERFACE_TABLE_DATA  ( TABLE_ID,TABLE_NAME,DISPLAY_NAME,ALIAS_NAME) values (2, 'CATISSUE_STORAGE_CONTAINER_CAPACITY','Storage Container Capacity','StorageContainerCapacity');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 2, 'ONE_DIMENSION_CAPACITY' , 'One Dimension Capacity' , 'integer');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 2, 'TWO_DIMENSION_CAPACITY' , 'Two Dimension Capacity' , 'integer');

insert into CATISSUE_QUERY_INTERFACE_TABLE_DATA  ( TABLE_ID,TABLE_NAME,DISPLAY_NAME,ALIAS_NAME) values (3,'CATISSUE_SITE','Site','Site');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 3, 'IDENTIFIER' , 'Site Identifier' , 'bigint');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 3, 'NAME' , 'Site Name' , 'varchar');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 3, 'TYPE' , 'Site Type' , 'varchar');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 3, 'EMAIL_ADDRESS' , 'Site Email Address' , 'varchar');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 3, 'USER_ID' , 'User Identifier' , 'bigint');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 3, 'ACTIVITY_STATUS' , 'Site Activity Status' , 'varchar');

insert into CATISSUE_QUERY_INTERFACE_TABLE_DATA  ( TABLE_ID,TABLE_NAME,DISPLAY_NAME,ALIAS_NAME) values (4,'CATISSUE_ADDRESS','Address','Address');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 4, 'STREET' , 'Street' , 'varchar' );
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 4, 'CITY' , 'City' , 'varchar' );
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 4, 'STATE' , 'State' , 'varchar' );
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 4, 'COUNTRY' , 'Country' , 'varchar' );
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 4, 'ZIPCODE' , 'Zipcode' , 'varchar' );
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 4, 'PHONE_NUMBER' , 'Phone Number' , 'varchar' );
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 4, 'FAX_NUMBER' , 'Fax Number' , 'varchar' );

insert into CATISSUE_QUERY_INTERFACE_TABLE_DATA  ( TABLE_ID,TABLE_NAME,DISPLAY_NAME,ALIAS_NAME) values (5,'CATISSUE_DEPARTMENT','Department','Department');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 5, 'IDENTIFIER' , 'Department Identifier' , 'bigint');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 5, 'NAME' , 'Department Name' , 'varchar');

insert into CATISSUE_QUERY_INTERFACE_TABLE_DATA  ( TABLE_ID,TABLE_NAME,DISPLAY_NAME,ALIAS_NAME) values (6, 'CATISSUE_INSTITUTION','Institution','Institution');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 6, 'IDENTIFIER' , 'Institution Identifier' , 'bigint');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 6, 'NAME' , 'Institution Name' , 'varchar');

insert into CATISSUE_QUERY_INTERFACE_TABLE_DATA  ( TABLE_ID, TABLE_NAME,DISPLAY_NAME,ALIAS_NAME) values (7, 'CATISSUE_CANCER_RESEARCH_GROUP','Cancer Research Group','CancerResearchGroup');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 7, 'IDENTIFIER' , 'Cancer Research Group Identifier' , 'bigint');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 7, 'NAME' , 'Cancer Research Group Name' , 'varchar');

insert into CATISSUE_QUERY_INTERFACE_TABLE_DATA  ( TABLE_ID, TABLE_NAME,DISPLAY_NAME,ALIAS_NAME) values (8,'CATISSUE_BIOHAZARD','Biohazard','BioHazard');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 8, 'IDENTIFIER' , 'Identifier' , 'bigint' );
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 8, 'NAME' , 'Name' , 'varchar' );
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 8, 'COMMENTS' , 'Comments' , 'varchar' );
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 8, 'TYPE' , 'Type' , 'varchar' );

insert into CATISSUE_QUERY_INTERFACE_TABLE_DATA  ( TABLE_ID, TABLE_NAME,DISPLAY_NAME,ALIAS_NAME) values (9,'CATISSUE_SPECIMEN_PROTOCOL','Specimen Protocol','SpecimenProtocol');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 9, 'PRINCIPAL_INVESTIGATOR_ID' , 'Principal Investigator Identifier' , 'bigint');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 9, 'TITLE' , 'Title' , 'varchar');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 9, 'SHORT_TITLE' , 'Short Title' , 'varchar');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 9, 'IRB_IDENTIFIER' , 'Irb Identifier' , 'varchar');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 9, 'START_DATE' , 'Start Date' , 'date');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 9, 'END_DATE' , 'End Date' , 'date');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 9, 'ENROLLMENT' , 'Enrollment' , 'integer');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 9, 'DESCRIPTION_URL' , 'Description Url' , 'varchar');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 9, 'ACTIVITY_STATUS' , 'Activity Status' , 'varchar');

insert into CATISSUE_QUERY_INTERFACE_TABLE_DATA  ( TABLE_ID, TABLE_NAME,DISPLAY_NAME,ALIAS_NAME) values (10,'CATISSUE_COLLECTION_PROTOCOL','Collection Protocol','CollectionProtocol');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 10, 'IDENTIFIER' , 'Identifier' , 'bigint');

insert into CATISSUE_QUERY_INTERFACE_TABLE_DATA  ( TABLE_ID, TABLE_NAME,DISPLAY_NAME,ALIAS_NAME) values (11,'CATISSUE_COLLECTION_PROTOCOL_EVENT','Collection Protocol Event','CollectionProtocolEvent');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 11, 'CLINICAL_STATUS' , 'Clinical Status' , 'varchar' );
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 11, 'STUDY_CALENDAR_EVENT_POINT' , 'Study Calendar Event Point' , 'double' );

insert into CATISSUE_QUERY_INTERFACE_TABLE_DATA  ( TABLE_ID, TABLE_NAME,DISPLAY_NAME,ALIAS_NAME) values (12,'CATISSUE_SPECIMEN_REQUIREMENT','Specimen Requirement','SpecimenRequirement');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 12, 'SPECIMEN_TYPE' , 'Specimen Type' , 'varchar');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 12, 'TISSUE_SITE' , 'Tissue Site' , 'varchar');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 12, 'PATHOLOGY_STATUS' , 'Pathology Status' , 'varchar');

insert into CATISSUE_QUERY_INTERFACE_TABLE_DATA  ( TABLE_ID, TABLE_NAME,DISPLAY_NAME,ALIAS_NAME) values (13,'CATISSUE_CELL_SPECIMEN_REQUIREMENT','Cell Specimen Requirement','CellSpecimenRequirement');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 13, 'QUANTITY_IN_CELL_COUNT' , 'Quantity In Cell Count' , 'integer');

insert into CATISSUE_QUERY_INTERFACE_TABLE_DATA  ( TABLE_ID, TABLE_NAME,DISPLAY_NAME,ALIAS_NAME) values (14,'CATISSUE_MOLECULAR_SPECIMEN_REQUIREMENT','Molecular Specimen Requirement','MolecularSpecimenRequirement');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 14, 'QUANTITY_IN_MICRO_GRAM' , 'Quantity In Micro Gram' , 'double');

insert into CATISSUE_QUERY_INTERFACE_TABLE_DATA  ( TABLE_ID, TABLE_NAME,DISPLAY_NAME,ALIAS_NAME) values (15,'CATISSUE_TISSUE_SPECIMEN_REQUIREMENT','Tissue Specimen Requirement','TissueSpecimenRequirement');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 15, 'QUANTITY_IN_GRAM' , 'Quantity In Gram' , 'double');

insert into CATISSUE_QUERY_INTERFACE_TABLE_DATA  ( TABLE_ID, TABLE_NAME,DISPLAY_NAME,ALIAS_NAME) values (16,'CATISSUE_FLUID_SPECIMEN_REQUIREMENT','Fluid Specimen Requirement','FluidSpecimenRequirement');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 16, 'QUANTITY_IN_MILILITER' , 'Quantity In Mililiter' , 'double');

insert into CATISSUE_QUERY_INTERFACE_TABLE_DATA  ( TABLE_ID, TABLE_NAME,DISPLAY_NAME,ALIAS_NAME) values (17,'CATISSUE_COLLECTION_COORDINATORS','Collection Coordinators','CollectionCoordinators');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 17, 'COLLECTION_PROTOCOL_ID' , 'Collection Protocol Identifier' , 'bigint');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 17, 'USER_ID' , 'User Identifier' , 'bigint');

insert into CATISSUE_QUERY_INTERFACE_TABLE_DATA  ( TABLE_ID, TABLE_NAME,DISPLAY_NAME,ALIAS_NAME) values (18,'CATISSUE_COLLECTION_SPECIMEN_REQUIREMENT','Collection Specimen Requirement','CollectionSpecimenRequirement');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 18, 'COLLECTION_PROTOCOL_EVENT_ID' , 'Collection Protocol Event Identifier' , 'bigint');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 18, 'SPECIMEN_REQUIREMENT_ID' , 'Specimen Requirement Identifier' , 'bigint');

insert into CATISSUE_QUERY_INTERFACE_TABLE_DATA  ( TABLE_ID, TABLE_NAME,DISPLAY_NAME,ALIAS_NAME) values (19,'CATISSUE_DISTRIBUTION_PROTOCOL','Distribution Protocol','DistributionProtocol');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 19, 'IDENTIFIER' , 'Identifier' , 'bigint');

insert into CATISSUE_QUERY_INTERFACE_TABLE_DATA  ( TABLE_ID, TABLE_NAME,DISPLAY_NAME,ALIAS_NAME) values (20,'CATISSUE_DISTRIBUTION_SPECIMEN_REQUIREMENT','Distribution Specimen Requirement','DistributionSpecimenRequirement');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 20, 'DISTRIBUTION_PROTOCOL_ID' , 'Distribution Protocol Identifier' , 'bigint');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 20, 'SPECIMEN_REQUIREMENT_ID' , 'Specimen Requirement Identifier' , 'bigint');

insert into CATISSUE_QUERY_INTERFACE_TABLE_DATA  ( TABLE_ID, TABLE_NAME,DISPLAY_NAME,ALIAS_NAME) values (21,'CATISSUE_STORAGE_CONTAINER','Storage Container','StorageContainer');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 21, 'IDENTIFIER' , 'Identifier' , 'bigint');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 21, 'CONTAINER_NUMBER' , 'Container Number' , 'varchar');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 21, 'TEMPERATURE' , 'Temperature' , 'double');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 21, 'IS_CONTAINER_FULL' , 'Is Container Full' , 'bit');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 21, 'BARCODE' , 'Barcode' , 'varchar');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 21, 'ACTIVITY_STATUS' , 'Activity Status' , 'varchar');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 21, 'PARENT_CONTAINER_ID' , 'Parent Container Identifier' , 'bigint');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 21, 'POSITION_DIMENSION_ONE' , 'Position Dimension One' , 'integer');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 21, 'POSITION_DIMENSION_TWO' , 'Position Dimension Two' , 'integer');

insert into CATISSUE_QUERY_INTERFACE_TABLE_DATA  ( TABLE_ID, TABLE_NAME,DISPLAY_NAME,ALIAS_NAME) values (22,'CATISSUE_STORAGE_CONTAINER_DETAILS','Storage Container Details','StorageContainerDetails');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 22, 'PARAMETER_NAME' , 'Parameter Name' , 'varchar');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 22, 'PARAMETER_VALUE' , 'Parameter Value' , 'varchar');


insert into CATISSUE_QUERY_INTERFACE_TABLE_DATA  ( TABLE_ID, TABLE_NAME,DISPLAY_NAME,ALIAS_NAME) values (23,'CATISSUE_USER','User','User');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 23, 'IDENTIFIER' , 'Identifier' , 'bigint');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 23, 'ACTIVITY_STATUS' , 'Activity Status' , 'varchar');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 23, 'STATUS_COMMENT' , 'Status Comment' , 'varchar');


insert into CATISSUE_QUERY_INTERFACE_TABLE_DATA  ( TABLE_ID, TABLE_NAME,DISPLAY_NAME,ALIAS_NAME) values (24,'CSM_USER','CSM User','CsmUser');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 24, 'LOGIN_NAME' , 'Login Name' , 'varchar');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 24, 'FIRST_NAME' , 'First Name' , 'varchar');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 24, 'LAST_NAME' , 'Last Name' , 'varchar');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 24, 'EMAIL_ID' , 'Email Id' , 'varchar');

insert into CATISSUE_QUERY_INTERFACE_TABLE_DATA  ( TABLE_ID, TABLE_NAME, DISPLAY_NAME, ALIAS_NAME) values ( 25, 'CATISSUE_CELL_SPECIMEN', 'Cell Specimen', 'CellSpecimen');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 25, 'IDENTIFIER', 'Identifier', 'bigint');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 25, 'QUANTITY_IN_CELL_COUNT', 'Quantity In Cell Count', 'integer');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 25, 'AVAILABLE_QUANTITY_IN_CELL_COUNT', 'Available Quantity In Cell Count', 'integer');
insert into CATISSUE_QUERY_INTERFACE_TABLE_DATA  ( TABLE_ID, TABLE_NAME, DISPLAY_NAME, ALIAS_NAME) values ( 26, 'CATISSUE_CLINICAL_REPORT', 'Clinical Report', 'ClinicalReport');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 26, 'IDENTIFIER', 'Identifier', 'bigint');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 26, 'SURGICAL_PATHOLOGICAL_NUMBER', 'Surgical Pathological Number', 'varchar');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 26, 'PARTICIPENT_MEDICAL_IDENTIFIER_ID', 'Participent Medical Identifier Id', 'bigint');
insert into CATISSUE_QUERY_INTERFACE_TABLE_DATA  ( TABLE_ID, TABLE_NAME, DISPLAY_NAME, ALIAS_NAME) values ( 27, 'CATISSUE_COLLECTION_PROTOCOL_REGISTRATION', 'Collection Protocol Registration', 'CollectionProtocolRegistration');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 27, 'IDENTIFIER', 'Collection Protocol Registration Identifier', 'bigint');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 27, 'PROTOCOL_PARTICIPANT_IDENTIFIER', 'Protocol Participant Identifier', 'varchar');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 27, 'REGISTRATION_DATE', 'Registration Date', 'date');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 27, 'PARTICIPANT_ID', 'Participant Identifier', 'bigint');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 27, 'COLLECTION_PROTOCOL_ID', 'Collection Protocol Identifier', 'bigint');
insert into CATISSUE_QUERY_INTERFACE_TABLE_DATA  ( TABLE_ID, TABLE_NAME, DISPLAY_NAME, ALIAS_NAME) values ( 28, 'CATISSUE_EXTERNAL_IDENTIFIER', 'External Identifier', 'ExternalIdentifier');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 28, 'IDENTIFIER', 'Identifier', 'bigint');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 28, 'NAME', 'Name', 'varchar');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 28, 'VALUE', 'Value', 'varchar');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 28, 'SPECIMEN_ID', 'Specimen Id', 'bigint');
insert into CATISSUE_QUERY_INTERFACE_TABLE_DATA  ( TABLE_ID, TABLE_NAME, DISPLAY_NAME, ALIAS_NAME) values ( 29, 'CATISSUE_FLUID_SPECIMEN', 'Fluid Specimen', 'FluidSpecimen');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 29, 'IDENTIFIER', 'Identifier', 'bigint');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 29, 'QUANTITY_IN_MILILITER', 'Quantity In Mililiter', 'double');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 29, 'AVAILABLE_QUANTITY_IN_MILILITER', 'Available Quantity In Mililiter', 'double');
insert into CATISSUE_QUERY_INTERFACE_TABLE_DATA  ( TABLE_ID, TABLE_NAME, DISPLAY_NAME, ALIAS_NAME) values ( 30, 'CATISSUE_MOLECULAR_SPECIMEN', 'Molecular Specimen', 'MolecularSpecimen');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 30, 'IDENTIFIER', 'Identifier', 'bigint');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 30, 'CONCENTRATION_IN_MICROGRAM_PER_MICROLITER', 'Concentration In Microgram Per Microliter', 'double');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 30, 'QUANTITY_IN_MICROGRAM', 'Quantity In Microgram', 'double');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 30, 'AVAILABLE_QUANTITY_IN_MICROGRAM', 'Available Quantity In Microgram', 'double');
insert into CATISSUE_QUERY_INTERFACE_TABLE_DATA  ( TABLE_ID, TABLE_NAME, DISPLAY_NAME, ALIAS_NAME) values ( 31, 'CATISSUE_PARTICIPANT', 'Participant', 'Participant');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 31, 'IDENTIFIER', 'Identifier', 'bigint');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 31, 'LAST_NAME', 'Last Name', 'varchar');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 31, 'FIRST_NAME', 'First Name', 'varchar');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 31, 'MIDDLE_NAME', 'Middle Name', 'varchar');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 31, 'BIRTH_DATE', 'Birth Date', 'date');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 31, 'GENDER', 'Gender', 'varchar');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 31, 'GENOTYPE', 'Genotype', 'varchar');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 31, 'RACE', 'Race', 'varchar');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 31, 'ETHNICITY', 'Ethnicity', 'varchar');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 31, 'SOCIAL_SECURITY_NUMBER', 'Social Security Number', 'varchar');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 31, 'ACTIVITY_STATUS', 'Activity Status', 'varchar');
insert into CATISSUE_QUERY_INTERFACE_TABLE_DATA  ( TABLE_ID, TABLE_NAME, DISPLAY_NAME, ALIAS_NAME) values ( 32, 'CATISSUE_PARTICIPANT_MEDICAL_IDENTIFIER', 'Participant Medical Identifier', 'ParticipantMedicalIdentifier');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 32, 'MEDICAL_RECORD_NUMBER', 'Medical Record Number', 'varchar');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 32, 'SITE_ID', 'Site Identifier', 'bigint');
insert into CATISSUE_QUERY_INTERFACE_TABLE_DATA  ( TABLE_ID, TABLE_NAME, DISPLAY_NAME, ALIAS_NAME) values ( 33, 'CATISSUE_SPECIMEN', 'Specimen', 'Specimen');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 33, 'IDENTIFIER', 'Identifier', 'bigint');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 33, 'TYPE', 'Type', 'varchar');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 33, 'AVAILABLE', 'Available', 'tinyint');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 33, 'POSITION_DIMENSION_ONE', 'Position Dimension One', 'integer');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 33, 'POSITION_DIMENSION_TWO', 'Position Dimension Two', 'integer');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 33, 'BARCODE', 'Barcode', 'varchar');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 33, 'COMMENTS', 'Comments', 'varchar');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 33, 'ACTIVITY_STATUS', 'Activity Status', 'varchar');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 33, 'PARENT_SPECIMEN_ID', 'Parent Specimen Identifier', 'bigint');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 33, 'STORAGE_CONTAINER_IDENTIFIER', 'Storage Container Identifier', 'bigint');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 33, 'SPECIMEN_COLLECTION_GROUP_ID', 'Specimen Collection Group Identifier', 'bigint');
insert into CATISSUE_QUERY_INTERFACE_TABLE_DATA  ( TABLE_ID, TABLE_NAME, DISPLAY_NAME, ALIAS_NAME) values ( 34, 'CATISSUE_SPECIMEN_CHARACTERISTICS', 'Specimen Characteristics', 'SpecimenCharacteristics');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 34, 'TISSUE_SITE', 'Tissue Site', 'varchar');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 34, 'TISSUE_SIDE', 'Tissue Side', 'varchar');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 34, 'PATHOLOGICAL_STATUS', 'Pathological Status', 'varchar');
insert into CATISSUE_QUERY_INTERFACE_TABLE_DATA  ( TABLE_ID, TABLE_NAME, DISPLAY_NAME, ALIAS_NAME) values ( 35, 'CATISSUE_SPECIMEN_COLLECTION_GROUP', 'Specimen Collection Group', 'SpecimenCollectionGroup');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 35, 'IDENTIFIER', 'Identifier', 'bigint');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 35, 'CLINICAL_DIAGNOSIS', 'Clinical Diagnosis', 'varchar');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 35, 'CLINICAL_STATUS', 'Clinical Status', 'varchar');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 35, 'ACTIVITY_STATUS', 'Activity Status', 'varchar');
insert into CATISSUE_QUERY_INTERFACE_TABLE_DATA  ( TABLE_ID, TABLE_NAME, DISPLAY_NAME, ALIAS_NAME) values ( 36, 'CATISSUE_TISSUE_SPECIMEN', 'Tissue Specimen', 'TissueSpecimen');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 36, 'IDENTIFIER', 'Identifier', 'bigint');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 36, 'QUANTITY_IN_GRAM', 'Quantity In Gram', 'double');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 36, 'AVAILABLE_QUANTITY_IN_GRAM', 'Available Quantity In Gram', 'double');
insert into CATISSUE_QUERY_INTERFACE_TABLE_DATA  ( TABLE_ID, TABLE_NAME, DISPLAY_NAME, ALIAS_NAME) values ( 37, 'CATISSUE_DISTRIBUTED_ITEM', 'Distributed Item', 'DistributedItem');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 37, 'IDENTIFIER', 'Identifier', 'bigint');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 37, 'QUANTITY', 'Quantity', 'double');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 37, 'SPECIMEN_ID', 'Specimen Id', 'bigint');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 37, 'DISTRIBUTION_ID', 'Distribution Id', 'bigint');
insert into CATISSUE_QUERY_INTERFACE_TABLE_DATA  ( TABLE_ID, TABLE_NAME, DISPLAY_NAME, ALIAS_NAME) values ( 38, 'CATISSUE_DISTRIBUTION', 'Distribution', 'Distribution');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 38, 'IDENTIFIER', 'Identifier', 'bigint');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 38, 'TO_SITE_ID', 'To Site Id', 'bigint');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 38, 'FROM_SITE_ID', 'From Site Id', 'bigint');
insert into CATISSUE_QUERY_INTERFACE_COLUMN_DATA ( TABLE_ID, COLUMN_NAME , DISPLAY_NAME , ATTRIBUTE_TYPE ) values ( 38, 'DISTRIBUTION_PROTOCOL_ID', 'Distribution Protocol Id', 'bigint');



insert into CATISSUE_TABLE_RELATION (PARENT_TABLE_ID,CHILD_TABLE_ID) values ( 1 , 1 );
insert into CATISSUE_TABLE_RELATION (PARENT_TABLE_ID,CHILD_TABLE_ID) values ( 1 , 2 );
insert into CATISSUE_TABLE_RELATION (PARENT_TABLE_ID,CHILD_TABLE_ID) values ( 6 , 6 );
insert into CATISSUE_TABLE_RELATION (PARENT_TABLE_ID,CHILD_TABLE_ID) values ( 5 , 5 );
insert into CATISSUE_TABLE_RELATION (PARENT_TABLE_ID,CHILD_TABLE_ID) values ( 7 , 7 );
insert into CATISSUE_TABLE_RELATION (PARENT_TABLE_ID,CHILD_TABLE_ID) values ( 8 , 8 );
insert into CATISSUE_TABLE_RELATION (PARENT_TABLE_ID,CHILD_TABLE_ID) values ( 3 , 3 );
insert into CATISSUE_TABLE_RELATION (PARENT_TABLE_ID,CHILD_TABLE_ID) values ( 3 , 4 );
insert into CATISSUE_TABLE_RELATION (PARENT_TABLE_ID,CHILD_TABLE_ID) values (21 , 21);
insert into CATISSUE_TABLE_RELATION (PARENT_TABLE_ID,CHILD_TABLE_ID) values (21 , 2 );
insert into CATISSUE_TABLE_RELATION (PARENT_TABLE_ID,CHILD_TABLE_ID) values (21 , 1 );
insert into CATISSUE_TABLE_RELATION (PARENT_TABLE_ID,CHILD_TABLE_ID) values (21 , 3 );
insert into CATISSUE_TABLE_RELATION (PARENT_TABLE_ID,CHILD_TABLE_ID) values (10 , 10);
insert into CATISSUE_TABLE_RELATION (PARENT_TABLE_ID,CHILD_TABLE_ID) values (10 , 9 );
insert into CATISSUE_TABLE_RELATION (PARENT_TABLE_ID,CHILD_TABLE_ID) values (10 , 11);
insert into CATISSUE_TABLE_RELATION (PARENT_TABLE_ID,CHILD_TABLE_ID) values (10 , 12);
insert into CATISSUE_TABLE_RELATION (PARENT_TABLE_ID,CHILD_TABLE_ID) values (10 , 18);
insert into CATISSUE_TABLE_RELATION (PARENT_TABLE_ID,CHILD_TABLE_ID) values (19 , 19);
insert into CATISSUE_TABLE_RELATION (PARENT_TABLE_ID,CHILD_TABLE_ID) values (19 , 9 );
insert into CATISSUE_TABLE_RELATION (PARENT_TABLE_ID,CHILD_TABLE_ID) values (19 , 20);
insert into CATISSUE_TABLE_RELATION (PARENT_TABLE_ID,CHILD_TABLE_ID) values (19 , 12);
insert into CATISSUE_TABLE_RELATION (PARENT_TABLE_ID,CHILD_TABLE_ID) values (23 , 23);
insert into CATISSUE_TABLE_RELATION (PARENT_TABLE_ID,CHILD_TABLE_ID) values (23 , 24);
insert into CATISSUE_TABLE_RELATION (PARENT_TABLE_ID,CHILD_TABLE_ID) values (23 , 4);
insert into CATISSUE_TABLE_RELATION (PARENT_TABLE_ID,CHILD_TABLE_ID) values (23 , 5);
insert into CATISSUE_TABLE_RELATION (PARENT_TABLE_ID,CHILD_TABLE_ID) values (23 , 6);
insert into CATISSUE_TABLE_RELATION (PARENT_TABLE_ID,CHILD_TABLE_ID) values (23 , 7);

insert into CATISSUE_TABLE_RELATION (PARENT_TABLE_ID,CHILD_TABLE_ID) values (31 , 31);
insert into CATISSUE_TABLE_RELATION (PARENT_TABLE_ID,CHILD_TABLE_ID) values (31 , 32);
insert into CATISSUE_TABLE_RELATION (PARENT_TABLE_ID,CHILD_TABLE_ID) values (27 , 27);
insert into CATISSUE_TABLE_RELATION (PARENT_TABLE_ID,CHILD_TABLE_ID) values (35 , 35);
insert into CATISSUE_TABLE_RELATION (PARENT_TABLE_ID,CHILD_TABLE_ID) values (35 , 11);
insert into CATISSUE_TABLE_RELATION (PARENT_TABLE_ID,CHILD_TABLE_ID) values (35 , 3);
insert into CATISSUE_TABLE_RELATION (PARENT_TABLE_ID,CHILD_TABLE_ID) values (35 , 27);
insert into CATISSUE_TABLE_RELATION (PARENT_TABLE_ID,CHILD_TABLE_ID) values (33 , 33);
insert into CATISSUE_TABLE_RELATION (PARENT_TABLE_ID,CHILD_TABLE_ID) values (33 , 34);



commit;

insert into CATISSUE_INSTITUTION VALUES (1,'Washington University');

insert into CATISSUE_DEPARTMENT VALUES (1,'Cardiology');
insert into CATISSUE_DEPARTMENT VALUES (2,'Pathology');

insert into CATISSUE_CANCER_RESEARCH_GROUP VALUES (1,'Siteman Cancer Center');
insert into CATISSUE_CANCER_RESEARCH_GROUP VALUES (2,'Washington University');

insert into CATISSUE_ADDRESS values (1,'abc','abc','asd','abc','abc','asdas','asdas');
insert into CATISSUE_SITE VALUES (1,'SITE1',"LAB","as@b.cn",47,'Active',1);
insert into CATISSUE_STORAGE_container_capacity VALUES (1,5,5,'abc','abc');
insert into CATISSUE_STORAGE_TYPE VALUES (1,'Box',50,1);
insert into CATISSUE_STORAGE_container VALUES (1,'name1',50,1,'abc','Active',1,1,null,1,0,0);
insert into CATISSUE_STORAGE_CONTAINER values(2,'name2',50,false,'acb','Active',1,null,1,1,0,1)

drop table CATISSUE_RELATED_TABLES_MAP;
CREATE TABLE CATISSUE_RELATED_TABLES_MAP
(
      FIRST_TABLE_ID bigint,      
      SECOND_TABLE_ID bigint,
      FIRST_TABLE_JOIN_COLUMN varchar(50),
      SECOND_TABLE_JOIN_COLUMN varchar(50)
);

insert into CATISSUE_RELATED_TABLES_MAP values ( 10 , 11 , 'IDENTIFIER','COLLECTION_PROTOCOL_ID');
insert into CATISSUE_RELATED_TABLES_MAP values ( 5 , 23 , 'IDENTIFIER','DEPARTMENT_ID');
insert into CATISSUE_RELATED_TABLES_MAP values ( 6 , 23 , 'IDENTIFIER','INSTITUTION_ID');
insert into CATISSUE_RELATED_TABLES_MAP values ( 7 , 23 , 'IDENTIFIER','CANCER_RESEARCH_GROUP_ID');
insert into CATISSUE_RELATED_TABLES_MAP values ( 4 , 23 , 'IDENTIFIER','ADDRESS_ID');
insert into CATISSUE_RELATED_TABLES_MAP values ( 23 , 3 , 'IDENTIFIER','USER_ID');
insert into CATISSUE_RELATED_TABLES_MAP values ( 4 , 3 , 'IDENTIFIER','ADDRESS_ID');
insert into CATISSUE_RELATED_TABLES_MAP values ( 2 , 21 , 'IDENTIFIER','STORAGE_CONTAINER_CAPACITY_ID');
insert into CATISSUE_RELATED_TABLES_MAP values ( 9 , 10 , 'IDENTIFIER','IDENTIFIER');
insert into CATISSUE_RELATED_TABLES_MAP values ( 23 , 9 , 'IDENTIFIER','PRINCIPAL_INVESTIGATOR_ID');
insert into CATISSUE_RELATED_TABLES_MAP values ( 23 , 17 , 'IDENTIFIER','USER_ID');
insert into CATISSUE_RELATED_TABLES_MAP values ( 10 , 17 , 'IDENTIFIER','COLLECTION_PROTOCOL_ID');
insert into CATISSUE_RELATED_TABLES_MAP values ( 12 , 18, 'IDENTIFIER','SPECIMEN_REQUIREMENT_ID');
insert into CATISSUE_RELATED_TABLES_MAP values ( 11 , 18 , 'IDENTIFIER','COLLECTION_PROTOCOL_EVENT_ID');
insert into CATISSUE_RELATED_TABLES_MAP values ( 9 , 19 , 'IDENTIFIER','IDENTIFIER');
insert into CATISSUE_RELATED_TABLES_MAP values ( 12 , 20 , 'IDENTIFIER','SPECIMEN_REQUIREMENT_ID');
insert into CATISSUE_RELATED_TABLES_MAP values ( 19 , 20 , 'IDENTIFIER','DISTRIBUTION_PROTOCOL_ID');
insert into CATISSUE_RELATED_TABLES_MAP values ( 13 , 12 , 'IDENTIFIER','IDENTIFIER');
insert into CATISSUE_RELATED_TABLES_MAP values ( 14 , 12 , 'IDENTIFIER','IDENTIFIER');
insert into CATISSUE_RELATED_TABLES_MAP values ( 15 , 12 , 'IDENTIFIER','IDENTIFIER');
insert into CATISSUE_RELATED_TABLES_MAP values ( 16 , 12 , 'IDENTIFIER','IDENTIFIER');
insert into CATISSUE_RELATED_TABLES_MAP values ( 22 , 21 , 'IDENTIFIER','STORAGE_TYPE_ID');
insert into CATISSUE_RELATED_TABLES_MAP values ( 3 , 21 , 'IDENTIFIER','SITE_ID');
insert into CATISSUE_RELATED_TABLES_MAP values ( 2 , 21 , 'IDENTIFIER','STORAGE_CONTAINER_CAPACITY_ID');
insert into CATISSUE_RELATED_TABLES_MAP values ( 23 , 24 , 'IDENTIFIER','USER_ID');

insert into CATISSUE_RELATED_TABLES_MAP values ( 31 , 32 , 'IDENTIFIER','PARTICIPANT_ID');
insert into CATISSUE_RELATED_TABLES_MAP values ( 10 , 27 , 'IDENTIFIER','COLLECTION_PROTOCOL_ID');
insert into CATISSUE_RELATED_TABLES_MAP values ( 31 , 27 , 'IDENTIFIER','PARTICIPANT_ID');
insert into CATISSUE_RELATED_TABLES_MAP values ( 3 , 35 , 'IDENTIFIER','SITE_ID');
insert into CATISSUE_RELATED_TABLES_MAP values ( 11 , 35 , 'IDENTIFIER','COLLECTION_PROTOCOL_EVENT_ID');
insert into CATISSUE_RELATED_TABLES_MAP values ( 26 , 35 , 'IDENTIFIER','CLINICAL_REPORT_ID');
insert into CATISSUE_RELATED_TABLES_MAP values ( 27 , 35 , 'IDENTIFIER','COLLECTION_PROTOCOL_REGISTRATION_ID');
insert into CATISSUE_RELATED_TABLES_MAP values ( 21 , 33 , 'IDENTIFIER','STORAGE_CONTAINER_IDENTIFIER');
insert into CATISSUE_RELATED_TABLES_MAP values ( 35 , 33 , 'IDENTIFIER','SPECIMEN_COLLECTION_GROUP_ID');
insert into CATISSUE_RELATED_TABLES_MAP values ( 34 , 33 , 'IDENTIFIER','SPECIMEN_CHARACTERISTICS_ID');
insert into CATISSUE_RELATED_TABLES_MAP values ( 33 , 33 , 'IDENTIFIER','PARENT_SPECIMEN_ID');

