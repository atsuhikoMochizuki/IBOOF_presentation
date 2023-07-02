package fr.diginamic.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Cacheable
public class Nutriscore {
    //Attributs
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id_nutriscore;
    @Column
    private String valeurScore;

    //relations
    @OneToMany(mappedBy = "nutriscore")
    private Set<Produit> produits;

    //Constructeurs
    public Nutriscore() {
    }

    public Nutriscore(String valeurScore) {
        this.valeurScore = valeurScore;
    }

    public Nutriscore(Integer id_nutriscore, String valeurScore) {
        this.id_nutriscore = id_nutriscore;
        this.valeurScore = valeurScore;
    }

    //Getters
    public Integer getId_nutriscore() {
        return id_nutriscore;
    }

    public String getValeurScore() {
        return valeurScore;
    }

    public Set<Produit> getProduits() {
        return produits;
    }

    //Setters
    public void setId_nutriscore(Integer id_nutriscore) {
        this.id_nutriscore = id_nutriscore;
    }

    public void setValeurScore(String valeurScore) {
        this.valeurScore = valeurScore;
    }

    public void setProduits(Set<Produit> produits) {
        this.produits = produits;
    }

    //ToString()


    @Override
    public String toString() {
        return "Nutriscore{" +
                "id_nutriscore=" + id_nutriscore +
                ", valeurScore='" + valeurScore + '\'' +
                ", produits=" + produits +
                '}';
    }
}
