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
 * FlightManagerServlet - a servlet for connecting with the manager page
 */
@WebServlet(name = "FlightManagerServlet", urlPatterns = {"/admin/flight-manager"})
public class FlightManagerServlet extends HttpServlet {

    /* Database DAO objects */
    private FlightDao flightdao;

    @Override
    public void init() {
        this.flightdao = new FlightDao();
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
        List<Flight> flights = flightdao.selectAllFlights();
        request.setAttribute("flight-data", flights);
        RequestDispatcher view = request.getServletContext().getRequestDispatcher("/flight-manager.jsp");
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
        String action = request.getParameter("ACTION");

        if (null != action) {
            switch (action) {
                case "add-flight": {
                    String departureLoc = request.getParameter("departureLoc");
                    String arrivalLoc = request.getParameter("arrivalLoc");

                    String departureTime = request.getParameter("departureTime");
                    String arrivalTime = request.getParameter("arrivalTime");

                    // available initially equals capacity
                    int capacity = Integer.parseInt(request.getParameter("capacity"));
                    double price = Double.parseDouble(request.getParameter("price"));
                    Flight newFlight = new Flight(departureLoc, arrivalLoc, departureTime, arrivalTime, capacity, capacity, price);
                    this.flightdao.insert(newFlight);
                    break;
                }
                case "update-flight": {
                    int flightID = Integer.parseInt(request.getParameter("flightID"));

                    String departureLoc = request.getParameter("departureLoc");
                    String arrivalLoc = request.getParameter("arrivalLoc");

                    String departureTime = request.getParameter("departureTime");
                    String arrivalTime = request.getParameter("arrivalTime");

                    int capacity = Integer.parseInt(request.getParameter("capacity"));
                    int available = capacity - Integer.parseInt(request.getParameter("num-flights-booked"));
                    double price = Double.parseDouble(request.getParameter("price"));

                    Flight newFlight = new Flight(flightID, departureLoc, arrivalLoc, departureTime, arrivalTime, capacity, available, price);
                    System.out.println(newFlight);
                    this.flightdao.update(newFlight);
                    break;
                }
                case "remove-flight": {
                    int flightID = Integer.parseInt(request.getParameter("flightID"));
                    this.flightdao.remove(flightID);
                    break;
                }
                default:
                    break;
            }
        }

        List<Flight> flights = flightdao.selectAllFlights();
        request.setAttribute("flight-data", flights);
        RequestDispatcher view = request.getServletContext().getRequestDispatcher("/flight-manager.jsp");
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
