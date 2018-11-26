package persistencia;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import com.toedter.calendar.JDateChooser;
import java.util.Date;
import beans.Entidad;
import beans.Propiedad;
import modelo.ListaVideos;
import modelo.Usuario;
import modelo.Video;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorUsuarioTDS implements IAdaptadorUsuarioDAO {

	private static ServicioPersistencia servPersistencia;
	private static AdaptadorUsuarioTDS unicaInstancia = null;
	private SimpleDateFormat dateFormat;
	public static AdaptadorUsuarioTDS getUnicaInstancia() {
		if (unicaInstancia == null)
			return new AdaptadorUsuarioTDS();
		return unicaInstancia;
	}

	private AdaptadorUsuarioTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
		dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	}

	@Override
	public int registrarUsuario(Usuario usuario) {
		Entidad eUsuario;
		boolean existe = true;
		try {
			eUsuario = servPersistencia.recuperarEntidad(usuario.getCodigo());
		} catch (NullPointerException e) {
			existe = false;
		}

		if (existe)
			return -1;
		// registramos los videos de canciones recientes
		AdaptadorVideoTDS adaptadorVideo = AdaptadorVideoTDS.getUnicaInstancia();
		LinkedList<Video> recienteVideos = usuario.getListaReciente();
		for (Video vi : recienteVideos) {
			adaptadorVideo.registrarVideo(vi);
		}
		// registramos las listas de video
		AdaptadorListaVideosTDS adaptadorListaVideo = AdaptadorListaVideosTDS.getUnicaInstancia();
		for (ListaVideos listaVi : usuario.getListasVideos()) {
			adaptadorListaVideo.registrarListaVideos(listaVi);
		}
		eUsuario = new Entidad();
		eUsuario.setNombre("usuario");
		eUsuario.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(
				new Propiedad("usuario", usuario.getNombreUsuario()), new Propiedad("password", usuario.getPassword()),
				new Propiedad("FechaNacimiento",
						String.valueOf(dateFormat.format(usuario.getFechaNacimiento().getDate()))),
				new Propiedad("nombre", usuario.getNombre()), new Propiedad("apellidos", usuario.getApellidos()),
				new Propiedad("email", usuario.getEmail()),
				new Propiedad("premium", String.valueOf(usuario.isPremium())),
				new Propiedad("listasVideos", obtenerCodigosListasVideos(usuario.getListasVideos())),
				new Propiedad("listaReciente", obtenerCodigosVideosDeLista(usuario.getListaReciente()))
		// new Propiedad("filtro", "ver el filtro")
		)));

		eUsuario = servPersistencia.registrarEntidad(eUsuario);
		usuario.setCodigo(usuario.getCodigo());
		return eUsuario.getId();

	}

	@Override
	public void borrarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		Entidad entidad = servPersistencia.recuperarEntidad(usuario.getCodigo());
		servPersistencia.borrarEntidad(entidad);
	}

	public void borrarUsuario() {

		List<Entidad> entidades = servPersistencia.recuperarEntidades("usuario");
		for (Entidad entidad : entidades) {
			servPersistencia.borrarEntidad(entidad);
		}
	}

	@Override
	public void modificarUsuario(Usuario usuario) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(usuario.getCodigo());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "password");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "password", usuario.getPassword());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "email");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "email", usuario.getEmail());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "nombre");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "nombre", usuario.getNombre());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "apellidos");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "apellidos", usuario.getApellidos());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "premium");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "premium", String.valueOf(usuario.isPremium()));
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "usuario");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "usuario", usuario.getNombreUsuario());
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "fechaNaciemto");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "fechaNaciento",
				String.valueOf(dateFormat.format(usuario.getFechaNacimiento().getDate())));
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "listasVideos");
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "listaReceintes");
		servPersistencia.anadirPropiedadEntidad(eUsuario, "listaReciente",
				obtenerCodigosVideosDeLista(usuario.getListaReciente()));
		servPersistencia.eliminarPropiedadEntidad(eUsuario, "listasVideos");
		// las listas de videos de usuario
		LinkedList<ListaVideos> listaVideos = usuario.getListasVideos();
		// el string para la persistencia
		String listasVideos = obtenerCodigosListasVideos(listaVideos);

		AdaptadorListaVideosTDS adaptadorV = AdaptadorListaVideosTDS.getUnicaInstancia();
		// los añadimos
		for (ListaVideos lista : listaVideos) {
			adaptadorV.registrarListaVideos(lista);
		}
		servPersistencia.anadirPropiedadEntidad(eUsuario, "listasVideos", listasVideos);

	}

	@Override
	public Usuario recuperarUsuario(int codigo) {
		// TODO Auto-generated method stub
		// si ya esta en el pool la devuelvo directamente
		if (PoolDAO.getUnicaInstancia().contiene(codigo)) {
			return (Usuario) PoolDAO.getUnicaInstancia().getObjeto(codigo);
		}
		// si no esta la recupero de la base de datos
		Entidad eUsuario = servPersistencia.recuperarEntidad(codigo);
		String nombreUsuario = servPersistencia.recuperarPropiedadEntidad(eUsuario, "usuario");
		String password = servPersistencia.recuperarPropiedadEntidad(eUsuario, "password");
		// datos usuario
		Date fechaNacimiento = null;
		try {
			fechaNacimiento = dateFormat.parse(servPersistencia.recuperarPropiedadEntidad(eUsuario, "fechaNacimiento"));
		} catch (ParseException e) {

			e.printStackTrace();
		}
		String nombre = servPersistencia.recuperarPropiedadEntidad(eUsuario, "nombre");
		String apellidos = servPersistencia.recuperarPropiedadEntidad(eUsuario, "apellidos");
		String email = servPersistencia.recuperarPropiedadEntidad(eUsuario, "email");
		// String premium =
		// servPersistencia.recuperarPropiedadEntidad(eUsuasrio, "premium");
		boolean premium = Boolean.valueOf(servPersistencia.recuperarPropiedadEntidad(eUsuario, "premium"));
		Usuario usuario = new Usuario(nombreUsuario, password, nombre, apellidos, email,
				new JDateChooser(fechaNacimiento));
		usuario.setCodigo(codigo);
		usuario.setPremium(premium);
		// recuperar propiedades que no son objetos

		// IMPORTANTE:añadir el usuario al pool antes de llamar a otros
		// adaptadores

		PoolDAO.getUnicaInstancia().addObjeto(codigo, usuario);
		// recuperar propiedades que son objetos llamando a adaptadores
		// ventas
		List<Video> recienteVideos = new LinkedList<>();
		recienteVideos = obtenerVideosDesdeCodigos(
				servPersistencia.recuperarPropiedadEntidad(eUsuario, "recientesViddeos"));
		for (Video video : recienteVideos) {
			usuario.addRecienteVideos(video);
		}
		List<ListaVideos> listasVideos = new LinkedList<>();
		listasVideos = obtenerListasVideosDesdeCodigos(
				servPersistencia.recuperarPropiedadEntidad(eUsuario, "listasVideos"));
		for (ListaVideos listaVideos : listasVideos) {
			usuario.addListaAListaVideos(listaVideos);
		}
		return usuario;
	}

	@Override
	public List<Usuario> recuperarTodosUsuarios() {
		List<Usuario> usuarios = new LinkedList<>();
		List<Entidad> eUsuarios = servPersistencia.recuperarEntidades("usuario");
		assert (!eUsuarios.isEmpty());
		for (Entidad entidad : eUsuarios) {
			usuarios.add(recuperarUsuario(entidad.getId()));
		}

		return usuarios;
	}

	/*----------------------FUNCIONES AUXILIARES ----------------------------------------------*/
	private String obtenerCodigosListasVideos(LinkedList<ListaVideos> listasVideos) {

		String codigos = "";
		for (ListaVideos listaVideo : listasVideos) {
			codigos += listaVideo.getCodigo() + " ";
		}
		return codigos.trim();
	}

	private List<ListaVideos> obtenerListasVideosDesdeCodigos(String recuperarPropiedadEntidad) {

		LinkedList<ListaVideos> listaVideos = new LinkedList<>();
		StringTokenizer tokens = new StringTokenizer(recuperarPropiedadEntidad, " ");
		AdaptadorListaVideosTDS adaptadorLV = AdaptadorListaVideosTDS.getUnicaInstancia();
		while (tokens.hasMoreTokens()) {
			listaVideos.add(adaptadorLV.recuperarListaVideos(Integer.valueOf(tokens.nextToken())));
		}

		return listaVideos;
	}

	private String obtenerCodigosVideosDeLista(LinkedList<Video> listaReciente) {
		String codigo = "";
		for (Video video : listaReciente) {
			codigo += video.getCodigo() + " ";
		}
		return codigo;
	}

	private List<Video> obtenerVideosDesdeCodigos(String recuperarPropiedadEntidad) {
		LinkedList<Video> videos = new LinkedList<>();
		AdaptadorVideoTDS adaptadorVi = AdaptadorVideoTDS.getUnicaInstancia();
		StringTokenizer token = new StringTokenizer(recuperarPropiedadEntidad, " ");
		while (token.hasMoreTokens()) {
			videos.add(adaptadorVi.recuperarVideo(Integer.valueOf(token.nextToken())));
		}
		return videos;
	}

}
