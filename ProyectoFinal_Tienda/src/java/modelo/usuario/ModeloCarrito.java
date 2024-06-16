/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.usuario;

import dao.CarritoJpaController;
import dao.PedidoJpaController;
import dao.ProductoJpaController;
import dao.UsuarioJpaController;
import entidades.Carrito;
import entidades.Pedido;
import entidades.Producto;
import entidades.Usuario;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ModeloCarrito {

    public static List<Producto> getProductos(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoFinal_TiendaPU");
        UsuarioJpaController ujc = new UsuarioJpaController(emf);
        Usuario u = ujc.findUsuario(id);
        emf.close();
        if (u.getCarrito().getProductos() == null) {
            u.getCarrito().setProductos(new ArrayList<Producto>());
        }
        return u.getCarrito().getProductos();
    }

    public static Double getPrecio(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoFinal_TiendaPU");
        UsuarioJpaController ujc = new UsuarioJpaController(emf);
        Usuario u = ujc.findUsuario(id);
        emf.close();
        return u.getCarrito().getPrecio();
    }

    public static Usuario realizarPedido(Usuario u, Boolean entrega) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoFinal_TiendaPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            // Obtener usuario actualizado desde la base de datos
            Usuario usuario = em.find(Usuario.class, u.getId());

            if (usuario != null && usuario.getCarrito() != null) {
                // Crear el pedido y asociarlo al usuario
                Pedido p = new Pedido();
                p.setEntregaADomicilio(entrega);
                p.setEstado("Preparando");
                p.setFechaPedido(new Date());
                p.setUsuario(usuario);

                // Inicializar la lista de productos del pedido
                List<Producto> productosPedido = new ArrayList<>();
                p.setProductos(productosPedido);

                // Asignar los productos del carrito al pedido
                for (Producto producto : usuario.getCarrito().getProductos()) {
                    productosPedido.add(producto);
                }

                // Guardar el pedido en la base de datos
                em.persist(p);

                // Limpiar el carrito del usuario
                usuario.getCarrito().getProductos().clear();
                em.merge(usuario.getCarrito());

                // Actualizar el usuario en la base de datos
                em.merge(usuario);

                tx.commit();
            }
        } catch (Exception ex) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            Logger.getLogger(ModeloCarrito.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
            emf.close();
        }

        return u;
    }

    public static Usuario eliminarProductos(Usuario u) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoFinal_TiendaPU");
        UsuarioJpaController ujc = new UsuarioJpaController(emf);
        CarritoJpaController cjc = new CarritoJpaController(emf);
        Usuario usuario = ujc.findUsuario(u.getId());
        if(usuario!=null){
            Carrito carrito = usuario.getCarrito();
            carrito.clear();
            usuario.setCarrito(carrito);
            try {
                ujc.edit(usuario);
                cjc.edit(carrito);
            } catch (Exception ex) {
                Logger.getLogger(ModeloCarrito.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return usuario;
    }

}
