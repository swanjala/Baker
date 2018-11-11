package com.nanodegree.sam.baker.project.network;

import com.nanodegree.sam.baker.project.models.BakingNetworkData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FoodNetworkService {

    @GET("baking.json")
    Call<ArrayList<BakingNetworkData>> fetchData();
}
