<%--@elvariable id="book" type="model.IBook"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="config.Config" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="${book.title}">
    <meta name="author" content="James Ji">
    <%@include file="/components/fav-icon-fragment.jsp" %>

    <title>${book.title}</title>

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/static/styles/bootstrap.css" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/static/styles/book-details.css" rel="stylesheet">
</head>
<body>

<%@include file="../components/header.jsp" %>

<div class="container">
    <div class="row">
        <div class="book-cover col-sm-3 col-xs-12">
            <img src="${book.coverUrl}" alt="Book cover">
        </div>
        <div class="book-brief-info col-sm-9 col-xs-12">
            <h2>${book.title}
            </h2>
            <div class="row">
                <div class="col-sm-4 col-sm-push-8 purchase-card">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">Buy now</h3>
                        </div>
                        <div class="panel-body">
                            <form action="${pageContext.request.contextPath}/book/confirm" method="get">
                                <input type="hidden" name="book_id" value="${book.id}">
                                <div class="row">
                                    <div class="col-xs-6">
                                        List price:
                                    </div>
                                    <div class="col-xs-6">
                                        <span class="book-price">${book.price}</span>
                                    </div>

                                    <div class="col-xs-8">Quantity:</div>
                                    <select name="quantity" autocomplete="off" title="Quantity" class="col-xs-3">
                                        <%--@elvariable id="purchaseQuantityMax" type="Integer"--%>
                                        <c:forEach var="i" begin="1" end="${purchaseQuantityMax}">
                                            <option value="${i}" <c:if test="${i==1}">selected</c:if>>
                                                ${i}
                                            </option>
                                        </c:forEach>
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
                    <div class="col-xs-9">${book.author}</div>
                    <div class="col-xs-3"><b>ISBN</b></div>
                    <div class="col-xs-9">${book.isbn}</div>
                    <div class="col-xs-3"><b>Publisher</b></div>
                    <div class="col-xs-9">${book.imprint}</div>
                </div>
            </div>
        </div>

    </div>

    <div class="row">
        <h3>Summary of the book</h3>
        <blockquote>${book.summary}</blockquote>
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
