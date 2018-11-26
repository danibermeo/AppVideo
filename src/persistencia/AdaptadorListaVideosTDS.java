package persistencia;

import java.util.List;

import modelo.ListaVideos;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorListaVideosTDS implements IAdaptadorListaVideosDAO {

	@SuppressWarnings("unused")
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
	public void registrarListaVideos(ListaVideos videos) {
		
	}

	@Override
	public void borrarListaVideos(ListaVideos videos) {

	}

	@Override
	public void modificarListaVideos(ListaVideos videos) {

	}

	@Override
	public ListaVideos recuperarListaVideos(int codigo) {
		return null;
	}

	@Override
	public List<ListaVideos> recuperarTodasListasVideos() {
		
		return null;
	}

}
