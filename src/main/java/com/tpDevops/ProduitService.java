package com.tpDevops;

import java.util.ArrayList;
import java.util.List;

public class ProduitService {

    private List<Produit> produits;

    public ProduitService() {
        this.produits = new ArrayList<>();
    }

    // Fonction pour ajouter un produit
    public void ajouterProduit(Produit produit) {
        // Vérification de l'unicité du produit
        if (!produitExiste(produit.getId(), produit.getNom())) {
            // Validation des données
            if (produit.getPrix() > 0 && produit.getQuantite() > 0) {
                produits.add(produit);
                System.out.println("Produit ajouté avec succès.");
            } else {
                System.out.println("Erreur : Le prix et la quantité doivent être positifs.");
            }
        } else {
            System.out.println("Erreur : Un produit avec le même ID ou nom existe déjà.");
        }
    }
      // Fonction pour vérifier si un produit existe déjà
    private boolean produitExiste(Long id, String nom) {
        return produits.stream().anyMatch(p -> p.getId().equals(id) || p.getNom().equals(nom));
    }

    // Fonction pour vérifier si un produit existe déjà (utilisée pour la modification et la suppression)
    private boolean produitExiste(Long id) {
        return produits.stream().anyMatch(p -> p.getId().equals(id));
    }
}