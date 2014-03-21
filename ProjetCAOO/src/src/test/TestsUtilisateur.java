package src.test;

import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

import src.projet.Enchere;
import src.projet.Etat;
import src.projet.Objet;
import src.projet.Offre;
import src.projet.Utilisateur;

public class TestsUtilisateur {

	Utilisateur user = new Utilisateur("sbailleux", "bailleux", "samuel");	
	Utilisateur user2 = new Utilisateur("apegue", "pegue", "axel");
	
	Objet produit = new Objet("Objet001", "premier objet vente");
	Date dateLimite = new Date();
	Float prixMinimum = new Float(10);
	Float prixReserve = new Float(5);

	// TEST DONNEES UTILISATEUR
	@Test
	public void testCreationUtilisateur() {
		Assert.assertEquals("sbailleux", user.getLogin());
		Assert.assertEquals("bailleux", user.getNom());
		Assert.assertEquals("samuel", user.getPrenom());
	}

	// TEST ROLE UTILISATEUR VENDEUR
	@Test
	public void testRoleUtilisateur() {
		user.setAcheteur(true);
		user.setVendeur(false);

		Assert.assertSame(true, user.getAcheteur());
		Assert.assertSame(false, user.getVendeur());
	}

	// TEST CREATION ENCHERE PAR UN NON VENDEUR
	@Test
	public void testCreationEnchereNonVendeur() {
		user.setVendeur(false);
		Enchere e = user.creerEnchere(user.getLogin(), produit, dateLimite,
				prixMinimum, prixReserve);
		if (e != null) {

			e.setEtat(Etat.PUBLIEE);
			e.setVisible(true);

			Assert.assertEquals(e.getLoginCreateur(), user.getLogin());
			Assert.assertEquals(Etat.PUBLIEE, e.getEtat());
			Assert.assertEquals(new Float(10), e.getPrixMinimum());
			Assert.assertEquals(new Float(5), e.getPrixReserve());
			Assert.assertSame(true, e.getVisible());
		} 
		else {		
			System.out.println("Methode : testCreationEnchereNonVendeur - L'enchere n'a pas ete creee");
			Assert.assertNull(e);
		}
	}
	
	// TEST CREATION ENCHERE PAR UN VENDEUR A L'ETAT PUBLIEE DONC VISIBLE ET SANS OFFRE
	@Test
	public void testCreationEncherePubliee() {
		user.setVendeur(true);
		Enchere e = user.creerEnchere(user.getLogin(), produit, dateLimite,
				prixMinimum, prixReserve);
		if (e != null) {

			e.setEtat(Etat.PUBLIEE);
			e.setVisible(true);

			Assert.assertEquals(e.getLoginCreateur(), user.getLogin());
			Assert.assertEquals(Etat.PUBLIEE, e.getEtat());
			Assert.assertEquals(new Float(10), e.getPrixMinimum());
			Assert.assertEquals(new Float(5), e.getPrixReserve());
			Assert.assertSame(true, e.getVisible());
		} 
		else {		
			System.out.println("Methode : testCreationEncherePubliee - L'enchere n'a pas ete creee");
			Assert.assertNull(e);
		}
	}
	
	// TEST CREATION ENCHERE PAR UN VENDEUR A L'ETAT CREEE ET NON VISIBLE CAR NON PUBLIEE ET SANS OFFRE
	@Test
	public void testCreationEnchereCreee() {
		user.setVendeur(true);
		Enchere e = user.creerEnchere(user.getLogin(), produit, dateLimite,
				prixMinimum, prixReserve);
		if (e != null) {

			e.setEtat(Etat.CREE);
			e.setVisible(false);

			Assert.assertEquals(e.getLoginCreateur(), user.getLogin());
			Assert.assertEquals(Etat.CREE, e.getEtat());
			Assert.assertEquals(new Float(10), e.getPrixMinimum());
			Assert.assertEquals(new Float(5), e.getPrixReserve());
			Assert.assertSame(false, e.getVisible());
		} 
		else {		
			System.out.println("Methode : testCreationEnchereCreee - L'enchere n'a pas ete creee");
			Assert.assertNull(e);
		}
	}

	// TEST CREATION ENCHERE PAR UN VENDEUR A L'ETAT PUBLIEE ET AVEC OFFRE
	@Test
	public void testCreationEncherePublieeAvecOffre() {
		user.setVendeur(true);
		user2.setAcheteur(true);
		
		Enchere e = user.creerEnchere(user.getLogin(), produit, dateLimite, prixMinimum, prixReserve);
		
		if (e != null) 
		{
			Offre offre = new Offre(user2.getLogin(), e, new Float(70));
			e.setEtat(Etat.PUBLIEE);
			e.setVisible(true);
			user2.emettreOffre(offre, e);
			
			Assert.assertEquals(e.getLoginCreateur(), user.getLogin());
			Assert.assertEquals(Etat.PUBLIEE, e.getEtat());
			Assert.assertEquals(new Float(10), e.getPrixMinimum());
			Assert.assertEquals(new Float(5), e.getPrixReserve());
			Assert.assertSame(true, e.getVisible());
			Assert.assertEquals(new Float(70), e.getListeOffres().get(0).getPrix());
			System.out.println("Methode : testCreationEncherePublieeAvecOffre - Offre sur enchere enregistree");			
		} 
		else {		
			System.out.println("Methode : testCreationEncherePublieeAvecOffre - L'enchere n'a pas ete creee");
			Assert.assertNull(e);
		}
	}
	
	
	// TEST CREATION ENCHERE PAR UN VENDEUR A L'ETAT CREEE ET NON VISIBLE CAR NON PUBLIEE ET AVEC OFFRE
	@Test
	public void testCreationEnchereCreeeNonVisibleAvecOffre() {
		user.setVendeur(true);
		Enchere e = user.creerEnchere(user.getLogin(), produit, dateLimite,
				prixMinimum, prixReserve);
		if (e != null) {
			
			Offre offre = new Offre(user2.getLogin(), e, new Float(70));
			e.setEtat(Etat.CREE);
			e.setVisible(false);
			int result = user2.emettreOffre(offre, e);
			if(result == 0)
			{
				Assert.assertEquals(e.getLoginCreateur(), user.getLogin());
				Assert.assertEquals(Etat.CREE, e.getEtat());
				Assert.assertEquals(new Float(10), e.getPrixMinimum());
				Assert.assertEquals(new Float(5), e.getPrixReserve());
				Assert.assertSame(false, e.getVisible());
			}
			else{
				System.out.println("Methode : testCreationEnchereCreeeNonVisibleAvecOffre - Enchere non faite");
				Assert.assertEquals(0, e.getListeOffres().size());
			}
		} 
		else {		
			System.out.println("Methode : testCreationEnchereCreeeNonVisibleAvecOffre - L'enchere n'a pas ete creee");
			Assert.assertNull(e);
		}
	}
	
	// TEST CREATION ENCHERE PAR UN VENDEUR A L'ETAT PUBLIEE ET AVEC OFFRE DE LA PART DU MEME VENDEUR
	@Test
	public void testCreationEncherePublieeAvecOffreDuMemeVendeur() {
		user.setVendeur(true);
		user.setAcheteur(true);
		Enchere e = user.creerEnchere(user.getLogin(), produit, dateLimite,
				prixMinimum, prixReserve);
		if (e != null) {

			Offre offre = new Offre(user.getLogin(), e, new Float(70));
			e.setEtat(Etat.PUBLIEE);
			e.setVisible(true);
			if( user.emettreOffre(offre, e) == 1)
			{
				System.out.println("Methode : testCreationEncherePublieeAvecOffreDuMemeVendeur - Enchere non faite car meme vendeur");
				Assert.assertEquals(0, e.getListeOffres().size());
			}
			else
				Assert.assertEquals(e.getLoginCreateur(), user.getLogin());
				Assert.assertEquals(Etat.PUBLIEE, e.getEtat());
				Assert.assertEquals(new Float(10), e.getPrixMinimum());
				Assert.assertEquals(new Float(5), e.getPrixReserve());
				Assert.assertSame(true, e.getVisible());
		} 
		else {		
			System.out.println("Methode : testCreationEncherePublieeAvecOffreDuMemeVendeur - L'enchere n'a pas ete creee");
			Assert.assertNull(e);
		}
	}

}

