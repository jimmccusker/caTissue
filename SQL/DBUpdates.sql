INSERT INTO `CSM_PRIVILEGE` (`PRIVILEGE_ID`,`PRIVILEGE_NAME`,`PRIVILEGE_DESCRIPTION`,`UPDATE_DATE`) VALUES (11,'IDENTIFIED_DATA_ACCESS','This privilege allows a user to view identified data of an object','2005-08-22');

INSERT INTO `CSM_ROLE_PRIVILEGE` (`ROLE_PRIVILEGE_ID`,`ROLE_ID`,`PRIVILEGE_ID`,`UPDATE_DATE`) VALUES (23,8,3,'0000-00-00');
INSERT INTO `CSM_ROLE_PRIVILEGE` (`ROLE_PRIVILEGE_ID`,`ROLE_ID`,`PRIVILEGE_ID`,`UPDATE_DATE`) VALUES (24,1,11,'0000-00-00');
INSERT INTO `CSM_ROLE_PRIVILEGE` (`ROLE_PRIVILEGE_ID`,`ROLE_ID`,`PRIVILEGE_ID`,`UPDATE_DATE`) VALUES (25,2,11,'0000-00-00');
INSERT INTO `CSM_ROLE_PRIVILEGE` (`ROLE_PRIVILEGE_ID`,`ROLE_ID`,`PRIVILEGE_ID`,`UPDATE_DATE`) VALUES (26,4,11,'0000-00-00');


# --- insert commands for MolecularSpecimenReviewParametersAction of CSM.
INSERT INTO `CSM_PROTECTION_ELEMENT` (`PROTECTION_ELEMENT_ID`,`PROTECTION_ELEMENT_NAME`,`PROTECTION_ELEMENT_DESCRIPTION`,`OBJECT_ID`,`ATTRIBUTE`,`PROTECTION_ELEMENT_TYPE_ID`,`APPLICATION_ID`,`UPDATE_DATE`) VALUES (273,'edu.wustl.catissuecore.action.MolecularSpecimenReviewParametersAction','edu.wustl.catissuecore.action.MolecularSpecimenReviewParametersAction','edu.wustl.catissuecore.action.MolecularSpecimenReviewParametersAction',NULL,NULL,1,'2005-08-31');
INSERT INTO `CSM_PG_PE` (`PG_PE_ID`,`PROTECTION_GROUP_ID`,`PROTECTION_ELEMENT_ID`,`UPDATE_DATE`) VALUES (303,19,273,'0000-00-00');

UPDATE `CSM_PROTECTION_GROUP` SET `PROTECTION_GROUP_NAME` = 'TECHNICIANS_DATA_GROUP' WHERE `PROTECTION_GROUP_ID` = 23;