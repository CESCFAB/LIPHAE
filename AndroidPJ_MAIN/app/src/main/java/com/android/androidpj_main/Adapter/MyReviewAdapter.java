package com.android.androidpj_main.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.androidpj_main.Activity.MyReviewActivity;
import com.android.androidpj_main.Activity.MyViewActivity;
import com.android.androidpj_main.Activity.ProductViewActivity;
import com.android.androidpj_main.Activity.ReviewUpdateActivity;
import com.android.androidpj_main.Bean.MyReview;
import com.android.androidpj_main.Bean.Review;
import com.android.androidpj_main.Main.PreferenceManager;
import com.android.androidpj_main.NetworkTask.CUDNetworkTask;
import com.android.androidpj_main.R;
import com.android.androidpj_main.Share.ShareVar;

import java.util.ArrayList;

public class MyReviewAdapter extends RecyclerView.Adapter<MyReviewAdapter.ProductHolder> {

    final static String TAG = "MyReviewAdapter";

    // 지은 추가 21.01.26 *************************************

    Context mContext = null;
    int layout = 0;
    ArrayList<MyReview> data = null;
    LayoutInflater inflater = null;
    String urlAddr;

    String urlAddrDel = null;

    int ordNo;
    String userEmail;
    String ordName;

    public MyReviewAdapter(Context mContext, int layout, ArrayList<MyReview> data){
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
        v = LayoutInflater.from(mContext).inflate(R.layout.item_myreview, parent, false);
        ProductHolder productHolder = new ProductHolder(v);
        return productHolder;
    }


    // 추가 될때 생명주기
    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {

        urlAddr = "http://" + ShareVar.macIP + ":8080/Images/";  // Images 파일
        urlAddr = urlAddr + data.get(position).getPrdFilename(); // 경로에 이미지 이름 추가

        String htmlData = "<html>" +
                "<head>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                "</head>" +
                "<body><center>" +
                "<img src = \"" + urlAddr + "\"style=\"width: auto; height: 100%;\"" +
                "</center></body>" +
                "</html>";
        holder.myReview_img.loadData(htmlData,"text/html", "UTF-8");

        // 나의 리뷰 상품 브랜드
        holder.myReview_prdBrand.setText("[ "+data.get(position).getPrdBrand()+ " ]");
        // 나의 리뷰 상품 이름
        holder.myReview_prdName.setText(data.get(position).getPrdName());
        // 나의 리뷰 별점
        holder.myReview_star.setText(String.valueOf(data.get(position).getOrdStar()));
        // 나의 리뷰 내용
        holder.myReview_content.setText(String.valueOf(data.get(position).getOrdReview()));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ProductViewActivity.class);
                intent.putExtra("prdNo", data.get(position).getPrdNo());
                intent.putExtra("prdName", data.get(position).getPrdName());
                v.getContext().startActivity(intent);

                Toast.makeText(v.getContext(), "상세보기 페이지 이동", Toast.LENGTH_SHORT).show();
            }
        });






    }
    // 리뷰 삭제
    private void connectDeleteReview(){
        try {
            CUDNetworkTask eleteReviewnetworkTask = new CUDNetworkTask(mContext, urlAddrDel);
            eleteReviewnetworkTask.execute().get();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }



    // ProductHolder
    public class ProductHolder extends RecyclerView.ViewHolder {

        // item_myreview.xml 선언
        WebView myReview_img;
        protected TextView myReview_prdBrand; // 나의 리뷰 상품 브랜드
        protected TextView myReview_prdName; // 나의 리뷰 상품 이름
        protected TextView myReview_star;   // 나의 리뷰 별점
        protected TextView myReview_content;   // 나의 리뷰 내용

        ImageButton review_update;  // 수정 화면으로 이동 시키기
        ImageButton review_delete;  // 삭제 시키기

        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            myReview_img = itemView.findViewById(R.id.myReview_img);
            myReview_prdBrand = itemView.findViewById(R.id.myReview_prdBrand);
            myReview_prdName = itemView.findViewById(R.id.myReview_prdName);
            myReview_star = itemView.findViewById(R.id.myReview_star);
            myReview_content = itemView.findViewById(R.id.myReview_content);

            review_update = itemView.findViewById(R.id.btn_review_update);
            review_delete = itemView.findViewById(R.id.review_delete);

            // WebView 세팅
            // Web Setting
            WebSettings webSettings = myReview_img.getSettings();
            webSettings.setJavaScriptEnabled(true); // 자바 스크립트는 쓰겠다.
            webSettings.setBuiltInZoomControls(true); // 확대 축소 기능
            webSettings.setDisplayZoomControls(false); // 돋보기 없애기
            myReview_img.setBackgroundColor(Color.TRANSPARENT);  // webview의 배경 투명으로 전환

            // 버튼 눌렀을때 삭제시킴
            review_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    userEmail = data.get(getAdapterPosition()).getUser_userEmail();
                    ordNo = data.get(getAdapterPosition()).getOrderNo();

                    ordName = data.get(getAdapterPosition()).getPrdName();
                    Toast.makeText(v.getContext(), ordName + "의 리뷰가 삭제되었습니다.", Toast.LENGTH_SHORT).show();

                    urlAddrDel = "http://" + ShareVar.macIP + ":8080/JSP/MyReview_Delete.jsp?userEmail=" + userEmail
                            + "&ordNo=" + ordNo;
                    connectDeleteReview();

                    data.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    notifyItemChanged(getAdapterPosition());
                }
            });


            // 수정화면으로 이동
            review_update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentU = new Intent(v.getContext(), ReviewUpdateActivity.class);
                    intentU.putExtra("prdName", data.get(getAdapterPosition()).getPrdName());
                    intentU.putExtra("prdBrand", data.get(getAdapterPosition()).getPrdBrand());
                    intentU.putExtra("prdFilename", data.get(getAdapterPosition()).getPrdFilename());
                    intentU.putExtra("orderNo", String.valueOf(data.get(getAdapterPosition()).getOrderNo()));
                    intentU.putExtra("ordReview", data.get(getAdapterPosition()).getOrdReview());
                    intentU.putExtra("ordStar", data.get(getAdapterPosition()).getOrdStar());

                    v.getContext().startActivity(intentU);

                    Toast.makeText(v.getContext(), "수정 페이지 이동", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
