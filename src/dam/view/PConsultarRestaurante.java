package dam.view;

import javax.swing.JPanel;

import dam.control.ControladorRestaurantes;
import dam.db.persistencia.RestauranteContract;
import dam.model.Restaurante;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PConsultarRestaurante extends JPanel {
	
	public static final String BTN_CONSULTAR = "Consultar";
	public static final String BTN_ELIMINAR = "Eliminar";
	public static final String TODOS_ELEMENTOS = "TODAS";
	
	private JComboBox<String> cmbRegion;
	private JComboBox<String> cmbDistincion;
	private JLabel lblListadoRest;
	private JButton btnConsultar;
	private JTable tblRestaurantes;
	private JScrollPane scrpTablaRest;
	private DefaultTableModel dtmTablaConsulta;
	private DefaultComboBoxModel<String> dcbmRegiones;
	private JButton btnEliminar;
	
	
	public PConsultarRestaurante() {
		
		inicializarComponentes();
		
	}



	private void inicializarComponentes() {
		setSize(VInicioGuiaMichelin.ANCHO, VInicioGuiaMichelin.ALTO);
		setLayout(null);
		
		JLabel lblTitulo = new JLabel("Consulta de Restaurantes");
		lblTitulo.setBounds(0, 0, 800, 25);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTitulo);
		
		JLabel lblFiltro = new JLabel("Filtro:");
		lblFiltro.setBounds(97, 49, 67, 14);
		add(lblFiltro);
		
		JLabel lblRegion = new JLabel("Región:");
		lblRegion.setBounds(97, 93, 67, 14);
		add(lblRegion);
		
		cmbRegion = new JComboBox<String>();
		cmbRegion.setBounds(188, 89, 171, 22);
		dcbmRegiones = new DefaultComboBoxModel<>();
		cmbRegion.setModel(dcbmRegiones);
		add(cmbRegion);
		
		JLabel lblDistincion = new JLabel("Distinción:");
		lblDistincion.setBounds(440, 93, 74, 14);
		add(lblDistincion);
		
		cmbDistincion = new JComboBox<String>();
		cmbDistincion.setBounds(523, 89, 75, 22);
		cmbDistincion.setModel(new DefaultComboBoxModel<String>(Restaurante.DISTINCION));
		add(cmbDistincion);
		
		lblListadoRest = new JLabel("Listado de restaurantes:");
		lblListadoRest.setBounds(97, 160, 147, 14);
		add(lblListadoRest);
		
		btnConsultar = new JButton(BTN_CONSULTAR);
		btnConsultar.setBounds(483, 156, 115, 23);
		add(btnConsultar);
		
		scrpTablaRest = new JScrollPane();
		scrpTablaRest.setBounds(62, 213, 681, 286);
		add(scrpTablaRest);
		
		tblRestaurantes = new JTable();
		scrpTablaRest.setViewportView(tblRestaurantes);
		
		btnEliminar = new JButton(BTN_ELIMINAR);
		btnEliminar.setBounds(628, 156, 115, 23);
		add(btnEliminar);
		configurarTabla();
	}

	
	public void hacerTabVisi(boolean b) {
		scrpTablaRest.setVisible(b);
		lblListadoRest.setVisible(b);
	}
	
	
	
	private void configurarTabla() {
		dtmTablaConsulta = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		tblRestaurantes.setModel(dtmTablaConsulta);
		
		dtmTablaConsulta.addColumn(RestauranteContract.COL_NOMBRE);
		dtmTablaConsulta.addColumn(RestauranteContract.COL_CIUDAD);
		dtmTablaConsulta.addColumn(RestauranteContract.COL_DISTINCION);
		dtmTablaConsulta.addColumn(RestauranteContract.COL_COCINA);
		dtmTablaConsulta.addColumn(RestauranteContract.COL_PRECIO);
		
		tblRestaurantes.getColumn(RestauranteContract.COL_NOMBRE).setPreferredWidth(40);
		tblRestaurantes.getColumn(RestauranteContract.COL_CIUDAD).setPreferredWidth(60);
		tblRestaurantes.getColumn(RestauranteContract.COL_DISTINCION).setPreferredWidth(30);
		tblRestaurantes.getColumn(RestauranteContract.COL_COCINA).setPreferredWidth(40);
		tblRestaurantes.getColumn(RestauranteContract.COL_PRECIO).setPreferredWidth(50);
		
	}




	public void setControlaador(ControladorRestaurantes controlador) {
		btnConsultar.addActionListener(controlador);
		btnEliminar.addActionListener(controlador);
		
	}



	public void rellenarTabla(ArrayList<Restaurante> listaRestaurantes) {
		dtmTablaConsulta.getDataVector().clear();
		Object[] fila = new Object[5];
		
		for (Restaurante restaurante : listaRestaurantes) {
			fila[0] = restaurante.getNombre();
			
			fila[1] = restaurante.getCiudad();
			
			if (restaurante.getDistincion()==1) {
				fila[2] = "*";
			} else if (restaurante.getDistincion()==2) {
				fila[2] = "**";
			} else {
				fila[2] = "***";
			}
			
			fila[3] = restaurante.getCocina();
			
			if (restaurante.getPrecioMax()  == 0) {
				fila[4]  =  restaurante.getPrecioMin();
			} else {
				fila[4] = restaurante.getPrecioMin() + " - " + restaurante.getPrecioMax();
			}
			
			dtmTablaConsulta.addRow(fila);
			
		}
		
	}
	
	
	public String filtroRegion() {
		String region = "";
		
		region = (String) cmbRegion.getSelectedItem();
		
		return region;		
	}
	
	
	public String filtroDistincion() {
		String distincion = "";
		
		distincion = (String) cmbDistincion.getSelectedItem();
		
		return distincion;
	}



	public String restauranteEliminar() {
		String nombreRes = "";
		
		if (tblRestaurantes.getSelectedRow() >= 0) { //si es menor, es que no hay ninguno seleccionado
			int columna = 0; //la columna 0 contiene el nombre de los restaurantes según hemos configurado la tabla
			int fila = tblRestaurantes.getSelectedRow();
			nombreRes = tblRestaurantes.getModel().getValueAt(fila, columna).toString(); //asiganamos al String el valor de la columna 0 (nombre) de la fila seleccionada
		}
		return nombreRes;
	}
	
	
	public void cargarCombo(ArrayList<String> listaRegiones) {
		dcbmRegiones.removeAllElements(); //limpiamos el combobox
		dcbmRegiones.addElement(TODOS_ELEMENTOS);
		dcbmRegiones.addAll(listaRegiones);
	}


}
