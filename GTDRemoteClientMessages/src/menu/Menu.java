package menu;

import menu.options.AnadirTareaAction;
import menu.options.MarcarTerminadaAction;
import menu.options.listarTareasHoyAtrasadasAction;
import alb.util.menu.BaseMenu;

public class Menu extends BaseMenu {
	
	
	
	public Menu() {
		menuOptions = new Object[][] { 
			{ "Listar tareas hoy y retrasadas",    		listarTareasHoyAtrasadasAction.class },
			{ "Marcar tarea como terminada", 			MarcarTerminadaAction.class }, 
			{ "Añadir una nueva tarea", 			    AnadirTareaAction.class },
		};
	}

	
	public static void main(String[] args) {
		ClientInfo.saveClientInformation();
		new Menu().execute();
	}
	
	

}
