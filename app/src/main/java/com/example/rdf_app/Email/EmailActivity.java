package com.example.rdf_app.Email;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.rdf_app.R;

public class EmailActivity extends AppCompatActivity {

    EditText email_to, email_Subject, email_Message;
    Button btn_email_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        email_to = findViewById(R.id.email_to);
        email_Subject = findViewById(R.id.email_subject);
        email_Message = findViewById(R.id.email_message);
        btn_email_send = findViewById(R.id.btn_email_send);

        btn_email_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW
                        ,Uri.parse("mailto:" + email_to.getText().toString()));
                intent.putExtra(Intent.EXTRA_SUBJECT, email_Subject.getText().toString());
                intent.putExtra(Intent.EXTRA_TEXT, email_Message.getText().toString());
                startActivity(intent);
            }
        });
    }
}