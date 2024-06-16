package controladores.admin;

import entidades.Categoria;
import entidades.Marca;
import entidades.Producto;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(name = "Pp", urlPatterns = {"/admin/Pp"})
@MultipartConfig
public class Pp extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoFinal_TiendaPU");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();
        List<Producto> productos = em.createQuery("SELECT p FROM Producto p", Producto.class).getResultList();
        List<Marca> marcas = em.createQuery("SELECT m FROM Marca m", Marca.class).getResultList();
        List<Categoria> categorias = em.createQuery("SELECT c FROM Categoria c", Categoria.class).getResultList();

        request.setAttribute("productos", productos);
        request.setAttribute("marcas", marcas);
        request.setAttribute("categorias", categorias);
        em.close();
        request.getRequestDispatcher("/admin/pruebaProducto.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        EntityManager em = emf.createEntityManager();

        if ("Crear".equals(action)) {
            em.getTransaction().begin();
            Producto producto = new Producto();
            producto.setNombre(request.getParameter("nombre"));
            producto.setDescripcion(request.getParameter("descripcion"));
            producto.setPrecio(Double.parseDouble(request.getParameter("precio")));

            // Manejar la subida de archivos
            Part filePart = request.getPart("file");
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String uploadDir = getServletContext().getRealPath("/") + "img";
            File uploadDirFile = new File(uploadDir);
            if (!uploadDirFile.exists()) {
                uploadDirFile.mkdir();
            }
            String filePath = uploadDir + File.separator + fileName;
            try (InputStream fileContent = filePart.getInputStream()) {
                Files.copy(fileContent, new File(filePath).toPath());
            }
            producto.setUrl("img/" + fileName);

            String[] marcaIds = request.getParameterValues("marcas");
            List<Marca> marcas = new ArrayList<>();
            if (marcaIds != null) {
                for (String marcaId : marcaIds) {
                    Marca marca = em.find(Marca.class, Long.parseLong(marcaId));
                    if (marca != null) {
                        marcas.add(marca);
                    }
                }
            }
            producto.setMarcas(marcas);

            String[] categoriaIds = request.getParameterValues("categorias");
            List<Categoria> categorias = new ArrayList<>();
            if (categoriaIds != null) {
                for (String categoriaId : categoriaIds) {
                    Categoria categoria = em.find(Categoria.class, Long.parseLong(categoriaId));
                    if (categoria != null) {
                        categorias.add(categoria);
                    }
                }
            }
            producto.setCategorias(categorias);

            em.persist(producto);
            em.getTransaction().commit();
        } else if ("Eliminar".equals(action)) {
            em.getTransaction().begin();
            Long id = Long.parseLong(request.getParameter("id"));
            Producto producto = em.find(Producto.class, id);
            if (producto != null) {
                em.remove(producto);
                em.getTransaction().commit();
            }
        }
        em.close();
        response.sendRedirect("GestionProductos");
    }
}
