package projet;

import java.util.Date;


public class Utilisateur {

	private final String login;
	private final String nom;
	private final String prenom;

	private Boolean acheteur = false;
	private Boolean vendeur = false;
	
	public Utilisateur(String login, String nom, String prenom)
	{
		this.login = login;
		this.nom = nom;
		this.prenom = prenom;
	}
	
	/*********************************ACHETEUR*************************************/
	public void emettreOffre(Offre offre, Enchere enchere)
	{
		if(acheteur)
		{
			enchere.addOffre(this.login ,offre);
		}
		else{
			new Exception("L'utilisateur n'est pas un acheteur");
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
	
	/********************************* VENDEUR ************************************/
	public void creerEnchere()
	{
		if(vendeur)
		{
			// creer une enchere
		}
		else{
			new Exception("L'utilisateur n'est pas un vendeur");
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

	
	
}
