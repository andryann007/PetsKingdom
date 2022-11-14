package com.example.petskingdom.ui.account;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.petskingdom.databinding.FragmentAccountBinding;

public class AccountFragment extends Fragment {

    private FragmentAccountBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAccountBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.fabEmail.setOnClickListener(view ->{
            if(isGmailInstalled()){
                Intent intentEmail = new Intent(Intent.ACTION_VIEW);
                intentEmail.setData(Uri.parse("mailto:" + Uri.encode("s31190080@student.ubm.ac.id") +
                        "?subject=" + Uri.encode("Subjek Email : ") +
                        "&body=" + Uri.encode("Pesan Email : ")));
                startActivity(intentEmail);
            }
            else {
                Toast.makeText(getActivity(), "Gmail tidak terinstall !!!", Toast.LENGTH_SHORT).show();
            }
        });

        binding.fabPhone.setOnClickListener(view -> {
            String waURL = "https://api.whatsapp.com/send?phone=" + "+628567519148";

            if(isWAInstalled()){
                Intent intentWA = new Intent(Intent.ACTION_VIEW);
                intentWA.setData(Uri.parse(waURL));
                startActivity(intentWA);
            }
            else {
                Toast.makeText(getActivity(), "Whatsapp tidak terinstall !!!", Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }

    private boolean isGmailInstalled(){
        PackageManager packageManager = getActivity().getPackageManager();
        boolean isInstalled;

        try {
            packageManager.getPackageInfo("com.google.android.gm", PackageManager.GET_ACTIVITIES);
            isInstalled = true;
        }catch (PackageManager.NameNotFoundException e){
            isInstalled = false;
            e.printStackTrace();
        }
        return isInstalled;
    }

    private boolean isWAInstalled(){
        PackageManager packageManager = getActivity().getPackageManager();
        boolean isInstalled;

        try {
            packageManager.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES);
            isInstalled = true;
        }catch (PackageManager.NameNotFoundException e){
            isInstalled = false;
            e.printStackTrace();
        }
        return isInstalled;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}