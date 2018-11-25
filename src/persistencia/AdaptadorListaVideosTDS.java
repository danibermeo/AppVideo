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
		// TODO Auto-generated method stub

	}

	@Override
	public void borrarListaVideos(ListaVideos videos) {
		// TODO Auto-generated method stub

	}

	@Override
	public void modificarListaVideos(ListaVideos videos) {
		// TODO Auto-generated method stub

	}

	@Override
	public ListaVideos recuperarListaVideos(int codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ListaVideos> recuperarTodasListasVideos() {
		// TODO Auto-generated method stub
		return null;
	}

}
