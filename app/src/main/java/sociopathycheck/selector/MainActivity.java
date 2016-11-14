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
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

//TO TAG IT IN THE MONITOR:
// public static final String TAG = MainActivity.class.getSimpleName();


private Button mFindWeatherButton;
    private EditText mLocationEditText;
    private TextView mWeatherTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     mLocationEditText = (EditText) findViewById(R.id.locationEditText);
     mFindWeatherButton = (Button) findViewById(R.id.findWeatherButton);

        mWeatherTextView = (TextView)findViewById(R.id.weatherTextView);
        Typeface quicksand = Typeface.createFromAsset(getAssets(), "fonts/Quicksand-Regular.otf");
        mWeatherTextView.setTypeface(quicksand);



     mFindWeatherButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {

             String location = mLocationEditText.getText().toString();

             //TO LOG IT IN THE MONITOR
             // Log.d(TAG, location);

             Intent intent = new Intent(MainActivity.this, CitiesActivity.class);
             intent.putExtra("location", location);
             startActivity(intent);

         }
     });


    }
}
