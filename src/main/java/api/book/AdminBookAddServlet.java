package api.book;

import contentprovider.bookdetail.BookDetailProviderFactory;
import model.SQLBookImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AdminBookAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String inputTitle = new String("");
        String inputAuthor = new String("");
        String inputPublisher = new String("");
        String inputSummary = new String("");
        String inputISBN = new String("");
        String inputCover = new String("");
        String inputPrice = new String("");
        
        if (request.getParameter("inputTitle") != null)
            inputTitle = request.getParameter("inputTitle");
        if (request.getParameter("inputAuthor") != null)
            inputAuthor = request.getParameter("inputAuthor");
        if (request.getParameter("inputPublisher") != null)
            inputPublisher = request.getParameter("inputPublisher");
        if (request.getParameter("inputSummary") != null)
            inputSummary = request.getParameter("inputSummary");
        if (request.getParameter("inputISBN") != null)
            inputISBN = request.getParameter("inputISBN");
        if (request.getParameter("inputCover") != null)
            inputCover = request.getParameter("inputCover");
        if (request.getParameter("inputPrice") != null)
            inputPrice = request.getParameter("inputPrice");

        String errorMessage = new String("");
        boolean inputPass = true;

        if (inputTitle.equals(""))
        {
            errorMessage += "Title is missing.<br>";
            inputPass = false;
        }
        if (inputPrice.equals(""))
        {
            errorMessage += "Price is missing.";
            inputPass = false;
        }

        if (inputPass)
        {
            SQLBookImpl iBook = new SQLBookImpl(inputTitle, inputPublisher, inputSummary, inputAuthor, inputISBN, inputCover, inputPrice, "");
            DatabaseHelper.getInstance().insertBook(iBook);
            request.setAttribute("successMessage", "One book added.");
        }
        else
        {
            request.setAttribute("errorMessage", errorMessage);
        }

        request.getRequestDispatcher("/admin/book_add.jsp").forward(request, response);        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
