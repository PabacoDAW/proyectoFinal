/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.usuario;

import dao.CarritoJpaController;
import dao.PedidoJpaController;
import entidades.Carrito;
import entidades.Producto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ModeloCarrito {

    public static List<Producto> getProductos(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoFinal_TiendaPU");
        CarritoJpaController cjc = new CarritoJpaController(emf);
        List<Carrito> carritos = cjc.findCarritoEntities();
        List<Producto> productos = new ArrayList();
        for (Carrito carrito : carritos) {
            if (carrito.getUsuario().getId() == id) {
                productos = carrito.getProductos();
            }
        }
        return productos;
    }

    public static Double getPrecio(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoFinal_TiendaPU");
        CarritoJpaController cjc = new CarritoJpaController(emf);
        List<Carrito> carritos = cjc.findCarritoEntities();
        double precio;
        for (Carrito carrito : carritos) {
            if (carrito.getUsuario().getId() == id) {
                precio = carrito.getPrecio();
                return precio;  
            }
        }
        return null;
    }

}
