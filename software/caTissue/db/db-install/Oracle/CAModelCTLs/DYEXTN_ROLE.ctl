LOAD DATA INFILE 'H://caTissue//work//workspace//catissuecoreNew/SQL/DBUpgrade/Common/CAModelCSVs/DYEXTN_ROLE.csv' 
APPEND 
INTO TABLE DYEXTN_ROLE 
FIELDS TERMINATED BY ',' OPTIONALLY ENCLOSED BY '"'
(IDENTIFIER NULLIF IDENTIFIER='\\N',ASSOCIATION_TYPE NULLIF ASSOCIATION_TYPE='\\N',MAX_CARDINALITY NULLIF MAX_CARDINALITY='\\N',MIN_CARDINALITY NULLIF MIN_CARDINALITY='\\N',NAME NULLIF NAME='\\N')