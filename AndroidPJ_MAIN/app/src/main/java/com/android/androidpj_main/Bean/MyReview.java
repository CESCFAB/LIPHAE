package com.android.androidpj_main.Bean;


// 21.01.26 지은 추가  ***************************************
// orderdetail 테이블에서 불러옴
public class MyReview {

    //Field (필드) = Bean 에선 이렇게 한줄 씩 쓰는 것이 좋다.
    String user_userEmail;
    int prdNo;
    int orderNo;
    int goods_prdNo;
    String prdFilename;
    String prdBrand;
    String prdName;
    String ordReview;
    String ordStar;


    // Constructor (생성자)

    public MyReview(String user_userEmail, int prdNo, int orderNo, int goods_prdNo, String prdFilename, String prdBrand, String prdName, String ordReview, String ordStar) {
        this.user_userEmail = user_userEmail;
        this.prdNo = prdNo;
        this.orderNo = orderNo;
        this.goods_prdNo = goods_prdNo;
        this.prdFilename = prdFilename;
        this.prdBrand = prdBrand;
        this.prdName = prdName;
        this.ordReview = ordReview;
        this.ordStar = ordStar;
    }

    public String getUser_userEmail() {
        return user_userEmail;
    }

    public void setUser_userEmail(String user_userEmail) {
        this.user_userEmail = user_userEmail;
    }

    public int getPrdNo() {
        return prdNo;
    }

    public void setPrdNo(int prdNo) {
        this.prdNo = prdNo;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public int getGoods_prdNo() {
        return goods_prdNo;
    }

    public void setGoods_prdNo(int goods_prdNo) {
        this.goods_prdNo = goods_prdNo;
    }

    public String getPrdFilename() {
        return prdFilename;
    }

    public void setPrdFilename(String prdFilename) {
        this.prdFilename = prdFilename;
    }

    public String getPrdBrand() {
        return prdBrand;
    }

    public void setPrdBrand(String prdBrand) {
        this.prdBrand = prdBrand;
    }

    public String getPrdName() {
        return prdName;
    }

    public void setPrdName(String prdName) {
        this.prdName = prdName;
    }

    public String getOrdReview() {
        return ordReview;
    }

    public void setOrdReview(String ordReview) {
        this.ordReview = ordReview;
    }

    public String getOrdStar() {
        return ordStar;
    }

    public void setOrdStar(String ordStar) {
        this.ordStar = ordStar;
    }
}
