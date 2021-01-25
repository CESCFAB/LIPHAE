package com.android.androidpj_main.Bean;


// 21.01.25 지은 추가  ***************************************
// orderdetail 테이블에서 불러옴
public class Review {

    //Field (필드) = Bean 에선 이렇게 한줄 씩 쓰는 것이 좋다.
    String user_userEmail;
    int orderNo;
    int goods_prdNo;
    String ordReview;
    String ordStar;
    String userFilename;
    String userName;
    String userEmail;

    // Constructor (생성자)

    public Review(String user_userEmail, int orderNo, int goods_prdNo, String ordReview, String ordStar, String userFilename, String userName, String userEmail) {
        this.user_userEmail = user_userEmail;
        this.orderNo = orderNo;
        this.goods_prdNo = goods_prdNo;
        this.ordReview = ordReview;
        this.ordStar = ordStar;
        this.userFilename = userFilename;
        this.userName = userName;
        this.userEmail = userEmail;
    }

    public String getUser_userEmail() {
        return user_userEmail;
    }

    public void setUser_userEmail(String user_userEmail) {
        this.user_userEmail = user_userEmail;
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

    public String getUserFilename() {
        return userFilename;
    }

    public void setUserFilename(String userFilename) {
        this.userFilename = userFilename;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}