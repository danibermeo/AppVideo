package persistencia;

import java.util.List;

import modelo.ListaVideos;

public interface IAdaptadorListaVideosDAO {
	public int registrarListaVideos(ListaVideos videos);

	public void borrarListaVideos(ListaVideos videos);

	public void modificarListaVideos(ListaVideos videos);

	public ListaVideos recuperarListaVideos(int codigo);

	public List<ListaVideos> recuperarTodasListasVideos();
}
