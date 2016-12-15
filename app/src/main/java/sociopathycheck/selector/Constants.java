package sociopathycheck.selector;

import android.os.Build;

/**
 * Created by JS on 11/19/16.
 */

public class Constants {

        // YELP

        public static final String YELP_CONSUMER_KEY = BuildConfig.YELP_CONSUMER_KEY;
        public static final String YELP_CONSUMER_SECRET = BuildConfig.YELP_CONSUMER_SECRET;
        public static final String YELP_TOKEN = BuildConfig.YELP_TOKEN;
        public static final String YELP_TOKEN_SECRET = BuildConfig.YELP_TOKEN_SECRET;
        public static final String YELP_BASE_URL = "https://api.yelp.com/v2/search?term=food";
        public static final String YELP_LOCATION_QUERY_PARAMETER = "location";


        // FOURSQUARE
        public static final String FOURSQUARE_CLIENT_ID = BuildConfig.FOURSQUARE_CLIENT_ID;
        public static final String FOURSQUARE_CLIENT_SECRET = BuildConfig.FOURSQUARE_CLIENT_SECRET;
        public static final String FOURSQUARE_BASE_URL = "https://api.foursquare.com/v2/venues/explore?";
        public static final String FOURSQUARE_LAT_LONG = "ll";
        public static final String FOURSQUARE_EXTRAS = "&v=20161214%20&m=foursquare";
        public static final String FOURSQUARE_CID = "client_id";
        public static final String FOURSQUARE_CS = "client_secret";


        //WEATHER

        public static final String WEATHER_API_KEY = BuildConfig.WEATHER_API_KEY;
        public static final String WEATHER_BASE_URL = "http://api.openweathermap.org/data/2.5/forecast/daily?mode=json&units=imperial&cnt=1";

        public static final String WEATHER_LOCATION_QUERY_PARAMETER = "q";
        public static final String WEATHER_API_QUERY_PARAMETER = "appid";


        //TIME

        public static final String TIME_API_KEY = BuildConfig.TIME_API_KEY;

        public static final String TIME_BASE_URL = "http://api.worldweatheronline.com/premium/v1/tz.ashx?format=json";

        public static final String TIME_API_QUERY_PARAMETER = "key";

        public static final String TIME_LOCATION_QUERY_PARAMETER = "q";




        // PLACE

        public static final String PLACES_API_KEY = BuildConfig.PLACE_API_KEY;

        public static final String PLACE_API_QUERY_PARAMETER ="key";

        public static final String PLACE_LOCATION_QUERY_PARAMETER = "location";

        // location=40.8665166,-124.0828396 (example)
        // key=

        public static final String PLACES_BASE_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?&radius=500";

//

        // PHOTO

        public static final String PHOTO_LOCATION_QUERY_PARAMETER = "photoreference";

        public static final String PHOTOS_BASE_URL = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=800";


        // Dark Sky

        public static final String DARKSKY_API_KEY = BuildConfig.DARKSKY_API_KEY;

        public static final String DARKSKY_BASE_URL = "https://api.darksky.net/forecast/";


}