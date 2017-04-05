<%-- 
    Document   : login
    Created on : Mar 20, 2017, 3:23:01 PM
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

        <title><%=Config.SIGNIN_TITLE%></title>

        <!-- Bootstrap core CSS -->
        <link href="./styles/bootstrap.css" rel="stylesheet">
        <link href="./styles/book-details.css" rel="stylesheet">

        <script src="https://use.fontawesome.com/6a66321c71.js"></script>
    </head>
    <body>
        <%@include file="components/header.jsp" %>
        
        <div class="container">
            <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <form class="form-signin">
                    <h2 class="form-signin-heading"><%=Config.SIGNIN_TITLE%></h2>
                    <br>
                    <div class="form-group">
                        <label for="inputEmail" ><%=Config.SIGNIN_EMAIL_LABEL%></label>
                        <input type="email" id="inputEmail" class="form-control" placeholder="email@example.com" required="" autofocus="">
                    </div>
                    <div class="form-group">
                        <label for="inputPassword" ><%=Config.SIGNIN_PASSWORD_LABEL%></label>
                        <input type="password" id="inputPassword" class="form-control" placeholder="password" required="">
                    </div>
                    <div class="checkbox">
                        <label>
                            <input type="checkbox" value="remember-me"> <%=Config.SIGNIN_REMEMBERME_CHECKBOX%>
                        </label>
                    </div>
                    <button class="btn btn-default btn-block" type="submit"><%=Config.SIGNIN_SIGNIN_BUTTON%></button>
                    <a class="btn btn-default btn-block" href="sign-up.jsp" role="button"><%=Config.SIGNIN_SIGNUP_BUTTON%></a>
                </form>
            </div>
            </div>
        </div>
    </body>
</html>
