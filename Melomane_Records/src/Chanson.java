/**
 * Cette classe permet de gerer les donnees concernant les chansons
 * @author Anderson
 *
 */
public class Chanson 
{
	private String titre;
	private String auteur;
	private int duree; 
	
	/**
	 * Cette methode est un constructeur permettant d'intialiser les donnees concernant les chansons
	 */
	public Chanson()
	{
		titre = "inconnu";
		auteur = "inconnu";
		duree = 0;
	}
	
	/**
	 * Cette methode est un accesseur qui retourne le titre d'une chanson
	 * @return le titre de la chanson
	 */
	public String getTitre()
	{
		return titre;
	}
	
	/**
	 * Cette methode est un accesseur qui retourne le nom de l'auteur d'une chanson
	 * @return le nom de l'auteur de la chanson
	 */
	public String getAuteur()
	{
		return auteur;
	}
	
	/**
	 * Cette methode est un accesseur qui retourne la duree d'une chanson
	 * @return la duree de la chanson en minutes
	 */
	public int getDuree()
	{
		return duree;
	}
	
	/**
	 * Cette methode est un mutateur qui permet de modifier le titre d'une chanson
	 * @param le titre de la chanson
	 */
	public void setTitre(String titre)
	{
		this.titre = titre;
	}
	
	/**
	 * Cette methode est un mutateur qui permet de modifier le nom de l'auteur d'une chanson
	 * @param l'auteur de la chanson
	 */
	public void setAuteur(String auteur)
	{
		this.auteur = auteur;
	}
	
	/**
	 * Cette methode est un mutateur qui permet de modifier la duree d'une chanson
	 * @param la duree de la chanson
	 */
	public void setDuree(int duree)
	{
		this.duree = duree;
	}
}
