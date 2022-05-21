package dam.view;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import dam.control.ControladorRestaurantes;
import dam.model.Restaurante;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;




public class PRegistroRestaurante extends JPanel {
	
	public static final String BTN_GUARDAR = "Guardar datos";
	public static final String BTN_LIMPIAR = "Limpiar datos";
	
	private JTextField txtNombre;
	private JComboBox<String> cmbRegion;
	private JTextField txtCiudad;
	private JTextField txtDireccion;
	private JTextField txtPrecioMin;
	private JTextField txtPrecioMax;
	private JTextField txtTelefono;
	private JTextField txtWeb;
	private JButton btnGuardar;
	private JButton btnLimpiar;
	private JComboBox<String> cmbCocina;
	private JSpinner spnEstrellas;
	private DefaultComboBoxModel<String> dcbmRegiones;
	
	
	public PRegistroRestaurante() {
		inicializarComponentes();
	}


	private void inicializarComponentes() {
		setSize(VInicioGuiaMichelin.ANCHO, VInicioGuiaMichelin.ALTO);
		setLayout(null);
		
		JLabel lblTitulo = new JLabel("Registro de Restaurantes");
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
		cmbCocina.setBounds(517, 64, 126, 22);
		cmbCocina.setModel(new DefaultComboBoxModel<String>(Restaurante.COCINA));
		add(cmbCocina);
		
		JLabel lblRegion = new JLabel("Región:");
		lblRegion.setBounds(37, 134, 71, 14);
		add(lblRegion);
		
		cmbRegion = new JComboBox<String>();
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
		
		btnGuardar = new JButton(BTN_GUARDAR);
		btnGuardar.setBounds(100, 470, 143, 23);
		add(btnGuardar);
		
		btnLimpiar = new JButton(BTN_LIMPIAR);
		btnLimpiar.setBounds(439, 470, 144, 23);
		add(btnLimpiar);
	}
	
	

	public void setControlaador(ControladorRestaurantes controlador) {
		btnGuardar.addActionListener(controlador);
		btnLimpiar.addActionListener(controlador);
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


	public Restaurante obtenerDatos() {
		Restaurante  nuevoRes = null;
		
		String nombre = txtNombre.getText().trim();
		if (nombre.isEmpty()) {
			mostrarError("El nombre no puede estar vacío");
		} else {
			String ciudad = txtCiudad.getText().trim();
			if (ciudad.isEmpty()) {
				mostrarError("La ciudad no puede estar vacía");
			} else {
				try {
					double precioMin = Double.valueOf(txtPrecioMin.getText());
					double precioMax = Double.valueOf(txtPrecioMax.getText());
					
					if (precioMin > precioMax) {
						mostrarError("El precio mínimo no puede ser superior al máximo");
					} else {
						String cocina = (String) cmbCocina.getSelectedItem();
						String region = (String) cmbRegion.getSelectedItem();
						String direccion = txtDireccion.getText();
						int distincion = (int) spnEstrellas.getValue();
						String telefono = txtTelefono.getText();
						String web = txtWeb.getText();
						
						nuevoRes  = new Restaurante(0, nombre, region, ciudad, distincion, direccion, precioMin, precioMax, cocina, telefono, web);
					}
				} catch (NumberFormatException e) {
					mostrarError("El precio mínimo y máximo deben ser valores numéricos");
				}
			}
		}
		
		return nuevoRes;
	}


	public void mostrarError(String error) {
		JOptionPane.showMessageDialog(this, error, "Error de datos", JOptionPane.ERROR_MESSAGE);
	}
	
	
	public void cargarCombo(ArrayList<String> listaRegiones) {
		dcbmRegiones.removeAllElements();
		dcbmRegiones.addAll(listaRegiones);
	}
	
	
}
