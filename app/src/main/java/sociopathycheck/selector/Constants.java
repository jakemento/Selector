package sociopathycheck.selector;

/**
 * Created by JS on 11/19/16.
 */

public class Constants {
        public static final String WEATHER_API_KEY = BuildConfig.WEATHER_API_KEY;
        public static final String WEATHER_BASE_URL = "http://api.openweathermap.org/data/2.5/forecast/daily?mode=json&units=imperial&cnt=1";

        public static final String WEATHER_LOCATION_QUERY_PARAMETER = "q";
        public static final String WEATHER_API_QUERY_PARAMETER = "appid";

        public static final String TIME_API_KEY = BuildConfig.TIME_API_KEY;

        //public static final String TIME_BASE_URL = "http://api.worldweatheronline.com/premium/v1/tz.ashx?&format=json";
        public static final String TIME_BASE_URL = "http://api.worldweatheronline.com/premium/v1/tz.ashx?&format=json";

        public static final String TIME_API_QUERY_PARAMETER = "key";

        public static final String TIME_LOCATION_QUERY_PARAMETER = "q";





}