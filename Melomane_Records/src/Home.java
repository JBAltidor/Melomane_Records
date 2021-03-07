import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.jdesktop.swingx.JXDatePicker;


public class Home extends JFrame
{
	private static final int LONGUEUR_FENETRE = 1000;
	private static final int LARGEUR_FENETRE = 750;
	
	private JButton afficherMusicien;
	private JButton ajouterMusicien;
	private JButton afficherInstrument;
	private JButton ajouterInstrument;
	private JButton afficherAlbum;
	private JButton ajouterAlbum;
	private JButton afficherChanson;
	private JButton ajouterChanson;
	private JButton searchButton;
	private JButton delete;
	private int ActivePanel ;
	private JTable jt;
	private JTable jtable;
	private int whichjtable;
	//musicien 1 instruement 2 album 3 chanson 4
	private JLabel label;
	
	
	private JPanel panel;
	private JPanel panelinfo;
	private JTextField searchField;
	public static Connection connexion;
	public static Statement state;
	public static Statement stat;
	public static Statement stat1;
	
	private JPanel panel1 = new JPanel();
	
	public Home()
	{
		final JPanel panelmain=new JPanel();
		panelmain.setLayout(new BoxLayout(panelmain, BoxLayout.Y_AXIS));
		final JTextField ip=new JTextField();
		final JTextField port=new JTextField();
		final JTextField user=new JTextField();
		final JPasswordField mdp=new JPasswordField();
		final JPasswordField mdps=new JPasswordField();
		ip.setPreferredSize(new Dimension(40,2));
		port.setPreferredSize(new Dimension(40,2));
		user.setPreferredSize(new Dimension(40,2));
		mdp.setPreferredSize(new Dimension(40,2));
		ip.setText("127.0.0.1");
		port.setText("3306");
		user.setText("root");

		JButton connecter=new JButton("connecter");
		panelmain.add(new JLabel("Bienvenue dans Melomane Records Veuillez entrer les informations de")); 
		panelmain.add(new JLabel("la Database la Database doit s appeler melomane_database"));
		panelmain.add(new JLabel("IP:"));
		panelmain.add(ip);
		panelmain.add(new JLabel("port:"));
		panelmain.add(port);
		panelmain.add(new JLabel("Username:"));
		panelmain.add(user);
		panelmain.add(new JLabel("Mot de passe:"));
		panelmain.add(mdp);
		panelmain.add(new JLabel("Mot de passe systeme"));
		panelmain.add(mdps);
		
		panelmain.add(connecter);
		panelmain.setBackground(Color.GRAY);
		add(panelmain,BorderLayout.CENTER);
		
		setSize(500, 300);
		class BoutonConnecter implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				
				
				try{
					connexion=DriverManager.getConnection("jdbc:mysql://"+ip.getText()+":"+port.getText()+"/melomane_database",user.getText(),mdp.getText());
					if(mdps.getText().toString().compareTo("melomane1234;")==1)
					{
						throw new Exception();
					}
					state = connexion.createStatement();
					 stat = connexion.createStatement();
					 stat1 = connexion.createStatement();
					 JFrame f;
						f = new JFrame();
						JOptionPane.showMessageDialog(f,"Connection Reussi");
					 setSize(LONGUEUR_FENETRE, LARGEUR_FENETRE);
					 remove(panelmain);
						panel1.setLayout(new BorderLayout()); 
						createPanelMenu();
						createPanelInfo();
						createPanelSearch();
						add(panel1, BorderLayout.CENTER);
						whichjtable=0;
					}
				catch (Exception e) 
					{ JFrame f;
					f = new JFrame();
					JOptionPane.showMessageDialog(f,"Erreur Login mot de passe systeme ou parametre database incorect");}
				
			}
		}
		
	BoutonConnecter bt=new BoutonConnecter();
	connecter.addActionListener(bt);

	}
	
	public void createPanelSearch()
	{
		panel = new JPanel();
		buttonSearch();
		createTextField();
		panel.add(searchField);
		panel.add(searchButton);
		add(panel, BorderLayout.NORTH);
		panel.setBackground(Color.DARK_GRAY);
		panel.repaint();
	}
	
	public void createPanelMenu()
	{
		JPanel panel = new JPanel();
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		add(panel, BorderLayout.WEST);
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Menu"));
		panel.setBackground(Color.LIGHT_GRAY);
		panel.repaint();
		
		
		JPanel musicienPanel= createMusicienPanel();
		JPanel instrumentPanel = createInstrumentPanel();
		JPanel albumPanel = createAlbumPanel();
		JPanel chansonPanel = createChansonPanel();
		
		layout.setHorizontalGroup(layout.createParallelGroup()
				.addComponent(musicienPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(instrumentPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(albumPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(chansonPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addComponent(musicienPanel)
				.addComponent(instrumentPanel)
				.addComponent(albumPanel)
				.addComponent(chansonPanel));
	}
	
	public void createPanelInfo()
	{
		panelinfo = new JPanel();
		 panelinfo.setSize(200, 600);
		add(panelinfo, BorderLayout.EAST);
		
		panelinfo.setPreferredSize(new Dimension (200,100));

		
		panelinfo.setBorder(new TitledBorder(new EtchedBorder(), "Infos")); 
		panelinfo.setBackground(Color.LIGHT_GRAY);
		panelinfo.repaint();

	}
	public void fillPanelInfo(String[] info,int type_info)
	{
		panelinfo.removeAll();
	    remove(panelinfo);
		
		 panelinfo = new JPanel();
		 panelinfo.setSize(200, 600);
			add(panelinfo, BorderLayout.EAST);
			panelinfo.setBackground(Color.LIGHT_GRAY);
			panelinfo.repaint();

		panelinfo.setLayout(new BoxLayout(panelinfo, BoxLayout.Y_AXIS));
		panelinfo.setPreferredSize(new Dimension (200,100));

		if(type_info==1)
		{ 
			panelinfo.add(new JLabel("NOM:")); 
			panelinfo.add(new JLabel(info[0]));
			panelinfo.add(new JLabel("DATE:"));
			panelinfo.add(new JLabel(info[1]));
			
			
		}
		if(type_info==2)
		{
			panelinfo.add(new JLabel("CIN:"));
			panelinfo.add(new JLabel(info[0]));
			panelinfo.add(new JLabel("NOM:"));
			panelinfo.add(new JLabel(info[1]));
			panelinfo.add(new JLabel("PRENOM:"));
			panelinfo.add(new JLabel(info[2]));
			panelinfo.add(new JLabel("ADRESSE:"));
			panelinfo.add(new JLabel(info[3]));
			panelinfo.add(new JLabel("TELEPHONE:"));
			panelinfo.add(new JLabel(info[4]));
			panelinfo.add(new JLabel("Liste ALBUM:"));
			String sql="SELECT ID from musicien where IDM=?";
			PreparedStatement statement;
			try {
				statement = connexion.prepareStatement(sql);
				statement.setString(1, info[0]);
				ResultSet resultat=statement.executeQuery();
				resultat.next();
		        int id=resultat.getInt("ID");
				String sql1="SELECT * FROM ALBUM WHERE producteur=?";
				statement=connexion.prepareStatement(sql1);
				statement.setInt(1, id);
				resultat=statement.executeQuery();
				int i=0;
				while(resultat.next())
					i++;
				String val[][]=new String[i][2];
				resultat.beforeFirst();
				i=0;
				while(resultat.next())
				{
					val[i][0]=resultat.getString("TITRE");
					val[i][1]=resultat.getString("FORMAT");
					i++;
				}
				String col[]= {"TITRE","FORMAT"};
				JTable tab=new JTable(val,col);
				JScrollPane sp=new JScrollPane(tab);
				panelinfo.add(sp);
				
				
				//////////////////////////
				String sql2="SELECT ID_INSTRUMENT FROM musicien_instrument where ID_MUSICIEN=?";
				statement=connexion.prepareStatement(sql2);
				statement.setInt(1, id);
				resultat=statement.executeQuery();
				
				i=0;
				int j=0;
				while(resultat.next())
					i++;
				String val2[][]=new String[i][2];
				resultat.beforeFirst();
				while(resultat.next())
				{
					String sql3="SELECT nom,type from instrument where id=?";
					statement=connexion.prepareStatement(sql3);
					statement.setInt(1, resultat.getInt("ID_INSTRUMENT"));
					ResultSet resultat2=statement.executeQuery();
					resultat2.next();
					val2[j][0]=resultat2.getString("nom");
					val2[j][1]=resultat2.getString("type");
					System.out.println(val2[j][0]+" "+val2[j][1]);
					j++;
					
				}
				String col2[]={"NOM","TYPE"};
				JTable table1=new JTable(val2,col2);
				JScrollPane ss=new JScrollPane(table1);
				panelinfo.add(new JLabel("Liste instrument"));
				panelinfo.add(ss);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e);
			}
			
			 
		}
		if(type_info==3)
		{
			
			JButton ajouterchan = new JButton("ajouter une chanson");
			final JComboBox chansons = new JComboBox();
			chansons.addItem("");
			try 
			{
				String sql = "SELECT ID FROM ALBUM WHERE TITRE = ?";
				PreparedStatement stat = connexion.prepareStatement(sql);
				stat.setString(1, jt.getValueAt(jt.getSelectedRow(), 0).toString());
				ResultSet result  = stat.executeQuery();
				
				int jj = 0;
				while(result.next())
				{
					jj = result.getInt(1);
				}
				
				String sql1 = "SELECT ID,TITRE FROM CHANSON WHERE ALBUM = ?";
				PreparedStatement stat1 = connexion.prepareStatement(sql1);
				stat1.setInt(1, jj);
				ResultSet result1 = stat1.executeQuery();
				
				int i=0;
				
				while(result1.next())
				{
					
					i++;
				}
				String val[][]=new String[i][2];
				 i=0;
				result1.beforeFirst();
				while(result1.next())
				{
					val[i][0]=result1.getString(1);
					val[i][1]=result1.getString(2);
					i++;
				}
				System.out.println(i);
				String col[]= {"ID","TITRE"};
				 jtable = new JTable(val,col);
				 JScrollPane sp=new JScrollPane(jtable);
				  ListSelectionModel lis=jtable.getSelectionModel(); 
					
					
				  lis.addListSelectionListener(new ListSelectionListener()
						  {
					  
					  public void valueChanged(ListSelectionEvent e) {
						
					  whichjtable=1;
					  System.out.println("ou klike");
					  
						  }});
				 panelinfo.add(new JLabel("TITRE"));
					panelinfo.add(new JLabel(info[0]));
					panelinfo.add(new JLabel("FORMAT"));
					panelinfo.add(new JLabel(info[1]));
					panelinfo.add(new JLabel("DATE LANCEMENT"));
					panelinfo.add(new JLabel(info[2]));
					panelinfo.add(Box.createRigidArea(new Dimension(20,20)));
					panelinfo.add(sp);
					panelinfo.add(Box.createRigidArea(new Dimension(20,20)));
					panelinfo.add(ajouterchan);
					panelinfo.add(Box.createRigidArea(new Dimension(20,20)));
					
					class ajouterc implements ActionListener
					{
						public void actionPerformed(ActionEvent event)
						{
							try 
							{
								ResultSet resultat = state.executeQuery("SELECT * FROM chanson");
								while(resultat.next())
								{
									chansons.addItem(resultat.getString("titre"));
									
								}
							} 
							catch (SQLException e) 
							{
								e.printStackTrace();
							}
							
							panelinfo.add(chansons);
							panelinfo.add(Box.createRigidArea(new Dimension(20,20)));
							panelinfo.revalidate();
						}
					}
					
					ajouterc aj = new ajouterc();
					ajouterchan.addActionListener(aj);
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			
			final ArrayList<String> chantalb = new ArrayList<String>();
			
			class ajouterchansonalb implements ActionListener
			{
				public void actionPerformed(ActionEvent event)
				{
					chantalb.add((String) chansons.getSelectedItem());
					
					try
					{
						String sql1 = "SELECT ID FROM ALBUM WHERE TITRE = ?";
						PreparedStatement stat = connexion.prepareStatement(sql1);
						stat.setString(1, jt.getValueAt(jt.getSelectedRow(), 0).toString());
						ResultSet result = stat.executeQuery();
						
						int ji = 0;
						
						while(result.next())
						{
							ji = result.getInt(1);
						}
						
						int c;
						for(c=0; c<chantalb.size(); c++)
						{
							try 
							{
								System.out.println(chantalb.get(c));
								System.out.println(ji);
								String sql2 = "UPDATE CHANSON SET ALBUM = ? WHERE TITRE = ?";
								PreparedStatement stat1 = connexion.prepareStatement(sql2);
								stat1.setInt(1, ji);
								stat1.setString(2, chantalb.get(c));
								stat1.executeUpdate();
							}
							catch(SQLException e)
							{
								e.printStackTrace();
							}
						}
					}
					catch(SQLException e) 
					{
						e.printStackTrace();
					}
					panelinfo.revalidate();
				}
			}
		
			ajouterchansonalb essai = new ajouterchansonalb();
			chansons.addActionListener(essai);
	}
		if(type_info==4)
		{
			panelinfo.add(new JLabel("TITRE"));
			panelinfo.add(new JLabel(info[0]));
			panelinfo.add(new JLabel("AUTEUR"));
			panelinfo.add(new JLabel(info[1]));
			panelinfo.add(new JLabel("DUREE"));
			panelinfo.add(new JLabel(info[2]));
			String sql="SELECT ID from chanson where TITRE=? AND AUTEUR=?";
			PreparedStatement statement;
			try {
				statement = connexion.prepareStatement(sql);
				statement.setString(1, info[0]);
				statement.setString(2, info[1]);
				ResultSet resultat=statement.executeQuery();
				int id = 0;
				ResultSet resultat2;
				while(resultat.next())
					id=resultat.getInt("ID");
				String sql1="select ID_MUSICIEN from musicien_chanson where ID_CHANSON=?";
				statement=connexion.prepareStatement(sql1);
				statement.setInt(1, id);
				resultat=statement.executeQuery();
				int i=0;
				while(resultat.next())
					i++;
				String val[][]=new String[i][2];
				resultat.beforeFirst();
				i=0;
				while(resultat.next())
				{
					String sql2="select NOM,PRENOM from musicien where id=?";
					statement=connexion.prepareStatement(sql2);
					statement.setInt(1, resultat.getInt("ID_MUSICIEN"));
					resultat2=statement.executeQuery();
					resultat2.next();
					val[i][0]=resultat2.getString("NOM");
					val[i][1]=resultat2.getString("PRENOM");
					i++;
				}
				String col[]= {"NOM","RENOM"};
				JTable table=new JTable(val,col);
				JScrollPane ss=new JScrollPane(table);
				panelinfo.add(new JLabel("Liste Musiciens"));
				panelinfo.add(ss);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
			}
			
					
			
		}

			panelinfo.setBorder(new TitledBorder(new EtchedBorder(), "Infos"));
		

	}
	
	public JPanel createMusicienPanel()
	{
		JPanel panel = new JPanel();
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		panel.setBackground(Color.white);
		panel.repaint();
		
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		

		buttonAfficherMusicien();
		
		layout.setHorizontalGroup(layout.createParallelGroup()
				.addComponent(afficherMusicien, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addComponent(afficherMusicien));
		
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Musicien"));
		return panel;
	}
	
	public JPanel createInstrumentPanel()
	{
		JPanel panel = new JPanel();
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		panel.setBackground(Color.white);
		panel.repaint();
		
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		

		buttonAfficherInstrument();
		
		layout.setHorizontalGroup(layout.createParallelGroup()
	
				.addComponent(afficherInstrument, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		
		layout.setVerticalGroup(layout.createSequentialGroup()

				.addComponent(afficherInstrument));
		
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Instrument"));
		return panel;
	}
	
	public JPanel createAlbumPanel()
	{
		JPanel panel = new JPanel();
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		panel.setBackground(Color.white);
		panel.repaint();
		
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		

		buttonAfficherAlbum();
		
		layout.setHorizontalGroup(layout.createParallelGroup()
				.addComponent(afficherAlbum, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		
		layout.setVerticalGroup(layout.createSequentialGroup()

				.addComponent(afficherAlbum));
		
		panel.setBorder(new TitledBorder(new EtchedBorder(), "album"));
		return panel;
	}
	
	public JPanel createChansonPanel()
	{
		JPanel panel = new JPanel();
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);
		panel.setBackground(Color.white);
		panel.repaint();
		
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		

		buttonAfficherChanson();
		
		layout.setHorizontalGroup(layout.createParallelGroup()
				.addComponent(afficherChanson, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addComponent(afficherChanson));
		
		panel.setBorder(new TitledBorder(new EtchedBorder(), "chanson"));
		return panel;
	}
	
	
	public void buttonAjouterMusicien()
	{
		ajouterMusicien = new JButton(new ImageIcon("add.png"));
		ajouterMusicien.setBackground(Color.white);
		ajouterMusicien.setMargin(new Insets (5,30,5,30));
		
		
		class AjoutMusicien implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				panelinfo.removeAll();
				remove(panelinfo);
				createPanelInfo();
				panel1.removeAll();
				panel = new JPanel();
				GroupLayout layout = new GroupLayout(panel);
				panel.setLayout(layout);
				
				layout.setAutoCreateGaps(true);
				layout.setAutoCreateContainerGaps(true);
				
				JButton saveButton = new JButton(new ImageIcon("save.png"));
				JButton cancelButton = new JButton(new ImageIcon("back.png"));
				saveButton.setBackground(Color.white);
				saveButton.setMargin(new Insets (5,30,5,30));
				cancelButton.setBackground(Color.white);
				cancelButton.setMargin(new Insets (5,30,5,30));
				
				final JPanel listeinstru = new JPanel();
				JLabel label1 = new JLabel("ID Musicien");
				final JTextField ID = new JTextField(20);
				JLabel label2 = new JLabel("Nom Musicien");
				final JTextField nom = new JTextField(20);
				JLabel label3 = new JLabel("Prenom Musicien");
				final JTextField prenom = new JTextField(30);
				JLabel label4 = new JLabel("Adresse Musicien");
				final JTextField adresse = new JTextField(40);
				JLabel label5 = new JLabel("telephone Musicien");
				JLabel label6 = new JLabel("instruments");
				final JTextField telephone = new JTextField(10);
				final ComboImageText combobox=new ComboImageText();
				
				layout.setVerticalGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(label1)
								.addComponent(ID))
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(label2)
								.addComponent(nom))
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(label3)
								.addComponent(prenom))
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(label4)
								.addComponent(adresse))
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(label5)
								.addComponent(telephone))
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(label6)
								.addComponent(combobox.jcomb(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(saveButton)
								.addComponent(cancelButton))
						);
				
				layout.setHorizontalGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(label1)
								.addComponent(label2)
								.addComponent(label3)
								.addComponent(label4)
								.addComponent(label5)
								.addComponent(label6)
								.addComponent(saveButton))
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(ID)
								.addComponent(nom)
								.addComponent(prenom)
								.addComponent(adresse)
								.addComponent(telephone)
								.addComponent(combobox.jcomb(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(cancelButton))
						);
				
				final ArrayList<String> musicieninstru = new ArrayList<String>();
				
				class ajouterinstrumusic implements ActionListener
				{
					public void actionPerformed(ActionEvent event)
					{
						 String nom[]={"Accordeon","banjo","Basse","cymbales","Flute","Guitare acoustique","Guitare electrique","Maraca","Piano","Saxophone","Snare","Tambour","Trombone","Trompete","Xylophone"};  
						JLabel aff = new JLabel(nom[combobox.jcomb().getSelectedIndex()]);
						musicieninstru.add(nom[combobox.jcomb().getSelectedIndex()]);
						listeinstru.add(aff);
						panel1.add(listeinstru, BorderLayout.SOUTH);
						panel1.revalidate();
					}
				}
				
				ajouterinstrumusic essai = new ajouterinstrumusic();
				combobox.jcomb().addActionListener(essai);
				
				class saveMusicien implements ActionListener
				{
					public void actionPerformed(ActionEvent event) throws NumberFormatException
					{
						
						Musicien musicien = new Musicien();
						
						try
						{
						if((ID.getText().length()==0)||(nom.getText().length()==0)||(prenom.getText().length()==0))
						{ 
							throw new Exception();
						}
						if(musicieninstru.size()==0)
						{
							throw new Exception();
						}
						else
						{
							int ci;
							for(ci=0; ci<musicieninstru.size();ci++)
							{
								if(verification_instrument(musicieninstru.get(ci))==0)
								{
									throw new Exception();
								}
							}
						}
						if(!Verification.verification_CIN(ID.getText()))
						{
							throw new Exception_CIN();
						} 
						if(!Verification.verification_tel(telephone.getText()))
						{
							throw new VerificationTEL();
						}
						musicien.setId_musicien(ID.getText());
						musicien.setNom_musicien(nom.getText());
						musicien.setPrenom_musicien(prenom.getText());
						musicien.setAdresse_musicien(adresse.getText());
						musicien.setNum_musicien(telephone.getText());
						
						try
						{
						
							String sql = "INSERT INTO musicien(idm, nom, prenom, adresse, tel) VALUES (?,?,?,?,?)";
							PreparedStatement state = connexion.prepareStatement(sql);
							state.setString(1, musicien.getId_musicien());
							state.setString(2, musicien.getNom_musicien());
							state.setString(3, musicien.getPrenom_musicien());
							state.setString(4, musicien.getAdresse_musicien());
							state.setString(5, musicien.getNum_musicien());
							state.executeUpdate(); 
							
							String sql1 = "SELECT ID FROM musicien WHERE NOM = ?";
							PreparedStatement stat = connexion.prepareStatement(sql1);
							stat.setString(1, musicien.getNom_musicien());
							ResultSet result = stat.executeQuery();
							
							int ji = 0;
							
							while(result.next())
							{
								ji = result.getInt(1);
							}
							
							int c;
							for(c=0; c<musicieninstru.size() ; c++)
							{
								try 
								{
									String sql3 = "SELECT ID FROM INSTRUMENT WHERE type = ?";
									PreparedStatement stat2 = connexion.prepareStatement(sql3);
									stat2.setString(1, musicieninstru.get(c));
									ResultSet rsa = stat2.executeQuery();
									int id = 0;
									while(rsa.next())
									{
										id = rsa.getInt(1);
									}
									String sql2 = "INSERT INTO MUSICIEN_INSTRUMENT(ID_MUSICIEN, ID_INSTRUMENT) VALUE(?,?)";
									PreparedStatement stat1 = connexion.prepareStatement(sql2);
									stat1.setInt(1, ji);
									stat1.setInt(2, id);
									stat1.executeUpdate();
								}
								catch(SQLException e)
								{
									e.printStackTrace();
								}
							
							 
							JFrame f;
							f = new JFrame();
							JOptionPane.showMessageDialog(f,"Successfully saved!");
						}
					}
						
						
						catch(Exception exception)
						{
							JFrame f;
							f = new JFrame();
							JOptionPane.showMessageDialog(f,"nous n'avons pas pu sauvegarder vos donnes.");
							System.out.println(exception);
						}
						
						}
						catch(NumberFormatException exception)
						{
							JFrame f;
							f = new JFrame();
							JOptionPane.showMessageDialog(f,"il faut mettre des caracteres numerique pour un numero de telephone et l'ID");
						}
						catch(VerificationTEL exception)
						{
							JFrame f;
							f = new JFrame();
							JOptionPane.showMessageDialog(f, "Telephone incorrect");
						}
						catch(Exception_CIN e)
						{
							JFrame f;
							f = new JFrame();
							JOptionPane.showMessageDialog(f, "CIN incorect ou existant");
						}
						catch(Exception e)
						{
							
							JFrame f;
							f = new JFrame();
							JOptionPane.showMessageDialog(f,"Vous avez laisse vide un champ obligatoire ou vous avez un probleme lors de l'enregistrement des instruments ");
						}
					}
				}
				
				saveMusicien save = new saveMusicien();
				saveButton.addActionListener(save);
				
				class cancelSave implements ActionListener
				{
					public void actionPerformed(ActionEvent event)
					{
						
						afficher_table_musicien();

						
					}
				}
				
				
				cancelSave cancel = new cancelSave();
				cancelButton.addActionListener(cancel);
			
				
				
				panel1.add(panel, BorderLayout.CENTER);
				panel.setBorder(new TitledBorder(new EtchedBorder(), "Ajouter musicien"));
				panel1.setBackground(Color.LIGHT_GRAY);
				panel1.repaint();
				panel.setBackground(Color.LIGHT_GRAY);
				panel.repaint();
				
			}
		}
		
		AjoutMusicien musicien = new AjoutMusicien();
		ajouterMusicien.addActionListener(musicien);
	}
	public void afficher_table_musicien()
	{
		ActivePanel=1;
		try {
			ResultSet resultat=state.executeQuery("SELECT * FROM musicien");
			final List <Musicien> Musiciens=new ArrayList<Musicien>();
			
			
			while(resultat.next())
			{ 
				Musicien musicien=new Musicien();
				musicien.setNom_musicien(resultat.getString("NOM"));
				musicien.setPrenom_musicien(resultat.getString("PRENOM"));
				musicien.setAdresse_musicien(resultat.getString("ADRESSE"));
				musicien.setNum_musicien(resultat.getString("TEL"));
				musicien.setId_musicien(resultat.getString("IDM"));
	            Musiciens.add( musicien);
	        	
				
			} 
			 
			String col[]= {"CIN","NOM","PRENOM","ADRESSE","TELEPHONE"};
			final String val[][]= new String[Musiciens.size()][5]; 
			for(int i=0;i<Musiciens.size();i++)  
			{
					val[i][0]=Musiciens.get(i).getId_musicien();
					val[i][1]=Musiciens.get(i).getNom_musicien();  
					val[i][2]=Musiciens.get(i).getPrenom_musicien();
					val[i][3]=Musiciens.get(i).getAdresse_musicien();
					val[i][4]=Musiciens.get(i).getNum_musicien();
					System.out.println(i);
					
			} 
			jt=new JTable(val,col);
			  jt.setBounds(30,40,200,300);    
			  ListSelectionModel lis=jt.getSelectionModel(); 
				
				
			  lis.addListSelectionListener(new ListSelectionListener()
					  {
				  
				  public void valueChanged(ListSelectionEvent e) {
					
				  int row=jt.getSelectedRow(); 
				  fillPanelInfo(val[row],2); 
				  
					  }});
			  
			  jt.getDefaultEditor(String.class).addCellEditorListener(new CellEditorListener() {
				  
				public void editingCanceled(ChangeEvent e) {
					
					
					
				}

				public void editingStopped(ChangeEvent e) {
					
					int row=jt.getSelectedRow();
					int col=jt.getSelectedColumn();
					
					String[] coltab= {"IDM","NOM","PRENOM","ADRESSE","TEL"};
					String sql="UPDATE musicien SET musicien."+coltab[col]+"=? WHERE NOM=?";
						try {
							
							PreparedStatement st=connexion.prepareStatement(sql);
							st.setString(1, jt.getValueAt(row, col).toString());
							st.setString(2, Musiciens.get(row).getNom_musicien());
							System.out.println(st);
							if(jt.getValueAt(row, 0).toString().length()!=16||jt.getValueAt(row, 0).toString().length()==0)
							{
								throw new Exception_CIN();
							} 
							if(!Verification.verification_tel(jt.getValueAt(row, 4).toString()))
							{
								throw new VerificationTEL();
							}
							st.executeUpdate();
							
							JFrame f;
							f = new JFrame();
							JOptionPane.showMessageDialog(f, "Champ modifie avec succes");
						}
					
						
							
							catch(NumberFormatException exception)
							{
								JFrame f;
								f = new JFrame();
								JOptionPane.showMessageDialog(f,"il faut mettre des caracteres numerique pour un numero de telephone et l'ID");
							}
							catch(VerificationTEL exception)
							{
								JFrame f;
								f = new JFrame();
								JOptionPane.showMessageDialog(f, "Telephone incorrect");
							}
							catch(Exception_CIN en)
							{
								JFrame f;
								f = new JFrame();
								JOptionPane.showMessageDialog(f, "CIN incorect ou existant");
							}
							catch(Exception ej)
							{
								
								JFrame f;
								f = new JFrame();
								JOptionPane.showMessageDialog(f,"Vous avez laisse vide un champ obligatoire ");
							}				
						
						
						
						
				
					
					
					
					
					}
					
				}
			  );
			  remove(panel1);
			  
			  
			  JScrollPane sp=new JScrollPane(jt);
			  buttonAjouterMusicien();
			  buttonDelete();
			  panel1.removeAll();
				panel1.setBackground(Color.DARK_GRAY);
				panel1.repaint();
			  panel1=new JPanel();
			  add(panel1, BorderLayout.CENTER);
			  JPanel ajout = new JPanel();
			  panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
				panel1.setBackground(Color.DARK_GRAY);
				panel1.repaint();
			
			  panel1.add(sp);
			  panel1.add(ajout);
			  ajout.setLayout(new BoxLayout(ajout, BoxLayout.X_AXIS));
			  ajout.add(delete); 
			  ajout.add(ajouterMusicien);

			  panel1.revalidate();


			  
			  
			 
		} catch (SQLException e) {
			JFrame f;
			f = new JFrame();
			JOptionPane.showMessageDialog(f,"Erreur lors de la lecture des musiciens dans la base de donnee");
		}
	}
	
	public void afficher_table_instrument()
	{
		ActivePanel=2;
		try {
			SimpleDateFormat date_format=new SimpleDateFormat("dd-MM-yyyy");
			
			ResultSet resultat=state.executeQuery("SELECT * FROM instrument");
			List <Instrument> Instruments=new ArrayList<Instrument>();
			
			
			while(resultat.next())
			{ 
				Instrument instrument=new Instrument();
				instrument.setNom_instrument(resultat.getString("NOM"));
				instrument.setDate_acqu_instrument(date_format.format((resultat.getDate("DATE_ACQUISITION"))));
	            Instruments.add( instrument);
	        	
				
			} 
			 
			String col[]= {"NOM","DATE ACQUISITION"};
			final String val[][]= new String[Instruments.size()][2]; 
			for(int i=0;i<Instruments.size();i++)
			{
				
					val[i][0]=Instruments.get(i).getNom_instrument();  
					val[i][1]=Instruments.get(i).getDate_acqu_instrument();
				
					
			} 
			jt=new JTable(val,col);
			ListSelectionModel lis=jt.getSelectionModel();
			
			  lis.addListSelectionListener(new ListSelectionListener()
					  {
				  
				  public void valueChanged(ListSelectionEvent e) {
					
				  int row=jt.getSelectedRow(); 
				  fillPanelInfo(val[row],1);  
				  
					  }});
			  jt.setBounds(30,40,200,300);          
			  remove(panel1);
			  
			  
			  JScrollPane sp=new JScrollPane(jt);
			  buttonAjouterInstrument();
			  buttonDelete();
			  panel1.removeAll();
			  panel1=new JPanel();
			  panel1.setBackground(Color.DARK_GRAY);
				panel1.repaint();
			  add(panel1, BorderLayout.CENTER);
			  JPanel ajout = new JPanel();
			  panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
			  panel1.add(sp);
			  panel1.add(ajout);
			  ajout.setLayout(new BoxLayout(ajout, BoxLayout.X_AXIS));
			  ajout.add(delete); 
			  ajout.add(ajouterInstrument);
				panel.setBackground(Color.DARK_GRAY);
				panel.repaint();
			  panel1.revalidate();


			  
			  
			 
		} catch (SQLException e) {
			JFrame f;
			f = new JFrame();
			JOptionPane.showMessageDialog(f,"Erreur lors de la lecture des instruments dans la base de donnee");
		}
	}
	
	public void afficher_table_album()
	
	{
		ActivePanel=3;
		try {
			SimpleDateFormat date_format=new SimpleDateFormat("dd-MM-yyyy");
			
			ResultSet resultat=state.executeQuery("SELECT * FROM album");
			
			final List <Album> Albums=new ArrayList<Album>();
			while(resultat.next())
			{ 
				Album album=new Album();
				album.setTitre_album(resultat.getString("TITRE"));
				album.setFormat_album(resultat.getString("FORMAT"));
				album.setDate_lancement_album(date_format.format(resultat.getDate("DATE_LANCEMENT")));
				 
	            Albums.add(album);
	        	
				
			} 
			 
			String col[]= {"TITRE","FORMAT","DATE LANCEMENT"};
			final String val[][]= new String[Albums.size()][3]; 
			for(int i=0;i<Albums.size();i++)
			{
				
					val[i][0]=Albums.get(i).getTitre_album();
					val[i][1]=Albums.get(i).getFormat_album();
					val[i][2]=Albums.get(i).getDate_lancement_album();
				
					
			} 
			 jt=new JTable(val,col);
			  jt.setBounds(30,40,200,300); 
				ListSelectionModel lis=jt.getSelectionModel();
				
				  lis.addListSelectionListener(new ListSelectionListener()
						  {
					 
					  public void valueChanged(ListSelectionEvent e) {
						whichjtable=0;
					  int row=jt.getSelectedRow(); 
					  fillPanelInfo(val[row],3);  
					  
						  }});
			  jt.getDefaultEditor(String.class).addCellEditorListener(new CellEditorListener() {
				  
				public void editingCanceled(ChangeEvent e) {
					
					
					
				}

				public void editingStopped(ChangeEvent e) {
					
					int row=jt.getSelectedRow();
					int col=jt.getSelectedColumn();
					
					String[] coltab= {"TITRE","AUTEUR","DUREE"};
					String sql="UPDATE album SET album."+coltab[col]+"=? WHERE "+coltab[col]+"=?";
						try {
							
							PreparedStatement st=connexion.prepareStatement(sql);
							st.setString(1, jt.getValueAt(row, col).toString());
							st.setString(2, Albums.get(row).getTitre_album());
							if(jt.getValueAt(row, col).toString().length()==0)
							{
								throw new Exception();
							} 
							
							st.execute();
							
							JFrame f;
							f = new JFrame();
							JOptionPane.showMessageDialog(f, "Champ modifie avec succes");
						}
					
						
							
							catch(Exception ej)
							{
								
								JFrame f;
								f = new JFrame();
								JOptionPane.showMessageDialog(f,"Vous avez laisse vide un champ obligatoire ");
							}				
						
						
						
						
				
					
					
					
					
					}
					
				}
			  );
			  remove(panel1);
			  
			  
			  JScrollPane sp=new JScrollPane(jt);
			  buttonAjouterAlbum();
			  buttonDelete();
			  panel1.removeAll();
			  panel1=new JPanel();
			  panel1.setBackground(Color.DARK_GRAY);
				panel1.repaint();
			  add(panel1, BorderLayout.CENTER);
			  JPanel ajout = new JPanel();
			  panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
			  panel1.add(sp);
			  panel1.add(ajout);
			  ajout.setLayout(new BoxLayout(ajout, BoxLayout.X_AXIS));
			  ajout.add(delete); 
			  ajout.add(ajouterAlbum);
			  panel1.revalidate();


			  
			  
			 
		} catch (SQLException e) {
			JFrame f;
			f = new JFrame();
			JOptionPane.showMessageDialog(f,"Erreur lors de la lecture des Albums dans la base de donnee");
		}
	}
	
	public void afficher_table_chanson()
	{
		ActivePanel=4;
		try {
		
			
			ResultSet resultat=state.executeQuery("SELECT * FROM chanson");
			List <Chanson> Chansons=new ArrayList<Chanson>();
			
			
			while(resultat.next())
			{ 
				Chanson chanson=new Chanson();
				chanson.setTitre(resultat.getString("TITRE"));
				chanson.setAuteur(resultat.getString("AUTEUR"));
				chanson.setDuree(resultat.getInt("DUREE"));
				
	            Chansons.add(chanson);
	        	
				
			} 
			 
			String col[]= {"TITRE","AUTEUR","DUREE"};
			final String val[][]= new String[Chansons.size()][3]; 
			for(int i=0;i<Chansons.size();i++)
			{
				
					val[i][0]=Chansons.get(i).getTitre();
					val[i][1]=Chansons.get(i).getAuteur();
					val[i][2]=new Integer(Chansons.get(i).getDuree()).toString();
				
					
			} 
			
			 jt=new JTable(val,col);
			  jt.setBounds(30,40,200,300);    
				ListSelectionModel lis=jt.getSelectionModel();
				
				  lis.addListSelectionListener(new ListSelectionListener()
						  {
					  
					  public void valueChanged(ListSelectionEvent e) {
						
					  int row=jt.getSelectedRow(); 
					  fillPanelInfo(val[row],4);  
					  
						  }});
			  remove(panel1);
			  
			  
			  JScrollPane sp=new JScrollPane(jt);
			  buttonAjouterChanson();
			  buttonDelete();
			  panel1.removeAll();
			  panel1=new JPanel();
			  panel1.setBackground(Color.DARK_GRAY);
				panel1.repaint();
			  add(panel1, BorderLayout.CENTER);
			  JPanel ajout = new JPanel();
			  panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
			  panel1.add(sp);
			  panel1.add(ajout);
			  ajout.setLayout(new BoxLayout(ajout, BoxLayout.X_AXIS));
			  ajout.add(delete); 
			  ajout.add(ajouterChanson);
			  panel1.revalidate();


			  
			  
			 
		} catch (SQLException e) {
			JFrame f;
			f = new JFrame();
			JOptionPane.showMessageDialog(f,"Erreur lors de la lecture des chansons dans la base de donnee");
			System.out.println(e);
		}
	}
	
	public void buttonAfficherMusicien()
	{
		afficherMusicien = new JButton(new ImageIcon("art.png"));
		afficherMusicien.setBackground(Color.white);
		afficherMusicien.setMargin(new Insets (0,0,0,0));
		afficherMusicien.setBorder(null);


		class AffichMusicien implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				afficher_table_musicien();
				
				panelinfo.removeAll();
				remove(panelinfo);
				createPanelInfo();
			}
		}
		
		AffichMusicien amusicien = new AffichMusicien();
		afficherMusicien.addActionListener(amusicien);
	
	}
	
	public void buttonAjouterInstrument()
	{
		ajouterInstrument = new JButton(new ImageIcon("add.png"));
		ajouterInstrument.setBackground(Color.white);
		ajouterInstrument.setMargin(new Insets (5,30,5,30));

		
		class AjoutInstrument implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				panelinfo.removeAll();
				remove(panelinfo);
				createPanelInfo();
				panel1.removeAll();
				panel = new JPanel();
				GroupLayout layout= new GroupLayout(panel);
				panel.setLayout(layout);
				
				layout.setAutoCreateGaps(true);
				layout.setAutoCreateContainerGaps(true);
				
				JButton saveButton1 = new JButton(new ImageIcon("save.png"));
				JButton cancel1 = new JButton(new ImageIcon("back.png"));
				saveButton1.setBackground(Color.white);
				saveButton1.setMargin(new Insets (5,30,5,30));
				cancel1.setBackground(Color.white);
				cancel1.setMargin(new Insets (5,30,5,30));

				
				JLabel label1 = new JLabel("Nom instrument");
				final JTextField nom = new JTextField(20);
				
				JLabel label2 = new JLabel("Date d'acquisition");
				
				JLabel label3 = new JLabel("type instrument");
				
				final ComboImageText instru = new ComboImageText();	
			

					
				final JXDatePicker date = new JXDatePicker();
			
				layout.setVerticalGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(label1)
								.addComponent(nom)
								)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(label3)
								.addComponent(instru.jcomb(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(label2)
								.addComponent(date))
					
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(saveButton1)
								.addComponent(cancel1))
						);
				
				layout.setHorizontalGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(label1)
								.addComponent(label3)
								.addComponent(label2)
								.addComponent(saveButton1))
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(nom)
								.addComponent(date)
								.addComponent(instru.jcomb(), GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(cancel1)) 
						);

				class saveInstru implements ActionListener
				{
					public void actionPerformed(ActionEvent event)
					{
						Instrument instrument = new Instrument();
						try
						{
							if(nom.getText().length()==0)
							{
								throw new Exception();
							}
							String nom1[]={"Accordeon","banjo","Basse","cymbales","Flute","Guitare acoustique","Guitare electrique","Maraca","Piano","Saxophone","Snare","Tambour","Trombone","Trompete","Xylophone"};
							instrument.setNom_instrument(nom.getText());
							instrument.setType_instrument(nom1[instru.jcomb().getSelectedIndex()]);
							try
							{
								
								SimpleDateFormat date_format=new SimpleDateFormat("dd-MM-yyyy");
								java.util.Date dated = date_format.parse(date_format.format(date.getDate()));
								
								String sql = "INSERT INTO instrument (nom, type, date_acquisition) VALUES (?,?,?)"; 
								PreparedStatement state = connexion.prepareStatement(sql); 
								state.setString(1, instrument.getNom_instrument());
								state.setDate(3, new java.sql.Date(dated.getTime()));
								state.setString(2, instrument.getType_instrument());
								state.executeUpdate();  
								
								JFrame f;
								f = new JFrame();
								JOptionPane.showMessageDialog(f,"successfully saved");
							}
							
							catch(Exception exception)
							{
								System.out.println(exception);
							}
					}
						catch(Exception e)
						{
							JFrame f;
							f = new JFrame();
							JOptionPane.showMessageDialog(f,"Le champ NOM est obligatoire");
						}
						
					}
				}
				
				saveInstru save = new saveInstru();
				saveButton1.addActionListener(save);
				
				class cancelSave implements ActionListener
				{
					public void actionPerformed(ActionEvent event)
					{
						afficher_table_instrument();
					}
				}
				
				
				cancelSave cancel = new cancelSave();
				cancel1.addActionListener(cancel);
				
				panel1.add(panel, BorderLayout.CENTER);
				panel.setBorder(new TitledBorder(new EtchedBorder(), "Ajouter instrument"));
				panel1.setBackground(Color.LIGHT_GRAY);
				panel1.repaint();
				panel.setBackground(Color.LIGHT_GRAY);
				panel.repaint();
				
			}
		}
		
		AjoutInstrument instru = new AjoutInstrument();
		ajouterInstrument.addActionListener(instru);
	}
	
	public void buttonAfficherInstrument()
	{
		afficherInstrument = new JButton(new ImageIcon("ins.png"));
		afficherInstrument.setBackground(Color.white);
		afficherInstrument.setMargin(new Insets (0,0,0,0));
		afficherInstrument.setBorder(null);
		
		
		class AffichInstrument implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				panelinfo.removeAll();
				remove(panelinfo);
				createPanelInfo();
				afficher_table_instrument();
			}
		}
		
		AffichInstrument instrum = new AffichInstrument();
		afficherInstrument.addActionListener(instrum);
	}
	
	public void buttonAjouterAlbum()
	{
		ajouterAlbum = new JButton(new ImageIcon("add.png"));
		ajouterAlbum.setBackground(Color.white);
		ajouterAlbum.setMargin(new Insets (5,30,5,30));
		
		class AjoutAlbum implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				panelinfo.removeAll();
				remove(panelinfo);
				createPanelInfo();
				panel1.removeAll();
				panel = new JPanel();
				final JPanel listechanson = new JPanel();
				listechanson.setLayout(new BoxLayout(listechanson, BoxLayout.Y_AXIS));
				
				GroupLayout layout = new GroupLayout(panel);
				panel.setLayout(layout);
				
				layout.setAutoCreateGaps(true);
				layout.setAutoCreateContainerGaps(true);
				
				JButton saveButton2 = new JButton(new ImageIcon("save.png"));
				JButton cancel2 = new JButton(new ImageIcon("back.png"));
				saveButton2.setBackground(Color.white);
				saveButton2.setMargin(new Insets (5,30,5,30));
				cancel2.setBackground(Color.white);
				cancel2.setMargin(new Insets (5,30,5,30));

				
				final JComboBox format = new JComboBox();
				
				final JXDatePicker date = new JXDatePicker();
				
				format.addItem("");
				format.addItem("Vinyl");
				format.addItem("CD");
				format.addItem("Cassette");
				
				JLabel label2 = new JLabel("Titre Album");
				final JTextField titre = new JTextField(20);
				JLabel label3 = new JLabel("Format Albm");
				JLabel label4 = new JLabel("date de lancement");
				JLabel label5 = new JLabel("Producteur");
				JLabel label6 = new JLabel("chansons");
				final JComboBox prod= new JComboBox();
				prod.addItem("");
				final JComboBox chansons = new JComboBox();
				chansons.addItem("");
				
				try 
				{
					ResultSet resultat = state.executeQuery("SELECT * FROM musicien");
					while(resultat.next())
					{
						prod.addItem(resultat.getString("nom")+" "+resultat.getString("prenom")+resultat.getInt("id"));
						
					}
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
				
				try 
				{
					ResultSet resultat = state.executeQuery("SELECT * FROM chanson");
					while(resultat.next())
					{
						chansons.addItem(resultat.getString("titre"));
						
					}
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
				
				layout.setVerticalGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(label2)
								.addComponent(titre))
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(label3)
								.addComponent(format))
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(label4)
								.addComponent(date))
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(label5)
								.addComponent(prod))
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(label6)
								.addComponent(chansons))
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(saveButton2)
								.addComponent(cancel2))
						);
				
				layout.setHorizontalGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(label2)
								.addComponent(label3)
								.addComponent(label4)
								.addComponent(label5)
								.addComponent(label6)
								.addComponent(saveButton2))
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(titre)
								.addComponent(format)
								.addComponent(date)
								.addComponent(prod)
								.addComponent(chansons)
								.addComponent(cancel2))
						);
				
				
				final ArrayList<String> chantalb = new ArrayList<String>();
				
				class ajouterchansonalb implements ActionListener
				{
					public void actionPerformed(ActionEvent event)
					{
						JLabel aff = new JLabel((String) chansons.getSelectedItem());
						chantalb.add((String) chansons.getSelectedItem());
						listechanson.add(aff);
						panel1.add(listechanson, BorderLayout.SOUTH);
						panel1.revalidate();
					}
				}
				
				ajouterchansonalb essai = new ajouterchansonalb();
				chansons.addActionListener(essai);
				
				class saveAlbum implements ActionListener
				{
					public void actionPerformed(ActionEvent event)
					{
						Album album = new Album();
						String str=(String)prod.getSelectedItem();
						str = str.replaceAll("\\D+","");
						album.setProducteur(Integer.parseInt(str));
						
						String select = (String) format.getSelectedItem();						
						try
						{
						if((titre.getText().length()==0)||(select.length()==0))
						{ 
							throw new Exception();
						}
						album.setFormat_album(select);
						album.setTitre_album(titre.getText());
						
						try
						{
							SimpleDateFormat date_format=new SimpleDateFormat("dd-MM-yyyy");
							java.util.Date dated = date_format.parse(date_format.format(date.getDate()));
							
							String sql = "INSERT INTO album (titre, format, date_lancement,producteur) VALUES (?,?,?,?)";
							PreparedStatement state = connexion.prepareStatement(sql);
							state.setString(1, album.getTitre_album());
							state.setString(2, album.getFormat_album());
							state.setDate(3, new java.sql.Date(dated.getTime())); 
							state.setInt(4, album.getProducteur());
							state.executeUpdate();
							
							String sql1 = "SELECT ID FROM ALBUM WHERE TITRE = ?";
							PreparedStatement stat = connexion.prepareStatement(sql1);
							stat.setString(1, album.getTitre_album());
							ResultSet result = stat.executeQuery();
							
							int ji = 0;
							
							while(result.next())
							{
								ji = result.getInt(1);
							}
							
							int c;
							for(c=0; c<chantalb.size(); c++)
							{
								try 
								{
									System.out.println(chantalb.get(c));
									System.out.println(ji);
									String sql2 = "UPDATE CHANSON SET ALBUM = ? WHERE TITRE = ?";
									PreparedStatement stat1 = connexion.prepareStatement(sql2);
									stat1.setInt(1, ji);
									stat1.setString(2, chantalb.get(c));
									stat1.executeUpdate();
								}
								catch(SQLException e)
								{
									e.printStackTrace();
								}
								
							}
							
							JFrame f;
							f = new JFrame();
							JOptionPane.showMessageDialog(f,"successfully saved");
						}
						catch(Exception exception)
						{
							JFrame f;
							f = new JFrame();
							JOptionPane.showMessageDialog(f,"nous n'avons pas pu sauvegarder vos donnes. Reessayez!");
						}
						}
						catch(Exception e)
						{
							JFrame f;
							f = new JFrame();
							JOptionPane.showMessageDialog(f,"Vous avez laisse vide un champ obligatoire");
						}	
					}
				}
				
				saveAlbum save = new saveAlbum();
				saveButton2.addActionListener(save);
				
				class cancelSave implements ActionListener
				{
					public void actionPerformed(ActionEvent event)
					{
						afficher_table_album();
					}
				}
				
				
				cancelSave cancel = new cancelSave();
				cancel2.addActionListener(cancel);
				
				panel1.add(panel, BorderLayout.CENTER);
				panel.setBorder(new TitledBorder(new EtchedBorder(), "Ajouter album"));
				panel1.setBackground(Color.LIGHT_GRAY);
				panel1.repaint();
				panel.setBackground(Color.LIGHT_GRAY);
				panel.repaint();
			}
		}
		
		AjoutAlbum alb = new AjoutAlbum();
		ajouterAlbum.addActionListener(alb);
	}
	
	public void buttonAfficherAlbum()
	{
		afficherAlbum = new JButton(new ImageIcon("alb.png"));
		afficherAlbum.setBackground(Color.white);
		afficherAlbum.setMargin(new Insets (0,0,0,0));
		afficherAlbum.setBorder(null);
	
	class AffichAlbum implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			afficher_table_album();
			panelinfo.removeAll();
			remove(panelinfo);
			createPanelInfo();
		}
	} 
	
	AffichAlbum album = new AffichAlbum();
	afficherAlbum.addActionListener(album);
	}
	
	public void buttonAjouterChanson()
	{
		ajouterChanson = new JButton(new ImageIcon("add.png"));
		ajouterChanson.setBackground(Color.white);
		ajouterChanson.setMargin(new Insets (5,30,5,30));
		
		class AjoutChanson implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				panelinfo.removeAll();
				remove(panelinfo);
				createPanelInfo();
				panel1.removeAll();
				panel = new JPanel();
				GroupLayout layout = new GroupLayout(panel);
				panel.setLayout(layout);
				
				layout.setAutoCreateGaps(true);
				layout.setAutoCreateContainerGaps(true);
				
				JButton saveButton3 = new JButton(new ImageIcon("save.png"));
				JButton cancel3 = new JButton(new ImageIcon("back.png"));
				saveButton3.setBackground(Color.white);
				saveButton3.setMargin(new Insets (5,30,5,30));
				cancel3.setBackground(Color.white);
				cancel3.setMargin(new Insets (5,30,5,30));

				final JPanel listemusicien = new JPanel();
				JLabel label1 = new JLabel("titre chanson");
				final JTextField titre = new JTextField(20);
				JLabel label2 = new JLabel("auteur chanson");
				final JTextField auteur = new JTextField(20);
				JLabel label3 = new JLabel("duree chanson");
				final JTextField duree = new JTextField(10);
				JLabel label4 = new JLabel("album");
				JLabel label5 = new JLabel("musicien");
				final JComboBox musicien = new JComboBox();
				musicien.addItem("");
				final JComboBox album= new JComboBox();
				album.addItem("");
				
				try 
				{
					ResultSet resultat = state.executeQuery("SELECT * FROM album");
					while(resultat.next())
					{
						album.addItem(resultat.getString("titre"));
						
					}
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
				
				try 
				{
					ResultSet resultat = state.executeQuery("SELECT * FROM musicien");
					while(resultat.next())
					{
						musicien.addItem(resultat.getInt("id")+") "+resultat.getString("nom")+" "+resultat.getString("prenom"));
						
					}
				} 
				
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
				
				
				layout.setVerticalGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(label1)
								.addComponent(titre))
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(label2)
								.addComponent(auteur))
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(label3)
								.addComponent(duree))
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(label4)
								.addComponent(album))
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(label5)
								.addComponent(musicien))
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(saveButton3)
								.addComponent(cancel3))
						);
				
				layout.setHorizontalGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(label1)
								.addComponent(label2)
								.addComponent(label3)
								.addComponent(label4)
								.addComponent(label5)
								.addComponent(saveButton3))
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(titre)
								.addComponent(auteur)
								.addComponent(duree)
								.addComponent(album)
								.addComponent(musicien)
								.addComponent(cancel3))
						);
				
				final ArrayList<String> chanmus = new ArrayList<String>();
				
				class ajouterchanmus implements ActionListener
				{
					public void actionPerformed(ActionEvent event)
					{
						JLabel aff = new JLabel((String) musicien.getSelectedItem());
						String str = musicien.getSelectedItem().toString();
						str = str.replaceAll("\\D+","");
						chanmus.add(str);
						System.out.println(chanmus);
						listemusicien.add(aff);
						panel1.add(listemusicien, BorderLayout.SOUTH);
						panel1.revalidate();
					}
				}
				
				ajouterchanmus essai = new ajouterchanmus();
				musicien.addActionListener(essai);
				
				class saveChanson implements ActionListener
				{
					public void actionPerformed(ActionEvent event) 
					{
						Chanson chanson = new Chanson();
						try
						{
						if((titre.getText().length()==0)||(auteur.getText().length()==0)||(duree.getText().length()==0))
						{
							throw new Exception();
						}
						chanson.setTitre(titre.getText());
						chanson.setAuteur(auteur.getText());
						chanson.setDuree(Integer.parseInt(duree.getText())); 
						try
						{
							if((String) album.getSelectedItem()=="")
							{
								String sql = "INSERT INTO chanson (titre, auteur , duree) VALUES (?,?,?)";
								PreparedStatement state = connexion.prepareStatement(sql);
								state.setString(1, chanson.getTitre());
								state.setString(2, chanson.getAuteur());
								state.setInt(3, chanson.getDuree());
								state.executeUpdate();
							}
							else 
							{
								ResultSet result;
								String sql1 = "SELECT ID FROM ALBUM WHERE TITRE = ?";
								PreparedStatement stat = connexion.prepareStatement(sql1);
								stat.setString(1, (String) album.getSelectedItem());
								result = stat.executeQuery();
								int ji = 0;
								while(result.next())
								{
									ji = result.getInt(1);
								}
									String sql = "INSERT INTO chanson (titre, auteur , duree, album) VALUES (?,?,?,?)";
									PreparedStatement state = connexion.prepareStatement(sql);
									state.setString(1, chanson.getTitre());
									state.setString(2, chanson.getAuteur());
									state.setInt(3, chanson.getDuree());
									state.setInt(4, ji);
									state.executeUpdate();
							}
									
									String sql2 = "SELECT ID FROM CHANSON WHERE TITRE = ? AND AUTEUR = ?";
									PreparedStatement stat1 = connexion.prepareStatement(sql2);
									stat1.setString(1, chanson.getTitre());
									stat1.setString(2, chanson.getAuteur());
									ResultSet rs = stat1.executeQuery();
									
									int ii = 0;
									while(rs.next())
									{
										ii = rs.getInt(1);
									}
									System.out.println(""+ii);
									int c;
									for(c=0; c<chanmus.size(); c++)
									{
										try 
										{
											String sql3 = "INSERT INTO MUSICIEN_CHANSON(ID_CHANSON, ID_MUSICIEN) VALUE(?,?)";
											PreparedStatement stat2 = connexion.prepareStatement(sql3);
											stat2.setInt(1, ii);
											stat2.setInt(2, Integer.parseInt(chanmus.get(c)));
											stat2.executeUpdate();
										}
										catch(SQLException e)
										{
											e.printStackTrace();
										}
										catch(NumberFormatException exeption)
										{
											exeption.printStackTrace();
										}
										
									}
							JFrame f;
							f = new JFrame();
							JOptionPane.showMessageDialog(f,"successfully saved");
						}
						catch(Exception exception)
						{
							JFrame f;
							f = new JFrame();
							JOptionPane.showMessageDialog(f,"nous n'avons pas pu sauvegarder vos donnes. Reessayez!");
							System.out.println(exception);
						}
						}
						catch(NumberFormatException exception) 
						{
							JFrame f;
							f = new JFrame();
							JOptionPane.showMessageDialog(f,"il faut mettre des caracteres numerique pour la duree");
						}
						catch(Exception e)
						{
							JFrame f;
							f = new JFrame();
							JOptionPane.showMessageDialog(f,"Vous avez laisse vide un champ obligatoire");
						}
					}
				}
				
				saveChanson save = new saveChanson();
				saveButton3.addActionListener(save);
				
				class cancelSave implements ActionListener
				{
					public void actionPerformed(ActionEvent event)
					{
						afficher_table_chanson();
					}
				}
				
				
				cancelSave cancel = new cancelSave();
				cancel3.addActionListener(cancel);
				
				panel1.add(panel, BorderLayout.CENTER);
				panel.setBorder(new TitledBorder(new EtchedBorder(), "Ajouter chanson"));
				panel1.setBackground(Color.LIGHT_GRAY);
				panel1.repaint();
				panel.setBackground(Color.LIGHT_GRAY);
				panel.repaint();
			}
		}
		
		AjoutChanson chant = new AjoutChanson();
		ajouterChanson.addActionListener(chant);
	}
	
	public void buttonAfficherChanson()
	{
		remove(panel1);
		panel1.removeAll();
		  panel1=new JPanel();
		  add(panel1, BorderLayout.CENTER);
			afficherChanson = new JButton(new ImageIcon("mus.png"));
			afficherChanson.setBackground(Color.white);
			afficherChanson.setMargin(new Insets (0,0,0,0));
			afficherChanson.setBorder(null);
		
		class AffichChanson implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				afficher_table_chanson();
				panelinfo.removeAll();
				remove(panelinfo);
				createPanelInfo();
			}
		}
		
		AffichChanson chanson = new AffichChanson();
		afficherChanson.addActionListener(chanson);
	}
	
	public void buttonSearch()
	{
		searchButton = new JButton("search");
		
		class Search implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{

				switch(ActivePanel)
				{
				case 1:
					PreparedStatement state;
					try {
						state = connexion.prepareStatement("SELECT * FROM musicien WHERE NOM LIKE ? OR PRENOM LIKE ?");
						state.setString(1, "%"+searchField.getText()+"%");
						state.setString(2, "%"+ searchField.getText()+"%");
						ResultSet resultat=state.executeQuery();
						final List <Musicien> Musiciens=new ArrayList<Musicien>();
						
						
						while(resultat.next())
						{ 
							Musicien musicien=new Musicien();
							musicien.setNom_musicien(resultat.getString("NOM"));
							musicien.setPrenom_musicien(resultat.getString("PRENOM"));
							musicien.setAdresse_musicien(resultat.getString("ADRESSE"));
							musicien.setNum_musicien(resultat.getString("TEL"));
							musicien.setId_musicien(resultat.getString("IDM"));
				            Musiciens.add( musicien);
				        	
							
						} 
						 
						String col[]= {"CIN","NOM","PRENOM","TELEPHONE","ADRESSE"};
						final String val[][]= new String[Musiciens.size()][5]; 
						for(int i=0;i<Musiciens.size();i++)  
						{
								val[i][0]=Musiciens.get(i).getId_musicien();
								val[i][1]=Musiciens.get(i).getNom_musicien();  
								val[i][2]=Musiciens.get(i).getPrenom_musicien();
								val[i][3]=Musiciens.get(i).getNum_musicien();
								val[i][4]=Musiciens.get(i).getAdresse_musicien();
								System.out.println(i);
								
						} 
						 jt=new JTable(val,col);
						  jt.setBounds(30,40,200,300);  
						  jt.setCellSelectionEnabled(true);
							ListSelectionModel lis=jt.getSelectionModel(); 
							
							  lis.addListSelectionListener(new ListSelectionListener()
									  {
								  
								  public void valueChanged(ListSelectionEvent e) {
									
								  int row=jt.getSelectedRow(); 
								  fillPanelInfo(val[row],2); 
								  
									  }});
							  
							  jt.getDefaultEditor(String.class).addCellEditorListener(new CellEditorListener() {
								  
								public void editingCanceled(ChangeEvent e) {
									
									
									
								}

								public void editingStopped(ChangeEvent e) 
								{
									
									int row=jt.getSelectedRow();
									int col=jt.getSelectedColumn();
									
									String[] coltab= {"IDM","NOM","PRENOM","ADRESSE","TEL"};
									String sql="UPDATE musicien SET musicien."+coltab[col]+"=? WHERE "+coltab[col]+"=?";
										try {
											
											PreparedStatement st=connexion.prepareStatement(sql);
											st.setString(1, jt.getValueAt(row, col).toString());
											st.setString(2, Musiciens.get(row).getNom_musicien());
											if(jt.getValueAt(row, 0).toString().length()!=16||jt.getValueAt(row, 0).toString().length()==0)
											{
												throw new Exception_CIN();
											} 
											if(!Verification.verification_tel(jt.getValueAt(row, 4).toString()))
											{
												throw new VerificationTEL();
											}
											st.execute();
											
											JFrame f;
											f = new JFrame();
											JOptionPane.showMessageDialog(f, "Champ modifie avec succes");
										}
									
										
											
											catch(NumberFormatException exception)
											{
												JFrame f;
												f = new JFrame();
												JOptionPane.showMessageDialog(f,"il faut mettre des caracteres numerique pour un numero de telephone et l'ID");
											}
											catch(VerificationTEL exception)
											{
												JFrame f;
												f = new JFrame();
												JOptionPane.showMessageDialog(f, "Telephone incorrect");
											}
											catch(Exception_CIN en)
											{
												JFrame f;
												f = new JFrame();
												JOptionPane.showMessageDialog(f, "CIN incorect ou existant");
											}
											catch(Exception ej)
											{
												
												JFrame f;
												f = new JFrame();
												JOptionPane.showMessageDialog(f,"Vous avez laisse vide un champ obligatoire ");
											}													
									}
									
								}
							  );
							  remove(panel1);
							  
							  
						  JScrollPane sp=new JScrollPane(jt);
						  buttonAjouterMusicien();
						  panel1.removeAll();
						  panel1=new JPanel();
						  add(panel1, BorderLayout.CENTER);
						  panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
						  panel1.add(sp);
						  panel1.add(ajouterMusicien);
						  panel1.revalidate();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				case 4:
					try {
						state = connexion.prepareStatement("SELECT * FROM chanson WHERE TITRE LIKE ?");
						state.setString(1,"%"+searchField.getText()+"%");
						ResultSet resultat=state.executeQuery();
						List <Chanson> Chansons=new ArrayList<Chanson>();
						
						
						while(resultat.next())
						{ 
							Chanson chanson=new Chanson();
							chanson.setTitre(resultat.getString("TITRE"));
							chanson.setAuteur(resultat.getString("AUTEUR"));
							chanson.setDuree(resultat.getInt("DUREE"));
							
				            Chansons.add(chanson);
				        	
							
						} 
						 
						String col[]= {"TITRE","AUTEUR","DUREE"};
						final String val[][]= new String[Chansons.size()][3]; 
						for(int i=0;i<Chansons.size();i++)
						{
							
								val[i][0]=Chansons.get(i).getTitre();
								val[i][1]=Chansons.get(i).getAuteur();
								val[i][2]=new Integer(Chansons.get(i).getDuree()).toString();
							
								
						} 
						 jt=new JTable(val,col);
						jt.setCellSelectionEnabled(true);
						ListSelectionModel lis=jt.getSelectionModel();
						
						  lis.addListSelectionListener(new ListSelectionListener()
								  {
							 
							  public void valueChanged(ListSelectionEvent e) {
							
							  int row=jt.getSelectedRow(); 
							  fillPanelInfo(val[row],4);  
							  
								  }});
						  jt.setBounds(30,40,200,300);          
						  JScrollPane sp=new JScrollPane(jt);
						  buttonAjouterChanson();
						  panel1.removeAll();
						  panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
						  panel1.add(sp);
						  panel1.add(ajouterChanson);
						  panel1.revalidate();
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					break;
					
				}
			}
		}
		
		Search search = new Search();
		searchButton.addActionListener(search);
		
	}
	
	public void buttonDelete()
	{
		delete = new JButton(new ImageIcon("delete.png"));
		delete.setBackground(Color.white);
		delete.setMargin(new Insets (5,30,5,30));


		class deleter implements ActionListener
		{
			

			public void actionPerformed(ActionEvent event)
			{
				if(whichjtable==0)
				{
					System.out.println("Va effaceer");
				String col[][]= {{"IDM","NOM","PRENOM","TELEPHONE","ADRESSE"},{"NOM","DATE_ACQUISITION"},{"TITRE","FORMAT","DATE_LANCEMENT"},{"TITRE","DUREE","AUTEUR"}};
				String tab[]= {"musicien","instrument","album","chanson"};
				String effacer = jt.getValueAt(jt.getSelectedRow(), jt.getSelectedColumn()).toString();
			String txt = "DELETE FROM "+tab[ActivePanel-1]+" WHERE "+col[ActivePanel-1][jt.getSelectedColumn()]+" = ?";
			PreparedStatement statement;
			try {
				
				statement = connexion.prepareStatement(txt);
				statement.setString(1, effacer);
				statement.executeUpdate();
				System.out.println("table g");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.print(e);
			}
				}
				if(whichjtable==1)
				{
					whichjtable=0;
					String id=jtable.getValueAt(jtable.getSelectedRow(), 0).toString();
					System.out.println(id+"me voici");
					String sql="UPDATE chanson SET chanson.ALBUM=NULL WHERE chanson.ID=?";
					PreparedStatement statement;
					try {
						
						statement = connexion.prepareStatement(sql);
						statement.setInt(1, Integer.parseInt(id));
						System.out.println(statement);
						statement.executeUpdate();
						panelinfo.removeAll();
						panelinfo.revalidate();
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						System.out.print(e);
					}
					
				}
			
				
			}
			}
		  deleter del = new deleter();
			delete.addActionListener(del);
			
	}
	public void rechercheauto()
	{
		 switch(ActivePanel)
			{
			case 1:
				PreparedStatement state;
				try {
					state = connexion.prepareStatement("SELECT * FROM musicien WHERE NOM LIKE ? OR PRENOM LIKE ?");
					state.setString(1, "%"+searchField.getText()+"%");
					state.setString(2, "%"+ searchField.getText()+"%");
					ResultSet resultat=state.executeQuery();
					final List <Musicien> Musiciens=new ArrayList<Musicien>();
					
					
					while(resultat.next())
					{ 
						Musicien musicien=new Musicien();
						musicien.setNom_musicien(resultat.getString("NOM"));
						musicien.setPrenom_musicien(resultat.getString("PRENOM"));
						musicien.setAdresse_musicien(resultat.getString("ADRESSE"));
						musicien.setNum_musicien(resultat.getString("TEL"));
						musicien.setId_musicien(resultat.getString("IDM"));
			            Musiciens.add( musicien);
			        	
						
					} 
					 
					String col[]= {"CIN","NOM","PRENOM","TELEPHONE","ADRESSE"};
					final String val[][]= new String[Musiciens.size()][5]; 
					for(int i=0;i<Musiciens.size();i++)  
					{
							val[i][0]=Musiciens.get(i).getId_musicien();
							val[i][1]=Musiciens.get(i).getNom_musicien();  
							val[i][2]=Musiciens.get(i).getPrenom_musicien();
							val[i][3]=Musiciens.get(i).getNum_musicien();
							val[i][4]=Musiciens.get(i).getAdresse_musicien();
							System.out.println(i);
							
					} 
					 jt=new JTable(val,col);
					  jt.setBounds(30,40,200,300);  
					  jt.setCellSelectionEnabled(true);
						ListSelectionModel lis=jt.getSelectionModel(); 
						
						  lis.addListSelectionListener(new ListSelectionListener()
								  {
							  
							  public void valueChanged(ListSelectionEvent e) {
								
							  int row=jt.getSelectedRow(); 
							  fillPanelInfo(val[row],2); 
							  
								  }});
						  
						  jt.getDefaultEditor(String.class).addCellEditorListener(new CellEditorListener() {
							  
							public void editingCanceled(ChangeEvent e) {
								
								
								
							}

							public void editingStopped(ChangeEvent e) 
							{
								
								int row=jt.getSelectedRow();
								int col=jt.getSelectedColumn();
								
								String[] coltab= {"IDM","NOM","PRENOM","ADRESSE","TEL"};
								String sql="UPDATE musicien SET musicien."+coltab[col]+"=? WHERE "+coltab[col]+"=?";
									try {
										
										PreparedStatement st=connexion.prepareStatement(sql);
										st.setString(1, jt.getValueAt(row, col).toString());
										st.setString(2, Musiciens.get(row).getNom_musicien());
										if(jt.getValueAt(row, 0).toString().length()!=16||jt.getValueAt(row, 0).toString().length()==0)
										{
											throw new Exception_CIN();
										} 
										if(!Verification.verification_tel(jt.getValueAt(row, 4).toString()))
										{
											throw new VerificationTEL();
										}
										st.execute();
										
										JFrame f;
										f = new JFrame();
										JOptionPane.showMessageDialog(f, "Champ modifie avec succes");
									}
								
									
										
										catch(NumberFormatException exception)
										{
											JFrame f;
											f = new JFrame();
											JOptionPane.showMessageDialog(f,"il faut mettre des caracteres numerique pour un numero de telephone et l'ID");
										}
										catch(VerificationTEL exception)
										{
											JFrame f;
											f = new JFrame();
											JOptionPane.showMessageDialog(f, "Telephone incorrect");
										}
										catch(Exception_CIN en)
										{
											JFrame f;
											f = new JFrame();
											JOptionPane.showMessageDialog(f, "CIN incorect ou existant");
										}
										catch(Exception ej)
										{
											
											JFrame f;
											f = new JFrame();
											JOptionPane.showMessageDialog(f,"Vous avez laisse vide un champ obligatoire ");
										}													
								}
								
							}
						  );
						  remove(panel1);
						  
						  
					  JScrollPane sp=new JScrollPane(jt);
					  buttonAjouterMusicien();
					  panel1.removeAll();
					  panel1=new JPanel();
					  add(panel1, BorderLayout.CENTER);
					  panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
					  panel1.add(sp);
					  panel1.add(ajouterMusicien);
					  panel1.revalidate();
				} catch (SQLException d) {
					// TODO Auto-generated catch block
					d.printStackTrace();
				}
				break;
			case 4:
				try {
					state = connexion.prepareStatement("SELECT * FROM chanson WHERE TITRE LIKE ?");
					state.setString(1,"%"+searchField.getText()+"%");
					ResultSet resultat=state.executeQuery();
					List <Chanson> Chansons=new ArrayList<Chanson>();
					
					
					while(resultat.next())
					{ 
						Chanson chanson=new Chanson();
						chanson.setTitre(resultat.getString("TITRE"));
						chanson.setAuteur(resultat.getString("AUTEUR"));
						chanson.setDuree(resultat.getInt("DUREE"));
						
			            Chansons.add(chanson);
			        	
						
					} 
					 
					String col[]= {"TITRE","AUTEUR","DUREE"};
					final String val[][]= new String[Chansons.size()][3]; 
					for(int i=0;i<Chansons.size();i++)
					{
						
							val[i][0]=Chansons.get(i).getTitre();
							val[i][1]=Chansons.get(i).getAuteur();
							val[i][2]=new Integer(Chansons.get(i).getDuree()).toString();
						
							
					} 
					 jt=new JTable(val,col);
					jt.setCellSelectionEnabled(true);
					ListSelectionModel lis=jt.getSelectionModel();
					
					  lis.addListSelectionListener(new ListSelectionListener()
							  {
						 
						  public void valueChanged(ListSelectionEvent e) {
						
						  int row=jt.getSelectedRow(); 
						  fillPanelInfo(val[row],4);  
						  
							  }});
					  jt.setBounds(30,40,200,300);          
					  JScrollPane sp=new JScrollPane(jt);
					  buttonAjouterChanson();
					  panel1.removeAll();
					  panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
					  panel1.add(sp);
					  panel1.add(ajouterChanson);
					  panel1.revalidate();
					
				} catch (SQLException f) {
					// TODO Auto-generated catch block
					f.printStackTrace();
				}
				
				break;
				
			}
	}
	
	public void createTextField()
	{
		final int LimiteField = 20;
		searchField = new JTextField(LimiteField);
		searchField.getDocument().addDocumentListener(new DocumentListener()
		{

			
			

		
			public void changedUpdate(DocumentEvent arg0) {
				rechercheauto();
				
			}

			public void insertUpdate(DocumentEvent arg0) {
		rechercheauto();
				
			}

			
			public void removeUpdate(DocumentEvent arg0) {
				
				rechercheauto();
			}
	
		});
	}
	
	public int verification_instrument(String type)
	{
		String sql="SELECT * FROM instrument where type=? ";
		PreparedStatement statement;
		try {
			statement = connexion.prepareStatement(sql);
			statement.setString(1,type);
			ResultSet result=statement.executeQuery();
			int i=0;
			
			while(result.next())
			{
				i++;
			}
			if(i>0)
				return 1;
			else
				return 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
		
		
	}
	
}
