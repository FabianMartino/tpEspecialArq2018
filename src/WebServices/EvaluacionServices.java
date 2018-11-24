package WebServices;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.tpEspecialArq2018.Evaluacion;
import com.tpEspecialArq2018.EvaluacionDAO;
import com.tpEspecialArq2018.LugarDeTrabajo;
import com.tpEspecialArq2018.LugarDeTrabajoDAO;
import com.tpEspecialArq2018.Trabajo;
import com.tpEspecialArq2018.TrabajoDAO;
import com.tpEspecialArq2018.Usuario;
import com.tpEspecialArq2018.UsuarioDAO;

import WebServices.UserServices.RecursoDuplicado;

@Path("/evaluaciones")
public class EvaluacionServices {

	@POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	public Response createEvaluacion(Evaluacion e) {
		Evaluacion result= EvaluacionDAO.getInstance().persist(e);
		if(result==null) {
			throw new RecursoDuplicado(e.getId());
		}else {
			return Response.status(201).entity(e).build();
		}		
	}
	
	@GET
	@Path("/{id}")
	@Produces (MediaType.APPLICATION_JSON) 
	public Evaluacion getEvaluacion(@PathParam("id") String id) {
		Long idEva = Long.parseLong(id);	    
		Evaluacion e = EvaluacionDAO.getInstance().findById(idEva); 
		return e;
	}
	
	private Date formatDateFromString(String fecha) throws ParseException  {
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.parse(fecha);
	}
	
	@POST
	@Path("/asignar/{idUser}/{idTrab}/{fecha}/{nota}")
	@Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	public Response asignarEvaluacion(@PathParam("idUser") long idUser,@PathParam("idTrab") long idTrab,@PathParam("fecha") String fecha,@PathParam("nota") int nota) throws ParseException {
		Trabajo t = TrabajoDAO.getInstance().findById(idTrab);
		Usuario u = UsuarioDAO.getInstance().findById(idUser);
		List<Evaluacion> evaTrabajo = TrabajoDAO.getInstance().EvaluacionesTrabajo(idTrab);
		List<Evaluacion> evaUsuario = UsuarioDAO.getInstance().EvaluacionesRevisor(idUser);
		List<Usuario> autores = TrabajoDAO.getInstance().autores(idTrab);
		boolean condicionAutor = false;
		for (int i = 0; i < autores.size(); i++) {
			if(autores.get(i).getId_user()==u.getId_user()) {
				condicionAutor = true;
			}
		}
		if(evaTrabajo.size()<3 && evaUsuario.size()<3 && !condicionAutor ) {
			Date f = formatDateFromString(fecha);
			Evaluacion eva = new Evaluacion(u, t, f, nota);
			Evaluacion result= EvaluacionDAO.getInstance().persist(eva);
			if(result==null) {
				throw new RecursoDuplicado(eva.getId());
			}else {
				return Response.status(201).entity(eva).build();
			}
		}else {
			throw new RecursoErroneo();
		}	
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
	
	public class RecursoErroneo extends WebApplicationException {
	     public RecursoErroneo() {
	         super(Response.status(Response.Status.NOT_FOUND)
	             .entity("Los elemntos ingresados tienen un problema para ser ingresados").type(MediaType.TEXT_PLAIN).build());
	     }
	}
}