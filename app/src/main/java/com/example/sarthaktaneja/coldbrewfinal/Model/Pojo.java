package com.example.sarthaktaneja.coldbrewfinal.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sarthaktaneja on 11/11/18.
 */

public class Pojo implements Serializable {


        @SerializedName("data")
        @Expose
        List<Pojo1> data = null;
        @SerializedName("status")
        @Expose
        private float status;
        @SerializedName("message")
        @Expose
        private String message;


        // Getter Methods

        public float getStatus() {
            return status;
        }

        public void setStatus( float status ) {
            this.status = status;
        }

        // Setter Methods

        public String getMessage() {
            return message;
        }

        public void setMessage( String message ) {
            this.message = message;
        }

    public List<Pojo1> getData() {
        return data;
    }

    public void setResults(ArrayList<Pojo1> results) {
        this.data = results;
    }
    }

