package com.example.mycardview;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;


import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Fruit[] mFruits = {new Fruit("apple", R.mipmap.apple),new Fruit("banana", R.mipmap.banana),new Fruit("orange", R.mipmap.orange),
            new Fruit("cherry", R.mipmap.apple),new Fruit("apple", R.mipmap.apple)};
    private ArrayList<Fruit> mFruitArrayList = new ArrayList<>();
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private FruitAdapter mAdapter;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        initFruit();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        mSwipeRefreshLayout = findViewById(R.id.swipe_refresh);
        toolbar.setTitle("卡片式布局");
        setSupportActionBar(toolbar);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        mAdapter = new FruitAdapter(mContext, mFruitArrayList);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(mAdapter);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFruit();
            }
        });

    }

    private void refreshFruit() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initFruit();
                        mAdapter.notifyDataSetChanged();
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    private void initFruit() {
        mFruitArrayList.clear();
        for (int i = 0; i < 20; i++) {
            Random random = new Random();
            int size = random.nextInt(mFruits.length);
            mFruitArrayList.add(mFruits[size]);
        }
    }
}
