package src.projet;

import java.util.ArrayList;
import java.util.Date;


public class Utilisateur {

	private final String login;
	private final String nom;
	private final String prenom;

	private Boolean acheteur = false;
	private Boolean vendeur = false;
	
	private ArrayList<Enchere> listeEncheres = new ArrayList<Enchere>();
	private ArrayList<Alerte> listeAlertes = new ArrayList<Alerte>();
	
	public Utilisateur(String login, String nom, String prenom)
	{
		this.login = login;
		this.nom = nom;
		this.prenom = prenom;
	}
	
	public void creerAlerte(Enchere enchere, TypeAlerte typeAlerte){
		listeAlertes.add(new Alerte(enchere, typeAlerte));
	}
	
	/*********************************ACHETEUR*************************************/
	public int emettreOffre(Offre offre, Enchere enchere)
	{
		if(acheteur && enchere.getEtat().equals(Etat.PUBLIEE))
		{
			if (enchere.addOffre(this.login ,offre) == 0){
				this.creerAlerte(enchere, TypeAlerte.NOUVELLEOFFRE);
				return 0;
			}
			else
				return 1;
		}
		else{
			new Exception("L'utilisateur n'est pas un acheteur ou l'enchere n'est pas publiee");
			return 1;
		}
	}
	
	public Boolean savoirSiPrixReserveAtteint(Enchere enchere)
	{
		if(acheteur)
		{
			for(Offre o : enchere.getListeOffres())
			{
				if(o.getPrix() >= enchere.getPrixReserve())
				{
					return true;
				}
			}
		}
		else{
			new Exception("L'utilisateur n'est pas un acheteur");
			return false;
		}
		return false;
	}
	
	public ArrayList<Enchere> voirLaListeEnchere(Utilisateur utilisateurDemande, Utilisateur utilisateurVendeur)
	{
		if(utilisateurDemande.getLogin().equals(utilisateurVendeur.getLogin()))
		{
			return utilisateurVendeur.getListeEncheres();
		}
		else
		{
			ArrayList<Enchere> listeEncheresARetourner = new ArrayList<Enchere>();
			for(Enchere enchere : utilisateurVendeur.getListeEncheres())
			{
				if(enchere.getEtat().equals(Etat.PUBLIEE))
					listeEncheresARetourner.add(enchere);
				else if(enchere.getEtat().equals(Etat.ANNULEE))
				{
					for(Offre o : enchere.getListeOffres())
					{
						if(o.getLoginAcheteur().equals(utilisateurDemande.getLogin()))
							listeEncheresARetourner.add(enchere);
					}
				}
			}
			return listeEncheresARetourner;
		}
	}
	
	/********************************* VENDEUR ************************************/
	public Enchere creerEnchere(String loginCreateur , Objet objet, Date dateLimite, Float prixMinimum, Float prixReserve)
	{
		if(vendeur)
		{
			try
			{
				Enchere enchere = new Enchere(loginCreateur, objet, dateLimite, prixMinimum, prixReserve);
				this.listeEncheres.add(enchere);
				return enchere;
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return null;
			}
		}
		else{
			new Exception("L'utilisateur n'est pas un vendeur");
			return null;
		}
	}

	public void publierEnchere(Enchere enchere)
	{
		if(vendeur && this.login == enchere.getLoginCreateur())
		{
			enchere.setEtat(Etat.PUBLIEE);
		}
		else{
			new Exception("L'utilisateur n'est pas un vendeur ou n'est pas le createur de l'enchere");
		}
	}
	
	public String annulerEnchere(Enchere enchere)
	{
		if(vendeur)
		{
			enchere.setEtat(Etat.ANNULEE);
			for(Offre o : enchere.getListeOffres())
			{
				if(o.getPrix() >= enchere.getPrixReserve())
				{
					enchere.setEtat(Etat.PUBLIEE);
					return "ANNULEE";
				}
			}
			return "";
		}
		else{
			new Exception("L'utilisateur n'est pas un vendeur");
		}
		return "";
	}
	
	/******************************** ACESSEURS ************************************/
	public String getLogin() {
		return login;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public Boolean getAcheteur() {
		return acheteur;
	}

	public void setAcheteur(Boolean acheteur) {
		this.acheteur = acheteur;
	}

	public Boolean getVendeur() {
		return vendeur;
	}

	public void setVendeur(Boolean vendeur) {
		this.vendeur = vendeur;
	}

	public ArrayList<Enchere> getListeEncheres() {
		return listeEncheres;
	}

	public void setListeEncheres(ArrayList<Enchere> listeEncheres) {
		this.listeEncheres = listeEncheres;
	}

	public ArrayList<Alerte> getListeAlertes() {
		return listeAlertes;
	}

	
}
