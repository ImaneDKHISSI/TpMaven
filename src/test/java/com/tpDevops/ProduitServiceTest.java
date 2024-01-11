package com.tpDevops;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProduitServiceTest {
	private ProduitService produitService;

	@Before
	public void setUp() {
		produitService = new ProduitService();
	}

	@Test
	public void testCreateProduit() {
		Produit produit1 = new Produit(1L, "Produit1", 10.0, 5);

		// Ajout d'un produit valide
		produitService.createProduit(produit1);
		assertTrue("Le produit devrait exister après la création", produitService.produitExiste(1L, "Produit1"));

		// Tentative d'ajout d'un produit existant (devrait échouer)
		try {
	        produitService.createProduit(produit1);
	        // Si aucune exception n'est levée, la ligne suivante échouera
	        assertTrue("Devrait lever une exception pour produit existant", false);
	    } catch (IllegalArgumentException exExisting) {
	        assertEquals("Un produit avec le même ID ou nom existe déjà.", exExisting.getMessage());
	    }

		// Tentative d'ajout d'un produit avec des valeurs invalides (devrait échouer)
		Produit produit2 = new Produit(2L, "Produit2", -5.0, 3);
		 try {
		        produitService.createProduit(produit2);
		        // Si aucune exception n'est levée, la ligne suivante échouera
		        assertTrue("Devrait lever une exception pour valeurs invalides", false);
		    } catch (IllegalArgumentException exInvalid) {
		        assertEquals("Le prix et la quantité doivent être positifs.", exInvalid.getMessage());
		    }
	}

	@Test
	public void testReadProduit() {
		Produit produit1 = new Produit(1L, "Produit1", 10.0, 5);
		produitService.createProduit(produit1);

		// Lecture d'un produit existant
		Produit result = produitService.readProduit(1L);
		assertNotNull("Le produit devrait être trouvé", result);
		assertEquals("Produit1", result.getNom());

		// Tentative de lecture d'un produit inexistant (devrait échouer)
		 try {
		        Produit inexistant = produitService.readProduit(2L);
		        assertNull("Le produit inexistant ne devrait pas être trouvé", inexistant);
		    } catch (IllegalArgumentException e) {
		        assertEquals("Le produit avec l'ID 2 n'existe pas.", e.getMessage());
		    }
	}

	@Test
	public void testUpdateProduit() {
		Produit produit1 = new Produit(1L, "Produit1", 10.0, 5);
		produitService.createProduit(produit1);

		// Modification d'un produit existant
		Produit produitToUpdate = new Produit(1L, "NouveauNom", 20.0, 10);
		produitService.updateProduit(produitToUpdate);
		assertEquals("Le nom du produit devrait être mis à jour", "NouveauNom", produit1.getNom());
		assertEquals("Le prix du produit devrait être mis à jour", 20.0, produit1.getPrix(), 0.001);
		assertEquals("La quantité du produit devrait être mise à jour", 10, produit1.getQuantite());

		// Tentative de modification d'un produit inexistant (devrait échouer)
		Produit produit2 = new Produit(2L, "Produit2", 15.0, 8);
		 try {
		        Produit inexistant = produitService.readProduit(2L);
		        assertNull("Le produit inexistant ne devrait pas être trouvé", inexistant);
		    } catch (IllegalArgumentException e) {
		        assertEquals("Le produit avec l'ID 2 n'existe pas.", e.getMessage());
		    }
	}

	@Test
	public void testDeleteProduit() {
		Produit produit1 = new Produit(1L, "Produit1", 10.0, 5);
		produitService.createProduit(produit1);

		// Suppression d'un produit existant
		produitService.deleteProduit(1L);
		assertFalse("Le produit ne devrait pas exister après la suppression", produitService.produitExiste(1L, "Produit1"));

		// Tentative de suppression d'un produit inexistant (devrait échouer)
		 try {
		        Produit inexistant = produitService.readProduit(2L);
		        assertNull("Le produit inexistant ne devrait pas être trouvé", inexistant);
		    } catch (IllegalArgumentException e) {
		        assertEquals("Le produit avec l'ID 2 n'existe pas.", e.getMessage());
		    }
	}

	@After
	public void tearDown() {
		produitService = null;
	}
}
