package service;

import entities.CategorieProduit;
import repository.CategorieProduitRepository;
import java.util.List;

public class CategorieProduitService {
    private CategorieProduitRepository repository;
    
    // Le constructeur crée lui-même la liste en mémoire via le repository
    public CategorieProduitService() {
        this.repository = new CategorieProduitRepository();
    }

    public void ajouterCategorie(CategorieProduit categorie) {
        repository.save(categorie);
    }

    public List<CategorieProduit> listerCategories() {
        return repository.findAll();
    }

    public CategorieProduit rechercherParId(int id) {
        return repository.findById(id);
    }
}
