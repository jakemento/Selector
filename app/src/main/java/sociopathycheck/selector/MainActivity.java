package sociopathycheck.selector;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String savedLocation;
    @Bind(R.id.findWeatherButton) Button mFindWeatherButton;
    @Bind(R.id.locationEditText) EditText mLocationEditText;
    @Bind(R.id.weatherTextView) TextView mWeatherTextView;
    @Bind(R.id.recentListView) ListView mRecentListView;
    @Bind(R.id.testButton) Button mTestButton;


    // how to set string array
    // private String[] recentCities = new String[] {};

    ArrayList<String> recentCities = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//

        //  FIX THIS RECENT LIST
//        Intent intentThree = getIntent();
//        savedLocation = intentThree.getStringExtra("location");
//        ArrayAdapter adapterTwo = new ArrayAdapter(this, android.R.layout.simple_list_item_1, recentCities);
//        mRecentListView.setAdapter(adapterTwo);

        if (recentCities != null) {
            recentCities.add(savedLocation);
        }

        Typeface quicksand = Typeface.createFromAsset(getAssets(), "fonts/Quicksand-Regular.otf");
        mWeatherTextView.setTypeface(quicksand);




        mFindWeatherButton.setOnClickListener(this);
        mTestButton.setOnClickListener(this);

        mRecentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String location = ((TextView)view).getText().toString();
                Intent intentThree = new Intent(MainActivity.this, WeatherActivity.class);
                intentThree.putExtra("location", location);
                startActivity(intentThree);
                //makes a toast to display on the list view item that is clicked
                // Toast.makeText(MainActivity.this, city, Toast.LENGTH_SHORT).show();
            }
        });

     }

        @Override
                public void onClick(View v) {

            if(v == mFindWeatherButton) {
                String location = mLocationEditText.getText().toString();
                Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
                intent.putExtra("location", location);
                startActivity(intent);
            }
            if(v == mTestButton) {
                Intent intentTwo = new Intent(MainActivity.this, WeatherActivity.class);
                startActivity(intentTwo);
            }

        }
    }
