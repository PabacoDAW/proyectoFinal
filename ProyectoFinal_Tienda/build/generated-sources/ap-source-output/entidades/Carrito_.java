package entidades;

import entidades.Producto;
import entidades.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2024-06-16T22:50:36")
@StaticMetamodel(Carrito.class)
public class Carrito_ { 

    public static volatile SingularAttribute<Carrito, Usuario> usuario;
    public static volatile SingularAttribute<Carrito, Long> id;
    public static volatile ListAttribute<Carrito, Producto> productos;

}