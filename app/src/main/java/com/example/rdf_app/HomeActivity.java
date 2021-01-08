package com.example.rdf_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.rdf_app.Email.EmailActivity;
import com.example.rdf_app.Energie.EnergieActivity;
import com.example.rdf_app.Menu.AppelezUnExpertActivity;
import com.example.rdf_app.Menu.CGUActivity;
import com.example.rdf_app.Subvention.SubventionActivity;
import com.example.rdf_app.Telecom.TelecomActivity;

public class HomeActivity extends AppCompatActivity {

    RelativeLayout telecom, energie, subvention;
    Button send_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        telecom = (RelativeLayout) findViewById(R.id.telecom);
        telecom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, TelecomActivity.class);
                startActivity(intent);
            }
        });

        energie = (RelativeLayout) findViewById(R.id.energie);
        energie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, EnergieActivity.class);
                startActivity(intent);
            }
        });

        subvention = (RelativeLayout) findViewById(R.id.subvention);
        subvention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, SubventionActivity.class);
                startActivity(intent);
            }
        });

        send_email = findViewById(R.id.send_email);
        send_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, EmailActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_more, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.CGU:
                Intent intent1 = new Intent(HomeActivity.this, CGUActivity.class);
                startActivity(intent1);
                return true;
            case R.id.appeler_expert:
                Intent intent2 = new Intent(HomeActivity.this, AppelezUnExpertActivity.class);
                startActivity(intent2);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}