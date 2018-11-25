package persistencia;

import java.util.List;

import modelo.Video;

public interface IAdaptadorVideo {
	public void registrarVideo(Video videos);

	public void borrarVideo(Video video);

	public void modificarVideo(Video video);

	public Video recuperarVideo(int codigo);

	public List<Video> recuperarTodosVideos();
}
