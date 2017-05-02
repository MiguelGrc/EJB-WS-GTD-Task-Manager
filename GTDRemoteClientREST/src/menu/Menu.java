package menu;

import menu.options.CrearTareaAction;
import menu.options.ListarCategoriasAction;
import menu.options.ListarTareasRetrasadasCategoriaAction;
import menu.options.MarcarTareaComoFinalizadaAction;
import alb.util.menu.BaseMenu;

import com.sdi.client.ClientInfo;

public class Menu extends BaseMenu {
	
	public Menu() {
		menuOptions = new Object[][] { 
			{ "Listar categorías del usuario",    ListarCategoriasAction.class },
			{ "Listar tareas retrasadas de una categoría",    ListarTareasRetrasadasCategoriaAction.class },
			{ "Marcar tarea como finalizada",    MarcarTareaComoFinalizadaAction.class },
			{ "Crear tarea",    				CrearTareaAction.class },
		};
	}

	
	public static void main(String[] args) {
		ClientInfo.saveClientInformation();
		new Menu().execute();
	}
}
