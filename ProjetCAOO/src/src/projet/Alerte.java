package src.projet;

import java.util.Observable;
import java.util.Observer;

public class Alerte implements Observer{

	private final Enchere enchere;
	private final TypeAlerte typeAlerte;
	private boolean activated = true;
	private String message = "Rien à signaler";
	
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
				this.message ="Une nouvelle offre a été déposée sur votre enchère";
				break;
			case PRIXRESERVEATTEINT :
				this.message ="Le prix de réserve a été atteint";
				break;
			case ENCHEREANNULEE :
				this.message ="L'enchère a été annulée";
				break;
			case OFFRESUPERIEURE :
				this.message ="Une offre supérieure à la votre a été déposée sur l'enchère";
				break;
			}
		}
	}
	
}
