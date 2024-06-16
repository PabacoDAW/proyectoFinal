/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladores.admin;

import entidades.Carrito;
import entidades.Pedido;
import entidades.Usuario;
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

@WebServlet(name = "GestionPedidos", urlPatterns = {"/admin/GestionPedidos"})
public class GestionPedidos extends HttpServlet {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoFinal_TiendaPU");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();
        try {
            List<Pedido> pedidos = em.createQuery("SELECT p FROM Pedido p", Pedido.class).getResultList();
            request.setAttribute("pedidos", pedidos);

            request.getRequestDispatcher("/admin/gestionPedidos.jsp").forward(request, response);
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
            if ("editar".equals(action)) {
                Long id = Long.parseLong(request.getParameter("id"));
                String estado = request.getParameter("estado");
                boolean entregaADomicilio = request.getParameter("entregaADomicilio") != null;

                Pedido pedido = em.find(Pedido.class, id);
                if (pedido != null) {
                    pedido.setEstado(estado);
                    pedido.setEntregaADomicilio(entregaADomicilio);
                    em.merge(pedido);
                }
            }
            em.getTransaction().commit();
            response.sendRedirect("GestionPedidos");
        } finally {
            em.close();
        }
    }

    @Override
    public String getServletInfo() {
        return "GestionPedidos";
    }
}
