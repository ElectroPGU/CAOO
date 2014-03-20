package src.projet;

public class Offre {
	
	private Float prix;
	private String loginAcheteur;
	private Enchere enchere;
	
	public Offre(String loginAcheteur, Enchere enchere, Float prix ) {
		this.loginAcheteur = loginAcheteur;
		this.enchere = enchere;
		this.prix = prix;
	}

	public Float getPrix() {
		return prix;
	}

	public void setPrix(Float prix) {
		this.prix = prix;
	}

	public String getLoginAcheteur() {
		return loginAcheteur;
	}

	public void setLoginAcheteur(String loginAcheteur) {
		this.loginAcheteur = loginAcheteur;
	}

	public Enchere getEnchere() {
		return enchere;
	}

	public void setEnchere(Enchere enchere) {
		this.enchere = enchere;
	}
	
	
}
