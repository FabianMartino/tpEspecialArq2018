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
import javax.ws.rs.core.MediaType;

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
	public void createUser(Usuario user) {
//			@PathParam("nombre") String pNombre, 
//			@PathParam("apellido") String pApellido, 
//			@PathParam("lugarTrab") LugarDeTrabajo lugarT) 
//		Usuario user = new Usuario(pNombre, pApellido, lugarT);
		UsuarioDAO.getInstance().persist(user);
	}
	
//	public Response createUsuario(Usuario usuario) {
//		Usuario result= UsuarioDAO.getInstance().persist(usuario);
//		if(result==null) {
//			throw new RecursoDuplicado(usuario.getId());
//		}else {
//			return Response.status(201).entity(usuario).build();
//		}
//	}
	
	
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
	
	@GET
	@Path("evaluaciones/{idUser}")
	@Produces (MediaType.APPLICATION_JSON) 
	public List<Trabajo> getTrabajosAutor(@PathParam("idUser") String idU){
		System.out.println("Dado un autor, retornar todos los trabajos de investigación enviados");
		Long idUser = Long.parseLong(idU);	    
		List<Trabajo> trabajos = UsuarioDAO.getInstance().getTrabajos(idUser);
		return trabajos;
	}

	@GET
	@Path("trabajo/{idAutor}/{idEvaluador}/idPalabra")
	@Produces (MediaType.APPLICATION_JSON) 
	public List<Trabajo> getTrabajosAutorRevisor(
			@PathParam("idAutor") String idAutor, 
			@PathParam("idEvaluador") String idEvaluador,
			@PathParam("idPalabra") String idPalabra) {
		System.out.println("Seleccionar trabajos de investigación de un autor y revisor en una determinada área de investigación.");
		Long idUsuario = Long.parseLong(idAutor);	    
		Long idEv = Long.parseLong(idEvaluador);	    
		Long idP = Long.parseLong(idPalabra);	    
		
		List<Trabajo> trabajos = UsuarioDAO.getInstance().findAllTrabajosAutorRevisorPalabra(idUsuario, idEv, idP); 
		return trabajos;
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void updateUsuario(@PathParam("id") Long id, Usuario usuario) {
		Usuario result= UsuarioDAO.getInstance().update(id, usuario);
	}
}
	

