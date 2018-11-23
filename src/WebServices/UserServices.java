package WebServices;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import com.tpEspecialArq2018.Evaluacion;
import com.tpEspecialArq2018.EvaluacionDAO;
import com.tpEspecialArq2018.LugarDeTrabajo;
import com.tpEspecialArq2018.LugarDeTrabajoDAO;
import com.tpEspecialArq2018.Trabajo;
import com.tpEspecialArq2018.Usuario;
import com.tpEspecialArq2018.UsuarioDAO;

@Path("/users")
public class UserServices {
	
	@POST @Consumes(MediaType.APPLICATION_JSON) @Produces(MediaType.APPLICATION_JSON)
	public Response createUser(Usuario user) {

		LugarDeTrabajo l = new LugarDeTrabajo("campo");
		LugarDeTrabajoDAO.getInstance().persist(l);
		user.setLocacion(l);
		Usuario result= UsuarioDAO.getInstance().persist(user);
		if(result==null) {
			throw new RecursoDuplicado(user.getId_user());
		}else {
			return Response.status(201).entity(user).build();
		}
	}
	
	
	@GET
	@Path("/{id}")
	@Produces (MediaType.APPLICATION_JSON) 
	public Usuario getUserData(@PathParam("id") String id) {
		Long idUser = Long.parseLong(id);	    
		Usuario user = UsuarioDAO.getInstance().findById(idUser); 
		return user;
	}
	
	@GET
	@Path("trabajo/{idUser}")
	@Produces (MediaType.APPLICATION_JSON) 
	public List<Evaluacion> trabajosAsignados(@PathParam("idUser") String idUser) {
		System.out.println("Dado un revisor, retornar todos sus trabajos asignados");
		Long idUsuario = Long.parseLong(idUser);	    
		List<Evaluacion> evaluaciones = EvaluacionDAO.getInstance().findEvaluador(idUsuario);
		return evaluaciones;
	}
	
	@GET
	@Path("evaluacion/{id}/{fechaInicio}/{fechaFin}")
	@Produces (MediaType.APPLICATION_JSON) 
	public List<Evaluacion> fechaEvaluados(
			@PathParam("id") String id,
			@PathParam("fechaInicio") String paramInicio, 
			@PathParam("fechaFin") String paramFin) throws ParseException {
		System.out.println("Dado un revisor y un rango de fechas, retornar todas sus revisiones");
		Long idUser = Long.parseLong(id);	    
		Calendar fechaInicio = Calendar.getInstance();
		Calendar fechaFin = Calendar.getInstance();
		fechaInicio.setTime(this.formatDateFromString(paramInicio));
		fechaFin.setTime(this.formatDateFromString(paramFin));
		
		List<Evaluacion> list = EvaluacionDAO.getInstance().findEntreFechas(fechaInicio.toString(), fechaFin.toString(), idUser);
		return list;
	}

	private Date formatDateFromString(String fecha) throws ParseException  {
			String pattern = "dd-MM-yyyy";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			return simpleDateFormat.parse(fecha);
    }
	
//	@GET
//	@Path("evaluaciones/{idUser}")
//	@Produces (MediaType.APPLICATION_JSON) 
//	public List<Trabajo> getTrabajosAutor(@PathParam("idUser") String idU){
//		System.out.println("Dado un autor, retornar todos los trabajos de investigación enviados");
//		Long idUser = Long.parseLong(idU);	    
//		List<Trabajo> trabajos = UsuarioDAO.getInstance().getTrabajos(idUser);
//		return trabajos;
//	}

////	@GET
////	@Path("trabajo/{idAutor}/{idEvaluador}/idPalabra")
////	@Produces (MediaType.APPLICATION_JSON) 
////	public List<Trabajo> getTrabajosAutorRevisor(
////			@PathParam("idAutor") String idAutor, 
////			@PathParam("idEvaluador") String idEvaluador,
////			@PathParam("idPalabra") String idPalabra) {
////		System.out.println("Seleccionar trabajos de investigación de un autor y revisor en una determinada área de investigación.");
////		Long idUsuario = Long.parseLong(idAutor);	    
////		Long idEv = Long.parseLong(idEvaluador);	    
////		Long idP = Long.parseLong(idPalabra);	    
////		
//		List<Trabajo> trabajos = UsuarioDAO.getInstance().findAllTrabajosAutorRevisorPalabra(idUsuario, idEv, idP); 
//		return trabajos;
//	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUsuario(@PathParam("id") Long id, Usuario user) {
		Usuario result = UsuarioDAO.getInstance().findById(id);
		if(result!=null) {
			UsuarioDAO.getInstance().update(id, user);
			return Response.status(201).entity(user).build();
		}else {
			throw new RecursoNoExiste(id);
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
	@GET
	@Path("/trabajos/{id}")
	@Produces (MediaType.APPLICATION_JSON) 
	public List<Trabajo> getTrabajosAutor(@PathParam("id") String id) {
		Long idUser = Long.parseLong(id);	    
		List<Trabajo> trab = UsuarioDAO.getInstance().getTrabajos(idUser); 
		return trab;
	}
	@GET
	@Path("/trabajos/{id}/{desde}/{hasta}")
	@Produces (MediaType.APPLICATION_JSON) 
	public List<Trabajo> getTrabajosAutorFecha(@PathParam("id") String id,@PathParam("desde") String desde,@PathParam("hasta") String hasta) {
		Long idUser = Long.parseLong(id);	    
		List<Trabajo> trab = UsuarioDAO.getInstance().findAllTrabajosAutorRevisorFecha(idUser, desde, hasta); 
		return trab;
	}
}