package WebServices;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.tpEspecialArq2018.Trabajo;
import com.tpEspecialArq2018.TrabajoDAO;

@Path("/trabajos")
public class TrabajoServices {

	@POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	public void createTrabajo() {
		System.out.println("entro a create trabajo: ");
		Trabajo tr =  new Trabajo("Titulo", "categoria");
		TrabajoDAO.getInstance().persist(tr);
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

}
