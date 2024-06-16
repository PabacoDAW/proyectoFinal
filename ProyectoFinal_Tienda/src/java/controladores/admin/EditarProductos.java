package controladores.admin;

import entidades.Categoria;
import entidades.Marca;
import entidades.Producto;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
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

@WebServlet(name = "EditarProductos", urlPatterns = {"/admin/EditarProductos"})
@MultipartConfig
public class EditarProductos extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoFinal_TiendaPU");

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();

        try {
            Long id = Long.parseLong(request.getParameter("id"));
            Producto producto = em.find(Producto.class, id);

            if (producto != null) {
                List<Marca> marcas = em.createQuery("SELECT m FROM Marca m", Marca.class).getResultList();
                List<Categoria> categorias = em.createQuery("SELECT c FROM Categoria c", Categoria.class).getResultList();
                request.setAttribute("producto", producto);
                request.setAttribute("marcas", marcas);
                request.setAttribute("categorias", categorias);
                request.getRequestDispatcher("/admin/editarProductos.jsp").forward(request, response);
            } else {
                response.sendRedirect("GestionProductos");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("GestionProductos");
        } finally {
            em.close();
        }
    }

    protected void updateProducto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = emf.createEntityManager();

        try {
            Long id = Long.parseLong(request.getParameter("id"));
            Producto producto = em.find(Producto.class, id);

            if (producto != null) {
                em.getTransaction().begin();

                String nuevoNombre = request.getParameter("nombre");
                if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
                    producto.setNombre(nuevoNombre);
                }
                String nuevaDescripcion = request.getParameter("descripcion");
                if (nuevaDescripcion != null && !nuevaDescripcion.isEmpty()) {
                    producto.setDescripcion(nuevaDescripcion);
                }
                String nuevoPrecio = request.getParameter("precio");
                if (nuevoPrecio != null && !nuevoPrecio.isEmpty()) {
                    producto.setPrecio(Double.parseDouble(nuevoPrecio));
                }

                // Manejo de la subida de imagen
                Part filePart = request.getPart("imagen");
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                if (!fileName.isEmpty()) {
                    String uploadDir = getServletContext().getRealPath("/") + "img";
                    File uploadDirFile = new File(uploadDir);
                    if (!uploadDirFile.exists()) {
                        uploadDirFile.mkdir();
                    }
                    String filePath = uploadDir + File.separator + fileName;
                    filePart.write(filePath);
                    producto.setUrl("img/" + fileName);
                }

                String[] marcaIds = request.getParameterValues("marcas");
                List<Marca> marcas = producto.getMarcas();
                if (marcaIds != null) {
                    marcas.clear();
                    for (String marcaId : marcaIds) {
                        Marca marca = em.find(Marca.class, Long.parseLong(marcaId));
                        if (marca != null) {
                            marcas.add(marca);
                        }
                    }
                }

                String[] categoriaIds = request.getParameterValues("categorias");
                List<Categoria> categorias = producto.getCategorias();
                if (categoriaIds != null) {
                    categorias.clear();
                    for (String categoriaId : categoriaIds) {
                        Categoria categoria = em.find(Categoria.class, Long.parseLong(categoriaId));
                        if (categoria != null) {
                            categorias.add(categoria);
                        }
                    }
                }

                em.merge(producto);
                em.getTransaction().commit();

                response.sendRedirect("GestionProductos");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("GestionProductos");
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
        if (request.getParameter("actualizar") != null) {
            updateProducto(request, response);
        } else {
            processRequest(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
