package sociopathycheck.selector.models;

/**
 * Created by JS on 12/8/16.
 */

public class DarkSky {
    private String mSummary;
    private String mDate;
    private String mTempHigh;
    private String mTempLow;


    public DarkSky(String summary, String date, String tempHigh, String tempLow) {
        this.mSummary = summary;
        this.mDate = date;
        this.mTempHigh = tempHigh;
        this.mTempLow = tempLow;
    }

    public String getSummary() {
        return mSummary; }

    public String getDate() {
        return mDate;

}
    public String getTempHigh() {
        return mTempHigh;
    }
    public String getTempLow() {
        return mTempLow;
    }
}
