<%@ page import="config.Config,java.util.List,contentprovider.booklist.BookListProviderFactory,model.IBook" %>
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
<%! private List<? extends IBook> bookList;
%>
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
    <li class="active">View Books</li>
</ol>

<h2>All Book Data</h2>
<br><br>

<div class="row">
    
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

    <!-- The new-arrival cards below -->
    <div class="table-responsive">
    	<table class="table table-striped">
    		<tr>
    			<th>#</th>
    			<th>Title</th>
    			<th>ISBN</th>
    			<th>Price</th>
    			<th></th>
    			<th></th>
    		</tr>
    <%
        if (bookList == null) 
        {
            bookList = BookListProviderFactory.getAllProvider().provideBookList();
        }
        for (int i = 0; i < bookList.size(); i += 1) 
        {
            IBook book = bookList.get(i);
    %>
			<tr>
				<th><%=book.getId()%></th>
				<th><%=book.getTitle()%></th>
				<th><%=book.getIsbn()%></th>
				<th><%=book.getPrice()%></th>
				<th><a class="btn btn-default" href="${pageContext.request.contextPath}/admin/book/update?edit=1&id=<%=book.getId()%>" role="button">Update</a></th>
				<th><a class="btn btn-default" href="${pageContext.request.contextPath}/admin/book/delete?id=<%=book.getId()%>" role="button">Delete</a></th>
			</tr>
	<%
        }
	%>	    
		</table>
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