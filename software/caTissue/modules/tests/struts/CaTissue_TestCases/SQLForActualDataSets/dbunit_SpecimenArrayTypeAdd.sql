catissue_container_type;select NAME from catissue_container_type where NAME='array_1'
catissue_specimen_array_type;select SPECIMEN_CLASS from catissue_specimen_array_type,catissue_container_type where catissue_container_type.identifier=catissue_specimen_array_type.identifier and NAME='array_1'