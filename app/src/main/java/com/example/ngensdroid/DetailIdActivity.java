package com.example.ngensdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.ngensdroid.R;


import java.util.HashMap;
import java.util.Map;

import static com.example.ngensdroid.OrderViewIdActivity.EXTRA_CONTACT;
import static com.example.ngensdroid.OrderViewIdActivity.EXTRA_DESCRIPTION;
import static com.example.ngensdroid.OrderViewIdActivity.EXTRA_ID;
import static com.example.ngensdroid.OrderViewIdActivity.EXTRA_ID_USER;
import static com.example.ngensdroid.OrderViewIdActivity.EXTRA_IMAGE;
import static com.example.ngensdroid.OrderViewIdActivity.EXTRA_PRICE;
import static com.example.ngensdroid.OrderViewIdActivity.EXTRA_STATUS;
import static com.example.ngensdroid.OrderViewIdActivity.EXTRA_TAGS;
import static com.example.ngensdroid.OrderViewIdActivity.EXTRA_TITLE;


public class DetailIdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_id);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent intent = getIntent();
        int id = intent.getIntExtra(EXTRA_ID, 0);
        String title = intent.getStringExtra(EXTRA_TITLE);
        String description = intent.getStringExtra(EXTRA_DESCRIPTION);
        String tags = intent.getStringExtra(EXTRA_TAGS);
        String price = intent.getStringExtra(EXTRA_PRICE);
        String image = intent.getStringExtra(EXTRA_IMAGE);
        int id_user = intent.getIntExtra(EXTRA_ID_USER, 0);
        String contact = intent.getStringExtra(EXTRA_CONTACT);
        String status = intent.getStringExtra(EXTRA_STATUS);

        TextView viewTitle = (TextView) findViewById(R.id.viewOrderTitle);
        TextView viewDesc = (TextView) findViewById(R.id.viewOrderDescription);
        TextView viewTags = (TextView) findViewById(R.id.viewOrderType);
        TextView viewPrice = (TextView) findViewById(R.id.viewOrderPrice);
        TextView viewContact = (TextView) findViewById(R.id.viewOrderContact);
        ImageView viewImage = (ImageView) findViewById(R.id.viewOrderImage);
        TextView viewStatus = (TextView) findViewById(R.id.viewOrderStatus);

        Glide.with(this).load(image).into(viewImage);
        viewTitle.setText(title);
        viewDesc.setText(description);
        viewTags.setText(tags);
        viewPrice.setText("Rp" + price);
        viewContact.setText(contact);
        viewStatus.setText(status);

        Button cancelorder = (Button) findViewById(R.id.buttonCancelOrder);
        Button deleteOrder = (Button) findViewById(R.id.buttonDeleteOrder);


        //memberikan perintah bila button CANCEL ORDER ditekan
        cancelorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Membuat request untuk mengupdate status di order menjadi "Open Order"
                StringRequest updateorder = new StringRequest(Request.Method.POST, URLs.UPDATE_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

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
                        params.put("status", "Open Order");
                        params.put("id",String.valueOf(EXTRA_ID));
                        return params;
                    }
                };

                //adding our stringrequest to queue
                Volley.newRequestQueue(DetailIdActivity.this).add(updateorder);

                //Membuat request untuk meghapus order dari order_taken
                StringRequest cancelorder = new StringRequest(Request.Method.POST, URLs.CANCELORDER_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

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
                        params.put("id_order",String.valueOf(EXTRA_ID));
                        return params;
                    }
                };

                //adding our stringrequest to queue
                Volley.newRequestQueue(DetailIdActivity.this).add(cancelorder);

                finish();

            }
        });
        //memberikan perintah bila button DELETE ORDER ditekan
        deleteOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //Membuat request untuk meghapus order dari order_taken
                StringRequest canceldeleteorder = new StringRequest(Request.Method.POST, URLs.CANCELORDER_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

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
                        params.put("id_order",String.valueOf(EXTRA_ID));
                        return params;
                    }
                };

                //adding our stringrequest to queue
                Volley.newRequestQueue(DetailIdActivity.this).add(canceldeleteorder);

                //Membuat request untuk meghapus order dari database
                StringRequest deleteorder = new StringRequest(Request.Method.POST, URLs.DELETEORDER_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

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
                        params.put("id",String.valueOf(EXTRA_ID));
                        return params;
                    }
                };

                //adding our stringrequest to queue
                Volley.newRequestQueue(DetailIdActivity.this).add(deleteorder);

                finish();
            }
        });
    }
}