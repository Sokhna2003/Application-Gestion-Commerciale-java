package view;

import entities.CategorieProduit;
import entities.Produit;
import service.CategorieProduitService;
import service.ProduitService;
import java.util.Scanner;

public class ProduitView {
    private ProduitService service;
    private CategorieProduitService catService;
    private Scanner scanner;

    public ProduitView(ProduitService service, CategorieProduitService catService, Scanner scanner) {
        this.service = service;
        this.catService = catService;
        this.scanner = scanner;
    }

    public void afficherMenu() {
        int choix = 0;
        do {
            System.out.println("\n--- GESTION DES PRODUITS ---");
            System.out.println("1. Ajouter un produit");
            System.out.println("2. Lister les produits");
            System.out.println("3. Rechercher un produit par libellé ");
            System.out.println("4. Modifier la quantite en stock ");
            System.out.println("5. Retour au menu principal");
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine();

            if (choix == 1) {
                if (catService.listerCategories().isEmpty()) {
                    System.out.println("Veuillez d'abord créer au moins une catégorie de produit !");
                    return;
                }

                String libelle = "";
                while (libelle.trim().isEmpty()) {
                    System.out.print("Libellé (Obligatoire) : ");
                    libelle = scanner.nextLine();
                }

                int qte = -1;
                while (qte < 0) {
                    System.out.print("Quantité en stock (>= 0) : ");
                    qte = scanner.nextInt();
                }

                double pu = 0;
                while (pu <= 0) {
                    System.out.print("Prix unitaire (> 0) : ");
                    pu = scanner.nextDouble();
                }
    
                System.out.println("Sélectionnez l'ID de la catégorie :");
                for (CategorieProduit cat : catService.listerCategories()) {
                    cat.toChaine();
                }
                int idCat = scanner.nextInt();
                CategorieProduit selectedCat = catService.rechercherParId(idCat);

                if (selectedCat != null) {
                    Produit p = new Produit(0, libelle, qte, pu, selectedCat);
                    service.ajouterProduit(p);
                    selectedCat.addProduit(p);
                    System.out.println("Produit enregistré !");
                } else {
                    System.out.println("Catégorie invalide. Échec de l'ajout.");
                }
            } else if (choix == 2) {
                System.out.println("\nListe des produits :");
                for (Produit p : service.listerProduits()) {
                    p.toChaine();
                }
            } else if (choix == 3) {
                System.out.print("Entrez le libellé exact : ");
                String libelle = scanner.nextLine();
                Produit p = service.rechercherParLibelle(libelle);
                if (p != null) {
                    p.toChaine();
                } else {
                    System.out.println("Produit non trouvé.");
                }
            } else if (choix == 4) {
                System.out.print("Entrez l'ID du produit à modifier : ");
                int id = scanner.nextInt();
                System.out.print("Entrez la nouvelle quantité en stock : ");
                int nqte = scanner.nextInt();
                if (service.modifierQuantiteStock(id, nqte)) {
                    System.out.println("Stock mis à jour avec succès !");
                } else {
                    System.out.println("Échec de la mise à jour (Produit introuvable ou quantité négative).");
                }
            }
        } while (choix != 5);
    }
}
