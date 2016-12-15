package sociopathycheck.selector.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import sociopathycheck.selector.Constants;
import sociopathycheck.selector.R;
import sociopathycheck.selector.models.FourSquare;


public class FourSquareListAdapter extends RecyclerView.Adapter<FourSquareListAdapter.FourSquareViewHolder> {
    private ArrayList<FourSquare> mFourSquares = new ArrayList<>();
    private Context mContext;


    public FourSquareListAdapter(Context context, ArrayList<FourSquare> fourSquares) {
        mContext = context;
        mFourSquares = fourSquares;
    }

    @Override
    public FourSquareListAdapter.FourSquareViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.venue_list_item, parent, false);
        FourSquareViewHolder viewHolder = new FourSquareViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FourSquareListAdapter.FourSquareViewHolder holder, int position) {
        holder.bindVenue(mFourSquares.get(position));
    }

    @Override
    public int getItemCount() {
        return mFourSquares.size();
    }

    public class FourSquareViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.venueNameTextView) TextView mVenueNameTextView;
        @Bind(R.id.venueAddressTextView) TextView mVenueAddressTextView;


        private Context mContext;
        private String venueId;

        public FourSquareViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindVenue(FourSquare foursquare) {
            mVenueNameTextView.setText(foursquare.getVenueName());
            String venueAddressUnformatted = foursquare.getVenueAddress();
            String formattedVenueAddress = venueAddressUnformatted.replace("]", "").replace("[", "").replaceAll("\\(.*\\)", "").replaceAll("\"","");
            mVenueAddressTextView.setText(formattedVenueAddress);

            // VENUE ID URL FOR NEW API CALL //
//            venueId = foursquare.getVenueId();
//            String url = "https://api.foursquare.com/v2/venues/" + venueId + "/photos?&client_id=" + Constants.FOURSQUARE_CLIENT_ID + "&" + "client_secret=" + Constants.FOURSQUARE_CLIENT_SECRET + "&v=20161214%20&m=foursquare";
//            mVenueIdTextView.setText(url);
//            Picasso.with(mVenueImageView.getContext()).load(url).into(mVenueImageView);

        }

    }
}