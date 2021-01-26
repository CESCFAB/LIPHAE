<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>        
<%
	request.setCharacterEncoding("utf-8");
	String user_userEmail = request.getParameter("userEmail");
	String userPw = request.getParameter("userPw");	
	String userName = request.getParameter("userName");	
	String userTel = request.getParameter("userTel");	
	
		
//------
	String url_mysql = "jdbc:mysql://localhost/one?serverTimezone=Asia/Seoul&characterEncoding=utf8&useSSL=false";
	String id_mysql = "root";
	String pw_mysql = "qwer1234";

	int result = 0; // 입력 확인 

	PreparedStatement ps = null;
	try{
	    Class.forName("com.mysql.jdbc.Driver");
	    Connection conn_mysql = DriverManager.getConnection(url_mysql,id_mysql,pw_mysql);
		Statement stmt_mysql = conn_mysql.createStatement();
	
	    String A = "insert into user (userEmail, userPw, userName, userTel, insertDate) values (";
	    String B = "?, ?, ?, ?, NOW())";
		
	
	    ps = conn_mysql.prepareStatement(A+B);
	    ps.setString(1, user_userEmail);
	    ps.setString(2, userPw);
		ps.setString(3, userName);
		ps.setString(4, userTel);
		
		
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

