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
	Date dateLimite = new Date();
	Float prixMinimum = new Float(25);
	Float prixReserve = new Float(50);
	
	/****************************** ENCHERE ETAT CREEE ******************************/
	// TEST SI LE CREATEUR (vendeur) VOIT SON ENCHERE
	@Test
	public void testCreateurVoitSonEnchereCreee() {
		user.setVendeur(true);
		user2.setAcheteur(true);
		Enchere e1 = user.creerEnchere(user.getLogin(), produit, dateLimite, prixMinimum, prixReserve);
		Enchere e2 = user.creerEnchere(user.getLogin(), produit2, dateLimite, prixMinimum, prixReserve);
		e1.setEtat(Etat.CREE);
		e1.setVisible(false);
		e1.setEtat(Etat.PUBLIEE);
		e1.setVisible(true);
		
	}

	// TEST D'UN UTILISATEUR AUTRE QUE LE VENDEUR QUI ESSAIE DE VOIR UNE ENCHERE NON PUBLIEE
	@Test
	public void testAcheteurEssaieVoirEnchereCreeeNonPubliee()
	{
		
	}
	
	
	/****************************** ENCHERE ETAT PUBLIEE ******************************/
	// TEST SI LE CREATEUR (vendeur) VOIT SON ENCHERE PUBLIEE
	@Test
	public void testCreateurVoitSonEncherePubliee() {

	}

	// TEST D'UN ACHETEUR QUI ESSAIE DE VOIR UNE ENCHERE PUBLIEE PAR UN VENDEUR
	@Test
	public void testAcheteurEssaieVoirEncherePubliee()
	{
		
	}	
	
	
	/****************************** ENCHERE ETAT ANNULEE ******************************/
	// TEST SI LE CREATEUR (vendeur) VOIT SON ENCHERE
	@Test
	public void testCreateurVoitSonEnchereAnnulee() {

	}

	// TEST D'UN ACHETEUR QUI ESSAIE DE VOIR UNE ENCHERE D'UN VENDEUR NON PUBLIEE MAIS QUI N'A PAS FAIT D'OFFRE
	@Test
	public void testAcheteurEssaieVoirEnchereAnnuleeSansOffre()
	{
		
	}
	
	// TEST D'UN ACHETEUR QUI ESSAIE DE VOIR UNE ENCHERE D'UN VENDEUR NON PUBLIEE MAIS QUI A FAIT UNE OFFRE
	@Test
	public void testAcheteurEssaieVoirEnchereAnnuleeAvecOffre()
	{
		
	}
	
	
	/****************************** ENCHERE ETAT TERMINEE ******************************/
	// TEST SUR UNE ENCHERE A L'ETAT TERMINEE SI DATE BIEN ATTEINTE
	@Test
	public void testEnchereTermineeSiDateAtteinte()
	{
		
	}
	
	// TEST SI ENCHERE ENCHERE A DES OFFRES
	@Test
	public void testSiEnchereADesOffres()
	{
		
	}
	
	// TEST CONNAIRE L'ENCHERE LA PLUS HAUTE
	@Test
	public void testEnchereLaPlusHaute()
	{
		
	}
	
	// TEST CONNAITRE LA PERSONNE QUI REMPORTE L'ENCHERE
	@Test
	public void testConnaitreGagnant()
	{
		
	}
	
	
}
