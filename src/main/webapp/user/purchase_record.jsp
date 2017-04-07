<%@ page import="api.purchaserecord.PurchaseRecordManager" %>
<%@ page import="api.user.UserManager" %>
<%@ page import="model.IPurchaseRecord" %>
<%@ page import="model.IUser" %>
<%@ page import="java.util.*" %>
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

    <link href="${pageContext.request.contextPath}/static/styles/base.css" rel="stylesheet">

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
            <th>Title</th>
            <th>Quantity</th>
            <th>Status</th>
            <th>Payment</th>
            <th>Price</th>
        </tr>
        </thead>
        <tbody>
        <%
            // HashSet is used to prevent repeating purchase records. The interface Collection
            // doesn't guarantee preserving the original order in which the entries are added.
            // (And when the underlying class is a HashMap which uses hashing, it's almost
            // definitely true that the original order would not be preserved.)
            // Therefore it is required to sort the IPurchaseRecord's here, based on their ID's.
            List<IPurchaseRecord> recordList = new ArrayList<IPurchaseRecord>(purchaseRecordTitleMap.keySet());
            Collections.sort(recordList, new Comparator<IPurchaseRecord>() {
                @Override
                public int compare(IPurchaseRecord o1, IPurchaseRecord o2) {
                    return o1.getId() - o2.getId();
                }
            });
            for (IPurchaseRecord iPurchaseRecord : recordList) {
                String title = purchaseRecordTitleMap.get(iPurchaseRecord);
        %>
        <tr>
            <td><%=iPurchaseRecord.getId()%>
            </td>
            <td><%=title%>
            </td>
            <td><%=iPurchaseRecord.getQuantity()%>
            </td>
            <td><%=iPurchaseRecord.getPurchaseStatusString()%>
            </td>
            <td><%=iPurchaseRecord.getPaymentMethodString()%>
            </td>
            <td><%=iPurchaseRecord.getPrice()%>
            </td>
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
