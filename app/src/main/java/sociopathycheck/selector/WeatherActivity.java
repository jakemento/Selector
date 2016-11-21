package sociopathycheck.selector;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class WeatherActivity extends AppCompatActivity {

    public ArrayList<Weather> mWeathers = new ArrayList<>();

    @Bind(R.id.locationTextView) TextView mLocationTextView;

    @Bind(R.id.listView) ListView mListView;
    @Bind(R.id.backButton) Button mBackButton;
    @Bind(R.id.cityImageView) ImageView mCityImageView;

    public static final String TAG = WeatherActivity.class.getSimpleName();

    //private String[] cities = new String[] {"Arcata", "Portland", "Eugene", "St. Petersburg", "Kiev", "Aleppo", "Cairo", "Minsk", "Pinsk", "Toonville", "Rio", "Berlin", "Bejing", "Moscow", "New York", "Denver", "Paris", "Rome", "Bangkok"};


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        final String location = intent.getStringExtra("location");
        getWeathers(location);

       // old way to set adapter?
        // ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, cities);
       // mListView.setAdapter(adapter);
        //

        Typeface quicksand = Typeface.createFromAsset(getAssets(), "fonts/Quicksand-Regular.otf");
        mLocationTextView.setTypeface(quicksand);


        mLocationTextView.setText(location);

        //how to log something
        Log.d(TAG, "In the onCreate method!");
        //

        //makes the image transparent
        mCityImageView.setImageAlpha(99);
        //

        //sets on click listener toast for listview
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String city = ((TextView)view).getText().toString();
                Toast.makeText(WeatherActivity.this, city, Toast.LENGTH_SHORT).show();
                Log.v(TAG, "In the onItemClickListener!");
            }
        });
        //
        //tells the back button to go back and send intent with it
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentTwo = new Intent(WeatherActivity.this, MainActivity.class);

                //added the value of savedLocation string
                intentTwo.putExtra("location", location);
                startActivity(intentTwo);
            }


        });
    }
    private void getWeathers(String location) {
        final WeatherService weatherService = new WeatherService();

        weatherService.findWeathers(location, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mWeathers = weatherService.processResults(response);
                WeatherActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ArrayAdapter adapter = new ArrayAdapter(WeatherActivity.this,
                                android.R.layout.simple_list_item_1);
                        mListView.setAdapter(adapter);

                        for (Weather weather : mWeathers) {
                            adapter.add("city: " + weather.getName());
                            adapter.add("description: " + weather.getDescription());
                            adapter.add("temperature: " + weather.getTemp() + "˚");

                            adapter.add("wind speed " + weather.getWindSpeed() + " mph");


                            Log.d(TAG, "Temp: " + weather.getTemp());
                            Log.d(TAG, "City: " + weather.getName());
                            Log.d(TAG, "description: " + weather.getDescription());
                            Log.d(TAG, "humidity: " + weather.getHumidity() + "%");
                            Log.d(TAG, "windspeed: " + weather.getWindSpeed());
                        }
                    }
                });
            }
        });
    }
}