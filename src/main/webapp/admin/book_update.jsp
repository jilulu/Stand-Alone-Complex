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
    <li><a href="${pageContext.request.contextPath}/admin/book_view.jsp">View Books</a></li>
    <li class="active">Update Book</li>
</ol>

<h2>Update Book</h2>
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
                
    <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/admin/book/update?edit=0&id=${book.id}">
        <div class="form-group">
            <label for="inputTitle" class="col-sm-2 control-label">*Title</label>
            <div class="col-sm-8">
                <input type="text" class="form-control" id="inputTitle" name="inputTitle" placeholder="Pete the Cat: Big Easter Adventure" value="${book.title}">
            </div>
        </div>
        <div class="form-group">
            <label for="inputAuthor" class="col-sm-2 control-label">Author</label>
            <div class="col-sm-8">
                <input type="text" class="form-control" id="inputAuthor" name="inputAuthor" placeholder="James Dean, Kimberly Dean" value="${book.author}">
            </div>
        </div>
        <div class="form-group">
            <label for="inputPublisher" class="col-sm-2 control-label">Publisher</label>
            <div class="col-sm-8">
                <input type="text" class="form-control" id="inputPublisher" name="inputPublisher" placeholder="Harper Collins, 2014" value="${book.imprint}">
            </div>
        </div>
        
        <div class="form-group">
            <label for="inputSummary" class="col-sm-2 control-label">Summary</label>
            <div class="col-sm-8">
                <textarea class="form-control" rows="5" id="inputSummary" name="inputSummary" placeholder="The book summary..." value="${book.summary}"></textarea>
            </div>
        </div>
        <div class="form-group">
            <label for="inputISBN" class="col-sm-2 control-label">ISBN</label>
            <div class="col-sm-8">
                <input type="text" class="form-control" id="inputISBN" name="inputISBN" placeholder="0062198688, 9780062198686" value="${book.isbn}">
            </div>
        </div>

        <div class="form-group">
            <label for="inputCover" class="col-sm-2 control-label">Cover URL</label>
            <div class="col-sm-8">
                <input type="url" class="form-control" id="inputCover" name="inputCover" placeholder="www.abc.com" value="${book.coverUrl}">
            </div>
        </div>

        <div class="form-group">
            <label for="inputPrice" class="col-sm-2 control-label">*Price</label>
            <div class="col-sm-8">
                <input type="number" class="form-control" id="inputPrice" name="inputPrice" placeholder="20" value="${book.price}">
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">Update</button>
            </div>
        </div>
    </form>
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
