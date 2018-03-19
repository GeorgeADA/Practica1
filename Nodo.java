import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Nodo extends JFrame{
	
	public Panel fr;

	public Nodo(){
		super("NODOS");
		setSize(450,450);
		fr = new Panel();
		getContentPane().setLayout(new GridLayout());
		getContentPane().add(fr); 
	}
	
	public static void main(String args[]){
		Nodo fr = new Nodo();
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.setVisible(true);
	}


}