import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

public class ComboImageText extends JPanel {
	private JComboBox comboBox ;
	public String nom[];
	
	public JComboBox jcomb()
	{
		return comboBox;
	}

 public ComboImageText() {
 

	  
	                                                                                                                    
  comboBox = new JComboBox();
  comboBox.setPreferredSize(new Dimension(200, 50));
setPreferredSize(new Dimension(40,10));
  // Model
  comboBox.setModel(populate());
  comboBox.setRenderer(new ImagesAndTextRenderer());
 
  

 }

 private DefaultComboBoxModel populate() {

  DefaultComboBoxModel boxModel = new DefaultComboBoxModel();
  boxModel.addElement(new ImagesNText(new ImageIcon("images/accordion.png"), "Accordeon"));
  boxModel.addElement(new ImagesNText(new ImageIcon("banjo.png"), "Banjo"));
  boxModel.addElement(new ImagesNText(new ImageIcon("basse.png"), "Basse"));
  boxModel.addElement(new ImagesNText(new ImageIcon("cymbales.png"), "Cymbales"));
  boxModel.addElement(new ImagesNText(new ImageIcon("flute.png"), "Flute"));
 
  boxModel.addElement(new ImagesNText(new ImageIcon("guitare acoustique.png"), "Guitare acoustique"));
  boxModel.addElement(new ImagesNText(new ImageIcon("guitare electrique.png"), "Guitare electrique"));
  boxModel.addElement(new ImagesNText(new ImageIcon("maraca.png"), "Maraca (Tchatcha)"));
  boxModel.addElement(new ImagesNText(new ImageIcon("piano.png"), "Piano"));
  boxModel.addElement(new ImagesNText(new ImageIcon("saxophone.png"), "Saxophone"));
  
  boxModel.addElement(new ImagesNText(new ImageIcon("snare.png"), "Snare"));
  boxModel.addElement(new ImagesNText(new ImageIcon("tambour.png"), "Tambour"));
  boxModel.addElement(new ImagesNText(new ImageIcon("trombone.png"), "Trombone"));
  boxModel.addElement(new ImagesNText(new ImageIcon("trompette.png"), "Trompete"));
  boxModel.addElement(new ImagesNText(new ImageIcon("violon.png"), "Violon"));
  
  boxModel.addElement(new ImagesNText(new ImageIcon("xylophone.png"), "Xylophone"));
  return boxModel;
 }



}

// 
class ImagesAndTextRenderer extends JLabel implements ListCellRenderer {


 public Component getListCellRendererComponent(JList list, Object val, int index, boolean isSelected,
   boolean cellHasFocus) {
  // TODO Auto-generated method stub

  ImagesNText imagesNText = (ImagesNText) val;
  setIcon(imagesNText.getImg());
  setText(imagesNText.getName());

  if (isSelected) {
   setBackground(list.getSelectionBackground());
   setForeground(list.getSelectionForeground());
  } else {

   setBackground(list.getBackground());
   setForeground(list.getForeground());
  }
  setFont(list.getFont());
  return this;
 }

}


class ImagesNText {

 private Icon img;
 private String name;

 public ImagesNText(Icon aicon, String aname) {
  this.img = aicon;
  this.name = aname;// TODO Auto-generated constructor stub
 }

 public Icon getImg() {
  return img;
 }

 public void setImg(Icon img) {
  this.img = img;
 }

 public String getName() {
  return name;
 }

 public void setName(String name) {
  this.name = name;
 }

}
 

