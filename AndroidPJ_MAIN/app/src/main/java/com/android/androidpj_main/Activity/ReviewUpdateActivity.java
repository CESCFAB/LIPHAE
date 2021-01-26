package com.android.androidpj_main.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.androidpj_main.Adapter.PrdReviewAdapter;
import com.android.androidpj_main.Bean.Review;
import com.android.androidpj_main.Bean.User;
import com.android.androidpj_main.Main.PreferenceManager;
import com.android.androidpj_main.NetworkTask.CUDNetworkTask;
import com.android.androidpj_main.NetworkTask.ReviewNetworkTask;
import com.android.androidpj_main.R;
import com.android.androidpj_main.Share.ShareVar;

import java.util.ArrayList;

// 21.01.25 지은 완료
public class ReviewUpdateActivity extends AppCompatActivity {

    final static String TAG = "ReviewUpdateActivity";

    WebView update_prdImg;
    TextView update_prdName, update_prdPrice;
    String prdImg, prdName, prdBrand, ordNo, ordReview, ordStar;

    TextView update_tvStar;
    Spinner update_star_spinner;

    Button btn_update_review;
    EditText update_etReview;


    String urlAddr_review = null;

    String userEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_update);
        setTitle("리뷰 수정 창");

        update_prdImg = findViewById(R.id.update_prdImg);   // 내가 구매한 상품 사진
        update_prdName = findViewById(R.id.update_prdName); // 내가 구매한 상품 이름
        update_prdPrice = findViewById(R.id.update_prdBrand);   // 내가 구매한 상품 브랜드

        Intent intent = getIntent();
        prdImg = intent.getStringExtra("prdFilename");
        prdName = intent.getStringExtra("prdName");
        prdBrand = intent.getStringExtra("prdBrand");
        ordNo = intent.getStringExtra("orderNo");
        ordReview = intent.getStringExtra("ordReview");
        ordStar = intent.getStringExtra("ordStar");


        update_prdPrice.setText("[ "+ prdBrand + " ]");
        update_prdPrice.setText(prdName);

        update_tvStar = findViewById(R.id.update_tvStar);


        // 별점 스피너 ***************************************
        update_star_spinner = findViewById(R.id.update_star_spinner);

        update_star_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                update_tvStar.setText((CharSequence) parent.getItemAtPosition(position));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        // ***************************************

        //
        btn_update_review = findViewById(R.id.btn_update_review);
        btn_update_review.setOnClickListener(registerOnClickListener);

        update_etReview = findViewById(R.id.update_etReview);

        // 저장된 아이디(이메일) 값
        userEmail = PreferenceManager.getString(ReviewUpdateActivity.this, "email");


        update_etReview.setText(ordReview);

    }


    View.OnClickListener registerOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_update_review:
                    String review_star = update_tvStar.getText().toString();
                    String review_content = update_etReview.getText().toString();


                    urlAddr_review = "http://" + ShareVar.macIP + ":8080/JSP/MyReview_Update.jsp?ordReview=" + review_content;
                    urlAddr_review = urlAddr_review +"&ordStar="+ review_star +"&userEmail=" + userEmail+"&ordNo="+ordNo;

                    connectRegisterReview();
                    Toast.makeText(ReviewUpdateActivity.this, prdName+" 상품에 대한 리뷰가 수정되었습니다.", Toast.LENGTH_SHORT).show();

                    break;
            }
        }



        // 리뷰 입력 받은값을 업데이트 시킴
        private void connectRegisterReview(){
            try {
                CUDNetworkTask reviewNetworkTask = new CUDNetworkTask(ReviewUpdateActivity.this, urlAddr_review);
                reviewNetworkTask.execute().get();

            }catch (Exception e){
                e.printStackTrace();
            }
        }

    };


    //editText 외의 화면 클릭시 키보드 숨기기
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View focusView = getCurrentFocus();
        if (focusView != null) {
            Rect rect = new Rect();
            focusView.getGlobalVisibleRect(rect);
            int x = (int) ev.getX(), y = (int) ev.getY();
            if (!rect.contains(x, y)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if (imm != null)
                    imm.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
                focusView.clearFocus();
            }
        }
        return super.dispatchTouchEvent(ev);
    }


}