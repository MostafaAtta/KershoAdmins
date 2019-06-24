package com.example.android.loginretrofittest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private List<Dish> dishesList;
    private DishesAdapter dishesAdapter;
    Order order;

    TextView timeTv, addressTv, mobileTv, subTotalTv, deliveryFeesTv, totalTx, canceledTv;

    ImageView submittedImage, receivedImage, readyImage, deliveredImage, backToMain;

    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        initiateViews();


        if (getIntent().getSerializableExtra("order") != null){
            order = (Order) getIntent().getSerializableExtra("order");

            setOrderData();


            dishesDetails();
        }



    }

    private void dishesDetails(){

        recyclerView = findViewById(R.id.recycler_cart2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        int OrderId = order.getId();

//        Bundle extras = getIntent().getExtras();
//        if (intent != null) {
//            int OrderId = extras.getInt("OrderId");


            Call<DishesItem> call = RetrofitClient.getInstance().getApi().getDishesItem(OrderId);
            call.enqueue(new Callback<DishesItem>() {
                @Override
                public void onResponse(Call<DishesItem> call, Response<DishesItem> response) {

                    dishesList = response.body().getDishes();
                    dishesAdapter = new DishesAdapter(OrderDetailsActivity.this, dishesList, order);
                    recyclerView.setAdapter(dishesAdapter);

                }

                @Override
                public void onFailure(Call<DishesItem> call, Throwable t) {

                    Toast.makeText(OrderDetailsActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();

                }
            });
//        }
    }


    private void initiateViews() {
        // National ID, Password input text
        timeTv = findViewById(R.id.tv_delivery_time_sum);
        addressTv = findViewById(R.id.tv_address_sum);
        addressTv.setOnClickListener(this);
        mobileTv = findViewById(R.id.tv_phone_sum);
        mobileTv.setOnClickListener(this);
        subTotalTv = findViewById(R.id.tv_subtotal_sum);
        deliveryFeesTv = findViewById(R.id.tv_delivery_sum);
        totalTx = findViewById(R.id.tv_total_sum);

        recyclerView = findViewById(R.id.recycler_cart2);


        submittedImage = findViewById(R.id.imageView);
        receivedImage = findViewById(R.id.imageView2);
        readyImage = findViewById(R.id.imageView3);
        deliveredImage = findViewById(R.id.imageView4);
        canceledTv = findViewById(R.id.canceled_tv);

        backToMain = findViewById(R.id.btn_back_to_main);
        backToMain.setOnClickListener(this);

        constraintLayout = findViewById(R.id.order_tracker_layout);

    }


    private void setOrderData() {
        timeTv.setText(order.getOrderTime());
        addressTv.setText(order.getFullAddress().getFullAddress());
        mobileTv.setText(order.getMobile());
        subTotalTv.setText(order.getSubtotalPrice() + " EGP");
        deliveryFeesTv.setText(order.getDelivery() + " EGP");
        totalTx.setText(order.getTotalPrice() + " EGP");

        switch (order.getStatus()){
            case 0:
                submittedImage.setImageResource(R.drawable.dot_and_circle);
                receivedImage.setImageResource(R.drawable.circle);
                readyImage.setImageResource(R.drawable.circle);
                deliveredImage.setImageResource(R.drawable.circle);
                receivedImage.setOnClickListener(this);
                break;

            case 1:
                submittedImage.setImageResource(R.drawable.circle);
                receivedImage.setImageResource(R.drawable.dot_and_circle);
                readyImage.setImageResource(R.drawable.circle);
                deliveredImage.setImageResource(R.drawable.circle);
                receivedImage.setOnClickListener(null);
                readyImage.setOnClickListener(this);
                break;

            case 2:
                submittedImage.setImageResource(R.drawable.circle);
                receivedImage.setImageResource(R.drawable.circle);
                readyImage.setImageResource(R.drawable.dot_and_circle);
                deliveredImage.setImageResource(R.drawable.circle);
                readyImage.setOnClickListener(null);
                deliveredImage.setOnClickListener(this);
                break;

            case 3:
                submittedImage.setImageResource(R.drawable.circle);
                receivedImage.setImageResource(R.drawable.circle);
                readyImage.setImageResource(R.drawable.circle);
                deliveredImage.setImageResource(R.drawable.dot_and_circle);
                readyImage.setOnClickListener(null);
                deliveredImage.setOnClickListener(null);
                break;

            case 4:
                constraintLayout.setVisibility(View.INVISIBLE);
                canceledTv.setVisibility(View.VISIBLE);
                break;

            default:
                submittedImage.setImageResource(R.drawable.dot_and_circle);
                receivedImage.setImageResource(R.drawable.circle);
                readyImage.setImageResource(R.drawable.circle);
                deliveredImage.setImageResource(R.drawable.circle);
                readyImage.setOnClickListener(this);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        if (v == addressTv){


            double latitude = order.getFullAddress().getLatitude();
            double longitude = order.getFullAddress().getLongitude();
            String label = "Deliver Address!";
            String uriBegin = "geo:" + latitude + "," + longitude;
            String query = latitude + "," + longitude + "(" + label + ")";
            String encodedQuery = Uri.encode(query);
            String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
            Uri uri = Uri.parse(uriString);
            Intent mapIntent = new Intent(android.content.Intent.ACTION_VIEW, uri);
            startActivity(mapIntent);


        }else if (v == mobileTv){

            String mobile = order.getMobile();
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + mobile));
            startActivity(intent);
        }else if (v == receivedImage){

            submittedImage.setImageResource(R.drawable.circle);
            receivedImage.setImageResource(R.drawable.dot_and_circle);
            deliveredImage.setImageResource(R.drawable.circle);
            receivedImage.setOnClickListener(null);
            readyImage.setOnClickListener(this);

            //orderDetailsPresenter.updateOrderStatus(1, order.getId());
        }else if (v == readyImage){

            receivedImage.setImageResource(R.drawable.circle);
            readyImage.setImageResource(R.drawable.dot_and_circle);
            deliveredImage.setImageResource(R.drawable.circle);
            readyImage.setOnClickListener(null);
            deliveredImage.setOnClickListener(this);

            //orderDetailsPresenter.updateOrderStatus(1, order.getId());
        }else if (v == deliveredImage){

            readyImage.setImageResource(R.drawable.circle);
            deliveredImage.setImageResource(R.drawable.dot_and_circle);
            deliveredImage.setOnClickListener(null);
            //orderDetailsPresenter.updateOrderStatus(2, order.getId());
        }else if (v == backToMain){

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }
}
