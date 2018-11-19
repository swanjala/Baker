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
    private static final String SCREEN_FLAG = "ScreenFlag";
    private FragmentManager fragmentManager = getSupportFragmentManager();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FoodFragment foodFragment = new FoodFragment();
        if (findViewById(R.id.cl_fragment_container) != null) {

            largeScreen = true;
            if (savedInstanceState == null) {

                fragmentManager.beginTransaction().add(R.id.fragment_frame, foodFragment)
                        .commit();
            }
        } else {
            largeScreen = false;
            fragmentManager.beginTransaction().add(R.id.food_names_container, foodFragment)
                    .commit();
        }
    }

    @Override
    public void onFoodSelected(BakingNetworkData data) {

        IngredientFragment ingredientFragment = new IngredientFragment();
        DetailFragment detailFragment = new DetailFragment();
        Bundle bundle = new Bundle();

        if (largeScreen) {

            bundle.putParcelable(INGREDIENTS, data);
            bundle.putBoolean(SCREEN_FLAG, largeScreen);
            ingredientFragment.setArguments(bundle);


            fragmentManager.beginTransaction().replace(R.id.fragment_ingredients,
                    ingredientFragment)
                    .addToBackStack(null)
                    .commit();

            bundle.putParcelableArrayList(DETAILS, data.getCookingSteps());
            detailFragment.setArguments(bundle);
            fragmentManager.beginTransaction().replace(R.id.cooking_steps,
                    detailFragment).addToBackStack(null).commit();

        } else {

            bundle.putParcelable(INGREDIENTS, data);
            bundle.putBoolean(SCREEN_FLAG, largeScreen);
            ingredientFragment.setArguments(bundle);
            fragmentManager.beginTransaction().replace(R.id.food_names_container, ingredientFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public void onIngredientViewActionClicked(ArrayList<CookingSteps> cookingStepsData) {

        Bundle bundle = new Bundle();

        DetailFragment detailFragment = new DetailFragment();

        if (largeScreen) {
            bundle.putParcelableArrayList(DETAILS, cookingStepsData);
            detailFragment.setArguments(bundle);
            fragmentManager.beginTransaction().replace(R.id.cooking_steps,
                    detailFragment).addToBackStack(null).commit();
        } else {

            bundle.putParcelableArrayList(DETAILS, cookingStepsData);
            detailFragment.setArguments(bundle);
            fragmentManager.beginTransaction().replace(R.id.food_names_container,
                    detailFragment).addToBackStack(null).commit();
        }

    }

    @Override
    public void onPlayVideoSelected(String videoString) {
        VideoPlayerFragment videoPlayerFragment = new VideoPlayerFragment();

        Bundle bundle = new Bundle();

        if (largeScreen) {
            bundle.putString(DETAILS, videoString);
            bundle.putBoolean(SCREEN_FLAG, largeScreen);
            videoPlayerFragment.setArguments(bundle);
            fragmentManager.beginTransaction().replace(R.id.cooking_video,
                    videoPlayerFragment).addToBackStack(null).commit();

        } else {
            bundle.putString(DETAILS, videoString);
            videoPlayerFragment.setArguments(bundle);
            fragmentManager.beginTransaction().replace(R.id.food_names_container,
                    videoPlayerFragment).addToBackStack(null).commit();

        }
    }

}
