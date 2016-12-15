package sociopathycheck.selector.models;

import java.util.ArrayList;

/**
 * Created by JS on 12/14/16.
 */

public class FourSquare {
    private String mVenueName;
    private double mVenueLat;
    private double mVenueLong;
    private String mVenueId;
    private String mVenueCity;
    private String mVenueState;
    private double mVenueRating;
    private String mVenueCategoryName;
    private String mVenueCurrency;
    private String mVenueStatus;
    private boolean mVenueisOpen;
    private String mVenueTipsText;
    private ArrayList<String> mVenueFormattedAddress;

    public FourSquare(String venueName, double venueLat, double venueLong, String venueId, String venueCity, String venueState, double venueRating, String venueCategoryName,
                      String venueCurrency, String venueStatus, boolean venueIsOpen, String venueTipsText, ArrayList<String> venueFormattedAddress) {
        this.mVenueName = venueName;
        this.mVenueLat = venueLat;
        this.mVenueLong = venueLong;
        this.mVenueId = venueId;
        this.mVenueCity = venueCity;
        this.mVenueState = venueState;
        this.mVenueRating = venueRating;
        this.mVenueCategoryName = venueCategoryName;
        this.mVenueCurrency = venueCurrency;
        this.mVenueStatus = venueStatus;
        this.mVenueisOpen = venueIsOpen;
        this.mVenueTipsText = venueTipsText;
        this.mVenueFormattedAddress = venueFormattedAddress;
    }

    public String getVenueName() {

        return mVenueName;
    }

    public Double getVenueLat() {
        return mVenueLat;
    }

    public Double getVenueLong() {
        return mVenueLong;
    }

    public String getVenueId() {
        return mVenueId;
    }
    public String getVenueCity() {
        return mVenueCity;
    }
    public String getVenueState() {
        return mVenueState;
    }
    public Double getVenueRating() {
        return mVenueRating;
    }
    public String getVenueCategoryName() {
        return mVenueCategoryName;
    }
    public String getVenueCurrency() {
        return mVenueCurrency;
    }
    public String getVenueStatus() {
        return mVenueStatus;
    }
    public boolean getVenueIsOpen() {
        return mVenueisOpen;
    }
    public String getVenueTipsText() {
        return mVenueTipsText;
    }
    public ArrayList<String> getVenueFormattedAddress() {
        return mVenueFormattedAddress;
    }

}


