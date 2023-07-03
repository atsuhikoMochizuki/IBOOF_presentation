package fr.diginamic.ihm;

import fr.diginamic.services.subSystems.I_IboofManager;
import fr.diginamic.utilitaires.mochizukiTools.Utils;
import fr.diginamic.services.subSystems.Services_IboofManager;

import java.util.ResourceBundle;
import java.util.Scanner;

public class Ihm_CLI {
    public static void display() {
        Utils.clearConsole();   //Nota : clearConsole n'a pas la possibilité de fonctionner dans un IDE
        String choix1, choix2;
        Scanner scanner = new Scanner(System.in);
        boolean existRequest = false;
        String MENU = """
                    (\\
                     \\ \\
                 __    \\/ ___,.-------..__                   
                //\\\\ _,-'\\\\               `'--._ //\\\\            ___ ____   ___   ___  _____
                \\\\ ;'      \\\\                   `: //           |_ _| __ ) / _ \\ / _ \\|  ___| 
                 `(          \\\\                   )'             | ||  _ \\| | | | | | | |_   
                   :.          \\\\,----,         ,;               | || |_) | |_| | |_| |  _| 
                    `.`--.___   (    /  ___.--','               |___|____/ \\___/ \\___/|_|  
                      `.     ``-----'-''     ,'                 Immersive & Best open fOOd Facts advisor
                         -.               ,-
                            `-._______.-'
                                
                                                    Menu:
                                                    
                1 -> Choisissez votre marque pour afficher les meilleurs produits associés
                2 -> Choisissez votre catégorie pour afficher les meilleurs produits associés
                3 -> Choisissez votre marque et votre catégorie pour afficher les meilleurs produits associés
                4 -> Choisissez votre ingrédient et découvrez le nombre de produits dans lesquels il apparaît
                5 -> Choisissez votre allergène et découvrez le nombre de produits dans lesquels il apparaît
                6 -> Choisissez votre additif et découvrez le nombre de produits dans lesquels il apparaît
                7 -> RECHARGEMENT DE LA BASE DE DONNEES
                8 -> SORTIR DE L'APPLICATION""";


//        Utils.msgInfo("Initialisation des services...Veuillez patienter");
//        Services services = new Services();   //C'est pas bon ca, ca n'a pas à être dans l'iHM
        //l'IHM est indépendante de ce qu'elle déclenche, elle doit
        //juste appeler les éléments de ton interface IServices
        //Ce n'est pas une instance de Services que tu dois appeler
        //mais de IServices, genre IService service = new Service();
        Utils.clearConsole();
        System.out.println("\n" + MENU);

        while (true) {
            System.out.println("Entrez votre choix:");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> {
                    Utils.msgPrompt("Entrez votre marque:");
                    choix1 = scanner.nextLine();
                    Utils.msgInfo("Lancement de la recherche");
//                    if (services.getMarque().equalsIgnoreCase(String.valueOf(choix1))) {
//                        services.search_BestProducts_byBrand();
//                    }
                }

                case "2" -> {
                    Utils.msgPrompt("Entrez votre catégorie:");
                    choix1 = scanner.nextLine();
                    Utils.msgInfo("Lancement de la recherche");
//                    if (services.getCategorie().equalsIgnoreCase(String.valueOf(choix))) {
//                        services.search_BestProducts_byCategory();
//                    }
                }

                case "3" -> {
                    Utils.msgPrompt("Entrez votre marque:");
                    choix1 = scanner.nextLine();
                    Utils.msgPrompt("Entrez votre catégorie:");
                    choix2 = scanner.nextLine();
                    Utils.msgInfo("Lancement de la recherche");

                    //ATTENTION : l'interface doit être indépendante!
                    //La condition que tu déroule ici doit être réalisé dans la classe
                    //qui implémente l'interface Iservice
//                   if (services.getMarque().equalsIgnoreCase(String.valueOf(choix1));
//                            && services.getCategorie().equalsIgnoreCase(String.valueOf(choixCategorie))) {
//                        services.search_BestProducts_byBrandAndCategorie();
////                    }
                }

                case "4" -> {
                    Utils.msgPrompt("Entrez votre ingrédient:");
                    choix1 = scanner.nextLine();
                    Utils.msgInfo("Lancement de la recherche");
                    //services.search_IngredientsNbrInProduct();
                }

                case "5" -> {
                    Utils.msgPrompt("Entrez votre allergène:");
                    choix1 = scanner.nextLine();
                    Utils.msgInfo("Lancement de la recherche");
//                    services.search_AllergensNbrInProduct();
                }

                case "6" -> {
                    Utils.msgPrompt("Entrez votre additif:");
                    choix1 = scanner.nextLine();
                    Utils.msgInfo("Lancement de la recherche");
//                    services.search_AdditivesNbrInProduct();
                }

                case "7" -> {
                    String fileTestPath = ResourceBundle.getBundle("project").getString("project.TEST_csvfile");
                    I_IboofManager iboofManager = new Services_IboofManager(fileTestPath);
                    iboofManager.mapCsvFileToDatabase();
                }


                case "8" -> {
                    existRequest = true;
                    scanner.close();
                }

                default -> {
                    Utils.msgWarning("Entrée incorrecte");
                    Utils.beep();
                }
            }
            if (existRequest) {
                System.out.println("Sortie du programme");
                break;
            }
        }
    }
}