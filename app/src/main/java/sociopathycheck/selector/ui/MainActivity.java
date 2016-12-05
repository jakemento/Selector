package sociopathycheck.selector.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import sociopathycheck.selector.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String savedLocation;
    @Bind(R.id.findWeatherButton) Button mFindWeatherButton;
    @Bind(R.id.locationEditText) EditText mLocationEditText;
    @Bind(R.id.weatherTextView) TextView mWeatherTextView;
    @Bind(R.id.recentListView) ListView mRecentListView;
    @Bind(R.id.testButton) Button mTestButton;

    ArrayList<String> recentCities = new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (recentCities.isEmpty()) {
            mRecentListView.setVisibility(View.INVISIBLE);
        } else {

            Intent intentFour = getIntent();
            savedLocation = intentFour.getStringExtra("location");
            recentCities.add(savedLocation);
            ArrayAdapter adapterTwo = new ArrayAdapter(this, android.R.layout.simple_list_item_1, recentCities);
            mRecentListView.setAdapter(adapterTwo);
            mRecentListView.setVisibility(View.VISIBLE);

        }

        Typeface quicksand = Typeface.createFromAsset(getAssets(), "fonts/Quicksand-Regular.otf");
        mWeatherTextView.setTypeface(quicksand);




        mFindWeatherButton.setOnClickListener(this);
        mTestButton.setOnClickListener(this);

        //Search API on recent CLICK
//        mRecentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String location = ((TextView)view).getText().toString();
//                Intent intentThree = new Intent(MainActivity.this, WeatherActivity.class);
//                intentThree.putExtra("location", location);
//                startActivity(intentThree);
//            }
//        });

     }

        @Override
                public void onClick(View v) {

            if(v == mFindWeatherButton) {
                String location = mLocationEditText.getText().toString();
//                recentCities.add(location);
                Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
                intent.putExtra("location", location);
                recentCities.add(location);
                startActivity(intent);
            }
            if(v == mTestButton) {
                Intent intentTwo = new Intent(MainActivity.this, WeatherActivity.class);
                startActivity(intentTwo);
            }

        }
    }
