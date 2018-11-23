package com.nanodegree.sam.baker.project.activities;

import android.support.test.espresso.IdlingRegistry;

import com.jakewharton.espresso.OkHttp3IdlingResource;

import okhttp3.OkHttpClient;

public abstract class IdlingResources {

    public static void registerOkHttp(OkHttpClient okHttpClient) {
        IdlingRegistry.getInstance().register(OkHttp3IdlingResource.create(
                "okhttp", okHttpClient));
    }

}

