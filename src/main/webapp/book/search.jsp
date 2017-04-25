<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="query" type="java.lang.String"--%>
<%--@elvariable id="result" type="model.IQueryResult"--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="Search result for ${query}">
    <meta name="author" content="James Ji">

    <%@include file="/components/fav-icon-fragment.jsp" %>

    <title>Search result for ${query}
    </title>

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/static/styles/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/styles/search.css" rel="stylesheet"/>
</head>
<body>
<%@include file="../components/header.jsp" %>

<div class="container">
    <!-- Search result headings -->
    <h1>Search results for ${query}
    </h1>

    <c:choose>
        <c:when test="${empty result or result.total == 0}">
            <div class="alert alert-danger" role="alert">
                No results found for your query ${query}. Maybe try with another keyword?
            </div>
        </c:when>
        <c:otherwise>
            <div class="alert alert-success" role="alert">
                <c:choose>
                    <c:when test="${result.start == 0}">
                        Showing first <b>${result.count}</b> of a total of <b>${result.total}</b> results.
                    </c:when>
                    <c:otherwise>
                        Showing records <b>${result.start + 1}</b> - <b>${result.start + result.count}</b>
                        of a total of <b>${result.total}</b> results.
                    </c:otherwise>
                </c:choose>
            </div>
        </c:otherwise>
    </c:choose>

    <!-- The results -->

    <c:forEach var="iBook" items="${result.bookList}">
        <div class="row search-result">
            <div class="col-sm-3">
                <a href="${pageContext.request.contextPath}/book/details?id=${iBook.id}">
                    <img class="book-cover" src="${iBook.coverUrl}" alt="Book cover"/>
                </a>
            </div>
            <div class="col-sm-9">
                <h3>
                    <a href="${pageContext.request.contextPath}/book/details?id=${iBook.id}">
                            ${iBook.title}
                    </a>
                </h3>
                <p><b>Author: </b>${iBook.author}
                </p>
                <p><b>ISBN: </b>${iBook.isbn}
                </p>
                <p><b>Publisher: </b>${iBook.imprint}
                </p>
                <p><b>Price: </b>${iBook.price}
                </p>
            </div>
        </div>
    </c:forEach>

    <%--@elvariable id="nextPage" type="java.lang.String"--%>
    <%--@elvariable id="previousPage" type="java.lang.String"--%>
    <c:if test="${not empty previousPage or not empty nextPage}">
        <nav>
            <ul class="pager">
                <li class="<c:if test="${empty previousPage}">disabled</c:if>">
                    <a href="${previousPage}">Previous</a>
                </li>
                <li class="<c:if test="${empty nextPage}">disabled</c:if>">
                    <a href="${nextPage}">Next</a>
                </li>
            </ul>
        </nav>
    </c:if>

</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>
</html>
