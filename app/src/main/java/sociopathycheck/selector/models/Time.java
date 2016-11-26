package sociopathycheck.selector.models;

/**
 * Created by JS on 11/20/16.
 */

public class Time {
    private String mLocalTime;

    public Time(String localTime) {
        this.mLocalTime = localTime;
    }

    public String getTime() {

        return mLocalTime;
    }
}
