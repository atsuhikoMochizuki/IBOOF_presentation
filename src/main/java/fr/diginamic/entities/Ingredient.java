package fr.diginamic.entities;

import jakarta.persistence.*;

import java.time.temporal.ValueRange;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Cacheable
public class Ingredient {

    //Attributs
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id_ingredient;
    @Column(length = 1024)
    private String nom_ingredient;

    //Relations
    @ManyToMany(mappedBy = "ingredients")
    private Set<Produit> produits;

    public Ingredient() {
    }

    public Ingredient(String nom_ingredient) {
        this.nom_ingredient = nom_ingredient;
    }

    public Ingredient(Integer id_ingredient, String nom_ingredient) {
        this.id_ingredient = id_ingredient;
        this.nom_ingredient = nom_ingredient;
    }

    //Getters
    public Integer getId_ingredient() {
        return id_ingredient;
    }

    public String getNom_ingredient() {
        return nom_ingredient;
    }

    public Set<Produit> getProduits() {
        return produits;
    }

    //Setters
    public void setId_ingredient(Integer id_ingredient) {
        this.id_ingredient = id_ingredient;
    }

    public void setNom_ingredient(String nom_ingredient) {
        this.nom_ingredient = nom_ingredient;
    }

    public void setProduits(Set<Produit> produits) {
        this.produits = produits;
    }

    //ToString()
    @Override
    public String toString() {
        return "Ingredient{" +
                "id_ingredient=" + id_ingredient +
                ", nom_ingredient='" + nom_ingredient + '\'' +
                ", produits=" + produits +
                '}';
    }
}
