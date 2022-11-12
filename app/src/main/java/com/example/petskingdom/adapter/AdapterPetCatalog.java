package com.example.petskingdom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petskingdom.R;
import com.example.petskingdom.model.ModelPetCatalog;

import java.util.ArrayList;
import java.util.List;

public class AdapterPetCatalog extends RecyclerView.Adapter<AdapterPetCatalog.ViewHolderPetCatalog> {

    private List<ModelPetCatalog> items_list;
    private final OnGridItemSelectedListener onGridItemSelectedListener;

    public AdapterPetCatalog(OnGridItemSelectedListener onGridItemSelectedListener){
        this.onGridItemSelectedListener = onGridItemSelectedListener;
        items_list = new ArrayList<>();
    }

    private void add(ModelPetCatalog item){
        items_list.add(item);
        notifyItemInserted(items_list.size() - 1);
    }

    public void addAll(List<ModelPetCatalog> modelPetCatalogs){
        for (ModelPetCatalog modelPetCatalog : modelPetCatalogs){
            add(modelPetCatalog);
        }
    }

    public ModelPetCatalog getItem(int position){
        return items_list.get(position);
    }

    @NonNull
    @Override
    public AdapterPetCatalog.ViewHolderPetCatalog onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_gridview, parent, false);
        final ViewHolderPetCatalog viewHolderPetCatalog = new ViewHolderPetCatalog(view);
        viewHolderPetCatalog.itemView.setOnClickListener(v-> {
            int adapterPos = viewHolderPetCatalog.getAdapterPosition();
            if(adapterPos != RecyclerView.NO_POSITION){
                if(onGridItemSelectedListener != null){
                    onGridItemSelectedListener.onGridItemClick(viewHolderPetCatalog.itemView, adapterPos);
                }
            }
        });
        return viewHolderPetCatalog;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPetCatalog.ViewHolderPetCatalog holder, int position) {
        final ModelPetCatalog modelPetCatalog = items_list.get(position);
        holder.imageView.setImageResource(modelPetCatalog.getImageId());
        holder.title.setText(modelPetCatalog.getTitle());
        holder.description.setText(modelPetCatalog.getDescription());
        holder.category.setText(modelPetCatalog.getCategory());
        holder.salesCount.setText(modelPetCatalog.getSalesCount());
        holder.favoriteOutlined.setImageResource(modelPetCatalog.getFavoriteOutlined());
    }

    @Override
    public int getItemCount() {
        return items_list.size();
    }

    public static class ViewHolderPetCatalog extends RecyclerView.ViewHolder{
        public ImageView imageView, favoriteOutlined;
        public TextView title, description, category, salesCount;

        public ViewHolderPetCatalog(View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            category = itemView.findViewById(R.id.categoryProduct);
            salesCount = itemView.findViewById(R.id.salesCount);
            favoriteOutlined = itemView.findViewById(R.id.favoriteButton);
        }
    }

    public interface OnGridItemSelectedListener{
        void onGridItemClick(View v, int position);
    }
}
