package sociopathycheck.selector.models;

/**
 * Created by JS on 11/20/16.
 */

public class Weather {
    private int mDayTemperature;
    private String mCityName;
    private String mCountry;
    private int mCityId;
    private String mDescription;
    private int mHumidity;
    private int mWindSpeed;

    public Weather(String cityName, int cityId, String country, int dayTemp, String description, int humidity, int windSpeed) {
        this.mDayTemperature = dayTemp;
        this.mCityName = cityName;
        this.mCountry = country;
        this.mCityId = cityId;
        this.mDescription = description;
        this.mHumidity = humidity;
        this.mWindSpeed = windSpeed;
    }

    public int getTemp() {

        return mDayTemperature;
    }

    public String getName() {
        return mCityName;
    }

    public String getCountry() {
        return mCountry;
    }
    public String getDescription() {
        return mDescription;
    }
    public int getHumidity() {
        return mHumidity;
    }
    public int getWindSpeed() {
        return mWindSpeed;
    }
}
