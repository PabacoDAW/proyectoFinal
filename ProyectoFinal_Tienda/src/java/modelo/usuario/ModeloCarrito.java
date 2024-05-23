/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.usuario;

import dao.CarritoJpaController;
import dao.PedidoJpaController;
import dao.UsuarioJpaController;
import entidades.Carrito;
import entidades.Producto;
import entidades.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ModeloCarrito {

    public static List<Producto> getProductos(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoFinal_TiendaPU");
        UsuarioJpaController ujc = new UsuarioJpaController(emf);
        Usuario u = ujc.findUsuario(id);
        emf.close();
        return u.getCarrito().getProductos();
    }

    public static Double getPrecio(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoFinal_TiendaPU");
        UsuarioJpaController ujc = new UsuarioJpaController(emf);
        Usuario u = ujc.findUsuario(id);
        return u.getCarrito().getPrecio();
    }


}
