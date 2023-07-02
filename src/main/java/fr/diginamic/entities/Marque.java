package fr.diginamic.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Cacheable
public class Marque {

    //Attributs
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id_marque;
    @Column
    private String nom_marque;

    //Relations
    @ManyToMany(mappedBy = "marques")
    private Set<Produit> produits;

    //Constructeurs
    public Marque() {

    }

    public Marque(String nom_marque) {
        this.nom_marque = nom_marque;
    }

    public Marque(Integer id_marque, String nom_marque, Set<Produit> produits) {
        this.id_marque = id_marque;
        this.nom_marque = nom_marque;
        this.produits = produits;
    }

    //Getters
    public Integer getId_marque() {
        return id_marque;
    }

    public String getNom_marque() {
        return nom_marque;
    }

    public Set<Produit> getProduits() {
        return produits;
    }

    //Setters
    public void setId_marque(Integer id_marque) {
        this.id_marque = id_marque;
    }

    public void setNom_marque(String nom_marque) {
        this.nom_marque = nom_marque;
    }

    public void setProduits(Set<Produit> produits) {
        this.produits = produits;
    }

    //ToString()
    @Override
    public String toString() {
        return "Marque{" +
                "id_marque=" + id_marque +
                ", nom_marque='" + nom_marque + '\'' +
                ", produits=" + produits +
                '}';
    }
}
