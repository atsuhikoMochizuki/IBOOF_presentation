package fr.diginamic.dataAccessLayer;

import fr.diginamic.entities.Allergene;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

public class DaoAllergene implements I_Crud {

    private Allergene allergene;

    public DaoAllergene(Allergene allergene) {
        this.allergene = allergene;
    }

    @PersistenceContext
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("IBOOF-JPA");
    private final EntityManager em = emf.createEntityManager();

    @Transactional
    @Override
    public void ajouter() {
        em.createNativeQuery("INSERT INTO Allergene (nom_allergene) VALUES (?)")
                .setParameter(1, this.allergene.getNom_allergene())
                .executeUpdate();
    }

    @Transactional
    @Override
    public void modifier() {
        em.createNativeQuery("UPDATE Allergene as allerg SET allerg.nom_allergene = allerg.nom_allergene + :increment)")
                .setParameter("increment", this.allergene.getNom_allergene())
                .executeUpdate();
    }

    @Transactional
    @Override
    public void supprimer() {
        em.createNativeQuery("DELETE FROM Allergene as allerg WHERE allerg.nom_allergene = ?")
                .setParameter(1, this.allergene
                        .getNom_allergene()).executeUpdate();
    }
}
