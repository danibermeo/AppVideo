package persistencia;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import beans.Entidad;
import beans.Propiedad;
import modelo.ListaVideos;
import modelo.Usuario;
import modelo.Video;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorUsuarioTDS implements IAdaptadorUsuarioDAO {
	@SuppressWarnings("unused")
	private static ServicioPersistencia servPersistencia;
	private static AdaptadorUsuarioTDS unicaInstancia = null;
	@SuppressWarnings("unused")
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
				new Propiedad("premium", String.valueOf(usuario.isPremium()))

		)));

		eUsuario = servPersistencia.registrarEntidad(eUsuario);
		usuario.setCodigo(usuario.getCodigo());
		return eUsuario.getId();

	}

	@Override
	public void borrarUsuario(Usuario cliente) {
		// TODO Auto-generated method stub

	}

	@Override
	public void modificarUsuario(Usuario cliente) {
		// TODO Auto-generated method stub

	}

	@Override
	public Usuario recuperarCliente(int codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> recuperarTodosUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}

}
