/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avn.synchronizertokenpattern;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;

/**
 *
 * @author Sewmi
 */
public class TokenValidation extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Validator</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Validator at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        String csrfToken = TokenDB.getStorage().getCookie(cookies[0].getValue());
        response.setContentType("application/json");
        JSONObject user = new JSONObject();
        user.put("csrfToken",csrfToken);
        PrintWriter printWriter =response.getWriter();
        printWriter.print(user.toString());
        printWriter.flush();
        printWriter.close();
    }

    

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
        serviceNew(request, response);
    }

    protected void serviceNew(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        try {
            String uid = req.getParameter("id");
            String pwd = req.getParameter("key");
            String CSRFTokenRecieved = req.getParameter("tokentext");
            out.println("Your ID : " + uid);
            out.println("</br>");
            
            out.println("Your Password : " + pwd);
            out.println("</br>");
            
            out.println("Your Token : " + CSRFTokenRecieved);
            out.println("</br>");
//Validates the token which user sends             
            Cookie[] cookies = req.getCookies();
            String CSRFToken = TokenDB.getStorage().getCookie(cookies[0].getValue());
            if (CSRFTokenRecieved.equals(CSRFToken)) {
                out.println("Token Verification Successful");
            } else {
                out.println("Token Verification Failed!");
            }
        } finally {
            out.close();
        }

    }
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
