package com.example.rdf_app.Energie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.rdf_app.HomeActivity;
import com.example.rdf_app.MainActivity;
import com.example.rdf_app.PDFActivity;
import com.example.rdf_app.R;
import com.example.rdf_app.Subvention.SubventionActivity;

public class EnergieActivity extends AppCompatActivity {

    Button btn_open_pdf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_energie);

        btn_open_pdf = findViewById(R.id.ouvir_pdf);
        btn_open_pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EnergieActivity.this, PDFActivity.class);
                startActivity(intent);
            }
        });
    }
}