package projet;
import java.util.ArrayList;


public class Utilisateur {
	final String login;
	final String nom;
	final String prenom;
	ArrayList<Role> roles = new ArrayList<Role>();
	
	public Utilisateur(String login, String nom, String prenom)
	{
		this.login = login;
		this.nom = nom;
		this.prenom = prenom;
	}
	
}
