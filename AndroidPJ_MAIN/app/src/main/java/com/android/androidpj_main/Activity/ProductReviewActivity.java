package com.android.androidpj_main.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.androidpj_main.Adapter.PrdReviewAdapter;
import com.android.androidpj_main.Adapter.SearchAdapter;
import com.android.androidpj_main.Bean.Product;
import com.android.androidpj_main.Bean.Review;
import com.android.androidpj_main.NetworkTask.LikeNetworkTask;
import com.android.androidpj_main.NetworkTask.ReviewNetworkTask;
import com.android.androidpj_main.R;
import com.android.androidpj_main.Share.ShareVar;

import java.util.ArrayList;

// 21.01.25 지은 완료
public class ProductReviewActivity extends AppCompatActivity {

    // 상품 번호 띄움
    TextView goods_prdNo;
    // 그 리뷰에 대한 번호 비교 위함
    String prdNo;

    final static String TAG = "ProductReviewActivity";
    String urlAddr = null;
    ArrayList<Review> reviews;
    PrdReviewAdapter reviewAdapter;
    private RecyclerView.LayoutManager layoutManager;
    RecyclerView review_recycleView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_review);

        goods_prdNo = findViewById(R.id.review_tvprdNo);

        // 상품 번호 받아옴
        Intent intent = getIntent();
        prdNo = intent.getStringExtra("prdNo");

        goods_prdNo.setText(prdNo);

        review_recycleView = findViewById(R.id.product_review_recycleView);


        urlAddr = "http://" + ShareVar.macIP + ":8080/JSP/ReviewList.jsp?goods_prdNo=" + prdNo;
        connectGetReview();
    }

    //메소드 = 로그인한 아이디값에 저장된 연락처 띄워주는
    private void connectGetReview(){
        try {
            ReviewNetworkTask networkTask = new ReviewNetworkTask(ProductReviewActivity.this, urlAddr); //onCreate 에 urlAddr 이 선언된것이 들어옴

            // object 에서 선언은 되었지만 실질적으로 리턴한것은 arraylist
            Object object = networkTask.execute().get();
//            gridLayoutManager = new GridLayoutManager(this, 2);
            reviews = (ArrayList<Review>) object;

            reviewAdapter = new PrdReviewAdapter(ProductReviewActivity.this, R.layout.item_review, reviews);
            review_recycleView.setAdapter(reviewAdapter);
            review_recycleView.setHasFixedSize(true); // 리사이클러뷰 기존성능 강화
            layoutManager = new LinearLayoutManager(ProductReviewActivity.this);
            review_recycleView.setLayoutManager(layoutManager);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //입력 되어도 리스트에 실시간으로 추가 되게 만들어줌(유지)
    @Override
    public void onResume() {
        super.onResume();
        connectGetReview();
        Log.v(TAG, "onResume()");
    }

}