package com.example.ngensdroid;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderViewIdActivity extends AppCompatActivity implements OrderAdapter.OnItemClickListener {

    public static final String EXTRA_ID = "id";
    public static final String EXTRA_TITLE = "title";
    public static final String EXTRA_DESCRIPTION = "description";
    public static final String EXTRA_TAGS = "tags";
    public static final String EXTRA_PRICE = "price";
    public static final String EXTRA_IMAGE = "image";
    public static final String EXTRA_ID_USER = "id_user";
    public static final String EXTRA_CONTACT= "contact";
    public static final String EXTRA_STATUS= "status";

    //this is the JSON Data URL
    //make sure you are using the correct ip else it will not work
    private static final String URL_PRODUCTS = "http://192.168.100.8/api_ngens/Order_Api_Id.php";
    private FloatingActionButton fab;

    //a list to store all the products
    List<Order> productList;
    SwipeRefreshLayout swipeRefreshLayout;
    //the recyclerview
    RecyclerView recyclerView;
    User user = SharedPrefManager.getInstance(this).getUser();
    final int id_user = user.getId();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_view_id);


        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.simpleSwiperRefreshLayout);

        //getting the recyclerview from xml
        recyclerView = findViewById(R.id.recylcerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the productlist
        productList = new ArrayList<>();

        //this method will fetch and parse json
        //to display it in recyclerview


        loadProducts();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                productList.clear();
                loadProducts();
            }
        });



        // pada method onCreate, panggil fab dari xml
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderViewIdActivity.this,RegisterOrder.class));
            }
        });
    }

    private void loadProducts() {

        /*
         * Creating a String Request
         * The request type is GET defined by first parameter
         * The URL is defined in the second parameter
         * Then we have a Response Listener and a Error Listener
         * In response listener we will get the JSON response as a String
         * */
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_PRODUCTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject product = array.getJSONObject(i);

                                //adding the product to product list
                                productList.add(new Order(
                                        product.getInt("id"),
                                        product.getString("title"),
                                        product.getString("description"),
                                        product.getString("tags"),
                                        product.getString("price"),
                                        product.getString("image"),
                                        product.getInt("id_user"),
                                        product.getString("contact"),
                                        product.getString("status")
                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            OrderAdapter adapter = new OrderAdapter(OrderViewIdActivity.this, productList);
                            recyclerView.setAdapter(adapter);

                            OrderAdapter.setOnItemClickListener(OrderViewIdActivity.this);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
                ) {
                @Override
                protected Map<String, String> getParams()
                {
                    Map<String, String>  params = new HashMap<String, String>();
                    params.put("id_user", String.valueOf(id_user));

                    return params;
                }
            };

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }

    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(this,DetailIdActivity.class);
        Order clickedItem = productList.get(position);

        detailIntent.putExtra(EXTRA_ID,clickedItem.getId());
        detailIntent.putExtra(EXTRA_TITLE,clickedItem.getTitle());
        detailIntent.putExtra(EXTRA_DESCRIPTION,clickedItem.getDescription());
        detailIntent.putExtra(EXTRA_TAGS,clickedItem.getTags());
        detailIntent.putExtra(EXTRA_PRICE,clickedItem.getPrice());
        detailIntent.putExtra(EXTRA_IMAGE,clickedItem.getImage());
        detailIntent.putExtra(EXTRA_ID_USER,clickedItem.getId_user());
        detailIntent.putExtra(EXTRA_CONTACT,clickedItem.getContact());
        detailIntent.putExtra(EXTRA_STATUS,clickedItem.getStatus());

        startActivity(detailIntent);
    }


}