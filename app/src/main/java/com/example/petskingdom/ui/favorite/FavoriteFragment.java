package com.example.petskingdom.ui.favorite;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petskingdom.DetailProduct;
import com.example.petskingdom.R;
import com.example.petskingdom.adapter.AdapterPetCatalog;
import com.example.petskingdom.databinding.FragmentFavoriteBinding;
import com.example.petskingdom.model.ModelPetCatalog;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment implements AdapterPetCatalog.OnGridItemSelectedListener {
    private RecyclerView recyclerView;
    private AdapterPetCatalog adapterPetCatalog;
    private Context context;
    private FragmentFavoriteBinding binding;

    int[] imageId = {
            R.drawable.pedigree,
            R.drawable.whiskas,
    };
    String[] title = {
            "Pedigree Dry Dog Food Adult Grilled Steak & Vegetable Flavor",
            "Whiskas Dry Food Skin & Coat",
    };
    String[] price = {
            "Rp. 350.000",
            "Rp. 150.000",
    };
    String[] category = {
            "Dog Food",
            "Cat Food",
    };

    String[] size = {
            "5.67 kg",
            "4 kg",
    };

    String[] description = {
            "Every dog deserves the best… that’s why PEDIGREE Complete Nutrition Dry Dog Food delivers 100% complete and balanced nutrition for your adult dog. It has the antioxidants, vitamins, and minerals canines need to help maintain a healthy lifestyle, and in the delicious, meaty steak flavor they love.",
            "Bulu dan kulit yang indah adalah ciri khas kucing yang dirawat dengan baik dan sehat. Banyak faktor yang bisa menyebabkan kesehatan kulit & bulu yang jelek, seperti nutrisi yang buruk, berat badan yang berlebihan (yang mempersulit kucing Anda merawat tubuh mereka sendiri), usia tua dan bahkan terlalu sering dimandikan (yang bisa menghilangkan minyak alami dan menyebabkan iritasi kulit).",
    };

    String[] nutrition = {
            "Vitamin E : 100 IU/kg, Crude Protein : 21.0%, Crude Fat (min.) : 10.0%, Crude Fiber : 4.0%, Phosphorus : 0.8%, Calcium : 1.0%, Moisture : 12.0",
            "Vitamin E : 100 IU/kg, Crude Protein : 36.50%, Crude Fat (min.) : 14.0%, Crude Fiber : 4.0%, Phosphorus : 0.8%, Calcium : 1.0%, Moisture : 10.0",
    };

    String[] composition = {
            "Ground Whole Grain Corn, Meat and Bone Meal, Soybean Meal, Animal Fat (Source of Omega 6 Fatty Acids [Preserved with BHA & Citric Acid]), Corn Gluten Meal, Natural Flavor, Dried Plain Beet Pulp, Chicken Byproduct Meal, Salt, Ground Whole Grain Wheat, Brewers Rice, Potassium Chloride, Calcium Carbonate, Choline Chloride, Dried Peas, DL-Methionine, Natural Grilled Steak Flavor, Zinc Sulfate, Yellow 6, Monocalcium Phosphate, Vitamin E Supplement, L-Tryptophan, Yellow 5, Red 40, Blue 2, Dried Carrots, Niacin Supplement (Vitamin B3), Copper Sulfate, Sodium Selenite, Potassium Iodide, D-Calcium Pantothenate (Source of Vitamin B5), Vitamin A Supplement, Riboflavin Supplement (Vitamin B2), Vitamin B12 Supplement, Thiamine Mononitrate (Vitamin B1), Vitamin D3 Supplement, Pyridoxine Hydrochloride (Vitamin B6), Folic Acid.",
            "Grain sereal (jagung, padi), gluten tepung jagung, unggas dan unggas oleh produk, ikan laut, bungkil kedelai, stearin sawit, minyak kedelai, owder susu, garam beryodium, taurin, vitamin (A, E, B1, B6, B2 , B12, Choline, Niacin, Asam Folat), mineral (kalsium, fosfor, seng, besi, tembaga, selenium, mangan)",
    };

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

        for(int i=0; i < imageId.length; i++){
            modelPetCatalog = new ModelPetCatalog();
            modelPetCatalog.setImageId(imageId[i]);
            modelPetCatalog.setTitle(title[i]);
            modelPetCatalog.setDescription(price[i]);
            modelPetCatalog.setCategory(category[i]);
            modelPetCatalog.setFavoriteOutlined(R.drawable.ic_favorite);
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
        Intent intentDetail = new Intent(getActivity(), DetailProduct.class);
        intentDetail.putExtra("productImage", imageId[position]);
        intentDetail.putExtra("productName", title[position]);
        intentDetail.putExtra("productPrice", price[position]);
        intentDetail.putExtra("productSize", size[position]);
        intentDetail.putExtra("productDescription", description[position]);
        intentDetail.putExtra("productNutrition", nutrition[position]);
        intentDetail.putExtra("productComposition", composition[position]);
        startActivity(intentDetail);
    }
}