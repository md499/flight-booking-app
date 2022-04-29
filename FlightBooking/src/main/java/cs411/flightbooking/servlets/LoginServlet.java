/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package cs411.flightbooking.servlets;

import cs411.flightbooking.models.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import cs411.flightbooking.dao.UserDao;
import cs411.flightbooking.models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.core.Request;

/**
 *
 *
 * @author medha499
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private UserDao userdao;

    @Override
    public void init() {
        this.userdao = new UserDao();
    }

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
//        response.setContentType("text/html;charset=UTF-8");
//        try ( PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet LoginServlet</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
        //       }
    }

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

        HttpSession session = request.getSession();

        String role = (String) session.getAttribute("role");
        if (role != null && role.equals("admin")) {
            response.sendRedirect("http://localhost:8080/FlightBooking/admin/flight-manager");
        } else if (role != null && role.equals("user")) {
            RequestDispatcher view = request.getRequestDispatcher("user.jsp");
            view.forward(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
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
        HttpSession session = request.getSession();

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        RequestDispatcher view;

        User user = this.userdao.getUser(email);
        
        
        System.out.println(user);

        if (user != null) {
            session.setAttribute("username", user.getEmail());
            if (user.getEmail().equals("admin") && user.getPassword().equals(password)) {
                session.setAttribute("role", "admin");
                request.setAttribute("loginError", "");
                view = request.getRequestDispatcher("/admin/flight-manager");
            } else {
                if (password.equals(user.getPassword())) {
                    session.setAttribute("role", "user");

                    request.setAttribute("loginError", "");
                    view = request.getRequestDispatcher("user.jsp");
                } else {
                    request.setAttribute("loginError", "Incorrect email or password");
                    view = request.getRequestDispatcher("login.jsp");
                }
            }
        } else {
            request.setAttribute("loginError", "Incorrect email or password");
            view = request.getRequestDispatcher("login.jsp");
        }

        view.forward(request, response);
    }

    // Insert new user into the database
    //response.sendRedirect("user.jsp");
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
