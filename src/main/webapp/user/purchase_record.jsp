<%--@elvariable id="username" type="java.lang.String"--%>
<%--@elvariable id="recordList" type="java.util.List<model.IPurchaseRecord>"--%>
<%--@elvariable id="purchaseRecordTitleMap" type="java.util.Map<model.IPurchaseRecord, java.lang.String>"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <link href="${pageContext.request.contextPath}/static/styles/purchase_record.css" rel="stylesheet">

    <script src="https://use.fontawesome.com/6a66321c71.js"></script>
</head>
<body>
<%@include file="/components/header.jsp" %>

<div class="container">
    <div class="jumbotron">
        <h1>My Books</h1>
        <h2>${username}, welcome to <%=Config.CINEMA_NAME_ANNOTATED%>!
            Here are all the books you ordered from us. </h2>
    </div>

    <c:choose>
        <c:when test="${purchaseRecordTitleMap != null && purchaseRecordTitleMap.size() > 0}">
            <table class="table table-striped table-hover">
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

                <c:forEach items="${recordList}" var="record">
                    <tr class="purchase-record" id="${record.id}">
                        <td>${record.id}
                        </td>
                        <td>${purchaseRecordTitleMap.get(record)}
                        </td>
                        <td>${record.quantity}
                        </td>
                        <td>${record.purchaseStatusString}
                        </td>
                        <td>${record.paymentMethodString}
                        </td>
                        <td>${record.price}
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <div class="alert alert-danger" role="alert">
                <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                <span class="sr-only">Error:</span>
                We are encountering some error delivering your purchase records.
            </div>
        </c:otherwise>
    </c:choose>
</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script>
    $(function () {
        var record = $(".purchase-record");
        record.click(function () {
            var purchaseId = this.id;
            window.location.href = "${pageContext.request.contextPath}/user/purchase/" + purchaseId;
        });
    })
</script>

</body>
</html>
