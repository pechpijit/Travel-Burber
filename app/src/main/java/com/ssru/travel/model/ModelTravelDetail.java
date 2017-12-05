package com.ssru.travel.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ModelTravelDetail {

    @SerializedName("travel")
    @Expose
    private ModelTravel travel;
    @SerializedName("gallery")
    @Expose
    private ArrayList<ModelGallery> gallery;

    public ModelTravel getTravel() {
        return travel;
    }

    public void setTravel(ModelTravel travel) {
        this.travel = travel;
    }

    public ArrayList<ModelGallery> getGallery() {
        return gallery;
    }

    public void setGallery(ArrayList<ModelGallery> gallery) {
        this.gallery = gallery;
    }
}