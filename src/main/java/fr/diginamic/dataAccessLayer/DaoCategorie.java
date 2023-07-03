package fr.diginamic.dataAccessLayer;

import fr.diginamic.entities.Categorie;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

public class DaoCategorie implements I_Crud {

    private Categorie categorie;

    public DaoCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    @PersistenceContext
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("IBOOF-JPA");
    private final EntityManager em = emf.createEntityManager();

    @Transactional
    @Override
    public void ajouter() {
        em.createNativeQuery("INSERT INTO Categorie (nom_categorie) VALUES (?)")
                .setParameter(1, this.categorie.getNom_categorie())
                .executeUpdate();
    }

    @Transactional
    @Override
    public void modifier() {
        em.createNativeQuery("UPDATE Categorie as categ SET categ.nom_categorie = categ.nomm_categorie + :increment)")
                .setParameter("increment", this.categorie.getNom_categorie())
                .executeUpdate();
    }

    @Transactional
    @Override
    public void supprimer() {
        em.createNativeQuery("DELETE FROM Categorie as categ WHERE categ.nom_marque = ?")
                .setParameter(1, this.categorie
                        .getNom_categorie()).executeUpdate();
    }
}
