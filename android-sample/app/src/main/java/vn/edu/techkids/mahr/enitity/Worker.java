package vn.edu.techkids.mahr.enitity;

import android.util.Log;

import com.google.gson.Gson;

import java.io.InputStreamReader;
import java.util.List;

import vn.edu.techkids.mahr.constants.Constants;

/**
 * Created by qhuydtvt on 3/9/2016.
 */
public class Worker {

    public final static int STATUS_FREE = 1;
    public final static int STATUS_WAITING = 2;
    public final static int STATUS_CONFIRM = 3;

    private int id;
    private String code;
    private String name;
    private int gender;
    private String nationality;
    private int birthyear;
    private String birthday;
    private int height;
    private int weight;
    private String mobile_number;
    private int education_level;
    private String avatar;
    private String excel_path;
    private int status;
    private int age;

    private List<Expertise> skills;

    private static List<Worker> workerList;

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getGender() {
        return gender;
    }

    public String getNationality() {
        return nationality;
    }

    public int getBirthyear() {
        return birthyear;
    }

    public String getBirthday() {
        return birthday;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public int getEducation_level() {
        return education_level;
    }

    public String getAvatar() {
        if(avatar == null || avatar == "")
            return Constants.API_DEFAULT_PHOTO_LINK;
        return avatar;
    }

    public String getAgetAvatarWithFixedSize() {
        if(avatar == null || avatar == "")
            return Constants.API_DEFAULT_PHOTO_LINK;
        return avatar + Constants.API_AVATAR_FIXED_SIZE_PARAM;
    }

    public String getExcel_path() {
        return excel_path;
    }

    public int getStatus() {
        return status;
    }

    public int getAge() {
        return age;
    }

    public List<Expertise> getSkills() {
        return skills;
    }

    public String getExpertiseString() {
        String ret = "";
        if(skills != null) {
            int size = skills.size();
            if (size > 0) {
                Log.d("getExpertiseString", String.valueOf(size));
                for (int i = 0; i < size; i++) {
                    String name = skills.get(i).getName();
                    if (name != null && name != "") {
                        ret += name;
                        if (i < size - 1) ret += ", ";
                    }
                }
            }
        }
        return ret;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setBirthyear(int birthyear) {
        this.birthyear = birthyear;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public void setEducation_level(int education_level) {
        this.education_level = education_level;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setExcel_path(String excel_path) {
        this.excel_path = excel_path;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSkills(List<Expertise> skills) {
        this.skills = skills;
    }

    public static List<Worker> getWorkerList() {
        return workerList;
    }

    /*public static void setWorkerList()*/

    public static List<Worker> loadJsonToList(InputStreamReader inputStreamReader) {
        WorkerList workerList = (new Gson()).fromJson(inputStreamReader, WorkerList.class);
        Worker.workerList = workerList.getList();
        return Worker.workerList;
    }

//    public static void loadJsonToList(JSONObject jsonObject) {
//        JSONArray jsonArray = null;
//        Gson gson = new Gson();
//        workerList.clear();
//        try {
//            jsonArray = jsonObject.getJSONArray(Constants.API_KEY_ITEMS);
//            for(int idx = 0; idx < jsonArray.length(); idx++) {
//                JSONObject jsonWorker = jsonArray.getJSONObject(idx);
//                Worker worker = gson.fromJson(jsonWorker.toString(), Worker.class);
//                workerList.add(worker);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }

}
