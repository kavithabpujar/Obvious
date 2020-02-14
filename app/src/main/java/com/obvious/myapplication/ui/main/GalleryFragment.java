package com.obvious.myapplication.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.obvious.myapplication.MainActivity;
import com.obvious.myapplication.R;
import com.obvious.myapplication.pojo.ImageData;

public class GalleryFragment extends Fragment {
    public static final String TAG=GalleryFragment.class.toString();

    private MainViewModel mViewModel;

    public static GalleryFragment newInstance() {
        return new GalleryFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.gallery_fragment, container, false);



        Button clickMe=(Button)view.findViewById(R.id.clickMe);
        clickMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainAct = (MainActivity)getActivity();
                mainAct.popFragment();
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);

        mViewModel.getSelected().observe(getViewLifecycleOwner(), new Observer<ImageData>() {
            @Override
            public void onChanged(ImageData imageData) {
                System.out.println("COPYRIGHT************ "+imageData.getCopyright());
            }
        });

        System.out.println("GALLERY FRAGMENT************ "+mViewModel.getSelected().getValue());


    }
}
