 package com.example.rdf_app;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnDrawListener;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;

import android.graphics.Color;
import android.view.MotionEvent;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnRenderListener;
import com.github.barteksc.pdfviewer.listener.OnTapListener;

 public class PDFViewActivity extends AppCompatActivity {

    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_d_f_view);

        pdfView = findViewById(R.id.pdf_viewer);

        if (getIntent() != null){

            String viewType = getIntent().getStringExtra("ViewType");
            if (viewType != null || !TextUtils.isEmpty(viewType))
            {
                if (viewType.equals("storage"))
                {
                    final Uri pdfFile = Uri.parse(getIntent().getStringExtra("FileUri"));

                    pdfView.fromUri(pdfFile)
                            .password(null)
                            .defaultPage(0)
                            .enableSwipe(true)
                            .swipeHorizontal(false)
                            .enableDoubletap(true)
                            .onDraw(new OnDrawListener() {
                                @Override
                                public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {

                                }
                            })
                            .onDrawAll(new OnDrawListener() {
                                @Override
                                public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {

                                }
                            })
                            .onPageError(new OnPageErrorListener() {
                                @Override
                                public void onPageError(int page, Throwable t) {
                                    Toast.makeText(PDFViewActivity.this, "Error while open page "+page, Toast.LENGTH_SHORT).show();
                                }
                            })
                            .onPageChange(new OnPageChangeListener() {
                                @Override
                                public void onPageChanged(int page, int pageCount) {
                                    //Code here if you want to do something
                                }
                            })
                            .onTap(new OnTapListener() {
                                @Override
                                public boolean onTap(MotionEvent e) {
                                    return true;
                                }
                            }).onRender(new OnRenderListener() {
                        @Override
                        public void onInitiallyRendered(int nbPages, float pageWidth, float pageHeight) {
                            pdfView.fitToWidth(); //Fixed screen  size
                        }
                    })
                            .enableAnnotationRendering(true)
                            .invalidPageColor(Color.BLACK)
                            .load();
                }
            }
        }
    }
}