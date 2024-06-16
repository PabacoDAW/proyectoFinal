/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladores.admin;

import entidades.Carrito;
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

@WebServlet(name = "GestionUsuarios", urlPatterns = {"/admin/GestionUsuarios"})
public class GestionUsuarios extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoFinal_TiendaPU");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();
        List<Usuario> usuarios = em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
        request.setAttribute("usuarios", usuarios);
        em.close();
        request.getRequestDispatcher("/admin/gestionUsuarios.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            if ("Crear".equals(action)) {
                Usuario usuario = new Usuario();
                usuario.setUsuario(request.getParameter("usuario"));
                usuario.setNombre(request.getParameter("nombre"));
                usuario.setApellidos(request.getParameter("apellidos"));
                usuario.setContraseña(request.getParameter("contrasena"));
                usuario.setEmail(request.getParameter("email"));
                usuario.setDomicilio(request.getParameter("domicilio"));
                usuario.setTipoUsuario(Usuario.TipoUsuario.valueOf(request.getParameter("tipo")));
                em.persist(usuario);
            } else if ("Eliminar".equals(action)) {
                try {
                    Long id = Long.parseLong(request.getParameter("id"));
                    Usuario usuario = em.find(Usuario.class, id);
                    if (usuario != null) {
                        Carrito carrito = usuario.getCarrito();
                        if (carrito != null) {
                            em.remove(carrito);
                        }
                        em.remove(usuario);
                    }
                } catch (Exception e) {
                   
                }
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            request.setAttribute("error", "No se puede eliminar este usuario");
        } finally {
            em.close();
        }

        response.sendRedirect("GestionUsuarios");
    }
}
