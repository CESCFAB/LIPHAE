package com.android.androidpj_main.Activity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.androidpj_main.Adapter.PrdReviewAdapter;
import com.android.androidpj_main.Bean.Review;
import com.android.androidpj_main.NetworkTask.ReviewNetworkTask;
import com.android.androidpj_main.R;
import com.android.androidpj_main.Share.ShareVar;

import java.util.ArrayList;

// 21.01.25 지은 완료
public class ReviewRegisterActivity extends AppCompatActivity {

    final static String TAG = "ReviewRegisterActivity";

    WebView register_prdImg;
    TextView register_prdName, register_prdPrice;
    String prdImg, prdName, prdPrice;

    TextView tv_star;
    Spinner review_star_spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_register);
        setTitle("리뷰 작성 창");

        register_prdImg = findViewById(R.id.register_prdImg);   // 내가 구매한 상품 사진
        register_prdName = findViewById(R.id.register_prdName); // 내가 구매한 상품 이름
        register_prdPrice = findViewById(R.id.register_prdPrice);   // 내가 구매한 상품 가격

        Intent intent = getIntent();
        prdImg = intent.getStringExtra("prdFilename");
        prdName = intent.getStringExtra("prdName");
        prdPrice = intent.getStringExtra("prdPrice");

        register_prdName.setText(prdName);
        register_prdPrice.setText(prdPrice + " 원");
        Log.v(TAG, prdName);

        tv_star = findViewById(R.id.tv_star);
        review_star_spinner = findViewById(R.id.review_star_spinner);

        review_star_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                tv_star.setText((CharSequence) parent.getItemAtPosition(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }


}