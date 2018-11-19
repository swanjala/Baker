package com.nanodegree.sam.baker.project.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.nanodegree.sam.baker.R;
import com.nanodegree.sam.baker.project.fragments.DetailFragment;
import com.nanodegree.sam.baker.project.fragments.FoodFragment;
import com.nanodegree.sam.baker.project.fragments.IngredientFragment;
import com.nanodegree.sam.baker.project.fragments.VideoPlayerFragment;
import com.nanodegree.sam.baker.project.models.BakingNetworkData;
import com.nanodegree.sam.baker.project.models.CookingSteps;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FoodFragment.OnFoodItemClickListener,
        IngredientFragment.OnIngredientActionClicked, DetailFragment.OnVideoButtonClickListener {

    private boolean largeScreen;

    private static final String DETAILS = "Details";
    private static final String INGREDIENTS = "Ingredients";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (findViewById(R.id.cl_fragment_container) != null) {

            largeScreen = true;

            if (savedInstanceState == null) {
                FoodFragment foodFragment = new FoodFragment();

                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().add(R.id.fragment_frame, foodFragment)
                        .commit();

            }
        } else {
            largeScreen = false;
            FoodFragment foodFragment = new FoodFragment();

            FragmentManager fragmentManager = getSupportFragmentManager();

            fragmentManager.beginTransaction().add(R.id.food_names_container, foodFragment)
                    .commit();
        }

    }

    @Override
    public void onFoodSelected(BakingNetworkData data) {

        if (largeScreen) {

            FragmentManager fragmentManager = getSupportFragmentManager();
            IngredientFragment ingredientFragment = new IngredientFragment();

            Bundle bundle = new Bundle();
            bundle.putParcelable(INGREDIENTS, data);
            bundle.putBoolean("ScreenFlag", largeScreen);
            ingredientFragment.setArguments(bundle);


            fragmentManager.beginTransaction().replace(R.id.fragment_ingredients,
                    ingredientFragment)
                    .addToBackStack(null)
                    .commit();
            //FragmentManager fragmentManager = getSupportFragmentManager();
            DetailFragment detailFragment = new DetailFragment();

           // Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(DETAILS, data.getCookingSteps());
            detailFragment.setArguments(bundle);
            fragmentManager.beginTransaction().replace(R.id.cooking_steps,
                    detailFragment).addToBackStack(null).commit();

            VideoPlayerFragment videoPlayerFragment = new VideoPlayerFragment();

        } else {

            FragmentManager fragmentManager = getSupportFragmentManager();
            IngredientFragment ingredientFragment = new IngredientFragment();

            Bundle bundle = new Bundle();
            bundle.putParcelable(INGREDIENTS, data);
            bundle.putBoolean("ScreenFlag", largeScreen);
            ingredientFragment.setArguments(bundle);
            fragmentManager.beginTransaction().replace(R.id.food_names_container, ingredientFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }


    @Override
    public void onIngredientViewActionClicked(ArrayList<CookingSteps> cookingStepsData) {


        if (largeScreen) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            DetailFragment detailFragment = new DetailFragment();

            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(DETAILS, cookingStepsData);
            detailFragment.setArguments(bundle);
            fragmentManager.beginTransaction().replace(R.id.cooking_steps,
                    detailFragment).addToBackStack(null).commit();
        } else {

            FragmentManager fragmentManager = getSupportFragmentManager();
            DetailFragment detailFragment = new DetailFragment();

            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(DETAILS, cookingStepsData);
            detailFragment.setArguments(bundle);
            fragmentManager.beginTransaction().replace(R.id.food_names_container,
                    detailFragment).addToBackStack(null).commit();
        }

    }

    @Override
    public void onPlayVideoSelected(String videoString) {

        if(largeScreen){
            FragmentManager fragmentManager = getSupportFragmentManager();
            VideoPlayerFragment videoPlayerFragment = new VideoPlayerFragment();

            Bundle bundle = new Bundle();
            bundle.putString(DETAILS, videoString);
            bundle.putBoolean("ScreenFlag",largeScreen);
            videoPlayerFragment.setArguments(bundle);
            fragmentManager.beginTransaction().replace(R.id.cooking_video,
                    videoPlayerFragment).addToBackStack(null).commit();

        } else {
            FragmentManager fragmentManager = getSupportFragmentManager();
            VideoPlayerFragment videoPlayerFragment = new VideoPlayerFragment();

            Bundle bundle = new Bundle();
            bundle.putString(DETAILS, videoString);
            videoPlayerFragment.setArguments(bundle);
            fragmentManager.beginTransaction().replace(R.id.food_names_container,
                    videoPlayerFragment).addToBackStack(null).commit();

        }
    }

}
