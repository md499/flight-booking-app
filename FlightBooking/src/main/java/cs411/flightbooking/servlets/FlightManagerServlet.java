/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package cs411.flightbooking.servlets;

import cs411.flightbooking.dao.FlightDao;
import cs411.flightbooking.models.Flight;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author minhl
 */
@WebServlet(name = "FlightManagerServlet", urlPatterns = {"/admin/flight-manager"})
public class FlightManagerServlet extends HttpServlet {

    // initialize the connection between db and web
    private FlightDao flightdao;

    @Override
    public void init() {
        this.flightdao = new FlightDao();
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
//            out.println("<title>Servlet FlightManagerServlet</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet FlightManagerServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
        RequestDispatcher view = request.getRequestDispatcher("flight-manager.jsp");
        view.forward(request, response);
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

        if ("add-flight".equals(action)) {
            String departureLoc = request.getParameter("departureLoc");
            String arrivalLoc = request.getParameter("arrivalLoc");

            String departureTime = request.getParameter("departureTime");
            String arrivalTime = request.getParameter("arrivalTime");

            // available initially equals capacity
            int capacity = Integer.parseInt(request.getParameter("capacity"));
            double price = Double.parseDouble(request.getParameter("price"));

            Flight newFlight = new Flight(departureLoc, arrivalLoc, departureTime, arrivalTime, capacity, capacity, price);
            this.flightdao.insert(newFlight);
        } else if ("update-flight".equals(action)) {
            int flightID = Integer.parseInt(request.getParameter("flightID"));

            String departureLoc = request.getParameter("departureLoc");
            String arrivalLoc = request.getParameter("arrivalLoc");

            String departureTime = request.getParameter("departureTime");
            String arrivalTime = request.getParameter("arrivalTime");

            // available initially equals capacity
            int capacity = Integer.parseInt(request.getParameter("capacity"));
            double price = Double.parseDouble(request.getParameter("price"));

            Flight newFlight = new Flight(flightID, departureLoc, arrivalLoc, departureTime, arrivalTime, capacity, capacity, price);
            System.out.println(newFlight);
//this.flightdao.insert(newFlight);
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
    }// </editor-fold>

}
