package com.nanodegree.sam.baker.project.models;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BakingNetworkData implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("servings")
    @Expose
    private Integer servings;

    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("ingredients")
    @Expose
    private ArrayList<Ingredients> ingredients;

    @SerializedName("steps")
    @Expose
    private ArrayList<CookingSteps> cookingSteps;


    protected BakingNetworkData(Parcel in){
        id = in.readInt();
        name = in.readString();
        servings = in.readInt();
        image = in.readString();
        ingredients = in.readArrayList(Ingredients.class.getClassLoader());
        cookingSteps = in.readArrayList(CookingSteps.class.getClassLoader());

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Integer getServings() {
        return servings;
    }
    public ArrayList<Ingredients> getIngredients() {
        return ingredients;
    }
    public ArrayList<CookingSteps> getCookingSteps(){
        return cookingSteps;
    }

    public void setIngredients(ArrayList<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeInt(servings);
        parcel.writeString(image);
        parcel.writeList(ingredients);
        parcel.writeList(cookingSteps);

    }

    public static final Creator<BakingNetworkData> CREATOR = new Parcelable.Creator<BakingNetworkData>(){
        @Override
        public BakingNetworkData createFromParcel(Parcel in){
            return new BakingNetworkData(in);
        }
        @Override
        public BakingNetworkData[] newArray(int size ){
            return new BakingNetworkData[size];
        }
    };
}

