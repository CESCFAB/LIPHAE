<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    request.setCharacterEncoding("utf-8");
	String goods_prdNo = request.getParameter("goods_prdNo");

	String url_mysql = "jdbc:mysql://localhost/one?serverTimezone=Asia/Seoul&characterEncoding=utf8&useSSL=false";
 	String id_mysql = "root";
 	String pw_mysql = "qwer1234";
    String WhereDefault = "select o.user_userEmail, o.orderNo, o.goods_prdNo, o.ordReview, o.ordStar, u.userFilename, u.userName, u.userEmail from orderdetail o, user u";

    String Condition = " where o.user_userEmail = u.userEmail and o.goods_prdNo = '" + goods_prdNo + "'";
    int count = 0;
    
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
        Statement stmt_mysql = conn_mysql.createStatement();

        ResultSet rs = stmt_mysql.executeQuery(WhereDefault + Condition); // 
%>
		{ 
  			"review_select"  : [ 
<%
        while (rs.next()) {
            if (count == 0) {

            }else{
%>
            , 
<%
            }
%>            
			
			{
			"user_userEmail" : "<%=rs.getString(1) %>",  
			"orderNo" : "<%=rs.getString(2) %>",   
			"goods_prdNo" : "<%=rs.getString(3) %>", 
			"ordReview" : "<%=rs.getString(4) %>",
			"ordStar" : "<%=rs.getString(5) %>",
			"userFilename" : "<%=rs.getString(6) %>",
			"userName" : "<%=rs.getString(7) %>",
			"userEmail" : "<%=rs.getString(8) %>"
			}

<%		
        count++;
        }
%>
		  ] 
		} 
<%		
        conn_mysql.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
	
%>
