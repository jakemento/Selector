package sociopathycheck.selector.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import sociopathycheck.selector.R;
import sociopathycheck.selector.models.Place;

/**
 * Created by JS on 11/25/16.
 */

public class PlaceListAdapter extends RecyclerView.Adapter<PlaceListAdapter.PlaceViewHolder> {
    private ArrayList<Place> mPlaces = new ArrayList<>();
    private Context mContext;

    public PlaceListAdapter(Context context, ArrayList<Place> places) {
        mContext = context;
        mPlaces = places;
    }

    @Override
    public PlaceListAdapter.PlaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_list_item, parent, false);
        PlaceViewHolder viewHolder = new PlaceViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PlaceListAdapter.PlaceViewHolder holder, int position) {
        holder.bindPlace(mPlaces.get(position));
    }

    @Override
    public int getItemCount() {
        return mPlaces.size();
    }

    public class PlaceViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.placeImageView) ImageView mPlaceImageView;
//        @Bind(R.id.locationName) TextView mLocationName;
//        @Bind(R.id.distanceTextView) TextView mDistanceTextView;
//        @Bind(R.id.cityTextView) TextView mcityTextView;

        private Context mContext;

        public PlaceViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindPlace(Place place) {

//            mNameTextView.setText(restaurant.getName());
//            mCategoryTextView.setText(restaurant.getCategories().get(0));
//            mRatingTextView.setText("Rating: " + restaurant.getRating() + "/5");
        }
    }
}
