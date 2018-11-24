package utils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.http.client.ClientProtocolException;

import com.tpEspecialArq2018.Evaluacion;
import com.tpEspecialArq2018.EvaluacionDAO;
import com.tpEspecialArq2018.LugarDeTrabajo;
import com.tpEspecialArq2018.LugarDeTrabajoDAO;
import com.tpEspecialArq2018.Palabra;
import com.tpEspecialArq2018.PalabraDAO;
import com.tpEspecialArq2018.Rol;
import com.tpEspecialArq2018.RolDAO;
import com.tpEspecialArq2018.Trabajo;
import com.tpEspecialArq2018.TrabajoDAO;
import com.tpEspecialArq2018.Usuario;
import com.tpEspecialArq2018.UsuarioDAO;

public class DBcreator {
	private static DBcreator dbCreator;
	private boolean dbCreated;
	private ArrayList<LugarDeTrabajo> lugaresDeTrabajo = new ArrayList<LugarDeTrabajo>();
	private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	private ArrayList<Palabra> palabras = new ArrayList<Palabra>();
	private ArrayList<Evaluacion> evaluaciones = new ArrayList<Evaluacion>();
	private ArrayList<Trabajo> trabajos = new ArrayList<Trabajo>();
	private	ArrayList<Rol> roles = new ArrayList<Rol>();
	
	private DBcreator() {
		this.dbCreated = false;
	}
	
	public static DBcreator getDBcreator() {
		if (dbCreator==null) {
			dbCreator = new DBcreator();
		}
		return dbCreator;
	}
	
	public void create() throws ClientProtocolException, IOException, ParseException {
		if (!dbCreated) {
			crearRoles();
			crearLugaresDeTrabajos();
			crearPalabras();
			crearTrabajos();
			crearUsuarios();
			crearEvaluaciones();
			dbCreated = true;
		}
	}
	
	private void crearTrabajos() throws ClientProtocolException, IOException {
		ArrayList<String[]> trabajos = CSVReader.read("src/datasets/trabajos.csv");
		for (String[] trabajo : trabajos) {
			Trabajo t = new Trabajo(trabajo[0], trabajo[1]);
			t.addPalabra(this.palabras.get(0));
			this.trabajos.add(t);
			t = TrabajoDAO.getInstance().persist(t);
		}
	}
	
	private void crearLugaresDeTrabajos() throws ClientProtocolException, IOException {
		ArrayList<String[]> trabajos = CSVReader.read("src/datasets/lugaresDeTrabajo.csv");
		for (String[] trabajo : trabajos) {
			LugarDeTrabajo lugar = new LugarDeTrabajo(trabajo[0]);
			lugaresDeTrabajo.add(lugar);
			lugar = LugarDeTrabajoDAO.getInstance().persist(lugar);
		}
	}	

	private void crearUsuarios() throws ClientProtocolException, IOException {
		
		ArrayList<String[]> usuarios = CSVReader.read("src/datasets/users.csv");
		int index = 0;
		for (String[] usuario : usuarios) {
			Usuario u = new Usuario(usuario[0], usuario[1], lugaresDeTrabajo.get(index++));
			u.addRol(this.roles.get(1/index));
			u.addTrabajo(this.trabajos.get(index-1));
			u.addPalabra(this.palabras.get(0));
			this.usuarios.add(u);
			UsuarioDAO.getInstance().persist(u);
		}
	}

	private void crearEvaluaciones() throws ClientProtocolException, IOException, ParseException {
		ArrayList<String[]> evaluaciones = CSVReader.read("src/datasets/evaluaciones.csv");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int i = 0;
		for (String[] evaluacion : evaluaciones) {
			Date date = sdf.parse(evaluacion[0]);
			Evaluacion e = new Evaluacion(usuarios.get(i),trabajos.get(i), date,Integer.parseInt(evaluacion[1]));
			this.evaluaciones.add(e);
			e = EvaluacionDAO.getInstance().persist(e);
			i++;
		}
	}
	
	private void crearRoles(){
		ArrayList<String[]> rolesRead = CSVReader.read("src/datasets/roles.csv");
		for (String[] rol : rolesRead) {
			Rol r = new Rol(rol[0]);
			this.roles.add(r);
			RolDAO.getInstance().persist(r);
		}
	}
	
	private void crearPalabras() throws ClientProtocolException, IOException {
		Palabra p1 = new Palabra("Palabra1", true);
		p1 = PalabraDAO.getInstance().persist(p1);
		this.palabras.add(p1);
		Palabra p2 = new Palabra("Palabra2", false);
		p2 = PalabraDAO.getInstance().persist(p2);
		this.palabras.add(p2);
		Palabra p3 = new Palabra("Palabra3", false);
		p3 = PalabraDAO.getInstance().persist(p3);
		this.palabras.add(p3);
		Palabra p4 = new Palabra("Palabra4", true);
		p4 = PalabraDAO.getInstance().persist(p4);		
		this.palabras.add(p4);
	}
	
}
