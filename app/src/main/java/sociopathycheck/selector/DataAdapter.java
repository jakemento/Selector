package sociopathycheck.selector;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import sociopathycheck.selector.models.Photo;

/**
 * Created by JS on 12/1/16.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private ArrayList<Photo> photo;
    private Context context;

    public DataAdapter(Context context,ArrayList<Photo> photo) {
        this.context = context;
        this.photo = photo;

    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        Picasso.with(context).load(photo.get(i).getPhoto_url()).resize(120, 60).into(viewHolder.img_android);
    }

    @Override
    public int getItemCount() {
        return photo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView img_android;
        public ViewHolder(View view) {
            super(view);
            img_android = (ImageView)view.findViewById(R.id.img_android);
        }
    }
}
