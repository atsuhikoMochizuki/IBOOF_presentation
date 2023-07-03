package fr.diginamic.service;

public interface I_services_search {

    void search_BestProducts_byBrand();
    void search_BestProducts_byCategory();
    void search_BestProducts_byBrandAndCategorie();
    void search_IngredientsNbrInProduct();
    void search_AllergensNbrInProduct();
    void search_AdditivesNbrInProduct();

    //Il faudra fusionner nos deux classes de service
//    void mapCsvFileToDatabase();
}
