/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import dao.ProductoJpaController;
import entidades.Producto;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author usuario
 */
public class ModeloProducto {
    
    public static Producto buscarProducto(String id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoFinal_TiendaPU");
        ProductoJpaController pjc = new ProductoJpaController(emf);
        Producto p = null;
        if(id!=null && !id.trim().isEmpty()){
            p = pjc.findProducto(Long.parseLong(id));
        }
        return p;
    }
}
