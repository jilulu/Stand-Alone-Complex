<%--@elvariable id="book" type="model.IBook"--%>
<%--@elvariable id="purchase_record" type="model.IPurchaseRecord"--%>
<%@ page import="config.Config" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link href="${pageContext.request.contextPath}/static/styles/purchase_record_details.css" rel="stylesheet">
    <script src="https://use.fontawesome.com/6a66321c71.js"></script>
</head>
<body>

<%@include file="/components/header.jsp" %>

<div class="container">
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
    <h1>Purchase Record #${purchase_record.id}</h1>
    <h3>Thank you for shopping with us! Here is your order. </h3>
    <hr/>


    <div class="row">
        <div class="col-sm-8">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Book Details
                </div>
                <div class="panel-body">
                    <h3>${book.title}</h3>
                    <div class="book-detail-row">
                        <div class="col-sm-3"><b>Title</b></div>
                        <div class="col-sm-9">${book.title}</div>
                    </div>
                    <div class="book-detail-row">
                        <div class="col-sm-3"><b>Author</b></div>
                        <div class="col-sm-9">${book.author}</div>
                    </div>
                    <div class="book-detail-row">
                        <div class="col-sm-3"><b>Publisher</b></div>
                        <div class="col-sm-9">${book.imprint}</div>
                    </div>
                    <div class="book-detail-row">
                        <div class="col-sm-3"><b>ISBN</b></div>
                        <div class="col-sm-9">${book.isbn}</div>
                    </div>
                    <div class="book-detail-row">
                        <div class="col-sm-3"><b>Summary</b></div>
                        <div class="col-sm-9">${book.summary}</div>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-sm-4">
            <div class="panel panel-info">
                <div class="panel-heading">
                    Payment Details
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="book-detail-row">
                            <div class="col-sm-6"><b>Order Status</b></div>
                            <div class="col-sm-6">${purchase_record.purchaseStatusString}</div>
                        </div>
                    </div>
                    <hr/>
                    <div class="row">
                        <div class="book-detail-row">
                            <div class="col-sm-6"><b>Amount Paid</b></div>
                            <div class="col-sm-6">${purchase_record.price * purchase_record.quantity}</div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="book-detail-row">
                            <div class="col-sm-6"><b>Paid by</b></div>
                            <div class="col-sm-6">${purchase_record.paymentMethodString}</div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="book-detail-row">
                            <div class="col-sm-6"><b>Quantity</b></div>
                            <div class="col-sm-6">${purchase_record.quantity}</div>
                        </div>
                    </div>

                </div>
            </div>
            <div class="panel panel-info">
                <div class="panel-heading">
                    More Action
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="book-detail-row">
                            <form method="post" action="${pageContext.request.contextPath}/user/purchaseaction">
                                <input type="hidden" name="purchaseId" value="${purchase_record.id}"/>
                                <div class="col-sm-6">
                                    <select class="form-control" id="actionId" name="actionId">
                                        <option value="1" selected="selected">Refund</option>
                                    </select>
                                </div>
                                <div class="col-sm-6">
                                    <button type="submit" class="btn btn-default">Confirm</button>
                                    
                                </div>
                            </form>
                        </div>
                    </div>
                    

                </div>
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
