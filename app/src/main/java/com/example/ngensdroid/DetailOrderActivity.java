package com.example.ngensdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.ngensdroid.OrderViewActivity.EXTRA_CONTACT;
import static com.example.ngensdroid.OrderViewActivity.EXTRA_DESCRIPTION;
import static com.example.ngensdroid.OrderViewActivity.EXTRA_ID;
import static com.example.ngensdroid.OrderViewActivity.EXTRA_ID_USER;
import static com.example.ngensdroid.OrderViewActivity.EXTRA_IMAGE;
import static com.example.ngensdroid.OrderViewActivity.EXTRA_PRICE;
import static com.example.ngensdroid.OrderViewActivity.EXTRA_STATUS;
import static com.example.ngensdroid.OrderViewActivity.EXTRA_TAGS;
import static com.example.ngensdroid.OrderViewActivity.EXTRA_TITLE;




public class DetailOrderActivity extends AppCompatActivity {

    User user = SharedPrefManager.getInstance(this).getUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_order);
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
        final int id = intent.getIntExtra(EXTRA_ID,0);
        String title = intent.getStringExtra(EXTRA_TITLE);
        String description = intent.getStringExtra(EXTRA_DESCRIPTION);
        String tags = intent.getStringExtra(EXTRA_TAGS);
        String price = intent.getStringExtra(EXTRA_PRICE);
        String image = intent.getStringExtra(EXTRA_IMAGE);
        int id_user = intent.getIntExtra(EXTRA_ID_USER,0);
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
        viewPrice.setText("Rp"+price);
        viewContact.setText(contact);
        viewStatus.setText(status);

        Button takeorder = (Button) findViewById(R.id.buttonTakeOrder);

        takeorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //Membuat request untuk mengupdate status di order menjadi "ONGOING"
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
                        params.put("status", "Ongoing");
                        params.put("id",String.valueOf(id));
                        return params;
                    }
                };

                //adding our stringrequest to queue
                Volley.newRequestQueue(DetailOrderActivity.this).add(updateorder);

                //Membuat request untuk menerima order
                StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.TAKEORDER_URL,
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
                        params.put("id_technician", String.valueOf(user.getId()));
                        params.put("id_order",String.valueOf(id));
                        return params;
                    }
                };

                //adding our stringrequest to queue
                Volley.newRequestQueue(DetailOrderActivity.this).add(stringRequest);

                finish();

            }
        });
    }
}
