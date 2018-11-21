package WebServices;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.tpEspecialArq2018.Evaluacion;
import com.tpEspecialArq2018.EvaluacionDAO;
import com.tpEspecialArq2018.Trabajo;
import com.tpEspecialArq2018.Usuario;

@Path("/evaluacion")
public class EvaluacionServices {
	
	@POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	public void createEvaluacion(@PathParam("idUser") Usuario idUser, @PathParam("idTrabajo") Trabajo idTrab, @PathParam("date") Date fecha, @PathParam("nota") int nota) {
		Evaluacion user = new Evaluacion(idUser, idTrab, fecha, nota);
		EvaluacionDAO.getInstance().persist(user);
	}
	
	@GET
	@Path("/{id}")
	@Produces (MediaType.APPLICATION_JSON) 
	public Evaluacion getEvaluacion(@PathParam("id") String id) {
		Long idEvaluacion = Long.parseLong(id);	    
		Evaluacion evaluacion = EvaluacionDAO.getInstance().findById(idEvaluacion); 
		return evaluacion;
	}
	
	@GET
	@Path("/todasEvaluaciones")
	@Produces (MediaType.APPLICATION_JSON) 
	//Consultar trabajos de investigación y sus propiedades.
	public List<Evaluacion> getEvaluaciones() {
		List<Evaluacion> evaluaciones = EvaluacionDAO.getInstance().findAll(); 
		return evaluaciones;
	}
}
