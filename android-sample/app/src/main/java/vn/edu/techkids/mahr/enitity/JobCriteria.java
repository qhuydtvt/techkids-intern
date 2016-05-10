package vn.edu.techkids.mahr.enitity;

import android.util.Log;

import com.google.gson.Gson;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import vn.edu.techkids.mahr.R;
import vn.edu.techkids.mahr.constants.Constants;

/**
 * Created by qhuydtvt on 3/7/2016.
 */
public class JobCriteria {

    private static JobCriteria inst = new JobCriteria();

    private String nationality;
    private String major;

    private List<Expertise> mExpertiseList;
    private List<Agency> mAgencyList;
    private int mApprenticeship = -1;

    private int minAge = -1;
    private int maxAge = -1;
    private int minHeight = -1;
    private int maxHeight = -1;
    private int minWeight = -1;
    private int maxWeight = -1;

    private Lang[] langs = new Lang[]{
            new Lang(R.string.vietnam, false),
            new Lang(R.string.indonesia, false),
            new Lang(R.string.taiwan, false)
    };

    private int minExperience = -1;
    private int maxExperience = -1;
    private int degree = -1;

    public static final int TOTAL_CRITERIA = 11;
    public static final int NATIONALITY_CRITERIA = 0;
    public static final int JOB_TYPE = 1;
    public static final int EXPERTISE = 2;
    public static final int AGE = 3;
    public static final int HEIGHT = 4;
    public static final int WEIGHT = 5;
    public static final int LANG = 6;
    public static final int EXP = 7;
    public static final int DEGREE = 8;
    public static final int AGENCY = 9;
    public static final int APPRENTICESHIP = 10;

    private JobCriteriaListener mJobCriteriaListener;

    private JobCriteria() {
    }

    public static JobCriteria getInst() {
        return inst;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getExpertiseString() {
        String ret = "";
        if (mExpertiseList != null) {
            for (int idx = 0;
                 idx < mExpertiseList.size();
                 idx++) {
                Expertise expertise = mExpertiseList.get(idx);
                if (expertise.getSelected()) {
                    if (ret != "") {
                        ret += " | ";
                    }
                    ret += expertise.getName();
                }
            }
        }
        return ret;
    }

    public String getAgencyString() {
        String ret = "";
        if (mAgencyList != null) {
            for (Agency agency : mAgencyList) {
                if (agency.getSelected()) {
                    ret = agency.getChineseName();
                    break;
                }
            }
        }
        return ret;
    }

    public String getLangString() {
        String ret = "";
        for (int idx = 0;
             idx < langs.length;
             idx++) {
            if (langs[idx].isSelected()) {
                if (ret != "") {
                    ret += " | ";
                }
                ret += langs[idx].getid();
            }
        }
        return ret;
    }

    public void setAgeRange(int minAge, int maxAge) {
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.notifyListener();
    }

    public String getAgeRange() {
        if (minAge == -1 || maxAge == -1) {
            return null;
        }
        return String.format("%d - %d", minAge, maxAge);
    }

    public String getWeightRange() {
        if (minWeight == -1 || maxWeight == -1) {
            return null;
        }
        return String.format("%d - %d", minWeight, maxWeight);
    }

    public String getHeightRange() {
        if (minHeight == -1 || maxHeight == -1) {
            return null;
        }
        return String.format("%d - %d", minHeight, maxHeight);
    }

    public String getExpRange() {
        if (minExperience == -1 || maxExperience == -1) {
            return null;
        }
        return String.format("%d - %d", minExperience, maxExperience);
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
        notifyListener();
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
        notifyListener();
    }

    public int getApprenticeship() {
        return mApprenticeship;
    }

    public boolean isApprenticeship() {
        return mApprenticeship == 1;
    }

    public void setApprenticeship(int apprenticeship) {
        this.mApprenticeship = apprenticeship;
        this.notifyListener();
    }

    public void setJobCriteriaListener(JobCriteriaListener jobCriteriaListener) {
        this.mJobCriteriaListener = jobCriteriaListener;
    }

    public int getMinHeight() {
        return minHeight;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public List<Expertise> getExpertiseList() {
        return this.mExpertiseList;
    }

    public List<Agency> getAgencyList() {
        return this.mAgencyList;
    }

    public Object loadExpertiseFromJSON(InputStreamReader inputStreamReader) {
        ExpertiseList expertiseList = (new Gson()).fromJson(inputStreamReader, ExpertiseList.class);
        this.mExpertiseList = expertiseList.getList();
        return this.mExpertiseList;
    }

    public Object loadAgencyFromJSON(InputStreamReader inputStreamReader) {
        AgencyList agencyList = (new Gson()).fromJson(inputStreamReader, AgencyList.class);
        this.mAgencyList = agencyList.getList();
        return this.mExpertiseList;
    }

    public Lang[] getLangs() {
        return this.langs;
    }
/*
    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
        notifyListener();
    }*/

    public void setHeightRange(int minHeight, int maxHeight) {
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.notifyListener();
    }

    public int getMinWeight() {
        return minWeight;
    }

    /*public void setMinWeight(int minWeight) {
        this.minWeight = minWeight;
        notifyListener();
    }*/

    public int getMaxWeight() {
        return maxWeight;
    }

    /*public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
        notifyListener();
    }*/

    /*public int getLanguage() {
        return language;
    }

    public void setLanguage(int language) {
        this.language = language;
        notifyListener();
    }*/

    /*public String getLanguageAPIParam() {
        *//*switch (language){
            case R.string.vietnam: return Constants.API_VIETNAM;
            case R.string.indonesia: return Constants.API_INDONESIA;
            case R.string.taiwan: return Constants.API_TAIWAN;
        }
        return "";*//*

        String ret = "";
        for (int idx = 0; idx < langs.length; idx++) {
            if (langs[idx].isSelected()) {
                if (ret != "") ret += ",";
                ret += langs[idx].getAPIParam();
            }
        }
        return ret;
    }*/

    public int getDegree() {
        return degree;
    }

    private String getDegreeAPIParam() {
        switch (degree) {
            case R.string.primary_school:
                return Constants.API_PARAM_DEGREE_PRIMARY_SCHOOL;
            case R.string.secondary_school:
                return Constants.API_PARAM_DEGREE_SECONDARY_SCHOOL;
            case R.string.high_school:
                return Constants.API_PARAM_DEGREE_HIGH_SCHOOL;
            case R.string.vocational:
                return Constants.API_PARAM_DEGREE_VOCATIONAL;
            case R.string.college:
                return Constants.API_PARAM_DEGREE_COLLEGE;
            case R.string.university:
                return Constants.API_PARAM_DEGREE_UNIVERSITY;
        }
        return "";
    }


    public void setDegree(int degree) {
        this.degree = degree;
        notifyListener();
    }

    /*public void setMinExperience(int minExperience) {
        notifyListener();
        this.minExperience = minExperience;
    }

    public void setMaxExperience(int maxExperience) {
        notifyListener();
        this.maxExperience = maxExperience;
    }*/

    public void setExperienceRange(int minExperience, int maxExperience) {
        this.minExperience = minExperience;
        this.maxExperience = maxExperience;
        notifyListener();
    }

    public void setWeightRange(int minWeight, int maxWeight) {
        this.minWeight = minWeight;
        this.maxWeight = maxWeight;
        notifyListener();
    }

//    public  void loadExperiseArrayList(JSONObject jsonObject) {
//
//    }


//    public  void loadExperiseArrayList(JSONObject jsonObject) {
//        try {
//            JSONArray jsonArray = jsonObject.getJSONArray(Constants.API_KEY_ITEMS);
//            expertiseList = new ArrayList<>();
//            for(int i = 0; i < jsonArray.length(); i++) {
//                Expertise expertise = new Expertise(jsonArray.getJSONObject(i));
//                expertiseList.add(expertise);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }

    public void setExpertiseArrayList() {

    }

    public void notifyListener() {
        if (this.mJobCriteriaListener != null) {
            mJobCriteriaListener.onJobCriteriaChange();
        }
    }

    /***********************************************************************************************/
    /******************************************* API ***********************************************/
    /***********************************************************************************************/
    private String getExpertiseAPIString() {
        /*String param = getExpertiseAPIParam();
        if(param != null && param != "") return String.format(Constants.API_FILTER_EXPERTISE_FORMAT,
                param);
        return "";*/
        String ret = "";

        if (mExpertiseList != null) {
            for (Expertise expertise : mExpertiseList) {
                if (expertise.getSelected()) {
                    ret += String.format(Constants.API_FILTER_EXPERTISE_FORMAT, expertise.getId());
                }
            }
        }

        return ret;
    }

//    private String getExpertiseAPIParam() {
//        /*if(expertise == -1) return "";
//        return String.format(Constants.API_FILTER_EXPERTISE_FORMAT, this.expertise);*/
//
//        String ret = "";
//        if(expertiseList != null) {
//            for (int idx = 0; idx < expertiseList.size(); idx++) {
//                Expertise expertise = expertiseList.get(idx);
//                if (expertise.getSelected()) {
//                    if (ret != "")
//                        ret += ",";
//                    ret += expertise.getChineseName();
//                }
//            }
//        }
//        return ret;
//    }

    private String getAgeAPIString() {
        if (this.minAge == -1 || this.maxAge == -1) {
            return "";
        }
        return String.format(Constants.API_FILTER_AGE_FORMAT, this.minAge, this.maxAge);
    }

    private String getBirthyearAPIString() {
        String ret = "";

        if (minAge != -1 && maxAge != 1) {

            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);

            int birthyear_low = year - maxAge;
            int birthyear_high = year - minAge;
            ret = String.format(Constants.API_FILTER_BIRTH_YEAR_FORMAT, birthyear_low, birthyear_high);
        }

        return ret;
    }

    private String getHeightAPIString() {
        if (this.minHeight == -1 || this.maxHeight == -1) {
            return "";
        }
        return String.format(Constants.API_FILTER_HEIGHT_FORMAT, this.minHeight, this.maxHeight);
    }

    private String getWeightAPIString() {
        if (this.minWeight == -1 || this.maxWeight == -1) {
            return "";
        }
        return String.format(Constants.API_FILTER_WEIGHT_FORMAT, this.minWeight, this.maxWeight);
    }

    private String getLangAPIString() {
        String ret = "";
        for (Lang lang : this.langs) {
            if (lang.isSelected()) {
                ret += String.format(Constants.API_FILTER_LANG_FORMAT,
                        lang.getAPIParam().toLowerCase());
            }
        }
        return ret;

        /*if(this.getLanguageAPIParam() == "") return "";
        return String.format(Constants.API_FILTER_LANG_FORMAT, this.getLanguageAPIParam());*/
    }

    public List<Integer> getLangIds() {
        ArrayList<Integer> retList = new ArrayList<>();

        for (Lang lang : this.langs) {
            if (lang.isSelected()) {
                retList.add(lang.getid());
            }
        }
        return retList;
    }

    private String getExperienceAPIString() {
        if (minExperience == -1 || maxExperience == -1) {
            return "";
        }
        return String.format(Constants.API_FILTER_EXP_FORMAT, this.minExperience, this.maxExperience);
    }

    private String getDegreeAPIString() {
        if (degree == -1) {
            return "";
        }
        return String.format(Constants.API_FILTER_DEGREE_FORMAT, getDegreeAPIParam());
    }

    private String getMajorAPIString() {
        if (major == null) {
            return "";
        }
        return String.format(Constants.API_FILTER_MAJOR_FORMAT, major);
    }

    private String getNationalityAPIString() {
        if (nationality == null) {
            return "";
        }
        return String.format(Constants.API_FILTER_NATION_FORMAT, nationality);
    }

    public String getApprenticeshipAPIString() {
        if (mApprenticeship == -1) {
            return "";
        }
        return String.format(Constants.API_FILTER_APPRENTICESHIP_FORMAT, mApprenticeship);
    }

    public String getAgencyAPIString() {
        String ret = "";

        if (mAgencyList != null) {
            for (Agency agency : mAgencyList) {
                if (agency.getSelected()) {
                    ret = String.format(Constants.API_FILTER_AGENCY_FORMAT,
                            agency.getId());
                    break;
                }
            }
        }

        return ret;
    }

    public String getAPIString() {
        String url = Constants.API_URL_PROFILE_GET +
                getExpertiseAPIString() +
                getBirthyearAPIString() +
                getHeightAPIString() +
                getWeightAPIString() +
                getLangAPIString() +
                getDegreeAPIString() +
                getMajorAPIString() +
                getNationalityAPIString() +
                getApprenticeshipAPIString() +
                getAgencyAPIString();
        Log.d("API", "API = " + url);
        return url;

        /* getExperienceAPIString() + */
    }
}
