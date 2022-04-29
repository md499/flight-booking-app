package cs411.flightbooking.servlets;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import cs411.flightbooking.dao.UserDao;
import cs411.flightbooking.models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpSession;

/**
 * LoginServlet - a servlet for connecting with the login page
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    /* Database DAO objects */
    private UserDao userdao;

    @Override
    public void init() {
        this.userdao = new UserDao();
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
        RequestDispatcher view = null;

        User user = this.userdao.getUser(email);

        if (user != null) {
            session.setAttribute("userEmail", user.getEmail());
            if (user.getEmail().equals("admin") && user.getPassword().equals(password)) {
                session.setAttribute("role", "admin");
                request.setAttribute("loginError", "");
                response.sendRedirect("./admin/flight-manager");
            } else {
                if (password.equals(user.getPassword())) {
                    session.setAttribute("role", "user");

                    request.setAttribute("loginError", "");
                    response.sendRedirect("user");
                } else {
                    request.setAttribute("loginError", "Incorrect email or password");
                    view = request.getRequestDispatcher("login.jsp");
                }
            }
        } else {
            request.setAttribute("loginError", "Incorrect email or password");
            view = request.getRequestDispatcher("login.jsp");
        }

        if (view != null) {
            view.forward(request, response);
        }

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
