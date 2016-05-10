package vn.edu.techkids.mahr.enitity;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.InputStreamReader;

/**
 * Created by qhuydtvt on 3/16/2016.
 */
public class MigrationProgress {

    public final static String API_MIG_PROG_PROFILE_ID = "profile_id";
    public final static String API_MIG_PROG_FACTORY = "factory";
    public final static String API_MIG_PROG_DEPARTMENT = "department";
    public final static String API_MIG_PROG_COMPANY = "company";
    public final static String API_MIG_PROG_PASSPORT_STATUS = "1_passport_status";
    public final static String API_MIG_PROG_PASSPORT_END_DATE = "1_passport_end_date";
    public final static String API_MIG_PROG_LEGAL_STATUS = "2_legal_status";
    public final static String API_MIG_PROG_LEGAL_END_DATE = "2_legel_end_date";
    public final static String API_MIG_PROG_HEALTH_STATUS = "3_health_status";
    public final static String API_MIG_PROG_HEALTH_END_DATE = "3_health_end_date";
    public final static String API_MIG_PROG_FILE_STATUS = "4_file_status";
    public final static String API_MIG_PROG_FILE_END_DATE = "4_file_end_date";
    public final static String API_MIG_PROG_OFFICE_STATUS = "5_office_status";
    public final static String API_MIG_PROG_OFFICE_START_DATE = "5_office_start_date";
    public final static String API_MIG_PROG_OFFICE_END_DATE = "5_office_end_date";
    public final static String API_MIG_PROG_VISA_STATUS = "6_visa_status";
    public final static String API_MIG_PROG_VISA_START_DATE = "6_visa_start_date";
    public final static String API_MIG_PROG_VISA_END_DATE = "6_visa_end_date";
    public final static String API_MIG_PROG_FLIGHT_STATUS = "7_flight_status";
    public final static String API_MIG_PROG_FLIGHT_END_DATE = "7_flight_end_date";
    public final static String API_MIG_PROG_FINISH = "8_finish";
    public final static String API_MIG_PROG_PROFILE = "profile";



    @SerializedName(API_MIG_PROG_PROFILE_ID)
    private int profileId;

    @SerializedName(API_MIG_PROG_FACTORY)
    private String factory;

    @SerializedName(API_MIG_PROG_DEPARTMENT)
    private String department;

    @SerializedName(API_MIG_PROG_COMPANY)
    private String company;

    @SerializedName(API_MIG_PROG_PASSPORT_STATUS)
    private int passportStatus;

    @SerializedName(API_MIG_PROG_PASSPORT_END_DATE)
    private String passportEndDate;

    @SerializedName(API_MIG_PROG_LEGAL_STATUS)
    private int legalStatus;

    @SerializedName(API_MIG_PROG_LEGAL_END_DATE)
    private String legalEndDate;

    @SerializedName(API_MIG_PROG_HEALTH_STATUS)
    private int healthStatus;

    @SerializedName(API_MIG_PROG_HEALTH_END_DATE)
    private String healthEndDate;

    @SerializedName(API_MIG_PROG_FILE_STATUS)
    private int fileStatus;

    @SerializedName(API_MIG_PROG_FILE_END_DATE)
    private String fileEndDate;

    @SerializedName(API_MIG_PROG_OFFICE_STATUS)
    private int officeStatus;

    @SerializedName(API_MIG_PROG_OFFICE_START_DATE)
    private String officeStartDate;

    @SerializedName(API_MIG_PROG_OFFICE_END_DATE)
    private String officeEndDate;

    @SerializedName(API_MIG_PROG_VISA_STATUS)
    private int visaStatus;

    @SerializedName(API_MIG_PROG_VISA_START_DATE)
    private String visaStartDate;

    @SerializedName(API_MIG_PROG_VISA_END_DATE)
    private String visaEndDate;

    @SerializedName(API_MIG_PROG_FLIGHT_STATUS)
    private int flightStatus;

    @SerializedName(API_MIG_PROG_FLIGHT_END_DATE)
    private String flightEndDate;

    @SerializedName(API_MIG_PROG_FINISH)
    private int finish;

    @SerializedName(API_MIG_PROG_PROFILE)
    private Worker profile;

    public void setmDataChangeListener(DataChangeListener mDataChangeListener) {
        this.mDataChangeListener = mDataChangeListener;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getPassportStatus() {
        return passportStatus;
    }

    public void setPassportStatus(int passportStatus) {
        this.passportStatus = passportStatus;
    }

    public String getPassportEndDate() {
        return passportEndDate;
    }

    public void setPassportEndDate(String passportEndDate) {
        this.passportEndDate = passportEndDate;
    }

    public int getLegalStatus() {
        return legalStatus;
    }

    public void setLegalStatus(int legalStatus) {
        this.legalStatus = legalStatus;
    }

    public String getLegalEndDate() {
        return legalEndDate;
    }

    public void setLegalEndDate(String legalEndDate) {
        this.legalEndDate = legalEndDate;
    }

    public int getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(int healthStatus) {
        this.healthStatus = healthStatus;
    }

    public String getHealthEndDate() {
        return healthEndDate;
    }

    public void setHealthEndDate(String healthEndDate) {
        this.healthEndDate = healthEndDate;
    }

    public int getFileStatus() {
        return fileStatus;
    }

    public void setFileStatus(int fileStatus) {
        this.fileStatus = fileStatus;
    }

    public String getFileEndDate() {
        return fileEndDate;
    }

    public void setFileEndDate(String fileEndDate) {
        this.fileEndDate = fileEndDate;
    }

    public int getOfficeStatus() {
        return officeStatus;
    }

    public void setOfficeStatus(int officeStatus) {
        this.officeStatus = officeStatus;
    }

    public String getOfficeStartDate() {
        return officeStartDate;
    }

    public void setOfficeStartDate(String officeStartDate) {
        this.officeStartDate = officeStartDate;
    }

    public String getOfficeEndDate() {
        return officeEndDate;
    }

    public void setOfficeEndDate(String officeEndDate) {
        this.officeEndDate = officeEndDate;
    }

    public int getVisaStatus() {
        return visaStatus;
    }

    public void setVisaStatus(int visaStatus) {
        this.visaStatus = visaStatus;
    }

    public String getVisaStartDate() {
        return visaStartDate;
    }

    public void setVisaStartDate(String visaStartDate) {
        this.visaStartDate = visaStartDate;
    }

    public String getVisaEndDate() {
        return visaEndDate;
    }

    public void setVisaEndDate(String visaEndDate) {
        this.visaEndDate = visaEndDate;
    }

    public int getFlightStatus() {
        return flightStatus;
    }

    public void setFlightStatus(int flightStatus) {
        this.flightStatus = flightStatus;
    }

    public String getFlightEndDate() {
        return flightEndDate;
    }

    public void setFlightEndDate(String flightEndDate) {
        this.flightEndDate = flightEndDate;
    }

    public int getFinish() {
        return finish;
    }

    public void setFinish(int finish) {
        this.finish = finish;
    }

    public Worker getProfile() {
        return profile;
    }

    public void setProfile(Worker profile) {
        this.profile = profile;
    }

    public static void notifyDataChanged(){
        if(mDataChangeListener != null){
            mDataChangeListener.onDataChange(inst);
        }
    }

    public static void setDataChangeListener(DataChangeListener dataChangeListener) {
        mDataChangeListener = dataChangeListener;
    }

    private static DataChangeListener mDataChangeListener;

    private static MigrationProgress inst;

    public static MigrationProgress getInst() {
        return inst;
    }

    public static MigrationProgress loadFromJSON(InputStreamReader inputStreamReader) {
        inst = (new Gson()).fromJson(inputStreamReader, MigrationProgress.class);
//        notifyDataChanged();
        return inst;
    }
}
