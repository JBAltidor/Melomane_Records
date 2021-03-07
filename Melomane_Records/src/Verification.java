
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Verification {
	
public static boolean  verification_CIN(String cin)
{
	if(cin.length()!=16 )
		return false; 
	
	try
	{
		Long.parseLong(cin);
	}
	catch(Exception exception)
	{
		return false;
	}
	
	String sql = "SELECT IDM FROM musicien WHERE IDM=?"; 
PreparedStatement state;
int i=0;
try {
	state = Home.connexion.prepareStatement(sql);
	state.setString(1, cin); 

	ResultSet resultat=state.executeQuery();
	
	
	while(resultat.next())
		i++;
	
	
} catch (SQLException e1) {
	
	e1.printStackTrace();
} 

if(i==0)
	return true;
else
	return false; 

}
 public static boolean verification_tel(String tel)
 {
		if(tel.length()!=8 )
			return false; 
		
		try
		{
			Long.parseLong(tel);
		}
		catch(Exception exception)
		{
			return false;
		}
		return true;
 }


}
