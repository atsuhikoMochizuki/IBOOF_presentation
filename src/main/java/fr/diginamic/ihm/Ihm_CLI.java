package fr.diginamic.ihm;

import fr.diginamic.service.Services;

import java.util.Scanner;

public class Ihm_CLI {
    public static void main(String[] args) {
        String menu = """
                Hello Commencer votre application
                Menu:
                1 -> Choisissez votre marque pour afficher les meilleurs produits associés
                2 -> Choisissez votre catégorie pour afficher les meilleurs produits associés
                3 -> Choisissez votre marque et votre categorie pour afficher les meilleurs produits associés
                4 -> Choisissez votre ingredient et aaficher le nombre de produits dans lesquels il apparaît
                5 -> Choisissez votre allergene et aaficher le nombre de produits dans lesquels il apparaît
                6 -> Choisissez votre additif et aficher le nombre de produits dans lesquels il apparaît
                7 -> SORTIR DE L'APPLICATION
                Choisissez votre service:
                """;
        Scanner scanner = new Scanner(System.in);

        Services services = new Services();

        while (!scanner.hasNext("x")) {
            System.out.println(menu);
            switch (scanner.next()) {
                case "1" -> {
                    System.out.println("Choix de la marque : ");
                    Scanner choixMarque = new Scanner(System.in);
                    if (services.getMarque().equalsIgnoreCase(String.valueOf(choixMarque))) {
                        services.service1();
                    }
                }
                case "2" -> {
                    System.out.println("Choix de la Categorie : ");
                    Scanner choixCategorie = new Scanner(System.in);
                    if (services.getCategorie().equalsIgnoreCase(String.valueOf(choixCategorie))) {
                        services.service2();
                    }
                }
                case "3" -> {
                    System.out.println("Choix de la marque : ");
                    Scanner choixMarque = new Scanner(System.in);
                    System.out.println("Choix de la Categorie : ");
                    Scanner choixCategorie = new Scanner(System.in);
                    if (services.getMarque().equalsIgnoreCase(String.valueOf(choixMarque))
                            && services.getCategorie().equalsIgnoreCase(String.valueOf(choixCategorie))) {
                        services.service3();
                    }
                }
                case "4" -> services.service4();
                case "5" -> services.service5();
                case "6" -> services.service6();
                default -> System.out.println("Invalid response");
            }
        }
        System.out.println("Fin du programme");

    }
}
