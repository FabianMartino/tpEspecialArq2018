package WebServices;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.tpEspecialArq2018.LugarDeTrabajo;
import com.tpEspecialArq2018.LugarDeTrabajoDAO;
import com.tpEspecialArq2018.Usuario;
import com.tpEspecialArq2018.UsuarioDAO;

@Path("/users")
public class UserServices {

	@GET
	@Path("/{id}")
	@Produces (MediaType.APPLICATION_JSON) 
	public String getUserData(@PathParam("id") String id) {
		Long idUser = Long.parseLong(id);	    
		String user = UsuarioDAO.getInstance().getUserData(idUser); 
		return user;
	}
	
	@POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	public void createUser() {
		System.out.println("entro a create user: ");
		LugarDeTrabajo lugar =  new LugarDeTrabajo("lugar");
		Usuario user = new Usuario("juan","perez", lugar);
		LugarDeTrabajoDAO.getInstance().persist(lugar);
		UsuarioDAO.getInstance().persist(user);
	}
	
	
	

//	@GET
//	@Path("/{id}")
//	@Produces (MediaType.APPLICATION_JSON) 
//	public Usuario getTodosLosTrabajos(@PathParam("id") String id) {
//		System.out.println("Dado un revisor, retornar todos sus trabajos asignados");
//		Long idUser = Long.parseLong(id);	    
//		Usuario user = UsuarioDAO.getInstance().findById(idUser); 
//		//UsuarioDAO.getInstance().getTrabajos(idUser, categoria)
//		return user;
//	}
	
	
}
