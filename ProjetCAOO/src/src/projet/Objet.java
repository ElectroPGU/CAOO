package src.projet;

public class Objet {
	
	private String identifiant;
	private String description;

	public Objet(String identifiant, String description) {
		this.identifiant = identifiant;
		this.description = description;
	}

	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
