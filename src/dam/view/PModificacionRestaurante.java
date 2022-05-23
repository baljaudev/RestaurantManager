package dam.view;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import dam.control.ControladorRestaurantes;
import dam.model.Restaurante;

public class PModificacionRestaurante extends JPanel {


	
	public static final String BTN_GUARDAR_MOD = "Guardar nuevos datos";
	public static final String BTN_CANCELAR = "Cancelar";
	public static final String BTN_BUSCAR = "Buscar";
	
	private JTextField txtNombre;
	private JComboBox<String> cmbRegion;
	private JTextField txtCiudad;
	private JTextField txtDireccion;
	private JTextField txtPrecioMin;
	private JTextField txtPrecioMax;
	private JTextField txtTelefono;
	private JTextField txtWeb;
	private JButton btnGuardarMod;
	private JButton btnCancelar;
	private JComboBox<String> cmbCocina;
	private JSpinner spnEstrellas;
	private JButton btnBuscar;
	private DefaultComboBoxModel<String> dcbmRegiones;
	
	
	public PModificacionRestaurante() {
		inicializarComponentes();
	}


	private void inicializarComponentes() {
		setSize(VInicioGuiaMichelin.ANCHO, VInicioGuiaMichelin.ALTO);
		setLayout(null);
		
		JLabel lblTitulo = new JLabel("Modificación de Restaurantes");
		lblTitulo.setBounds(0, 0, 800, 25);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTitulo);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(37, 68, 91, 14);
		add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(134, 65, 159, 20);
		add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblTipoCocina = new JLabel("Cocina:");
		lblTipoCocina.setBounds(396, 68, 62, 14);
		add(lblTipoCocina);
		
		cmbCocina = new JComboBox<String>();
		cmbCocina.setEnabled(false);
		cmbCocina.setBounds(517, 64, 126, 22);
		cmbCocina.setModel(new DefaultComboBoxModel<String>(Restaurante.COCINA));
		add(cmbCocina);
		
		JLabel lblRegion = new JLabel("Región:");
		lblRegion.setBounds(37, 134, 71, 14);
		add(lblRegion);
		
		cmbRegion = new JComboBox<String>();
		cmbRegion.setEnabled(false);
		cmbRegion.setBounds(134, 130, 159, 22);
		dcbmRegiones = new DefaultComboBoxModel<>();
		cmbRegion.setModel(dcbmRegiones);
		add(cmbRegion);
		
		JLabel lblCiudad = new JLabel("Ciudad:");
		lblCiudad.setBounds(396, 134, 62, 14);
		add(lblCiudad);
		
		txtCiudad = new JTextField();
		txtCiudad.setBounds(517, 131, 126, 20);
		add(txtCiudad);
		txtCiudad.setColumns(10);
		
		JLabel lblDireccion = new JLabel("Dirección:");
		lblDireccion.setBounds(37, 214, 71, 14);
		add(lblDireccion);
		
		txtDireccion = new JTextField();
		txtDireccion.setBounds(134, 211, 509, 20);
		add(txtDireccion);
		txtDireccion.setColumns(10);
		
		JLabel lblDistincion = new JLabel("Distinción:");
		lblDistincion.setBounds(37, 319, 71, 14);
		add(lblDistincion);
		
		spnEstrellas = new JSpinner();
		spnEstrellas.setEnabled(false);
		spnEstrellas.setModel(new SpinnerNumberModel(1, 1, 3, 1));
		spnEstrellas.setBounds(134, 316, 42, 20);
		spnEstrellas.setEditor(new JSpinner.DefaultEditor(spnEstrellas));
		add(spnEstrellas);
		
		JLabel lblPrecioMin = new JLabel("Precio mínimo:");
		lblPrecioMin.setBounds(221, 319, 104, 14);
		add(lblPrecioMin);
		
		txtPrecioMin = new JTextField();
		txtPrecioMin.setBounds(315, 316, 86, 20);
		add(txtPrecioMin);
		txtPrecioMin.setColumns(10);
		
		JLabel lblPrecioMax = new JLabel("Precio máximo:");
		lblPrecioMax.setBounds(438, 319, 104, 14);
		add(lblPrecioMax);
		
		txtPrecioMax = new JTextField();
		txtPrecioMax.setColumns(10);
		txtPrecioMax.setBounds(557, 316, 86, 20);
		add(txtPrecioMax);
		
		JLabel lblTelefono = new JLabel("Teléfono:");
		lblTelefono.setBounds(37, 411, 71, 14);
		add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(134, 408, 109, 20);
		add(txtTelefono);
		txtTelefono.setColumns(10);
		
		JLabel lblWeb = new JLabel("Web:");
		lblWeb.setBounds(320, 411, 46, 14);
		add(lblWeb);
		
		txtWeb = new JTextField();
		txtWeb.setBounds(396, 408, 247, 20);
		add(txtWeb);
		txtWeb.setColumns(10);
		
		btnGuardarMod = new JButton(BTN_GUARDAR_MOD);
		btnGuardarMod.setEnabled(false);
		btnGuardarMod.setBounds(100, 470, 193, 23);
		add(btnGuardarMod);
		
		btnCancelar = new JButton(BTN_CANCELAR);
		btnCancelar.setEnabled(false);
		btnCancelar.setBounds(499, 470, 144, 23);
		add(btnCancelar);
		
		btnBuscar = new JButton(BTN_BUSCAR);
		btnBuscar.setBounds(332, 470, 126, 23);
		add(btnBuscar);
	}
	
	

	public void setControlaador(ControladorRestaurantes controlador) {
		btnBuscar.addActionListener(controlador);
		btnGuardarMod.addActionListener(controlador);
		btnCancelar.addActionListener(controlador);
	}


	
	public void limpiarComponentes() {
		txtNombre.setText("");
		cmbCocina.setSelectedIndex(0);
		cmbRegion.setSelectedIndex(0);
		txtCiudad.setText("");
		txtDireccion.setText("");
		spnEstrellas.setValue(1);
		txtPrecioMin.setText("");
		txtPrecioMax.setText("");
		txtTelefono.setText("");
		txtWeb.setText("");
		
	}


	public void mostrarError(String error) {
		JOptionPane.showMessageDialog(this, error, "Error de datos", JOptionPane.ERROR_MESSAGE);
	}


	public String obtenerNombre() {
		String nombre = txtNombre.getText();
		
		return nombre;
	}
	
	
	public void cargarCombo(ArrayList<String> listaRegiones) {
		dcbmRegiones.removeAllElements();
		dcbmRegiones.addAll(listaRegiones);
	}


	public void rellenarDatos(Restaurante restaurante) {
		
		String precioMin = String.valueOf(restaurante.getPrecioMin());
		
		if (restaurante.getPrecioMax() >  restaurante.getPrecioMin()) {
			String precioMax  =  String.valueOf(restaurante.getPrecioMax());
			txtPrecioMax.setText(precioMax);
		}
		
		txtNombre.setText(restaurante.getNombre());
		cmbCocina.setSelectedItem(restaurante.getCocina());
		cmbRegion.setSelectedItem(restaurante.getRegion());
		txtCiudad.setText(restaurante.getCiudad());
		txtDireccion.setText(restaurante.getDireccion());
		spnEstrellas.setValue(restaurante.getDistincion());
		txtPrecioMin.setText(precioMin);
		txtTelefono.setText(restaurante.getTelefono());
		txtWeb.setText(restaurante.getWeb());
	}


	public void hacerVisibleMod(boolean bandera) {
		cmbCocina.setEnabled(bandera);
		cmbRegion.setEnabled(bandera);
		spnEstrellas.setEnabled(bandera);
		btnBuscar.setEnabled(!bandera);
		txtNombre.setEnabled(!bandera);
		btnGuardarMod.setEnabled(bandera);
		btnCancelar.setEnabled(bandera);
	}


	public Restaurante comrpobarDatosMod() {
		Restaurante restMod = null;
		
		String ciudad = txtCiudad.getText();
		if (ciudad.isBlank()) {
			mostrarError("Debe introducir una ciudad");
		} else {
			try {
				double precioMin = Double.valueOf(txtPrecioMin.getText());
				double precioMax = Double.valueOf(txtPrecioMax.getText());
				
				if (precioMax < precioMin) {
					mostrarError("El precio mínimo no puede ser superior al máximo");
				} else {
					String nombre = txtNombre.getText();
					String cocina = (String) cmbCocina.getSelectedItem();
					String region = (String) cmbRegion.getSelectedItem();
					String direccion = txtDireccion.getText();
					int distincion = (int) spnEstrellas.getValue();
					String telefono = txtTelefono.getText();
					String web = txtWeb.getText();
					
					restMod = new Restaurante(0, nombre, region, ciudad, distincion, direccion, precioMin, precioMax, cocina, telefono, web);
				}
				
			} catch (NumberFormatException e) {
				mostrarError("El precio mínimo y máximo deben ser numéricos");
			}
		}
		
		
		return restMod;
	}
}
