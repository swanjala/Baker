package com.nanodegree.sam.baker.project.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.nanodegree.sam.baker.R;
import com.nanodegree.sam.baker.project.models.CookingSteps;

import java.util.ArrayList;

public class DetailFragment extends Fragment {

    private static final String DETAILS = "Details";

    private ArrayList<CookingSteps> stepsData;

    DetailFragment.OnVideoButtonClickListener mDetailListenerCallBack;

    public interface OnVideoButtonClickListener{
        void onPlayVideoSelected(String videoString);
    }

    public DetailFragment(){

    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try {
            mDetailListenerCallBack = (DetailFragment.OnVideoButtonClickListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()+"must" +
                    "implement onFoodSelectedClickListener");
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle SavedInstanceState){

        final View rootView = inflater.inflate(R.layout.fragment_steps, container, false);

        final RecyclerView recyclerView = rootView.findViewById(R.id.rv_steps);

        Bundle bundle = this.getArguments();

        stepsData = bundle.getParcelableArrayList(DETAILS);

        DetailAdapter detailAdapter = new DetailAdapter(getContext(),stepsData);
        recyclerView.setAdapter(detailAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        FloatingActionButton fb_back_navigator = rootView.findViewById(R.id.fabDetailBack);

        if (bundle.getBoolean("ScreenFlag")) {
            fb_back_navigator.setVisibility(View.INVISIBLE);

        }
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

    public class DetailAdapter extends RecyclerView.
            Adapter<DetailAdapter.ViewHolder> {

        private ArrayList<CookingSteps> mCookingStepsData;

        private Context context;

        public DetailAdapter(Context context, ArrayList<CookingSteps> cookingStepsData){
            this.context = context;
            this.mCookingStepsData = cookingStepsData;
        }
        public class ViewHolder extends RecyclerView.ViewHolder {

            TextView tv_short_description, tv_long_description;
            ImageView im_play_video;

            public ViewHolder(View itemView) {
                super(itemView);
                this.tv_short_description = itemView.findViewById(R.id.tv_short_description);
                this.tv_long_description = itemView.findViewById(R.id.tv_full_description);
                this.im_play_video = itemView.findViewById(R.id.im_play_video);

            }
        }
        @Override
        public DetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_steps,parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;

        }
        @Override
        public void onBindViewHolder(DetailAdapter.ViewHolder holder, final int position){
            holder.tv_long_description.setText(mCookingStepsData.get(position).getDescription());
            holder.tv_short_description.setText(mCookingStepsData.get(position).getShortDescription());
            holder.im_play_video.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mDetailListenerCallBack.onPlayVideoSelected(mCookingStepsData.get(position).getVideoURL());
                }
            });
        }
        @Override
        public int getItemCount() {
            return mCookingStepsData.size();
        }

    }

}
