catissue_user;select FIRST_NAME,LAST_NAME,DEPARTMENT_ID,CANCER_RESEARCH_GROUP_ID,INSTITUTION_ID,EMAIL_ADDRESS from catissue_user where IDENTIFIER='543'
catissue_site_users;select SITE_ID from catissue_site_users where USER_ID='543'
catissue_address;select CITY,STATE,COUNTRY,ZIPCODE,PHONE_NUMBER from catissue_address where IDENTIFIER IN(select ADDRESS_ID from catissue_user where IDENTIFIER='543')
csm_user;select password from csm_user where user_id IN(select csm_user_id from catissue_user where identifier='543')
catissue_audit_event_details;select ELEMENT_NAME,CURRENT_VALUE from catissue_audit_event_details where ELEMENT_NAME='FIRST_NAME' 
catissue_audit_event;SELECT EVENT_TYPE FROM catissue_audit_event WHERE COMMENTS IS NULL OR COMMENTS NOT LIKE 'QueryLog' 
