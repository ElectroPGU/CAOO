package src.projet;

import java.util.Observable;
import java.util.Observer;

public class Alerte implements Observer{

	private final Enchere enchere;
	private final TypeAlerte typeAlerte;
	private boolean activated = true;
	private String message = "Rien � signaler";
	
	public String getMessage() {
		return message;
	}

	public Enchere getEnchere() {
		return enchere;
	}

	public TypeAlerte getTypeAlerte() {
		return typeAlerte;
	}
	
	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}
	
	public Alerte(Enchere enchere,TypeAlerte typeAlerte){
		this.typeAlerte = typeAlerte;
		this.enchere = enchere;
		this.enchere.addObserver(this);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		TypeAlerte evenement = (TypeAlerte) arg1;
		if(this.typeAlerte==evenement && this.activated==true)
		{
			switch(typeAlerte)
			{
			case NOUVELLEOFFRE :
				this.message ="Une nouvelle offre a �t� d�pos�e sur votre ench�re";
				break;
			case PRIXRESERVEATTEINT :
				this.message ="Le prix de r�serve a �t� atteint";
				break;
			case ENCHEREANNULEE :
				this.message ="L'ench�re a �t� annul�e";
				break;
			case OFFRESUPERIEURE :
				this.message ="Une offre sup�rieure � la votre a �t� d�pos�e sur l'ench�re";
				break;
			}
		}
	}
	
}
