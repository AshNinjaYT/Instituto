package model;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.Pista;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2026-04-20T23:25:09", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Estacio.class)
public class Estacio_ { 

    public static volatile SingularAttribute<Estacio, String> comarca;
    public static volatile ListAttribute<Estacio, Pista> pistes;
    public static volatile SingularAttribute<Estacio, String> web;
    public static volatile SingularAttribute<Estacio, Double> percentatgePistesObertes;
    public static volatile SingularAttribute<Estacio, String> qualificacio;
    public static volatile SingularAttribute<Estacio, Integer> altitudMaxima;
    public static volatile SingularAttribute<Estacio, String> id;
    public static volatile SingularAttribute<Estacio, String> nom;

}