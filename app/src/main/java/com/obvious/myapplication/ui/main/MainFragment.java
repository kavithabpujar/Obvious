package com.obvious.myapplication.ui.main;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.obvious.myapplication.MainActivity;
import com.obvious.myapplication.R;
import com.obvious.myapplication.pojo.ImageData;

import java.util.List;

public class MainFragment extends Fragment {


    public static final String TAG=MainFragment.class.toString();
    private MainViewModel mViewModel;
    RecyclerView recyclerView;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.main_fragment, container, false);


         recyclerView=(RecyclerView)view.findViewById(R.id.imageRecycler);


//
//        Button clickMe=(Button)view.findViewById(R.id.clickMe);
//        clickMe.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ImageData data =new ImageData();
//                data.setCopyright("NEW COPYRIGHT");
//                 mViewModel.select(data);
//                System.out.println("MAIN FRAGMENT************ "+mViewModel.getSelected().getValue().getCopyright());
//                MainActivity mainAct = (MainActivity)getActivity();
//                mainAct.changeFragment(GalleryFragment.newInstance());
//            }
//        });

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);

        mViewModel.getImageList().observe(getViewLifecycleOwner(), new Observer<List<ImageData>>() {
            @Override
            public void onChanged(List<ImageData> imageData) {

            }
        });
    }



}
