package cs411.flightbooking.servlets;

import cs411.flightbooking.dao.FlightDao;
import cs411.flightbooking.models.Flight;

import cs411.flightbooking.dao.TicketDao;
import cs411.flightbooking.models.Ticket;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * BookFlightServlet - a servlet for connecting with the book-flight page
 */
@WebServlet(name = "BookFlightServlet", urlPatterns = {"/book-flight", "/user/book-flight"})
public class BookFlightServlet extends HttpServlet {

    /* Database dao objects */
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
        doPost(request, response);
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

        int flight_id = Integer.parseInt(request.getParameter("booked-flight-id"));
        Flight choseFlight = this.flightdao.getFlight(flight_id);
        String user_email = (String) session.getAttribute("userEmail");
        System.out.println(user_email);

        if (user_email == null || user_email.equals("")) {
            response.sendRedirect("user");
        } else {
            this.flightdao.bookFlight(choseFlight);
            this.ticketdao.insert(new Ticket(user_email, flight_id));
            response.sendRedirect("../user");
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
    }

}
