package dam.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import dam.db.persistencia.RestaurantePersistencia;
import dam.model.Restaurante;
import dam.view.PConsultarRestaurante;
import dam.view.PModificacionRestaurante;
import dam.view.PRegistroRestaurante;
import dam.view.VInicioGuiaMichelin;

public class ControladorRestaurantes implements ActionListener {

	VInicioGuiaMichelin vInicio;
	PConsultarRestaurante pConsultaRes;
	PRegistroRestaurante pRegistroRes;
	PModificacionRestaurante pModRes;
	RestaurantePersistencia rp;
	
	
	
	public ControladorRestaurantes(VInicioGuiaMichelin vInicio, PConsultarRestaurante pConsultaRes,
			PRegistroRestaurante pRegistroRes, PModificacionRestaurante pModRes) {
		this.vInicio = vInicio;
		this.pConsultaRes = pConsultaRes;
		this.pRegistroRes = pRegistroRes;
		this.pModRes = pModRes;
		this.rp = new RestaurantePersistencia();
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JMenuItem) {
			if (e.getActionCommand().equals(VInicioGuiaMichelin.MNIM_CONSULTAR)) {
				ArrayList<String> listaRegiones = new ArrayList<String>();
				listaRegiones = rp.seleccionarRegiones();
				pConsultaRes.cargarCombo(listaRegiones);
				vInicio.cargarPanel(pConsultaRes);
				pConsultaRes.hacerTabVisi(false);
			} else if (e.getActionCommand().equals(VInicioGuiaMichelin.MNIM_REGISTRO)) {
				vInicio.cargarPanel(pRegistroRes);
			} else if (e.getActionCommand().equals(VInicioGuiaMichelin.MNIM_MODIFICACION)) {
				ArrayList<String> listaRegiones = new ArrayList<String>();
				listaRegiones = rp.seleccionarRegiones();
				pModRes.cargarCombo(listaRegiones);
				vInicio.cargarPanel(pModRes);
			} else if (e.getActionCommand().equals(VInicioGuiaMichelin.MNIM_SALIR)) {
				int resp = JOptionPane.showConfirmDialog(vInicio, "Se va a cerrar la aplicación, ¿desea continuar?",
						"Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if (resp == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
			
		}   else if (e.getSource() instanceof JButton) {
			if (e.getActionCommand().equals(PConsultarRestaurante.BTN_CONSULTAR)) {
				consultaRestaurantes();

			} else if (e.getActionCommand().equals(PConsultarRestaurante.BTN_ELIMINAR)) {
				eliminarRestaurante();
				
			} else if (e.getActionCommand().equals(PRegistroRestaurante.BTN_GUARDAR)) {
				registrarNuevoRestaurante();
				
			} else if (e.getActionCommand().equals(PRegistroRestaurante.BTN_LIMPIAR)) {
				pRegistroRes.limpiarComponentes();
				
			} else if (e.getActionCommand().equals(PModificacionRestaurante.BTN_BUSCAR)) {
				buscarRestauranteMod(); 
				
			} else if (e.getActionCommand().equals(PModificacionRestaurante.BTN_GUARDAR_MOD)) {
				modificarRestaurante();
				
			} else if (e.getActionCommand().equals(PModificacionRestaurante.BTN_CANCELAR)) {
				pModRes.limpiarComponentes();
				pModRes.hacerVisibleMod(false);
			}
		}
	}



	private void modificarRestaurante() {
		Restaurante restMod = pModRes.comrpobarDatosMod();
		if (restMod  == null) {
			pModRes.mostrarError("No han podido guardarse los cambios");
		} else {
			int resp = rp.modRestaurante(restMod);
			
			if (resp == 1) {
				JOptionPane.showMessageDialog(pModRes, "Se ha modificado el restaurante con éxito", "Información", JOptionPane.INFORMATION_MESSAGE);
				pModRes.limpiarComponentes();
			} else {
				pModRes.mostrarError("No han podido guardarse los cambios");
			}
		}
	}



	private void buscarRestauranteMod() {
		String nomRes = pModRes.obtenerNombre();
		if (nomRes.trim().isEmpty()) {
			JOptionPane.showMessageDialog(pModRes, "Debe introducir un nombre", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			String cocinaRes = rp.consultarResNom(nomRes);
			if (cocinaRes == null) {
				pModRes.mostrarError("No se ha encontrado ningún restaurante con el nombre introducido");
			} else {
				Restaurante restaurante = rp.selecionarUnRestaurante(nomRes);
				pModRes.rellenarDatos(restaurante);
				pModRes.hacerVisibleMod(true);
			}
		}
	}



	private void registrarNuevoRestaurante() {
		Restaurante nuevoRes = pRegistroRes.obtenerDatos();
		if (nuevoRes != null) {
			String cocinaRes = rp.consultarResNom(nuevoRes.getNombre());
			if (cocinaRes != null) {
				pRegistroRes.mostrarError("Ya existe un restaurante con ese nombre");
			} else {
				int resp = rp.registrarRestaurante(nuevoRes);
				
				if (resp == 1) {
					JOptionPane.showMessageDialog(pRegistroRes, "Se ha registrado el restaurante", "Información", JOptionPane.INFORMATION_MESSAGE);
					pRegistroRes.limpiarComponentes();
				} else {
					pRegistroRes.mostrarError("No se ha podido añadir el restaurante");
				}
			}
		}
	}



	private void eliminarRestaurante() {
		String restauranteEliminar = pConsultaRes.restauranteEliminar();
		if (restauranteEliminar == null) {
			JOptionPane.showMessageDialog(pConsultaRes, "No se ha seleccionado ningún restaurante", "Error selección", JOptionPane.ERROR_MESSAGE);
		} else {
			int resp = JOptionPane.showConfirmDialog(pConsultaRes, "Se va a eliminar el restaurante, ¿desea continuar?",
					"Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if (resp == JOptionPane.YES_OPTION) {
				int res = rp.eliminarRestaurante(restauranteEliminar);
				consultaRestaurantes();
				if (res==1) {
					JOptionPane.showMessageDialog(pConsultaRes, "Se ha eliminado el restaurante", "Información", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(pConsultaRes, "No se ha podido eliminar el restaurante", "Error eliminación", JOptionPane.ERROR_MESSAGE);
				}						
			}
		}
	}



	private void consultaRestaurantes() {
		String region = pConsultaRes.filtroRegion();
		String distincion = pConsultaRes.filtroDistincion();
		ArrayList<Restaurante> listaRestaurantes = new ArrayList<Restaurante>();
		if (region.equals(PConsultarRestaurante.TODOS_ELEMENTOS) && distincion.equals(PConsultarRestaurante.TODOS_ELEMENTOS)) {
			listaRestaurantes = rp.seleccionarRestaurantes();
			pConsultaRes.rellenarTabla(listaRestaurantes);
			pConsultaRes.hacerTabVisi(true);
		} else {
			listaRestaurantes = rp.filtrarRestaurante(region, distincion);
			pConsultaRes.rellenarTabla(listaRestaurantes);
			pConsultaRes.hacerTabVisi(true);
			if (listaRestaurantes.isEmpty()) {
				JOptionPane.showMessageDialog(pConsultaRes, "No hay  datos para ese filtro", "No hay datos", JOptionPane.ERROR_MESSAGE);
				pConsultaRes.hacerTabVisi(false);
			}
		}
	}

	
}
