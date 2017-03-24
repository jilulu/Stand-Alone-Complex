<%@ page import="config.Config" %>
<%@ page import="java.util.List" %>
<%@ page import="contentprovider.booklist.BookListProviderFactory" %>
<%@ page import="model.IBook" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%! private List<? extends IBook> bookList;
    private List<? extends IBook> recommendationBookList; %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="<%=Config.CINEMA_NAME%>">
    <meta name="author" content="James Ji">
    <%@include file="components/fav-icon-fragment.jsp" %>

    <title><%=Config.CINEMA_NAME%>
    </title>

    <!-- Bootstrap core CSS -->
    <link href="./styles/bootstrap.css" rel="stylesheet">

    <link href="./styles/index.css" rel="stylesheet">

    <script src="https://use.fontawesome.com/6a66321c71.js"></script>
</head>

<body>

<%@include file="components/header.jsp" %>

<div class="container">
    <div class="recommendations">
        <h1>New Arrivals</h1>

        <!-- The new-arrival cards below -->
        <div class="row">
            <%
                if (bookList == null) {
                    bookList = BookListProviderFactory.getArrivalProvider().provideBookList();
                }
                for (int i = 0; i < bookList.size(); i += 1) {
                    IBook book = bookList.get(i);
            %>
            <div class="new-arrival-card col-md-4 col-xs-12 <%if (i >= 3) {%>hidden<%}%>" id="<%=book.getId()%>">
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
                            <div class="action-button add-cart-action-button col-sm-6 col-xs-12"><a
                                    class="fa fa-cart-plus"
                                    aria-hidden="true"></a>
                                Add to cart
                            </div>
                            <div class="action-button details-action-button col-sm-6 col-xs-12"><a
                                    class="fa fa-info-circle"
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


    <div class="recommendations">
        <h1>Recommended Readings</h1>

        <!-- The new-arrival cards below -->
        <div class="row">
            <%
                if (recommendationBookList == null) {
                    recommendationBookList = BookListProviderFactory.getRecommendationProvider().provideBookList();
                }
                for (int i = 0; i < recommendationBookList.size(); i += 1) {
                    IBook book = recommendationBookList.get(i);
            %>
            <div class="new-arrival-card col-md-4 col-xs-12 <%if (i >= 3) {%>hidden<%}%>" id="<%=book.getId()%>">
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
                            <div class="action-button add-cart-action-button col-sm-6 col-xs-12"><a
                                    class="fa fa-cart-plus"
                                    aria-hidden="true"></a>
                                Add to cart
                            </div>
                            <div class="action-button details-action-button col-sm-6 col-xs-12"><a
                                    class="fa fa-info-circle"
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
    $(function () {
        $(".content-description").text(function (index, text) {
            if (text.length < 200) {
                return text;
            } else {
                return text.substring(0, 200) + "...";
            }
        });
    });
    $(function () {
        $('.details-action-button').click(function () {
            var bookId = $(this).parents('.new-arrival-card').attr('id');
            window.location.href = "${pageContext.request.contextPath}/book-details.jsp?id=" + bookId;
        });
    })
</script>

</body>
</html>
