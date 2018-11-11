package com.nanodegree.sam.baker.project.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

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


        if (findViewById(R.id.large_constraint) != null) {

            largeScreen = true;

            if (savedInstanceState == null) {
                FoodFragment foodFragment = new FoodFragment();

                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().add(R.id.main_food_list, foodFragment)
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
            ingredientFragment.setArguments(bundle);
            fragmentManager.beginTransaction().replace(R.id.ingredient_pane,
                    ingredientFragment)
                    .addToBackStack(null)
                    .commit();

        } else {

            FragmentManager fragmentManager = getSupportFragmentManager();
            IngredientFragment ingredientFragment = new IngredientFragment();

            Bundle bundle = new Bundle();
            bundle.putParcelable(INGREDIENTS, data);
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
        FragmentManager fragmentManager = getSupportFragmentManager();
        VideoPlayerFragment videoPlayerFragment = new VideoPlayerFragment();

        Bundle bundle = new Bundle();
        bundle.putString(DETAILS, videoString);
        videoPlayerFragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.food_names_container,
                videoPlayerFragment).addToBackStack(null).commit();
    }

}
