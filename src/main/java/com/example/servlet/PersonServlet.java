package com.example.servlet;

import com.example.dao.PersonDAO;
import com.example.dao.PersonDAOImpl;
import com.example.model.Person;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/person")
public class PersonServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private PersonDAO personDAO = new PersonDAOImpl();
    
    @Override
    public void init() throws ServletException {
        super.init();
        personDAO = new PersonDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        try {
            if (action == null || action.isEmpty()) {
                // List all persons
                req.setAttribute("persons", personDAO.getAllPersons());
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            } else if ("edit".equals(action)) {
                // Edit a person
                int id = Integer.parseInt(req.getParameter("id"));
                Person person = personDAO.getPerson(id);
                req.setAttribute("person", person);
                req.getRequestDispatcher("update.jsp").forward(req, resp);
            } else if ("delete".equals(action)) {
                // Delete a person
                int id = Integer.parseInt(req.getParameter("id"));
                personDAO.deletePerson(id);
                resp.sendRedirect("person");
            } else {
                // Invalid action
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ação Inválida");
            }
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Formato de ID Inválido");
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ocorreu um erro");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        try {
            if ("create".equals(action)) {
                // Create a new person
                Person person = new Person();
                person.setName(req.getParameter("name"));
                person.setAge(Integer.parseInt(req.getParameter("age")));
                personDAO.addPerson(person);
                resp.sendRedirect("person");
            } else if ("update".equals(action)) {
                // Update an existing person
                Person person = new Person();
                person.setId(Integer.parseInt(req.getParameter("id")));
                person.setName(req.getParameter("name"));
                person.setAge(Integer.parseInt(req.getParameter("age")));
                personDAO.updatePerson(person);
                resp.sendRedirect("person");
            } else {
                // Invalid action
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
            }
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input format");
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred");
        }
    }
}
