package sociopathycheck.selector;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
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

     mFindWeatherButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {

             String location = mLocationEditText.getText().toString();

             Intent intent = new Intent(MainActivity.this, CitiesActivity.class);
             intent.putExtra("location", location);
             startActivity(intent);

         }
     });


    }
}
