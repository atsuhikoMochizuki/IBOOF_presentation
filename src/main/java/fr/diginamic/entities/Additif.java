package fr.diginamic.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Cacheable
public class Additif {
    //Attributs
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id_additif;
    @Column
    private String nom_additif;

    @ManyToMany(mappedBy = "additifs")
    private Set<Produit> produits;

    //Constructeurs
    public Additif() {
    }

    public Additif(String nom_additif) {
        this.nom_additif = nom_additif;
    }

    public Additif(Integer id_additif, String nom_additif) {
        this.id_additif = id_additif;
        this.nom_additif = nom_additif;
    }

    //Getters
    public Integer getId_additif() {
        return id_additif;
    }

    public String getNom_additif() {
        return nom_additif;
    }

    public Set<Produit> getProduits() {
        return produits;
    }

    //Setters
    public void setId_additif(Integer id_additif) {
        this.id_additif = id_additif;
    }

    public void setNom_additif(String nom_additif) {
        this.nom_additif = nom_additif;
    }

    public void setProduits(Set<Produit> produits) {
        this.produits = produits;
    }

    //ToString()

    @Override
    public String toString() {
        return "Additif{" +
                "id_additif=" + id_additif +
                ", nom_additif='" + nom_additif + '\'' +
                ", produits=" + produits +
                '}';
    }
}
