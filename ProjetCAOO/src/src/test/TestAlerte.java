package src.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import src.projet.Enchere;
import src.projet.Etat;
import src.projet.Objet;
import src.projet.Offre;
import src.projet.TypeAlerte;
import src.projet.Utilisateur;

public class TestAlerte {


	Utilisateur user = new Utilisateur("sbailleux", "bailleux", "samuel");	
	Utilisateur user2 = new Utilisateur("apegue", "pegue", "axel");	
	Utilisateur user3 = new Utilisateur("snzaramba", "nzaramba", "sylvestre");
	
	Objet produit = new Objet("Objet001", "premier objet vente");
	Date dateLimite = new Date();
	Float prixMinimum = new Float(25);
	Float prixReserve = new Float(50);
	
	
	//TEST PRIX DE RESERVE ATTEINT PAR UNE OFFRE
	@Test
	public void testPrixDeReserveAtteint() {
		user.setVendeur(true);
		user2.setAcheteur(true);
		
		Enchere e = user.creerEnchere(user.getLogin(), produit, dateLimite, prixMinimum, prixReserve);
		e.setEtat(Etat.PUBLIEE);
		user2.creerAlerte(e, TypeAlerte.PRIXRESERVEATTEINT);
		Offre offre = new Offre(user2.getLogin(), e, new Float(51));
		user2.emettreOffre(offre, e);
		
		assertEquals("Le prix de réserve a été atteint",user2.getListeAlertes().get(0).getMessage().toString());
		System.out.println("Methode : testPrixDeReserveAtteint - Alerte prix reserve atteint : " + user2.getListeAlertes().get(0).getMessage().toString());
		
		
	}

	//TEST ENCHERE ANNULEE PAR LE VENDEUR
	@Test
	public void testEnchereAnnulee() {

		user.setVendeur(true);
		user2.setAcheteur(true);
		
		Enchere e = user.creerEnchere(user.getLogin(), produit, dateLimite, prixMinimum, prixReserve);
		e.setEtat(Etat.PUBLIEE);
		user2.creerAlerte(e, TypeAlerte.ENCHEREANNULEE);
		user.annulerEnchere(e);
		
		assertEquals("L'enchère a été annulée",user2.getListeAlertes().get(0).getMessage().toString());
		System.out.println("Methode : testEnchereAnnulee - Alerte enchère annulée : " + user2.getListeAlertes().get(0).getMessage().toString());
		
	}

	//TEST UNE OFFRE SUPERIEUR A CELLE DE L'ACHETEUR A ETE EMISE PAR UN AUTRE ACHETEUR
	@Test
	public void testOffreSuperieurEmiseParAutreAcheteur() {

		user.setVendeur(true);
		user2.setAcheteur(true);
		user3.setAcheteur(true);
		
		Enchere e = user.creerEnchere(user.getLogin(), produit, dateLimite, prixMinimum, prixReserve);
		e.setEtat(Etat.PUBLIEE);
		e.setVisible(true);
		user2.creerAlerte(e, TypeAlerte.NOUVELLEOFFRE);
		Offre offre = new Offre(user2.getLogin(), e, new Float(51));
		Offre offre1 = new Offre(user3.getLogin(), e, new Float(70));
		user2.emettreOffre(offre, e);
		user3.emettreOffre(offre1, e);
		
		assertEquals("Une nouvelle offre a été déposée sur votre enchère",user2.getListeAlertes().get(0).getMessage().toString());
		System.out.println("Methode : testOffreSuperieurEmiseParAutreAcheteur - Alerte offre superieur à la votre émise : " + user2.getListeAlertes().get(0).getMessage().toString());
		
		
	}
}
