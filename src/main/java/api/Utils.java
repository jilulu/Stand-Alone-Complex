package api;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by James Ji on 07/03/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
public class Utils {
    private Utils() {

    }

    public static String appendParamsToURL(Map<String, String> params, String url) {
        StringBuilder builder = new StringBuilder(url);
        builder.append("?");
        for (Map.Entry<String, String> entry : params.entrySet()) {
            builder.append(entry.getKey())
                    .append("=")
                    .append(entry.getValue())
                    .append("&");
        }
        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

    public static double getFirstNumberInString(String s) {
        try {
            // in case of 8,888 HKD, convert to 8888 HKD
            s = s.replace(",", "");

            int numberStart = -1, numberEnd = -1;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (Character.isDigit(c)) {
                    // mark start position of a number
                    if (numberStart == -1) {
                        numberStart = i;
                    }
                    // always update end position of a number
                    numberEnd = i + 1;
                } else if (numberStart >= 0 && numberEnd > 0 && c != '.') {
                    break;
                }
            }
            if (numberStart == -1 || numberEnd == -1) {
                return 0;
            }
            String substring = s.substring(numberStart, numberEnd);
            return Double.parseDouble(substring);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void forwardSignInPageWithSelfRedirect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder callbackUrl = new StringBuilder(request.getRequestURL());
        String query = request.getQueryString();
        if (query != null && query.length() > 0) {
            callbackUrl.append("?").append(query);
        }
        String callbackUrlEncoded = encodeURL(callbackUrl.toString());
        String redirectUrl = response.encodeRedirectURL("/user/signin?redirect=" + callbackUrlEncoded);
        response.sendRedirect(redirectUrl);
    }

    static String encodeURL(String url) {
        try {
            return java.net.URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decodeURL(String encodedUrl) {
        try {
            return java.net.URLDecoder.decode(encodedUrl, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
