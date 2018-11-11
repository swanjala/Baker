package com.nanodegree.sam.baker.project.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ingredients implements Parcelable {

    @SerializedName("quantity")
    @Expose
    private Double quantity;

    @SerializedName("measure")
    @Expose
    private String measure;

    @SerializedName("ingredient")
    @Expose
    private String ingredient;

    protected Ingredients(Parcel in) {

        quantity = in.readDouble();
        measure = in.readString();
        ingredient = in.readString();
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public int describeContents(){
        return 0;
    }
    @Override
    public void writeToParcel(Parcel parcel, int i){
        parcel.writeDouble(quantity);
        parcel.writeString(measure);
        parcel.writeString(ingredient);
    }

    public static final Creator<Ingredients> CREATOR = new Parcelable.Creator<Ingredients>(){
        @Override
        public Ingredients createFromParcel(Parcel in){
            return new Ingredients(in);

        }
        @Override
        public Ingredients[] newArray(int size){
            return new Ingredients[size];
        }
    };
}
