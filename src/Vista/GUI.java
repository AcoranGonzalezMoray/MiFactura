package Vista;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.intellijthemes.FlatGradiantoMidnightBlueIJTheme;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.intellijthemes.FlatCarbonIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatHighContrastIJTheme;
import BoxPDF.GFG;
import BoxPDF.GetLinesFromPDF;
import Lectura.Procesamiento;
import Lectura.Salida;
import Modelo.Documentos;


import java.awt.*;
import java.awt.dnd.DropTarget;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class GUI extends JFrame implements ItemListener{
	
	private static final long serialVersionUID = 1L;
	private List<String> contenido = new ArrayList<String>();
	
	public static void main(String[] args) throws Exception{
		FlatLightLaf.setup();
    	GUI ventana = new GUI();
     }
    
	private JButton boton1, boton2;
	private TextField cantidad;
	private JLabel comoUsar;
	private JTextArea taN;
	private JTextArea taS;
	private JButton bu;
	private JButton empty;
	private TextField ficheros;
	private JScrollPane scroll;
	private JScrollPane scroll_clave;
	
    public GUI() throws IOException {
    	// Set the frame title
        super("MiFactura");
        Documentos D = new Documentos();
        GetLinesFromPDF P =new GetLinesFromPDF();
        Procesamiento F = new Procesamiento();
        GFG G = new GFG();
        Salida S = new Salida();
        
        

        JComboBox<String> tema= new JComboBox<String>();
        tema.addItem("Claro");
        tema.addItem("Oscuro");
        tema.addItem("Intelli");
        tema.addItem("Dracula");
        tema.addItem("Carbón");
        tema.addItem("Midnight");
        tema.addItem("High Contrast");       
		// Accion a realizar cuando el JComboBox cambia de item seleccionado.
		tema.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String seleccionado=(String)tema.getSelectedItem();
				if (seleccionado.compareTo("Claro")==0) FlatLightLaf.setup();
				if (seleccionado.compareTo("Oscuro")==0)  FlatDarkLaf.setup();
				if (seleccionado.compareTo("Intelli")==0) FlatIntelliJLaf.setup();
				if (seleccionado.compareTo("Dracula")==0) FlatDarculaLaf.setup();
				if (seleccionado.compareTo("Carbón")==0) FlatCarbonIJTheme.setup();
				if (seleccionado.compareTo("Midnight")==0) FlatGradiantoMidnightBlueIJTheme.setup();
				if (seleccionado.compareTo("High Contrast")==0) FlatHighContrastIJTheme.setup();
				FlatLaf.updateUI();

			}
		});
		JTextField clave = new JTextField("Ingrese Palabra Clave");
		clave.setPreferredSize(new Dimension(170,23));
        JTextField ficheros = new JTextField("Facturas :");
        JLabel comoUsar = new JLabel("¿Como Usar?");
       
        comoUsar.setForeground(Color.blue);
        JTextField claves = new JTextField("Palabras Clave:");
        JTextField salida = new JTextField("Salida :");
        ficheros.setEditable(false);
        claves.setEditable(false);
        salida.setEditable(false);
        
        JPanel paneltxt = new JPanel();
        paneltxt.setLayout(new BoxLayout(paneltxt,BoxLayout.Y_AXIS));
        paneltxt.setBorder(new EmptyBorder(0,0,5,5));
        JPanel paneltema= new JPanel();
        paneltema.setLayout(new BoxLayout(paneltema,BoxLayout.Y_AXIS));
        JPanel panelload = new JPanel();
        JPanel panelBU = new JPanel();
        paneltema.setLayout(new BoxLayout(paneltema,BoxLayout.X_AXIS));

        
        //Facturas
        JTextArea taN = new JTextArea();  
        taN.setPreferredSize(new Dimension(450,500));
        taN.setEditable(false);
        scroll = new JScrollPane(taN);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        //Palabras Clave
        JTextArea palabra_clave = new JTextArea();  
        palabra_clave.setPreferredSize(new Dimension(450,500));
        palabra_clave .setEditable(false);
        scroll_clave = new JScrollPane(palabra_clave );
        scroll_clave.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        
        // �rea de texto en el centro 
        JButton clave_pro = new JButton("Añadir");
        JButton bu = new JButton("Procesar");
        JButton ba = new JButton("Obtener Total");
        JButton empty = new JButton("Borrar Todo");
        bu.setVisible(false);
        ba.setVisible(false);
        empty.setVisible(false);
        panelBU.add(clave);
        panelBU.add(clave_pro);
        panelBU.add(bu);
        panelBU.add(ba);
        panelBU.add(empty);
        // Create the label
        JLabel myLabel = new JLabel("Arrastrar Facturas Aqui!", SwingConstants.CENTER);
        myLabel.setPreferredSize(new Dimension(450,500));
        myLabel.setVisible(false);
        // Create the drag and drop listener
        MyDragDropListener myDragDropListener = new MyDragDropListener(D);
        
		clave_pro.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  myLabel.setText("Arrastrar Facturas Aqui con palabra clave "+clave.getText());
				  myLabel.setVisible(true);
				  bu.setVisible(true);
				  
				  D.setClaves(clave.getText());
				  clave.setText("");
				  String temp_content = "";
				  for(int i=0;i<D.getClaves().size();i++) {
					  temp_content += "Clave("+i+"): "+D.getClaves().get(i).toString()+"\n";
				  }
				  palabra_clave.setText(temp_content);
			  } 
		} );
	
		
		comoUsar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
				  try {
					    File objetofile = new File ("Instrucción MiFactura.pdf");
			            Desktop.getDesktop().open(objetofile);

			     }catch (IOException ex) {

			            System.out.println(ex);
			     }
            }

        });
		
		bu.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  taN.setText("");
				  String content="";
				  D.setDocumentos(myDragDropListener.R().getDocumentos());
				  ficheros.setText("Facturas: "+D.getDocumentos().size());
				  for( int i=0;i<D.getDocumentos().size();i++) {
					 content+=D.getDocumentos().get(i).toString()+"\n";
				  }
				  if(!D.getDocumentos().isEmpty()) {
					  bu.setVisible(true);
					  ba.setVisible(true);
					  empty.setVisible(true);
				  }
				  taN.setText(content);
			  } 
		} );
		
		ba.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  try {
					G.Fusionar(D);
					P.CARGAR();
					contenido = F.Facturas(P.getLines(), D.getClaves());
					S.CrearSalida(contenido);
				  } catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				  }
				 
				  P.setLinesEmpty();
			  } 
		} );
		
		empty.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				 D.getDocumentos().clear();
				 bu.setVisible(false);
				 ba.setVisible(false);
				 empty.setVisible(false);
				 taN.setText("");
				 palabra_clave.setText("");
				 ficheros.setText("Facturas: "+D.getDocumentos().size());
			  } 
		} );
        // Connect the label with a drag and drop listener
        new DropTarget(myLabel, myDragDropListener);

        // Add the label to the content
        paneltxt.add(ficheros);
        paneltxt.add(scroll);
        paneltxt.add(claves);
        paneltxt.add(scroll_clave);
        paneltxt.add(comoUsar);
        paneltema.add(tema);
        panelload.add(paneltema);
        panelload.add(myLabel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().add(BorderLayout.WEST, panelload);
        this.getContentPane().add(BorderLayout.CENTER, panelBU);
        this.getContentPane().add(BorderLayout.EAST, paneltxt);
        // Set the size
        this.setSize(1250, 500);
        // Show the frame
        this.setVisible(true);
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}
}