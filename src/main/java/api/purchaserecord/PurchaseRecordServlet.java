package api.purchaserecord;

import api.user.UserManager;
import model.IPurchaseRecord;
import model.IUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by jamesji on 06/04/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
public class PurchaseRecordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IUser sessionUser = UserManager.getManager().getSessionUser(request);
        if (sessionUser != null) {
            Map<IPurchaseRecord, String> purchaseRecordTitleMap =
                    PurchaseRecordManager.getManager().getUserPurchaseRecordWithTitle(sessionUser.getUserId());
            if (purchaseRecordTitleMap != null) {
                List<IPurchaseRecord> recordList = new ArrayList<IPurchaseRecord>(purchaseRecordTitleMap.keySet());
                Collections.sort(recordList, new Comparator<IPurchaseRecord>() {
                    @Override
                    public int compare(IPurchaseRecord o1, IPurchaseRecord o2) {
                        return o1.getId() - o2.getId();
                    }
                });
                request.setAttribute("purchaseRecordTitleMap", purchaseRecordTitleMap);
                request.setAttribute("recordList", recordList);
            }
            request.setAttribute("username", sessionUser.getUserName());

            request.getRequestDispatcher("/user/purchase_record.jsp")
                    .forward(request,response);
        } else {
            response.sendRedirect(response.encodeRedirectURL("/user/signin"));
        }
    }
}
