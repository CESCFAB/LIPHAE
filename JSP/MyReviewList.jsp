<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    request.setCharacterEncoding("utf-8");
	String userEmail = request.getParameter("userEmail");

	String url_mysql = "jdbc:mysql://localhost/one?serverTimezone=Asia/Seoul&characterEncoding=utf8&useSSL=false";
 	String id_mysql = "root";
 	String pw_mysql = "qwer1234";
    String WhereDefault = "select o.user_userEmail, p.prdNo, o.orderNo, o.goods_prdNo, p.prdFilename, p.prdBrand, p.prdName, o.ordReview, o.ordStar from orderdetail o, product p";

    String Condition = " where o.goods_prdNo = p.prdNo and o.user_userEmail = ? and o.ordReview is not null";
    int count = 0;
    PreparedStatement ps = null;
    ResultSet rs =null;
    
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
        Statement stmt_mysql = conn_mysql.createStatement();

        ps = conn_mysql.prepareStatement(WhereDefault + Condition);
        ps.setString(1, userEmail);
        rs = ps.executeQuery();
        %>  
		{ 
  			"MyReview_Select"  : [ 
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
			"prdNo" : "<%=rs.getString(2) %>",   
			"orderNo" : "<%=rs.getString(3) %>", 
			"goods_prdNo" : "<%=rs.getString(4) %>",
			"prdFilename" : "<%=rs.getString(5) %>",
			"prdBrand" : "<%=rs.getString(6) %>",
			"prdName" : "<%=rs.getString(7) %>",
			"ordReview" : "<%=rs.getString(8) %>",
			"ordStar" : "<%=rs.getString(9) %>"
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
