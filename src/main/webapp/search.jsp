<%@ page import="contentprovider.bookquery.BookQueryProviderFactory" %>
<%@ page import="model.IBook" %>
<%@ page import="java.util.List" %>
<%@ page import="model.IQueryResult" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String query = request.getParameter("q");
%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="Search result for <%=query%>">
    <meta name="author" content="James Ji">

    <%@include file="components/fav-icon-fragment.jsp" %>

    <title>Search result for <%=query%>
    </title>

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/static/styles/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/styles/search.css" rel="stylesheet"/>
</head>
<body>
<%@include file="components/header.jsp" %>

<div class="container">
    <% IQueryResult result = BookQueryProviderFactory.getProvider().queryForBooks(query, 0, 20); %>

    <!-- Search result headings -->
    <h1>Search results for <%=query%>
    </h1>
    <% if (result == null || result.getTotal() == 0) { %>
    <div class="alert alert-danger" role="alert">
        No results found for your query "<%=query%>". Maybe try with another keyword?
    </div>
    <% } else { %>
    <div class="alert alert-success" role="alert">
        Showing first <b><%=result.getCount()%>
    </b> of a total of <b><%=result.getTotal()%>
    </b> results.
    </div>

    <!-- The results -->
    <%
        List<? extends IBook> bookList = result.getBookList();
        for (IBook iBook : bookList) {
    %>
    <div class="row search-result">
        <div class="col-sm-3">
            <a href="${pageContext.request.contextPath}/book/details?id=<%=iBook.getId()%>">
                <img class="book-cover" src="<%=iBook.getCoverUrl()%>" alt="Book cover"/>
            </a>
        </div>
        <div class="col-sm-9">
            <h3>
                <a href="${pageContext.request.contextPath}/book/details?id=<%=iBook.getId()%>">
                    <%=iBook.getTitle()%>
                </a>
            </h3>
            <p><b>Author: </b><%=iBook.getAuthor()%>
            </p>
            <p><b>ISBN: </b><%=iBook.getIsbn()%>
            </p>
            <p><b>Publisher: </b><%=iBook.getImprint()%>
            </p>
            <p><b>Price: </b><%=iBook.getPrice()%>
            </p>
        </div>
    </div>

    <% }
    } %>
</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>
</html>
