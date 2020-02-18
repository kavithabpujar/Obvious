package com.obvious.myapplication.ui.main.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.obvious.myapplication.R;
import com.obvious.myapplication.pojo.ImageData;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter  extends RecyclerView.Adapter {

    private static int TYPE_DISPLAY = 1;
    private static int TYPE_INFO = 2;
    Context context;
    List<ImageData> imgList;
    OnItemClickListener listener;
    private boolean fullLayout=false;


    public interface OnItemClickListener {
        void onItemClick(ImageData item);
    }

    public ImageAdapter(Context context, List<ImageData> items,boolean fullLayout) {
        this.context = context;
        this.imgList = items;
        this.fullLayout=fullLayout;
    }


    public ImageAdapter(Context context, List<ImageData> items,OnItemClickListener listener) {
        this.context = context;
        this.imgList = items;
        this.listener=listener;
    }

    public void setMessagesList(ArrayList<ImageData> items) {
        this.imgList = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if(listener != null) {
            return TYPE_DISPLAY;
        } else {
            return TYPE_INFO;
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(viewType == TYPE_DISPLAY) {
            view = LayoutInflater.from(context).inflate(R.layout.image_item, parent, false);
            return new ImageViewHolder(view);
        }else {
            view = LayoutInflater.from(context).inflate(R.layout.image_full_layout, parent, false);
            return new ImageInfoViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull  final RecyclerView.ViewHolder holder, final int position) {

        if (getItemViewType(position) == TYPE_DISPLAY) {
             ((ImageViewHolder) holder).img.setImageResource(R.mipmap.ic_launcher);
            ((ImageViewHolder) holder).img.setContentDescription(imgList.get(position).getTitle());;
        } else {
            ((ImageInfoViewHolder) holder).img.setImageResource(R.mipmap.ic_launcher);
            ((ImageInfoViewHolder) holder).img.setContentDescription(imgList.get(position).getTitle());;
            ((ImageInfoViewHolder) holder).title.setText(imgList.get(position).getTitle());
            ((ImageInfoViewHolder) holder).description.setText(imgList.get(position).getCopyright());
        }

        try {
            Glide.with(context)
                    .asBitmap()
                    .load(imgList.get(position).getUrl())
                     .error(R.drawable.ic_launcher_foreground)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(new CustomTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            if(getItemViewType(position) == TYPE_DISPLAY){
                                ((ImageViewHolder) holder).img.setImageBitmap(resource);
                                ((ImageViewHolder) holder).img.buildDrawingCache();
                                ((ImageViewHolder) holder).img.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if(listener != null)
                                            listener.onItemClick(imgList.get(position));
                                    }
                                });
                            }else{
                                ((ImageInfoViewHolder) holder).img.setImageBitmap(resource);
                                ((ImageInfoViewHolder) holder).img.buildDrawingCache();
                            }
                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {
                        }
                    });



        }catch (Exception e){
            e.printStackTrace();
        }

    }


    @Override
    public int getItemCount() {
        return imgList.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView title;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.itemImageView);
            title=itemView.findViewById(R.id.title);

        }
    }

    public class ImageInfoViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView title;
        TextView description;
        public ImageInfoViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.itemImageView);
            title=itemView.findViewById(R.id.title);
            description=itemView.findViewById(R.id.description);


        }
    }
}