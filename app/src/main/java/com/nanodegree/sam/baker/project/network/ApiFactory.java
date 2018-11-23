package com.nanodegree.sam.baker.project.network;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nanodegree.sam.baker.R;
import com.nanodegree.sam.baker.project.activities.IdlingResources;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiFactory {
    
    public static FoodNetworkService create(Context context){

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        OkHttpClient client = builder.build();

        IdlingResources.registerOkHttp(client);

        Retrofit.Builder retrofit = new Retrofit.Builder()
                .baseUrl(context.getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client);

        Retrofit retrofitInstance = retrofit.build();

        return retrofitInstance.create(FoodNetworkService.class);

    }

}
