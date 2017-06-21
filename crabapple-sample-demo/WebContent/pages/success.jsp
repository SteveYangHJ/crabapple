<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>  
<%@ page language="java" import="com.crabapple.entity.Account"%>  
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>  
  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
<head>  
  <base href="<%=basePath%>">  
  <title>Register Success</title>  
  <meta http-equiv="pragma" content="no-cache">  
  <meta http-equiv="cache-control" content="no-cache">  
  <meta http-equiv="expires" content="0">      
  <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">  
  <meta http-equiv="description" content="This is my page">  
  <!-- 
  <link rel="stylesheet" type="text/css" href="styles.css"> 
  -->  
</head>  
  
<body>  
  <% Account account = (Account)request.getAttribute("account");  
     String username= account.getUsername();  
     String password= account.getPassword();  
   %>   
   You have inputed username:<%=username %>,password:<%=password%>  
</body>  
</html>  