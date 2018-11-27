package persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import beans.Entidad;
import beans.Propiedad;

import modelo.ListaVideos;
import modelo.Video;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorListaVideosTDS implements IAdaptadorListaVideosDAO {

	private static ServicioPersistencia servPersistencia;
	private static AdaptadorListaVideosTDS unicaInstancia = null;

	public static AdaptadorListaVideosTDS getUnicaInstancia() {
		if (unicaInstancia == null)
			return new AdaptadorListaVideosTDS();
		return unicaInstancia;
	}

	private AdaptadorListaVideosTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	@Override
	public int registrarListaVideos(ListaVideos listaVideos) {

		Entidad eListaVideos;
		boolean exite = true;
		try {
			eListaVideos = servPersistencia.recuperarEntidad(listaVideos.getCodigo());
		} catch (Exception e) {
			exite = false;
		}
		if (exite) {
			return -1;
		}
		// crear Entidad
		eListaVideos = new Entidad();
		eListaVideos.setNombre("listaVideos");
		eListaVideos.setPropiedades(
				new ArrayList<Propiedad>(Arrays.asList(new Propiedad("nombreLista", listaVideos.getNombreLista()),
						new Propiedad("numeroVideos", String.valueOf(listaVideos.getNumeroVideos())),
						new Propiedad("videos", obtenerCodigosListaVideos(listaVideos.getVideos())))));

		eListaVideos = servPersistencia.registrarEntidad(eListaVideos);
		listaVideos.setCodigo(eListaVideos.getId());
		// PoolDAO.getUnicaInstancia().addObjeto(video.getCodigo(), video);
		// mirar
		return eListaVideos.getId();

	}

	@Override
	public void borrarListaVideos(ListaVideos listaVideos) {
		Entidad eListaVideos = servPersistencia.recuperarEntidad(listaVideos.getCodigo());
		servPersistencia.borrarEntidad(eListaVideos);
	}

	@Override
	public void modificarListaVideos(ListaVideos listaVideos) {
		Entidad eListaVideos;

		eListaVideos = servPersistencia.recuperarEntidad(listaVideos.getCodigo());
		servPersistencia.eliminarPropiedadEntidad(eListaVideos, "nombreLista");
		servPersistencia.anadirPropiedadEntidad(eListaVideos, "nombreLista", listaVideos.getNombreLista());
		servPersistencia.eliminarPropiedadEntidad(eListaVideos, "numeroVideos");
		servPersistencia.anadirPropiedadEntidad(eListaVideos, "numeroVideos",
				String.valueOf(listaVideos.getNumeroVideos()));

		for (Video video : listaVideos.getVideos()) {
			AdaptadorVideoTDS.getUnicaInstancia().registrarVideo(video);
		}
		String videos = obtenerCodigosListaVideos(listaVideos.getVideos());
		servPersistencia.eliminarPropiedadEntidad(eListaVideos, "videos");
		servPersistencia.anadirPropiedadEntidad(eListaVideos, "videos", videos);

	}

	@Override
	public ListaVideos recuperarListaVideos(int codigo) {

		// Si la entidad est� en el pool la devuelve directamente
		if (PoolDAO.getUnicaInstancia().contiene(codigo))
			return (ListaVideos) PoolDAO.getUnicaInstancia().getObjeto(codigo);

		// si no, la recupera de la base de datos
		// recuperar entidad
		Entidad eListaVideos = null;

		try {
			eListaVideos = servPersistencia.recuperarEntidad(codigo);
		} catch (NullPointerException e) {
			System.err.println("Adaptador Listas");
		}

		// recuperar propiedades que no son objetos
		// fecha
		String nombreLista = servPersistencia.recuperarPropiedadEntidad(eListaVideos, "nombreLista");

		ListaVideos listaVideos = new ListaVideos(nombreLista);
		listaVideos.setCodigo(codigo);

		// IMPORTANTE:a�adir la listaCanciones al pool antes de llamar a otros
		// adaptadores
		// PoolDAO.getUnicaInstancia().addObjeto(id, listaCanciones);

		// recuperar propiedades que son objetos llamando a adaptadores
		// cliente
		// AdaptadorCancion adaptadorCancion =
		// AdaptadorCancion.getUnicaInstancia();

		// lineas de listaCanciones
		List<Video> videos = obtenerVideosDesdeCodigos(
				servPersistencia.recuperarPropiedadEntidad(eListaVideos, "videos"));
		for (Video video : videos) {
			listaVideos.addVideo(video);
		}

		// devolver el objeto listaCanciones
		PoolDAO.getUnicaInstancia().addObjeto(codigo, listaVideos);
		return listaVideos;
	}

	@Override
	public List<ListaVideos> recuperarTodasListasVideos() {
		LinkedList<ListaVideos> listaVideo = new LinkedList<ListaVideos>();
		List<Entidad> eListaVideos = servPersistencia.recuperarEntidades("listaVdios");
		for (Entidad eLista : eListaVideos) {
			listaVideo.add(recuperarListaVideos(eLista.getId()));
		}
		return listaVideo;
	}

	/*-------------------------Funciones Auxiliares------------------------------------*/

	private String obtenerCodigosListaVideos(List<Video> videos) {

		String codigos = "";
		for (Video video : videos) {
			codigos += video.getCodigo() + " ";
		}
		return codigos.trim();
	}

	public void borrarListas() {
		List<Entidad> entidades = servPersistencia.recuperarEntidades("listaVideos");
		for (Entidad eVideos : entidades) {
			servPersistencia.borrarEntidad(eVideos);
		}

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
