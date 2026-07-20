package service;

import entities.Client;
import repository.ClientRepository;
import java.util.List;

public class ClientService {
    private ClientRepository repository;

    public ClientService() {
        this.repository = new ClientRepository();
    }

    public void ajouterClient(Client client) {
        repository.save(client);
    }

    public List<Client> listerClients() {
        return repository.findAll();
    }

    // BONUS : Recherche d'un client par son numéro de téléphone
    public Client rechercherParTelephone(String telephone) {
        return repository.findByTelephone(telephone);
    }
}
