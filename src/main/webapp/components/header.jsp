<%@ page import="config.Config" %>
<%@ page import="java.io.PrintWriter" %>
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
            <a class="navbar-brand" href="${pageContext.request.contextPath}/"><%=Config.CINEMA_NAME%>
            </a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li
                        <% if (request.getRequestURI().equals("/") || request.getRequestURI().equals("/index.jsp")) { %>
                        class="active"
                        <% } %>
                ><a href="${pageContext.request.contextPath}/">Home</a></li>
                <!-- Activate this tab only if uri matches that of the home page -->
                <%--<li><a href="#">New Arrivals</a></li>--%>
                <%--<li><a href="#">Recommended Readings</a></li>--%>
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

            <form class="navbar-form navbar-left" role="search"
                  action="${pageContext.request.contextPath}/book/search" method="post">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Search" name="q" autocomplete="off"
                           style="background-color: #3F4347; border: none; ">
                </div>
                <%--<button type="submit" class="btn btn-default">Submit</button>--%>
            </form>

            <ul class="nav navbar-nav navbar-right">
                <%
                    String usernameAttribute = (String) request.getSession().getAttribute("username");
                    String userTokenAttribute = (String) request.getSession().getAttribute("token");
                    if (usernameAttribute != null && userTokenAttribute != null) {
                %>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                       aria-haspopup="true" aria-expanded="false"><%=usernameAttribute%><span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/user/purchase">Purchase Record</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="${pageContext.request.contextPath}/user/logout">Logout</a></li>
                    </ul>
                </li>

                <% } else { %>
                <li><a href="${pageContext.request.contextPath}/user/signin">Login</a></li>
                <% } %>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>
