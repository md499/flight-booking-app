package cs411.flightbooking.servlets;

import cs411.flightbooking.dao.FlightDao;
import cs411.flightbooking.dao.TicketDao;
import cs411.flightbooking.models.Flight;
import cs411.flightbooking.models.Ticket;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 * UserServlet - a servlet for connecting with the user page
 */
@WebServlet(name = "UserServlet", urlPatterns = {"/user"})
public class UserServlet extends HttpServlet {

    /* Database DAO objects */
    private FlightDao flightdao;
    private TicketDao ticketdao;

    @Override
    public void init() {
        this.flightdao = new FlightDao();
        this.ticketdao = new TicketDao();
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
        // processRequest(request, response);
        HttpSession session = request.getSession();

        String userEmail = (String) session.getAttribute("userEmail");
        if (userEmail == null || userEmail.equals("admin") || userEmail.equals("")) {
            response.sendRedirect("login.jsp");
            return;
        }

        List<Ticket> tickets = this.ticketdao.getTicketsForUser(userEmail);
        List< Flight> flights = this.flightdao.getFlightsFromTickets(tickets);
        request.setAttribute("flight-history", flights);
        RequestDispatcher view = request.getRequestDispatcher("user.jsp");
        view.forward(request, response);
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
        doGet(request, response);
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
