package com.nanodegree.sam.baker.project.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nanodegree.sam.baker.R;
import com.nanodegree.sam.baker.project.network.ApiFactory;
import com.nanodegree.sam.baker.project.models.BakingNetworkData;
import com.nanodegree.sam.baker.project.network.FoodNetworkService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodFragment extends Fragment {

    private final static String LOG_TAG = "Network Data Failure";

    FoodFragment.OnFoodItemClickListener mFoodListenerCallBack;

    public interface OnFoodItemClickListener {
        void onFoodSelected(BakingNetworkData data);
    }

    public FoodFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mFoodListenerCallBack = (FoodFragment.OnFoodItemClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must " +
                    "implement onFoodSelectedClickListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

            final View rootView = inflater.inflate(R.layout.fragment_food_names, container, false);
            final RecyclerView recyclerView = rootView.findViewById(R.id.rv_food_names);
            FoodNetworkService foodNetworkService = ApiFactory.create(getContext());

        if (savedInstanceState == null) {
            Call<ArrayList<BakingNetworkData>> call = foodNetworkService.fetchData();
            call.enqueue(new Callback<ArrayList<BakingNetworkData>>() {

                @Override
                public void onResponse(Call<ArrayList<BakingNetworkData>> call,
                                       Response<ArrayList<BakingNetworkData>> response) {

                    ListAdapter mListAdapter = new ListAdapter(response.body());

                    recyclerView.setAdapter(mListAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                }

                @Override
                public void onFailure(Call<ArrayList<BakingNetworkData>> call, Throwable t) {
                    Log.e(LOG_TAG, t.getLocalizedMessage());

                }
            });
        }

        return rootView;
    }

    public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
        private ArrayList<BakingNetworkData> mData;

        public ListAdapter(ArrayList<BakingNetworkData> data) {
            this.mData = data;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView tv_food_name;

            public ViewHolder(View itemView) {
                super(itemView);
                this.tv_food_name = (TextView) itemView.findViewById(R.id.tv_food_name);
            }
        }

        @Override
        public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_food_name,
                    parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ListAdapter.ViewHolder holder,
                                     final int position) {
            holder.tv_food_name.setText(String.valueOf(mData.get(position).getName()));

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    mFoodListenerCallBack.onFoodSelected(mData.get(position));

                }
            });
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

    }


}
