package src.test;

import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

import src.projet.Enchere;
import src.projet.Etat;
import src.projet.Objet;
import src.projet.Offre;
import src.projet.Utilisateur;

public class TestsEnchere {

	
	Utilisateur user = new Utilisateur("sbailleux", "bailleux", "samuel");	
	Utilisateur user2 = new Utilisateur("apegue", "pegue", "axel");
	
	Objet produit = new Objet("Objet001", "premier objet vente");
	Date dateLimite = new Date();
	Float prixMinimum = new Float(25);
	Float prixReserve = new Float(50);
	
	// EMETTRE UNE OFFRE SUR UNE ENCHERE DES QUE CELLE-CI EST PUBLIEE
	@Test
	public void testOffreSurEncherePubliee() {
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
			Assert.assertEquals(new Float(25), e.getPrixMinimum());
			Assert.assertEquals(new Float(50), e.getPrixReserve());
			Assert.assertSame(true, e.getVisible());
			Assert.assertEquals(new Float(70), e.getListeOffres().get(0).getPrix());
			System.out.println("Methode : testOffreSurEncherePubliee - Offre sur enchere enregistree");			
		} 
		else {		
			System.out.println("Methode : testOffreSurEncherePubliee - L'enchere n'a pas ete creee");
			Assert.assertNull(e);
		}
	}

	// EMETTRE UNE OFFRE SUR UNE ENCHERE NON PUBLIEE
	@Test
	public void testOffreSurEnchereNonPubliee() {
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
				Assert.assertEquals(new Float(25), e.getPrixMinimum());
				Assert.assertEquals(new Float(50), e.getPrixReserve());
				Assert.assertSame(false, e.getVisible());
			}
			else{
				System.out.println("Methode : testOffreSurEnchereNonPubliee - Enchere non faite");
				Assert.assertEquals(0, e.getListeOffres().size());
			}
		} 
		else {		
			System.out.println("Methode : testOffreSurEnchereNonPubliee - L'enchere n'a pas ete creee");
			Assert.assertNull(e);
		}
	}

	// TEST OFFRE EN DESSOUS DU PRIX MINIMUM
	@Test
	public void testOffreEnDessousPrixMinimum()
	{
		user.setVendeur(true);
		user2.setAcheteur(true);
		
		Enchere e = user.creerEnchere(user.getLogin(), produit, dateLimite, prixMinimum, prixReserve);
		
		if (e != null) 
		{
			Offre offre = new Offre(user2.getLogin(), e, new Float(2));
			e.setEtat(Etat.PUBLIEE);
			e.setVisible(true);

			int result = user2.emettreOffre(offre, e);
			if(result == 0)
			{
				Assert.assertEquals(e.getLoginCreateur(), user.getLogin());
				Assert.assertEquals(Etat.CREE, e.getEtat());
				Assert.assertEquals(new Float(25), e.getPrixMinimum());
				Assert.assertEquals(new Float(50), e.getPrixReserve());
				Assert.assertSame(false, e.getVisible());
				Assert.assertEquals(new Float(2), e.getListeOffres().get(0).getPrix());
				System.out.println("Methode : testOffreEnDessousPrixMinimum - Offre sur enchere enregistree");	
			}
			else{
				System.out.println("Methode : testOffreEnDessousPrixMinimum - Enchere non faite");
				Assert.assertEquals(0, e.getListeOffres().size());
			}
		} 
		else {		
			System.out.println("Methode : testOffreEnDessousPrixMinimum - L'enchere n'a pas ete creee");
			Assert.assertNull(e);
		}
	}
	
	// TEST FAIRE UNE OFFRE EGAL OU AU DESSUS DU PRIX MINIMUM
	@Test
	public void testOffreAuDessusPrixMinimum()
	{
		user.setVendeur(true);
		user2.setAcheteur(true);
		Enchere e = user.creerEnchere(user.getLogin(), produit, dateLimite, prixMinimum, prixReserve);
		Offre offre = new Offre(user2.getLogin(), e, new Float(30));
		e.setEtat(Etat.PUBLIEE);
		e.setVisible(true);
		int result = user2.emettreOffre(offre, e);
		if(result == 1)
			Assert.assertEquals(1, e.getListeOffres().size());
			System.out.println("Methode : testOffreAuDessusPrixMinimum - enchere acceptee");
	}
	
	// TEST VENDEUR LAMBDA QUI ESSAIE DE VOIR LE PRIX MINIMUM D'UNE ENCHERE
	@Test
	public void testVendeurLambdaEssaieDeVoirPrixMinimumEnchere()
	{
		user.setVendeur(true);
		Enchere e = user.creerEnchere(user.getLogin(), produit, dateLimite, prixMinimum, prixReserve);
		Assert.assertEquals(new Float(25), e.getPrixMinimum());
	}
	
	// TEST ACHETEUR LAMBDA QUI ESSAIE DE VOIR LE PRIX MINIMUM D'UNE ENCHERE
	@Test
	public void testAcheteurLambdaEssaieDeVoirPrixMinimumEnchere()
	{
		user.setVendeur(true);
		user2.setAcheteur(true);
		Enchere e = user.creerEnchere(user.getLogin(), produit, dateLimite, prixMinimum, prixReserve);
		Assert.assertEquals(new Float(25), e.getPrixMinimum());
	}
	
	// TEST CREATEUR ESSAIE DE VOIR LE PRIX DE RESERVE DE SON ENCHERE
	@Test
	public void testCreateurRegardePrixReserveDeSonEnchere()
	{
		user.setVendeur(true);
		Enchere e = user.creerEnchere(user.getLogin(), produit, dateLimite, prixMinimum, prixReserve);
		Assert.assertEquals(new Float(50), e.getPrixReserve());
	}
	
	// TEST D'UN UTILISATEUR AUTRE QUE LE VENDEUR QUI ESSAIE DE CONNAITRE LE PRIX DE RESERVE QUI N'A PAS ETE ATTEINT 
	@Test
	public void testAcheteurEssaieConnaitrePrixReserveNonAtteintDuneEnchere()
	{
		user.setVendeur(true);
		user2.setAcheteur(true);
		Enchere e = user.creerEnchere(user.getLogin(), produit, dateLimite, prixMinimum, prixReserve);
		Offre offre = new Offre(user2.getLogin(), e, new Float(1));
		e.setEtat(Etat.PUBLIEE);
		e.setVisible(true);
		int result = user2.emettreOffre(offre, e);
		if(result == 0 && e.getPrixReserveAtteint())
		{
			Assert.assertEquals(new Float(50), e.getPrixReserve());
			System.out.println("Methode : testAcheteurEssaieConnaitrePrixReserveNonAtteintDuneEnchere - prix reserve atteint :" + e.getPrixReserve().toString());
		}
		else{
			Assert.assertSame(false, e.getPrixReserveAtteint());
			System.out.println("Methode : testAcheteurEssaieConnaitrePrixReserveNonAtteintDuneEnchere - prix reserve NON atteint");
		}
	}

	// TEST D'UN UTILISATEUR AUTRE QUE LE VENDEUR QUI ESSAIE DE CONNAITRE LE PRIX DE RESERVE QUI A ETE ATTEINT 
	@Test
	public void testAcheteurEssaieConnaitrePrixReserveAtteintDuneEnchere()
	{
		user.setVendeur(true);
		user2.setAcheteur(true);
		Enchere e = user.creerEnchere(user.getLogin(), produit, dateLimite, prixMinimum, prixReserve);
		Offre offre = new Offre(user2.getLogin(), e, new Float(100));
		e.setEtat(Etat.PUBLIEE);
		e.setVisible(true);
		int result = user2.emettreOffre(offre, e);
		if(result == 0 && e.getPrixReserveAtteint())
		{
			Assert.assertEquals(new Float(50), e.getPrixReserve());
			System.out.println("Methode : testAcheteurEssaieConnaitrePrixReserveAtteintDuneEnchere - prix reserve atteint :" + e.getPrixReserve().toString());
		}
		else{
			Assert.assertSame(false, e.getPrixReserveAtteint());
			System.out.println("Methode : testAcheteurEssaieConnaitrePrixReserveAtteintDuneEnchere - prix reserve NON atteint");
		}
	}
	
	// TEST VENDEUR ANNULE ENCHERE CAR AUCUNE OFFRE N'A ATTEINT LE PRIX DE RESERVE
	@Test
	public void testCreateurAnnuleEnchereCarAucuneOffreAAtteintPrixReserve()
	{
		user.setVendeur(true);
		user2.setAcheteur(true);
		Enchere e = user.creerEnchere(user.getLogin(), produit, dateLimite, prixMinimum, prixReserve);
		Offre offre = new Offre(user2.getLogin(), e, new Float(40));
		e.setEtat(Etat.PUBLIEE);
		e.setVisible(true);
		int result = user2.emettreOffre(offre, e);
		if(result == 0 && !e.getPrixReserveAtteint())
		{
			e.setEtat(Etat.ANNULEE);
			Assert.assertSame(false, e.getPrixReserveAtteint());
			System.out.println("Methode : testCreateurAnnuleEnchereCarAucuneOffreAAtteintPrixReserve - offre annulee car prix reserve NON atteint");
		}
		else if(result == 0){
			Assert.assertSame(true, e.getPrixReserveAtteint());
			System.out.println("Methode : testCreateurAnnuleEnchereCarAucuneOffreAAtteintPrixReserve - prix reserve atteint offre toujours publiee");
		}
		else{
			System.out.println("Methode : testCreateurAnnuleEnchereCarAucuneOffreAAtteintPrixReserve - offre non faite");
		}
	}
	
	// TEST VENDEUR ESSAIE D'ANNULER UNE ENCHERE MAIS LE PRIX DE RESERVE A ETE ATTEINT
	@Test
	public void testCreateurAnnuleEnchereAvecPrixReserveAtteint()
	{
		user.setVendeur(true);
		user2.setAcheteur(true);
		Enchere e = user.creerEnchere(user.getLogin(), produit, dateLimite, prixMinimum, prixReserve);
		Offre offre = new Offre(user2.getLogin(), e, new Float(55));
		e.setEtat(Etat.PUBLIEE);
		e.setVisible(true);
		int result = user2.emettreOffre(offre, e);
		if(result == 0 && !e.getPrixReserveAtteint())
		{
			e.setEtat(Etat.ANNULEE);
			Assert.assertSame(false, e.getPrixReserveAtteint());
			System.out.println("Methode : testCreateurAnnuleEnchereAvecPrixReserveAtteint - offre annulee car prix reserve NON atteint");
		}
		else if(result == 0){
			Assert.assertSame(true, e.getPrixReserveAtteint());
			System.out.println("Methode : testCreateurAnnuleEnchereAvecPrixReserveAtteint - prix reserve atteint offre toujours publiee");
		}
		else{
			System.out.println("Methode : testCreateurAnnuleEnchereAvecPrixReserveAtteint - offre non faite");
		}
	}
	
	
	// TEST D'UN ACHETEUR QUI ESSAIE DE FAIRE UNE OFFRE SUR UNE ENCHERE ANNULEE DONC INVISIBLE
	@Test
	public void testAcheteurEssaieFaireOffreSurEnchereAnnulee()
	{
		user.setVendeur(true);
		user2.setAcheteur(true);
		Enchere e = user.creerEnchere(user.getLogin(), produit, dateLimite, prixMinimum, prixReserve);
		Offre offre = new Offre(user2.getLogin(), e, new Float(55));
		e.setEtat(Etat.ANNULEE);
		e.setVisible(false);
		int result = user2.emettreOffre(offre, e);
		if(result == 0)
		{
			System.out.println("Methode : testAcheteurEssaieFaireOffreSurEnchereAnnulee - offre faite");
		}
		else{
			Assert.assertEquals(Etat.ANNULEE, e.getEtat());
			System.out.println("Methode : testAcheteurEssaieFaireOffreSurEnchereAnnulee - offre non faite car enchere annulee");
		}
	}
	
	
}

