/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.synchronizertokenpattern;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Sewmi
 */
public class Login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
  

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
  

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { 
//Generate a session ID 
        String sessionid = UUID.randomUUID().toString();
//store the SID as a cookie in the web browser
        Cookie cookie = new Cookie("sessionid", sessionid);
        cookie.setMaxAge(3600);
        cookie.setSecure(false);
        response.addCookie(cookie);
        service(request, response);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        try {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
//request Auto generated cookies in the browser
            Cookie[] cookies = req.getCookies();
            cookies[0].setPath("/");

            if (username.equals("admin") && password.equals("admin")) {

                HttpSession session = req.getSession();
                session.setAttribute("username", username);
                TokenDB.getStorage().addCookie(cookies[0].getValue(), generateCSRFToken());
                System.out.println("Inside Login CSRF token =" + TokenDB.getStorage().getCookie(cookies[0].getValue()));
                TokenDB.getStorage().printCookie();
                resp.sendRedirect("homePage.jsp");
            } else {
                out.println("Invalid username or password. Username: admin Password: admin");
            }
        } finally {
            out.close();
        }
    }
//Generates the long Random CSRF Token
    private static String generateCSRFToken() {
        String CSRFToken = null;
        byte[] bytes = new byte[16];
        try {
            SecureRandom secureRandom = SecureRandom.getInstanceStrong();
            secureRandom.nextBytes(bytes);
            CSRFToken = new String(Base64.getEncoder().encode(bytes));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return CSRFToken;
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
   

}
