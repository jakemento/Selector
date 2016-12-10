package sociopathycheck.selector.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

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
            mDateTextViewSeven.setText(darksky.getDate());
            mSummaryTextViewSeven.setText(darksky.getSummary());
            mTempHighTextViewSeven.setText("High: "+ darksky.getTempHigh()+"˚");
            mTempLowTextViewSeven.setText("Low: " + darksky.getTempLow()+"˚");

            String summary = darksky.getSummary();
            if (summary.contains("rain")) {
                mWeatherImageViewSeven.setImageDrawable(this.mContext.getDrawable(R.drawable.rainy));
            } else if (summary.contains("sun")) {
                mWeatherImageViewSeven.setImageDrawable(this.mContext.getDrawable(R.drawable.sunny));
            } else if (summary.contains("snow")) {
                mWeatherImageViewSeven.setImageDrawable(this.mContext.getDrawable(R.drawable.snow));
            } else if (summary.contains("lightning")) {
                mWeatherImageViewSeven.setImageDrawable(this.mContext.getDrawable(R.drawable.lightning));
            } else if (summary.contains("partial")) {
                mWeatherImageViewSeven.setImageDrawable(this.mContext.getDrawable(R.drawable.partialsunny));
            } else if (summary.contains("cloud")) {
                mWeatherImageViewSeven.setImageDrawable(this.mContext.getDrawable(R.drawable.cloudy));
        }
    }
}
}