package fr.diginamic.dataAccessLayer.daoMochizuki;

public final class DAOFactory {
    private DAOFactory() {
    }
    public static INutriscoreDAO getNutriscoreDAO() {
        INutriscoreDAO dao = new NutriscoreDAO();
        return dao;
    }
}
