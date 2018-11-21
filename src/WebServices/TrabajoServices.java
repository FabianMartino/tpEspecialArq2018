package WebServices;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.tpEspecialArq2018.LugarDeTrabajo;
import com.tpEspecialArq2018.LugarDeTrabajoDAO;
import com.tpEspecialArq2018.Trabajo;
import com.tpEspecialArq2018.TrabajoDAO;
import com.tpEspecialArq2018.Usuario;
import com.tpEspecialArq2018.UsuarioDAO;

import WebServices.UserServices.RecursoDuplicado;

@Path("/trabajos")
public class TrabajoServices {

	@POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	public Response createTrabajo(Trabajo tr) {
		Trabajo result= TrabajoDAO.getInstance().persist(tr);
		if(result==null) {
			throw new RecursoDuplicado(tr.getId());
		}else {
			return Response.status(201).entity(tr).build();
		}		
	}
	
	@GET
	@Path("/{id}")
	@Produces (MediaType.APPLICATION_JSON) 
	public Trabajo getTrabajo(@PathParam("id") String id) {
		System.out.println("entro a get trabajo: ");
		Long idTrab = Long.parseLong(id);	    
		Trabajo tr = TrabajoDAO.getInstance().findById(idTrab); 
		return tr;
	}
	public class RecursoDuplicado extends WebApplicationException {
	     public RecursoDuplicado(long id) {
	         super(Response.status(Response.Status.CONFLICT)
	             .entity("El recurso con ID "+id+" ya existe").type(MediaType.TEXT_PLAIN).build());
	     }
	}
	
	public class RecursoNoExiste extends WebApplicationException {
	     public RecursoNoExiste(long id) {
	         super(Response.status(Response.Status.NOT_FOUND)
	             .entity("El recurso con id "+id+" no fue encontrado").type(MediaType.TEXT_PLAIN).build());
	     }
	}
}
