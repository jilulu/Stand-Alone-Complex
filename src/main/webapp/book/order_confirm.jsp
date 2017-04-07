<%
    int bookId = Integer.parseInt(request.getParameter("bookid"));
    int quantity = Integer.parseInt(request.getParameter("quantity"));

    IBook iBook = BookDetailProviderFactory.getProvider().provideBookDetails(String.valueOf(bookId));
%>
<%@ page import="config.Config" %>
<%@ page import="model.IBook" %>
<%@ page import="contentprovider.bookdetail.BookDetailProviderFactory" %>
<%@ page import="api.purchaserecord.DatabaseHelper" %>
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

    <title>Order Confirmation
    </title>

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/static/styles/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/styles/order_confirm.css" rel="stylesheet">

    <script src="https://use.fontawesome.com/6a66321c71.js"></script>

</head>
<body>

<%@include file="/components/header.jsp" %>

<div class="container">
    <h1>Order Details</h1>

    <div class="col-sm-8">
        <div class="panel panel-default">
            <div class="panel-heading">Delivery and Payment information</div>
            <div class="panel-body" style="padding: 20px;">
                <h4>Thank you for buying from us! We need some more information to complete your order.</h4>
                <form class="form-horizontal row" action="${pageContext.request.contextPath}/book/purchase" method="post">
                    <input type="hidden" name="bookid" value="<%=bookId%>"/>
                    <div class="form-group">
                        <label for="qty" class="col-sm-2 control-label">Quantity</label>
                        <div class="col-sm-10">
                            <select class="form-control" disabled id="qty" name="quantity">
                                <option value="<%=quantity%>"><%=quantity%>
                                </option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="pmtd" class="col-sm-2 control-label">Payment Method</label>
                        <div class="col-sm-10">
                            <select class="form-control" id="pmtd" name="payment_method">
                                <% String[] definition_payment_record = DatabaseHelper.Table.DEFINITION_PAYMENT_RECORD;
                                    for (int i = 0; i < definition_payment_record.length; i++) {
                                        String method = definition_payment_record[i]; %>
                                <option value="<%=i%>"><%=method%>
                                </option>
                                <%}%>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="addrl1" class="col-sm-2 control-label">Address Line 1</label>
                        <div class="col-sm-10">
                            <input class="form-control" id="addrl1" type="text">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="addrl2" class="col-sm-2 control-label">Address Line 2</label>
                        <div class="col-sm-10">
                            <input type="text" id="addrl2" class="form-control">
                        </div>
                    </div>

                </form>
            </div>
        </div>
    </div>

    <div class="col-sm-4">
        <div class="panel panel-info">
            <div class="panel-heading">Order Summary</div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-xs-6">
                        <b>List price</b>
                    </div>
                    <div class="col-xs-6">
                        <span class="book-price"><%=iBook.getPrice()%></span>
                    </div>

                    <div class="col-xs-8"><b>Quantity</b></div>
                    <div class="col-xs-4 book-quantity"><%=quantity%>
                    </div>

                    <hr class="col-xs-12 content-divider"/>

                    <div class="col-xs-6">
                        <b>Total amount</b>
                    </div>
                    <div class="col-xs-6">
                        <span class="book-price"><%=Double.parseDouble(iBook.getPrice()) * quantity%></span>
                    </div>

                    <hr class="col-xs-12 content-divider">
                </div>

                <button type="button" class="btn btn-success buybuybuybutton col-xs-12">
                    Confirm Order
                </button>
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

<script>
    $(function() {
        $(".buybuybuybutton").click(function () {
            $("form").submit()
        });
    })
</script>
</body>
</html>
