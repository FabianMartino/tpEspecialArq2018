package WebServices;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.tpEspecialArq2018.Usuario;
import com.tpEspecialArq2018.UsuarioDAO;

@Path("/users")
public class UserServices {

	@GET @Path("/{id}") @Produces (MediaType.APPLICATION_JSON)
	public Usuario getUserData(@PathParam("id") String id) {
		Long idUser = Long.parseLong(id);	    
		return UsuarioDAO.getInstance().findById(idUser);
	}
	
}
