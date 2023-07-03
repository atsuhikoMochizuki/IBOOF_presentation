package fr.diginamic.dataAccessLayer;

import fr.diginamic.entities.Produit;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

public class DaoProduit implements I_Crud {

    private Produit produit;

    private DaoProduit(Produit produit) {
        this.produit = produit;
    }

    @PersistenceContext
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("IBOOF-JPA");
    private final EntityManager em = emf.createEntityManager();

    @Transactional
    @Override
    public void ajouter() {
        em.createNativeQuery("INSERT INTO Produit (nom_produit) VALUES (?)")
                .setParameter(1, this.produit.getNom_produit())
                .executeUpdate();
    }

    @Transactional
    @Override
    public void modifier() {
        em.createNativeQuery("UPDATE Produit as prod SET prod.nom_produit = prod.nom_produit + :increment)")
                .setParameter("increment", this.produit.getNom_produit()).executeUpdate();
    }

    @Transactional
    @Override
    public void supprimer() {
        em.createNativeQuery("DELETE FROM Produit as prod WHERE prod.nom_produit = ?")
                .setParameter(1, this.produit
                        .getNom_produit()).executeUpdate();
    }
}
