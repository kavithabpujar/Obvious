package com.obvious.myapplication.ui.main;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.obvious.myapplication.MainActivity;
import com.obvious.myapplication.MainApplication;
import com.obvious.myapplication.R;
import com.obvious.myapplication.pojo.ImageData;
import com.obvious.myapplication.ui.main.adapter.ImageAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {


    public static final String TAG=MainFragment.class.toString();
    private MainViewModel mViewModel;
    RecyclerView recyclerView;
    ImageAdapter imageAdpater;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.main_fragment, container, false);

         recyclerView=(RecyclerView)view.findViewById(R.id.imageRecycler);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainApplication.getMainApplicationContext(),2);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);

       return view;
    }

    ImageAdapter.OnItemClickListener listener=new ImageAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(ImageData item) {
                mViewModel.select(item);
                MainActivity mainAct = (MainActivity)getActivity();
                mainAct.changeFragment(GalleryFragment.newInstance());
        }
    };


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);

        mViewModel.getImageList().observe(getViewLifecycleOwner(), new Observer<List<ImageData>>() {
            @Override
            public void onChanged(List<ImageData> imageData) {
                imageAdpater=new ImageAdapter(MainApplication.getMainApplicationContext(),imageData,listener);
                recyclerView.setAdapter(imageAdpater);
                imageAdpater.notifyDataSetChanged();

            }
        });
    }



}
