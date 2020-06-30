package com.example.sarthaktaneja.coldbrewfinal.View;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.sarthaktaneja.coldbrewfinal.Adaptor.RecyclerAdapter;
import com.example.sarthaktaneja.coldbrewfinal.Model.Pojo;
import com.example.sarthaktaneja.coldbrewfinal.ModelView.GsonRequest;
import com.example.sarthaktaneja.coldbrewfinal.ModelView.VolleyQueue;
import com.example.sarthaktaneja.coldbrewfinal.R;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recycleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        recyclerView.setItemAnimator(itemAnimator);
        recyclerView.setAdapter(recycleAdapter);

        RecyclerView.LayoutManager layoutManager1 = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(layoutManager1);





        if (isOnline() == true) {
            networkRequest();

        }
        else
            Toast.makeText(MainActivity.this, "You are not connected to the internet", Toast.LENGTH_SHORT).show();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy < 0) {
                    System.out.println("Scrolled Downwards");
                    View backgroundimage = findViewById(R.id.image);
                    backgroundimage.setAlpha(1.0f);

                } else if (dy > 0) {
                    System.out.println("Scrolled Upwards");
                    View backgroundimage = findViewById(R.id.image);
                    backgroundimage.setAlpha(0.5f);

                }
            }

        });
    }


    private void networkRequest()
    {
        showProgress();
        String url = "http://18.222.17.234:8000/getPhotos";

        Request customReq = new GsonRequest(Request.Method.POST, url, new Response.Listener<Object>() {
            @Override
            public void onResponse(Object response) {
                hideProgress();
                if (response instanceof Pojo) {
                    setAdapter((Pojo) response);
                    showRecycler();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hideProgress();
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                showText();
            }
        }, new Pojo());

        VolleyQueue.getRequestQueue(this).add(customReq);
    }

    private void showText() {
        findViewById(R.id.text_middle).setVisibility(View.VISIBLE);
    }

    private void showProgress() {
        findViewById(R.id.progressbar).setVisibility(View.VISIBLE);
    }

    private void hideProgress() {
        findViewById(R.id.progressbar).setVisibility(View.INVISIBLE);
    }

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    private void showRecycler()
    {
        findViewById(R.id.recycler_view).setVisibility(View.VISIBLE);
    }

    private void setAdapter(Pojo response) {
        recycleAdapter = new RecyclerAdapter(response.getData(),this);
        recyclerView.setAdapter(recycleAdapter);
    }


}
