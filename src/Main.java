import service.*;
import view.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Initialisation du Scanner partagé pour toute l'application
        Scanner scanner = new Scanner(System.in);

        // Instanciation de la couche SERVICE (avec injection des repositories)
        CategorieProduitService categorieService = new CategorieProduitService();
        ClientService clientService = new ClientService();
        ProduitService produitService = new ProduitService();
        FacturationService facturationService = new FacturationService();
        
        // CommandeService a besoin de FacturationService pour la génération automatique
        CommandeService commandeService = new CommandeService(facturationService);
        // PaiementService a besoin de FacturationService pour mettre à jour les statuts
        PaiementService paiementService = new PaiementService(facturationService);

        // Instanciation de la couche VIEW (avec injection des services et du scanner)
        CategorieProduitView categorieView = new CategorieProduitView(categorieService, scanner);
        ClientView clientView = new ClientView(clientService, scanner);
        ProduitView produitView = new ProduitView(produitService, categorieService, scanner);
        CommandeView commandeView = new CommandeView(commandeService, clientService, produitService, scanner);
        FacturationView facturationView = new FacturationView(facturationService, scanner);
        PaiementView paiementView = new PaiementView(paiementService, facturationService, scanner);


        // Instanciation du Menu Principal et lancement de l'application
        MenuView menuPrincipal = new MenuView(
            categorieView, 
            clientView, 
            produitView, 
            commandeView, 
            facturationView, 
            paiementView, 
            scanner
        );

        // Lancement de l'application
        menuPrincipal.afficherMenuPrincipal();

        // Fermeture propre du scanner à la fermeture de l'application
        scanner.close();
    }
}
