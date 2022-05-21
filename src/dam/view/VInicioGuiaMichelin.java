package dam.view;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import dam.control.ControladorRestaurantes;

import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.BorderLayout;

public class VInicioGuiaMichelin extends JFrame {
	public static final int ALTO = 600;
	public static final int ANCHO = 800;
	public static final String MNIM_CONSULTAR = "Consulta de Restaurantes";
	public static final String MNIM_REGISTRO = "Registro de Restaurante";
	public static final String MNIM_MODIFICACION = "Modificación de Restaurante";
	public static final String MNIM_SALIR = "Salir";
	
	private JMenu mnMantenimiento;
	private JMenuItem mntmRegistro;
	private JMenuItem mntmConsulta;
	private JMenuItem mntmModificacion;
	private JMenuItem mntmSalir;
	private JScrollPane scrpContenedor;
	
	
	public VInicioGuiaMichelin() {
		inicializarComponentes();
	}
	
	
	private void inicializarComponentes() {
		setTitle("* * G U I A   M I C H E L I N * *");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		setSize(ANCHO, ALTO);
		centrarVentana();
		
		crearMenu();
		
		scrpContenedor = new JScrollPane();
		getContentPane().add(scrpContenedor, BorderLayout.CENTER);
		
		JLabel lblSaludo = new JLabel("RESTAURANTES ESTRELLAS MICHELIN");
		lblSaludo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSaludo.setFont(new Font("Tahoma", Font.BOLD, 20));
		scrpContenedor.setColumnHeaderView(lblSaludo);
	}


	private void crearMenu() {
		JMenuBar mbMenu = new JMenuBar();
		setJMenuBar(mbMenu);
		
		mnMantenimiento = new JMenu("Mantenimiento");
		mnMantenimiento.setHorizontalAlignment(SwingConstants.CENTER);
		mbMenu.add(mnMantenimiento);
		
		mntmConsulta = new JMenuItem(MNIM_CONSULTAR);
		mnMantenimiento.add(mntmConsulta);
		
		JSeparator separator = new JSeparator();
		mnMantenimiento.add(separator);
		
		mntmRegistro = new JMenuItem(MNIM_REGISTRO);
		mnMantenimiento.add(mntmRegistro);
		
		JSeparator separator_1 = new JSeparator();
		mnMantenimiento.add(separator_1);
		
		mntmModificacion = new JMenuItem(MNIM_MODIFICACION);
		mnMantenimiento.add(mntmModificacion);
		
		JSeparator separator_2 = new JSeparator();
		mbMenu.add(separator_2);
		
		mntmSalir = new JMenuItem(MNIM_SALIR);
		mntmSalir.setHorizontalAlignment(SwingConstants.CENTER);
		mbMenu.add(mntmSalir);
		

	}


	private void centrarVentana() {
		setPreferredSize(new Dimension(ANCHO, ALTO));     
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();                  
		Dimension ventana = this.getPreferredSize();                      
		setLocation((pantalla.width - ventana.width) / 2,  (pantalla.height - ventana.height) / 2);
	}
	
	public void hacerVisible() {
		setVisible(true);
	}
	
	
	public void cargarPanel(JPanel panel) {
		scrpContenedor.setViewportView(panel);
	}
	
	
	public void setControlador(ControladorRestaurantes controlador) {
		mntmConsulta.addActionListener(controlador);
		mntmModificacion.addActionListener(controlador);
		mntmRegistro.addActionListener(controlador);
		mntmSalir.addActionListener(controlador);
	}
	
	
 }
