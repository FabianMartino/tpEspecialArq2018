package WebServices;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.tpEspecialArq2018.Trabajo;
import com.tpEspecialArq2018.TrabajoDAO;

@Path("/trabajo")
public class TrabajoServices {

	@POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	public void createTrabajo(@PathParam("titulo") String pTitulo, @PathParam("categoria") String pCategoria ) {
		Trabajo tr =  new Trabajo(pTitulo, pCategoria);
		TrabajoDAO.getInstance().persist(tr);
	}
	
	@GET
	@Path("/{id}")
	@Produces (MediaType.APPLICATION_JSON) 
	public Trabajo getTrabajo(@PathParam("id") String id) {
		Long idTrab = Long.parseLong(id);	    
		Trabajo tr = TrabajoDAO.getInstance().findById(idTrab); 
		return tr;
	}
	
	@GET
	@Path("/todosTrabajos")
	@Produces (MediaType.APPLICATION_JSON) 
	public List<Trabajo> getTrabajos() {
		List<Trabajo> tr = TrabajoDAO.getInstance().findAll(); 
		return tr;
	}
	
	
}
