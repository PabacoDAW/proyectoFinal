/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import dao.ProductoJpaController;
import entidades.Categoria;
import entidades.Marca;
import entidades.Producto;
import java.util.ArrayList;
import java.util.List;
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

}
