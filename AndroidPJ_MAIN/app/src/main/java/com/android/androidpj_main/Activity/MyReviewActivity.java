package com.android.androidpj_main.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.androidpj_main.Adapter.MyReviewAdapter;
import com.android.androidpj_main.Adapter.PrdReviewAdapter;
import com.android.androidpj_main.Bean.MyReview;
import com.android.androidpj_main.Bean.Review;
import com.android.androidpj_main.Main.PreferenceManager;
import com.android.androidpj_main.NetworkTask.MyReviewNetworkTask;
import com.android.androidpj_main.NetworkTask.ReviewNetworkTask;
import com.android.androidpj_main.R;
import com.android.androidpj_main.Share.ShareVar;

import java.util.ArrayList;

// 21.01.25 지은 완료
public class MyReviewActivity extends AppCompatActivity {

    // 나의 리뷰만을 띄우기 위해 유저 아이디 받았는지 확인
    TextView review_myEmail;
    // 그 리뷰에 대한 번호 비교 위함
    String userEmail;

    final static String TAG = "MyReviewActivity";
    String urlAddr = null;
    ArrayList<MyReview> myReviews;
    MyReviewAdapter myReviewAdapter;
    private RecyclerView.LayoutManager layoutManager;
    RecyclerView MyReview_recycleView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myreview);

        review_myEmail = findViewById(R.id.review_myEmail);

        // 저장된 아이디(이메일) 값
        userEmail = PreferenceManager.getString(MyReviewActivity.this, "email");

        review_myEmail.setText(userEmail);

        MyReview_recycleView = findViewById(R.id.MyReview_recycleView);


        urlAddr = "http://" + ShareVar.macIP + ":8080/JSP/MyReviewList.jsp?userEmail=" + userEmail;
        connectGetMyReview();
    }

    //메소드 = 로그인한 아이디값에 대한 리뷰만을 띄워주는
    private void connectGetMyReview(){
        try {
            MyReviewNetworkTask networkTask = new MyReviewNetworkTask(MyReviewActivity.this, urlAddr); //onCreate 에 urlAddr 이 선언된것이 들어옴

            // object 에서 선언은 되었지만 실질적으로 리턴한것은 arraylist
            Object object = networkTask.execute().get();
            myReviews = (ArrayList<MyReview>) object;

            myReviewAdapter = new MyReviewAdapter(MyReviewActivity.this, R.layout.item_myreview, myReviews);
            MyReview_recycleView.setAdapter(myReviewAdapter);
            MyReview_recycleView.setHasFixedSize(true); // 리사이클러뷰 기존성능 강화
            layoutManager = new LinearLayoutManager(MyReviewActivity.this);
            MyReview_recycleView.setLayoutManager(layoutManager);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //입력 되어도 리스트에 실시간으로 추가 되게 만들어줌(유지)
    @Override
    public void onResume() {
        super.onResume();
        connectGetMyReview();
        Log.v(TAG, "onResume()");
    }

}