package api.purchaserecord;


import api.user.UserManager;
import model.IPurchaseRecord;
import model.IAdmin;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class AdminRefundViewServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IAdmin sessionUser = UserManager.getManager().getAdminSessionUser(request);
        if (sessionUser == null) {
            request.getRequestDispatcher("/admin/signin.jsp")
                        .forward(request, response);
            return;
        }
        
        Collection<IPurchaseRecord> results = PurchaseRecordManager.getManager().getUserPurchaseRecordByStatus(2);
        List<IPurchaseRecord> purchaseRecords = new ArrayList<IPurchaseRecord>(results);
        Collections.sort(purchaseRecords, new Comparator<IPurchaseRecord>()
        {
            @Override
            public int compare(IPurchaseRecord o1, IPurchaseRecord o2)
            {
                return o1.getId() - o2.getId();
            }
        });
        request.setAttribute("records", purchaseRecords);
        request.getRequestDispatcher("/admin/refund_view.jsp").forward(request, response);

    }
}
