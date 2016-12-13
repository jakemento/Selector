package sociopathycheck.selector.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import butterknife.Bind;
import butterknife.ButterKnife;
import sociopathycheck.selector.R;
import sociopathycheck.selector.models.DarkSky;

/**
 * Created by JS on 12/8/16.
 */

public class SevenListAdapter extends RecyclerView.Adapter<SevenListAdapter.SevenViewHolder> {
    private ArrayList<DarkSky> mDarkSkies = new ArrayList<>();
    private Context mContext;
    private String numberDate;
    private Date newDate;
    private int dayOfWeek;
    private Date myDate;

    public SevenListAdapter(Context context, ArrayList<DarkSky> darkskies) {
        mContext = context;
        mDarkSkies = darkskies;
    }

    @Override
    public SevenListAdapter.SevenViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.seven_list_item, parent, false);
        SevenViewHolder viewHolder = new SevenViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SevenListAdapter.SevenViewHolder holder, int position) {
        holder.bindSeven(mDarkSkies.get(position));
    }

    @Override
    public int getItemCount() {
        return mDarkSkies.size();
    }
    public class SevenViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.dateTextViewSeven) TextView mDateTextViewSeven;
        @Bind(R.id.tempHighTextViewSeven) TextView mTempHighTextViewSeven;
        @Bind(R.id.tempLowTextViewSeven) TextView mTempLowTextViewSeven;
        @Bind(R.id.summaryTextViewSeven) TextView mSummaryTextViewSeven;
        @Bind(R.id. weatherImageViewSeven) ImageView mWeatherImageViewSeven;

        private Context mContext;

        public SevenViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindSeven(DarkSky darksky) {

            numberDate = darksky.getDate();
            long longDate = Integer.parseInt(numberDate);
            Date date = new Date(longDate*1000L);
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
            sdf.setTimeZone(TimeZone.getTimeZone("GMT-4"));
                String formattedDate = sdf.format(date);
            String newFormattedDate = formattedDate.replaceAll("-", "/");

            DateTimeFormatter formatter = DateTimeFormat.forPattern( "MM/dd/yyyy" );
            LocalDate localDate = formatter.parseLocalDate( newFormattedDate );
            int dayOfWeek = localDate.getDayOfWeek();
            if (dayOfWeek == 1) {  mDateTextViewSeven.setText("Monday"); }
            else if (dayOfWeek == 2) {  mDateTextViewSeven.setText("Tuesday"); }
            else if (dayOfWeek == 3) {  mDateTextViewSeven.setText("Wednesday"); }
            else if (dayOfWeek == 4) {  mDateTextViewSeven.setText("Thursday"); }
            else if (dayOfWeek == 5) {  mDateTextViewSeven.setText("Friday"); }
            else if (dayOfWeek == 6) {  mDateTextViewSeven.setText("Saturday"); }
            else if (dayOfWeek == 7) {  mDateTextViewSeven.setText("Sunday"); }

            mSummaryTextViewSeven.setText(darksky.getSummary());
            mTempHighTextViewSeven.setText("High: "+ darksky.getTempHigh()+"˚");
            mTempLowTextViewSeven.setText("Low: " + darksky.getTempLow()+"˚");

            String summary = darksky.getSummary();
            if (summary.contains("rain") ||(summary.contains("precipitation") || (summary.contains("Drizzle")) || (summary.contains("Rain"))|| (summary.contains("showers")))) {
                mWeatherImageViewSeven.setImageDrawable(this.mContext.getDrawable(R.drawable.rainy));
            } else if (summary.contains("sun")|| (summary.contains("Sun"))) {
                mWeatherImageViewSeven.setImageDrawable(this.mContext.getDrawable(R.drawable.sunny));
            } else if (summary.contains("Snow")|| (summary.contains("snow")) || (summary.contains("Flurries"))) {
                mWeatherImageViewSeven.setImageDrawable(this.mContext.getDrawable(R.drawable.snow));
            } else if (summary.contains("Lightning")|| (summary.contains("lightning"))) {
                mWeatherImageViewSeven.setImageDrawable(this.mContext.getDrawable(R.drawable.lightning));
            } else if (summary.contains("partial")) {
                mWeatherImageViewSeven.setImageDrawable(this.mContext.getDrawable(R.drawable.partialsunny));
            } else if (summary.contains("Cloud")|| (summary.contains("cloud"))) {
                mWeatherImageViewSeven.setImageDrawable(this.mContext.getDrawable(R.drawable.cloudy));
        }
    }
}
}