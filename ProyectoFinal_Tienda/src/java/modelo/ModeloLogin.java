/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import dao.CarritoJpaController;
import dao.UsuarioJpaController;
import entidades.Carrito;
import entidades.Usuario;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ModeloLogin {

    public static Usuario validarUsuario(String email, String contrasena) {
        Usuario u = null;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoFinal_TiendaPU");
        UsuarioJpaController ejc = new UsuarioJpaController(emf);
        List<Usuario> usuarios = ejc.findUsuarioEntities();
        emf.close();
        boolean encontrado = false;
        if (email != null && !email.trim().isEmpty() && contrasena != null && !contrasena.trim().isEmpty()) {
            for (int i = 0; i < usuarios.size() && !encontrado; i++) {
                Usuario actual = usuarios.get(i);
                if (actual.getEmail().equals(email) || actual.getUsuario().equals(email)) {
                    encontrado = true;
                    if (actual.getContraseña() != null && actual.getContraseña().equals(contrasena)) {
                        u = actual;
                    }
                }

            }
        }
        return u;
    }

    public static Carrito getCarrito(Usuario u) {
        if (u == null) {
            return null;
        } else {
            return u.getCarrito();
        }
    }

    public static int getNProductos(Usuario u) {
    if (u != null && u.getCarrito() != null && u.getCarrito().getProductos() != null) {
        return u.getCarrito().getProductos().size();
    } else {
        return 0;
    }
}


}
