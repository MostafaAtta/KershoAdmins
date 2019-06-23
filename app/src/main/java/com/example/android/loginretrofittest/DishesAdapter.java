package com.example.android.loginretrofittest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class DishesAdapter extends RecyclerView.Adapter<DishesAdapter.DishesViewHolder> {

    private Context mContext;
    private List<Dish> dishesList;
    Order order;

    public DishesAdapter(Context mContext, List<Dish> mDishesList, Order order) {
        this.mContext = mContext;
        this.dishesList = mDishesList;
        this.order = order;
    }


    @NonNull
    @Override
    public DishesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dishes_item, parent, false);
        return new DishesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DishesViewHolder dishesViewHolder, int position) {

        Dish dish = dishesList.get(position);

        dishesViewHolder.textViewDishName.setText(dish.getDishName());
        int count = order.getDishesList().get(position).getQuantity();

        dishesViewHolder.count.setText("X" + count);
        dishesViewHolder.textViewKitchen.setText(String.valueOf(dish.getKitchen()));

    }

    @Override
    public int getItemCount() {

        return dishesList.size();
    }

    public class DishesViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewDishName;
        public TextView count;
        public TextView textViewKitchen;
        public CardView cardView;

        public DishesViewHolder(@NonNull View itemView){
            super(itemView);

            textViewDishName = itemView.findViewById(R.id.textViewDishName);
            count = itemView.findViewById(R.id.count_sam);
            textViewKitchen = itemView.findViewById(R.id.textViewKitchen);

            cardView = itemView.findViewById(R.id.cardViewDishes);
        }
    }
}
