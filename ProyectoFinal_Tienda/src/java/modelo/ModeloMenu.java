/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import dao.ProductoJpaController;
import entidades.Producto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Rovimatica
 */
public class ModeloMenu {

    public static List<Producto> getProductos(String filtro) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Biblioteca_finalPU");
        ProductoJpaController ljc = new ProductoJpaController(emf);
        List<Producto> productos = new ArrayList<Producto>();
        productos = ljc.findProductoEntities();
        emf.close();
        List<Producto> productosFiltrados = new ArrayList();
        for (Producto p: productos) {
            if (p.getCategoria().getNombre().contains(filtro) || p.getNombre().contains(filtro) || p.getMarca().getNombre().contains(filtro)) {
                productosFiltrados.add(p);
            }
        }
        return productosFiltrados;
    }

    public static List<Producto> getProductos() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoFinal_TiendaPU");
        ProductoJpaController pjc = new ProductoJpaController(emf);
        List<Producto> productos = pjc.findProductoEntities();
        emf.close();
        return productos;
    }

}
