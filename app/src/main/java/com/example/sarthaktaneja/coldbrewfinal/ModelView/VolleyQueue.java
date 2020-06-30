package com.example.sarthaktaneja.coldbrewfinal.ModelView;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by sarthaktaneja on 11/11/18.
 */

public class VolleyQueue {

    static RequestQueue queue ;

    public static RequestQueue getRequestQueue(Context context){
        if(queue == null){
            queue = Volley.newRequestQueue(context.getApplicationContext());
        }

        return queue;
    }

    void add(Request request){
        queue.add(request);
    }
}
