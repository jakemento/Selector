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

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WeatherActivity extends AppCompatActivity {

    public ArrayList<Weather> mWeathers = new ArrayList<>();
    public ArrayList<Time> mTimes = new ArrayList<>();
    private String militaryTime;
    private String militaryTimeTwo;
    private List<String> militaryArray = new ArrayList<String>();
    private List<String> militaryArrayTwo = new ArrayList<String>();
    private String timer;
    private boolean isClicked = false;

    @Bind(R.id.locationTextView) TextView mLocationTextView;
    @Bind(R.id.listViewTwo) ListView mListViewTwo;
    @Bind(R.id.listView) ListView mListView;
    @Bind(R.id.backButton) Button mBackButton;
    public static final String TAG = WeatherActivity.class.getSimpleName();

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        final String location = intent.getStringExtra("location");
        getWeathers(location);
        getTimes(location);

        //sets up a typeface from the fonts folder & sets textview to it
        Typeface quicksand = Typeface.createFromAsset(getAssets(), "fonts/Quicksand-Regular.otf");
        mLocationTextView.setTypeface(quicksand);
        mLocationTextView.setText(location);


        //makes the image transparent
//        mCityImageView.setImageAlpha(99);
        //

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String city = ((TextView) view).getText().toString();
                Toast.makeText(WeatherActivity.this, city, Toast.LENGTH_SHORT).show();
                Log.v(TAG, "In the onItemClickListener!");
            }
        });

        mListViewTwo.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (isClicked == true) {
                changeTime();
                    isClicked = false;
            }
                else if (isClicked == false) {
                    changeTimeBack();
                    isClicked = true;
                }
            }
        });

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
                            adapter.add("conditions: " + weather.getDescription());
                            adapter.add("temp: " + weather.getTemp() + "˚");
                            adapter.add("humidity: " + weather.getHumidity());
                            adapter.add("wind speed " + weather.getWindSpeed() + " mph");

//                            Log.d(TAG, "Temp: " + weather.getTemp());
//                            Log.d(TAG, "City: " + weather.getName());
//                            Log.d(TAG, "description: " + weather.getDescription());
//                            Log.d(TAG, "humidity: " + weather.getHumidity() + "%");
//                            Log.d(TAG, "windspeed: " + weather.getWindSpeed());
                        }
                    }
                });
            }
        });
    }

    private void getTimes(String location) {
        final TimeService timeService = new TimeService();

        timeService.findTimes(location, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mTimes = timeService.processResults(response);
                WeatherActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        String[] times = new String[mTimes.size()];
                        for (int i = 0; i < times.length; i++) {
                            times[i] = mTimes.get(i).getTime();

                            times[i] = times[i].substring(times[i].lastIndexOf(" "));
                            militaryTimeTwo = times[i];


                            times[i] = times[i].replaceAll("[:]", "");

                            timer = times[i];
                            militaryTime = timer;

                            DateTimeFormatter inputFormatter = DateTimeFormat.forPattern(" HHmm");
                            DateTimeFormatter outputFormatter = DateTimeFormat.forPattern(" hh:mm a");
                            DateTime dateTime = inputFormatter.parseDateTime(timer);
                            String formattedTimer = outputFormatter.print(dateTime.getMillis());
                            times[i] = formattedTimer;
                        }

                        ArrayAdapter adapterTwo = new ArrayAdapter(WeatherActivity.this,
                                R.layout.list_item, R.id.item_text, times);
                        mListViewTwo.setAdapter(adapterTwo);
                    }
                });
            }
        });
    }
    private void changeTime() {

        militaryArray.add(militaryTime);

        ArrayAdapter adapterThree = new ArrayAdapter(WeatherActivity.this, R.layout.list_item, R.id.item_text, militaryArray);
        mListViewTwo.setAdapter(adapterThree);
        isClicked = true;
    }

    private void changeTimeBack() {

        DateTimeFormatter inputFormatter = DateTimeFormat.forPattern(" HHmm");
        DateTimeFormatter outputFormatter = DateTimeFormat.forPattern(" hh:mm a");
        DateTime dateTime = inputFormatter.parseDateTime(timer);
        militaryTimeTwo = outputFormatter.print(dateTime.getMillis());

        militaryArrayTwo.add(militaryTimeTwo);
        ArrayAdapter adapterFour = new ArrayAdapter(WeatherActivity.this, R.layout.list_item, R.id.item_text, militaryArrayTwo);
        mListViewTwo.setAdapter(adapterFour);
    }
}


