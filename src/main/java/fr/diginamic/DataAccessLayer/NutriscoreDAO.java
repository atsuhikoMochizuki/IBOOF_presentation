package fr.diginamic.DataAccessLayer;

import fr.diginamic.entities.Nutriscore;
import fr.diginamic.utilitaires.mochizukiTools.Utils;
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
            Nutriscore nutri2 = em.find(Nutriscore.class, 1);
            if (nutri2 != null){
                System.out.println("troivé");

            }
            else
                System.out.println("perdu");
        }

    }

    @Override
    public void updateNutriscore(Nutriscore nutriscore) {
        Utils.msgInfo("modificaiton d'un nutriscore avec Jpa");
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("IBOOF-JPA");
             EntityManager em = emf.createEntityManager();
        ) {

            Nutriscore nutri = em.find(Nutriscore.class,nutriscore.getId_nutriscore());
            if(nutri != null){
               nutri.setValeurScore("P");
            }


        }
    }


}

