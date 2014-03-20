package src.projet;

import java.util.ArrayList;
import java.util.Date;

public class Enchere {
	
	private String loginCreateur;
	private final Objet objet;
	private final Date dateLimite;
	private final Float prixMinimum;
	private final Float prixReserve;
	private Boolean visible = false;
	private Boolean prixReserveAtteint = false;
	private Etat etat;
	private ArrayList<Offre> listeOffres = new ArrayList<Offre>();

	public Enchere(String loginCreateur , Objet objet, Date dateLimite, Float prixMinimum, Float prixReserve) {
		this.loginCreateur = loginCreateur;
		this.objet = objet;
		this.dateLimite = dateLimite;
		this.prixMinimum = prixMinimum;
		this.prixReserve = prixReserve;
	}
	

	public int addOffre(String loginCreateur ,Offre offre)
	{
		if(loginCreateur != this.loginCreateur && offre.getPrix() >= prixMinimum)
		{
			if(offre.getPrix() >= this.prixReserve)
			{
				this.prixReserveAtteint = true;
			}
			this.listeOffres.add(offre);
			return 0;
		}
		else
			return 1;
	}
	
	public String getLoginCreateur() {
		return loginCreateur;
	}

	public void setLoginCreateur(String loginCreateur) {
		this.loginCreateur = loginCreateur;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public Etat getEtat() {
		return etat;
	}

	public void setEtat(Etat etat) {
		this.etat = etat;
	}

	public Objet getObjet() {
		return objet;
	}

	public Date getDateLimite() {
		return dateLimite;
	}

	public Float getPrixMinimum() {
		return prixMinimum;
	}

	public Float getPrixReserve() {
		return prixReserve;
	}

	public ArrayList<Offre> getListeOffres() {
		return listeOffres;
	}

	public void setListeOffres(ArrayList<Offre> listeOffres) {
		this.listeOffres = listeOffres;
	}

	public Boolean getPrixReserveAtteint() {
		if(prixReserveAtteint)
			return prixReserveAtteint;
		return false;
	}

	public void setPrixReserveAtteint(Boolean prixReserveAtteint) {
		this.prixReserveAtteint = prixReserveAtteint;
	}

	
	
}
