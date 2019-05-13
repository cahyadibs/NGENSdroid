package com.example.ngensdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageButton list,client_list,taken_order,profile;
    TextView textViewUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        User user = SharedPrefManager.getInstance(this).getUser();
        final String username = user.getUsername();

        list = (ImageButton) findViewById(R.id.imageButtonList);
        client_list = (ImageButton) findViewById(R.id.imageButtonClientList);
        taken_order = (ImageButton) findViewById(R.id.imageButtonService);
        profile = (ImageButton) findViewById(R.id.imageButtonAccount);
        textViewUsername = (TextView) findViewById(R.id.textViewUsername1);

        textViewUsername.setText(username);


        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),OrderViewActivity.class));
            }
        });

        client_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),OrderViewIdActivity.class));
            }
        });

        taken_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),OrderViewTakenActivity.class));
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
            }
        });

    }
}
