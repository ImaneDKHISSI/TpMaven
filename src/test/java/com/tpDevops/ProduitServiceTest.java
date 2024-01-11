package com.tpDevops;
import static org.junit.Assert.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class ProduitServiceTest {
	 private ProduitService produitService;
	    

	    @Before
	    public void setUp() throws Exception {
	        produitService = new ProduitService();
	    }
	    @Test
	    public void testAjouterProduit() {
	        Produit produit = new Produit(1L, "Ordinateur", 800.0, 5);
	        produitService.ajouterProduit(produit);
	        List<Produit> produits = produitService.listerProduits();
	        assertTrue(produits.contains(produit));
	    }
	    @Test
	    public void testAjouterProduitProduitExisteDeja() {
	        Produit produit1 = new Produit(1L, "Ordinateur", 800.0, 5);
	        Produit produit2 = new Produit(1L, "Portable", 1000.0, 3);
	        produitService.ajouterProduit(produit1);
	        produitService.ajouterProduit(produit2);
	        List<Produit> produits = produitService.listerProduits();
	        assertFalse(produits.contains(produit2));
	    }

	    @Test
	    public void testAjouterProduitPrixEtQuantiteNegatifs() {
	        Produit produit = new Produit(1L, "Ordinateur", -800.0, -5);
	        produitService.ajouterProduit(produit);
	        List<Produit> produits = produitService.listerProduits();
	        assertFalse(produits.contains(produit));
	    }
	    @Test
	    public void testModifierProduitProduitExiste() {
	        Produit produit1 = new Produit(1L, "Ordinateur", 800.0, 5);
	        produitService.ajouterProduit(produit1);

	        Produit produit2 = new Produit(1L, "Portable", 1000.0, 3);
	        produitService.modifierProduit(produit2);

	        List<Produit> produits = produitService.listerProduits();

	        // Utilisez une méthode de comparaison appropriée ou vérifiez chaque attribut individuellement
	        assertTrue(produits.stream().anyMatch(p -> p.getId().equals(produit2.getId()) && p.getNom().equals(produit2.getNom())));
	        assertFalse(produits.stream().anyMatch(p -> p.getId().equals(produit1.getId())));
	    }
	    @Test
	    public void testModifierProduitProduitInexistant() {
	        Produit produit = new Produit(1L, "Ordinateur", 800.0, 5);
	        produitService.modifierProduit(produit);
	        List<Produit> produits = produitService.listerProduits();
	        assertFalse(produits.contains(produit));
	    }
	    @Test
	    public void testSupprimerProduitProduitExiste() {
	        Produit produit = new Produit(1L, "Ordinateur", 800.0, 5);
	        produitService.ajouterProduit(produit);

	        produitService.supprimerProduit(1L);

	        List<Produit> produits = produitService.listerProduits();
	        assertFalse(produits.contains(produit));
	    }
	    @Test
	    public void testSupprimerProduitProduitInexistant() {
	        produitService.supprimerProduit(1L);
	        List<Produit> produits = produitService.listerProduits();
	        assertTrue(produits.isEmpty());
	    }
	    @After
	    public void tearDown() throws Exception {
	    	 produitService= null;
	    }

}
