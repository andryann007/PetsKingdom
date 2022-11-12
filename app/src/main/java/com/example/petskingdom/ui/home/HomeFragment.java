package com.example.petskingdom.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petskingdom.R;
import com.example.petskingdom.adapter.AdapterPetCatalog;
import com.example.petskingdom.databinding.FragmentHomeBinding;
import com.example.petskingdom.model.ModelPetCatalog;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements AdapterPetCatalog.OnGridItemSelectedListener {
    private RecyclerView recyclerView;
    private AdapterPetCatalog adapterPetCatalog;
    private Context context;
    private FragmentHomeBinding binding;

    public static Fragment newInstance() { return new HomeFragment(); }

    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
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
                R.drawable.pedigree_2,
                R.drawable.pedigree_3,
                R.drawable.whiskas,
                R.drawable.whiskas_2,
                R.drawable.whiskas_3
        };
        String[] title = {
                "Pedigree Adult",
                "Pedigree Marrobites",
                "Pedigree Tenderbites",
                "Whiskas Skin & Coat",
                "Whiskas Hairball Control",
                "Whiskas Indoor"
        };
        String[] price = {
                "Rp. 350.000",
                "Rp. 375.000",
                "Rp. 360.000",
                "Rp. 150.000",
                "Rp. 170.000",
                "Rp. 175.000"
        };

        String[] category = {
                "Dog Food",
                "Dog Food",
                "Dog Food",
                "Cat Food",
                "Cat Food",
                "Cat Food"
        };

        String[] salesCount = {
                "230 pcs sold",
                "120 pcs sold",
                "175 pcs sold",
                "145 pcs sold",
                "120 pcs sold",
                "90 pcs sold"
        };

        int[] favoriteOutlined = {
                R.drawable.ic_favorite_border,
                R.drawable.ic_favorite_border,
                R.drawable.ic_favorite_border,
                R.drawable.ic_favorite_border,
                R.drawable.ic_favorite_border,
                R.drawable.ic_favorite_border
        };

        for(int i=0; i < imageId.length; i++){
            modelPetCatalog = new ModelPetCatalog();
            modelPetCatalog.setImageId(imageId[i]);
            modelPetCatalog.setTitle(title[i]);
            modelPetCatalog.setDescription(price[i]);
            modelPetCatalog.setCategory(category[i]);
            modelPetCatalog.setSalesCount(salesCount[i]);
            modelPetCatalog.setFavoriteOutlined(favoriteOutlined[i]);
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