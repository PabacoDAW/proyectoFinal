/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.usuario;

import dao.CarritoJpaController;
import dao.PedidoJpaController;
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
import javax.persistence.EntityManagerFactory;
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
        UsuarioJpaController ujc = new UsuarioJpaController(emf);
        PedidoJpaController pjc = new PedidoJpaController(emf);
        Carrito nuevoCarrito = new Carrito();
        u.setCarrito(nuevoCarrito);
        Pedido p = new Pedido();
        p.setEntregaADomicilio(entrega);
        p.setEstado("Preparando");
        p.setFechaPedido(new Date());
        p.setUsuario(u);
        p.setCarrito(u.getCarrito());
        pjc.create(p);
        try {
            ujc.edit(u);
        } catch (Exception ex) {
            Logger.getLogger(ModeloCarrito.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }

}
