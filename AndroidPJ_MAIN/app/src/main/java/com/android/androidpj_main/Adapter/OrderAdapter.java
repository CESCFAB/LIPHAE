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
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.androidpj_main.Activity.ProductViewActivity;
import com.android.androidpj_main.Activity.ReviewRegisterActivity;
import com.android.androidpj_main.Bean.Order;
import com.android.androidpj_main.R;
import com.android.androidpj_main.Share.ShareVar;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class OrderAdapter extends BaseAdapter {

    final static String TAG = "OrderAdapter";
    private Context mContext = null;
    private int layout = 0;
    private ArrayList<Order> data = null;
    private LayoutInflater inflater = null;
    String urlAddr;
    DecimalFormat myFormatter;

    public OrderAdapter(Context mContext, int layout, ArrayList<Order> data) {
        this.mContext = mContext;
        this.layout = layout;
        this.data = data;
        this.inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position).getOrdNo();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = inflater.inflate(this.layout, parent, false);
        }

        WebView wv_orderimg = (WebView) convertView.findViewById(R.id.wv_orderimg);
        TextView tv_ordername = (TextView) convertView.findViewById(R.id.tv_ordername);
        TextView tv_orderprice = (TextView) convertView.findViewById(R.id.tv_orderprice);
        TextView tv_orderstate = (TextView) convertView.findViewById(R.id.tv_orderstate);
        TextView tv_orderdate = (TextView) convertView.findViewById(R.id.tv_orderdate);
        Button btn_orderOK = (Button) convertView.findViewById(R.id.btn_orderOK);

        // WebView 세팅
        // Web Setting
        WebSettings webSettings = wv_orderimg.getSettings();
        webSettings.setJavaScriptEnabled(true); // 자바 스크립트는 쓰겠다.
        webSettings.setBuiltInZoomControls(true); // 확대 축소 기능
        webSettings.setDisplayZoomControls(false); // 돋보기 없애기
        wv_orderimg.setBackgroundColor(Color.TRANSPARENT);  // webview의 배경 투명

        // 상품 이미지
        urlAddr = "http://" + ShareVar.macIP + ":8080/Images/";  // Images 파일
        urlAddr = urlAddr + data.get(position).getPrdFilename(); // 경로에 이미지 이름 추가

        String htmlData = "<html>" +
                "<head>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                "</head>" +
                "<body><center>" +
                "<img src = \"" + urlAddr + "\"style=\"width: auto; height: 90%;\"" +
                "</center></body>" +
                "</html>";
        wv_orderimg.loadData(htmlData,"text/html", "UTF-8");

        myFormatter = new DecimalFormat("###,###");
        String formattedStringPrice = myFormatter.format(data.get(position).getPrdPrice());

        tv_ordername.setText(data.get(position).getPrdName());
        tv_orderprice.setText(formattedStringPrice+" 원");
        tv_orderstate.setText(data.get(position).getOrdDelivery());
        tv_orderdate.setText(data.get(position).getOrdDate());

        if(tv_orderstate.getText().equals("배송완료")){
            btn_orderOK.setVisibility(View.VISIBLE);
            btn_orderOK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btn_orderOK.setText("리뷰쓰기");
                    btn_orderOK.setBackgroundColor(0x50ff0000);

                    Intent intent = new Intent(v.getContext(), ReviewRegisterActivity.class);
                    intent.putExtra("prdFilename", data.get(position).getPrdFilename());
                    intent.putExtra("prdPrice",String.valueOf(data.get(position).getPrdPrice()));
                    intent.putExtra("prdName", data.get(position).getPrdName());
                    intent.putExtra("ordNo", String.valueOf(data.get(position).getOrdNo()));
                    v.getContext().startActivity(intent);

                    Toast.makeText(v.getContext(), "리뷰 작성 화면으로 이동", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            btn_orderOK.setVisibility(View.INVISIBLE);
        }

        return convertView;

    }




} // -----
