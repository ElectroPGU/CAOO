package projet;

import java.util.Date;

public class Enchere {
final Objet objet;
final Date dateLimite;
final Float prixMinimum;
Boolean visible = false;
Etat etat = Etat.cree;

public Enchere(Objet objet, Date dateLimite, Float prixMinimum)
{
	this.objet = objet;
	this.dateLimite = dateLimite;
	this.prixMinimum = prixMinimum;
	
}
}
