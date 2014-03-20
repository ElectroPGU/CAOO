package src.test;

import static org.junit.Assert.*;

import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

import src.projet.Enchere;
import src.projet.Etat;
import src.projet.Objet;
import src.projet.Offre;
import src.projet.Utilisateur;

public class TestsEtat {

	
	Utilisateur user = new Utilisateur("sbailleux", "bailleux", "samuel");	
	Utilisateur user2 = new Utilisateur("apegue", "pegue", "axel");
	
	Objet produit = new Objet("Objet001", "premier objet vente");
	Objet produit2 = new Objet("Objet002", "second objet vente");
	Objet produit3 = new Objet("Objet003", "troisieme objet vente");
	Objet produit4 = new Objet("Objet004", "quatrieme objet vente");
	Date dateLimite = new Date();
	Float prixMinimum = new Float(25);
	Float prixReserve = new Float(50);
	
	/****************************** ENCHERE ETAT CREEE ******************************/
	// TEST SI LE CREATEUR (vendeur) VOIT SES ENCHERE
	@Test
	public void testCreateurVoitSesEncheres() {
		user.setVendeur(true);
		Enchere e1 = user.creerEnchere(user.getLogin(), produit, dateLimite, prixMinimum, prixReserve);
		Enchere e2 = user.creerEnchere(user.getLogin(), produit2, dateLimite, prixMinimum, prixReserve);
		e1.setEtat(Etat.CREE);
		e1.setVisible(false);
		e2.setEtat(Etat.PUBLIEE);
		e2.setVisible(true);
		Assert.assertEquals(2, user.voirLaListeEnchere(user, user).size());
	}

	// TEST D'UN UTILISATEUR AUTRE QUE LE VENDEUR QUI ESSAIE DE VOIR UNE ENCHERE NON PUBLIEE
	@Test
	public void testAcheteurEssaieVoirEnchereCreeeNonPubliee()
	{
		user.setVendeur(true);
		user2.setAcheteur(true);
		Enchere e1 = user.creerEnchere(user.getLogin(), produit, dateLimite, prixMinimum, prixReserve);
		Enchere e2 = user.creerEnchere(user.getLogin(), produit2, dateLimite, prixMinimum, prixReserve);
		Enchere e3 = user.creerEnchere(user.getLogin(), produit3, dateLimite, prixMinimum, prixReserve);
		Enchere e4 = user.creerEnchere(user.getLogin(), produit4, dateLimite, prixMinimum, prixReserve);
		e1.setEtat(Etat.CREE);
		e1.setVisible(false);
		e2.setEtat(Etat.PUBLIEE);
		e2.setVisible(true);
		e3.setEtat(Etat.CREE);
		e3.setVisible(false);
		e4.setEtat(Etat.PUBLIEE);
		e4.setVisible(true);
		Assert.assertEquals(2, user2.voirLaListeEnchere(user2, user).size());

	}
	
	
	/****************************** ENCHERE ETAT PUBLIEE ******************************/
	// TEST SI LE CREATEUR (vendeur) VOIT SES ENCHERE PUBLIEE
	@Test
	public void testCreateurVoitSonEncherePubliee() {
		user.setVendeur(true);
		user2.setAcheteur(true);
		Enchere e1 = user.creerEnchere(user.getLogin(), produit, dateLimite, prixMinimum, prixReserve);
		Enchere e2 = user.creerEnchere(user.getLogin(), produit2, dateLimite, prixMinimum, prixReserve);
		Enchere e3 = user.creerEnchere(user.getLogin(), produit3, dateLimite, prixMinimum, prixReserve);
		Enchere e4 = user.creerEnchere(user.getLogin(), produit4, dateLimite, prixMinimum, prixReserve);
		e1.setEtat(Etat.PUBLIEE);
		e1.setVisible(true);
		e2.setEtat(Etat.PUBLIEE);
		e2.setVisible(true);
		e3.setEtat(Etat.CREE);
		e3.setVisible(false);
		e4.setEtat(Etat.PUBLIEE);
		e4.setVisible(true);
		Assert.assertEquals(3, user2.voirLaListeEnchere(user2, user).size());
	}

	// TEST D'UN UTILISATEUR QUI ESSAIE DE VOIR LES ENCHERES PUBLIEEES PAR UN VENDEUR
	@Test
	public void testAcheteurEssaieVoirEncherePubliee()
	{
		user.setVendeur(true);
		user2.setAcheteur(true);
		Enchere e1 = user.creerEnchere(user.getLogin(), produit, dateLimite, prixMinimum, prixReserve);
		Enchere e2 = user.creerEnchere(user.getLogin(), produit2, dateLimite, prixMinimum, prixReserve);
		Enchere e3 = user.creerEnchere(user.getLogin(), produit3, dateLimite, prixMinimum, prixReserve);
		Enchere e4 = user.creerEnchere(user.getLogin(), produit4, dateLimite, prixMinimum, prixReserve);
		e1.setEtat(Etat.PUBLIEE);
		e1.setVisible(true);
		e2.setEtat(Etat.PUBLIEE);
		e2.setVisible(true);
		e3.setEtat(Etat.CREE);
		e3.setVisible(false);
		e4.setEtat(Etat.PUBLIEE);
		e4.setVisible(true);
		Assert.assertEquals(3, user2.voirLaListeEnchere(user2, user).size());
	}	
	
	
	/****************************** ENCHERE ETAT ANNULEE ******************************/
	// TEST SI LE CREATEUR (vendeur) VOIT SON ENCHERE
	@Test
	public void testCreateurVoitSonEnchereAnnulee() {
		user.setVendeur(true);
		user2.setAcheteur(true);
		Enchere e1 = user.creerEnchere(user.getLogin(), produit, dateLimite, prixMinimum, prixReserve);
		Enchere e2 = user.creerEnchere(user.getLogin(), produit2, dateLimite, prixMinimum, prixReserve);
		Enchere e3 = user.creerEnchere(user.getLogin(), produit3, dateLimite, prixMinimum, prixReserve);
		Enchere e4 = user.creerEnchere(user.getLogin(), produit4, dateLimite, prixMinimum, prixReserve);
		e1.setEtat(Etat.PUBLIEE);
		e1.setVisible(true);
		e2.setEtat(Etat.ANNULEE);
		e2.setVisible(false);
		e3.setEtat(Etat.CREE);
		e3.setVisible(false);
		e4.setEtat(Etat.ANNULEE);
		e4.setVisible(false);
		Assert.assertEquals(4, user.voirLaListeEnchere(user, user).size());
	}

	// TEST D'UN ACHETEUR QUI ESSAIE DE VOIR UNE ENCHERE ANNULEE SUR LAQUELLE IL N'A PAS FAIT D'OFFRE
	@Test
	public void testAcheteurEssaieVoirEnchereAnnuleeSansOffre()
	{
		user.setVendeur(true);
		user2.setAcheteur(true);
		Enchere e1 = user.creerEnchere(user.getLogin(), produit, dateLimite, prixMinimum, prixReserve);
		Enchere e2 = user.creerEnchere(user.getLogin(), produit2, dateLimite, prixMinimum, prixReserve);
		Enchere e3 = user.creerEnchere(user.getLogin(), produit3, dateLimite, prixMinimum, prixReserve);
		Enchere e4 = user.creerEnchere(user.getLogin(), produit4, dateLimite, prixMinimum, prixReserve);
		e1.setEtat(Etat.PUBLIEE);
		e1.setVisible(true);
		e2.setEtat(Etat.ANNULEE);
		e2.setVisible(false);
		e3.setEtat(Etat.CREE);
		e3.setVisible(false);
		e4.setEtat(Etat.ANNULEE);
		e4.setVisible(false);
		Assert.assertEquals(1, user2.voirLaListeEnchere(user2, user).size());
	}
	
	// TEST D'UN ACHETEUR QUI ESSAIE DE VOIR UNE ENCHERE ANNULEE SUR LAQUELLE IL A FAIT UNE OFFRE
	@Test
	public void testAcheteurEssaieVoirEnchereAnnuleeAvecOffre()
	{
		user.setVendeur(true);
		user2.setAcheteur(true);
		Enchere e1 = user.creerEnchere(user.getLogin(), produit, dateLimite, prixMinimum, prixReserve);
		Enchere e2 = user.creerEnchere(user.getLogin(), produit2, dateLimite, prixMinimum, prixReserve);
		Enchere e3 = user.creerEnchere(user.getLogin(), produit3, dateLimite, prixMinimum, prixReserve);
		Enchere e4 = user.creerEnchere(user.getLogin(), produit4, dateLimite, prixMinimum, prixReserve);
		Offre offre = new Offre(user2.getLogin(), e2, new Float(7));

		e1.setEtat(Etat.PUBLIEE);//verra cette annonce
		e1.setVisible(true);
		e2.setEtat(Etat.PUBLIEE);//verra cette annonce
		e2.setVisible(true);
		e3.setEtat(Etat.CREE);
		e3.setVisible(false);
		e4.setEtat(Etat.ANNULEE);
		e4.setVisible(false);
		
		if( user2.emettreOffre(offre, e2) == 0)
		{
			e2.setEtat(Etat.ANNULEE);
			e2.setVisible(false);
			Assert.assertEquals(2, user2.voirLaListeEnchere(user2, user).size());
		}
		else
		{
			e2.setEtat(Etat.ANNULEE);
			e2.setVisible(false);
			Assert.assertEquals(1, user.voirLaListeEnchere(user2, user).size());
		}

	}
	
	
	/****************************** ENCHERE ETAT TERMINEE ******************************/
		
	// TEST SI ENCHERE TERMINEE A DES OFFRES
	@Test
	public void testSiEnchereTermineeADesOffres()
	{
		user.setVendeur(true);
		user2.setAcheteur(true);
		Enchere e1 = user.creerEnchere(user.getLogin(), produit, dateLimite, prixMinimum, prixReserve);
		Offre offre1 = new Offre(user2.getLogin(), e1, new Float(70));
		Offre offre2 = new Offre(user2.getLogin(), e1, new Float(80));
		
		e1.setEtat(Etat.PUBLIEE);
		e1.setVisible(true);

		if( user2.emettreOffre(offre1, e1) == 0 && user2.emettreOffre(offre2, e1) == 0)
		{
			e1.setEtat(Etat.TERMINEE);
			e1.setVisible(false);
			Assert.assertEquals(2, e1.getListeOffres().size());
		}
		else
		{
			System.out.println("Methode: testSiEnchereADesOffres - offre non faite");
		}
	}
	
	// TEST CONNAIRE L'ENCHERE LA PLUS HAUTE
	@Test
	public void testEnchereLaPlusHaute()
	{
		user.setVendeur(true);
		user2.setAcheteur(true);
		Enchere e1 = user.creerEnchere(user.getLogin(), produit, dateLimite, prixMinimum, prixReserve);
		Offre offre1 = new Offre(user2.getLogin(), e1, new Float(70));
		Offre offre2 = new Offre(user2.getLogin(), e1, new Float(80));
		Offre offre3 = new Offre(user2.getLogin(), e1, new Float(160));
		Offre offre4 = new Offre(user2.getLogin(), e1, new Float(120));
		
		e1.setEtat(Etat.PUBLIEE);
		e1.setVisible(true);

		if( user2.emettreOffre(offre1, e1) == 0 && user2.emettreOffre(offre2, e1) == 0 && user2.emettreOffre(offre3, e1) == 0 && user2.emettreOffre(offre4, e1) == 0 )
		{
			e1.setEtat(Etat.TERMINEE);
			e1.setVisible(false);
			Assert.assertEquals(new Float(160), e1.getValeurPlusHaute(e1.getListeOffres()));
		}
		else
		{
			System.out.println("Methode: testSiEnchereADesOffres - offre non faite");
		}
	}
	
	// TEST CONNAITRE LA PERSONNE QUI REMPORTE L'ENCHERE
	@Test
	public void testConnaitreGagnant()
	{
		user.setVendeur(true);
		user2.setAcheteur(true);
		Enchere e1 = user.creerEnchere(user.getLogin(), produit, dateLimite, prixMinimum, prixReserve);
		Offre offre1 = new Offre(user2.getLogin(), e1, new Float(70));
		Offre offre2 = new Offre(user2.getLogin(), e1, new Float(80));
		Offre offre3 = new Offre(user2.getLogin(), e1, new Float(160));
		Offre offre4 = new Offre(user2.getLogin(), e1, new Float(120));
		
		e1.setEtat(Etat.PUBLIEE);
		e1.setVisible(true);

		if( user2.emettreOffre(offre1, e1) == 0 && user2.emettreOffre(offre2, e1) == 0 && user2.emettreOffre(offre3, e1) == 0 && user2.emettreOffre(offre4, e1) == 0 )
		{
			e1.setEtat(Etat.TERMINEE);
			e1.setVisible(false);
			Assert.assertEquals("apegue", e1.getGagnant(e1.getListeOffres()));
		}
		else
		{
			System.out.println("Methode: testSiEnchereADesOffres - offre non faite");
		}
	}
	
	
}
