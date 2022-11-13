package com.example.petskingdom.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petskingdom.DetailProduct;
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

    int[] imageId = {
            R.drawable.pedigree,
            R.drawable.pedigree_2,
            R.drawable.pedigree_3,
            R.drawable.whiskas,
            R.drawable.whiskas_2,
            R.drawable.whiskas_3,
    };
    String[] title = {
            "Pedigree Dry Dog Food Adult Grilled Steak & Vegetable Flavor",
            "Pedigree Adult Marrobites Steak and Vegetable",
            "Pedigree With Tender Bites Complete Nutrition Adult Dry Dog Food Chicken & Steak Flavor Dog Kibble",
            "Whiskas Dry Adult Skin & Coat",
            "Whiskas Dry Adult Hairball Control",
            "Whiskas Dry Adult Indoor",
    };
    String[] price = {
            "Rp. 350.000",
            "Rp. 375.000",
            "Rp. 360.000",
            "Rp. 150.000",
            "Rp. 170.000",
            "Rp. 175.000",
    };

    String[] category = {
            "Dog Food",
            "Dog Food",
            "Dog Food",
            "Cat Food",
            "Cat Food",
            "Cat Food",
    };

    String[] size = {
            "5.67 kg",
            "5.44 kg",
            "5.44 kg",
            "4 kg",
            "4 kg",
            "4 kg",
    };

    String[] description = {
            "Every dog deserves the best… that’s why PEDIGREE Complete Nutrition Dry Dog Food delivers 100% complete and balanced nutrition for your adult dog. It has the antioxidants, vitamins, and minerals canines need to help maintain a healthy lifestyle, and in the delicious, meaty steak flavor they love.",
            "Every dog deserves the best… that’s why PEDIGREE Complete Nutrition Dry Dog Food delivers 100% complete and balanced nutrition for your adult dog. It has the antioxidants, vitamins, and minerals canines need to help maintain a healthy lifestyle, and in the delicious, meaty steak flavor they love.",
            "Every dog deserves the best… that’s why PEDIGREE Complete Nutrition Dry Dog Food delivers 100% complete and balanced nutrition for your adult dog. It has the antioxidants, vitamins, and minerals canines need to help maintain a healthy lifestyle, and in the delicious, meaty steak flavor they love.",
            "Bulu dan kulit yang indah adalah ciri khas kucing yang dirawat dengan baik dan sehat. Banyak faktor yang bisa menyebabkan kesehatan kulit & bulu yang jelek, seperti nutrisi yang buruk, berat badan yang berlebihan (yang mempersulit kucing Anda merawat tubuh mereka sendiri), usia tua dan bahkan terlalu sering dimandikan (yang bisa menghilangkan minyak alami dan menyebabkan iritasi kulit).",
            "Kucing menghabiskan setidaknya lima jam sehari untuk merawat dirinya. Lidah mereka yang kasar seperti amplas mudah menjaring rambut yang terlepas, yang kemudiannya tertelan. Meskipun bulu tersebut biasanya bisa melewati usus dan dikeluarkan melalui feses, beberapa kucing (terutama kucing berambut panjang) cenderung membentuk hairball di perutnya. Memang kucing dapat memuntahkan hairball (sesuatu yang sudah cukup tidak menyenangkan), namun hairball yang menumpuk di perut dapat menjadi masalah serius jika tersendat pada saluran pencernaan.",
            "Sebagai pencipta makanan kucing WHISKAS®, kami tahu bahwa kucing rumahan memiliki kebutuhan diet khusus yang perlu disesuaikan dengan gaya hidup mereka yang santai. Kucing rumahan cenderung menghabiskan waktu mereka dengan tidur dan bersantai di tempat favorit mereka seharian, mereka tidak terlalu aktif dan membutuhkan hanya sedikit energi. Karena itu, kucing rumahan lebih rentan terhadap kenaikan berat badan. Juga, dengan kurangnya bergerak, pergerakan usus merekapun menjadi lamban, ini menyebabkan feses mereka menjadi sangat bau dan mengganggu kenyamanan pemilik kucing.",
    };

    String[] nutrition = {
            "Vitamin E : 100 IU/kg, Crude Protein : 21.0%, Crude Fat (min.) : 10.0%, Crude Fiber : 4.0%, Phosphorus : 0.8%, Calcium : 1.0%, Moisture : 12.0",
            "Vitamin E : 80 IU/kg, Zinc (Zinc sulphate, monohydrate) : 80 mg/kg, Crude Protein : 21.0%, Crude Fat (min.) : 10.0%, Crude Fiber : 4.0%, Phosphorus : 0.8%, Calcium : 1.0%, Moisture : 12.0%",
            "Vitamin E : 100 IU/kg, Crude Protein : 21.0%, Crude Fat (min.) : 10.0%, Crude Fiber : 4.0%, Phosphorus : 0.8%, Calcium : 1.0%, Moisture : 12.0",
            "Vitamin E : 100 IU/kg, Crude Protein : 36.50%, Crude Fat (min.) : 14.0%, Crude Fiber : 4.0%, Phosphorus : 0.8%, Calcium : 1.0%, Moisture : 10.0",
            "Vitamin E : 75 IU/kg, Crude Protein : 36.50%, Crude Fat (min.) : 14.0%, Crude Fiber : 4.0%, Phosphorus : 0.8%, Calcium : 1.0%, Moisture : 10.0",
            "Vitamin E : 100 IU/kg, Crude Protein : 36.50%, Crude Fat (min.) : 14.0%, Crude Fiber : 4.0%, Phosphorus : 0.8%, Calcium : 1.0%, Moisture : 10.0",
    };

    String[] composition = {
            "Ground Whole Grain Corn, Meat and Bone Meal, Soybean Meal, Animal Fat (Source of Omega 6 Fatty Acids [Preserved with BHA & Citric Acid]), Corn Gluten Meal, Natural Flavor, Dried Plain Beet Pulp, Chicken Byproduct Meal, Salt, Ground Whole Grain Wheat, Brewers Rice, Potassium Chloride, Calcium Carbonate, Choline Chloride, Dried Peas, DL-Methionine, Natural Grilled Steak Flavor, Zinc Sulfate, Yellow 6, Monocalcium Phosphate, Vitamin E Supplement, L-Tryptophan, Yellow 5, Red 40, Blue 2, Dried Carrots, Niacin Supplement (Vitamin B3), Copper Sulfate, Sodium Selenite, Potassium Iodide, D-Calcium Pantothenate (Source of Vitamin B5), Vitamin A Supplement, Riboflavin Supplement (Vitamin B2), Vitamin B12 Supplement, Thiamine Mononitrate (Vitamin B1), Vitamin D3 Supplement, Pyridoxine Hydrochloride (Vitamin B6), Folic Acid.",
            "Ground Whole Grain Corn, Meat And Bone Meal, Corn Gluten Meal, Soybean Meal, Animal Fat (Source Of Omega 6 Fatty Acids) [Preserved With BHA & Citric Acid], Natural Flavor, Dried Plain Beet Pulp, Salt, Cooked Bone Marrow, Potassium Chloride, Choline Chloride, Calcium Carbonate, Dried Peas, DL-Methionine, Natural Grilled Steak Flavor, Yellow 6, Zinc Sulfate, Vitamin E Supplement, Yellow 5, Red 40, L-Tryptophan, Dried Carrots, Blue 2, Niacin [Vitamin B3], D-Calcium Pantothenate [Source Of Vitamin B5], Copper Sulfate, Sodium Selenite, Potassium Iodide, Vitamin A Supplement, Riboflavin Supplement [Vitamin B2], Vitamin B12 Supplement, Thiamine Mononitrate [Vitamin B1], Vitamin D3 Supplement, Pyridoxine Hydrochloride [Vitamin B6], Folic Acid",
            "Ground Whole Grain Corn, Meat and Bone Meal, Soybean Meal, Animal Fat (Source of Omega 6 Fatty Acids [Preserved with BHA & Citric Acid]), Corn Gluten Meal, Natural Flavor, Dried Plain Beet Pulp, Chicken Byproduct Meal, Chicken Meal, Glycerin, Salt, Sugar, Ground Whole Grain Wheat, Potassium Chloride, Choline Chloride, Calcium Carbonate, Phosphoric Acid, Dried Peas, DL-Methionine, Potassium Sorbate (preservative), Natural Grilled Steak Flavor, Zinc Sulfate, Yellow 6, Vitamin E Supplement, L-Tryptophan, Red 40, Yellow 5, Blue 2, Dried Carrots, Niacin Supplement (Vitamin B3), Copper Sulfate, Sodium Selenite, Potassium Iodide, D-Calcium Pantothenate (Source of Vitamin B5), Vitamin A Supplement, Riboflavin Supplement (Vitamin B2), Vitamin B12 Supplement, Thiamine Mononitrate (Vitamin B1), Vitamin D3 Supplement, Pyridoxine Hydrochloride (Vitamin B6), Folic Acid.",
            "Grain sereal (jagung, padi), gluten tepung jagung, unggas dan unggas oleh produk, ikan laut, bungkil kedelai, stearin sawit, minyak kedelai, owder susu, garam beryodium, taurin, vitamin (A, E, B1, B6, B2 , B12, Choline, Niacin, Asam Folat), mineral (kalsium, fosfor, seng, besi, tembaga, selenium, mangan)",
            "Grain sereal (jagung, padi), gluten tepung jagung, unggas dan unggas oleh produk, ikan laut, bungkil kedelai, stearin sawit, minyak kedelai, owder susu, garam beryodium, taurin, vitamin (A, E, B1, B6, B2 , B12, Choline, Niacin, Asam Folat), mineral (kalsium, fosfor, seng, besi, tembaga, selenium, mangan)",
            "Grain sereal (jagung, padi), gluten tepung jagung, unggas dan unggas oleh produk, ikan laut, bungkil kedelai, stearin sawit, minyak kedelai, owder susu, garam beryodium, taurin, vitamin (A, E, B1, B6, B2 , B12, Choline, Niacin, Asam Folat), mineral (kalsium, fosfor, seng, besi, tembaga, selenium, mangan)",
    };

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
        recyclerView.setClickable(true);
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
            modelPetCatalog.setFavoriteOutlined(R.drawable.ic_favorite_border);
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