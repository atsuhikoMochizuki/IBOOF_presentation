package fr.diginamic.service;

import fr.diginamic.entities.Categorie;
import fr.diginamic.entities.Marque;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

public class Services_search implements I_services_search {

    private Marque marque;
    private Categorie categorie;

    String nomMarque;
    String nomCategorie;

    public Services_search() {
    }

    @PersistenceContext
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("IBOOF-JPA");
    private final EntityManager em = emf.createEntityManager();


    @Transactional
    @Override
    public void search_BestProducts_byBrand() {
        List resultList = em.createNativeQuery(
                        "SELECT prod.nom_produit FROM Produit AS prod, " +
                                "Marque AS marq, " +
                                "NutriScore AS nutri " +
                                "WHERE marq.nom_marque = ? " +
                                "AND nutri.valeurScore = \"a\"" +
                                "LIMIT 10")
                .setParameter(1, getMarque())
                .getResultList();
        resultList.forEach(System.out::println);
    }

    @Transactional
    @Override
    public void search_BestProducts_byCategory() {
        List resultList = em.createNativeQuery(
                        "SELECT prod.nom_produit FROM Produit AS prod, " +
                                "Categorie AS categ, " +
                                "NutriScore AS nutri " +
                                "WHERE categ.nom_categorie = ? " +
                                "AND nutri.valeurScore = \"a\"" +
                                "LIMIT 10")
                .setParameter(1, getCategorie())
                .getResultList();
        resultList.forEach(System.out::println);
    }

    @Transactional
    @Override
    public void search_BestProducts_byBrandAndCategorie() {
        List resultList = em.createNativeQuery(
                        "SELECT prod.nom_produit FROM Produit AS prod, " +
                                "Categorie AS categ, " +
                                "Marque AS marq, " +
                                "NutriScore AS nutri " +
                                "WHERE categ.nom_categorie = ? " +
                                "AND marq.nom_marque = ? " +
                                "AND nutri.valeurScore = \"a\"" +
                                "LIMIT 10")
                .setParameter(1, getCategorie())
                .setParameter(2, getMarque())
                .getResultList();
        resultList.forEach(System.out::println);
    }

    @Transactional
    @Override
    public void search_IngredientsNbrInProduct() {
        List resultList = em.createNativeQuery("SELECT ingt, COUNT(prod) FROM Produit AS prod" +
                ", Ingredient AS ingt " +
                "WHERE ingdt" +
                "GROUP BY prod " +
                "ORDER BY DESC" +
                "LIMIT 10").getResultList();
        resultList.forEach(System.out::println);
    }

    @Transactional
    @Override
    public void search_AllergensNbrInProduct() {
        List resultList = em.createNativeQuery("SELECT allerg, COUNT(prod) FROM Produit AS prod" +
                ", Allergene AS allerg" +
                "WHERE allerg" +
                "GROUP BY prod " +
                "ORDER BY DESC" +
                "LIMIT 10").getResultList();
        resultList.forEach(System.out::println);
    }

    @Transactional
    @Override
    public void search_AdditivesNbrInProduct() {
        List resultList = em.createNativeQuery("SELECT additi, COUNT(prod) FROM Produit AS prod" +
                ", Additif AS additi" +
                "WHERE additi" +
                "GROUP BY prod " +
                "ORDER BY DESC" +
                "LIMIT 10").getResultList();
        resultList.forEach(System.out::println);
    }

    public String getMarque() {
        nomMarque = this.marque.getNom_marque();
        return nomMarque;
    }

    public String getCategorie() {
        nomCategorie = this.categorie.getNom_categorie();
        return nomCategorie;
    }
}
