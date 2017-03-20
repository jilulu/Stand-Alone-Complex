<%@ page import="config.Config" %>
<%@ page import="model.IBook" %>
<%@ page import="contentprovider.bookdetail.BookDetailProviderFactory" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String id = request.getParameter("id");
    IBook book = BookDetailProviderFactory.getProvider().provideBookDetails(id);
%>
<%!
    private final static int PURCHASE_QUANTITY_MAX = 30;
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="<%=Config.CINEMA_NAME%>">
    <meta name="author" content="James Ji">
    <%--<link rel="icon" href="../../favicon.ico">--%>

    <title><%=book.getTitle()%></title>

    <!-- Bootstrap core CSS -->
    <link href="./styles/bootstrap.css" rel="stylesheet">

    <link href="./styles/book-details.css" rel="stylesheet">

    <script src="https://use.fontawesome.com/6a66321c71.js"></script>
</head>
<body>

<%@include file="components/header.jsp" %>

<div class="container">
    <div class="row">
        <div class="book-cover col-sm-3 col-sm-12">
            <img src="<%=book.getCoverUrl()%>" alt="Book cover">
        </div>
        <div class="book-brief-info col-sm-9 col-sm-12">
            <h2><%=book.getTitle()%>
            </h2>
            <div class="row">
                <div class="col-sm-4 col-sm-push-8 purchase-card">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">Buy now</h3>
                        </div>
                        <div class="panel-body">
                            <form action="index.jsp" method="get">
                                <input type="hidden" name="id" value="<%=book.getId()%>">
                                <div class="row">
                                    <div class="col-xs-6">
                                        List price:
                                    </div>
                                    <div class="col-xs-6">
                                        <span class="book-price"><%=book.getPrice()%></span>
                                    </div>

                                    <div class="col-xs-8">Quantity:</div>
                                    <select name="quantity" autocomplete="off" title="Quantity" class="col-xs-3">
                                        <% for (int i = 0; i < PURCHASE_QUANTITY_MAX; i++) { %>
                                        <option value="<%=(i + 1)%>"
                                                <%=i == 0 ? "selected" : ""%>>
                                            <%=(i + 1)%>
                                        </option>
                                        <% } %>
                                    </select>

                                </div>

                                <div class="shipping-info">
                                    <span class="shipping-policy">FREE Shipping</span>
                                    on orders with at least
                                    <span class="book-price"><%=Config.FREE_SHIPPING_OFFSET%></span>
                                    of books.
                                </div>

                                <div class="stocking-conditions">
                                    <span>In Stock</span>
                                </div>
                                <button type="submit" class="btn btn-primary btn-lg buybuybuybutton">Buy now</button>
                            </form>
                        </div>
                    </div>
                </div>

                <div class="col-sm-8 col-sm-pull-4 book-info">
                    <h3 class="col-xs-12">
                        About the book
                    </h3>
                    <div class="col-xs-3"><b>Author</b></div>
                    <div class="col-xs-9"><%=book.getAuthor()%></div>
                    <div class="col-xs-3"><b>ISBN</b></div>
                    <div class="col-xs-9"><%=book.getIsbn()%></div>
                    <div class="col-xs-3"><b>Publisher</b></div>
                    <div class="col-xs-9"><%=book.getImprint()%></div>
                </div>
            </div>
        </div>

    </div>

    <div class="row">
        <h3>Summary of the book</h3>
        <blockquote><%=book.getSummary()%></blockquote>
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
