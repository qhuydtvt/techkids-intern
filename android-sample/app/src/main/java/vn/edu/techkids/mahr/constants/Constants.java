package vn.edu.techkids.mahr.constants;

/**
 * Created by hungtran on 3/6/16.
 */
public class Constants {

    public static final int MIN_AGE = 15;
    public static final int MAX_AGE = 60;

    public static final int MIN_HEIGHT = 150;
    public static final int MAX_HEIGHT = 190;

    public static final int MIN_WEIGHT = 40;
    public static final int MAX_WEIGHT = 190;

    public static final int MIN_XP = 0;
    public static final int MAX_XP = 30;

    public final static String API_VIETNAM = "vi";
    public final static String API_INDONESIA = "id";
    public final static String API_TAIWAN = "cn";

    public final static String API_NATIONALITY_VIETNAM = "vn";
    public final static String API_NATIONALITY_INDONESIA = "id";
    public final static String API_NATIONALITY_TAIWAN = "cn";

    public static String API_DATE_FORMAT = "%04d-%02d-%02d";
    public static String API_AVATAR_FIXED_SIZE_PARAM = "?w=196&h=-";

    public static String API_PARAM_DEGREE_PRIMARY_SCHOOL = "1";
    public static String API_PARAM_DEGREE_SECONDARY_SCHOOL = "2";
    public static String API_PARAM_DEGREE_HIGH_SCHOOL = "3";
    public static String API_PARAM_DEGREE_VOCATIONAL = "4";
    public static String API_PARAM_DEGREE_COLLEGE = "5";
    public static String API_PARAM_DEGREE_UNIVERSITY = "6";

    /*public static final String KEY_VIETNAM = "KEYVIETNAM";
    public static final String KEY_INDONESIA = "KEYINDONESIA";*/

    public static final String API_MAJOR_MALE_WORKER = "worker_male";
    public static final String API_MAJOR_FEMALE_WORKER = "worker_female";
    public static final String API_MAJOR_HOUSEMAID = "house_cleaner";

    public static final String API_URL_ROOT = "http://api.mahr.adcviet.com/v1";
    public static final String API_URL_EXPERTISE = API_URL_ROOT + "/skills";
    public static final String API_URL_AGENCY = API_URL_ROOT + "/agencies";

    public static final String API_URL_PROFILE_GET = API_URL_ROOT + "/profiles?";
    public static final String API_URL_PROFILE_PUT = API_URL_ROOT + "/profiles/";

    public static final String API_URL_PROGRESSES_GET = API_URL_ROOT + "/progresses/";
    public static final String API_URL_PROGRESSES_PUT = API_URL_ROOT + "/progresses/";

    public static final String API_KEY_ITEMS ="items";

    public static final String API_DEFAULT_PHOTO_LINK = "http://rs829.pbsrc.com/albums/zz219/vinadragon/Son16.jpg~c200";

    public static final String HEIGHT_UNIT = "cm";
    public static final String WEIGHT_UINT = "kg";

    public static String API_FILTER_PREFIX = "&filter[]=";
    public static String API_FILTER_EXPERTISE_FORMAT = API_FILTER_PREFIX + "skill_%s+eq+1";
    public static String API_FILTER_AGE_FORMAT = API_FILTER_PREFIX + "age+between+%s,%s";
    public static String API_FILTER_BIRTH_YEAR_FORMAT = API_FILTER_PREFIX + "birthyear+between+%s,%s";
    public static String API_FILTER_HEIGHT_FORMAT = API_FILTER_PREFIX + "height+between+%s,%s";
    public static String API_FILTER_WEIGHT_FORMAT = API_FILTER_PREFIX + "weight+between+%s,%s";
    public static String API_FILTER_LANG_FORMAT = API_FILTER_PREFIX + "lang_%s+eq+1";
    public static String API_FILTER_EXP_FORMAT = API_FILTER_PREFIX + "exp_year+between+%s,%s";
    public static String API_FILTER_DEGREE_FORMAT = API_FILTER_PREFIX + "educational_level+gte+%s";
    public static String API_FILTER_MAJOR_FORMAT = API_FILTER_PREFIX + "job_%s+eq+1";
    public static String API_FILTER_NATION_FORMAT = API_FILTER_PREFIX + "nationality+eq+%s";
    public static String API_FILTER_APPRENTICESHIP_FORMAT = API_FILTER_PREFIX + "apprenticeship+eq+%s";
    public static String API_FILTER_AGENCY_FORMAT = API_FILTER_PREFIX + "agency_id+eq+%s";

    public static String API_URL_PROFILE_PUT_FORMAT = API_URL_PROFILE_PUT + "%s";
    public static String API_PUT_STATUS = "status";

    public static String API_PUT_WORKER_STATUS_USE = "2";
    public static String API_PUT_WORKER_STATUS_CONFIRM = "3";

    public static String API_PUT_STATUS_NOT_DONE = "0";
    public static String API_PUT_STATUS_DONE = "1";


    public static String API_URL_PROGRESSES_GET_FORMAT =  API_URL_PROGRESSES_GET +  "%s?expand=profile";
    public static String API_URL_PROGRESSES_PUT_FORMAT = API_URL_PROGRESSES_PUT + "%s?expand=profile";

}
