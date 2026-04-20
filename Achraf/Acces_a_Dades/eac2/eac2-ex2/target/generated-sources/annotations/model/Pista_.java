package model;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Estacio;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2026-04-20T03:08:34", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Pista.class)
public abstract class Pista_ { 

    public static volatile SingularAttribute<Pista, Integer> gruixNeu;
    public static volatile SingularAttribute<Pista, Integer> longitud;
    public static volatile SingularAttribute<Pista, Boolean> oberta;
    public static volatile SingularAttribute<Pista, Boolean> iluminada;
    public static volatile SingularAttribute<Pista, String> id;
    public static volatile SingularAttribute<Pista, String> qualitatNeu;
    public static volatile SingularAttribute<Pista, Estacio> estacio;
    public static volatile SingularAttribute<Pista, String> nom;
    public static volatile SingularAttribute<Pista, Integer> desnivell;

}