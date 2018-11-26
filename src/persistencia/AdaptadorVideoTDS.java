package persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import beans.Entidad;
import beans.Propiedad;
import modelo.Etiqueta;

import modelo.Video;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorVideoTDS implements IAdaptadorVideo {
	private static ServicioPersistencia servPersistencia;
	private static AdaptadorVideoTDS unicaInstancia = null;

	public static AdaptadorVideoTDS getUnicaInstancia() {
		if (unicaInstancia == null)
			return new AdaptadorVideoTDS();
		return unicaInstancia;
	}

	private AdaptadorVideoTDS() {

		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	@Override
	public int registrarVideo(Video video) {
		Entidad eVideo;
		boolean exite = true;
		try {
			eVideo = servPersistencia.recuperarEntidad(video.getCodigo());
		} catch (Exception e) {
			exite = false;
		}
		if (exite) {
			return -1;
		}
		// crear Entidad
		eVideo = new Entidad();
		eVideo.setNombre("video");
		eVideo.setPropiedades(new ArrayList<Propiedad>(
				Arrays.asList(new Propiedad("titulo", video.getTitulo()), new Propiedad("url", video.getUrl()),
						new Propiedad("nRepr", String.valueOf(video.getNumeroReproducciones())),
						new Propiedad("etiquetas", obtenerCodigosListasEtiqueta(video.getEtiquetas())))));
		eVideo = servPersistencia.registrarEntidad(eVideo);
		video.setCodigo(eVideo.getId());
		// PoolDAO.getUnicaInstancia().addObjeto(video.getCodigo(), video); mirar
		return eVideo.getId();
	}

	@Override
	public void borrarVideo(Video video) {
		Entidad eVideo = servPersistencia.recuperarEntidad(video.getCodigo());
		try {
			servPersistencia.borrarEntidad(eVideo);
		} catch (NullPointerException e) {

		}
	}

	@Override
	public void modificarVideo(Video video) {
		Entidad eVideo = servPersistencia.recuperarEntidad(video.getCodigo());
		servPersistencia.eliminarPropiedadEntidad(eVideo, "titulo");
		servPersistencia.anadirPropiedadEntidad(eVideo, "titulo", video.getTitulo());
		servPersistencia.eliminarPropiedadEntidad(eVideo, "url");
		servPersistencia.anadirPropiedadEntidad(eVideo, "url", video.getUrl());
		servPersistencia.eliminarPropiedadEntidad(eVideo, "numeroReproduccion");
		servPersistencia.anadirPropiedadEntidad(eVideo, "numeroReproduccion",
				String.valueOf(video.getNumeroReproducciones()));
		servPersistencia.eliminarPropiedadEntidad(eVideo, "etiquetas");
		servPersistencia.anadirPropiedadEntidad(eVideo, "etiquetas",
				obtenerCodigosListasEtiqueta(video.getEtiquetas()));

	}

	@Override
	public Video recuperarVideo(int codigo) {

		// POOOL
		if (PoolDAO.getUnicaInstancia().contiene(codigo)) {

			return (Video) PoolDAO.getUnicaInstancia().getObjeto(codigo);
		}
		Entidad eVideo = null;
		String titulo;
		String url;
		Integer numeroReproducciones;

		try {
			eVideo = servPersistencia.recuperarEntidad(codigo);
		} catch (NullPointerException e) {
			e.printStackTrace();
			System.err.println("Adaptador Listas");
		}

		titulo = servPersistencia.recuperarPropiedadEntidad(eVideo, "titulo");
		url = servPersistencia.recuperarPropiedadEntidad(eVideo, "url");
		numeroReproducciones = Integer
				.valueOf(servPersistencia.recuperarPropiedadEntidad(eVideo, "numeroReproducciones"));

		Video video = new Video(codigo, url, titulo);
		video.setNumeroReproducciones(numeroReproducciones);

		// Pool issues?
		// TODO verificar objetos globalmente compartidos
		video.setCodigo(codigo);
		PoolDAO.getUnicaInstancia().addObjeto(codigo, video);
		String stringEtiquetas = obtenerCodigosListasEtiqueta(video.getEtiquetas());
		List<Etiqueta> etiquetas = new LinkedList<>();
		etiquetas = obtenerEtiquetasDesdeCodigos(stringEtiquetas);

		for (Etiqueta etiqueta : etiquetas) {
			video.addEtiquetas(etiqueta);
		}
		return video;
	}

	@Override
	public List<Video> recuperarTodosVideos() {
		List<Video> videos = new LinkedList<>();
		List<Entidad> entidades = servPersistencia.recuperarEntidades("video");

		for (Entidad eVideo : entidades) {
			videos.add(recuperarVideo(eVideo.getId()));
		}
		return videos;

	}

	/*---------------------------funciones auxiliares ----------------------------------*/
	public void borrarVideos() {
		List<Entidad> entidades = servPersistencia.recuperarEntidades("video");
		for (Entidad eVideos : entidades) {
			servPersistencia.borrarEntidad(eVideos);
		}
	}

	private String obtenerCodigosListasEtiqueta(List<Etiqueta> list) {

		String codigos = "";
		for (Etiqueta eti : list) {
			codigos += eti.toString() + " ";
		}
		return codigos.trim();
	}

	private List<Etiqueta> obtenerEtiquetasDesdeCodigos(String recuperarPropiedadEntidad) {
		List<Etiqueta> etiquetas = new LinkedList<>();
		StringTokenizer token = new StringTokenizer(recuperarPropiedadEntidad, " ");
		while (token.hasMoreTokens()) {
			Etiqueta eti = new Etiqueta(token.nextToken());
			etiquetas.add(eti);
		}
		return etiquetas;
	}
}
