package dam.main;

import java.awt.EventQueue;

import dam.control.ControladorRestaurantes;
import dam.view.PConsultarRestaurante;
import dam.view.PModificacionRestaurante;
import dam.view.PRegistroRestaurante;
import dam.view.VInicioGuiaMichelin;

public class InicioGuiaMichelin {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				VInicioGuiaMichelin vInicio = new VInicioGuiaMichelin();
				PConsultarRestaurante pConsulta = new PConsultarRestaurante();
				PRegistroRestaurante pRegistro = new PRegistroRestaurante();
				PModificacionRestaurante pMod = new PModificacionRestaurante();
				
				
				ControladorRestaurantes controlador = new ControladorRestaurantes(vInicio, pConsulta, pRegistro, pMod);
				
				vInicio.setControlador(controlador);
				pConsulta.setControlaador(controlador);
				pRegistro.setControlaador(controlador);
				pMod.setControlaador(controlador);
				
				
				vInicio.hacerVisible();
			}
		});
	}

}
