/**
 * Cette classe permet de gerer les instruments
 * @author Anderson
 *
 */
public class Instrument 
{
	private String nom_instrument;
	private String type_instrument;
	private String date_acqu_instrument;
	
	/**
	 * Cette methode est un constructeur pour initialiser les donnees concernant les instruments
	 */
	public Instrument()
	{
		nom_instrument = "";
		type_instrument = "";
		date_acqu_instrument = "";
	}
	
	/**
	 * Cette methode est un accesseur qui retourne le nom d'un instrument
	 * @return le nom de l'instrument
	 */
	public String getNom_instrument()
	{
		return nom_instrument;
	}
	
	public String getType_instrument()
	{
		return type_instrument;
	}
	
	/**
	 * Cette methode est un accesseur qui retourne la date d'acquisition d'un instrument
	 * @return la date d'acquision de l'instrument
	 */
	public String getDate_acqu_instrument()
	{
		return date_acqu_instrument;
	}
	
	/**
	 * Cette methode est un mutateur qui permet de modifier le nom d'un instrument
	 * @param le nom de l'instrument
	 */
	public void setNom_instrument(String nom_instrument)
	{
		this.nom_instrument = nom_instrument;
	}
	
	public void setType_instrument(String type_instrument)
	{
		this.type_instrument = type_instrument;
	}
	/**
	 * Cette methode est un mutateur qui permet de modifier la date d'acquisition d'un instrument
	 * @param la date d'acquisition de l'instrument
	 */
	public void setDate_acqu_instrument(String date_acqu_instrument)
	{
		this.date_acqu_instrument = date_acqu_instrument;
	}
}
