package menu;

import menu.options.DeshabilitarUsuarioAction;
import menu.options.EliminarUsuariosAction;
import menu.options.ListarUsuariosAction;
import alb.util.menu.BaseMenu;

public class Menu extends BaseMenu {
	
	public Menu() {
		menuOptions = new Object[][] { 
			{ "Listar usuarios del sistema",    ListarUsuariosAction.class },
			{ "Deshabilitar usuario", 			DeshabilitarUsuarioAction.class }, 
			{ "Eliminar usuario", 			    EliminarUsuariosAction.class },
		};
	}

	
	public static void main(String[] args) {
		new Menu().execute();
	}
}
