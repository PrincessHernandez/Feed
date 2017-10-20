package travelers.feed.info_adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import travelers.feed.info_feed.Destinations;

import travelers.feed.R;

public class DestAdpater extends RecyclerView.Adapter<DestAdpater.DestHolder> {

    private ArrayList<Destinations> destData;
    private Activity destActivity;

    public DestAdpater(ArrayList<Destinations> data, Activity activity) {
        this.destData = data;
        this.destActivity = activity;
    }

    @Override
    public DestHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.places,parent,false);
        return new DestHolder(v);
    }

    @Override
    public void onBindViewHolder(DestHolder holder, int position) {
        Destinations dest = destData.get(position);
        holder.setDestinationName(dest.getName());
        holder.setDestinationAddress(dest.getAddress());
        holder.setExpense("Average cost: " + dest.getCurrency()+dest.getExpense());

        holder.setRating(dest.getRating());

        Glide.with(destActivity)
                .load(dest.getRating())
                .into(holder.imageviewDestination);
    }

    @Override
    public int getItemCount() {
        if (destData == null)
            return 0;
        return destData.size();
    }

    public class DestHolder extends RecyclerView.ViewHolder {

        ImageView imageviewDestination;
        TextView textviewDestinationName;
        TextView textviewDestinationAddress;
        TextView textviewRating;
        TextView textviewExpense;

        public DestHolder (View v) {
            super(v);

            imageviewDestination = (ImageView) itemView.findViewById(R.id.imageviewDestination);
            textviewDestinationName = (TextView) itemView.findViewById(R.id.textviewDestinationName);
            textviewDestinationAddress = (TextView) itemView.findViewById(R.id.textviewDestinationAddress);
            textviewRating = (TextView) itemView.findViewById(R.id.textviewRating);
            textviewExpense = (TextView) itemView.findViewById(R.id.textviewExpense);
        }

        public void setDestinationName(String DestinationName) {
            textviewDestinationName.setText(DestinationName);
        }

        public void setDestinationAddress(String DestinationAddress) {
            textviewDestinationAddress.setText(DestinationAddress);
        }
        public void setRating(String rating) {
            textviewRating.setText(rating);
        }

        public void setExpense(String Expense) {
            textviewExpense.setText(Expense);
        }
    }
}
