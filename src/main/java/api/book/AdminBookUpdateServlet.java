package api.book;

import api.user.UserManager;
import api.book.DatabaseHelper;
import contentprovider.bookdetail.BookDetailProviderFactory;
import model.IBook;
import model.SQLBookImpl;
import model.IAdmin;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;





public class AdminBookUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	IAdmin sessionUser = UserManager.getManager().getAdminSessionUser(request);
        if (sessionUser == null) {
            request.getRequestDispatcher("/admin/signin.jsp").forward(request, response);
            return;
        }

        String errorMessage = new String("");

        int inputEdit = -1;
        int inputId = -1;

        if (request.getParameter("edit") != null)
            inputEdit = Integer.parseInt(request.getParameter("edit"));
        if (request.getParameter("id") != null)
            inputId = Integer.parseInt(request.getParameter("id"));


        if ((inputId != -1) && (inputEdit != -1))
        {
        	
        	if (inputEdit == 0)
        	{

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
		        	SQLBookImpl iBook = new SQLBookImpl(inputTitle, inputPublisher, inputSummary, inputAuthor, inputISBN, inputCover, inputPrice, Integer.toString(inputId));
		        	DatabaseHelper.getInstance().updateBook(iBook);
		        	request.setAttribute("successMessage", "One book updated.");
		        }
		        else
		        {
		            request.setAttribute("errorMessage", errorMessage);
		        }
		        request.getRequestDispatcher("/admin/book_view.jsp").forward(request, response);

        	}
        	else if (inputEdit == 1)
        	{
	        	IBook iBook = BookDetailProviderFactory.getProvider().provideBookDetails(Integer.toString(inputId));
		        if (iBook == null) 
		        {
		            response.sendError(400, "Illegal book ID. ");
		            return;
		        }
		        request.setAttribute("book", iBook);
		        request.getRequestDispatcher("/admin/book_update.jsp").forward(request, response);
	    	}
	    	else
	    	{
	    		request.setAttribute("errorMessage", "Some error occurred, please contact customer service for help.");
            	request.getRequestDispatcher("/admin/book_view.jsp").forward(request, response);
	    	}
        }
        else
        {
        	request.setAttribute("errorMessage", "Some error occurred, please contact customer service for help.");
            request.getRequestDispatcher("/admin/book_view.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
