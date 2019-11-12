/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.AccountService;

/**
 *
 * @author 784564
 */
public class ForgotPasswordServlet extends HttpServlet {

 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/forgot.jsp").forward(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        String email = request.getParameter("email");
        
        if (email == null || email.equals("")) {
            getServletContext().getRequestDispatcher("/WEB-INF/forgot.jsp").forward(request, response);
            return;
        }
        
        AccountService as = new AccountService();
        try {
            as.forgotPassword(email, getServletContext().getRealPath("/WEB-INF"));
        } catch (Exception ex) {
            Logger.getLogger(ForgotPasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
       getServletContext().getRequestDispatcher("/WEB-INF/forgot.jsp").forward(request, response);
    }

}
