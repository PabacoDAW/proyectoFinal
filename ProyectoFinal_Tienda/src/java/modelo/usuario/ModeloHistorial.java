/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.usuario;

import dao.PedidoJpaController;
import entidades.Pedido;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ModeloHistorial {

    public static List<Pedido> getPedidos(Long id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoFinal_TiendaPU");
        PedidoJpaController pjc = new PedidoJpaController(emf);
        List<Pedido> pedidos = pjc.findPedidoEntities();
        emf.close();
        List<Pedido> pedidoUsu = new ArrayList();
        for (Pedido pedido : pedidos) {
            if (pedido.getUsuario().getId().equals(id)) {
                pedidoUsu.add(pedido);
            }
        }
        /*Collections.sort(pedidoUsu, new Comparator<Pedido>() {
            public int compare(Pedido p1, Pedido p2) {
                return p2.getFechaPedido().compareTo(p1.getFechaPedido());
            }
        });*/
        return pedidoUsu;
    }

}
