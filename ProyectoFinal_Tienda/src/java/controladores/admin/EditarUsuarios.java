/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controladores.admin;

import entidades.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "EditarUsuarios", urlPatterns = {"/admin/EditarUsuarios"})
public class EditarUsuarios extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String actualizar = request.getParameter("actualizar");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoFinal_TiendaPU");
        EntityManager em = emf.createEntityManager();
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            Usuario usuario = em.find(Usuario.class, id);
            if (usuario == null) {
                response.sendRedirect("GestionUsuarios");
                return; // Salir si el usuario no existe
            }

            if (actualizar == null) {
                // Cargar usuario para edición
                request.setAttribute("usuario", usuario);
                request.getRequestDispatcher("/admin/editarUsuarios.jsp").forward(request, response);
            } else {
                // Actualizar usuario
                em.getTransaction().begin();

                String nuevoUsuario = request.getParameter("usuario");
                if (nuevoUsuario != null && !nuevoUsuario.isEmpty()) {
                    usuario.setUsuario(nuevoUsuario);
                }
                String nuevoNombre = request.getParameter("nombre");
                if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
                    usuario.setNombre(nuevoNombre);
                }
                String nuevosApellidos = request.getParameter("apellidos");
                if (nuevosApellidos != null && !nuevosApellidos.isEmpty()) {
                    usuario.setApellidos(nuevosApellidos);
                }
                String nuevaContraseña = request.getParameter("contrasena");
                if (nuevaContraseña != null && !nuevaContraseña.isEmpty()) {
                    usuario.setContraseña(nuevaContraseña);
                }
                String nuevoEmail = request.getParameter("email");
                if (nuevoEmail != null && !nuevoEmail.isEmpty()) {
                    usuario.setEmail(nuevoEmail);
                }
                String nuevoDomicilio = request.getParameter("domicilio");
                if (nuevoDomicilio != null && !nuevoDomicilio.isEmpty()) {
                    usuario.setDomicilio(nuevoDomicilio);
                }
                String nuevoTipoUsuario = request.getParameter("tipo");
                if (nuevoTipoUsuario != null && !nuevoTipoUsuario.isEmpty()) {
                    usuario.setTipoUsuario(Usuario.TipoUsuario.valueOf(nuevoTipoUsuario));
                }

                em.merge(usuario);
                em.getTransaction().commit();

                response.sendRedirect("GestionUsuarios");
            }
        } finally {
            em.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
