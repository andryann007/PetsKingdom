package com.example.petskingdom.ui.favorite;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petskingdom.R;
import com.example.petskingdom.adapter.AdapterPetCatalog;
import com.example.petskingdom.databinding.FragmentFavoriteBinding;
import com.example.petskingdom.databinding.FragmentHomeBinding;
import com.example.petskingdom.model.ModelPetCatalog;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment implements AdapterPetCatalog.OnGridItemSelectedListener {
    private RecyclerView recyclerView;
    private AdapterPetCatalog adapterPetCatalog;
    private Context context;
    private FragmentFavoriteBinding binding;

    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recyclerView = root.findViewById(R.id.recyclerView);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        adapterPetCatalog = new AdapterPetCatalog(this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapterPetCatalog);
        loadData();
    }

    private void loadData(){
        List<ModelPetCatalog> list = new ArrayList<>();
        ModelPetCatalog modelPetCatalog;
        int[] imageId = {
                R.drawable.pedigree,
                R.drawable.whiskas
        };
        String[] title = {
                "Pedigree Adult",
                "Whiskas Skin & Coat"
        };
        String[] price = {
                "Rp. 350.000",
                "Rp. 150.000"
        };

        for(int i=0; i < imageId.length; i++){
            modelPetCatalog = new ModelPetCatalog();
            modelPetCatalog.setImageId(imageId[i]);
            modelPetCatalog.setTitle(title[i]);
            modelPetCatalog.setDescription(price[i]);
            list.add(modelPetCatalog);
        }
        adapterPetCatalog.addAll(list);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onGridItemClick(View v, int position) {
        Toast.makeText(context, adapterPetCatalog.getItem(position).getTitle(), Toast.LENGTH_SHORT).show();
    }
}