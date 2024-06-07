/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import dao.UsuarioJpaController;
import entidades.Usuario;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ModeloRegistro {

    public static Usuario registrar(String nombre, String apellidos, String nombreUsuario, String domicilio, String email, String contrasena) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoFinal_TiendaPU");
        UsuarioJpaController ujp = new UsuarioJpaController(emf);
        Usuario usuario = new Usuario();
        if (nombre != null && !nombre.isEmpty()) {
            usuario.setNombre(nombre);
        }
        if (apellidos != null && !apellidos.isEmpty()) {
            usuario.setApellidos(apellidos);
        }
        if (nombreUsuario != null && !nombreUsuario.isEmpty()) {
            usuario.setUsuario(nombreUsuario);
        }
        if (domicilio != null && !domicilio.isEmpty()) {
            usuario.setDomicilio(domicilio);
        }
        if (email != null && !email.isEmpty()) {
            usuario.setEmail(email);
        }
        if (contrasena != null && !contrasena.isEmpty()) {
            usuario.setContraseña(contrasena);
        }
        usuario.setTipoUsuario(Usuario.TipoUsuario.SOCIO);
        try {
            ujp.create(usuario);
        } catch(Exception e){
            usuario = null;
        }
        emf.close();
        return usuario;
    }

}
