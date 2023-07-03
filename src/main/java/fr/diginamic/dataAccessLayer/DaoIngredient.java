package fr.diginamic.dataAccessLayer;

import fr.diginamic.entities.Ingredient;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

public class DaoIngredient implements I_Crud {

    private Ingredient ingredient;

    public DaoIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    @PersistenceContext
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("IBOOF-JPA");
    private final EntityManager em = emf.createEntityManager();

    @Transactional
    @Override
    public void ajouter() {
        em.createNativeQuery("INSERT INTO Ingredient (nom_ingredient) VALUES (?)")
                .setParameter(1, this.ingredient.getNom_ingredient())
                .executeUpdate();
    }

    @Transactional
    @Override
    public void modifier() {
        em.createNativeQuery("UPDATE Ingredient as ingt SET ingt.nom_ingredient = ingt.nom_ingredient + :increment)")
                .setParameter("increment", this.ingredient.getNom_ingredient())
                .executeUpdate();
    }

    @Transactional
    @Override
    public void supprimer() {
        em.createNativeQuery("DELETE FROM Ingredient as ingt WHERE ingt.nom_ingredient = ?")
                .setParameter(1, this.ingredient
                        .getNom_ingredient()).executeUpdate();
    }
}
