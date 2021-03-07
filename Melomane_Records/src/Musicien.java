/**
 * Cette classe permet de gerer les musiciens
 * @author Anderson
 *
 */
public class Musicien
{
	private String id_musicien;
	private String nom_musicien;
	private String prenom_musicien;
	private String adresse_musicien;
	private String num_musicien;
	
	/**
	 * Cette methode est un constructeur pour initialiser les données concernant les musiciens
	 */
	public Musicien()
	{
		id_musicien = "";
		nom_musicien= "";
		prenom_musicien = "";
		adresse_musicien = "";
		num_musicien = "";
	}
	
	/**
	 * Cette methode est un accesseur qui retourne l'identifiant d'un musicien
	 * @return l'identifiant du musicien
	 */
	public String getId_musicien()
	{
		return id_musicien;
	}
	
	/**
	 * Cette methode est un accesseur qui retourne le nom d'un musicien
	 * @return le nom du musicien
	 */
	public String getNom_musicien()
	{
		return nom_musicien;
	}
	
	/**
	 * Cette methode est un accesseur qui retourne le prenom d'un musicien
	 * @return le prenom du musicien
	 */
	public String getPrenom_musicien()
	{
		return prenom_musicien;
	}
	
	/**
	 * Cette methode est un accesseur qui retourne l'adresse d'un musicien
	 * @return l'adresse du musicien
	 */
	public String getAdresse_musicien()
	{
		return adresse_musicien;
	}
	
	/**
	 * Cette methode est un accesseur qui retourne le numero du musicien
	 * @return le numero du musicien
	 */
	public String getNum_musicien()
	{
		return num_musicien;
	}
	
	/**
	 * Cette methode est un mutateur qui permet de modifier l'identifiant d'un musicien
	 * @param l'identifiant du musicien
	 */
	public void setId_musicien(String id_musicien)
	{
		this.id_musicien = id_musicien;
	}
	
	/**
	 * Cette methode est un mutateur qui permet de modifier le nom d'un musicien
	 * @param le nom du musicien
	 */
	public void setNom_musicien(String nom_musicien)
	{
		this.nom_musicien = nom_musicien;
	}
	
	/**
	 * Cette methode est un mutateur qui permet de modifier le prenom d'un musicien
	 * @param le prenom du musicien
	 */
	public void setPrenom_musicien(String prenom_musicien)
	{
		this.prenom_musicien = prenom_musicien;
	}
	
	/**
	 * Cette methode est un mutateur qui permet de modifier l'adresse d'un musicien
	 * @param l'adresse du musicien
	 */
	public void setAdresse_musicien(String adresse_musicien)
	{
		this.adresse_musicien = adresse_musicien;
	}
	
	/**
	 * Cette methode est un mutateur qui permet de modifier le numero de telephone d'un musicien
	 * @param le numero du musicien
	 */
	public void setNum_musicien(String num_musicien)
	{
		this.num_musicien = num_musicien;
	}
	
	
}
