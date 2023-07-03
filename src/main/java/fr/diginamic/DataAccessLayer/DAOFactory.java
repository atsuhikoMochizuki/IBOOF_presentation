package fr.diginamic.DataAccessLayer;

public final class DAOFactory {
    private DAOFactory() {
    }
    public static INutriscoreDAO getNutriscoreDAO() {
        INutriscoreDAO dao = new NutriscoreDAO();
        return dao;
    }
}
