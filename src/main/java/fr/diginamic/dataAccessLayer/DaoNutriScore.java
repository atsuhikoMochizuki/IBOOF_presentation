package fr.diginamic.dataAccessLayer;

import fr.diginamic.entities.Nutriscore;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

public class DaoNutriScore implements I_Crud {
    private Nutriscore nutriscore;

    public DaoNutriScore(Nutriscore nutriscore) {
        this.nutriscore = nutriscore;
    }

    @PersistenceContext
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("IBOOF-JPA");
    private final EntityManager em = emf.createEntityManager();

    @Transactional
    @Override
    public void ajouter() {
        em.createNativeQuery("INSERT INTO NutriScore (valeurScore) VALUES (?)")
                .setParameter(1, this.nutriscore.getValeurScore())
                .executeUpdate();
    }

    @Transactional
    @Override
    public void modifier() {
        em.createNativeQuery("UPDATE NutriScore as nutri SET nutri.valeurScore = nutri.valeurScore + :increment)")
                .setParameter("increment", this.nutriscore.getValeurScore())
                .executeUpdate();
    }

    @Transactional
    @Override
    public void supprimer() {
        em.createNativeQuery("DELETE FROM NutriScore as nutri WHERE nutri.valeurScore = ?")
                .setParameter(1, this.nutriscore
                        .getValeurScore()).executeUpdate();
    }
}
