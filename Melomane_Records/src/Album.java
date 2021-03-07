/**
 * Cette classe permet de gerer les donnees concernant les albums
 * @author Anderson
 *
 */
public class Album 
{
	private int id_album;
	private String titre_album;
	private String format_album;
	private String date_lancement_album;
	private int producteur;
	
	/**
	 * Cette methode est un constructeur permettant d'intialiser les donnees concernant les albums
	 */
	public Album()
	{
		id_album = 0;
		titre_album = "";
		format_album = "";
		date_lancement_album = "";
	}
	
	/**
	 * Cette methode est un accesseur qui retourne l'identifiant d'un album
	 * @return l'identifiant de l'album
	 */
	public int getId_album()
	{
		return id_album;
	}
	
	/**
	 * Cette methode est un accesseur qui retourne le titre d'un album
	 * @return le titre de l'album
	 */
	public String getTitre_album()
	{
		return titre_album;
	}
	
	/**
	 * Cette methode est un accesseur qui retourne le format (disque, vinyl, cassette etc...) de l'album
	 * @return le format de l'album
	 */
	public String getFormat_album()
	{
		return format_album;
	}
	
	/**
	 * Cette methode est un accesseur qui retourne la date de lancement d'un album
	 * @return la date de lancement de l'album
	 */
	public String getDate_lancement_album()
	{
		return date_lancement_album;
	}
	
	public int getProducteur()
	{
		return producteur;
	}
	
	/**
	 * Cette methode est un mutateur qui permet de modifier l'identifiant d'un album
	 * @param l'identifiant de l'album
	 */
	public void setId_album(int id_album)
	{
		this.id_album = id_album;
	}
	
	/**
	 * Cette methode est un mutateur qui permet de modifier le titre d'un album
	 * @param le titre de l'album
	 */
	public void setTitre_album(String titre_album)
	{
		this.titre_album = titre_album;
	}
	
	/**
	 * Cette methode est un mutateur qui permet de modifier le format de l'album
	 * @param le format de l'album
	 */
	public void setFormat_album(String format_album)
	{
		this.format_album = format_album;
	}
	
	/**
	 * Cette methode est un mutateur qui permet de modifier la date de lancement d'un album
	 * @param la date de lancement de l'album
	 */
	public void setDate_lancement_album(String date_lancement_album)
	{
		this.date_lancement_album = date_lancement_album;
	}
	
	public void setProducteur(int id)
	{
		producteur=id;
	}
}
