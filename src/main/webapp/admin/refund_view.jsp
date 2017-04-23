<%@ page import="config.Config,java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <li><a href="${pageContext.request.contextPath}/admin/index.jsp">Home</a></li>
    <li class="active">View Refund Applications</li>
</ol>

<h2>All Refund Application</h2>
<br><br>

<div>
    <c:if test="${not empty successMessage}">
        <div class="alert alert-success">
            <c:out value="${successMessage}"/>
        </div>
    </c:if>
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger">
            <c:out value="${errorMessage}"/>
        </div>
    </c:if>
    <c:choose>
    <c:when test="${not empty records && records.size() > 0}">
        <table class="table table-striped table-hover">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Book ID</th>
                    <th>Quantity</th>
                    <th>Status</th>
                    <th>Payment</th>
                    <th>Price</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${records}" var="record">
                    <tr class="purchase-record" id="${record.id}">
                        <td>${record.id}</td>
                        <td>${record.bookId}</td>
                        <td>${record.quantity}</td>
                        <td>${record.purchaseStatusString}</td>
                        <td>${record.paymentMethodString}</td>
                        <td>${record.price}</td>
                        <td><a class="btn btn-default" href="${pageContext.request.contextPath}/admin/refund/approve?id=${record.id}" role="button">Approve</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:when>
    </c:choose>
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