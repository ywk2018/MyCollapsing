package com.example.mycardview;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * class description:
 * author ywk
 * since 2019-05-04
 */
public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {

    private Context mContext;
    private List<Fruit> mFruitlist;

    public FruitAdapter(Context context, List<Fruit> fruitlist) {
        mContext = context;
        mFruitlist = fruitlist;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView fruitName;
        ImageView fruitimageId;
        CardView mCardView;
        public ViewHolder(View view) {
            super(view);
            mCardView = (CardView) view;
            fruitName = view.findViewById(R.id.fruit_name);
            fruitimageId = view.findViewById(R.id.fruit_image);
        }

    }

    @NonNull
    @Override
    public FruitAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.fruit_item, viewGroup, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = viewHolder.getAdapterPosition();
                Fruit fruit1 = mFruitlist.get(i);
                Intent intent = new Intent(mContext, FruitActivity.class);
                intent.putExtra(FruitActivity.FRUIT_NAME, fruit1.getName());
                intent.putExtra(FruitActivity.FRUIT_IMAGE_ID, fruit1.getImageId());
                mContext.startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Fruit fruit = mFruitlist.get(i);
        viewHolder.fruitName.setText(fruit.getName());
        Glide.with(mContext).load(fruit.getImageId()).into(viewHolder.fruitimageId);

    }

    @Override
    public int getItemCount() {
        return mFruitlist.size();
    }
}