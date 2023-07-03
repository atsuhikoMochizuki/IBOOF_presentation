package fr.diginamic.services;

import fr.diginamic.DataAccessLayer.DAOFactory;
import fr.diginamic.DataAccessLayer.INutriscoreDAO;
import fr.diginamic.entities.Nutriscore;

public class Services {

    private static Services singleton;

    private Services() {}

    public static Services getSingleton() {
        if (null == singleton) {
            singleton = new Services();
        }
        return singleton;
    }

    public void createNutriscore(Nutriscore nutriscore) {

        INutriscoreDAO dao = DAOFactory.getNutriscoreDAO();
        dao.createNutriscore(nutriscore);
    }
    public void updateNutriscore(Nutriscore nutriscore) {
        INutriscoreDAO dao = DAOFactory.getNutriscoreDAO();
        dao.updateNutriscore(nutriscore);
    }
}
