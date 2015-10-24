package ru.kpfu.itis.utilities;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class ServletUtilities {

    public static Cookie getCookie(HttpServletRequest req,String name){
        Cookie[] cookies = req.getCookies();
        if (cookies != null){
            for (Cookie cookie : cookies){

                if(cookie.getName().equals(name)){
                    return cookie;
                }
            }
        }
        return null;
    }


}
