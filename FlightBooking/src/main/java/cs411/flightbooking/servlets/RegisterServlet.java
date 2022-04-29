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

/**
 * RegisterServlet - a servlet for connecting with the register page
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

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
        response.sendRedirect("register.jsp");
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

        String firstname = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new User(firstname, lastName, email, password);

        RequestDispatcher view;
        // Insert new user into the database
        if (this.userdao.insert(user) == 0) {
            request.setAttribute("registerError", "Email has already been registered");
            view = request.getRequestDispatcher("/register.jsp");
        } else {
            request.setAttribute("registerError", "");
            view = request.getRequestDispatcher("/login");
        }

        view.forward(request, response);
    }

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
