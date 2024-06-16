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
            if (p.getNombre()!=null && !p.getNombre().trim().isEmpty()&& (p.getNombre().contains(filtro) || p.getNombre().equalsIgnoreCase(filtro))) {
                productosFiltrados.add(p);
            } else if (p.getCategorias() != null && p.getCategorias().size() >= 1) {
                for (Categoria categoria : p.getCategorias()) {
                    if (categoria.getNombre().contains(filtro) || categoria.getNombre().equalsIgnoreCase(filtro)) {
                        productosFiltrados.add(p);
                    }
                }
            } else if (p.getMarcas() != null && p.getMarcas().size() >= 1) {
                for (Marca marca : p.getMarcas()) {
                    if (marca.getNombre().contains(filtro) || marca.getNombre().equalsIgnoreCase(filtro)) {
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

    public static void addProduct(String agregar, Usuario u) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoFinal_TiendaPU");
        EntityManager em = emf.createEntityManager();

        try {
            ProductoJpaController pjc = new ProductoJpaController(emf);
            CarritoJpaController cjc = new CarritoJpaController(emf);

            em.getTransaction().begin();

            if (agregar != null && !agregar.trim().isEmpty()) {
                Producto producto = pjc.findProducto(Long.parseLong(agregar));
                if (producto != null) {
                    if (u.getCarrito() == null) {
                        Carrito carrito = new Carrito();
                        carrito.setUsuario(u);
                        carrito.setProductos(new ArrayList<Producto>());
                        u.setCarrito(carrito);
                    }
                    if (!u.getCarrito().getProductos().contains(producto)) {
                        u.getCarrito().getProductos().add(producto);
                        cjc.edit(u.getCarrito());
                        em.merge(u); // Persiste los cambios en el usuario y su carrito
                    }
                }
            }

            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // Revierte la transacción si hay un error
            }
            Logger.getLogger(ModeloMenu.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
            emf.close();
        }
    }

}
