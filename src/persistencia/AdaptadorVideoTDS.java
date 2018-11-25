package persistencia;

import java.util.List;

import modelo.Video;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class AdaptadorVideoTDS implements IAdaptadorVideo {
	@SuppressWarnings("unused")
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
	public void registrarVideo(Video videos) {
		// TODO Auto-generated method stub

	}

	@Override
	public void borrarVideo(Video video) {
		// TODO Auto-generated method stub

	}

	@Override
	public void modificarVideo(Video video) {
		// TODO Auto-generated method stub

	}

	@Override
	public Video recuperarVideo(int codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Video> recuperarTodosVideos() {
		// TODO Auto-generated method stub
		return null;
	}

}
