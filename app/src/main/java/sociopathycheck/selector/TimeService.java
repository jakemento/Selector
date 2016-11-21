package sociopathycheck.selector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by JS on 11/20/16.
 */

public class TimeService {
    public static void findTimes(String city, Callback callback) {
        String TIME_API_KEY = Constants.TIME_API_KEY;


        OkHttpClient client = new OkHttpClient.Builder()
                .build();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.TIME_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.TIME_API_QUERY_PARAMETER, TIME_API_KEY);
        urlBuilder.addQueryParameter(Constants.TIME_LOCATION_QUERY_PARAMETER, city);

        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);

    }

    public ArrayList<Time> processResults(Response response) {
        ArrayList<Time> times = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {

                JSONObject timeJSON = new JSONObject(jsonData);
                JSONArray timesJSON = timeJSON.getJSONArray("data");

                for (int i = 0; i < timesJSON.length(); i++) {
                    JSONObject nightJSON = timesJSON.getJSONObject(i);
                    String localTime = nightJSON.getJSONObject("time_zone").getString("localtime");
                    Time time = new Time(localTime);
                    times.add(time);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return times;
    }


}

