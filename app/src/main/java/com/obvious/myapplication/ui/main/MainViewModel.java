package com.obvious.myapplication.ui.main;

import android.location.Location;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.obvious.myapplication.MainApplication;
import com.obvious.myapplication.pojo.ImageData;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainViewModel extends ViewModel {


    private final MutableLiveData<ImageData> selected = new MutableLiveData<ImageData>();
    private final MutableLiveData<List<ImageData>> imageList = new MutableLiveData<List<ImageData>>();

    public void select(ImageData item) {
        selected.setValue(item);
    }

    public LiveData<ImageData> getSelected() {
        return selected;
    }
    public ImageData getItem() {
        return selected.getValue();
    }

    public LiveData<List<ImageData>> getImageList(){
        if(imageList.getValue() == null){
            loadImageData();
        }
        return imageList;
    }

    public void loadImageData(){
        LoadImagesTask loadImagesTask = new LoadImagesTask();
        loadImagesTask.execute();
    }
    class LoadImagesTask extends AsyncTask<String, Void, Void> {

        protected Void doInBackground(String... params) {
            try {
              //  String message=params[0];

                Gson gson = new Gson();
                Type type = new TypeToken<List<ImageData>>(){}.getType();
                List<ImageData> imageDataList = gson.fromJson(loadJSONFromAsset(), type);
                imageList.postValue(imageDataList);
                System.out.println("IMAGE LIST POST *********** "+imageDataList.size());

            } catch (Exception e) {
                e.printStackTrace();

            }
            return null;
        };


    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = MainApplication.getMainApplicationContext().getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


}
