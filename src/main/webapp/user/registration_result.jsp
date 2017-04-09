<%@ page import="config.Config" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="<%=Config.CINEMA_NAME%>">
    <meta name="author" content="James Ji">
    <%@include file="../components/fav-icon-fragment.jsp" %>
    <%
        String title = null, username = null, errorMessage = null;
        boolean success = Boolean.parseBoolean(request.getParameter("success"));
        if (success) {
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    username = cookie.getValue();
                    title = "Registration successful";
                    break;
                }
            }
        } else {
            title = "Registration failed";
            errorMessage = request.getParameter("error_message");
        }
    %>
    <title><%=title == null ? "" : title%>
    </title>

    <!-- Bootstrap core CSS -->
    <link href="../static/styles/bootstrap.css" rel="stylesheet">

    <link href="../static/styles/base.css" rel="stylesheet">

    <script src="https://use.fontawesome.com/6a66321c71.js"></script>
</head>
<body>

<%@include file="../components/header.jsp" %>

<%if (success) {%>
<div class="container">
    <div class="jumbotron">
        <h1>Registration successful! </h1>
        <h2>Welcome to <%=Config.CINEMA_NAME_ANNOTATED%>, <%=username%>. </h2>
        <h2><a href="${pageContext.request.contextPath}/user/signin">Click here</a> to log in</h2>
    </div>
</div>
<%} else {%>
<div class="container">
    <div class="jumbotron">
        <h1>Registration failed</h1>
        <h2>
            <%=errorMessage%>,
            <a href="${pageContext.request.contextPath}/user/sign_up.jsp">Click here</a> to try again
        </h2>
    </div>
</div>
<%}%>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>