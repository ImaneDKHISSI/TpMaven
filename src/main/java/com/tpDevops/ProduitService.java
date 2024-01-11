package com.tpDevops;

import java.util.ArrayList;
import java.util.List;

public class ProduitService {

	private List<Produit> produits = new ArrayList<>();

	public boolean produitExiste(Long id, String nom) {
	    for (Produit p : produits) {
	        if (p.getId().equals(id) || p.getNom().equals(nom)) {
	            return true;
	        }
	    }
	    return false;
	}
	
	public boolean ValidationProduit(double prix, int quantite) {
	    return prix >= 0 && quantite >= 0;
	}

    public void createProduit(Produit produit) {
      
    	    if (!produitExiste(produit.getId(), produit.getNom())) {
    	        if (ValidationProduit(produit.getPrix(), produit.getQuantite())) {
    	            produits.add(produit);
    	        } else {
    	            // Gestion d'exception : Valeurs invalides
    	        	
    	            throw new IllegalArgumentException("Le prix et la quantité doivent être positifs.");
    	        }
    	    } else {
    	        // Gestion d'exception : Produit déjà existant
    	    	
    	        throw new IllegalArgumentException("Un produit avec le même ID ou nom existe déjà.");
    	    }
    		}
   public Produit readProduit(Long id) {
	        for (Produit p : produits) {
	            if (p.getId().equals(id)) {
	                return p;
	            }
	        }
	        return null;
	    }
   public void updateProduit(Produit updatedProduit) {
	    for (Produit existingProd : produits) {
	        if (existingProd.getId().equals(updatedProduit.getId())) {
	            for (Produit p : produits) {
	                if (!existingProd.equals(p) && p.getNom().equals(updatedProduit.getNom())) {
	                    throw new IllegalArgumentException("Un produit avec le même nom existe déjà.");
	                }
	            }

	            existingProd.setNom(updatedProduit.getNom());
	            existingProd.setPrix(updatedProduit.getPrix());
	            existingProd.setQuantite(updatedProduit.getQuantite());

	            return;
	        }
	    }
	    throw new IllegalArgumentException("Produit non trouvé pour la mise à jour.");
	}

   public void deleteProduit(Long id) {
	    Produit produitToRemove = null;

	    for (int i = 0; i < produits.size(); i++) {
	        Produit p = produits.get(i);
	        if (p.getId().equals(id)) {
	            produitToRemove = p;
	            produits.remove(i);
	            return; // Arrêter la méthode après avoir supprimé l'élément
	        }
	    }
	}




}

