package com.nanodegree.sam.baker;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.nanodegree.sam.baker.project.activities.MainActivity;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;



/**
 * Implementation of App Widget functionality.
 */
public class BakerWidget extends AppWidgetProvider {


    private static final String INGREDIENTS = "ingredients";
    private static final String QUALITY_KEY = "quantity";
    private static final String MEASUREMENT_KEY = "measure";
    private static final String NAME = "name";


    static void updateAppWidget(final Context context, final AppWidgetManager appWidgetManager,
                                final int appWidgetId) {



        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    URL url = new URL(context.getString(R.string.base_url)
                            .concat(context.getString(R.string.data_file)));

                    HttpClient httpClient = new DefaultHttpClient();
                    HttpGet httpGet = new HttpGet(String.valueOf(url));

                    HttpResponse responseData = null;
                    try {
                        responseData = httpClient.execute(httpGet);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    String ResponseString = null;
                    try {
                        ResponseString = EntityUtils.toString(responseData.getEntity());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    JSONArray objArray = new JSONArray(ResponseString);
                    JSONObject obj = new JSONObject(objArray.get(0).toString());

                    JSONArray ingredientsDataList = new JSONArray(obj.getString(INGREDIENTS));

                    String value = "";

                    for (int i = 0; i < ingredientsDataList.length(); i++) {
                        JSONObject jsonObject = new JSONObject(ingredientsDataList.get(i).toString());
                        value = value + jsonObject.getString(QUALITY_KEY).concat(",")
                                .concat(jsonObject.getString(MEASUREMENT_KEY).concat(",")
                                .concat(jsonObject.getString(INGREDIENTS)).concat("\n"));
                    }
                    RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baker_widget);


                    views.setTextViewText(R.id.appwidget_text, obj.getString(NAME));
                    views.setTextViewText(R.id.appwidget_data, value);
                    appWidgetManager.updateAppWidget(appWidgetId, views);


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.baker_widget);

        for (int appWidgetId : appWidgetIds) {

            updateAppWidget(context, appWidgetManager, appWidgetId);

        }

        Intent intent = new Intent(context, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.appwidget_data,pendingIntent);
        appWidgetManager.updateAppWidget(appWidgetIds,remoteViews);


    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

