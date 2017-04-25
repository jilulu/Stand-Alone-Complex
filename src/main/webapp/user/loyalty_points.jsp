<%--@elvariable id="loyaltyPoints" type="java.lang.Double"--%>
<%@ page import="config.Config" %>
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

    <link href="${pageContext.request.contextPath}/static/styles/base.css" rel="stylesheet">

    <script src="https://use.fontawesome.com/6a66321c71.js"></script>
</head>
<body>

<%@include file="/components/header.jsp" %>

<div class="container">
    <div class="col-sm-6 col-sm-push-3">
        <div class="panel panel-default">
            <div class="panel-body">
                <h1>
                    My Loyalty Points
                </h1>

                <hr>

                <h3>
                    The amount of loyalty points that you've accumulated is
                </h3>

                <h3 style="text-align: center; color: forestgreen;">
                    ${loyaltyPoints}
                </h3>
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
