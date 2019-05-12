package com.example.ngensdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import static com.example.ngensdroid.OrderViewActivity.EXTRA_CONTACT;
import static com.example.ngensdroid.OrderViewActivity.EXTRA_DESCRIPTION;
import static com.example.ngensdroid.OrderViewActivity.EXTRA_ID;
import static com.example.ngensdroid.OrderViewActivity.EXTRA_ID_USER;
import static com.example.ngensdroid.OrderViewActivity.EXTRA_IMAGE;
import static com.example.ngensdroid.OrderViewActivity.EXTRA_PRICE;
import static com.example.ngensdroid.OrderViewActivity.EXTRA_TAGS;
import static com.example.ngensdroid.OrderViewActivity.EXTRA_TITLE;

public class DetailOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_order);

        Intent intent = getIntent();
        int id = intent.getIntExtra(EXTRA_ID,0);
        String title = intent.getStringExtra(EXTRA_TITLE);
        String description = intent.getStringExtra(EXTRA_DESCRIPTION);
        String tags = intent.getStringExtra(EXTRA_TAGS);
        String price = intent.getStringExtra(EXTRA_PRICE);
        String image = intent.getStringExtra(EXTRA_IMAGE);
        int id_user = intent.getIntExtra(EXTRA_ID_USER,0);
        String contact = intent.getStringExtra(EXTRA_CONTACT);

        TextView viewTitle = (TextView) findViewById(R.id.viewOrderTitle);
        TextView viewDesc = (TextView) findViewById(R.id.viewOrderDesc);
        TextView viewTags = (TextView) findViewById(R.id.viewOrderType);
        TextView viewPrice = (TextView) findViewById(R.id.viewOrderPrice);
        TextView viewContact = (TextView) findViewById(R.id.viewOrderContact);
        ImageView viewImage = (ImageView) findViewById(R.id.viewOrderImage);

        Glide.with(this).load(image).into(viewImage);
        viewTitle.setText(title);
        viewDesc.setText(description);
        viewTags.setText(tags);
        viewPrice.setText("Rp"+price);
        viewContact.setText(contact);




    }
}
