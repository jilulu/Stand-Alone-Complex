<%@ page import="config.Config,java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="<%=Config.CINEMA_NAME%>">
    <meta name="author" content="James Ji">
    <%@include file="/components/fav-icon-fragment.jsp" %>

    <title><%=Config.CINEMA_NAME%>
    </title>

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/static/styles/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/styles/index.css" rel="stylesheet">

    <script src="https://use.fontawesome.com/6a66321c71.js"></script>
</head>

<body>
<%
    String session_Admin_Username = (String)session.getAttribute("admin_username");
    String session_Admin_Token = (String)session.getAttribute("admin_token");
    
    if ((session_Admin_Username == null) || (session_Admin_Token == null))
    {
        response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/admin/signin.jsp"));
    }
%>

<%@include file="/components/admin_header.jsp" %>

<div class="container">

<ol class="breadcrumb">
    <li class="active">Home</li>
</ol>

<h2>Hello <%=session_Admin_Username%>, welcome home.</h2>
<br><br>
<div>
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">Book Management</h3>
        </div>

        <div class="btn-group btn-group-justified" role="group" aria-label="...">
            <div class="btn-group" role="group">
                <a class="btn btn-default" href="${pageContext.request.contextPath}/admin/book_view.jsp">View Books</a>
            </div>
            <div class="btn-group" role="group">
                <a class="btn btn-default" href="${pageContext.request.contextPath}/admin/book_add.jsp">Add Book</a>
            </div>
            <div class="btn-group" role="group"></div>
            <div class="btn-group" role="group"></div>
        </div>
    </div>    
    <br>
    <div class="panel panel-success">
        <div class="panel-heading">
            <h3 class="panel-title">Purchase Management</h3>
        </div>

        <div class="btn-group btn-group-justified" role="group" aria-label="...">
            <div class="btn-group" role="group">
                <a class="btn btn-default" href="${pageContext.request.contextPath}/admin/refund/view">View Refund Applications</a>
            </div>
            <div class="btn-group" role="group"></div>
            <div class="btn-group" role="group"></div>
            <div class="btn-group" role="group"></div>
        </div>
    </div> 
</div>
    
</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


</body>
</html>
