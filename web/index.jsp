<%@ page import="config.Config" %><%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <link href="styles/index.css" rel="stylesheet">
</head>

<body>

<!-- Fixed navbar -->
<nav class="navbar navbar-default navbar-fixed-top">
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

    <!-- Main component for a primary marketing message or call to action -->
    <div class="jumbotron">
        <div class="container">
            <div class="col-lg-4 col-xs-12">
                <div id="slideshow">
                    <img src="https://t0.gstatic.com/images?q=tbn:ANd9GcQGfPCb6cq9-LOVKpZsf9Vjitl7dNbS0ZKPea_qXUBwwskkoueQ">
                    <%--<div>--%>
                    <%--<img src="http://farm6.static.flickr.com/5230/5638093881_a791e4f819_m.jpg">--%>
                    <%--</div>--%>
                    <%--<div>--%>
                    <%--Pretty cool eh? This slide is proof the content can be anything.--%>
                    <%--</div>--%>
                </div>
            </div>
            <div class="col-lg-8 col-xs-12">
                <h1><%=Config.CINEMA_NAME%>
                </h1>
            </div>
        </div>
    </div>

</div> <!-- /container -->


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<%--<script src="js/index.js"></script>--%>
</body>
</html>
