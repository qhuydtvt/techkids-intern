package vn.edu.techkids.mahr.enitity;

import java.util.ArrayList;

/**
 * Created by qhuydtvt on 3/6/2016.
 */
public class JobCriteriaViewModel {
    private int mPropertyNameId;
    private int mImageId;
    /*private String mValue;*/
    private int mCriteria;
    /*private JobCriteria mJobCriteria;*/

    public JobCriteriaViewModel(int propertyNameId, int imageId, int criteria) {
        this.mPropertyNameId = propertyNameId;
        this.mImageId = imageId;
        this.mCriteria = criteria;
        /*mJobCriteria = JobCriteria.getInst();*/
        /*this.mValue = null;*/
    }

    public int getPropertyNameId (){ return mPropertyNameId; }
    public int getImageId() { return mImageId; }
    public int getCriteria() {return mCriteria; }
    /*public String getValue() {
        switch (mCriteria) {
            case JobCriteria.EXPERTISE: return mJobCriteria.getExpertise();
            case JobCriteria.AGE: return mJobCriteria.getAgeRange();
            case JobCriteria.HEIGHT: return mJobCriteria.getHeightRange();
            case JobCriteria.WEIGHT: return mJobCriteria.getWeightRange();
            case JobCriteria.LANG: return mJobCriteria.getLanguage();
            case JobCriteria.EXP: return mJobCriteria.getExpRange();
            case JobCriteria.DEGREE: return mJobCriteria.getDegree();
        }
        return null;
    }*/
}
