package fr.diginamic.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Cacheable
public class Allergene {

    //Attributs
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id_allergene;
    @Column
    private String nom_allergene;

    //Relations
    @ManyToMany(mappedBy = "allergenes")
    private Set<Produit> produits;

    //Constructeurs
    public Allergene() {
    }

    public Allergene(String nom_allergene) {
        this.nom_allergene = nom_allergene;
    }

    public Allergene(Integer id_allergene, String nom_allergene) {
        this.id_allergene = id_allergene;
        this.nom_allergene = nom_allergene;
    }

    //Getters
    public Integer getId_allergene() {
        return id_allergene;
    }

    public String getNom_allergene() {
        return nom_allergene;
    }

    public Set<Produit> getProduits() {
        return produits;
    }

    //Setters
    public void setId_allergene(Integer id_allergene) {
        this.id_allergene = id_allergene;
    }

    public void setNom_allergene(String nom_allergene) {
        this.nom_allergene = nom_allergene;
    }

    public void setProduits(Set<Produit> produits) {
        this.produits = produits;
    }

    //ToString()

    @Override
    public String toString() {
        return "Allergene{" +
                "id_allergene=" + id_allergene +
                ", nom_allergene='" + nom_allergene + '\'' +
                ", produits=" + produits +
                '}';
    }
}
