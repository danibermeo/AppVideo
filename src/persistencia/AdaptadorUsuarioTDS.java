package persistencia;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;



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
		eUsuario.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(new Propiedad("usuario", usuario.getNombre()),
				new Propiedad("password", usuario.getPassword()),
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

	public List<Usuario> getUsuarios() {
		List<Usuario> usuarios = new LinkedList<>();
		List<Entidad> eUsuarios = servPersistencia.recuperarEntidades("usuario");
		assert (!eUsuarios.isEmpty());
		for (Entidad entidad : eUsuarios) {
			usuarios.add(recuperarUsuario(entidad.getId()));
		}

		return usuarios;
	}

	@Override
	public void modificarUsuario(Usuario cliente) {


	}

	@Override
	public Usuario recuperarUsuario(int codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> recuperarTodosUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}

	private String obtenerCodigosListasVideos(LinkedList<ListaVideos> listasVideos) {

		String codigos = "";
		for (ListaVideos listaVideo : listasVideos) {
			codigos += listaVideo.getCodigo() + " ";
		}
		return codigos.trim();
	}

	@SuppressWarnings("unused")
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

	@SuppressWarnings("unused")
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
