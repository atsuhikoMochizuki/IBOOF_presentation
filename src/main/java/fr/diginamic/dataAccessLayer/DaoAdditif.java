package fr.diginamic.dataAccessLayer;

import fr.diginamic.entities.Additif;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

public class DaoAdditif implements I_Crud {

    private Additif additif;

    public DaoAdditif(Additif additif) {
        this.additif = additif;
    }

    @PersistenceContext
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("IBOOF-JPA");
    private final EntityManager em = emf.createEntityManager();

    @Transactional
    @Override
    public void ajouter() {
        em.createNativeQuery("INSERT INTO Additif (nom_additif) VALUES (?)")
                .setParameter(1, this.additif.getNom_additif())
                .executeUpdate();
    }

    @Transactional
    @Override
    public void modifier() {
        em.createNativeQuery("UPDATE Additif as addti SET addti.nom_additif = addti.nom_additif + :increment)")
                .setParameter("increment", this.additif.getNom_additif())
                .executeUpdate();
    }

    @Transactional
    @Override
    public void supprimer() {
        em.createNativeQuery("DELETE FROM Additif as addti WHERE addti.nom_additif = ?")
                .setParameter(1, this.additif
                        .getNom_additif()).executeUpdate();
    }
}
