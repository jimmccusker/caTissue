LOAD DATA INFILE 'H://caTissue//work//workspace//catissuecoreNew/SQL/DBUpgrade/Common/CAModelCSVs/DYEXTN_FLOAT_CONCEPT_VALUE.csv' 
APPEND 
INTO TABLE DYEXTN_FLOAT_CONCEPT_VALUE 
FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"'
(IDENTIFIER NULLIF IDENTIFIER='\\N',VALUE NULLIF VALUE='\\N')