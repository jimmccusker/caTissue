LOAD DATA INFILE 'H://caTissue//work//workspace//catissuecoreNew/SQL/DBUpgrade/Common/CAModelCSVs/DYEXTN_TABLE_PROPERTIES.csv' 
APPEND 
INTO TABLE DYEXTN_TABLE_PROPERTIES 
FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"'
(IDENTIFIER NULLIF IDENTIFIER='\\N',ENTITY_ID NULLIF ENTITY_ID='\\N')