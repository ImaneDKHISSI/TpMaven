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
}
