package com.example.rdf_app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.BaseMultiplePermissionsListener;

import java.util.List;

public class PDFActivity extends AppCompatActivity {

    public static final int PICK_PDF_CODE = 1000;
    Button btn_open_pdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_d_f);

        //Request Read & Write Exetrnal Storage
        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new BaseMultiplePermissionsListener(){
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        super.onPermissionsChecked(report);
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        super.onPermissionRationaleShouldBeShown(permissions, token);
                    }
                }).check();

        btn_open_pdf = (Button) findViewById(R.id.btn_open_pdf);
        btn_open_pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserPDF = new Intent(Intent.ACTION_GET_CONTENT);
                browserPDF.setType("application/pdf");
                browserPDF.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(Intent.createChooser(browserPDF, "Select pdf"), PICK_PDF_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_PDF_CODE && resultCode == RESULT_OK && data != null)
        {
            Uri selectedPDF = data.getData();
            Intent intent = new Intent(PDFActivity.this, PDFViewActivity.class);
            intent.putExtra("ViewType", "storage");
            intent.putExtra("FileUri", selectedPDF.toString());
            startActivity(intent);
        }
    }
}