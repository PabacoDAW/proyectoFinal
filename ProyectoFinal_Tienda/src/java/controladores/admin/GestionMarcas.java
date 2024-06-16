/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladores.admin;

import entidades.Marca;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Cooler Master
 */
@WebServlet(name = "GestionMarcas", urlPatterns = {"/admin/GestionMarcas"})
public class GestionMarcas extends HttpServlet {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoFinal_TiendaPU");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();
        try {
            List<Marca> marcas = em.createQuery("SELECT m FROM Marca m", Marca.class).getResultList();
            request.setAttribute("marcas", marcas);
            request.getRequestDispatcher("/admin/gestionMarcas.jsp").forward(request, response);
        } finally {
            em.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            if ("crear".equals(action)) {
                String nombre = request.getParameter("nombre");
                Marca nuevaMarca = new Marca();
                nuevaMarca.setNombre(nombre);
                em.persist(nuevaMarca);
            } else if ("editar".equals(action)) {
                Long id = Long.parseLong(request.getParameter("id"));
                String nombre = request.getParameter("nombre");
                Marca marca = em.find(Marca.class, id);
                if (marca != null) {
                    marca.setNombre(nombre);
                    em.merge(marca);
                }
            } else if ("eliminar".equals(action)) {
                try {
                    Long id = Long.parseLong(request.getParameter("id"));
                    Marca marca = em.find(Marca.class, id);
                    if (marca != null) {
                        em.remove(marca);
                    }
                } catch (Exception e) {
                    request.setAttribute("error", "No se puede eliminar esta marca");
                }
            }
            em.getTransaction().commit();
            response.sendRedirect("GestionMarcas");
        } finally {
            em.close();
        }
    }

    @Override
    public String getServletInfo() {
        return "GestionMarcas";
    }

}
