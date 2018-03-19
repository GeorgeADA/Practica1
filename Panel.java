import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class Panel extends JPanel{
	
	private ArrayList<Ellipse2D.Double> nodos;
	private double diametro = 30.0;
	private boolean nodoPresionado;
	private int posicionNodoPresionado, numNodo;
	private JLabel info;
	
	public Panel(){
		
		nodos = new ArrayList<Ellipse2D.Double>();
		this.diametro = diametro;
		nodoPresionado = false;
		posicionNodoPresionado = 0;
		numNodo = 0;
		
		info = new JLabel("NODO SELECCIONADO: -");
		info.setBounds(200,50,300,20);
		this.add(info);
		
		setBackground(Color.orange);
		
		addMouseListener(new MouseAdapter(){	
			
			public void mouseClicked(MouseEvent evento1){ // Este método se activa cuando das un click sobre tu componente
				
				double centro_x = evento1.getX() - diametro/2;
				double centro_y = evento1.getY() - diametro/2;
				
				Ellipse2D.Double figura = new Ellipse2D.Double(centro_x,centro_y,diametro,diametro);
				nodos.add(figura);
				String.valueOf(numNodo);
				repaint();
				numNodo++;
				setCursor(new Cursor(Cursor.HAND_CURSOR ));
			}
			
			public void mousePressed(MouseEvent evento2){
				
				for(int i=0; i<nodos.size(); i++){
					if( nodos.get(i).contains(evento2.getX(), evento2.getY()) ){
						nodoPresionado = true;
						posicionNodoPresionado = i;
						info.setText("NODO SELECCIONADO: "+Integer.toString(i));
						break;
					}
					else{
						info.setText("NODO SELECCIONADO: -");
						nodoPresionado = false;
					}
				}
			}
			
		});
		
		addMouseMotionListener(new MouseMotionAdapter(){
			
			public void mouseDragged(MouseEvent evento3){ // Este método se activa cuando pulsas sobre un componente y arrastras el mouse
				
				
				if(nodoPresionado){
					if( (evento3.getX()>=35) && (evento3.getX()<=380) && (evento3.getY()>=35) && (evento3.getY()<=380) ){
						nodos.remove(posicionNodoPresionado);
						Ellipse2D.Double actualiza = new Ellipse2D.Double((evento3.getX()-diametro/2),(evento3.getY()-diametro/2),diametro,diametro);
						nodos.add(posicionNodoPresionado,actualiza);
						repaint();
					}
				}
				
			}
			public void	mouseMoved(MouseEvent evento4){ // Este método se usa cuando el mouse se ha movido dentro del componente pero sin ser pulsado
				
				boolean cambiomouse = false;
				for(int i=0; i<nodos.size(); i++){
					if( nodos.get(i).contains(evento4.getX(), evento4.getY()) ){
						cambiomouse = true;
						break;
					}
				}
				if(cambiomouse){
					setCursor(new Cursor(Cursor.HAND_CURSOR ));
				}
				else{
					setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
				}
			}	
		
		});

	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.white);
		
		Graphics2D aux = (Graphics2D)g;
		
		for(int i=0; i<nodos.size(); i++){
			aux.fill(nodos.get(i));		
		}
		
	}

}