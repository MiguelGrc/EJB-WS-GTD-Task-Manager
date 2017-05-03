package menu.options;

import java.util.List;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotAuthorizedException;

import alb.util.menu.Action;

import com.sdi.client.Category;
import com.sdi.client.RestServiceFactory;

public class ListarCategoriasAction implements Action {

	@Override
	public void execute() throws Exception {
		
		List<Category> catList = null;
		try{
			catList = RestServiceFactory.getClient().findCategoriesByUserId();
		} catch (NotAuthorizedException | InternalServerErrorException e){
			System.err.println("[ERROR] " + e.getMessage().split("\n")[0]);
			return;
		}
		
		printHeader();
		printCategories(catList);
		
		System.out.print("------------------------------------------------------");
		
	}

	private void printHeader() {
		System.out.printf("%s %s\n",
				"_ID____",
				"_NOMBRE_____________"
			);
	}

	private void printCategories(List<Category> catList) {
		for(Category cat : catList){
			System.out.printf("%-7d %-20s\n",
					cat.getId(),
					cat.getName()
				);
		}
	}
		
	
}
