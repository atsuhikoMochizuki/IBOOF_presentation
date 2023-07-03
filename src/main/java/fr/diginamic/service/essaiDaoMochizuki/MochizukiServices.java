package fr.diginamic.service.essaiDaoMochizuki;

import fr.diginamic.dataAccessLayer.daoMochizuki.DAOFactory;
import fr.diginamic.dataAccessLayer.daoMochizuki.INutriscoreDAO;
import fr.diginamic.entities.Nutriscore;

public class MochizukiServices {

    private static MochizukiServices singleton;

    private MochizukiServices() {}

    public static MochizukiServices getSingleton() {
        if (null == singleton) {
            singleton = new MochizukiServices();
        }
        return singleton;
    }

    public void createNutriscore(Nutriscore nutriscore) {

        INutriscoreDAO dao = DAOFactory.getNutriscoreDAO();
        dao.createNutriscore(nutriscore);
    }
    public void updateNutriscore(Nutriscore nutriscore) {
        System.out.println("modification du nutriscore");
    }
}
