package com.example.rdf_app.Energie;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.rdf_app.FormActivity;
import com.example.rdf_app.PDFActivity;
import com.example.rdf_app.R;

import android.Manifest;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.MediaStore;
import android.widget.Toast;

public class EnergieActivity extends AppCompatActivity {

    private  static final int PERMISSION_CODE = 1000;
    private  static final int IMAGE_CAPTURE_CODE = 1001;

    Button btn_open_pdf, mCaptureBtn, btn_open_form;
    ImageView mImageView;

    Uri image_uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_energie);

        btn_open_pdf = findViewById(R.id.ouvir_pdf);
        mCaptureBtn = findViewById(R.id.capture_image_btn);
        mImageView = findViewById(R.id.image_view_camera);
        btn_open_form = findViewById(R.id.pas_de_pdf);


        btn_open_pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EnergieActivity.this, PDFActivity.class);
                startActivity(intent);
            }
        });

        btn_open_form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EnergieActivity.this, FormActivity.class);
                startActivity(intent);
            }
        });


        //button click
        mCaptureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if system os is >= android 5, request runtime permission
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED ||
                            checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {

                        //Permission nor enebled, request it
                        String[] permission = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        //show popup to request permission
                        requestPermissions(permission, PERMISSION_CODE);
                    }
                    else {
                        //permission already granded
                        openCamera();
                    }
                }
                else {
                    //systeme os < 5
                    openCamera();
                }
            }
        });
    }

    private void openCamera(){
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "RDF image titre");
        values.put(MediaStore.Images.Media.DESCRIPTION, "RDF image description");
        image_uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        //camera intent
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri);
        startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE);

    }

    //Handling permission result
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //this method is called, when user presses Allow or deny from permission request popup
        switch (requestCode){
            case PERMISSION_CODE:{
                /*if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //permission from popup was granded
                    openCamera();
                }
                else{
                    //permission from popn was denied
                    Toast.makeText(this, "Permission refusé ...", Toast.LENGTH_SHORT).show();

                }*/
                openCamera();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //called when image was captured from camera
        if (resultCode == RESULT_OK){
            //set the image captured to our ImageView
            mImageView.setImageURI(image_uri);
        }
    }
}