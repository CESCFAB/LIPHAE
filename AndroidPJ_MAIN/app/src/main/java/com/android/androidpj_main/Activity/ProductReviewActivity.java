package com.android.androidpj_main.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.android.androidpj_main.R;

public class ProductReviewActivity extends AppCompatActivity {

    TextView review_prdNo;

    String reviewNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_review);

        review_prdNo = findViewById(R.id.review_tvprdNo);

        Intent intent = getIntent();
        reviewNo = intent.getStringExtra("prdNo");

        review_prdNo.setText(reviewNo);
    }
}