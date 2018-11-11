package com.nanodegree.sam.baker.project.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nanodegree.sam.baker.R;
import com.nanodegree.sam.baker.project.models.Ingredients;

import java.util.ArrayList;

public class IngredientAdapter extends RecyclerView.
        Adapter<IngredientAdapter.ViewHolder> {

    private ArrayList<Ingredients> mIngredientData;

    private Context context;
    private static final String INGREDIENT_DATA = "ingredient_data";
    private static final String LOG_TAG= "data_error";

    public IngredientAdapter(Context context, ArrayList<Ingredients> ingredientData){
        this.context = context;
        this.mIngredientData= ingredientData;
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{

        TextView tv_ingredient, tv_quantity, tv_measure;

        public ViewHolder(View itemView){
            super(itemView);
            this.tv_ingredient = itemView.findViewById(R.id.tv_ingredient);
            this.tv_quantity = itemView.findViewById(R.id.tv_quantity);
            this.tv_measure = itemView.findViewById(R.id.tv_measure);
        }
    }
    @Override
    public IngredientAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_ingredients, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;

    }
    @Override
    public void onBindViewHolder(IngredientAdapter.ViewHolder holder, final int position) {

        holder.tv_measure.setText(mIngredientData.get(position).getMeasure());
        holder.tv_quantity.setText(mIngredientData.get(position).getQuantity().toString());
        holder.tv_ingredient.setText(mIngredientData.get(position).getIngredient());
    }
        @Override
    public int getItemCount() {

        return mIngredientData.size();
        }
}
