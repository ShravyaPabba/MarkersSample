package com.example.pabbashravya.markerssample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class LocationsAdapter extends RecyclerView.Adapter<LocationsAdapter.LocationViewHolder> {

    private List<Datum> locations;
    private Context context;

    public LocationsAdapter(List<Datum> locations, Context context){
        this.locations=locations;
        this.context=context;
    }


    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new LocationViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder locationViewHolder, int position) {
        Datum location=locations.get(position);
        Glide.with(context.getApplicationContext()).load(location.getImage())
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(locationViewHolder.image);

        locationViewHolder.name.setText(location.getTitle());
        locationViewHolder.location.setText(String.valueOf(location.getLatitude())+","+String.valueOf(location.getLongitude()));

    }

    @Override
    public int getItemCount() {
        if(locations==null)
            return 0;
        else
            return locations.size();
    }

    public class LocationViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView name;
        TextView location;

        public LocationViewHolder(@NonNull View itemView) {
            super(itemView);

            image=itemView.findViewById(R.id.image);
            name=itemView.findViewById(R.id.name);
            location=itemView.findViewById(R.id.location);
        }
    }
}
