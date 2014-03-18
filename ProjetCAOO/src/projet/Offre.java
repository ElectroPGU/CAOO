package projet;

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

	public String getAcheteur() {
		return loginAcheteur;
	}

	public void setAcheteur(String loginAcheteur) {
		this.loginAcheteur = loginAcheteur;
	}
	
	
	
	
}
