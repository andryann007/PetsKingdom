package com.example.petskingdom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AdapterGridView extends ArrayAdapter<ModelGridView> {

    List<ModelGridView> items_list;
    int layout_grid_view;

    public AdapterGridView(@NonNull Context context, int resource, @NonNull List<ModelGridView> objects) {
        super(context, resource, objects);
        items_list = objects;
        layout_grid_view = resource;
    }

    @Override public int getCount(){
        return items_list.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent){
        View v = view;

        if(v == null){
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(layout_grid_view, null);
        }

        ImageView imageView = v.findViewById(R.id.imageView);
        TextView title = v.findViewById(R.id.title);
        TextView description = v.findViewById(R.id.description);

        ModelGridView item = items_list.get(position);

        imageView.setImageResource(item.getImageId());
        title.setText(item.getTitle());
        description.setText(item.getDescription());
        return v;
    }
}
