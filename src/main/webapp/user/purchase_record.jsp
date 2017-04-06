<%@ page import="api.purchaserecord.PurchaseRecordManager" %>
<%@ page import="api.user.UserManager" %>
<%@ page import="model.IPurchaseRecord" %>
<%@ page import="model.IUser" %>
<%@ page import="java.util.Collection   " %>
<%@ page import="java.util.Map" %>
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
    <title>Purchase Record</title>
    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/static/styles/bootstrap.css" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/static/styles/index.css" rel="stylesheet">

    <script src="https://use.fontawesome.com/6a66321c71.js"></script>
</head>
<body>
<%@include file="/components/header.jsp" %>

<div class="container">
    <div class="jumbotron">
        <h1>My Books</h1>
        <h2><%=request.getSession().getAttribute("username")%>, welcome to <%=Config.CINEMA_NAME_ANNOTATED%>!
            Here are all the books you ordered from us. </h2>
    </div>

    <%
        Map<IPurchaseRecord, String> purchaseRecordTitleMap = null;
        IUser sessionUser = UserManager.getManager().getSessionUser(request);
        if (sessionUser != null) {
            purchaseRecordTitleMap = PurchaseRecordManager.getManager().getUserPurchaseRecordWithTitle(sessionUser.getUserId());
        }
        if (purchaseRecordTitleMap != null && purchaseRecordTitleMap.size() > 0) {
    %>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Book ID</th>
            <th>Quantity</th>
            <th>Status</th>
            <th>Payment</th>
        </tr>
        </thead>
        <tbody>
        <%
            for (Map.Entry<IPurchaseRecord, String> purchaseRecordStringEntry : purchaseRecordTitleMap.entrySet()) {
                IPurchaseRecord iPurchaseRecord = purchaseRecordStringEntry.getKey();
                String title = purchaseRecordStringEntry.getValue();
        %>
        <tr>
            <th><%=iPurchaseRecord.getId()%>
            </th>
            <th><%=title%>
            </th>
            <th><%=iPurchaseRecord.getQuantity()%>
            </th>
            <th><%=iPurchaseRecord.getPurchaseStatusString()%>
            </th>
            <th><%=iPurchaseRecord.getPaymentMethodString()%>
            </th>
        </tr>
        <% } %>
        </tbody>
    </table>
    <% } else { %>
    <div class="alert alert-danger" role="alert">
        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
        <span class="sr-only">Error:</span>
        We are encountering some error delivering your purchase records.
    </div>
    <% } %>
</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
