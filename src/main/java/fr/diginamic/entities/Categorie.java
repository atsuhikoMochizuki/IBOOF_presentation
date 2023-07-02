package fr.diginamic.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Cacheable
public class Categorie {

    //Attributs
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id_categorie;
    @Column
    private String nom_categorie;

    //Relations
    @OneToMany(mappedBy = "categorie")
    private Set<Produit> produits;

    //Constructeurs
    public Categorie() {
    }

    public Categorie(String nom_categorie) {
        this.nom_categorie = nom_categorie;
    }

    public Categorie(Integer id_categorie, String nom_categorie) {
        this.id_categorie = id_categorie;
        this.nom_categorie = nom_categorie;
    }

    //Getters
    public Integer getId_categorie() {
        return id_categorie;
    }

    public String getNom_categorie() {
        return nom_categorie;
    }

    public Set<Produit> getProduits() {
        return produits;
    }

    //Setters
    public void setId_categorie(Integer id_categorie) {
        this.id_categorie = id_categorie;
    }

    public void setNom_categorie(String nom_categorie) {
        this.nom_categorie = nom_categorie;
    }

    public void setProduits(Set<Produit> produits) {
        this.produits = produits;
    }

    //ToString()
    @Override
    public String toString() {
        return "Categorie{" +
                "id_categorie=" + id_categorie +
                ", nom_categorie='" + nom_categorie + '\'' +
                ", produits=" + produits +
                '}';
    }
}
