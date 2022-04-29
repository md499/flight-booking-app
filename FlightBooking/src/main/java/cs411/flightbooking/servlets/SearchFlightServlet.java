package cs411.flightbooking.servlets;

import cs411.flightbooking.dao.FlightDao;
import cs411.flightbooking.models.Flight;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * SearchFlightServlet - a servlet for connecting with the register page
 */
@WebServlet(name = "SearchFlightServlet", urlPatterns = {"/search-flight", "/user/search-flight"})
public class SearchFlightServlet extends HttpServlet {

    /* Database DAO objects */
    private FlightDao flightdao;

    @Override
    public void init() {
        this.flightdao = new FlightDao();
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
        String dLocation = request.getParameter("from");
        String aLocation = request.getParameter("to");
        String Date = request.getParameter("departureTime");

        List<Flight> flights = this.flightdao.getFlightFromSearch(dLocation, aLocation, Date + "T00:00");

        request.setAttribute("search-result", flights);
        RequestDispatcher view = request.getServletContext().getRequestDispatcher("/search-flight.jsp");
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
    }// </editor-fold>

}
