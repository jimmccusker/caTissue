--updated the isimplicit field set to 1 for all existing rules for camodels
UPDATE  dyextn_rule SET IS_IMPLICIT = 1 WHERE NAME IN ('date','number','textLength');

update dyextn_abstract_metadata set NAME = 'TreatmentRegimen' where IDENTIFIER = 1227;
update dyextn_abstract_metadata set NAME = 'treatmentRegimen' where IDENTIFIER = 1229;
update dyextn_abstract_metadata set NAME = 'yearsAgentFree' where IDENTIFIER = 1233;
update dyextn_abstract_metadata set NAME = 'otherAgent' where IDENTIFIER = 1234;
update dyextn_abstract_metadata set NAME = 'agent' where IDENTIFIER = 1235;
update dyextn_abstract_metadata set NAME = 'durationInDays' where IDENTIFIER = 1238;
update dyextn_abstract_metadata set NAME = 'endDate' where IDENTIFIER = 1239;
update dyextn_abstract_metadata set NAME = 'startDate' where IDENTIFIER = 1240;
update dyextn_abstract_metadata set NAME = 'drinksPerWeek' where IDENTIFIER = 1243;
update dyextn_abstract_metadata set NAME = 'dateOfExamination' where IDENTIFIER = 1251;
update dyextn_abstract_metadata set NAME = 'otherProcedure' where IDENTIFIER = 1252;
update dyextn_abstract_metadata set NAME = 'nameOfProcedure' where IDENTIFIER = 1253;
update dyextn_abstract_metadata set NAME = 'otherClinicalDiagnosis' where IDENTIFIER = 1256;
update dyextn_abstract_metadata set NAME = 'clinicalDiagnosis' where IDENTIFIER = 1257;
update dyextn_abstract_metadata set NAME = 'ChemoRXAnnotation' where IDENTIFIER = 1261;
update dyextn_abstract_metadata set NAME = 'RadRXAnnotation' where IDENTIFIER = 1262;
update dyextn_abstract_metadata set NAME = 'TreatmentAnnotation' where IDENTIFIER = 1264;
update dyextn_abstract_metadata set NAME = 'doseUnits' where IDENTIFIER = 1267;
update dyextn_abstract_metadata set NAME = 'dose' where IDENTIFIER = 1268;
update dyextn_abstract_metadata set NAME = 'otherAgent' where IDENTIFIER = 1269;
update dyextn_abstract_metadata set NAME = 'agent' where IDENTIFIER = 1270;
update dyextn_abstract_metadata set NAME = 'otherTissueSite' where IDENTIFIER = 1281;
update dyextn_abstract_metadata set NAME = 'tissueSite' where IDENTIFIER = 1282;
update dyextn_abstract_metadata set NAME = 'LabAnnotation' where IDENTIFIER = 1283;
update dyextn_abstract_metadata set NAME = 'testDate' where IDENTIFIER = 1285;
update dyextn_abstract_metadata set NAME = 'resultUnits' where IDENTIFIER = 1286;
update dyextn_abstract_metadata set NAME = 'result' where IDENTIFIER = 1287;
update dyextn_abstract_metadata set NAME = 'otherLabTestName' where IDENTIFIER = 1288;
update dyextn_abstract_metadata set NAME = 'labTestName' where IDENTIFIER = 1289;
update dyextn_abstract_metadata set NAME = 'FamilyHistoryAnnotation' where IDENTIFIER = 1290;
update dyextn_abstract_metadata set NAME = 'otherRelation' where IDENTIFIER = 1293;
update dyextn_abstract_metadata set NAME = 'relation' where IDENTIFIER = 1294;
update dyextn_abstract_metadata set NAME = 'packsPerDay' where IDENTIFIER = 1299;
update dyextn_abstract_metadata set NAME = 'SpecimenDetails' where IDENTIFIER = 1320;
update dyextn_abstract_metadata set NAME = 'SpecimenDetails' where IDENTIFIER = 1322;
update dyextn_abstract_metadata set NAME = 'SpecimenHistologicGrade' where IDENTIFIER = 1328;
update dyextn_abstract_metadata set NAME = 'SpecimenInvasion' where IDENTIFIER = 1329;
update dyextn_abstract_metadata set NAME = 'SpecimenHistologicType' where IDENTIFIER = 1330;
update dyextn_abstract_metadata set NAME = 'comments' where IDENTIFIER = 1321;
update dyextn_abstract_metadata set NAME = 'SpecimenHistologicType' where IDENTIFIER = 1332;
update dyextn_abstract_metadata set NAME = 'SpecimenHistologicVariantType' where IDENTIFIER = 1334;
update dyextn_abstract_metadata set NAME = 'type' where IDENTIFIER = 1335;
update dyextn_abstract_metadata set NAME = 'SpecimenHistologicVariantType' where IDENTIFIER = 1336;
update dyextn_abstract_metadata set NAME = 'otherHistologicType' where IDENTIFIER = 1338;
update dyextn_abstract_metadata set NAME = 'SpecimenInvasion' where IDENTIFIER = 1339;
update dyextn_abstract_metadata set NAME = 'perineuralInvasion' where IDENTIFIER = 1341;
update dyextn_abstract_metadata set NAME = 'venousInvasion' where IDENTIFIER = 1342;
update dyextn_abstract_metadata set NAME = 'lymphaticInvasion' where IDENTIFIER = 1343;
update dyextn_abstract_metadata set NAME = 'SpecimenHistologicGrade' where IDENTIFIER = 1344;
update dyextn_abstract_metadata set NAME = 'grade' where IDENTIFIER = 1346;
update dyextn_abstract_metadata set NAME = 'otherGradingSystemName' where IDENTIFIER = 1347;
update dyextn_abstract_metadata set NAME = 'gradingSystemName' where IDENTIFIER = 1348;
update dyextn_abstract_metadata set NAME = 'mitoticIndex' where IDENTIFIER = 1351;
update dyextn_abstract_metadata set NAME = 'tumorRegression' where IDENTIFIER = 1352;
update dyextn_abstract_metadata set NAME = 'tumorInfiltratingLymphocytes' where IDENTIFIER = 1353;
update dyextn_abstract_metadata set NAME = 'depthOfInvasionCannotBeDetermined' where IDENTIFIER = 1354;
update dyextn_abstract_metadata set NAME = 'depthOfInvasion' where IDENTIFIER = 1355;
update dyextn_abstract_metadata set NAME = 'ulceration' where IDENTIFIER = 1356;
update dyextn_abstract_metadata set NAME = 'intratumoralPeritumoralLymphocyticResponse' where IDENTIFIER = 1361;
update dyextn_abstract_metadata set NAME = 'tumorBorderConfiguration' where IDENTIFIER = 1362;
update dyextn_abstract_metadata set NAME = 'mitoticCountIfOtherGradingSystemUsed' where IDENTIFIER = 1368;
update dyextn_abstract_metadata set NAME = 'SpecimenNottinghamHistologicScore' where IDENTIFIER = 1369;
update dyextn_abstract_metadata set NAME = 'proportionOrPercentOfProstaticTissueInvolvedByTumor' where IDENTIFIER = 1380;
update dyextn_abstract_metadata set NAME = 'SpecimenGleasonScore' where IDENTIFIER = 1381;
update dyextn_abstract_metadata set NAME = 'pathologicFinding' where IDENTIFIER = 1403;
update dyextn_abstract_metadata set NAME = 'details' where IDENTIFIER = 1406;
update dyextn_abstract_metadata set NAME = 'tertiaryPatternScore' where IDENTIFIER = 1410;
update dyextn_abstract_metadata set NAME = 'secondaryPatternScore' where IDENTIFIER = 1411;
update dyextn_abstract_metadata set NAME = 'primaryPatternScore' where IDENTIFIER = 1412;
update dyextn_abstract_metadata set NAME = 'totalNottinghamScore' where IDENTIFIER = 1416;
update dyextn_abstract_metadata set NAME = 'mitoticCountScore' where IDENTIFIER = 1417;
update dyextn_abstract_metadata set NAME = 'nuclearPleomorphismScore' where IDENTIFIER = 1418;
update dyextn_abstract_metadata set NAME = 'tubuleFormationScore' where IDENTIFIER = 1419;
update dyextn_abstract_metadata set NAME = 'ageAtDiagnosis' where IDENTIFIER = 1423;
update dyextn_abstract_metadata set NAME = 'otherClinicalDiagnosis' where IDENTIFIER = 1424;
update dyextn_abstract_metadata set NAME = 'clinicalDiagnosis' where IDENTIFIER = 1425;
update dyextn_abstract_metadata set NAME = 'RadRXAnnotationSet' where IDENTIFIER = 1429;
update dyextn_abstract_metadata set NAME = 'ChemoRXAnnotation' where IDENTIFIER = 1430;
update dyextn_abstract_metadata set NAME = 'cycle' where IDENTIFIER = 1431;
update dyextn_abstract_metadata set NAME = 'ChemoRXAnnotation' where IDENTIFIER = 1432;
update dyextn_abstract_metadata set NAME = 'RadRXAnnotationSet' where IDENTIFIER = 1434;
update dyextn_abstract_metadata set NAME = 'status' where IDENTIFIER = 1439;
update dyextn_abstract_metadata set NAME = 'comment' where IDENTIFIER = 1444;
update dyextn_abstract_metadata set NAME = 'otherSpecimenProcedure' where IDENTIFIER = 1445;
update dyextn_abstract_metadata set NAME = 'specimenProcedure' where IDENTIFIER = 1446;
update dyextn_abstract_metadata set NAME = 'type' where IDENTIFIER = 1450;
update dyextn_abstract_metadata set NAME = 'otherHistologicType' where IDENTIFIER = 1453;
update dyextn_abstract_metadata set NAME = 'pathologicFinding' where IDENTIFIER = 1457;
update dyextn_abstract_metadata set NAME = 'detail' where IDENTIFIER = 1460;
update dyextn_abstract_metadata set NAME = 'Size' where IDENTIFIER = 1466;
update dyextn_abstract_metadata set NAME = 'perineuralInvasion' where IDENTIFIER = 1470;
update dyextn_abstract_metadata set NAME = 'venousInvasion' where IDENTIFIER = 1471;
update dyextn_abstract_metadata set NAME = 'lymphaticInvasion' where IDENTIFIER = 1472;
update dyextn_abstract_metadata set NAME = 'Size' where IDENTIFIER = 1473;
update dyextn_abstract_metadata set NAME = 'greatestDimension' where IDENTIFIER = 1475;
update dyextn_abstract_metadata set NAME = 'additionalDimensionTwo' where IDENTIFIER = 1476;
update dyextn_abstract_metadata set NAME = 'additionalDimensionOne' where IDENTIFIER = 1477;
update dyextn_abstract_metadata set NAME = 'cannotBeDetermined' where IDENTIFIER = 1478;
update dyextn_abstract_metadata set NAME = 'grade' where IDENTIFIER = 1481;
update dyextn_abstract_metadata set NAME = 'otherGradingSystemName' where IDENTIFIER = 1320;
update dyextn_abstract_metadata set NAME = 'gradingSystemName' where IDENTIFIER = 1483;
update dyextn_abstract_metadata set NAME = 'mattedNodes' where IDENTIFIER = 1491;
update dyextn_abstract_metadata set NAME = 'numberPositiveMicroscopically' where IDENTIFIER = 1492;
update dyextn_abstract_metadata set NAME = 'numberPositiveMacroscopically' where IDENTIFIER = 1493;
update dyextn_abstract_metadata set NAME = 'numberInvolved' where IDENTIFIER = 1494;
update dyextn_abstract_metadata set NAME = 'numberExamined' where IDENTIFIER = 1495;
update dyextn_abstract_metadata set NAME = 'lymphNodeStage' where IDENTIFIER = 1496;
update dyextn_abstract_metadata set NAME = 'metastasisStage' where IDENTIFIER = 1500;
update dyextn_abstract_metadata set NAME = 'otherTissueSite' where IDENTIFIER = 1503;
update dyextn_abstract_metadata set NAME = 'tissueSite' where IDENTIFIER = 1504;
update dyextn_abstract_metadata set NAME = 'primaryTumorStage' where IDENTIFIER = 1507;
update dyextn_abstract_metadata set NAME = 'otherSite' where IDENTIFIER = 1511;
update dyextn_abstract_metadata set NAME = 'site' where IDENTIFIER = 1512;
update dyextn_abstract_metadata set NAME = 'side' where IDENTIFIER = 1515;
update dyextn_abstract_metadata set NAME = 'arterialInvasion' where IDENTIFIER = 1522;
update dyextn_abstract_metadata set NAME = 'otherTumorExtensions' where IDENTIFIER = 1523;
update dyextn_abstract_metadata set NAME = 'extensionOfTumor' where IDENTIFIER = 1526;
update dyextn_abstract_metadata set NAME = 'otherLocation' where IDENTIFIER = 1530;
update dyextn_abstract_metadata set NAME = 'marginLocation' where IDENTIFIER = 1531;
update dyextn_abstract_metadata set NAME = 'marginStatus' where IDENTIFIER = 1532;
update dyextn_abstract_metadata set NAME = 'closestDistanceToTumor' where IDENTIFIER = 1535;
update dyextn_abstract_metadata set NAME = 'percentOfSarcomatoidElement' where IDENTIFIER = 1538;
update dyextn_abstract_metadata set NAME = 'numberOfPieces' where IDENTIFIER = 1543;
update dyextn_abstract_metadata set NAME = 'type' where IDENTIFIER = 1544;
update dyextn_abstract_metadata set NAME = 'periprostaticFatInvasion' where IDENTIFIER = 1548;
update dyextn_abstract_metadata set NAME = 'seminalVesicleInvasion' where IDENTIFIER = 1549;
update dyextn_abstract_metadata set NAME = 'proportionOrPercentOfProstaticTissueInvolvedByTumor' where IDENTIFIER = 1550;
update dyextn_abstract_metadata set NAME = 'tertiaryPatternScore' where IDENTIFIER = 1553;
update dyextn_abstract_metadata set NAME = 'secondaryPatternScore' where IDENTIFIER = 1554;
update dyextn_abstract_metadata set NAME = 'primaryPatternScore' where IDENTIFIER = 1555;
update dyextn_abstract_metadata set NAME = 'visceralPleuraInvasion' where IDENTIFIER = 1561;
update dyextn_abstract_metadata set NAME = 'otherMarginLocation' where IDENTIFIER = 1564;
update dyextn_abstract_metadata set NAME = 'marginLocation' where IDENTIFIER = 1565;
update dyextn_abstract_metadata set NAME = 'marginStatus' where IDENTIFIER = 1569;
update dyextn_abstract_metadata set NAME = 'otherLocation' where IDENTIFIER = 1572;
update dyextn_abstract_metadata set NAME = 'location' where IDENTIFIER = 1573;
update dyextn_abstract_metadata set NAME = 'extentOfTumor' where IDENTIFIER = 1576;
update dyextn_abstract_metadata set NAME = 'ImmunoPhenotyping' where IDENTIFIER = 1580;
update dyextn_abstract_metadata set NAME = 'adequacyOfSpecimen' where IDENTIFIER = 1581;
update dyextn_abstract_metadata set NAME = 'otherBiopsyOrAspirateSite' where IDENTIFIER = 1582;
update dyextn_abstract_metadata set NAME = 'biopsyOrAspirateSite' where IDENTIFIER = 1583;
update dyextn_abstract_metadata set NAME = 'status' where IDENTIFIER = 1586;
update dyextn_abstract_metadata set NAME = 'result' where IDENTIFIER = 1587;
update dyextn_abstract_metadata set NAME = 'methodUsed' where IDENTIFIER = 1588;
update dyextn_abstract_metadata set NAME = 'result' where IDENTIFIER = 1591;
update dyextn_abstract_metadata set NAME = 'status' where IDENTIFIER = 1592;
update dyextn_abstract_metadata set NAME = 'intratumoralPeritumoralLymphocyticResponse' where IDENTIFIER = 1596;
update dyextn_abstract_metadata set NAME = 'tumorBorderConfiguration' where IDENTIFIER = 1597;
update dyextn_abstract_metadata set NAME = 'otherTumorConfiguration' where IDENTIFIER = 1598;
update dyextn_abstract_metadata set NAME = 'tumorConfiguration' where IDENTIFIER = 1599;
update dyextn_abstract_metadata set NAME = 'marginLocation' where IDENTIFIER = 1603;
update dyextn_abstract_metadata set NAME = 'marginStatus' where IDENTIFIER = 1604;
update dyextn_abstract_metadata set NAME = 'site' where IDENTIFIER = 1607;
update dyextn_abstract_metadata set NAME = 'marginStatus' where IDENTIFIER = 1611;
update dyextn_abstract_metadata set NAME = 'marginStatus' where IDENTIFIER = 1619;
update dyextn_abstract_metadata set NAME = 'marginLocation' where IDENTIFIER = 1623;
update dyextn_abstract_metadata set NAME = 'closestDistanceToTumor' where IDENTIFIER = 1624;
update dyextn_abstract_metadata set NAME = 'totalNumberOfChips' where IDENTIFIER = 1627;
update dyextn_abstract_metadata set NAME = 'numberOfPositiveChips' where IDENTIFIER = 1628;
update dyextn_abstract_metadata set NAME = 'isTumorIncidentalHistologicFindingAbove5Percent' where IDENTIFIER = 1629;
update dyextn_abstract_metadata set NAME = 'specimenWeight' where IDENTIFIER = 1630;
update dyextn_abstract_metadata set NAME = 'ExcisionalBiopsyColorectalDeepMargin' where IDENTIFIER = 1633;
update dyextn_abstract_metadata set NAME = 'ExcisionalBiopsyColorectalLateralOrMucosalMargin' where IDENTIFIER = 1634;
update dyextn_abstract_metadata set NAME = 'typeOfPolypInWhichInvasiveCarcinomaArose' where IDENTIFIER = 1638;
update dyextn_abstract_metadata set NAME = 'extentOfInvasion' where IDENTIFIER = 1639;
update dyextn_abstract_metadata set NAME = 'lengthInCentimeters' where IDENTIFIER = 1642;
update dyextn_abstract_metadata set NAME = 'distanceKnown' where IDENTIFIER = 1643;
update dyextn_abstract_metadata set NAME = 'stalkLength' where IDENTIFIER = 1646;
update dyextn_abstract_metadata set NAME = 'configuration' where IDENTIFIER = 1647;
update dyextn_abstract_metadata set NAME = 'marginStatus' where IDENTIFIER = 1651;
update dyextn_abstract_metadata set NAME = 'totalNumberOfCores' where IDENTIFIER = 1654;
update dyextn_abstract_metadata set NAME = 'numberOfPositiveCores' where IDENTIFIER = 1655;
update dyextn_abstract_metadata set NAME = 'otherQuantitiation' where IDENTIFIER = 1656;
update dyextn_abstract_metadata set NAME = 'coreLengthInMillimeter' where IDENTIFIER = 1657;
update dyextn_abstract_metadata set NAME = 'totalLinearCarcinomaInMillimeter' where IDENTIFIER = 1658;
update dyextn_abstract_metadata set NAME = 'otherExtentOfInvolvement' where IDENTIFIER = 1661;
update dyextn_abstract_metadata set NAME = 'extentOfInvolvement' where IDENTIFIER = 1662;
update dyextn_abstract_metadata set NAME = 'involvedMarginLocation' where IDENTIFIER = 1665;
update dyextn_abstract_metadata set NAME = 'otherLocation' where IDENTIFIER = 1666;
update dyextn_abstract_metadata set NAME = 'BreastMarginInvolved' where IDENTIFIER = 1670;
update dyextn_abstract_metadata set NAME = 'marginLocation' where IDENTIFIER = 1671;
update dyextn_abstract_metadata set NAME = 'marginStatus' where IDENTIFIER = 1672;
update dyextn_abstract_metadata set NAME = 'focalityOfInvolvedMargin' where IDENTIFIER = 1683;
update dyextn_abstract_metadata set NAME = 'marginStatus' where IDENTIFIER = 1684;
update dyextn_abstract_metadata set NAME = 'isFocal' where IDENTIFIER = 1688;
update dyextn_abstract_metadata set NAME = 'status' where IDENTIFIER = 1689;
update dyextn_abstract_metadata set NAME = 'tissueSite' where IDENTIFIER = 1692;
update dyextn_abstract_metadata set NAME = 'ResectionColorectalMesentricMargin' where IDENTIFIER = 1693;
update dyextn_abstract_metadata set NAME = 'ColorectalResectedMarginUninvolved' where IDENTIFIER = 1695;
update dyextn_abstract_metadata set NAME = 'marginStatus' where IDENTIFIER = 1696;
update dyextn_abstract_metadata set NAME = 'otherOtherOrgansResected' where IDENTIFIER = 1703;
update dyextn_abstract_metadata set NAME = 'otherOrgansResected' where IDENTIFIER = 1704;
update dyextn_abstract_metadata set NAME = 'totalNottinghamScore' where IDENTIFIER = 1711;
update dyextn_abstract_metadata set NAME = 'mitoticCountScore' where IDENTIFIER = 1712;
update dyextn_abstract_metadata set NAME = 'nuclearPleomorphismScore' where IDENTIFIER = 1713;
update dyextn_abstract_metadata set NAME = 'tubuleFormationScore' where IDENTIFIER = 1714;
update dyextn_abstract_metadata set NAME = 'ResectionColorectalDistalMargin' where IDENTIFIER = 1719;
update dyextn_abstract_metadata set NAME = 'ResectionColorectalProximalMargin' where IDENTIFIER = 1720;
update dyextn_abstract_metadata set NAME = 'intactnessOfMesorectum' where IDENTIFIER = 1721;
update dyextn_abstract_metadata set NAME = 'specimenLength' where IDENTIFIER = 1722;
update dyextn_abstract_metadata set NAME = 'ResectionColorectalProximalMargin' where IDENTIFIER = 1723;
update dyextn_abstract_metadata set NAME = 'ColorectalResectedMarginUninvolved' where IDENTIFIER = 1725;
update dyextn_abstract_metadata set NAME = 'marginStatus' where IDENTIFIER = 1726;
update dyextn_abstract_metadata set NAME = 'ResectionColorectalDistalMargin' where IDENTIFIER = 1727;
update dyextn_abstract_metadata set NAME = 'ColorectalResectedMarginUninvolved' where IDENTIFIER = 1729;
update dyextn_abstract_metadata set NAME = 'marginStatus' where IDENTIFIER = 1730;
update dyextn_abstract_metadata set NAME = 'ResectionColorectalRadialMargin' where IDENTIFIER = 1731;
update dyextn_abstract_metadata set NAME = 'ColorectalResectedMarginUninvolved' where IDENTIFIER = 1733;
update dyextn_abstract_metadata set NAME = 'marginStatus' where IDENTIFIER = 1734;
update dyextn_abstract_metadata set NAME = 'macroscopicTumor' where IDENTIFIER = 1736;
update dyextn_abstract_metadata set NAME = 'pigmentation' where IDENTIFIER = 1737;
update dyextn_abstract_metadata set NAME = 'ulceration' where IDENTIFIER = 1738;
update dyextn_abstract_metadata set NAME = 'depthOfInvasion' where IDENTIFIER = 1739;
update dyextn_abstract_metadata set NAME = 'depthOfInvasionCannotBeDetermined' where IDENTIFIER = 1740;
update dyextn_abstract_metadata set NAME = 'tumorInfiltratingLymphocytes' where IDENTIFIER = 1741;
update dyextn_abstract_metadata set NAME = 'tumorRegression' where IDENTIFIER = 1742;
update dyextn_abstract_metadata set NAME = 'mitoticIndex' where IDENTIFIER = 1743;
update dyextn_abstract_metadata set NAME = 'satelliteNoduleStatus' where IDENTIFIER = 1744;
update dyextn_abstract_metadata set NAME = 'LateralMelanomaMargin' where IDENTIFIER = 1745;
update dyextn_abstract_metadata set NAME = 'satelliteNodule' where IDENTIFIER = 1746;
update dyextn_abstract_metadata set NAME = 'DeepMelanomaMargin' where IDENTIFIER = 1747;
update dyextn_abstract_metadata set NAME = 'marginLocation' where IDENTIFIER = 1752;
update dyextn_abstract_metadata set NAME = 'marginStatus' where IDENTIFIER = 1753;
update dyextn_abstract_metadata set NAME = 'lymphNodeSampling' where IDENTIFIER = 1761;
update dyextn_abstract_metadata set NAME = 'value' where IDENTIFIER = 1764;
update dyextn_abstract_metadata set NAME = 'marginLocation' where IDENTIFIER = 1768;
update dyextn_abstract_metadata set NAME = 'marginStatus' where IDENTIFIER = 1769;
update dyextn_abstract_metadata set NAME = 'marginStatus' where IDENTIFIER = 1780;
update dyextn_abstract_metadata set NAME = 'marginLocation' where IDENTIFIER = 1783;
update dyextn_abstract_metadata set NAME = 'marginStatus' where IDENTIFIER = 1787;
update dyextn_abstract_metadata set NAME = 'LocalExcisionColorectalDeepMargin' where IDENTIFIER = 1791;
update dyextn_abstract_metadata set NAME = 'DistanceFromAnalVerge' where IDENTIFIER = 1792;
update dyextn_abstract_metadata set NAME = 'specimenWeight' where IDENTIFIER = 1796;
update dyextn_abstract_metadata set NAME = 'adrenalGlandStage' where IDENTIFIER = 1801;
update dyextn_abstract_metadata set NAME = 'focality' where IDENTIFIER = 1802;
update dyextn_abstract_metadata set NAME = 'Size' where IDENTIFIER = 1827;
update dyextn_abstract_metadata set NAME = 'Size' where IDENTIFIER = 1828;
update dyextn_abstract_metadata set NAME = 'cannotBeDetermined' where IDENTIFIER = 1830;
update dyextn_abstract_metadata set NAME = 'additionalDimensionTwo' where IDENTIFIER = 1831;
update dyextn_abstract_metadata set NAME = 'additionalDimensionOne' where IDENTIFIER = 1832;
update dyextn_abstract_metadata set NAME = 'greatestDimension' where IDENTIFIER = 1833;
update dyextn_abstract_metadata set NAME = 'Size' where IDENTIFIER = 1834;
update dyextn_abstract_metadata set NAME = 'Size' where IDENTIFIER = 1835;
update dyextn_abstract_metadata set NAME = 'Size' where IDENTIFIER = 1836;
update dyextn_abstract_metadata set NAME = 'Size' where IDENTIFIER = 1838;
update dyextn_abstract_metadata set NAME = 'Size' where IDENTIFIER = 1840;
update dyextn_abstract_metadata set NAME = 'Size' where IDENTIFIER = 1841;
update dyextn_abstract_metadata set NAME = 'DistanceFromAnalVerge' where IDENTIFIER = 1843;
update dyextn_abstract_metadata set NAME = 'DistanceFromAnalVerge' where IDENTIFIER = 1844;
update dyextn_abstract_metadata set NAME = 'DistanceFromAnalVerge' where IDENTIFIER = 1845;
update dyextn_abstract_metadata set NAME = 'DistanceFromAnalVerge' where IDENTIFIER = 1847;

update dyextn_abstract_metadata set NAME = 'DistanceOfAdenoma' where IDENTIFIER = 1845;
update dyextn_abstract_metadata set NAME = 'DistanceOfInvasiveCarcinoma' where IDENTIFIER = 1847;
update dyextn_abstract_metadata set NAME = 'SpecimenSize' where IDENTIFIER = 1828;
update dyextn_abstract_metadata set NAME = 'SizeOfInvasiveCarcinoma' where IDENTIFIER = 1836;
update dyextn_abstract_metadata set NAME = 'SizeOfSpecimen' where IDENTIFIER = 1838;
update dyextn_abstract_metadata set NAME = 'PolypSize' where IDENTIFIER = 1841;
update dyextn_abstract_metadata set NAME = 'TumorSize' where IDENTIFIER = 1473;
update dyextn_abstract_metadata set NAME = 'DeprecatedAnnotation1' where IDENTIFIER = 1244;
update dyextn_abstract_metadata set NAME = 'DeprecatedAnnotation2' where IDENTIFIER = 1258;
update dyextn_abstract_metadata set NAME = 'DeprecatedAnnotation3' where IDENTIFIER = 1262;
update dyextn_abstract_metadata set NAME = 'Chemotherapy' where IDENTIFIER = 1432;
update dyextn_abstract_metadata set NAME = 'SpecimenAdditionalFinding' where IDENTIFIER = 1400;
update dyextn_abstract_metadata set NAME = 'SpecimenDetails' where IDENTIFIER = 1404;
update dyextn_abstract_metadata set NAME = 'ProstateSpecimenGleasonScore' where IDENTIFIER = 1408;
update dyextn_abstract_metadata set NAME = 'BreastSpecimenNottinghamHistologicScore' where IDENTIFIER = 1414;

alter table DE_E_1697 add column DE_E_1731_1733_IDENTIFIER int(38);

delete from dyextn_string_concept_value where IDENTIFIER = 25567;
delete from dyextn_userdef_de_value_rel where PERMISSIBLE_VALUE_ID = 25567;
delete from dyextn_permissible_value where IDENTIFIER = 25567;