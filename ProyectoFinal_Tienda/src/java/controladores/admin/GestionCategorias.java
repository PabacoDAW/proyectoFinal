/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladores.admin;

import entidades.Categoria;
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
@WebServlet(name = "GestionCategorias", urlPatterns = {"/admin/GestionCategorias"})
public class GestionCategorias extends HttpServlet {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoFinal_TiendaPU");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();
        try {
            List<Categoria> categorias = em.createQuery("SELECT c FROM Categoria c", Categoria.class).getResultList();
            request.setAttribute("categorias", categorias);
            request.getRequestDispatcher("/admin/gestionCategorias.jsp").forward(request, response);
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
                Categoria nuevaCategoria = new Categoria();
                nuevaCategoria.setNombre(nombre);
                em.persist(nuevaCategoria);
            } else if ("editar".equals(action)) {
                Long id = Long.parseLong(request.getParameter("id"));
                String nombre = request.getParameter("nombre");
                Categoria categoria = em.find(Categoria.class, id);
                if (categoria != null) {
                    categoria.setNombre(nombre);
                    em.merge(categoria);
                }
            } else if ("eliminar".equals(action)) {
                try {
                    Long id = Long.parseLong(request.getParameter("id"));
                    Categoria categoria = em.find(Categoria.class, id);
                    if (categoria != null) {
                        em.remove(categoria);
                    }
                } catch (Exception e) {
                    request.setAttribute("error", "No se puede eliminar esta marca");
                }
            }
            em.getTransaction().commit();
            response.sendRedirect("GestionCategorias");
        } finally {
            em.close();
        }
    }

    @Override
    public String getServletInfo() {
        return "GestionCategorias";
    }
}
