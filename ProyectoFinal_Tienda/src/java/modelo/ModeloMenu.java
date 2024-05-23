/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import dao.CarritoJpaController;
import dao.ProductoJpaController;
import dao.UsuarioJpaController;
import entidades.Carrito;
import entidades.Categoria;
import entidades.Marca;
import entidades.Producto;
import entidades.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ModeloMenu {

    public static List<Producto> getProductos(String filtro) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoFinal_TiendaPU");
        ProductoJpaController ljc = new ProductoJpaController(emf);
        List<Producto> productos = new ArrayList<Producto>();
        productos = ljc.findProductoEntities();
        List<Producto> productosFiltrados = new ArrayList();
        for (Producto p : productos) {
            if (!p.getNombre().isBlank() && p.getNombre().contains(filtro)) {
                productosFiltrados.add(p);
            } else if (p.getCategorias() != null && p.getCategorias().size() >= 1) {
                for (Categoria categoria : p.getCategorias()) {
                    if (categoria.getNombre().contains(filtro)) {
                        productosFiltrados.add(p);
                    }
                }
            } else if (p.getMarcas() != null && p.getMarcas().size() >= 1) {
                for (Marca marca : p.getMarcas()) {
                    if (marca.getNombre().contains(filtro)) {
                        productosFiltrados.add(p);
                    }
                }
            }

        }
        emf.close();
        return productosFiltrados;
    }

    public static List<Producto> getProductos() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoFinal_TiendaPU");
        ProductoJpaController pjc = new ProductoJpaController(emf);
        List<Producto> productos = pjc.findProductoEntities();
        emf.close();
        return productos;
    }

//    public static void addProduct(String agregar, Usuario u) {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoFinal_TiendaPU");
//        UsuarioJpaController ujc = new UsuarioJpaController(emf);
//        ProductoJpaController pjc = new ProductoJpaController(emf);
//        if (agregar != null && !agregar.trim().isEmpty()) {
//            Producto producto = pjc.findProducto(Long.parseLong(agregar));
//            if (producto != null) {
//                if (u.getCarrito() == null) {
//                    u.getCarrito().setProductos(new ArrayList<Producto>()); 
//                    u.getCarrito().getProductos().add(producto);
//                } else {
//                    u.getCarrito().getProductos().add(producto);
//                }
//            }
//            try {
//                ujc.edit(u);
//            } catch (Exception ex) {
//                Logger.getLogger(ModeloMenu.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        emf.close();
//    }
    public static void addProduct(String agregar, Usuario u) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoFinal_TiendaPU");
        EntityManager em = emf.createEntityManager();

        try {
            UsuarioJpaController ujc = new UsuarioJpaController(emf);
            ProductoJpaController pjc = new ProductoJpaController(emf);

            if (agregar != null && !agregar.trim().isEmpty()) {
                Producto producto = pjc.findProducto(Long.parseLong(agregar));
                if (producto != null) {
                    // Asegurarse de que el usuario tiene un carrito inicializado
                    if (u.getCarrito() == null) {
                        Carrito carrito = new Carrito();
                        carrito.setUsuario(u);
                        carrito.setProductos(new ArrayList<Producto>());
                        u.setCarrito(carrito);
                    }

                    u.getCarrito().getProductos().add(producto);

                    // Iniciar transacción
                    em.getTransaction().begin();

                    // Editar usuario
                    ujc.edit(u);

                    // Confirmar transacción
                    em.getTransaction().commit();
                }
            }
        } catch (Exception ex) {
            // Si hay un error, hacer rollback de la transacción
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            Logger.getLogger(ModeloMenu.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
            emf.close();
        }
    }

}
