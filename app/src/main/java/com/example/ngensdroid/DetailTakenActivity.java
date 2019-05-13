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


import java.util.HashMap;
import java.util.Map;

import static com.example.ngensdroid.OrderViewTakenActivity.EXTRA_CONTACT;
import static com.example.ngensdroid.OrderViewTakenActivity.EXTRA_DESCRIPTION;
import static com.example.ngensdroid.OrderViewTakenActivity.EXTRA_ID;
import static com.example.ngensdroid.OrderViewTakenActivity.EXTRA_ID_TECHNICIAN;
import static com.example.ngensdroid.OrderViewTakenActivity.EXTRA_ID_USER;
import static com.example.ngensdroid.OrderViewTakenActivity.EXTRA_IMAGE;
import static com.example.ngensdroid.OrderViewTakenActivity.EXTRA_PRICE;
import static com.example.ngensdroid.OrderViewTakenActivity.EXTRA_STATUS;
import static com.example.ngensdroid.OrderViewTakenActivity.EXTRA_TAGS;
import static com.example.ngensdroid.OrderViewTakenActivity.EXTRA_TITLE;


public class DetailTakenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_taken);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(getString(R.string.app_name));
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent intent = getIntent();
        int id = intent.getIntExtra(EXTRA_ID,0);
        String title = intent.getStringExtra(EXTRA_TITLE);
        String description = intent.getStringExtra(EXTRA_DESCRIPTION);
        String tags = intent.getStringExtra(EXTRA_TAGS);
        String price = intent.getStringExtra(EXTRA_PRICE);
        String image = intent.getStringExtra(EXTRA_IMAGE);
        int id_user = intent.getIntExtra(EXTRA_ID_USER,0);
        String contact = intent.getStringExtra(EXTRA_CONTACT);
        String status = intent.getStringExtra(EXTRA_STATUS);
        int id_technician = intent.getIntExtra(EXTRA_ID_TECHNICIAN,0);

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
        viewPrice.setText("Rp"+price);
        viewContact.setText(contact);
        viewStatus.setText(status);

        Button cancelorder = (Button) findViewById(R.id.buttonCancelOrder);
        Button finishorder = (Button) findViewById(R.id.buttonFinishOrder);

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
                Volley.newRequestQueue(DetailTakenActivity.this).add(updateorder);

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
                Volley.newRequestQueue(DetailTakenActivity.this).add(cancelorder);

                finish();

            }
        });

        finishorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Membuat request untuk mengupdate status di order menjadi "Finished"
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
                        params.put("status", "Finished");
                        params.put("id",String.valueOf(EXTRA_ID));
                        return params;
                    }
                };

                //adding our stringrequest to queue
                Volley.newRequestQueue(DetailTakenActivity.this).add(updateorder);

                //Membuat request untuk mengupdate status di order menjadi "Finished"
                StringRequest finishorder = new StringRequest(Request.Method.POST, URLs.FINISHORDER_URL,
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
                Volley.newRequestQueue(DetailTakenActivity.this).add(finishorder);



            }
        });
    }
}
