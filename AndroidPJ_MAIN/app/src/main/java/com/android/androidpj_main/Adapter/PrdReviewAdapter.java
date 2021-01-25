package com.android.androidpj_main.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.androidpj_main.Activity.ProductViewActivity;
import com.android.androidpj_main.Bean.Product;
import com.android.androidpj_main.Bean.Review;
import com.android.androidpj_main.R;
import com.android.androidpj_main.Share.ShareVar;

import java.util.ArrayList;

public class PrdReviewAdapter extends RecyclerView.Adapter<PrdReviewAdapter.ProductHolder> {

    final static String TAG = "PrdReviewAdapter";

    // 지은 추가 21.01.11 *************************************

    Context mContext = null;
    int layout = 0;
    ArrayList<Review> data = null;
    LayoutInflater inflater = null;
    String urlAddr;

    public PrdReviewAdapter(Context mContext, int layout, ArrayList<Review> data){
        this.mContext = mContext;
        this.layout = layout;
        this.data = data;
        this.inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    // 리스트뷰 메뉴가 처음 생성될 때 생명주기
    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_review, parent, false);
        ProductHolder productHolder = new ProductHolder(v);
        return productHolder;
    }


    // 추가 될때 생명주기
    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {

        urlAddr = "http://" + ShareVar.macIP + ":8080/Images/";  // Images 파일
        urlAddr = urlAddr + data.get(position).getUserFilename(); // 경로에 이미지 이름 추가

        String htmlData = "<html>" +
                "<head>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                "</head>" +
                "<body><center>" +
                "<img src = \"" + urlAddr + "\"style=\"width: auto; height: 100%;\"" +
                "</center></body>" +
                "</html>";
        holder.review_img.loadData(htmlData,"text/html", "UTF-8");

        // 리뷰 작성자 이름
        holder.review_name.setText("[ "+data.get(position).getUserName()+ " ]");
        // 리뷰 별점
        holder.review_star.setText(data.get(position).getOrdStar());
        // 리뷰 내용
        holder.review_content.setText(String.valueOf(data.get(position).getOrdReview()));


    }

    @Override
    public int getItemCount() {
        return data.size();
    }



    // ProductHolder
    public class ProductHolder extends RecyclerView.ViewHolder {

        // item_review.xml 선언
        WebView review_img;
        protected TextView review_name; // 리뷰 작성자 이름
        protected TextView review_star; // 리뷰 별점
        protected TextView review_content;   // 리뷰 내용

        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            review_img = itemView.findViewById(R.id.review_img);
            review_name = itemView.findViewById(R.id.review_name);
            review_star = itemView.findViewById(R.id.review_star);
            review_content = itemView.findViewById(R.id.review_content);

            // WebView 세팅
            // Web Setting
            WebSettings webSettings = review_img.getSettings();
            webSettings.setJavaScriptEnabled(true); // 자바 스크립트는 쓰겠다.
            webSettings.setBuiltInZoomControls(true); // 확대 축소 기능
            webSettings.setDisplayZoomControls(false); // 돋보기 없애기
            review_img.setBackgroundColor(Color.TRANSPARENT);  // webview의 배경 투명으로 전환
        }
    }
}
