<%-- 
    Document   : sign-up
    Created on : Mar 20, 2017, 5:09:39 PM
    Author     : Kenneth_2
--%>
<%@ page import="config.Config" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

        <title><%=Config.SIGNUP_TITLE%></title>

        <!-- Bootstrap core CSS -->
        <link href="../static/styles/bootstrap.css" rel="stylesheet">

        <link href="../static/styles/book-details.css" rel="stylesheet">

        <script src="https://use.fontawesome.com/6a66321c71.js"></script>
    </head>
    <body>
        <%@include file="../components/header.jsp" %>
        
        <div class="container">
            <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <form class="form-signup" action="${pageContext.request.contextPath}/user/add" method="post">
                    <h2 class="form-signup-heading"><%=Config.SIGNUP_TITLE%></h2>
                    <br>
                    <div class="form-group">
                        <label for="inputUsername">*<%=Config.SIGNUP_USERNAME_LABEL%></label>
                        <input type="text" id="inputUsername" class="form-control" placeholder="peterpan" required="" name="username">
                        <p class="help-block"><%=Config.SIGNUP_USERNAME_HELP%></p>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword">*<%=Config.SIGNUP_PASSWORD_LABEL%></label>
                        <input type="password" id="inputPassword" class="form-control" placeholder="password" required="" name="userpassword">
                    </div>

                    <button class="btn btn-default btn-block" type="submit"><%=Config.SIGNUP_SIGNUP_BUTTON%></button>
                    
                </form>
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
