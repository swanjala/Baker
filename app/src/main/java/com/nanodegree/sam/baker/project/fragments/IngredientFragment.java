package com.nanodegree.sam.baker.project.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nanodegree.sam.baker.R;
import com.nanodegree.sam.baker.project.adapters.IngredientAdapter;
import com.nanodegree.sam.baker.project.models.BakingNetworkData;
import com.nanodegree.sam.baker.project.models.CookingSteps;
import com.nanodegree.sam.baker.project.models.Ingredients;

import java.util.ArrayList;

public class IngredientFragment extends Fragment {

    private BakingNetworkData bakingData;
    private static final String INGREDIENTS = "Ingredients";
    IngredientFragment.OnIngredientActionClicked mIngredientCallBack;

    public IngredientFragment() {

    }

    public interface OnIngredientActionClicked {
        void onIngredientViewActionClicked(ArrayList<CookingSteps> stepsData);

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mIngredientCallBack = (IngredientFragment.OnIngredientActionClicked) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must" +
                    "implement onFoodSelectedClickListener");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_ingredients, container, false);
        final RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_ingredients_names);
        Bundle bundle = this.getArguments();

        bakingData = bundle.getParcelable(INGREDIENTS);
        final IngredientAdapter mIngredientAdapter = new IngredientAdapter(getContext(), bakingData.getIngredients());
        recyclerView.setAdapter(mIngredientAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FloatingActionButton fb_view_steps = rootView.findViewById(R.id.fabDetails);


        FloatingActionButton fb_back_navigator = rootView.findViewById(R.id.fabBack);
        if (bundle.getBoolean("ScreenFlag")) {
            fb_view_steps.setVisibility(View.INVISIBLE);
            fb_back_navigator.setVisibility(View.INVISIBLE);

        }
        fb_view_steps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIngredientCallBack.onIngredientViewActionClicked(bakingData.getCookingSteps());
            }
        });

        fb_back_navigator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                if (fragmentManager.getBackStackEntryCount() > 0) {
                    fragmentManager.popBackStack();
                }

            }
        });

        return rootView;

    }
}
