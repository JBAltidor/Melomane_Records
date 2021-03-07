import javax.swing.JFrame;
public class Projet 
{

	public static void main(String[] args) 
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.print("SQL chargee");
		}
		catch (Exception e) 
		{
			System.out.println("erreur chargement:" + e);
		}
			
		JFrame frame = new Home();
		
		frame.setTitle("Le melomane records");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true); 
	}

}
