<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>        
<%
	request.setCharacterEncoding("utf-8");
	String userEmail = request.getParameter("userEmail");
	String ordNo = request.getParameter("ordNo");	
		
//------
	String url_mysql = "jdbc:mysql://localhost/one?serverTimezone=Asia/Seoul&characterEncoding=utf8&useSSL=false";
	String id_mysql = "root";
	String pw_mysql = "qwer1234";
	int result = 0; // 삭제 확인 
	PreparedStatement ps = null;
	try{
	    Class.forName("com.mysql.jdbc.Driver");
	    Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
	    Statement stmt_mysql = conn_mysql.createStatement();
	
	String A = "update orderdetail set ordReview = null, ordStar = null";
        String B = " where user_userEmail = ? and orderNo=?";
	
	    ps = conn_mysql.prepareStatement(A+B);
	    
		ps.setString(1, userEmail);
		ps.setString(2, ordNo);
		result = ps.executeUpdate();
		
		%>
		{
			"result" : "<%=result%>"
		}

<%	
	
	    conn_mysql.close();
	} 
	
	catch (Exception e){
		%>
		{
			"result" : "<%=result%>"
		}
<%	
	    e.printStackTrace();
	}
	
%>

