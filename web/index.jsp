<%@ page import="config.Config" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Book" %>
<%@ page import="contentprovider.MockArrivalBookListProvider" %>
<%@ page import="contentprovider.BookListProviderFactory" %><%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%! List<Book> bookList; %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="BiliFun Cinemas">
    <meta name="author" content="James Ji">
    <%--<link rel="icon" href="../../favicon.ico">--%>

    <title><%=Config.CINEMA_NAME%>
    </title>

    <!-- Bootstrap core CSS -->
    <link href="./styles/bootstrap.css" rel="stylesheet">

    <link href="./styles/index.css" rel="stylesheet">

    <script src="https://use.fontawesome.com/6a66321c71.js"></script>
</head>

<body>

<!-- Fixed navbar -->
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#"><%=Config.CINEMA_NAME%>
            </a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Home</a></li>
                <li><a href="#">Now showing</a></li>
                <li><a href="#">Upcoming</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">More<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">About</a></li>
                        <li role="separator" class="divider"></li>
                        <li class="dropdown-header">Tech support</li>
                        <li><a href="#">API</a></li>
                    </ul>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">Login</a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>

<div class="container">
    <div class="new-arrivals">
        <h1>New Arrivals</h1>

        <!-- The new-arrival cards below -->
        <div class="row">
            <%
                if (bookList == null) {
                    bookList = BookListProviderFactory.getArrivalProvider().provideBookList();
                }
                for (int i = 0; i < bookList.size(); i += 1) {
                    Book book = bookList.get(i);
            %>
            <div class="new-arrival-card col-md-4 col-xs-12 <%if (i >= 3) {%>hidden<%}%>">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <b><%=book.getTitle()%>
                        </b>
                    </div>
                    <div class="panel-body">
                        <img src="<%=book.getCoverUrl()%>" alt="Tatami Chika"/>
                        <div class="content-description"><%=book.getSummary()%>
                        </div>
                        <div class="row action-button-group">
                            <div class="action-button col-sm-6 col-xs-12"><a class="fa fa-cart-plus"
                                                                             aria-hidden="true"></a>
                                Add to cart
                            </div>
                            <div class="action-button col-sm-6 col-xs-12"><a class="fa fa-info-circle"
                                                                             aria-hidden="true"></a> Details
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <%
                }
            %>
        </div><!-- Those new-arrival cards above -->
        
    </div>

</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
    $(document).ready(function () {
        $(".content-description").text(function (index, text) {
            if (text.length < 200) {
                return text;
            } else {
                return text.substring(0, 200) + "...";
            }
        })
    });
</script>
</body>
</html>
