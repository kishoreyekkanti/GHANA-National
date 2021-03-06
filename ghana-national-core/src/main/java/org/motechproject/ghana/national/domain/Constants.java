package org.motechproject.ghana.national.domain;

public class Constants {
    //security roles
    public static final String SECURITY_ROLE_SUPER = "System Developer";
    public static final String SECURITY_ROLE_PROVIDER = "Provider";
    public static final String SECURITY_ROLE_CALL_CENTER = "Create/Edit MoTeCH Data";

    //person attributes
    public static final String PERSON_ATTRIBUTE_TYPE_EMAIL = "Email";
    public static final String PERSON_ATTRIBUTE_TYPE_PHONE_NUMBER = "Phone Number";
    public static final String PERSON_ATTRIBUTE_TYPE_STAFF_TYPE = "Staff Type";

    public static final String COUNTRIES = "countries";
    public static final String REGIONS = "regions";
    public static final String DISTRICTS = "districts";
    public static final String PROVINCES = "provinces";
    public static final String FACILITIES = "facilities";
    public static final String EMAIL_SUCCESS = "email_success";
    public static final String EMAIL_FAILURE = "email_failure";

    public static final String FORGOT_PASSWORD_SUCCESS = "Your Password is sent via email successfully";
    public static final String FORGOT_PASSWORD_FAILURE = "Your Password is not sent to you via email successfully.Please try again.";
    public static final String FORGOT_PASSWORD_USER_NOT_FOUND = "User Name not found. Please register!!!";
    public static final String FORGOT_PASSWORD_MESSAGE = "message";

    //idgen sources
    public static final String IDGEN_SEQ_ID_GEN_PATIENT_ID = "MoTeCH ID Generator";
    public static final String IDGEN_SEQ_ID_GEN_STAFF_ID = "MoTeCH Staff ID Generator";
    public static final String IDGEN_SEQ_ID_GEN_FACILITY_ID = "MoTeCH Facility ID Generator";

    //patient identifier types
    public static final String PATIENT_IDENTIFIER_TYPE_FACILITY_ID = "MoTeCH Facility Id";
    public static final String PATIENT_IDENTIFIER_TYPE_PATIENT_ID = "MoTeCH Id";
    public static final String PATIENT_IDENTIFIER_TYPE_STAFF_ID = "MoTeCH Staff Id";
    public static final String PATIENT_GENDER_MALE = "M";
    public static final String PATIENT_GENDER_FEMALE = "F";

    public static final String PATTERN_YYYY_MM_DD = "yyyy-MM-dd";

    public static final String NOT_FOUND = "not found";



    public static final String CARE_HISTORIES = "careHistories";
    public static final String LAST_IPTI = "lastIPTi";
    public static final String IPTI_1 = "IPTi 1";
    public static final String IPTI_2 = "IPTi 2";
    public static final String IPTI_3 = "IPTi 3";
    public static final String OPV_0 = "OPV 0";
    public static final String OPV_1 = "OPV 1";
    public static final String OPV_2 = "OPV 2";
    public static final String OPV_3 = "OPV 3";
    public static final String LAST_OPV = "lastOPV";
    public static final String LAST_PENTA = "lastPenta";
    public static final String PENTA_1 = "Penta 1";
    public static final String PENTA_2 = "Penta 2";
    public static final String PENTA_3 = "Penta 3";


    public static final String CONCEPT_OPV = "ORAL POLIO VACCINATION DOSE";
    public static final String CONCEPT_PENTA = "PENTA VACCINATION DOSE";
    public static final String CONCEPT_IPTI = "INTERMITTENT PREVENTATIVE TREATMENT INFANTS DOSE";
    public static final String CONCEPT_MEASLES = "MEASLES VACCINATION";
    public static final String CONCEPT_YF = "YELLOW FEVER VACCINATION";
    public static final String CONCEPT_VITA = "VITAMIN A";
    public static final String CONCEPT_BCG = "BACILLE CAMILE-GUERIN VACCINATION";
    public static final String CONCEPT_IMMUNIZATIONS_ORDERED = "IMMUNIZATIONS ORDERED";
    public static final String CONCEPT_CWC_REG_NUMBER = "CWC REGISTRATION NUMBER";

}
