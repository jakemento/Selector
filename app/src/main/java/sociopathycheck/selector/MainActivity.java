package sociopathycheck.selector;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String savedLocation;

    @Bind(R.id.findWeatherButton) Button mFindWeatherButton;
    @Bind(R.id.locationEditText) EditText mLocationEditText;
    @Bind(R.id.weatherTextView) TextView mWeatherTextView;
    @Bind(R.id.recentTextView) TextView mRecentTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface quicksand = Typeface.createFromAsset(getAssets(), "fonts/Quicksand-Regular.otf");
        mWeatherTextView.setTypeface(quicksand);

        Intent intentThree = getIntent();
        savedLocation = intentThree.getStringExtra("savedLocation");

        mRecentTextView.setText(savedLocation);

     mFindWeatherButton.setOnClickListener(this);
     }

        @Override
                public void onClick(View v) {

            if(v == mFindWeatherButton) {
                String location = mLocationEditText.getText().toString();
                Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
                intent.putExtra("location", location);
                startActivity(intent);
            }

        }

    }
