package fr.diginamic.dataAccessLayer.daoMochizuki;

import fr.diginamic.entities.Nutriscore;
import fr.diginamic.mochizukiTools.Utils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class NutriscoreDAO implements INutriscoreDAO {
    @Override
    public void createNutriscore(Nutriscore nutriscore) {
        Utils.msgInfo("Création d'un nutriscore avec Jpa");
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("IBOOF-JPA");
             EntityManager em = emf.createEntityManager();
        ) {
            em.getTransaction().begin();
            em.persist(nutriscore);
            em.getTransaction().commit();
        }
    }

    @Override
    public void updateNutriscore(Nutriscore nutriscore) {

    }
}

